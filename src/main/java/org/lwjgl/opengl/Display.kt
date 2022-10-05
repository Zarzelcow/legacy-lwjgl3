package org.lwjgl.opengl

import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWImage
import org.lwjgl.glfw.GLFWWindowSizeCallback
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse
import org.lwjgl.system.MemoryUtil
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.math.sqrt


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

    private var cached_icons: Array<ByteBuffer>? = null
    @JvmStatic
    fun setIcon(icons: Array<ByteBuffer>): Int {
        // LWJGL2 doesn't enforce this to be called after window creation,
        // meaning you have to keep hold the icons to use them when the window is created
        if (!this.cached_icons.contentEquals(icons)) {
            // you have to also clone the byte buffers to avoid seg faults from them being freed
            cached_icons = icons.map { cloneByteBuffer(it) }.toTypedArray()
        }

        if (this.isCreated) {
            GLFW.glfwSetWindowIcon(this.handle, iconsToGLFWBuffer(this.cached_icons!!))
            return 1
        } else {
            return 0
        }
    }


    private fun cloneByteBuffer(original: ByteBuffer): ByteBuffer {
        // code taken from LWJGL2' Display.java
        val clone = BufferUtils.createByteBuffer(original.capacity())
        val old_position = original.position()
        clone.put(original)
        // if compiled with java 11+ calls ByteBuffer.position/ByteBuffer.flip however java <= 8 doesnt have these
        // methods and instead compiles as calls to Buffer.position/Buffer.flip causing crashes on those java versions
        (original as Buffer).position(old_position)
        (clone as Buffer).flip()

        return clone
    }
    private fun iconsToGLFWBuffer(icons: Array<ByteBuffer>): GLFWImage.Buffer {
        val buffer = GLFWImage.create(icons.size)
        icons.forEach { icon ->
            val size: Int = icon.limit() / 4
            val dimension = sqrt(size.toDouble()).toInt()
            GLFWImage.malloc().use { image ->
                buffer.put(image.set(dimension, dimension, icon))
            }
        }
        buffer.flip()
        return buffer
    }

    @JvmStatic
    fun update() {
        window_resized = false
        if ( Mouse.isCreated() ) {
            Mouse.poll()
//            Mouse.updateCursor();
        }

        if ( Keyboard.isCreated() ) {
            Keyboard.poll()
        }
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
        Mouse.create()
        Keyboard.create()
        GLFW.glfwShowWindow(handle)
        if (this.cached_icons != null) {
            this.setIcon(this.cached_icons!!)
        }
    }

    /**
     * This is a shit code snippet, but at least it works.
     */
    @JvmStatic
    fun setFullscreen(fullscreen: Boolean) {
        println("setFullscreen: $fullscreen")
        runCatching {
            this.resizeCallback(handle, displayMode.width, displayMode.height)
            if (fullscreen) {
                var monitor = GLFW.glfwGetPrimaryMonitor()

                GLFW.glfwSetWindowMonitor(
                    handle,
                    monitor,
                    0,
                    0,
                    width,
                    height,
                    displayMode.frequency
                )

                GLFW.glfwSetWindowSize(handle, width, height)
            } else {

                GLFW.glfwSetWindowMonitor(
                    handle,
                    0L,
                    20,// need a xPos
                    20,// need a yPos
                    width,
                    height,
                    -1
                )
                GLFW.glfwSetWindowSize(handle, width, height)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    @JvmStatic
    fun updateFullscreen() {

    }
    @JvmStatic
    fun setMode() {

    }

    @JvmStatic
    fun updateDisplay() {
        Display.flipFrame()
    }
    @JvmStatic
    fun flipFrame() {
        GLFW.glfwPollEvents()
        GLFW.glfwSwapBuffers(handle)
        GLFW.glfwPollEvents()
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
        GLFW.glfwSetErrorCallback(null)?.free()
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