package com.github.zarzelcow.legacylwjgl3;

import kotlin.jvm.internal.Intrinsics;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.openal.AL.createCapabilities;

public class ALExtensions {
    private static long _contextPtr;
    private static long _devicePtr;
    private static boolean _created;

    public static boolean isCreated() {
        return _created;
    }

    public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized) throws LWJGLException {
        create(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, true);
    }

    public static void create(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice) throws LWJGLException {
        if (_created) {
            throw new IllegalStateException("Only one OpenAL context may be instantiated at any one time.");
        } else {
            init(deviceArguments, contextFrequency, contextRefresh, contextSynchronized, openDevice);
            _created = true;
        }
    }

    private static void init(String deviceArguments, int contextFrequency, int contextRefresh, boolean contextSynchronized, boolean openDevice) throws LWJGLException {
        try {
            if (openDevice) {
                _devicePtr = ALC10.alcOpenDevice(deviceArguments);
                if (_devicePtr == -1L) {
                    throw new LWJGLException("Could not open ALC device");
                }

                ALCCapabilities deviceCaps = ALC.createCapabilities(_devicePtr);
                if (contextFrequency == -1) {
                    _contextPtr = ALC10.alcCreateContext(_devicePtr, (IntBuffer)null);
                } else {
                    MemoryStack stack = MemoryStack.stackPush();
                    long var8 = _devicePtr;
                    int var10003 = contextSynchronized ? 1 : 0;
                    Intrinsics.checkNotNullExpressionValue(stack, "stack");
                    _contextPtr = ALC10.alcCreateContext(var8, createAttributeList(contextFrequency, contextRefresh, var10003, stack));
                    stack.close();
                }

                ALC10.alcMakeContextCurrent(_contextPtr);
                createCapabilities(deviceCaps);
            }

        } catch (LWJGLException var7) {
            destroy();
            throw var7;
        }
    }

    public static void create() throws LWJGLException {
        create(null, 44100, 60, false);
    }

    private static final IntBuffer createAttributeList(int contextFrequency, int contextRefresh, int contextSynchronized, MemoryStack stack) {
        IntBuffer buffer = stack.callocInt(7);
        buffer.put(0, 4103);
        buffer.put(1, contextFrequency);
        buffer.put(2, 4104);
        buffer.put(3, contextRefresh);
        buffer.put(4, 4105);
        buffer.put(5, contextSynchronized);
        buffer.put(6, 0);
        return buffer;
    }
    public static void al_destroy() {
        // stub method that gets replace with a proper call to the original al destroy
    }

    public static void destroy() {
        if (_contextPtr != -1L) {
            ALC10.alcMakeContextCurrent(0L);
            ALC10.alcDestroyContext(_contextPtr);
            _contextPtr = -1L;
        }

        if (_devicePtr != -1L) {
            ALC10.alcCloseDevice(_devicePtr);
            _devicePtr = -1L;
        }

        _created = false;
        al_destroy();
    }
}
