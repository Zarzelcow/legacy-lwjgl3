package com.github.zarzelcow.legacylwjgl3

import javassist.ClassPool
import javassist.CtField
import javassist.CtMethod
import javassist.CtNewMethod
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
        LegacyLWJGL3.LOGGER.info("EarlyRiser running")
        val pool = ClassPool.getDefault()
        macroRedefineWithErrorHandling(pool, ::addLegacyCompatibilityMethodsToGL11)
        macroRedefineWithErrorHandling(pool, ::gl20AddCompatibilityMethods)
        macroRedefineWithErrorHandling(pool, ::copyAlExtensions)
        macroRedefineWithErrorHandling(pool, ::addLegacyCompatibilityMethodsToAL10)
    }

    // helper function to increase code readability
    private inline fun macroRedefineWithErrorHandling(pool: ClassPool, method: (ClassPool) -> Either<Exception, Int>) {
        when (val result = method(pool)) {
            is Either.Left -> {
                LegacyLWJGL3.LOGGER.error("Failed in early riser while attempting to hacky things")
                result.value.printStackTrace()
            }
            is Either.Right -> Unit // noop since we're good
        }
    }

    companion object {
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

        // same reason as above
        private val al10Translations = listOf(
            Triple("alListener", "alListenerfv", "(ILjava/nio/FloatBuffer;)V"),
            Triple("alSource", "alSourcefv", "(IILjava/nio/FloatBuffer;)V"),
            Triple("alSourceStop", "alSourceStopv", "(Ljava/nio/IntBuffer;)V")
        )

        private fun CtMethod.rename(legacy: String): CtMethod {
            this.name = legacy
            return this
        }

        private fun debug(message: String) = LegacyLWJGL3.LOGGER.debug(message)


        /*
         * Adds a few legacy methods to GL11 like glGetFloat which was renamed to glGetFloatv in LWJGL3
         */
        fun addLegacyCompatibilityMethodsToGL11(classPool: ClassPool): Either<Exception, Int> {
            val cc = classPool.get("org.lwjgl.opengl.GL11")
            return try {
                for ((legacy, current, desc) in gl11Translations) {
                    val original = cc.getMethod(current, desc)
                    // copy original method and rename it
                    val copied = CtNewMethod.copy(original, cc, null)
                    copied.name = legacy

                    cc.addMethod(copied.rename(legacy))
                    debug("Added legacy compat method $legacy")
                }
                cc.toClass(GL::class.java)
                Either.Right(1)
            } catch (e: Exception) {
                Either.Left(e)
            }
        }

        // new GL20 doesn't have a way to supply a shader source using ByteBuffer so this adds a method to do it
        fun gl20AddCompatibilityMethods(classPool: ClassPool): Either<Exception, Int> {
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
            return try {
                cc.addMethod(CtNewMethod.make(code, cc))
                cc.toClass(GL::class.java)
                return Either.Right(1)
            } catch (e: Exception) {
                Either.Left(e)
            }
        }

        /**
         * It copies all the methods and fields from the extension class to the real class
         */
        fun copyAlExtensions(classPool: ClassPool): Either<Exception, Int> {
            val extension = classPool.get("com.github.zarzelcow.legacylwjgl3.ALExtensions")
            val target = classPool.get("org.lwjgl.openal.AL")
            return try {
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
                target.toClass(ALCapabilities::class.java)
                extension.detach()
                return Either.Right(1)
            } catch (e: Exception) {
                Either.Left(e)
            }
        }

        /**
         * Adds legacy compatibility methods to AL10
         */
        fun addLegacyCompatibilityMethodsToAL10(classPool: ClassPool): Either<Exception, Int> {
            val cc = classPool.get("org.lwjgl.openal.AL10")
            return try {
                for ((legacy, current, desc) in al10Translations) {
                    val original = cc.getMethod(current, desc)
                    // copy original method and rename it
                    val copied = CtNewMethod.copy(original, cc, null)
                    copied.name = legacy

                    cc.addMethod(copied.rename(legacy))
                    debug("Added legacy compat method $legacy")
                }
                cc.toClass(ALCapabilities::class.java)
                Either.Right(1)
            } catch (e: Exception) {
                Either.Left(e)
            }
        }

        // Functional programming helper "Either" based off of the arrow-kt library
        open class Either<L, R> {
            data class Left<L, R>(val value: L) : Either<L, R>()
            data class Right<L, R>(val value: R) : Either<L, R>()
//            companion object {
//                fun <L, R> left(value: L) = Left<L, R>(value)
//                fun <L, R> right(value: R) = Right<L, R>(value)
//            }
        }
    }
}