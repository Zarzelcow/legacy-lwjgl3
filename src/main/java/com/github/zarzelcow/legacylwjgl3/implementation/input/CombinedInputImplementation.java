package com.github.zarzelcow.legacylwjgl3.implementation.input;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * @author Zarzelcow
 * @created 28/09/2022 - 3:23 PM
 */
public class CombinedInputImplementation implements InputImplementation {
    private KeyboardImplementation keyboardImpl;
    private MouseImplementation mouseImpl;

    public CombinedInputImplementation(KeyboardImplementation keyboard, MouseImplementation mouse) {
        this.keyboardImpl = keyboard;
        this.mouseImpl = mouse;
    }

    // ~~~~~ KEYBOARD ~~~~~
    @Override
    public void createKeyboard() {
        keyboardImpl.createKeyboard();
    }

    @Override
    public void destroyKeyboard() {
        keyboardImpl.destroyKeyboard();
    }

    @Override
    public void pollKeyboard(ByteBuffer keyDownBuffer) {
        keyboardImpl.pollKeyboard(keyDownBuffer);
    }

    @Override
    public void readKeyboard(ByteBuffer readBuffer) {
        keyboardImpl.readKeyboard(readBuffer);
    }

    // ~~~~~ MOUSE ~~~~~

    @Override
    public void createMouse() {
        mouseImpl.createMouse();
    }

    @Override
    public void destroyMouse() {
        mouseImpl.destroyMouse();
    }

    @Override
    public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
        mouseImpl.pollMouse(coord_buffer, buttons_buffer);
    }

    @Override
    public void readMouse(ByteBuffer readBuffer) {
        mouseImpl.readMouse(readBuffer);
    }

    @Override
    public void setCursorPosition(int x, int y) {
        mouseImpl.setCursorPosition(x, y);
    }

    @Override
    public void grabMouse(boolean grab) {
        mouseImpl.grabMouse(grab);
    }

    @Override
    public boolean hasWheel() {
        return mouseImpl.hasWheel();
    }

    @Override
    public int getButtonCount() {
        return mouseImpl.getButtonCount();
    }

    @Override
    public boolean isInsideWindow() {
        return mouseImpl.isInsideWindow();
    }
}
