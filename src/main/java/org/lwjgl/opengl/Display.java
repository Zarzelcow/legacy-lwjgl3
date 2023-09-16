package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.LWJGLException;
import org.lwjgl.glfw.*;
import org.lwjgl.glfw.GLFWImage.Buffer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.system.MemoryUtil;

public final class Display {
	@NotNull
	private static String title = "";
	private static long handle = -1L;
	private static boolean resizable;
	@NotNull
	private static DisplayMode displayMode = new DisplayMode(640, 480, 24, 60);
	private static int width;
	private static int height;
	private static int xPos;
	private static int yPos;
	private static boolean window_resized;
	@Nullable
	private static GLFWWindowSizeCallback sizeCallback;
	@Nullable
	private static ByteBuffer[] cached_icons = null;

	private Display() {
	}

	@NotNull
	public static String getTitle() {
		return title;
	}

	public static void setTitle(@NotNull String title) {
		Display.title = title;
		if (isCreated()) {
			GLFW.glfwSetWindowTitle(handle, title);
		}
	}

	public static long getHandle() {
		return handle;
	}

	public static void setHandle(long handle) {
		Display.handle = handle;
	}

	@NotNull
	public static DisplayMode getDisplayMode() {
		return displayMode;
	}

	public static void setDisplayMode(@NotNull DisplayMode mode) {
		displayMode = mode;
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Display.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Display.height = height;
	}

	public static int getXPos() {
		return xPos;
	}

	public static void setXPos(int XPos) {
		xPos = XPos;
	}

	public static int getYPos() {
		return yPos;
	}

	public static void setYPos(int YPos) {
		yPos = YPos;
	}

	@Nullable
	public static DisplayMode getDesktopDisplayMode() {
		DisplayMode[] availableDisplayModes = getAvailableDisplayModes();
		return Arrays.stream(availableDisplayModes).max(Comparator.comparingInt(d -> d.getWidth() * d.getHeight())).orElse(null);
	}

	
	public static int setIcon(@NotNull ByteBuffer[] icons) {
		// LWJGL2 doesn't enforce this to be called after window creation,
		// meaning you have to keep hold the icons to use them when the window is created
		if (!Arrays.equals(cached_icons, icons)) {
			// you have to also clone the byte buffers to avoid seg faults from them being freed
			cached_icons = Arrays.stream(icons).map(buf -> {
				ByteBuffer copy = ByteBuffer.allocate(buf.capacity());
				int old_pos = buf.position();
				copy.put(buf);
				buf.position(old_pos);
				copy.flip();
				return copy;
			}).toArray(ByteBuffer[]::new);
		}

		if (isCreated()) {
			Buffer buffer = GLFWImage.create(icons.length);

			Arrays.stream(icons).forEach(buf -> {
				GLFWImage image = GLFWImage.malloc();
				int size = buf.limit() / 4;
				int dimension = (int) Math.sqrt(size);
				buffer.put(image.set(dimension, dimension, buf));
			});

			GLFW.glfwSetWindowIcon(handle, buffer);
			return 1;
		} else {
			return 0;
		}
	}

	public static void update() {
		window_resized = false;
		GLFW.glfwPollEvents();
		if (Mouse.isCreated()) {
			Mouse.poll();
		}

		if (Keyboard.isCreated()) {
			Keyboard.poll();
		}

		GLFW.glfwSwapBuffers(handle);
	}

	public static void create(@Nullable PixelFormat pixelFormat) throws LWJGLException {
		// Setup an error callback. The default implementation
		GLFWErrorCallback.createPrint(System.err).set();
		if (!GLFW.glfwInit()) {
			throw new IllegalStateException("Unable to initialize GLFW");
		} else {
			// Configure GLFW
			GLFW.glfwDefaultWindowHints();
			GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, 0);
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? 1 : 0);
			handle =
					GLFW.glfwCreateWindow(displayMode.getWidth(), displayMode.getHeight(), title, MemoryUtil.NULL, MemoryUtil.NULL);
			width = displayMode.getWidth();
			height = displayMode.getHeight();
			GLFW.glfwMakeContextCurrent(handle);
			GL.createCapabilities();
			// create general callbacks
			sizeCallback = GLFWWindowSizeCallback.create(Display::resizeCallback);
			GLFW.glfwSetWindowSizeCallback(handle, sizeCallback);
			Mouse.create();
			Keyboard.create();
			GLFW.glfwShowWindow(handle);
			if (cached_icons != null) {
				setIcon(cached_icons);
			}
		}
	}

	public static void setFullscreen(boolean fullscreen) {

		try {
			resizeCallback(handle, displayMode.getWidth(), displayMode.getHeight());

			if (fullscreen) {
				long monitor = GLFW.glfwGetPrimaryMonitor();
				GLFW.glfwSetWindowMonitor(getHandle(),
						monitor,
						0,
						0,
						getWidth(),
						getHeight(),
						getDisplayMode().getFrequency());
				setXPos(getDisplayMode().getWidth() / 2);
				setYPos(getDisplayMode().getHeight() / 2);
			} else {
				setXPos(getXPos() - getWidth() / 2);
				setYPos(getYPos() - getHeight() / 2);
				GLFW.glfwSetWindowMonitor(getHandle(),
						0L,
						getXPos(), // need a xPos
						getYPos(), // need a yPos
						getWidth(),
						getHeight(),
						-1);
			}

			GLFW.glfwSetWindowSize(getHandle(), getWidth(), getHeight());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	@NotNull
	public static DisplayMode[] getAvailableDisplayModes() {
		long primaryMonitor = GLFW.glfwGetPrimaryMonitor();
		if (primaryMonitor == MemoryUtil.NULL) {
			return new DisplayMode[0];
		} else {
			GLFWVidMode.Buffer videoModes = GLFW.glfwGetVideoModes(primaryMonitor);
			if (videoModes == null) {
				throw new IllegalStateException("No video modes found");
			} else {
				return videoModes.stream().map(mode -> new DisplayMode(mode.width(),
						mode.height(), mode.redBits() + mode.blueBits() + mode.greenBits(),
						mode.refreshRate())).toArray(DisplayMode[]::new);
			}
		}
	}

	public static void destroy() {
		// free callbacks
		assert sizeCallback != null;
		sizeCallback.free();
		Mouse.destroy();
		Keyboard.destroy();
		// Destroy the window
		GLFW.glfwDestroyWindow(handle);
		
		GLFW.glfwTerminate();
		GLFWErrorCallback callback = GLFW.glfwSetErrorCallback(null);
		if (callback != null) {
			callback.free();
		}
	}

	public static boolean isCreated() {
		return handle != -1L;
	}

	public static boolean isCloseRequested() {
		return GLFW.glfwWindowShouldClose(handle);
	}

	public static boolean isActive() {
		return true;
	}

	public static void setResizable(boolean isResizable) {
		resizable = isResizable;
		if (isCreated()) {
			GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, resizable ? 1 : 0);
		}
	}

	public static void sync(int fps) {
		Sync.sync(fps);
	}

	
	public static void setVSyncEnabled(boolean enabled) {
		GLFW.glfwSwapInterval(enabled ? 1 : 0);
	}

	
	public static boolean wasResized() {
		return window_resized;
	}

	public static boolean isVisible() {
		return GLFW.glfwGetWindowAttrib(handle, GLFW.GLFW_VISIBLE) != 0;
	}

	private static void resizeCallback(long window, int width, int height) {
		if (window == handle) {
			window_resized = true;
			Display.width = width;
			Display.height = height;
		}
	}
}
