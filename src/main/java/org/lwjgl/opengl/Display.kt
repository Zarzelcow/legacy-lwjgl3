package org.lwjgl.opengl

import org.lwjgl.glfw.GLFWWindowSizeCallback
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.system.MemoryUtil
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import java.nio.ByteBuffer

object Display {
    @JvmStatic
    var title: String = ""
        set(value) {
            field = value
            if (isCreated) {
                GLFW.glfwSetWindowTitle(handle, value)
            }
        }
    @JvmStatic
    var handle: Long = -1
    private var resizable = false

    @JvmStatic
    var displayMode: DisplayMode = DisplayMode(640, 480, 24, 60)

    @JvmStatic
    var width = 0

    @JvmStatic
    var height = 0

    @JvmStatic
    val desktopDisplayMode: DisplayMode?
     get() { return availableDisplayModes.maxByOrNull { it.width * it.height } }
    private var window_resized = false
    private var sizeCallback: GLFWWindowSizeCallback? = null
    @JvmStatic
    fun setIcon(icons: Array<ByteBuffer>): Int {
        // TODO implement this
        return 0;
    }

    @JvmStatic
    fun update() {
        window_resized = false
        GLFW.glfwPollEvents()
        GLFW.glfwSwapBuffers(handle)
    }

    @JvmStatic
    fun create(pixelFormat: PixelFormat?) {
        // Setup an error callback. The default implementation
        GLFWErrorCallback.createPrint(System.err).set()
        check(GLFW.glfwInit()) { "Unable to initialize GLFW" }
        // Configure GLFW
        GLFW.glfwDefaultWindowHints() // optional, the current window hints are already the default
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE) // the window will stay hidden after creation
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, if (resizable) GLFW.GLFW_TRUE else GLFW.GLFW_FALSE)
        handle =
            GLFW.glfwCreateWindow(displayMode.width, displayMode.height, title, MemoryUtil.NULL, MemoryUtil.NULL)
        width = displayMode.width
        height = displayMode.height
        GLFW.glfwMakeContextCurrent(handle)
        GL.createCapabilities()
        // create general callbacks
        sizeCallback = GLFWWindowSizeCallback.create(Display::resizeCallback)
        GLFW.glfwSetWindowSizeCallback(handle, sizeCallback)
        Mouse.setWindow(handle)
        Keyboard.setWindow(handle)
        GLFW.glfwShowWindow(handle)
    }

    @JvmStatic
    fun setFullscreen(fullscreen: Boolean) {
        println("setFullscreen: $fullscreen")
    }

    @JvmStatic
    val availableDisplayModes: Array<DisplayMode>
        get() {
            val primaryMonitor = GLFW.glfwGetPrimaryMonitor()
            if (primaryMonitor == MemoryUtil.NULL) {
                return arrayOf()
            }
            val videoModes = GLFW.glfwGetVideoModes(primaryMonitor) ?: error("No video modes found")
            return videoModes.map { mode ->
                DisplayMode(
                    mode.width(),
                    mode.height(),
                    mode.redBits() + mode.blueBits() + mode.greenBits(),
                    mode.refreshRate()
                )
            }.toHashSet().toTypedArray()
        }

    private fun resizeCallback(window: Long, width: Int, height: Int) {
        if (window == handle) {
            window_resized = true
            Display.width = width
            Display.height = height
        }
    }

    fun destroyWindow() {
        // free callbacks
        sizeCallback!!.free()
        Mouse.destroy()
        Keyboard.destroy()
        // Destroy the window
        GLFW.glfwDestroyWindow(handle)
    }

    @JvmStatic
    fun destroy() {
        destroyWindow()
        // Terminate GLFW and free the error callback
        GLFW.glfwTerminate()
        GLFW.glfwSetErrorCallback(null)!!.free()
    }

    @JvmStatic
    val isCreated: Boolean
        get() = handle != -1L

    @JvmStatic
    val isCloseRequested: Boolean
        get() = GLFW.glfwWindowShouldClose(handle)

    @JvmStatic
    val isActive: Boolean
        get() = true

    @JvmStatic
    fun setResizable(isResizable: Boolean) {
        resizable = isResizable
        if (isCreated) {
            GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, if (resizable) GLFW.GLFW_TRUE else GLFW.GLFW_FALSE)
        }
    }

    @JvmStatic
    fun sync(fps: Int) {
        Sync.sync(fps)
    }

    @JvmStatic
    fun setVSyncEnabled(enabled: Boolean) {
        GLFW.glfwSwapInterval(if (enabled) 1 else 0)
    }

    @JvmStatic
    fun wasResized(): Boolean = window_resized
}