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
package org.lwjgl.input;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.glfw.GLFW;
import com.github.zarzelcow.legacylwjgl3.implementation.LWJGLImplementationUtils;
import org.lwjgl.opengl.Display;
import com.github.zarzelcow.legacylwjgl3.implementation.input.InputImplementation;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * A raw Keyboard interface. This can be used to poll the current state of the
 * keys, or read all the keyboard presses / releases since the last read.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @author Brian Matzon <brian@matzon.dk>
 */
public class Keyboard {
    /** Internal use - event size in bytes */
    public static final int EVENT_SIZE = 4 + 1 + 4 + 8 + 1;

    /** Buffer size in events */
    private static final int BUFFER_SIZE = 50;

    public static final int KEYBOARD_SIZE = GLFW.GLFW_KEY_LAST + 1;

    /** Has the keyboard been created? */
    private static boolean created;

    /** Are repeat events enabled? */
    private static boolean repeat_enabled;

    /** The keys status from the last poll */
    private static final ByteBuffer keyDownBuffer = BufferUtils.createByteBuffer(KEYBOARD_SIZE);

    /**
     * The key events from the last read: a sequence of pairs of key number,
     * followed by state. The state is followed by
     * a 4 byte code point representing the translated character.
     */
    private static ByteBuffer readBuffer;

    /** current event */
    private static KeyEvent current_event = new KeyEvent();

    /** scratch event */
    private static KeyEvent tmp_event = new KeyEvent();

    /** One time initialization */
    private static boolean initialized;

    private static InputImplementation implementation;

    /**
     * Keyboard cannot be constructed.
     */
    private Keyboard() {
    }

    /**
     * Static initialization
     */
    private static void initialize() {
        if (initialized)
            return;
        Sys.initialize();
        initialized = true;
    }

    /**
     * "Create" the keyboard with the given implementation. This is used
     * reflectively from AWTInputAdapter.
     *
     * @throws LWJGLException if the keyboard could not be created for any reason
     */
    private static void create(InputImplementation impl) throws LWJGLException {
        if (created)
            return;
        if (!initialized)
            initialize();
        implementation = impl;
        implementation.createKeyboard();
        created = true;
        readBuffer = ByteBuffer.allocate(EVENT_SIZE*BUFFER_SIZE);
        reset();
    }

    /**
     * "Create" the keyboard. The display must first have been created. The
     * reason for this is so the keyboard has a window to "focus" in.
     *
     * @throws LWJGLException if the keyboard could not be created for any reason
     */
    public static void create() throws LWJGLException {
//        synchronized (OpenGLPackageAccess.global_lock) {
            if (!Display.isCreated()) throw new IllegalStateException("Display must be created.");

            create(LWJGLImplementationUtils.getOrCreateInputImplementation());
//        }
    }

    private static void reset() {
        readBuffer.limit(0);
        for (int i = 0; i < keyDownBuffer.remaining(); i++)
            keyDownBuffer.put(i, (byte)0);
        current_event.reset();
    }

    /**
     * @return true if the keyboard has been created
     */
    public static boolean isCreated() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return created;
//        }
    }

    /**
     * "Destroy" the keyboard
     */
    public static void destroy() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            if (!created)
                return;
            created = false;
            implementation.destroyKeyboard();
            reset();
//        }
    }

    /**
     * Polls the keyboard for its current state. Access the polled values using the
     * <code>isKeyDown</code> method.
     * By using this method, it is possible to "miss" keyboard keys if you don't
     * poll fast enough.
     *
     * To use buffered values, you have to call <code>next</code> for each event you
     * want to read. You can query which key caused the event by using
     * <code>getEventKey</code>. To get the state of that key, for that event, use
     * <code>getEventKeyState</code> - finally use <code>getEventCharacter</code> to get the
     * character for that event.
     *
     * NOTE: This method does not query the operating system for new events. To do that,
     * Display.processMessages() (or Display.update()) must be called first.
     *
     * @see org.lwjgl.input.Keyboard#isKeyDown(int key)
     * @see org.lwjgl.input.Keyboard#next()
     * @see org.lwjgl.input.Keyboard#getEventKey()
     * @see org.lwjgl.input.Keyboard#getEventKeyState()
     * @see org.lwjgl.input.Keyboard#getEventCharacter()
     */
    public static void poll() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            if (!created)
                throw new IllegalStateException("Keyboard must be created before you can poll the device");
            implementation.pollKeyboard(keyDownBuffer);
            read();
//        }
    }

    private static void read() {
        readBuffer.compact();
        implementation.readKeyboard(readBuffer);
        readBuffer.flip();
    }

    /**
     * Checks to see if a key is down.
     * @param key Keycode to check
     * @return true if the key is down according to the last poll()
     */
    public static boolean isKeyDown(int key) {
//        synchronized (OpenGLPackageAccess.global_lock) {
            if (!created)
                throw new IllegalStateException("Keyboard must be created before you can query key state");
            return keyDownBuffer.get(key) != 0;
//        }
    }

    /**
     * Checks whether one of the state keys are "active"
     *
     * @param key State key to test (KEY_CAPITAL | KEY_NUMLOCK | KEY_SYSRQ)
     * @return STATE_ON if on, STATE_OFF if off and STATE_UNKNOWN if the state is unknown
     */
/*	public static int isStateKeySet(int key) {
		if (!created)
			throw new IllegalStateException("Keyboard must be created before you can query key state");
		return implementation.isStateKeySet(key);
	}
*/
    /**
     * Gets a key's name
     * @param key The key
     * @return a String with the key's human readable name in it or null if the key is unnamed
     */
    public static synchronized String getKeyName(int key) {
        return keyNames[key];
    }

    /**
     * Get's a key's index. If the key is unrecognised then KEY_NONE is returned.
     * @param keyName The key name
     */
    public static synchronized int getKeyIndex(String keyName) {
        return keyMap.getOrDefault(keyName, KEY_NONE);
    }

    /**
     * Gets the number of keyboard events waiting after doing a buffer enabled poll().
     * @return the number of keyboard events
     */
    public static int getNumKeyboardEvents() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            if (!created)
                throw new IllegalStateException("Keyboard must be created before you can read events");
            int old_position = readBuffer.position();
            int num_events = 0;
            while (readNext(tmp_event) && (!tmp_event.repeat || repeat_enabled))
                num_events++;
            readBuffer.position(old_position);
            return num_events;
//        }
    }

    /**
     * Gets the next keyboard event. You can query which key caused the event by using
     * <code>getEventKey</code>. To get the state of that key, for that event, use
     * <code>getEventKeyState</code> - finally use <code>getEventCharacter</code> to get the
     * character for that event.
     *
     * @see org.lwjgl.input.Keyboard#getEventKey()
     * @see org.lwjgl.input.Keyboard#getEventKeyState()
     * @see org.lwjgl.input.Keyboard#getEventCharacter()
     * @return true if a keyboard event was read, false otherwise
     */
    public static boolean next() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            if (!created)
                throw new IllegalStateException("Keyboard must be created before you can read events");

            boolean result;
            while ((result = readNext(current_event)) && current_event.repeat && !repeat_enabled)
                ;
            return result;
//        }
    }

    /**
     * Controls whether repeat events are reported or not. If repeat events
     * are enabled, key down events are reported when a key is pressed and held for
     * a OS dependent amount of time. To distinguish a repeat event from a normal event,
     * use isRepeatEvent().
     *
     * @see org.lwjgl.input.Keyboard#getEventKey()
     */
    public static void enableRepeatEvents(boolean enable) {
//        synchronized (OpenGLPackageAccess.global_lock) {
            repeat_enabled = enable;
//        }
    }

    /**
     * Check whether repeat events are currently reported or not.
     *
     * @return true is repeat events are reported, false if not.
     * @see org.lwjgl.input.Keyboard#getEventKey()
     */
    public static boolean areRepeatEventsEnabled() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return repeat_enabled;
//        }
    }

    private static boolean readNext(KeyEvent event) {
        if (readBuffer.hasRemaining()) {
            event.key = readBuffer.getInt()/* & 0xFF */;
            event.state = readBuffer.get() != 0;
            event.character = readBuffer.getInt();
            event.nanos = readBuffer.getLong();
            event.repeat = readBuffer.get() == 1;
            return true;
        } else
            return false;
    }

    /**
     * @return Number of keys on this keyboard
     */
    public static int getKeyCount() {
        return keyMap.size();
    }

    /**
     * @return The character from the current event
     */
    public static char getEventCharacter() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return (char)current_event.character;
//        }
    }

    /**
     * Please note that the key code returned is NOT valid against the
     * current keyboard layout. To get the actual character pressed call
     * getEventCharacter
     *
     * @return The key from the current event
     */
    public static int getEventKey() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return current_event.key;
//        }
    }

    /**
     * Gets the state of the key that generated the
     * current event
     *
     * @return True if key was down, or false if released
     */
    public static boolean getEventKeyState() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return current_event.state;
//        }
    }

    /**
     * Gets the time in nanoseconds of the current event.
     * Only useful for relative comparisons with other
     * Keyboard events, as the absolute time has no defined
     * origin.
     * @return The time in nanoseconds of the current event
     */
    public static long getEventNanoseconds() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return current_event.nanos;
//        }
    }

    /**
     * @see org.lwjgl.input.Keyboard#enableRepeatEvents(boolean)
     * @return true if the current event is a repeat event, false if
     * the current event is not a repeat even or if repeat events are disabled.
     */
    public static boolean isRepeatEvent() {
//        synchronized (OpenGLPackageAccess.global_lock) {
            return current_event.repeat;
//        }
    }

    private static final class KeyEvent {
        /** The current keyboard character being examined */
        private int character;

        /** The current keyboard event key being examined */
        private int key;

        /** The current state of the key being examined in the event queue */
        private boolean state;

        /** The current event time */
        private long nanos;

        /** Is the current event a repeated event? */
        private boolean repeat;

        private void reset() {
            character = 0;
            key = 0;
            state = false;
            repeat = false;
        }
    }



    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // ~~~~~~~~~~~~~~~~ Key defines ~~~~~~~~~~~~~~~~
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private static int register(String name, int lwjglCode) {
        keyNames[lwjglCode] = name;
        keyMap.put(name, lwjglCode);
        return lwjglCode;
    }

    private static final String[] keyNames = new String[KEYBOARD_SIZE];
    private static final Map<String, Integer> keyMap = new HashMap<>(253);

    public static final int CHAR_NONE = '\0';
    public static final int KEY_NONE = register("NONE", 0x00);
    public static final int KEY_SPACE = register("SPACE", 57);
    public static final int KEY_APOSTROPHE = register("APOSTROPHE", 40);
    public static final int KEY_COMMA = register("COMMA", 51);
    public static final int KEY_MINUS = register("MINUS", 12);
    public static final int KEY_PERIOD = register("PERIOD", 52);
    public static final int KEY_SLASH = register("SLASH", 53);
    public static final int KEY_0 = register("0", 11);
    public static final int KEY_1 = register("1", 2);
    public static final int KEY_2 = register("2", 3);
    public static final int KEY_3 = register("3", 4);
    public static final int KEY_4 = register("4", 5);
    public static final int KEY_5 = register("5", 6);
    public static final int KEY_6 = register("6", 7);
    public static final int KEY_7 = register("7", 8);
    public static final int KEY_8 = register("8", 9);
    public static final int KEY_9 = register("9", 10);
    public static final int KEY_SEMICOLON = register("SEMICOLON", 39);
    public static final int KEY_EQUALS = register("EQUALS", 13);
    public static final int KEY_A = register("A", 30);
    public static final int KEY_B = register("B", 48);
    public static final int KEY_C = register("C", 46);
    public static final int KEY_D = register("D", 32);
    public static final int KEY_E = register("E", 18);
    public static final int KEY_F = register("F", 33);
    public static final int KEY_G = register("G", 34);
    public static final int KEY_H = register("H", 35);
    public static final int KEY_I = register("I", 23);
    public static final int KEY_J = register("J", 36);
    public static final int KEY_K = register("K", 37);
    public static final int KEY_L = register("L", 38);
    public static final int KEY_M = register("M", 50);
    public static final int KEY_N = register("N", 49);
    public static final int KEY_O = register("O", 24);
    public static final int KEY_P = register("P", 25);
    public static final int KEY_Q = register("Q", 16);
    public static final int KEY_R = register("R", 19);
    public static final int KEY_S = register("S", 31);
    public static final int KEY_T = register("T", 20);
    public static final int KEY_U = register("U", 22);
    public static final int KEY_V = register("V", 47);
    public static final int KEY_W = register("W", 17);
    public static final int KEY_X = register("X", 45);
    public static final int KEY_Y = register("Y", 21);
    public static final int KEY_Z = register("Z", 44);
    public static final int KEY_LBRACKET = register("LBRACKET", 26);
    public static final int KEY_BACKSLASH = register("BACKSLASH", 43);
    public static final int KEY_RBRACKET = register("RBRACKET", 27);
    public static final int KEY_GRAVE = register("GRAVE", 41);
    public static final int KEY_WORLD_1 = register("WORLD_1", 161);
    public static final int KEY_WORLD_2 = register("WORLD_2", 162);
    public static final int KEY_ESCAPE = register("ESCAPE", 1);
    public static final int KEY_RETURN = register("RETURN", 28);
    public static final int KEY_TAB = register("TAB", 15);
    public static final int KEY_BACK = register("BACK", 14);
    public static final int KEY_INSERT = register("INSERT", 210);
    public static final int KEY_DELETE = register("DELETE", 211);
    public static final int KEY_RIGHT = register("RIGHT", 205);
    public static final int KEY_LEFT = register("LEFT", 203);
    public static final int KEY_DOWN = register("DOWN", 208);
    public static final int KEY_UP = register("UP", 200);
    public static final int KEY_PRIOR = register("PRIOR", 201);
    public static final int KEY_NEXT = register("NEXT", 209);
    public static final int KEY_HOME = register("HOME", 199);
    public static final int KEY_END = register("END", 207);
    public static final int KEY_CAPITAL = register("CAPITAL", 58);
    public static final int KEY_SCROLL = register("SCROLL", 70);
    public static final int KEY_NUMLOCK = register("NUMLOCK", 69);
    public static final int KEY_PRINT_SCREEN = register("PRINT_SCREEN", 28);
    public static final int KEY_PAUSE = register("PAUSE", 197);
    public static final int KEY_F1 = register("F1", 59);
    public static final int KEY_F2 = register("F2", 60);
    public static final int KEY_F3 = register("F3", 61);
    public static final int KEY_F4 = register("F4", 62);
    public static final int KEY_F5 = register("F5", 63);
    public static final int KEY_F6 = register("F6", 64);
    public static final int KEY_F7 = register("F7", 65);
    public static final int KEY_F8 = register("F8", 66);
    public static final int KEY_F9 = register("F9", 67);
    public static final int KEY_F10 = register("F10", 68);
    public static final int KEY_F11 = register("F11", 87);
    public static final int KEY_F12 = register("F12", 88);
    public static final int KEY_F13 = register("F13", 100);
    public static final int KEY_F14 = register("F14", 101);
    public static final int KEY_F15 = register("F15", 102);
    public static final int KEY_F16 = register("F16", 103);
    public static final int KEY_F17 = register("F17", 104);
    public static final int KEY_F18 = register("F18", 105);
    public static final int KEY_F19 = register("F19", 113);
    public static final int KEY_F20 = register("F20", 309);
    public static final int KEY_F21 = register("F21", 310);
    public static final int KEY_F22 = register("F22", 311);
    public static final int KEY_F23 = register("F23", 312);
    public static final int KEY_F24 = register("F24", 313);
    public static final int KEY_F25 = register("F25", 314);
    public static final int KEY_NUMPAD0 = register("NUMPAD0", 82);
    public static final int KEY_NUMPAD1 = register("NUMPAD1", 79);
    public static final int KEY_NUMPAD2 = register("NUMPAD2", 80);
    public static final int KEY_NUMPAD3 = register("NUMPAD3", 81);
    public static final int KEY_NUMPAD4 = register("NUMPAD4", 75);
    public static final int KEY_NUMPAD5 = register("NUMPAD5", 76);
    public static final int KEY_NUMPAD6 = register("NUMPAD6", 77);
    public static final int KEY_NUMPAD7 = register("NUMPAD7", 71);
    public static final int KEY_NUMPAD8 = register("NUMPAD8", 72);
    public static final int KEY_NUMPAD9 = register("NUMPAD9", 73);
    public static final int KEY_DECIMAL = register("DECIMAL", 83);
    public static final int KEY_DIVIDE = register("DIVIDE", 181);
    public static final int KEY_MULTIPLY = register("MULTIPLY", 55);
    public static final int KEY_SUBTRACT = register("SUBTRACT", 74);
    public static final int KEY_ADD = register("ADD", 78);
    public static final int KEY_NUMPADENTER = register("NUMPADENTER", 156);
    public static final int KEY_NUMPADEQUALS = register("NUMPADEQUALS", 141);
    public static final int KEY_LSHIFT = register("LSHIFT", 42);
    public static final int KEY_LCONTROL = register("LCONTROL", 29);
    public static final int KEY_LMENU = register("LMENU", 56);
    public static final int KEY_LMETA = register("LMETA", 219);
    public static final int KEY_RSHIFT = register("RSHIFT", 54);
    public static final int KEY_RCONTROL = register("RCONTROL", 157);
    public static final int KEY_RMENU = register("RMENU", 184);
    public static final int KEY_RMETA = register("RMETA", 220);
    public static final int KEY_MENU = register("MENU", 348);

}
