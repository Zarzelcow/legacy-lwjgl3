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
package org.lwjgl;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by gudenau on 5/30/2017.
 * <p>
 * LWJGL3
 */
public class Sys {
    private static final long timerOffset;
    public static final String VERSION = Version.getVersion();

    private Sys() {
    }


    static {
        timerOffset = System.nanoTime();
    }

    /**
     * Obtains the number of ticks that the hires timer does in a second.
     *
     * @return timer resolution in ticks per second.
     */
    public static long getTimerResolution() {
        return 1000000000;
    }

    /**
     * Gets the current value of the hires timer, in ticks. When the Sys class is first loaded
     * the hires timer is reset to 0. If no hires timer is present then this method will always
     * return 0.<p><strong>NOTEZ BIEN</strong> that the hires timer WILL wrap around.
     *
     * @return the current hires time, in ticks (always >= 0)
     */
    public static long getTime() {
        return (System.nanoTime() - timerOffset) & 0x7FFFFFFFFFFFFFFFL;
    }

    /**
     * Return the version of the core LWJGL libraries as a String.
     */
    public static String getVersion() {
        return VERSION;
    }


    public static boolean openURL(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
            return true;
        } catch (IOException | URISyntaxException | UnsupportedOperationException e) {
            return false;
        }
    }
}
