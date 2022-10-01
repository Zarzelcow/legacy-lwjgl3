package com.github.zarzelcow.legacylwjgl3.implementation.input;

import java.nio.ByteBuffer;

/**
 * @author Zarzelcow
 * @created 28/09/2022 - 3:24 PM
 */
public interface KeyboardImplementation {
    void createKeyboard();

    void destroyKeyboard();

    void pollKeyboard(ByteBuffer keyDownBuffer);

    void readKeyboard(ByteBuffer readBuffer);
}
