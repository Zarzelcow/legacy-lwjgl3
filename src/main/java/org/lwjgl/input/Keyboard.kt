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

import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFW.GLFW_MOD_SHIFT
import org.lwjgl.glfw.GLFWKeyCallback
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

// This class is quite hard to attribute since it's a copy of LWJGL's implementation with heavy modifications to make
// it work with LWJGL3, but I also changed a sizeable amount so all 3 ¯\_(ツ)_/¯
/**
 * @author Created by gudenau on 5/31/2017.
 *
 * A raw Keyboard interface. This can be used to poll the current state of the
 * keys, or read all the keyboard presses / releases since the last read.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 */
object Keyboard {
    private val eventQueue: Queue<KeyEvent> = ConcurrentLinkedDeque()
    private var latestEvent: KeyEvent? = null
    private var windowHandle: Long = -1
    var keyCallback: GLFWKeyCallback? = null

    @JvmStatic
    fun getKeyName(key: Int): String? {
        return keyNames[key]
    }

    @JvmStatic
    fun getKeyIndex(keyName: String): Int {
        val ret = keyMap[keyName]
        return ret ?: KEY_NONE
    }

    fun setWindow(handle: Long) {
        // clear the queue and callbacks
        eventQueue.clear()
        destroy()
        windowHandle = handle
        keyCallback = GLFWKeyCallback.create { window: Long, key: Int, scancode: Int, action: Int, mods: Int ->
            if (windowHandle == window) {
                eventQueue.add(KeyEvent(key, scancode, action, mods))
            }
        }
        GLFW.glfwSetKeyCallback(windowHandle, keyCallback)
    }

    fun destroy() {
        if (windowHandle != -1L) {
            keyCallback?.free()
        }
    }

    @JvmStatic
    val isCreated: Boolean
        get() = windowHandle != -1L

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
    val eventKey: Int
        get() = if (latestEvent == null) -1 else latestEvent!!.key

    @JvmStatic
    val eventKeyState: Boolean
        get() {
            if (latestEvent == null) {
                return false
            }
            val action = latestEvent!!.action
            return action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT
        }

    @JvmStatic
    val isRepeatEvent: Boolean
        get() = latestEvent!!.action == GLFW.GLFW_REPEAT

    @JvmStatic
    fun enableRepeatEvents(enable: Boolean) {
        // TODO: Implement
    }

    @JvmStatic
    fun isKeyDown(key: Int): Boolean {
        return GLFW.glfwGetKey(windowHandle, translateKeyToGLFW(key)) == GLFW.GLFW_PRESS
    }

    @JvmStatic
    val eventCharacter: Char
        get() = (if (latestEvent == null) 0 else latestEvent!!.character) as Char

    private val numberMap = arrayOf(
        '!', '@', '#', '$', '%', '^', '&', '*', '(', ')'
    )

    fun getCharacter(key: Int, modifiers: Int): Char {
        if (key > LWJGLChars.size) {
            return '\u0000'
        }
        val character: Char = LWJGLChars[key]
        if (modifiers and GLFW_MOD_SHIFT != 0) {
            if (Character.isAlphabetic(character.code)) {
                return character.uppercaseChar()
            } else {
                if (character in '0'..'9') {
                    return numberMap[character - '0']
                }
                when (character) {
                    '`' -> return '~'
                    '-' -> return '_'
                    '=' -> return '+'
                    '\\' -> return '|'
                    '[' -> return '{'
                    ']' -> return '}'
                    ';' -> return ':'
                    '\'' -> return '"'
                    ',' -> return '<'
                    '.' -> return '>'
                    '/' -> return '?'
                }
            }
        }
        return character
    }

    fun translateKeyFromGLFW(key: Int): Int {
        return if (key != -1) {
            if (key < GLFW2LWJGL.size) GLFW2LWJGL[key] else CHAR_NONE
        } else {
            0
        }
    }

    fun translateKeyToGLFW(key: Int): Int {
        return if (key < LWJGL2GLFW.size) LWJGL2GLFW[key] else -1
    }

    // ~~~~~~~~~~~~~~~~ Key defines ~~~~~~~~~~~~~~~~
    private fun register(name: String, glfw: Int, lwjgl: Int): Int {
        GLFW2LWJGL[glfw] = lwjgl
        LWJGL2GLFW[lwjgl] = glfw
        keyNames[lwjgl] = name
        keyMap[name] = lwjgl
        if (name.length == 1) {
            LWJGLChars[lwjgl] = name.single().lowercaseChar()
        } else {
            LWJGLChars[lwjgl] = '\u0000'
        }
        return lwjgl
    }

    const val KEYBOARD_SIZE = GLFW.GLFW_KEY_LAST + 1
    private val GLFW2LWJGL = IntArray(KEYBOARD_SIZE)
    private val LWJGL2GLFW = IntArray(KEYBOARD_SIZE)
    private val LWJGLChars = CharArray(KEYBOARD_SIZE)
    private val keyNames = arrayOfNulls<String>(KEYBOARD_SIZE)
    private val keyMap: MutableMap<String, Int> = HashMap()

    // @formatter:off
    @JvmField val CHAR_NONE = '\u0000'.code
    @JvmField val KEY_NONE = 0x00
    @JvmField val KEY_SPACE = register("SPACE", GLFW.GLFW_KEY_SPACE, 57)
    @JvmField val KEY_APOSTROPHE = register("APOSTROPHE", GLFW.GLFW_KEY_APOSTROPHE, 40)
    @JvmField val KEY_COMMA = register("COMMA", GLFW.GLFW_KEY_COMMA, 51)
    @JvmField val KEY_MINUS = register("MINUS", GLFW.GLFW_KEY_MINUS, 12)
    @JvmField val KEY_PERIOD = register("PERIOD", GLFW.GLFW_KEY_PERIOD, 52)
    @JvmField val KEY_SLASH = register("SLASH", GLFW.GLFW_KEY_SLASH, 53)
    @JvmField val KEY_0 = register("0", GLFW.GLFW_KEY_0, 11)
    @JvmField val KEY_1 = register("1", GLFW.GLFW_KEY_1, 2)
    @JvmField val KEY_2 = register("2", GLFW.GLFW_KEY_2, 3)
    @JvmField val KEY_3 = register("3", GLFW.GLFW_KEY_3, 4)
    @JvmField val KEY_4 = register("4", GLFW.GLFW_KEY_4, 5)
    @JvmField val KEY_5 = register("5", GLFW.GLFW_KEY_5, 6)
    @JvmField val KEY_6 = register("6", GLFW.GLFW_KEY_6, 7)
    @JvmField val KEY_7 = register("7", GLFW.GLFW_KEY_7, 8)
    @JvmField val KEY_8 = register("8", GLFW.GLFW_KEY_8, 9)
    @JvmField val KEY_9 = register("9", GLFW.GLFW_KEY_9, 10)
    @JvmField val KEY_SEMICOLON = register("SEMICOLON", GLFW.GLFW_KEY_SEMICOLON, 39)
    @JvmField val KEY_EQUALS = register("EQUALS", GLFW.GLFW_KEY_EQUAL, 13)
    @JvmField val KEY_A = register("A", GLFW.GLFW_KEY_A, 30)
    @JvmField val KEY_B = register("B", GLFW.GLFW_KEY_B, 48)
    @JvmField val KEY_C = register("C", GLFW.GLFW_KEY_C, 46)
    @JvmField val KEY_D = register("D", GLFW.GLFW_KEY_D, 32)
    @JvmField val KEY_E = register("E", GLFW.GLFW_KEY_E, 18)
    @JvmField val KEY_F = register("F", GLFW.GLFW_KEY_F, 33)
    @JvmField val KEY_G = register("G", GLFW.GLFW_KEY_G, 34)
    @JvmField val KEY_H = register("H", GLFW.GLFW_KEY_H, 35)
    @JvmField val KEY_I = register("I", GLFW.GLFW_KEY_I, 23)
    @JvmField val KEY_J = register("J", GLFW.GLFW_KEY_J, 36)
    @JvmField val KEY_K = register("K", GLFW.GLFW_KEY_K, 37)
    @JvmField val KEY_L = register("L", GLFW.GLFW_KEY_L, 38)
    @JvmField val KEY_M = register("M", GLFW.GLFW_KEY_M, 50)
    @JvmField val KEY_N = register("N", GLFW.GLFW_KEY_N, 49)
    @JvmField val KEY_O = register("O", GLFW.GLFW_KEY_O, 24)
    @JvmField val KEY_P = register("P", GLFW.GLFW_KEY_P, 25)
    @JvmField val KEY_Q = register("Q", GLFW.GLFW_KEY_Q, 16)
    @JvmField val KEY_R = register("R", GLFW.GLFW_KEY_R, 19)
    @JvmField val KEY_S = register("S", GLFW.GLFW_KEY_S, 31)
    @JvmField val KEY_T = register("T", GLFW.GLFW_KEY_T, 20)
    @JvmField val KEY_U = register("U", GLFW.GLFW_KEY_U, 22)
    @JvmField val KEY_V = register("V", GLFW.GLFW_KEY_V, 47)
    @JvmField val KEY_W = register("W", GLFW.GLFW_KEY_W, 17)
    @JvmField val KEY_X = register("X", GLFW.GLFW_KEY_X, 45)
    @JvmField val KEY_Y = register("Y", GLFW.GLFW_KEY_Y, 21)
    @JvmField val KEY_Z = register("Z", GLFW.GLFW_KEY_Z, 44)
    @JvmField val KEY_LBRACKET = register("LBRACKET", GLFW.GLFW_KEY_LEFT_BRACKET, 26)
    @JvmField val KEY_BACKSLASH = register("BACKSLASH", GLFW.GLFW_KEY_BACKSLASH, 43)
    @JvmField val KEY_RBRACKET = register("RBRACKET", GLFW.GLFW_KEY_RIGHT_BRACKET, 27)
    @JvmField val KEY_GRAVE = register("GRAVE", GLFW.GLFW_KEY_GRAVE_ACCENT, 41)
    @JvmField val KEY_WORLD_1 = register("WORLD_1", GLFW.GLFW_KEY_WORLD_1, 161)
    @JvmField val KEY_WORLD_2 = register("WORLD_2", GLFW.GLFW_KEY_WORLD_2, 162)
    @JvmField val KEY_ESCAPE = register("ESCAPE", GLFW.GLFW_KEY_ESCAPE, 1)
    @JvmField val KEY_RETURN = register("RETURN", GLFW.GLFW_KEY_ENTER, 28)
    @JvmField val KEY_TAB = register("TAB", GLFW.GLFW_KEY_TAB, 15)
    @JvmField val KEY_BACK = register("BACK", GLFW.GLFW_KEY_BACKSPACE, 14)
    @JvmField val KEY_INSERT = register("INSERT", GLFW.GLFW_KEY_INSERT, 210)
    @JvmField val KEY_DELETE = register("DELETE", GLFW.GLFW_KEY_DELETE, 211)
    @JvmField val KEY_RIGHT = register("RIGHT", GLFW.GLFW_KEY_RIGHT, 205)
    @JvmField val KEY_LEFT = register("LEFT", GLFW.GLFW_KEY_LEFT, 203)
    @JvmField val KEY_DOWN = register("DOWN", GLFW.GLFW_KEY_DOWN, 208)
    @JvmField val KEY_UP = register("UP", GLFW.GLFW_KEY_UP, 200)
    @JvmField val KEY_PRIOR = register("PRIOR", GLFW.GLFW_KEY_PAGE_UP, 201)
    @JvmField val KEY_NEXT = register("NEXT", GLFW.GLFW_KEY_PAGE_DOWN, 209)
    @JvmField val KEY_HOME = register("HOME", GLFW.GLFW_KEY_HOME, 199)
    @JvmField val KEY_END = register("END", GLFW.GLFW_KEY_END, 207)
    @JvmField val KEY_CAPITAL = register("CAPITAL", GLFW.GLFW_KEY_CAPS_LOCK, 58)
    @JvmField val KEY_SCROLL = register("SCROLL", GLFW.GLFW_KEY_SCROLL_LOCK, 70)
    @JvmField val KEY_NUMLOCK = register("NUMLOCK", GLFW.GLFW_KEY_NUM_LOCK, 69)
    @JvmField val KEY_PRINT_SCREEN = register("PRINT_SCREEN", GLFW.GLFW_KEY_PRINT_SCREEN, 283)
    @JvmField val KEY_PAUSE = register("PAUSE", GLFW.GLFW_KEY_PAUSE, 197)
    @JvmField val KEY_F1 = register("F1", GLFW.GLFW_KEY_F1, 59)
    @JvmField val KEY_F2 = register("F2", GLFW.GLFW_KEY_F2, 60)
    @JvmField val KEY_F3 = register("F3", GLFW.GLFW_KEY_F3, 61)
    @JvmField val KEY_F4 = register("F4", GLFW.GLFW_KEY_F4, 62)
    @JvmField val KEY_F5 = register("F5", GLFW.GLFW_KEY_F5, 63)
    @JvmField val KEY_F6 = register("F6", GLFW.GLFW_KEY_F6, 64)
    @JvmField val KEY_F7 = register("F7", GLFW.GLFW_KEY_F7, 65)
    @JvmField val KEY_F8 = register("F8", GLFW.GLFW_KEY_F8, 66)
    @JvmField val KEY_F9 = register("F9", GLFW.GLFW_KEY_F9, 67)
    @JvmField val KEY_F10 = register("F10", GLFW.GLFW_KEY_F10, 68)
    @JvmField val KEY_F11 = register("F11", GLFW.GLFW_KEY_F11, 87)
    @JvmField val KEY_F12 = register("F12", GLFW.GLFW_KEY_F12, 88)
    @JvmField val KEY_F13 = register("F13", GLFW.GLFW_KEY_F13, 100)
    @JvmField val KEY_F14 = register("F14", GLFW.GLFW_KEY_F14, 101)
    @JvmField val KEY_F15 = register("F15", GLFW.GLFW_KEY_F15, 102)
    @JvmField val KEY_F16 = register("F16", GLFW.GLFW_KEY_F16, 103)
    @JvmField val KEY_F17 = register("F17", GLFW.GLFW_KEY_F17, 104)
    @JvmField val KEY_F18 = register("F18", GLFW.GLFW_KEY_F18, 105)
    @JvmField val KEY_F19 = register("F19", GLFW.GLFW_KEY_F19, 113)
    @JvmField val KEY_F20 = register("F20", GLFW.GLFW_KEY_F20, 309)
    @JvmField val KEY_F21 = register("F21", GLFW.GLFW_KEY_F21, 310)
    @JvmField val KEY_F22 = register("F22", GLFW.GLFW_KEY_F22, 311)
    @JvmField val KEY_F23 = register("F23", GLFW.GLFW_KEY_F23, 312)
    @JvmField val KEY_F24 = register("F24", GLFW.GLFW_KEY_F24, 313)
    @JvmField val KEY_F25 = register("F25", GLFW.GLFW_KEY_F25, 314)
    @JvmField val KEY_NUMPAD0 = register("NUMPAD0", GLFW.GLFW_KEY_KP_0, 82)
    @JvmField val KEY_NUMPAD1 = register("NUMPAD1", GLFW.GLFW_KEY_KP_1, 79)
    @JvmField val KEY_NUMPAD2 = register("NUMPAD2", GLFW.GLFW_KEY_KP_2, 80)
    @JvmField val KEY_NUMPAD3 = register("NUMPAD3", GLFW.GLFW_KEY_KP_3, 81)
    @JvmField val KEY_NUMPAD4 = register("NUMPAD4", GLFW.GLFW_KEY_KP_4, 75)
    @JvmField val KEY_NUMPAD5 = register("NUMPAD5", GLFW.GLFW_KEY_KP_5, 76)
    @JvmField val KEY_NUMPAD6 = register("NUMPAD6", GLFW.GLFW_KEY_KP_6, 77)
    @JvmField val KEY_NUMPAD7 = register("NUMPAD7", GLFW.GLFW_KEY_KP_7, 71)
    @JvmField val KEY_NUMPAD8 = register("NUMPAD8", GLFW.GLFW_KEY_KP_8, 72)
    @JvmField val KEY_NUMPAD9 = register("NUMPAD9", GLFW.GLFW_KEY_KP_9, 73)
    @JvmField val KEY_DECIMAL = register("DECIMAL", GLFW.GLFW_KEY_KP_DECIMAL, 83)
    @JvmField val KEY_DIVIDE = register("DIVIDE", GLFW.GLFW_KEY_KP_DIVIDE, 181)
    @JvmField val KEY_MULTIPLY = register("MULTIPLY", GLFW.GLFW_KEY_KP_MULTIPLY, 55)
    @JvmField val KEY_SUBTRACT = register("SUBTRACT", GLFW.GLFW_KEY_KP_SUBTRACT, 74)
    @JvmField val KEY_ADD = register("ADD", GLFW.GLFW_KEY_KP_ADD, 78)
    @JvmField val KEY_NUMPADENTER = register("NUMPADENTER", GLFW.GLFW_KEY_KP_ENTER, 156)
    @JvmField val KEY_NUMPADEQUALS = register("NUMPADEQUALS", GLFW.GLFW_KEY_KP_EQUAL, 141)
    @JvmField val KEY_LSHIFT = register("LSHIFT", GLFW.GLFW_KEY_LEFT_SHIFT, 42)
    @JvmField val KEY_LCONTROL = register("LCONTROL", GLFW.GLFW_KEY_LEFT_CONTROL, 29)
    @JvmField val KEY_LMENU = register("LMENU", GLFW.GLFW_KEY_LEFT_ALT, 56)
    @JvmField val KEY_LMETA = register("LMETA", GLFW.GLFW_KEY_LEFT_SUPER, 219)
    @JvmField val KEY_RSHIFT = register("RSHIFT", GLFW.GLFW_KEY_RIGHT_SHIFT, 54)
    @JvmField val KEY_RCONTROL = register("RCONTROL", GLFW.GLFW_KEY_RIGHT_CONTROL, 157)
    @JvmField val KEY_RMENU = register("RMENU", GLFW.GLFW_KEY_RIGHT_ALT, 184)
    @JvmField val KEY_RMETA = register("RMETA", GLFW.GLFW_KEY_RIGHT_SUPER, 220)
    @JvmField val KEY_MENU = register("MENU", GLFW.GLFW_KEY_MENU, 348)
    // @formatter:on
    init {
        LWJGLChars[KEY_SPACE] = ' '
        LWJGLChars[KEY_APOSTROPHE] = '\''
        LWJGLChars[KEY_COMMA] = ','
        LWJGLChars[KEY_MINUS] = '-'
        LWJGLChars[KEY_PERIOD] = '.'
        LWJGLChars[KEY_SLASH] = '/'
        LWJGLChars[KEY_SEMICOLON] = ';'
        LWJGLChars[KEY_EQUALS] = '='
        LWJGLChars[KEY_LBRACKET] = '['
        LWJGLChars[KEY_BACKSLASH] = '\\'
        LWJGLChars[KEY_RBRACKET] = ']'
        LWJGLChars[KEY_GRAVE] = '`'
    }

    class KeyEvent(key: Int, scancode: Int, action: Int, mods: Int) {
        val key: Int
        val scancode: Int
        val action: Int
        val mods: Int
        val character: Char

        init {
            this.key = translateKeyFromGLFW(key)
            this.scancode = scancode
            this.action = action
            this.mods = mods
            character = getCharacter(this.key, mods)
        }
    }
}