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
package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by gudenau on 5/31/2017.
 * <p>
 * LWJGL3
 */
public class GLContext {
    private static final ThreadLocal<ContextCapabilities> current_capabilities = new ThreadLocal<>();
    private static CapabilitiesCacheEntry fast_path_cache = new CapabilitiesCacheEntry();
    private static final ThreadLocal<CapabilitiesCacheEntry> thread_cache_entries = new ThreadLocal<>();
    private static final Map<Object, ContextCapabilities> capability_cache = new WeakHashMap<>();

    public static ContextCapabilities getCapabilities() {
        ContextCapabilities caps = getCapabilitiesImpl();
        if (caps == null) {
            //throw new RuntimeException("No OpenGL context found in the current thread.");
            try {
                ContextCapabilities created = new ContextCapabilities(false);
                setCapabilities(created);
                return created;
            } catch (LWJGLException e) {
                //e.printStackTrace();
                throw new RuntimeException("No OpenGL context found in the current thread and could not create!", e);
            }
        }

        return caps;
    }

    // Actually check if any of these lwjgl2 optimizations are working since im
    // pretty sure ThreadLocal by itself is faster since thread local should be right on the cpu register.
    private static ContextCapabilities getCapabilitiesImpl() {
        CapabilitiesCacheEntry recent_cache_entry = fast_path_cache;
        // Check owner of cache entry
        if (recent_cache_entry.owner == Thread.currentThread()) {
            /* The owner ship test succeeded, so the cache must contain the current ContextCapabilities instance
             * assert recent_cache_entry.capabilities == getThreadLocalCapabilities();
             */
            return recent_cache_entry.capabilities;
        } else // Some other thread has written to the cache since, and we fall back to the slower path
            return getThreadLocalCapabilities();
    }

    static ContextCapabilities getCapabilities(Object context) {
        return capability_cache.get(context);
    }

    private static ContextCapabilities getThreadLocalCapabilities() {
        return current_capabilities.get();
    }

    static void setCapabilities(ContextCapabilities capabilities) {
        current_capabilities.set(capabilities);

        CapabilitiesCacheEntry thread_cache_entry = thread_cache_entries.get();
        if (thread_cache_entry == null) {
            thread_cache_entry = new CapabilitiesCacheEntry();
            thread_cache_entries.set(thread_cache_entry);
        }
        thread_cache_entry.owner = Thread.currentThread();
        thread_cache_entry.capabilities = capabilities;

        fast_path_cache = thread_cache_entry;
    }

    private static final class CapabilitiesCacheEntry {
        Thread owner;
        ContextCapabilities capabilities;
    }
}
