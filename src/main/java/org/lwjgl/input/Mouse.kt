/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.input

import org.lwjgl.opengl.Display
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWCursorPosCallback
import org.lwjgl.glfw.GLFWMouseButtonCallback
import org.lwjgl.glfw.GLFWScrollCallback
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.math.max
import kotlin.math.min

/**
 * @author Created by gudenau on 5/31/2017
 * <br>
 * A raw Mouse interface. This can be used to poll the current state of the
 * mouse buttons, and determine the mouse movement delta since the last poll.
 *
 * n buttons supported, n being a native limit. A scrolly wheel is also
 * supported, if one such is available. Movement is reported as delta from
 * last position or as an absolute position. If the window has been created
 * the absolute position will be clamped to 0 - width | height.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 */
object Mouse {
    private val eventQueue: Queue<MouseEvent> = ConcurrentLinkedDeque()
    @Volatile
    private var latestEvent: MouseEvent? = null
    private var window: Long = -1
    var mouseButtonCallback: GLFWMouseButtonCallback? = null
    var cursorPosCallback: GLFWCursorPosCallback? = null
    var scrollCallback: GLFWScrollCallback? = null

    fun setWindow(window: Long) {
        // clear the queue and callbacks
        eventQueue.clear()
        destroy()
        Mouse.window = window
        // create glfw callbacks
        mouseButtonCallback = GLFWMouseButtonCallback.create { window1: Long, button: Int, action: Int, mode: Int ->
            if (window == window1) {
                eventQueue.add(MouseEvent(button, action, mode))
            }
        }
        cursorPosCallback = GLFWCursorPosCallback.create { window1: Long, xpos: Double, ypos: Double ->
            if (window1 == window) {
                eventQueue.add(MouseEvent(xpos, ypos, Display.width, Display.height))
                if (isGrabbed) {
                    glfwSetCursorPos(window, Display.width / 2.0, Display.height / 2.0)
                    deltaX += x - Display.width / 2
                    deltaY += y - Display.height / 2
                } else {
                    deltaX += x
                    deltaY += y
                }
            }
        }
        scrollCallback = GLFWScrollCallback.create { window1: Long, xoffset: Double, yoffset: Double ->
            if (window1 == window) {
                eventQueue.add(MouseEvent(yoffset))
            }
        }
        glfwSetScrollCallback(window, scrollCallback)
        glfwSetMouseButtonCallback(window, mouseButtonCallback)
        glfwSetCursorPosCallback(window, cursorPosCallback)

//        GLFW.glfwSetCursorEnterCallback(window) { window1: Long, entered: Boolean -> }
    }

    @JvmStatic
    fun destroy() {
        if (window != -1L) {
            scrollCallback?.free()
            cursorPosCallback?.free()
            mouseButtonCallback?.free()
        }
    }

    @JvmStatic
    val x: Int
        get() = MouseEvent.lastX.toInt()

    @JvmStatic
    val y: Int
        get() = Display.height - 1 - max(
            0,
            min(
                Display.height - 1,
                MouseEvent.lastY.toInt()
            )
        )

    @JvmStatic
    val isCreated: Boolean
        get() = window != -1L

    @JvmStatic
    operator fun next(): Boolean {
        return if (eventQueue.isEmpty()) {
            false
        } else {
            latestEvent =
                eventQueue.poll()
            true
        }
    }

    @JvmStatic
    val eventButton: Int
        get() = latestEvent?.button ?: -1

    @JvmStatic
    val eventButtonState: Boolean
        get() = latestEvent != null && latestEvent!!.action == GLFW.GLFW_PRESS

    @JvmStatic
    val eventDX: Int
        get() = latestEvent?.getDeltaX()?.toInt() ?: 0

    @JvmStatic
    val eventDY: Int
        get() = latestEvent?.getDeltaY()?.toInt() ?: 0

    private var deltaX = 0

    @JvmStatic
    val dX: Int
        get() {
            val x = deltaX
            deltaX = 0
            return x
        }
    private var deltaY = 0

    @JvmStatic
    val dY: Int
        get() {
            val y = deltaY
            deltaY = 0
            return y
        }

    @JvmStatic
    val eventX: Int
        get() = latestEvent?.x?.toInt() ?: 0

    @JvmStatic
    val eventY: Int
        get() = y

    @JvmStatic
    val eventDWheel: Int
        get() = latestEvent?.z?.toInt() ?: 0

    @JvmStatic
    val eventNanoseconds: Long
        get() = latestEvent?.time ?: 0

    @JvmStatic
    fun isButtonDown(button: Int): Boolean {
        return window != -1L && GLFW.glfwGetMouseButton(window, button) == GLFW.GLFW_PRESS
    }

    @JvmStatic
    var isGrabbed: Boolean
        get() = window != -1L && GLFW.glfwGetInputMode(window, GLFW.GLFW_CURSOR) == GLFW.GLFW_CURSOR_DISABLED
        set(grab) {
            if (window != -1L) {
                GLFW.glfwSetInputMode(
                    window,
                    GLFW.GLFW_CURSOR,
                    if (grab) GLFW.GLFW_CURSOR_DISABLED else GLFW.GLFW_CURSOR_NORMAL
                )
                setCursorPos(Display.width / 2, Display.height / 2)
                deltaX = 0
                deltaY = 0
            }
        }

    @JvmStatic
    fun setCursorPosition(new_x: Int, new_y: Int) {
        if (window != -1L) {
            setCursorPos(new_x, new_y)
        }
    }

    private fun setCursorPos(new_x: Int, new_y: Int) {
        if (window != -1L) {
            latestEvent = MouseEvent(new_x.toDouble(), new_y.toDouble(), Display.width, Display.height)

            MouseEvent.lastX = new_x.toDouble()
            MouseEvent.lastY = new_y.toDouble()
            glfwSetCursorPos(window, new_x.toDouble(), new_y.toDouble())
        }
    }

    class MouseEvent {
        val mode: Int
        val button: Int
        val action: Int
        val x: Double
        val y: Double
        val z: Double
        private val width: Int
        private val height: Int
        val time = System.nanoTime()

        internal constructor(button: Int, action: Int, mode: Int) {
            this.button = button
            this.action = action
            this.mode = mode
            x = lastX
            y = lastY
            z = 0.0
            width = 0
            height = 0
        }

        internal constructor(x: Double, y: Double, width: Int, height: Int) {
            lastX = x
            lastY = y
            this.button = -1
            this.action = 0
            this.mode = -1
            this.x = x
            this.y = y
            this.z = 0.0
            this.width = width
            this.height = height
        }

        internal constructor(yoffset: Double) {
            this.button = -1
            this.action = 0
            this.mode = -1
            this.x = 0.0
            this.y = 0.0
            this.z = yoffset
            this.width = 0
            this.height = 0
        }

        fun getDeltaX(): Double {
            return x - width / 2
        }

        fun getDeltaY(): Double {
            return y - height / 2
        }

        companion object {
            var lastX = 0.0
            var lastY = 0.0
        }
    }
}