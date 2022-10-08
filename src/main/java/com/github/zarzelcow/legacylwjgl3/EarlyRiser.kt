package com.github.zarzelcow.legacylwjgl3

import javassist.*
import javassist.util.proxy.DefineClassHelper
import net.fabricmc.loader.impl.launch.FabricLauncherBase
import org.lwjgl.openal.ALCapabilities
import org.lwjgl.opengl.GL

/**
 * Uses gross hacks to "redefine" classes that i couldnt find a way to edit with mixins.
 *
 * Uses javassist to edit the classes e.x adding fields and methods for compatibility with legacy LWJGL2 code.
 *
 * @author Zarzelcow
 */
class EarlyRiser : Runnable {
    override fun run() {
        LegacyLWJGL3.LOGGER.debug("EarlyRiser running")
        val pool = ClassPool()
        pool.appendClassPath(LoaderClassPath(FabricLauncherBase.getLauncher().targetClassLoader)) // knot class loader contains the fat jar with lwjgl 3 bundled

        macroRedefineWithErrorHandling(pool, ::addMissingGLCapabilities)
        macroRedefineWithErrorHandling(pool, ::addLegacyCompatibilityMethodsToGL11)
        macroRedefineWithErrorHandling(pool, ::gl20AddCompatibilityMethods)
        macroRedefineWithErrorHandling(pool, ::copyAlExtensions)
        macroRedefineWithErrorHandling(pool, ::addLegacyCompatibilityMethodsToAL10)
    }

    // rather than handling errors is every method manually, this will do it for you
    private inline fun macroRedefineWithErrorHandling(pool: ClassPool, method: (ClassPool) -> Unit) {
        try {
            method(pool)
        } catch (e: Exception) {
            LegacyLWJGL3.LOGGER.error("Failed in early riser while attempting to do hacky things", e)
        }
    }

    companion object {
        // Adds missing extension checks from LWJGL2 for use in ContextCapabilities
        private fun addMissingGLCapabilities(classPool: ClassPool) {
            with(classPool.get("org.lwjgl.opengl.GLCapabilities")) {
                this.addField(CtField.make("public final boolean GL_EXT_multi_draw_arrays;", this))
                this.addField(CtField.make("public final boolean GL_EXT_paletted_texture;", this))
                this.addField(CtField.make("public final boolean GL_EXT_rescale_normal;", this))
                this.addField(CtField.make("public final boolean GL_EXT_texture_3d;", this))
                this.addField(CtField.make("public final boolean GL_EXT_texture_lod_bias;", this))
                this.addField(CtField.make("public final boolean GL_EXT_vertex_shader;", this))
                this.addField(CtField.make("public final boolean GL_EXT_vertex_weighting;", this))
                val constructor =
                    this.getConstructor("(Lorg/lwjgl/system/FunctionProvider;Ljava/util/Set;ZLjava/util/function/IntFunction;)V")
                constructor.insertAfter(
                    """
                            GL_EXT_multi_draw_arrays = ext.contains("GL_EXT_multi_draw_arrays");
                            GL_EXT_paletted_texture = ext.contains("GL_EXT_paletted_texture");
                            GL_EXT_rescale_normal = ext.contains("GL_EXT_rescale_normal");
                            GL_EXT_texture_3d = ext.contains("GL_EXT_texture_3d");
                            GL_EXT_texture_lod_bias = ext.contains("GL_EXT_texture_lod_bias");
                            GL_EXT_vertex_shader = ext.contains("GL_EXT_vertex_shader");
                            GL_EXT_vertex_weighting = ext.contains("GL_EXT_vertex_weighting");
                        """.trimIndent()
                )
                defineCtClass(this, GL::class.java, classPool.classLoader)
            }
        }

        // list of legacy methods that we need to add to GL11. moved out of method for readability
        private val gl11Translations = listOf(
            Triple("glGetFloat", "glGetFloatv", "(ILjava/nio/FloatBuffer;)V"),
            Triple("glGetInteger", "glGetIntegerv", "(ILjava/nio/IntBuffer;)V"),
            Triple("glFog", "glFogfv", "(ILjava/nio/FloatBuffer;)V"),
            Triple("glLight", "glLightfv", "(IILjava/nio/FloatBuffer;)V"),
            Triple("glLightModel", "glLightModelfv", "(ILjava/nio/FloatBuffer;)V"),
            Triple("glMultMatrix", "glMultMatrixf", "(Ljava/nio/FloatBuffer;)V"),
            Triple("glTexEnv", "glTexEnvfv", "(IILjava/nio/FloatBuffer;)V")
        )

        private val al10Translations = listOf(
            Triple("alListener", "alListenerfv", "(ILjava/nio/FloatBuffer;)V"),
            Triple("alSource", "alSourcefv", "(IILjava/nio/FloatBuffer;)V"),
            Triple("alSourceStop", "alSourceStopv", "(Ljava/nio/IntBuffer;)V")
        )

        /*
         * Adds a few legacy methods to GL11 like glGetFloat which was renamed to glGetFloatv in LWJGL3
         */
        fun addLegacyCompatibilityMethodsToGL11(classPool: ClassPool) {
            val cc = classPool.get("org.lwjgl.opengl.GL11")
            for ((legacy, current, desc) in gl11Translations) {
                val original = cc.getMethod(current, desc)
                // copy original method and rename it
                val renamed = CtNewMethod.copy(original, cc, null)
                    .rename(legacy)

                cc.addMethod(renamed)
                debug("Added legacy compat method $legacy")
            }
            defineCtClass(cc, GL::class.java, classPool.classLoader)
        }

        // new GL20 doesn't have a way to supply a shader source using ByteBuffer so this adds a method to do it
        fun gl20AddCompatibilityMethods(classPool: ClassPool) {
            val cc = classPool.get("org.lwjgl.opengl.GL20")
            val code = """
                public static void glShaderSource(int shader, java.nio.ByteBuffer string) {
                    byte[] data = new byte[string.limit()];
                    string.position(0);
                    string.get(data);
                    string.position(0);
                    org.lwjgl.opengl.GL20.glShaderSource(shader, new String(data));
                }
            """.trimIndent()
            cc.addMethod(CtNewMethod.make(code, cc))
            defineCtClass(cc, GL::class.java, classPool.classLoader)
        }

        /**
         * It copies all the methods and fields from the extension class to the real class
         */
        fun copyAlExtensions(classPool: ClassPool) {
            val extension = classPool.get("com.github.zarzelcow.legacylwjgl3.ALExtensions")
            val target = classPool.get("org.lwjgl.openal.AL")
            // this code is hacky, but it replaces the stub method with the real one
            target.getMethod("destroy", "()V")
                .rename("al_destroy") // rename destroy to al_destroy so it doesnt conflict
            extension.removeMethod(
                extension.getMethod(
                    "al_destroy",
                    "()V"
                )
            ) // remove stub method from extension so it calls the real one

            // copy methods and felds from cc to target
            for (method in extension.declaredMethods) {
                val copied = CtNewMethod.copy(method, target, null)
                // dont add method if it already exists
                target.addMethod(copied)
                debug("Added AL extension method ${method.name}")
            }

            for (field in extension.declaredFields) {
                val copied = CtField(field, target)
                target.addField(copied)
                debug("Added AL extension field ${field.name}")
            }
            defineCtClass(target, ALCapabilities::class.java, classPool.classLoader)
        }

        /**
         * Adds legacy compatibility methods to AL10
         */
        fun addLegacyCompatibilityMethodsToAL10(classPool: ClassPool) {
            val cc = classPool.get("org.lwjgl.openal.AL10")
            for ((legacy, current, desc) in al10Translations) {
                val original = cc.getMethod(current, desc)
                // copy original method and rename it
                val copied = CtNewMethod.copy(original, cc, null)
                cc.addMethod(copied.rename(legacy))

                debug("Added legacy compat method $legacy")
            }
            defineCtClass(cc, ALCapabilities::class.java, classPool.classLoader)
        }

        private fun CtMethod.rename(legacy: String): CtMethod {
            this.name = legacy
            return this
        }

        private fun debug(message: String) = LegacyLWJGL3.LOGGER.debug(message)

        // all the Javassist [CtClass.toClass] methods use new java features,
        // DefineClassHelper.toClass has support for much older versions of java so use that instead
        private fun defineCtClass(cc: CtClass, neighbor: Class<*>, classLoader: ClassLoader) {
            DefineClassHelper.toClass(
                cc.name,
                neighbor,
                classLoader,
                null,
                cc.toBytecode()
            )
            cc.detach()
        }
    }
}