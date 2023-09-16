package com.github.zarzelcow.legacylwjgl3;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.ImmutableList;
import javassist.*;
import javassist.util.proxy.DefineClassHelper;
import net.fabricmc.loader.impl.launch.FabricLauncherBase;
import org.apache.commons.lang3.tuple.Triple;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.opengl.GL;

/**
 * Uses gross hacks to "redefine" classes that i couldnt find a way to edit with mixins.
 * <p>
 * Uses javassist to edit the classes e.x adding fields and methods for compatibility with legacy LWJGL2 code.
 *
 * @author Zarzelcow
 */
public class EarlyRiser implements Runnable {

	// list of legacy methods that we need to add to GL11. moved out of method for readability
	private final List<Triple<String, String, String>> gl11Translations = ImmutableList.of(
			Triple.of("glGetFloat", "glGetFloatv", "(ILjava/nio/FloatBuffer;)V"),
			Triple.of("glGetInteger", "glGetIntegerv", "(ILjava/nio/IntBuffer;)V"),
			Triple.of("glFog", "glFogfv", "(ILjava/nio/FloatBuffer;)V"),
			Triple.of("glLight", "glLightfv", "(IILjava/nio/FloatBuffer;)V"),
			Triple.of("glLightModel", "glLightModelfv", "(ILjava/nio/FloatBuffer;)V"),
			Triple.of("glMultMatrix", "glMultMatrixf", "(Ljava/nio/FloatBuffer;)V"),
			Triple.of("glTexEnv", "glTexEnvfv", "(IILjava/nio/FloatBuffer;)V")
	);
	private final List<Triple<String, String, String>> al10Translations = ImmutableList.of(
			Triple.of("alListener", "alListenerfv", "(ILjava/nio/FloatBuffer;)V"),
			Triple.of("alSource", "alSourcefv", "(IILjava/nio/FloatBuffer;)V"),
			Triple.of("alSourceStop", "alSourceStopv", "(Ljava/nio/IntBuffer;)V")
	);

	@Override
	public void run() {
		LegacyLWJGL3.LOGGER.debug("EarlyRiser running");
		ClassPool pool = new ClassPool();
		pool.appendClassPath(new LoaderClassPath(FabricLauncherBase.getLauncher().getTargetClassLoader())); // knot class loader contains the fat jar with lwjgl 3 bundled

		macroRedefineWithErrorHandling(pool, this::addMissingGLCapabilities);
		macroRedefineWithErrorHandling(pool, this::addLegacyCompatibilityMethodsToGL11);
		macroRedefineWithErrorHandling(pool, this::addLegacyCompatibilityMethodsToGL20);
		macroRedefineWithErrorHandling(pool, this::copyAlExtensions);
		macroRedefineWithErrorHandling(pool, this::addLegacyCompatibilityMethodsToAL10);
	}

	// rather than handling errors is every method manually, this will do it for you
	private void macroRedefineWithErrorHandling(ClassPool pool, Redefiner method) {
		try {
			method.redefine(pool);
		} catch (Exception e) {
			LegacyLWJGL3.LOGGER.error("Failed in early riser while attempting to do hacky things", e);
		}
	}

	// Adds missing extension checks from LWJGL2 for use in ContextCapabilities
	private void addMissingGLCapabilities(ClassPool classPool) throws NotFoundException, CannotCompileException, IOException {
		CtClass ctClass = classPool.get("org.lwjgl.opengl.GLCapabilities");

		ctClass.addField(CtField.make("public final boolean GL_EXT_multi_draw_arrays;", ctClass));
		ctClass.addField(CtField.make("public final boolean GL_EXT_paletted_texture;", ctClass));
		ctClass.addField(CtField.make("public final boolean GL_EXT_rescale_normal;", ctClass));
		ctClass.addField(CtField.make("public final boolean GL_EXT_texture_3d;", ctClass));
		ctClass.addField(CtField.make("public final boolean GL_EXT_texture_lod_bias;", ctClass));
		ctClass.addField(CtField.make("public final boolean GL_EXT_vertex_shader;", ctClass));
		ctClass.addField(CtField.make("public final boolean GL_EXT_vertex_weighting;", ctClass));
		CtConstructor constructor =
				ctClass.getConstructor("(Lorg/lwjgl/system/FunctionProvider;Ljava/util/Set;ZLjava/util/function/IntFunction;)V");
		constructor.insertAfter(
				"GL_EXT_multi_draw_arrays = ext.contains(\"GL_EXT_multi_draw_arrays\");" +
						"GL_EXT_paletted_texture = ext.contains(\"GL_EXT_paletted_texture\");" +
						"GL_EXT_rescale_normal = ext.contains(\"GL_EXT_rescale_normal\"); " +
						"GL_EXT_texture_3d = ext.contains(\"GL_EXT_texture_3d\");\n" +
						"GL_EXT_texture_lod_bias = ext.contains(\"GL_EXT_texture_lod_bias\");\n" +
						"GL_EXT_vertex_shader = ext.contains(\"GL_EXT_vertex_shader\");\n" +
						"GL_EXT_vertex_weighting = ext.contains(\"GL_EXT_vertex_weighting\");".trim());
		defineCtClass(ctClass, GL.class, classPool.getClassLoader());
	}

	/*
	 * Adds a few legacy methods to GL11 like glGetFloat which was renamed to glGetFloatv in LWJGL3
	 */
	private void addLegacyCompatibilityMethodsToGL11(ClassPool classPool) throws NotFoundException, CannotCompileException, IOException {
		CtClass cc = classPool.get("org.lwjgl.opengl.GL11");
		for (Triple<String, String, String> t : gl11Translations) {
			CtMethod original = cc.getMethod(t.getMiddle(), t.getRight());
			// copy original method and rename it
			CtMethod renamed = CtNewMethod.copy(original, cc, null);
			renamed.setName(t.getLeft());

			cc.addMethod(renamed);
			debug("Added legacy compat method $legacy");
		}
		defineCtClass(cc, GL.class, classPool.getClassLoader());
	}

	// new GL20 doesn't have a way to supply a shader source using ByteBuffer so this adds a method to do it
	private void addLegacyCompatibilityMethodsToGL20(ClassPool classPool) throws NotFoundException, CannotCompileException, IOException {
		CtClass cc = classPool.get("org.lwjgl.opengl.GL20");
		String code = "public static void glShaderSource(int shader, java.nio.ByteBuffer string) {\n" +
				"byte[] data = new byte[string.limit()];\n" +
				"string.position(0);\n" +
				"string.get(data);\n" +
				"string.position(0);\n" +
				"org.lwjgl.opengl.GL20.glShaderSource(shader, new String(data));\n" +
				"}\n" +
				"\n" +
				"public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {\n" +
				"glUniformMatrix4fv(location, transpose, matrices);\n" +
				"}";

		cc.addMethod(CtNewMethod.make(code, cc));
		defineCtClass(cc, GL.class, classPool.getClassLoader());
	}

	/**
	 * It copies all the methods and fields from the extension class to the real class
	 */
	private void copyAlExtensions(ClassPool classPool) throws NotFoundException, CannotCompileException, IOException {
		CtClass extension = classPool.get("com.github.zarzelcow.legacylwjgl3.ALExtensions");
		CtClass target = classPool.get("org.lwjgl.openal.AL");
		// this code is hacky, but it replaces the stub method with the real one
		target.getMethod("destroy", "()V")
				.setName("al_destroy"); // rename destroy to al_destroy so it doesnt conflict
		extension.removeMethod(
				extension.getMethod(
						"al_destroy",
						"()V"
				)
		); // remove stub method from extension so it calls the real one

		// copy methods and felds from cc to target
		for (CtMethod method : extension.getDeclaredMethods()) {
			CtMethod copied = CtNewMethod.copy(method, target, null);
			// dont add method if it already exists
			target.addMethod(copied);
			debug("Added AL extension method ${method.name}");
		}

		for (CtField field : extension.getDeclaredFields()) {
			CtField copied = new CtField(field, target);
			target.addField(copied);
			debug("Added AL extension field ${field.name}");
		}
		defineCtClass(target, ALCapabilities.class, classPool.getClassLoader());
	}

	/**
	 * Adds legacy compatibility methods to AL10
	 */
	private void addLegacyCompatibilityMethodsToAL10(ClassPool classPool) throws NotFoundException, CannotCompileException, IOException {
		CtClass cc = classPool.get("org.lwjgl.openal.AL10");
		for (Triple<String, String, String> t : al10Translations) {
			CtMethod original = cc.getMethod(t.getMiddle(), t.getRight());
			// copy original method and rename it
			CtMethod copied = CtNewMethod.copy(original, cc, null);
			copied.setName(t.getLeft());
			cc.addMethod(copied);

			debug("Added legacy compat method $legacy");
		}
		defineCtClass(cc, ALCapabilities.class, classPool.getClassLoader());
	}

	private void debug(String message) {
		LegacyLWJGL3.LOGGER.debug(message);
	}

	// all the Javassist [CtClass.toClass] methods use new java features,
	// DefineClassHelper.toClass has support for much older versions of java so use that instead
	private void defineCtClass(CtClass cc, Class<?> neighbor, ClassLoader classLoader) throws IOException, CannotCompileException {
		DefineClassHelper.toClass(
				cc.getName(),
				neighbor,
				classLoader,
				null,
				cc.toBytecode()
		);
		cc.detach();
	}

	private interface Redefiner {
		void redefine(ClassPool pool) throws Exception;
	}
}