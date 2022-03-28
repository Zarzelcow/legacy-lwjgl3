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
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.Util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.GL_NUM_EXTENSIONS;
import static org.lwjgl.opengl.GL30.glGetStringi;
import static org.lwjgl.opengl.GL32.GL_CONTEXT_COMPATIBILITY_PROFILE_BIT;
import static org.lwjgl.opengl.GL32.GL_CONTEXT_PROFILE_MASK;

/**
 * Created by gudenau on 5/30/2017.
 * <p>
 * LWJGL3
 */
public class ContextCapabilities{
    static final boolean DEBUG = false;
    //TODO?
    //final StateTracker tracker = new StateTracker();

    public boolean GL_AMD_blend_minmax_factor = false;
    public boolean GL_AMD_conservative_depth = false;
    public boolean GL_AMD_debug_output = false;
    public boolean GL_AMD_depth_clamp_separate = false;
    public boolean GL_AMD_draw_buffers_blend = false;
    public boolean GL_AMD_interleaved_elements = false;
    public boolean GL_AMD_multi_draw_indirect = false;
    public boolean GL_AMD_name_gen_delete = false;
    public boolean GL_AMD_performance_monitor = false;
    public boolean GL_AMD_pinned_memory = false;
    public boolean GL_AMD_query_buffer_object = false;
    public boolean GL_AMD_sample_positions = false;
    public boolean GL_AMD_seamless_cubemap_per_texture = false;
    public boolean GL_AMD_shader_atomic_counter_ops = false;
    public boolean GL_AMD_shader_stencil_export = false;
    public boolean GL_AMD_shader_trinary_minmax = false;
    public boolean GL_AMD_sparse_texture = false;
    public boolean GL_AMD_stencil_operation_extended = false;
    public boolean GL_AMD_texture_texture4 = false;
    public boolean GL_AMD_transform_feedback3_lines_triangles = false;
    public boolean GL_AMD_vertex_shader_layer = false;
    public boolean GL_AMD_vertex_shader_tessellator = false;
    public boolean GL_AMD_vertex_shader_viewport_index = false;
    public boolean GL_APPLE_aux_depth_stencil = false;
    public boolean GL_APPLE_client_storage = false;
    public boolean GL_APPLE_element_array = false;
    public boolean GL_APPLE_fence = false;
    public boolean GL_APPLE_float_pixels = false;
    public boolean GL_APPLE_flush_buffer_range = false;
    public boolean GL_APPLE_object_purgeable = false;
    public boolean GL_APPLE_packed_pixels = false;
    public boolean GL_APPLE_rgb_422 = false;
    public boolean GL_APPLE_row_bytes = false;
    public boolean GL_APPLE_texture_range = false;
    public boolean GL_APPLE_vertex_array_object = false;
    public boolean GL_APPLE_vertex_array_range = false;
    public boolean GL_APPLE_vertex_program_evaluators = false;
    public boolean GL_APPLE_ycbcr_422 = false;
    public boolean GL_ARB_ES2_compatibility = false;
    public boolean GL_ARB_ES3_1_compatibility = false;
    public boolean GL_ARB_ES3_compatibility = false;
    public boolean GL_ARB_arrays_of_arrays = false;
    public boolean GL_ARB_base_instance = false;
    public boolean GL_ARB_bindless_texture = false;
    public boolean GL_ARB_blend_func_extended = false;
    public boolean GL_ARB_buffer_storage = false;
    public boolean GL_ARB_cl_event = false;
    public boolean GL_ARB_clear_buffer_object = false;
    public boolean GL_ARB_clear_texture = false;
    public boolean GL_ARB_clip_control = false;
    public boolean GL_ARB_color_buffer_float = false;
    public boolean GL_ARB_compatibility = false;
    public boolean GL_ARB_compressed_texture_pixel_storage = false;
    public boolean GL_ARB_compute_shader = false;
    public boolean GL_ARB_compute_variable_group_size = false;
    public boolean GL_ARB_conditional_render_inverted = false;
    public boolean GL_ARB_conservative_depth = false;
    public boolean GL_ARB_copy_buffer = false;
    public boolean GL_ARB_copy_image = false;
    public boolean GL_ARB_cull_distance = false;
    public boolean GL_ARB_debug_output = false;
    public boolean GL_ARB_depth_buffer_float = false;
    public boolean GL_ARB_depth_clamp = false;
    public boolean GL_ARB_depth_texture = false;
    public boolean GL_ARB_derivative_control = false;
    public boolean GL_ARB_direct_state_access = false;
    public boolean GL_ARB_draw_buffers = false;
    public boolean GL_ARB_draw_buffers_blend = false;
    public boolean GL_ARB_draw_elements_base_vertex = false;
    public boolean GL_ARB_draw_indirect = false;
    public boolean GL_ARB_draw_instanced = false;
    public boolean GL_ARB_enhanced_layouts = false;
    public boolean GL_ARB_explicit_attrib_location = false;
    public boolean GL_ARB_explicit_uniform_location = false;
    public boolean GL_ARB_fragment_coord_conventions = false;
    public boolean GL_ARB_fragment_layer_viewport = false;
    public boolean GL_ARB_fragment_program = false;
    public boolean GL_ARB_fragment_program_shadow = false;
    public boolean GL_ARB_fragment_shader = false;
    public boolean GL_ARB_framebuffer_no_attachments = false;
    public boolean GL_ARB_framebuffer_object = false;
    public boolean GL_ARB_framebuffer_sRGB = false;
    public boolean GL_ARB_geometry_shader4 = false;
    public boolean GL_ARB_get_program_binary = false;
    public boolean GL_ARB_get_texture_sub_image = false;
    public boolean GL_ARB_gpu_shader5 = false;
    public boolean GL_ARB_gpu_shader_fp64 = false;
    public boolean GL_ARB_half_float_pixel = false;
    public boolean GL_ARB_half_float_vertex = false;
    public boolean GL_ARB_imaging = false;
    public boolean GL_ARB_indirect_parameters = false;
    public boolean GL_ARB_instanced_arrays = false;
    public boolean GL_ARB_internalformat_query = false;
    public boolean GL_ARB_internalformat_query2 = false;
    public boolean GL_ARB_invalidate_subdata = false;
    public boolean GL_ARB_map_buffer_alignment = false;
    public boolean GL_ARB_map_buffer_range = false;
    public boolean GL_ARB_matrix_palette = false;
    public boolean GL_ARB_multi_bind = false;
    public boolean GL_ARB_multi_draw_indirect = false;
    public boolean GL_ARB_multisample = false;
    public boolean GL_ARB_multitexture = false;
    public boolean GL_ARB_occlusion_query = false;
    public boolean GL_ARB_occlusion_query2 = false;
    public boolean GL_ARB_pipeline_statistics_query = false;
    public boolean GL_ARB_pixel_buffer_object = false;
    public boolean GL_ARB_point_parameters = false;
    public boolean GL_ARB_point_sprite = false;
    public boolean GL_ARB_program_interface_query = false;
    public boolean GL_ARB_provoking_vertex = false;
    public boolean GL_ARB_query_buffer_object = false;
    public boolean GL_ARB_robust_buffer_access_behavior = false;
    public boolean GL_ARB_robustness = false;
    public boolean GL_ARB_robustness_isolation = false;
    public boolean GL_ARB_sample_shading = false;
    public boolean GL_ARB_sampler_objects = false;
    public boolean GL_ARB_seamless_cube_map = false;
    public boolean GL_ARB_seamless_cubemap_per_texture = false;
    public boolean GL_ARB_separate_shader_objects = false;
    public boolean GL_ARB_shader_atomic_counters = false;
    public boolean GL_ARB_shader_bit_encoding = false;
    public boolean GL_ARB_shader_draw_parameters = false;
    public boolean GL_ARB_shader_group_vote = false;
    public boolean GL_ARB_shader_image_load_store = false;
    public boolean GL_ARB_shader_image_size = false;
    public boolean GL_ARB_shader_objects = false;
    public boolean GL_ARB_shader_precision = false;
    public boolean GL_ARB_shader_stencil_export = false;
    public boolean GL_ARB_shader_storage_buffer_object = false;
    public boolean GL_ARB_shader_subroutine = false;
    public boolean GL_ARB_shader_texture_image_samples = false;
    public boolean GL_ARB_shader_texture_lod = false;
    public boolean GL_ARB_shading_language_100 = false;
    public boolean GL_ARB_shading_language_420pack = false;
    public boolean GL_ARB_shading_language_include = false;
    public boolean GL_ARB_shading_language_packing = false;
    public boolean GL_ARB_shadow = false;
    public boolean GL_ARB_shadow_ambient = false;
    public boolean GL_ARB_sparse_buffer = false;
    public boolean GL_ARB_sparse_texture = false;
    public boolean GL_ARB_stencil_texturing = false;
    public boolean GL_ARB_sync = false;
    public boolean GL_ARB_tessellation_shader = false;
    public boolean GL_ARB_texture_barrier = false;
    public boolean GL_ARB_texture_border_clamp = false;
    public boolean GL_ARB_texture_buffer_object = false;
    public boolean GL_ARB_texture_buffer_object_rgb32 = false;
    public boolean GL_ARB_texture_buffer_range = false;
    public boolean GL_ARB_texture_compression = false;
    public boolean GL_ARB_texture_compression_bptc = false;
    public boolean GL_ARB_texture_compression_rgtc = false;
    public boolean GL_ARB_texture_cube_map = false;
    public boolean GL_ARB_texture_cube_map_array = false;
    public boolean GL_ARB_texture_env_add = false;
    public boolean GL_ARB_texture_env_combine = false;
    public boolean GL_ARB_texture_env_crossbar = false;
    public boolean GL_ARB_texture_env_dot3 = false;
    public boolean GL_ARB_texture_float = false;
    public boolean GL_ARB_texture_gather = false;
    public boolean GL_ARB_texture_mirror_clamp_to_edge = false;
    public boolean GL_ARB_texture_mirrored_repeat = false;
    public boolean GL_ARB_texture_multisample = false;
    public boolean GL_ARB_texture_non_power_of_two = false;
    public boolean GL_ARB_texture_query_levels = false;
    public boolean GL_ARB_texture_query_lod = false;
    public boolean GL_ARB_texture_rectangle = false;
    public boolean GL_ARB_texture_rg = false;
    public boolean GL_ARB_texture_rgb10_a2ui = false;
    public boolean GL_ARB_texture_stencil8 = false;
    public boolean GL_ARB_texture_storage = false;
    public boolean GL_ARB_texture_storage_multisample = false;
    public boolean GL_ARB_texture_swizzle = false;
    public boolean GL_ARB_texture_view = false;
    public boolean GL_ARB_timer_query = false;
    public boolean GL_ARB_transform_feedback2 = false;
    public boolean GL_ARB_transform_feedback3 = false;
    public boolean GL_ARB_transform_feedback_instanced = false;
    public boolean GL_ARB_transform_feedback_overflow_query = false;
    public boolean GL_ARB_transpose_matrix = false;
    public boolean GL_ARB_uniform_buffer_object = false;
    public boolean GL_ARB_vertex_array_bgra = false;
    public boolean GL_ARB_vertex_array_object = false;
    public boolean GL_ARB_vertex_attrib_64bit = false;
    public boolean GL_ARB_vertex_attrib_binding = false;
    public boolean GL_ARB_vertex_blend = false;
    public boolean GL_ARB_vertex_buffer_object = false;
    public boolean GL_ARB_vertex_program = false;
    public boolean GL_ARB_vertex_shader = false;
    public boolean GL_ARB_vertex_type_10f_11f_11f_rev = false;
    public boolean GL_ARB_vertex_type_2_10_10_10_rev = false;
    public boolean GL_ARB_viewport_array = false;
    public boolean GL_ARB_window_pos = false;
    public boolean GL_ATI_draw_buffers = false;
    public boolean GL_ATI_element_array = false;
    public boolean GL_ATI_envmap_bumpmap = false;
    public boolean GL_ATI_fragment_shader = false;
    public boolean GL_ATI_map_object_buffer = false;
    public boolean GL_ATI_meminfo = false;
    public boolean GL_ATI_pn_triangles = false;
    public boolean GL_ATI_separate_stencil = false;
    public boolean GL_ATI_shader_texture_lod = false;
    public boolean GL_ATI_text_fragment_shader = false;
    public boolean GL_ATI_texture_compression_3dc = false;
    public boolean GL_ATI_texture_env_combine3 = false;
    public boolean GL_ATI_texture_float = false;
    public boolean GL_ATI_texture_mirror_once = false;
    public boolean GL_ATI_vertex_array_object = false;
    public boolean GL_ATI_vertex_attrib_array_object = false;
    public boolean GL_ATI_vertex_streams = false;
    public boolean GL_EXT_Cg_shader = false;
    public boolean GL_EXT_abgr = false;
    public boolean GL_EXT_bgra = false;
    public boolean GL_EXT_bindable_uniform = false;
    public boolean GL_EXT_blend_color = false;
    public boolean GL_EXT_blend_equation_separate = false;
    public boolean GL_EXT_blend_func_separate = false;
    public boolean GL_EXT_blend_minmax = false;
    public boolean GL_EXT_blend_subtract = false;
    public boolean GL_EXT_compiled_vertex_array = false;
    public boolean GL_EXT_depth_bounds_test = false;
    public boolean GL_EXT_direct_state_access = false;
    public boolean GL_EXT_draw_buffers2 = false;
    public boolean GL_EXT_draw_instanced = false;
    public boolean GL_EXT_draw_range_elements = false;
    public boolean GL_EXT_fog_coord = false;
    public boolean GL_EXT_framebuffer_blit = false;
    public boolean GL_EXT_framebuffer_multisample = false;
    public boolean GL_EXT_framebuffer_multisample_blit_scaled = false;
    public boolean GL_EXT_framebuffer_object = false;
    public boolean GL_EXT_framebuffer_sRGB = false;
    public boolean GL_EXT_geometry_shader4 = false;
    public boolean GL_EXT_gpu_program_parameters = false;
    public boolean GL_EXT_gpu_shader4 = false;
    public boolean GL_EXT_multi_draw_arrays = false;
    public boolean GL_EXT_packed_depth_stencil = false;
    public boolean GL_EXT_packed_float = false;
    public boolean GL_EXT_packed_pixels = false;
    public boolean GL_EXT_paletted_texture = false;
    public boolean GL_EXT_pixel_buffer_object = false;
    public boolean GL_EXT_point_parameters = false;
    public boolean GL_EXT_provoking_vertex = false;
    public boolean GL_EXT_rescale_normal = false;
    public boolean GL_EXT_secondary_color = false;
    public boolean GL_EXT_separate_shader_objects = false;
    public boolean GL_EXT_separate_specular_color = false;
    public boolean GL_EXT_shader_image_load_store = false;
    public boolean GL_EXT_shadow_funcs = false;
    public boolean GL_EXT_shared_texture_palette = false;
    public boolean GL_EXT_stencil_clear_tag = false;
    public boolean GL_EXT_stencil_two_side = false;
    public boolean GL_EXT_stencil_wrap = false;
    public boolean GL_EXT_texture_3d = false;
    public boolean GL_EXT_texture_array = false;
    public boolean GL_EXT_texture_buffer_object = false;
    public boolean GL_EXT_texture_compression_latc = false;
    public boolean GL_EXT_texture_compression_rgtc = false;
    public boolean GL_EXT_texture_compression_s3tc = false;
    public boolean GL_EXT_texture_env_combine = false;
    public boolean GL_EXT_texture_env_dot3 = false;
    public boolean GL_EXT_texture_filter_anisotropic = false;
    public boolean GL_EXT_texture_integer = false;
    public boolean GL_EXT_texture_lod_bias = false;
    public boolean GL_EXT_texture_mirror_clamp = false;
    public boolean GL_EXT_texture_rectangle = false;
    public boolean GL_EXT_texture_sRGB = false;
    public boolean GL_EXT_texture_sRGB_decode = false;
    public boolean GL_EXT_texture_shared_exponent = false;
    public boolean GL_EXT_texture_snorm = false;
    public boolean GL_EXT_texture_swizzle = false;
    public boolean GL_EXT_timer_query = false;
    public boolean GL_EXT_transform_feedback = false;
    public boolean GL_EXT_vertex_array_bgra = false;
    public boolean GL_EXT_vertex_attrib_64bit = false;
    public boolean GL_EXT_vertex_shader = false;
    public boolean GL_EXT_vertex_weighting = false;
    public boolean OpenGL11 = false;
    public boolean OpenGL12 = false;
    public boolean OpenGL13 = false;
    public boolean OpenGL14 = false;
    public boolean OpenGL15 = false;
    public boolean OpenGL20 = false;
    public boolean OpenGL21 = false;
    public boolean OpenGL30 = false;
    public boolean OpenGL31 = false;
    public boolean OpenGL32 = false;
    public boolean OpenGL33 = false;
    public boolean OpenGL40 = false;
    public boolean OpenGL41 = false;
    public boolean OpenGL42 = false;
    public boolean OpenGL43 = false;
    public boolean OpenGL44 = false;
    public boolean OpenGL45 = false;
    public boolean GL_GREMEDY_frame_terminator = false;
    public boolean GL_GREMEDY_string_marker = false;
    public boolean GL_HP_occlusion_test = false;
    public boolean GL_IBM_rasterpos_clip = false;
    public boolean GL_INTEL_map_texture = false;
    public boolean GL_KHR_context_flush_control = false;
    public boolean GL_KHR_debug = false;
    public boolean GL_KHR_robust_buffer_access_behavior = false;
    public boolean GL_KHR_robustness = false;
    public boolean GL_KHR_texture_compression_astc_ldr = false;
    public boolean GL_NVX_gpu_memory_info = false;
    public boolean GL_NV_bindless_multi_draw_indirect = false;
    public boolean GL_NV_bindless_texture = false;
    public boolean GL_NV_blend_equation_advanced = false;
    public boolean GL_NV_blend_square = false;
    public boolean GL_NV_compute_program5 = false;
    public boolean GL_NV_conditional_render = false;
    public boolean GL_NV_copy_depth_to_color = false;
    public boolean GL_NV_copy_image = false;
    public boolean GL_NV_deep_texture3D = false;
    public boolean GL_NV_depth_buffer_float = false;
    public boolean GL_NV_depth_clamp = false;
    public boolean GL_NV_draw_texture = false;
    public boolean GL_NV_evaluators = false;
    public boolean GL_NV_explicit_multisample = false;
    public boolean GL_NV_fence = false;
    public boolean GL_NV_float_buffer = false;
    public boolean GL_NV_fog_distance = false;
    public boolean GL_NV_fragment_program = false;
    public boolean GL_NV_fragment_program2 = false;
    public boolean GL_NV_fragment_program4 = false;
    public boolean GL_NV_fragment_program_option = false;
    public boolean GL_NV_framebuffer_multisample_coverage = false;
    public boolean GL_NV_geometry_program4 = false;
    public boolean GL_NV_geometry_shader4 = false;
    public boolean GL_NV_gpu_program4 = false;
    public boolean GL_NV_gpu_program5 = false;
    public boolean GL_NV_gpu_program5_mem_extended = false;
    public boolean GL_NV_gpu_shader5 = false;
    public boolean GL_NV_half_float = false;
    public boolean GL_NV_light_max_exponent = false;
    public boolean GL_NV_multisample_coverage = false;
    public boolean GL_NV_multisample_filter_hint = false;
    public boolean GL_NV_occlusion_query = false;
    public boolean GL_NV_packed_depth_stencil = false;
    public boolean GL_NV_parameter_buffer_object = false;
    public boolean GL_NV_parameter_buffer_object2 = false;
    public boolean GL_NV_path_rendering = false;
    public boolean GL_NV_pixel_data_range = false;
    public boolean GL_NV_point_sprite = false;
    public boolean GL_NV_present_video = false;
    public boolean GL_NV_primitive_restart = false;
    public boolean GL_NV_register_combiners = false;
    public boolean GL_NV_register_combiners2 = false;
    public boolean GL_NV_shader_atomic_counters = false;
    public boolean GL_NV_shader_atomic_float = false;
    public boolean GL_NV_shader_buffer_load = false;
    public boolean GL_NV_shader_buffer_store = false;
    public boolean GL_NV_shader_storage_buffer_object = false;
    public boolean GL_NV_tessellation_program5 = false;
    public boolean GL_NV_texgen_reflection = false;
    public boolean GL_NV_texture_barrier = false;
    public boolean GL_NV_texture_compression_vtc = false;
    public boolean GL_NV_texture_env_combine4 = false;
    public boolean GL_NV_texture_expand_normal = false;
    public boolean GL_NV_texture_multisample = false;
    public boolean GL_NV_texture_rectangle = false;
    public boolean GL_NV_texture_shader = false;
    public boolean GL_NV_texture_shader2 = false;
    public boolean GL_NV_texture_shader3 = false;
    public boolean GL_NV_transform_feedback = false;
    public boolean GL_NV_transform_feedback2 = false;
    public boolean GL_NV_vertex_array_range = false;
    public boolean GL_NV_vertex_array_range2 = false;
    public boolean GL_NV_vertex_attrib_integer_64bit = false;
    public boolean GL_NV_vertex_buffer_unified_memory = false;
    public boolean GL_NV_vertex_program = false;
    public boolean GL_NV_vertex_program1_1 = false;
    public boolean GL_NV_vertex_program2 = false;
    public boolean GL_NV_vertex_program2_option = false;
    public boolean GL_NV_vertex_program3 = false;
    public boolean GL_NV_vertex_program4 = false;
    public boolean GL_NV_video_capture = false;
    public boolean GL_SGIS_generate_mipmap = false;
    public boolean GL_SGIS_texture_lod = false;
    public boolean GL_SUN_slice_accum = false;

    // AMD_debug_output
    long glDebugMessageEnableAMD;
    long glDebugMessageInsertAMD;
    long glDebugMessageCallbackAMD;
    long glGetDebugMessageLogAMD;
    // AMD_draw_buffers_blend
    long glBlendFuncIndexedAMD;
    long glBlendFuncSeparateIndexedAMD;
    long glBlendEquationIndexedAMD;
    long glBlendEquationSeparateIndexedAMD;
    // AMD_interleaved_elements
    long glVertexAttribParameteriAMD;
    // AMD_multi_draw_indirect
    long glMultiDrawArraysIndirectAMD;
    long glMultiDrawElementsIndirectAMD;
    // AMD_name_gen_delete
    long glGenNamesAMD;
    long glDeleteNamesAMD;
    long glIsNameAMD;
    // AMD_performance_monitor
    long glGetPerfMonitorGroupsAMD;
    long glGetPerfMonitorCountersAMD;
    long glGetPerfMonitorGroupStringAMD;
    long glGetPerfMonitorCounterStringAMD;
    long glGetPerfMonitorCounterInfoAMD;
    long glGenPerfMonitorsAMD;
    long glDeletePerfMonitorsAMD;
    long glSelectPerfMonitorCountersAMD;
    long glBeginPerfMonitorAMD;
    long glEndPerfMonitorAMD;
    long glGetPerfMonitorCounterDataAMD;
    // AMD_sample_positions
    long glSetMultisamplefvAMD;
    // AMD_sparse_texture
    long glTexStorageSparseAMD;
    long glTextureStorageSparseAMD;
    // AMD_stencil_operation_extended
    long glStencilOpValueAMD;
    // AMD_vertex_shader_tessellator
    long glTessellationFactorAMD;
    long glTessellationModeAMD;
    // APPLE_element_array
    long glElementPointerAPPLE;
    long glDrawElementArrayAPPLE;
    long glDrawRangeElementArrayAPPLE;
    long glMultiDrawElementArrayAPPLE;
    long glMultiDrawRangeElementArrayAPPLE;
    // APPLE_fence
    long glGenFencesAPPLE;
    long glDeleteFencesAPPLE;
    long glSetFenceAPPLE;
    long glIsFenceAPPLE;
    long glTestFenceAPPLE;
    long glFinishFenceAPPLE;
    long glTestObjectAPPLE;
    long glFinishObjectAPPLE;
    // APPLE_flush_buffer_range
    long glBufferParameteriAPPLE;
    long glFlushMappedBufferRangeAPPLE;
    // APPLE_object_purgeable
    long glObjectPurgeableAPPLE;
    long glObjectUnpurgeableAPPLE;
    long glGetObjectParameterivAPPLE;
    // APPLE_texture_range
    long glTextureRangeAPPLE;
    long glGetTexParameterPointervAPPLE;
    // APPLE_vertex_array_object
    long glBindVertexArrayAPPLE;
    long glDeleteVertexArraysAPPLE;
    long glGenVertexArraysAPPLE;
    long glIsVertexArrayAPPLE;
    // APPLE_vertex_array_range
    long glVertexArrayRangeAPPLE;
    long glFlushVertexArrayRangeAPPLE;
    long glVertexArrayParameteriAPPLE;
    // APPLE_vertex_program_evaluators
    long glEnableVertexAttribAPPLE;
    long glDisableVertexAttribAPPLE;
    long glIsVertexAttribEnabledAPPLE;
    long glMapVertexAttrib1dAPPLE;
    long glMapVertexAttrib1fAPPLE;
    long glMapVertexAttrib2dAPPLE;
    long glMapVertexAttrib2fAPPLE;
    // ARB_bindless_texture
    long glGetTextureHandleARB;
    long glGetTextureSamplerHandleARB;
    long glMakeTextureHandleResidentARB;
    long glMakeTextureHandleNonResidentARB;
    long glGetImageHandleARB;
    long glMakeImageHandleResidentARB;
    long glMakeImageHandleNonResidentARB;
    long glUniformHandleui64ARB;
    long glUniformHandleui64vARB;
    long glProgramUniformHandleui64ARB;
    long glProgramUniformHandleui64vARB;
    long glIsTextureHandleResidentARB;
    long glIsImageHandleResidentARB;
    long glVertexAttribL1ui64ARB;
    long glVertexAttribL1ui64vARB;
    long glGetVertexAttribLui64vARB;
    // ARB_buffer_object
    long glBindBufferARB;
    long glDeleteBuffersARB;
    long glGenBuffersARB;
    long glIsBufferARB;
    long glBufferDataARB;
    long glBufferSubDataARB;
    long glGetBufferSubDataARB;
    long glMapBufferARB;
    long glUnmapBufferARB;
    long glGetBufferParameterivARB;
    long glGetBufferPointervARB;
    // ARB_buffer_storage
    long glNamedBufferStorageEXT;
    // ARB_cl_event
    long glCreateSyncFromCLeventARB;
    // ARB_clear_buffer_object
    long glClearNamedBufferDataEXT;
    long glClearNamedBufferSubDataEXT;
    // ARB_color_buffer_float
    long glClampColorARB;
    // ARB_compute_variable_group_size
    long glDispatchComputeGroupSizeARB;
    // ARB_debug_output
    long glDebugMessageControlARB;
    long glDebugMessageInsertARB;
    long glDebugMessageCallbackARB;
    long glGetDebugMessageLogARB;
    // ARB_draw_buffers
    long glDrawBuffersARB;
    // ARB_draw_buffers_blend
    long glBlendEquationiARB;
    long glBlendEquationSeparateiARB;
    long glBlendFunciARB;
    long glBlendFuncSeparateiARB;
    // ARB_draw_instanced
    long glDrawArraysInstancedARB;
    long glDrawElementsInstancedARB;
    // ARB_framebuffer_no_attachments
    long glNamedFramebufferParameteriEXT;
    long glGetNamedFramebufferParameterivEXT;
    // ARB_geometry_shader4
    long glProgramParameteriARB;
    long glFramebufferTextureARB;
    long glFramebufferTextureLayerARB;
    long glFramebufferTextureFaceARB;
    // ARB_gpu_shader_fp64
    long glProgramUniform1dEXT;
    long glProgramUniform2dEXT;
    long glProgramUniform3dEXT;
    long glProgramUniform4dEXT;
    long glProgramUniform1dvEXT;
    long glProgramUniform2dvEXT;
    long glProgramUniform3dvEXT;
    long glProgramUniform4dvEXT;
    long glProgramUniformMatrix2dvEXT;
    long glProgramUniformMatrix3dvEXT;
    long glProgramUniformMatrix4dvEXT;
    long glProgramUniformMatrix2x3dvEXT;
    long glProgramUniformMatrix2x4dvEXT;
    long glProgramUniformMatrix3x2dvEXT;
    long glProgramUniformMatrix3x4dvEXT;
    long glProgramUniformMatrix4x2dvEXT;
    long glProgramUniformMatrix4x3dvEXT;
    // ARB_imaging
    long glColorTable;
    long glColorSubTable;
    long glColorTableParameteriv;
    long glColorTableParameterfv;
    long glCopyColorSubTable;
    long glCopyColorTable;
    long glGetColorTable;
    long glGetColorTableParameteriv;
    long glGetColorTableParameterfv;
    long glHistogram;
    long glResetHistogram;
    long glGetHistogram;
    long glGetHistogramParameterfv;
    long glGetHistogramParameteriv;
    long glMinmax;
    long glResetMinmax;
    long glGetMinmax;
    long glGetMinmaxParameterfv;
    long glGetMinmaxParameteriv;
    long glConvolutionFilter1D;
    long glConvolutionFilter2D;
    long glConvolutionParameterf;
    long glConvolutionParameterfv;
    long glConvolutionParameteri;
    long glConvolutionParameteriv;
    long glCopyConvolutionFilter1D;
    long glCopyConvolutionFilter2D;
    long glGetConvolutionFilter;
    long glGetConvolutionParameterfv;
    long glGetConvolutionParameteriv;
    long glSeparableFilter2D;
    long glGetSeparableFilter;
    // ARB_indirect_parameters
    long glMultiDrawArraysIndirectCountARB;
    long glMultiDrawElementsIndirectCountARB;
    // ARB_instanced_arrays
    long glVertexAttribDivisorARB;
    // ARB_matrix_palette
    long glCurrentPaletteMatrixARB;
    long glMatrixIndexPointerARB;
    long glMatrixIndexubvARB;
    long glMatrixIndexusvARB;
    long glMatrixIndexuivARB;
    // ARB_multisample
    long glSampleCoverageARB;
    // ARB_multitexture
    long glClientActiveTextureARB;
    long glActiveTextureARB;
    long glMultiTexCoord1fARB;
    long glMultiTexCoord1dARB;
    long glMultiTexCoord1iARB;
    long glMultiTexCoord1sARB;
    long glMultiTexCoord2fARB;
    long glMultiTexCoord2dARB;
    long glMultiTexCoord2iARB;
    long glMultiTexCoord2sARB;
    long glMultiTexCoord3fARB;
    long glMultiTexCoord3dARB;
    long glMultiTexCoord3iARB;
    long glMultiTexCoord3sARB;
    long glMultiTexCoord4fARB;
    long glMultiTexCoord4dARB;
    long glMultiTexCoord4iARB;
    long glMultiTexCoord4sARB;
    // ARB_occlusion_query
    long glGenQueriesARB;
    long glDeleteQueriesARB;
    long glIsQueryARB;
    long glBeginQueryARB;
    long glEndQueryARB;
    long glGetQueryivARB;
    long glGetQueryObjectivARB;
    long glGetQueryObjectuivARB;
    // ARB_point_parameters
    long glPointParameterfARB;
    long glPointParameterfvARB;
    // ARB_program
    long glProgramStringARB;
    long glBindProgramARB;
    long glDeleteProgramsARB;
    long glGenProgramsARB;
    long glProgramEnvParameter4fARB;
    long glProgramEnvParameter4dARB;
    long glProgramEnvParameter4fvARB;
    long glProgramEnvParameter4dvARB;
    long glProgramLocalParameter4fARB;
    long glProgramLocalParameter4dARB;
    long glProgramLocalParameter4fvARB;
    long glProgramLocalParameter4dvARB;
    long glGetProgramEnvParameterfvARB;
    long glGetProgramEnvParameterdvARB;
    long glGetProgramLocalParameterfvARB;
    long glGetProgramLocalParameterdvARB;
    long glGetProgramivARB;
    long glGetProgramStringARB;
    long glIsProgramARB;
    // ARB_robustness
    long glGetGraphicsResetStatusARB;
    long glGetnMapdvARB;
    long glGetnMapfvARB;
    long glGetnMapivARB;
    long glGetnPixelMapfvARB;
    long glGetnPixelMapuivARB;
    long glGetnPixelMapusvARB;
    long glGetnPolygonStippleARB;
    long glGetnTexImageARB;
    long glReadnPixelsARB;
    long glGetnColorTableARB;
    long glGetnConvolutionFilterARB;
    long glGetnSeparableFilterARB;
    long glGetnHistogramARB;
    long glGetnMinmaxARB;
    long glGetnCompressedTexImageARB;
    long glGetnUniformfvARB;
    long glGetnUniformivARB;
    long glGetnUniformuivARB;
    long glGetnUniformdvARB;
    // ARB_sample_shading
    long glMinSampleShadingARB;
    // ARB_shader_objects
    long glDeleteObjectARB;
    long glGetHandleARB;
    long glDetachObjectARB;
    long glCreateShaderObjectARB;
    long glShaderSourceARB;
    long glCompileShaderARB;
    long glCreateProgramObjectARB;
    long glAttachObjectARB;
    long glLinkProgramARB;
    long glUseProgramObjectARB;
    long glValidateProgramARB;
    long glUniform1fARB;
    long glUniform2fARB;
    long glUniform3fARB;
    long glUniform4fARB;
    long glUniform1iARB;
    long glUniform2iARB;
    long glUniform3iARB;
    long glUniform4iARB;
    long glUniform1fvARB;
    long glUniform2fvARB;
    long glUniform3fvARB;
    long glUniform4fvARB;
    long glUniform1ivARB;
    long glUniform2ivARB;
    long glUniform3ivARB;
    long glUniform4ivARB;
    long glUniformMatrix2fvARB;
    long glUniformMatrix3fvARB;
    long glUniformMatrix4fvARB;
    long glGetObjectParameterfvARB;
    long glGetObjectParameterivARB;
    long glGetInfoLogARB;
    long glGetAttachedObjectsARB;
    long glGetUniformLocationARB;
    long glGetActiveUniformARB;
    long glGetUniformfvARB;
    long glGetUniformivARB;
    long glGetShaderSourceARB;
    // ARB_shading_language_include
    long glNamedStringARB;
    long glDeleteNamedStringARB;
    long glCompileShaderIncludeARB;
    long glIsNamedStringARB;
    long glGetNamedStringARB;
    long glGetNamedStringivARB;
    // ARB_sparse_buffer
    long glBufferPageCommitmentARB;
    // ARB_sparse_texture
    long glTexPageCommitmentARB;
    long glTexturePageCommitmentEXT;
    // ARB_texture_buffer_object
    long glTexBufferARB;
    // ARB_texture_buffer_range
    long glTextureBufferRangeEXT;
    // ARB_texture_compression
    long glCompressedTexImage1DARB;
    long glCompressedTexImage2DARB;
    long glCompressedTexImage3DARB;
    long glCompressedTexSubImage1DARB;
    long glCompressedTexSubImage2DARB;
    long glCompressedTexSubImage3DARB;
    long glGetCompressedTexImageARB;
    // ARB_texture_storage
    long glTextureStorage1DEXT;
    long glTextureStorage2DEXT;
    long glTextureStorage3DEXT;
    // ARB_texture_storage_multisample
    long glTextureStorage2DMultisampleEXT;
    long glTextureStorage3DMultisampleEXT;
    // ARB_transpose_matrix
    long glLoadTransposeMatrixfARB;
    long glMultTransposeMatrixfARB;
    // ARB_vertex_attrib_64bit
    long glVertexArrayVertexAttribLOffsetEXT;
    // ARB_vertex_blend
    long glWeightbvARB;
    long glWeightsvARB;
    long glWeightivARB;
    long glWeightfvARB;
    long glWeightdvARB;
    long glWeightubvARB;
    long glWeightusvARB;
    long glWeightuivARB;
    long glWeightPointerARB;
    long glVertexBlendARB;
    // ARB_vertex_shader
    long glVertexAttrib1sARB;
    long glVertexAttrib1fARB;
    long glVertexAttrib1dARB;
    long glVertexAttrib2sARB;
    long glVertexAttrib2fARB;
    long glVertexAttrib2dARB;
    long glVertexAttrib3sARB;
    long glVertexAttrib3fARB;
    long glVertexAttrib3dARB;
    long glVertexAttrib4sARB;
    long glVertexAttrib4fARB;
    long glVertexAttrib4dARB;
    long glVertexAttrib4NubARB;
    long glVertexAttribPointerARB;
    long glEnableVertexAttribArrayARB;
    long glDisableVertexAttribArrayARB;
    long glBindAttribLocationARB;
    long glGetActiveAttribARB;
    long glGetAttribLocationARB;
    long glGetVertexAttribfvARB;
    long glGetVertexAttribdvARB;
    long glGetVertexAttribivARB;
    long glGetVertexAttribPointervARB;
    // ARB_window_pos
    long glWindowPos2fARB;
    long glWindowPos2dARB;
    long glWindowPos2iARB;
    long glWindowPos2sARB;
    long glWindowPos3fARB;
    long glWindowPos3dARB;
    long glWindowPos3iARB;
    long glWindowPos3sARB;
    // ATI_draw_buffers
    long glDrawBuffersATI;
    // ATI_element_array
    long glElementPointerATI;
    long glDrawElementArrayATI;
    long glDrawRangeElementArrayATI;
    // ATI_envmap_bumpmap
    long glTexBumpParameterfvATI;
    long glTexBumpParameterivATI;
    long glGetTexBumpParameterfvATI;
    long glGetTexBumpParameterivATI;
    // ATI_fragment_shader
    long glGenFragmentShadersATI;
    long glBindFragmentShaderATI;
    long glDeleteFragmentShaderATI;
    long glBeginFragmentShaderATI;
    long glEndFragmentShaderATI;
    long glPassTexCoordATI;
    long glSampleMapATI;
    long glColorFragmentOp1ATI;
    long glColorFragmentOp2ATI;
    long glColorFragmentOp3ATI;
    long glAlphaFragmentOp1ATI;
    long glAlphaFragmentOp2ATI;
    long glAlphaFragmentOp3ATI;
    long glSetFragmentShaderConstantATI;
    // ATI_map_object_buffer
    long glMapObjectBufferATI;
    long glUnmapObjectBufferATI;
    // ATI_pn_triangles
    long glPNTrianglesfATI;
    long glPNTrianglesiATI;
    // ATI_separate_stencil
    long glStencilOpSeparateATI;
    long glStencilFuncSeparateATI;
    // ATI_vertex_array_object
    long glNewObjectBufferATI;
    long glIsObjectBufferATI;
    long glUpdateObjectBufferATI;
    long glGetObjectBufferfvATI;
    long glGetObjectBufferivATI;
    long glFreeObjectBufferATI;
    long glArrayObjectATI;
    long glGetArrayObjectfvATI;
    long glGetArrayObjectivATI;
    long glVariantArrayObjectATI;
    long glGetVariantArrayObjectfvATI;
    long glGetVariantArrayObjectivATI;
    // ATI_vertex_attrib_array_object
    long glVertexAttribArrayObjectATI;
    long glGetVertexAttribArrayObjectfvATI;
    long glGetVertexAttribArrayObjectivATI;
    // ATI_vertex_streams
    long glVertexStream2fATI;
    long glVertexStream2dATI;
    long glVertexStream2iATI;
    long glVertexStream2sATI;
    long glVertexStream3fATI;
    long glVertexStream3dATI;
    long glVertexStream3iATI;
    long glVertexStream3sATI;
    long glVertexStream4fATI;
    long glVertexStream4dATI;
    long glVertexStream4iATI;
    long glVertexStream4sATI;
    long glNormalStream3bATI;
    long glNormalStream3fATI;
    long glNormalStream3dATI;
    long glNormalStream3iATI;
    long glNormalStream3sATI;
    long glClientActiveVertexStreamATI;
    long glVertexBlendEnvfATI;
    long glVertexBlendEnviATI;
    // EXT_bindable_uniform
    long glUniformBufferEXT;
    long glGetUniformBufferSizeEXT;
    long glGetUniformOffsetEXT;
    // EXT_blend_color
    long glBlendColorEXT;
    // EXT_blend_equation_separate
    long glBlendEquationSeparateEXT;
    // EXT_blend_func_separate
    long glBlendFuncSeparateEXT;
    // EXT_blend_minmax
    long glBlendEquationEXT;
    // EXT_compiled_vertex_array
    long glLockArraysEXT;
    long glUnlockArraysEXT;
    // EXT_depth_bounds_test
    long glDepthBoundsEXT;
    // EXT_direct_state_access
    long glClientAttribDefaultEXT;
    long glPushClientAttribDefaultEXT;
    long glMatrixLoadfEXT;
    long glMatrixLoaddEXT;
    long glMatrixMultfEXT;
    long glMatrixMultdEXT;
    long glMatrixLoadIdentityEXT;
    long glMatrixRotatefEXT;
    long glMatrixRotatedEXT;
    long glMatrixScalefEXT;
    long glMatrixScaledEXT;
    long glMatrixTranslatefEXT;
    long glMatrixTranslatedEXT;
    long glMatrixOrthoEXT;
    long glMatrixFrustumEXT;
    long glMatrixPushEXT;
    long glMatrixPopEXT;
    long glTextureParameteriEXT;
    long glTextureParameterivEXT;
    long glTextureParameterfEXT;
    long glTextureParameterfvEXT;
    long glTextureImage1DEXT;
    long glTextureImage2DEXT;
    long glTextureSubImage1DEXT;
    long glTextureSubImage2DEXT;
    long glCopyTextureImage1DEXT;
    long glCopyTextureImage2DEXT;
    long glCopyTextureSubImage1DEXT;
    long glCopyTextureSubImage2DEXT;
    long glGetTextureImageEXT;
    long glGetTextureParameterfvEXT;
    long glGetTextureParameterivEXT;
    long glGetTextureLevelParameterfvEXT;
    long glGetTextureLevelParameterivEXT;
    long glTextureImage3DEXT;
    long glTextureSubImage3DEXT;
    long glCopyTextureSubImage3DEXT;
    long glBindMultiTextureEXT;
    long glMultiTexCoordPointerEXT;
    long glMultiTexEnvfEXT;
    long glMultiTexEnvfvEXT;
    long glMultiTexEnviEXT;
    long glMultiTexEnvivEXT;
    long glMultiTexGendEXT;
    long glMultiTexGendvEXT;
    long glMultiTexGenfEXT;
    long glMultiTexGenfvEXT;
    long glMultiTexGeniEXT;
    long glMultiTexGenivEXT;
    long glGetMultiTexEnvfvEXT;
    long glGetMultiTexEnvivEXT;
    long glGetMultiTexGendvEXT;
    long glGetMultiTexGenfvEXT;
    long glGetMultiTexGenivEXT;
    long glMultiTexParameteriEXT;
    long glMultiTexParameterivEXT;
    long glMultiTexParameterfEXT;
    long glMultiTexParameterfvEXT;
    long glMultiTexImage1DEXT;
    long glMultiTexImage2DEXT;
    long glMultiTexSubImage1DEXT;
    long glMultiTexSubImage2DEXT;
    long glCopyMultiTexImage1DEXT;
    long glCopyMultiTexImage2DEXT;
    long glCopyMultiTexSubImage1DEXT;
    long glCopyMultiTexSubImage2DEXT;
    long glGetMultiTexImageEXT;
    long glGetMultiTexParameterfvEXT;
    long glGetMultiTexParameterivEXT;
    long glGetMultiTexLevelParameterfvEXT;
    long glGetMultiTexLevelParameterivEXT;
    long glMultiTexImage3DEXT;
    long glMultiTexSubImage3DEXT;
    long glCopyMultiTexSubImage3DEXT;
    long glEnableClientStateIndexedEXT;
    long glDisableClientStateIndexedEXT;
    long glEnableClientStateiEXT;
    long glDisableClientStateiEXT;
    long glGetFloatIndexedvEXT;
    long glGetDoubleIndexedvEXT;
    long glGetPointerIndexedvEXT;
    long glGetFloati_vEXT;
    long glGetDoublei_vEXT;
    long glGetPointeri_vEXT;
    long glNamedProgramStringEXT;
    long glNamedProgramLocalParameter4dEXT;
    long glNamedProgramLocalParameter4dvEXT;
    long glNamedProgramLocalParameter4fEXT;
    long glNamedProgramLocalParameter4fvEXT;
    long glGetNamedProgramLocalParameterdvEXT;
    long glGetNamedProgramLocalParameterfvEXT;
    long glGetNamedProgramivEXT;
    long glGetNamedProgramStringEXT;
    long glCompressedTextureImage3DEXT;
    long glCompressedTextureImage2DEXT;
    long glCompressedTextureImage1DEXT;
    long glCompressedTextureSubImage3DEXT;
    long glCompressedTextureSubImage2DEXT;
    long glCompressedTextureSubImage1DEXT;
    long glGetCompressedTextureImageEXT;
    long glCompressedMultiTexImage3DEXT;
    long glCompressedMultiTexImage2DEXT;
    long glCompressedMultiTexImage1DEXT;
    long glCompressedMultiTexSubImage3DEXT;
    long glCompressedMultiTexSubImage2DEXT;
    long glCompressedMultiTexSubImage1DEXT;
    long glGetCompressedMultiTexImageEXT;
    long glMatrixLoadTransposefEXT;
    long glMatrixLoadTransposedEXT;
    long glMatrixMultTransposefEXT;
    long glMatrixMultTransposedEXT;
    long glNamedBufferDataEXT;
    long glNamedBufferSubDataEXT;
    long glMapNamedBufferEXT;
    long glUnmapNamedBufferEXT;
    long glGetNamedBufferParameterivEXT;
    long glGetNamedBufferPointervEXT;
    long glGetNamedBufferSubDataEXT;
    long glProgramUniform1fEXT;
    long glProgramUniform2fEXT;
    long glProgramUniform3fEXT;
    long glProgramUniform4fEXT;
    long glProgramUniform1iEXT;
    long glProgramUniform2iEXT;
    long glProgramUniform3iEXT;
    long glProgramUniform4iEXT;
    long glProgramUniform1fvEXT;
    long glProgramUniform2fvEXT;
    long glProgramUniform3fvEXT;
    long glProgramUniform4fvEXT;
    long glProgramUniform1ivEXT;
    long glProgramUniform2ivEXT;
    long glProgramUniform3ivEXT;
    long glProgramUniform4ivEXT;
    long glProgramUniformMatrix2fvEXT;
    long glProgramUniformMatrix3fvEXT;
    long glProgramUniformMatrix4fvEXT;
    long glProgramUniformMatrix2x3fvEXT;
    long glProgramUniformMatrix3x2fvEXT;
    long glProgramUniformMatrix2x4fvEXT;
    long glProgramUniformMatrix4x2fvEXT;
    long glProgramUniformMatrix3x4fvEXT;
    long glProgramUniformMatrix4x3fvEXT;
    long glTextureBufferEXT;
    long glMultiTexBufferEXT;
    long glTextureParameterIivEXT;
    long glTextureParameterIuivEXT;
    long glGetTextureParameterIivEXT;
    long glGetTextureParameterIuivEXT;
    long glMultiTexParameterIivEXT;
    long glMultiTexParameterIuivEXT;
    long glGetMultiTexParameterIivEXT;
    long glGetMultiTexParameterIuivEXT;
    long glProgramUniform1uiEXT;
    long glProgramUniform2uiEXT;
    long glProgramUniform3uiEXT;
    long glProgramUniform4uiEXT;
    long glProgramUniform1uivEXT;
    long glProgramUniform2uivEXT;
    long glProgramUniform3uivEXT;
    long glProgramUniform4uivEXT;
    long glNamedProgramLocalParameters4fvEXT;
    long glNamedProgramLocalParameterI4iEXT;
    long glNamedProgramLocalParameterI4ivEXT;
    long glNamedProgramLocalParametersI4ivEXT;
    long glNamedProgramLocalParameterI4uiEXT;
    long glNamedProgramLocalParameterI4uivEXT;
    long glNamedProgramLocalParametersI4uivEXT;
    long glGetNamedProgramLocalParameterIivEXT;
    long glGetNamedProgramLocalParameterIuivEXT;
    long glNamedRenderbufferStorageEXT;
    long glGetNamedRenderbufferParameterivEXT;
    long glNamedRenderbufferStorageMultisampleEXT;
    long glNamedRenderbufferStorageMultisampleCoverageEXT;
    long glCheckNamedFramebufferStatusEXT;
    long glNamedFramebufferTexture1DEXT;
    long glNamedFramebufferTexture2DEXT;
    long glNamedFramebufferTexture3DEXT;
    long glNamedFramebufferRenderbufferEXT;
    long glGetNamedFramebufferAttachmentParameterivEXT;
    long glGenerateTextureMipmapEXT;
    long glGenerateMultiTexMipmapEXT;
    long glFramebufferDrawBufferEXT;
    long glFramebufferDrawBuffersEXT;
    long glFramebufferReadBufferEXT;
    long glGetFramebufferParameterivEXT;
    long glNamedCopyBufferSubDataEXT;
    long glNamedFramebufferTextureEXT;
    long glNamedFramebufferTextureLayerEXT;
    long glNamedFramebufferTextureFaceEXT;
    long glTextureRenderbufferEXT;
    long glMultiTexRenderbufferEXT;
    long glVertexArrayVertexOffsetEXT;
    long glVertexArrayColorOffsetEXT;
    long glVertexArrayEdgeFlagOffsetEXT;
    long glVertexArrayIndexOffsetEXT;
    long glVertexArrayNormalOffsetEXT;
    long glVertexArrayTexCoordOffsetEXT;
    long glVertexArrayMultiTexCoordOffsetEXT;
    long glVertexArrayFogCoordOffsetEXT;
    long glVertexArraySecondaryColorOffsetEXT;
    long glVertexArrayVertexAttribOffsetEXT;
    long glVertexArrayVertexAttribIOffsetEXT;
    long glEnableVertexArrayEXT;
    long glDisableVertexArrayEXT;
    long glEnableVertexArrayAttribEXT;
    long glDisableVertexArrayAttribEXT;
    long glGetVertexArrayIntegervEXT;
    long glGetVertexArrayPointervEXT;
    long glGetVertexArrayIntegeri_vEXT;
    long glGetVertexArrayPointeri_vEXT;
    long glMapNamedBufferRangeEXT;
    long glFlushMappedNamedBufferRangeEXT;
    // EXT_draw_buffers2
    long glColorMaskIndexedEXT;
    long glGetBooleanIndexedvEXT;
    long glGetIntegerIndexedvEXT;
    long glEnableIndexedEXT;
    long glDisableIndexedEXT;
    long glIsEnabledIndexedEXT;
    // EXT_draw_instanced
    long glDrawArraysInstancedEXT;
    long glDrawElementsInstancedEXT;
    // EXT_draw_range_elements
    long glDrawRangeElementsEXT;
    // EXT_fog_coord
    long glFogCoordfEXT;
    long glFogCoorddEXT;
    long glFogCoordPointerEXT;
    // EXT_framebuffer_blit
    long glBlitFramebufferEXT;
    // EXT_framebuffer_multisample
    long glRenderbufferStorageMultisampleEXT;
    // EXT_framebuffer_object
    long glIsRenderbufferEXT;
    long glBindRenderbufferEXT;
    long glDeleteRenderbuffersEXT;
    long glGenRenderbuffersEXT;
    long glRenderbufferStorageEXT;
    long glGetRenderbufferParameterivEXT;
    long glIsFramebufferEXT;
    long glBindFramebufferEXT;
    long glDeleteFramebuffersEXT;
    long glGenFramebuffersEXT;
    long glCheckFramebufferStatusEXT;
    long glFramebufferTexture1DEXT;
    long glFramebufferTexture2DEXT;
    long glFramebufferTexture3DEXT;
    long glFramebufferRenderbufferEXT;
    long glGetFramebufferAttachmentParameterivEXT;
    long glGenerateMipmapEXT;
    // EXT_geometry_shader4
    long glProgramParameteriEXT;
    long glFramebufferTextureEXT;
    long glFramebufferTextureLayerEXT;
    long glFramebufferTextureFaceEXT;
    // EXT_gpu_program_parameters
    long glProgramEnvParameters4fvEXT;
    long glProgramLocalParameters4fvEXT;
    // EXT_gpu_shader4
    long glVertexAttribI1iEXT;
    long glVertexAttribI2iEXT;
    long glVertexAttribI3iEXT;
    long glVertexAttribI4iEXT;
    long glVertexAttribI1uiEXT;
    long glVertexAttribI2uiEXT;
    long glVertexAttribI3uiEXT;
    long glVertexAttribI4uiEXT;
    long glVertexAttribI1ivEXT;
    long glVertexAttribI2ivEXT;
    long glVertexAttribI3ivEXT;
    long glVertexAttribI4ivEXT;
    long glVertexAttribI1uivEXT;
    long glVertexAttribI2uivEXT;
    long glVertexAttribI3uivEXT;
    long glVertexAttribI4uivEXT;
    long glVertexAttribI4bvEXT;
    long glVertexAttribI4svEXT;
    long glVertexAttribI4ubvEXT;
    long glVertexAttribI4usvEXT;
    long glVertexAttribIPointerEXT;
    long glGetVertexAttribIivEXT;
    long glGetVertexAttribIuivEXT;
    long glUniform1uiEXT;
    long glUniform2uiEXT;
    long glUniform3uiEXT;
    long glUniform4uiEXT;
    long glUniform1uivEXT;
    long glUniform2uivEXT;
    long glUniform3uivEXT;
    long glUniform4uivEXT;
    long glGetUniformuivEXT;
    long glBindFragDataLocationEXT;
    long glGetFragDataLocationEXT;
    // EXT_multi_draw_arrays
    long glMultiDrawArraysEXT;
    // EXT_paletted_texture
    long glColorTableEXT;
    long glColorSubTableEXT;
    long glGetColorTableEXT;
    long glGetColorTableParameterivEXT;
    long glGetColorTableParameterfvEXT;
    // EXT_point_parameters
    long glPointParameterfEXT;
    long glPointParameterfvEXT;
    // EXT_provoking_vertex
    long glProvokingVertexEXT;
    // EXT_secondary_color
    long glSecondaryColor3bEXT;
    long glSecondaryColor3fEXT;
    long glSecondaryColor3dEXT;
    long glSecondaryColor3ubEXT;
    long glSecondaryColorPointerEXT;
    // EXT_separate_shader_objects
    long glUseShaderProgramEXT;
    long glActiveProgramEXT;
    long glCreateShaderProgramEXT;
    // EXT_shader_image_load_store
    long glBindImageTextureEXT;
    long glMemoryBarrierEXT;
    // EXT_stencil_clear_tag
    long glStencilClearTagEXT;
    // EXT_stencil_two_side
    long glActiveStencilFaceEXT;
    // EXT_texture_buffer_object
    long glTexBufferEXT;
    // EXT_texture_integer
    long glClearColorIiEXT;
    long glClearColorIuiEXT;
    long glTexParameterIivEXT;
    long glTexParameterIuivEXT;
    long glGetTexParameterIivEXT;
    long glGetTexParameterIuivEXT;
    // EXT_timer_query
    long glGetQueryObjecti64vEXT;
    long glGetQueryObjectui64vEXT;
    // EXT_transform_feedback
    long glBindBufferRangeEXT;
    long glBindBufferOffsetEXT;
    long glBindBufferBaseEXT;
    long glBeginTransformFeedbackEXT;
    long glEndTransformFeedbackEXT;
    long glTransformFeedbackVaryingsEXT;
    long glGetTransformFeedbackVaryingEXT;
    // EXT_vertex_attrib_64bit
    long glVertexAttribL1dEXT;
    long glVertexAttribL2dEXT;
    long glVertexAttribL3dEXT;
    long glVertexAttribL4dEXT;
    long glVertexAttribL1dvEXT;
    long glVertexAttribL2dvEXT;
    long glVertexAttribL3dvEXT;
    long glVertexAttribL4dvEXT;
    long glVertexAttribLPointerEXT;
    long glGetVertexAttribLdvEXT;
    // EXT_vertex_shader
    long glBeginVertexShaderEXT;
    long glEndVertexShaderEXT;
    long glBindVertexShaderEXT;
    long glGenVertexShadersEXT;
    long glDeleteVertexShaderEXT;
    long glShaderOp1EXT;
    long glShaderOp2EXT;
    long glShaderOp3EXT;
    long glSwizzleEXT;
    long glWriteMaskEXT;
    long glInsertComponentEXT;
    long glExtractComponentEXT;
    long glGenSymbolsEXT;
    long glSetInvariantEXT;
    long glSetLocalConstantEXT;
    long glVariantbvEXT;
    long glVariantsvEXT;
    long glVariantivEXT;
    long glVariantfvEXT;
    long glVariantdvEXT;
    long glVariantubvEXT;
    long glVariantusvEXT;
    long glVariantuivEXT;
    long glVariantPointerEXT;
    long glEnableVariantClientStateEXT;
    long glDisableVariantClientStateEXT;
    long glBindLightParameterEXT;
    long glBindMaterialParameterEXT;
    long glBindTexGenParameterEXT;
    long glBindTextureUnitParameterEXT;
    long glBindParameterEXT;
    long glIsVariantEnabledEXT;
    long glGetVariantBooleanvEXT;
    long glGetVariantIntegervEXT;
    long glGetVariantFloatvEXT;
    long glGetVariantPointervEXT;
    long glGetInvariantBooleanvEXT;
    long glGetInvariantIntegervEXT;
    long glGetInvariantFloatvEXT;
    long glGetLocalConstantBooleanvEXT;
    long glGetLocalConstantIntegervEXT;
    long glGetLocalConstantFloatvEXT;
    // EXT_vertex_weighting
    long glVertexWeightfEXT;
    long glVertexWeightPointerEXT;
    // GL11
    long glAccum;
    long glAlphaFunc;
    long glClearColor;
    long glClearAccum;
    long glClear;
    long glCallLists;
    long glCallList;
    long glBlendFunc;
    long glBitmap;
    long glBindTexture;
    long glPrioritizeTextures;
    long glAreTexturesResident;
    long glBegin;
    long glEnd;
    long glArrayElement;
    long glClearDepth;
    long glDeleteLists;
    long glDeleteTextures;
    long glCullFace;
    long glCopyTexSubImage2D;
    long glCopyTexSubImage1D;
    long glCopyTexImage2D;
    long glCopyTexImage1D;
    long glCopyPixels;
    long glColorPointer;
    long glColorMaterial;
    long glColorMask;
    long glColor3b;
    long glColor3f;
    long glColor3d;
    long glColor3ub;
    long glColor4b;
    long glColor4f;
    long glColor4d;
    long glColor4ub;
    long glClipPlane;
    long glClearStencil;
    long glEvalPoint1;
    long glEvalPoint2;
    long glEvalMesh1;
    long glEvalMesh2;
    long glEvalCoord1f;
    long glEvalCoord1d;
    long glEvalCoord2f;
    long glEvalCoord2d;
    long glEnableClientState;
    long glDisableClientState;
    long glEnable;
    long glDisable;
    long glEdgeFlagPointer;
    long glEdgeFlag;
    long glDrawPixels;
    long glDrawElements;
    long glDrawBuffer;
    long glDrawArrays;
    long glDepthRange;
    long glDepthMask;
    long glDepthFunc;
    long glFeedbackBuffer;
    long glGetPixelMapfv;
    long glGetPixelMapuiv;
    long glGetPixelMapusv;
    long glGetMaterialfv;
    long glGetMaterialiv;
    long glGetMapfv;
    long glGetMapdv;
    long glGetMapiv;
    long glGetLightfv;
    long glGetLightiv;
    long glGetError;
    long glGetClipPlane;
    long glGetBooleanv;
    long glGetDoublev;
    long glGetFloatv;
    long glGetIntegerv;
    long glGenTextures;
    long glGenLists;
    long glFrustum;
    long glFrontFace;
    long glFogf;
    long glFogi;
    long glFogfv;
    long glFogiv;
    long glFlush;
    long glFinish;
    long glGetPointerv;
    long glIsEnabled;
    long glInterleavedArrays;
    long glInitNames;
    long glHint;
    long glGetTexParameterfv;
    long glGetTexParameteriv;
    long glGetTexLevelParameterfv;
    long glGetTexLevelParameteriv;
    long glGetTexImage;
    long glGetTexGeniv;
    long glGetTexGenfv;
    long glGetTexGendv;
    long glGetTexEnviv;
    long glGetTexEnvfv;
    long glGetString;
    long glGetPolygonStipple;
    long glIsList;
    long glMaterialf;
    long glMateriali;
    long glMaterialfv;
    long glMaterialiv;
    long glMapGrid1f;
    long glMapGrid1d;
    long glMapGrid2f;
    long glMapGrid2d;
    long glMap2f;
    long glMap2d;
    long glMap1f;
    long glMap1d;
    long glLogicOp;
    long glLoadName;
    long glLoadMatrixf;
    long glLoadMatrixd;
    long glLoadIdentity;
    long glListBase;
    long glLineWidth;
    long glLineStipple;
    long glLightModelf;
    long glLightModeli;
    long glLightModelfv;
    long glLightModeliv;
    long glLightf;
    long glLighti;
    long glLightfv;
    long glLightiv;
    long glIsTexture;
    long glMatrixMode;
    long glPolygonStipple;
    long glPolygonOffset;
    long glPolygonMode;
    long glPointSize;
    long glPixelZoom;
    long glPixelTransferf;
    long glPixelTransferi;
    long glPixelStoref;
    long glPixelStorei;
    long glPixelMapfv;
    long glPixelMapuiv;
    long glPixelMapusv;
    long glPassThrough;
    long glOrtho;
    long glNormalPointer;
    long glNormal3b;
    long glNormal3f;
    long glNormal3d;
    long glNormal3i;
    long glNewList;
    long glEndList;
    long glMultMatrixf;
    long glMultMatrixd;
    long glShadeModel;
    long glSelectBuffer;
    long glScissor;
    long glScalef;
    long glScaled;
    long glRotatef;
    long glRotated;
    long glRenderMode;
    long glRectf;
    long glRectd;
    long glRecti;
    long glReadPixels;
    long glReadBuffer;
    long glRasterPos2f;
    long glRasterPos2d;
    long glRasterPos2i;
    long glRasterPos3f;
    long glRasterPos3d;
    long glRasterPos3i;
    long glRasterPos4f;
    long glRasterPos4d;
    long glRasterPos4i;
    long glPushName;
    long glPopName;
    long glPushMatrix;
    long glPopMatrix;
    long glPushClientAttrib;
    long glPopClientAttrib;
    long glPushAttrib;
    long glPopAttrib;
    long glStencilFunc;
    long glVertexPointer;
    long glVertex2f;
    long glVertex2d;
    long glVertex2i;
    long glVertex3f;
    long glVertex3d;
    long glVertex3i;
    long glVertex4f;
    long glVertex4d;
    long glVertex4i;
    long glTranslatef;
    long glTranslated;
    long glTexImage1D;
    long glTexImage2D;
    long glTexSubImage1D;
    long glTexSubImage2D;
    long glTexParameterf;
    long glTexParameteri;
    long glTexParameterfv;
    long glTexParameteriv;
    long glTexGenf;
    long glTexGend;
    long glTexGenfv;
    long glTexGendv;
    long glTexGeni;
    long glTexGeniv;
    long glTexEnvf;
    long glTexEnvi;
    long glTexEnvfv;
    long glTexEnviv;
    long glTexCoordPointer;
    long glTexCoord1f;
    long glTexCoord1d;
    long glTexCoord2f;
    long glTexCoord2d;
    long glTexCoord3f;
    long glTexCoord3d;
    long glTexCoord4f;
    long glTexCoord4d;
    long glStencilOp;
    long glStencilMask;
    long glViewport;
    // GL12
    long glDrawRangeElements;
    long glTexImage3D;
    long glTexSubImage3D;
    long glCopyTexSubImage3D;
    // GL13
    long glActiveTexture;
    long glClientActiveTexture;
    long glCompressedTexImage1D;
    long glCompressedTexImage2D;
    long glCompressedTexImage3D;
    long glCompressedTexSubImage1D;
    long glCompressedTexSubImage2D;
    long glCompressedTexSubImage3D;
    long glGetCompressedTexImage;
    long glMultiTexCoord1f;
    long glMultiTexCoord1d;
    long glMultiTexCoord2f;
    long glMultiTexCoord2d;
    long glMultiTexCoord3f;
    long glMultiTexCoord3d;
    long glMultiTexCoord4f;
    long glMultiTexCoord4d;
    long glLoadTransposeMatrixf;
    long glLoadTransposeMatrixd;
    long glMultTransposeMatrixf;
    long glMultTransposeMatrixd;
    long glSampleCoverage;
    // GL14
    long glBlendEquation;
    long glBlendColor;
    long glFogCoordf;
    long glFogCoordd;
    long glFogCoordPointer;
    long glMultiDrawArrays;
    long glPointParameteri;
    long glPointParameterf;
    long glPointParameteriv;
    long glPointParameterfv;
    long glSecondaryColor3b;
    long glSecondaryColor3f;
    long glSecondaryColor3d;
    long glSecondaryColor3ub;
    long glSecondaryColorPointer;
    long glBlendFuncSeparate;
    long glWindowPos2f;
    long glWindowPos2d;
    long glWindowPos2i;
    long glWindowPos3f;
    long glWindowPos3d;
    long glWindowPos3i;
    // GL15
    long glBindBuffer;
    long glDeleteBuffers;
    long glGenBuffers;
    long glIsBuffer;
    long glBufferData;
    long glBufferSubData;
    long glGetBufferSubData;
    long glMapBuffer;
    long glUnmapBuffer;
    long glGetBufferParameteriv;
    long glGetBufferPointerv;
    long glGenQueries;
    long glDeleteQueries;
    long glIsQuery;
    long glBeginQuery;
    long glEndQuery;
    long glGetQueryiv;
    long glGetQueryObjectiv;
    long glGetQueryObjectuiv;
    // GL20
    long glShaderSource;
    long glCreateShader;
    long glIsShader;
    long glCompileShader;
    long glDeleteShader;
    long glCreateProgram;
    long glIsProgram;
    long glAttachShader;
    long glDetachShader;
    long glLinkProgram;
    long glUseProgram;
    long glValidateProgram;
    long glDeleteProgram;
    long glUniform1f;
    long glUniform2f;
    long glUniform3f;
    long glUniform4f;
    long glUniform1i;
    long glUniform2i;
    long glUniform3i;
    long glUniform4i;
    long glUniform1fv;
    long glUniform2fv;
    long glUniform3fv;
    long glUniform4fv;
    long glUniform1iv;
    long glUniform2iv;
    long glUniform3iv;
    long glUniform4iv;
    long glUniformMatrix2fv;
    long glUniformMatrix3fv;
    long glUniformMatrix4fv;
    long glGetShaderiv;
    long glGetProgramiv;
    long glGetShaderInfoLog;
    long glGetProgramInfoLog;
    long glGetAttachedShaders;
    long glGetUniformLocation;
    long glGetActiveUniform;
    long glGetUniformfv;
    long glGetUniformiv;
    long glGetShaderSource;
    long glVertexAttrib1s;
    long glVertexAttrib1f;
    long glVertexAttrib1d;
    long glVertexAttrib2s;
    long glVertexAttrib2f;
    long glVertexAttrib2d;
    long glVertexAttrib3s;
    long glVertexAttrib3f;
    long glVertexAttrib3d;
    long glVertexAttrib4s;
    long glVertexAttrib4f;
    long glVertexAttrib4d;
    long glVertexAttrib4Nub;
    long glVertexAttribPointer;
    long glEnableVertexAttribArray;
    long glDisableVertexAttribArray;
    long glGetVertexAttribfv;
    long glGetVertexAttribdv;
    long glGetVertexAttribiv;
    long glGetVertexAttribPointerv;
    long glBindAttribLocation;
    long glGetActiveAttrib;
    long glGetAttribLocation;
    long glDrawBuffers;
    long glStencilOpSeparate;
    long glStencilFuncSeparate;
    long glStencilMaskSeparate;
    long glBlendEquationSeparate;
    // GL21
    long glUniformMatrix2x3fv;
    long glUniformMatrix3x2fv;
    long glUniformMatrix2x4fv;
    long glUniformMatrix4x2fv;
    long glUniformMatrix3x4fv;
    long glUniformMatrix4x3fv;
    // GL30
    long glGetStringi;
    long glClearBufferfv;
    long glClearBufferiv;
    long glClearBufferuiv;
    long glClearBufferfi;
    long glVertexAttribI1i;
    long glVertexAttribI2i;
    long glVertexAttribI3i;
    long glVertexAttribI4i;
    long glVertexAttribI1ui;
    long glVertexAttribI2ui;
    long glVertexAttribI3ui;
    long glVertexAttribI4ui;
    long glVertexAttribI1iv;
    long glVertexAttribI2iv;
    long glVertexAttribI3iv;
    long glVertexAttribI4iv;
    long glVertexAttribI1uiv;
    long glVertexAttribI2uiv;
    long glVertexAttribI3uiv;
    long glVertexAttribI4uiv;
    long glVertexAttribI4bv;
    long glVertexAttribI4sv;
    long glVertexAttribI4ubv;
    long glVertexAttribI4usv;
    long glVertexAttribIPointer;
    long glGetVertexAttribIiv;
    long glGetVertexAttribIuiv;
    long glUniform1ui;
    long glUniform2ui;
    long glUniform3ui;
    long glUniform4ui;
    long glUniform1uiv;
    long glUniform2uiv;
    long glUniform3uiv;
    long glUniform4uiv;
    long glGetUniformuiv;
    long glBindFragDataLocation;
    long glGetFragDataLocation;
    long glBeginConditionalRender;
    long glEndConditionalRender;
    long glMapBufferRange;
    long glFlushMappedBufferRange;
    long glClampColor;
    long glIsRenderbuffer;
    long glBindRenderbuffer;
    long glDeleteRenderbuffers;
    long glGenRenderbuffers;
    long glRenderbufferStorage;
    long glGetRenderbufferParameteriv;
    long glIsFramebuffer;
    long glBindFramebuffer;
    long glDeleteFramebuffers;
    long glGenFramebuffers;
    long glCheckFramebufferStatus;
    long glFramebufferTexture1D;
    long glFramebufferTexture2D;
    long glFramebufferTexture3D;
    long glFramebufferRenderbuffer;
    long glGetFramebufferAttachmentParameteriv;
    long glGenerateMipmap;
    long glRenderbufferStorageMultisample;
    long glBlitFramebuffer;
    long glTexParameterIiv;
    long glTexParameterIuiv;
    long glGetTexParameterIiv;
    long glGetTexParameterIuiv;
    long glFramebufferTextureLayer;
    long glColorMaski;
    long glGetBooleani_v;
    long glGetIntegeri_v;
    long glEnablei;
    long glDisablei;
    long glIsEnabledi;
    long glBindBufferRange;
    long glBindBufferBase;
    long glBeginTransformFeedback;
    long glEndTransformFeedback;
    long glTransformFeedbackVaryings;
    long glGetTransformFeedbackVarying;
    long glBindVertexArray;
    long glDeleteVertexArrays;
    long glGenVertexArrays;
    long glIsVertexArray;
    // GL31
    long glDrawArraysInstanced;
    long glDrawElementsInstanced;
    long glCopyBufferSubData;
    long glPrimitiveRestartIndex;
    long glTexBuffer;
    long glGetUniformIndices;
    long glGetActiveUniformsiv;
    long glGetActiveUniformName;
    long glGetUniformBlockIndex;
    long glGetActiveUniformBlockiv;
    long glGetActiveUniformBlockName;
    long glUniformBlockBinding;
    // GL32
    long glGetBufferParameteri64v;
    long glDrawElementsBaseVertex;
    long glDrawRangeElementsBaseVertex;
    long glDrawElementsInstancedBaseVertex;
    long glProvokingVertex;
    long glTexImage2DMultisample;
    long glTexImage3DMultisample;
    long glGetMultisamplefv;
    long glSampleMaski;
    long glFramebufferTexture;
    long glFenceSync;
    long glIsSync;
    long glDeleteSync;
    long glClientWaitSync;
    long glWaitSync;
    long glGetInteger64v;
    long glGetInteger64i_v;
    long glGetSynciv;
    // GL33
    long glBindFragDataLocationIndexed;
    long glGetFragDataIndex;
    long glGenSamplers;
    long glDeleteSamplers;
    long glIsSampler;
    long glBindSampler;
    long glSamplerParameteri;
    long glSamplerParameterf;
    long glSamplerParameteriv;
    long glSamplerParameterfv;
    long glSamplerParameterIiv;
    long glSamplerParameterIuiv;
    long glGetSamplerParameteriv;
    long glGetSamplerParameterfv;
    long glGetSamplerParameterIiv;
    long glGetSamplerParameterIuiv;
    long glQueryCounter;
    long glGetQueryObjecti64v;
    long glGetQueryObjectui64v;
    long glVertexAttribDivisor;
    long glVertexP2ui;
    long glVertexP3ui;
    long glVertexP4ui;
    long glVertexP2uiv;
    long glVertexP3uiv;
    long glVertexP4uiv;
    long glTexCoordP1ui;
    long glTexCoordP2ui;
    long glTexCoordP3ui;
    long glTexCoordP4ui;
    long glTexCoordP1uiv;
    long glTexCoordP2uiv;
    long glTexCoordP3uiv;
    long glTexCoordP4uiv;
    long glMultiTexCoordP1ui;
    long glMultiTexCoordP2ui;
    long glMultiTexCoordP3ui;
    long glMultiTexCoordP4ui;
    long glMultiTexCoordP1uiv;
    long glMultiTexCoordP2uiv;
    long glMultiTexCoordP3uiv;
    long glMultiTexCoordP4uiv;
    long glNormalP3ui;
    long glNormalP3uiv;
    long glColorP3ui;
    long glColorP4ui;
    long glColorP3uiv;
    long glColorP4uiv;
    long glSecondaryColorP3ui;
    long glSecondaryColorP3uiv;
    long glVertexAttribP1ui;
    long glVertexAttribP2ui;
    long glVertexAttribP3ui;
    long glVertexAttribP4ui;
    long glVertexAttribP1uiv;
    long glVertexAttribP2uiv;
    long glVertexAttribP3uiv;
    long glVertexAttribP4uiv;
    // GL40
    long glBlendEquationi;
    long glBlendEquationSeparatei;
    long glBlendFunci;
    long glBlendFuncSeparatei;
    long glDrawArraysIndirect;
    long glDrawElementsIndirect;
    long glUniform1d;
    long glUniform2d;
    long glUniform3d;
    long glUniform4d;
    long glUniform1dv;
    long glUniform2dv;
    long glUniform3dv;
    long glUniform4dv;
    long glUniformMatrix2dv;
    long glUniformMatrix3dv;
    long glUniformMatrix4dv;
    long glUniformMatrix2x3dv;
    long glUniformMatrix2x4dv;
    long glUniformMatrix3x2dv;
    long glUniformMatrix3x4dv;
    long glUniformMatrix4x2dv;
    long glUniformMatrix4x3dv;
    long glGetUniformdv;
    long glMinSampleShading;
    long glGetSubroutineUniformLocation;
    long glGetSubroutineIndex;
    long glGetActiveSubroutineUniformiv;
    long glGetActiveSubroutineUniformName;
    long glGetActiveSubroutineName;
    long glUniformSubroutinesuiv;
    long glGetUniformSubroutineuiv;
    long glGetProgramStageiv;
    long glPatchParameteri;
    long glPatchParameterfv;
    long glBindTransformFeedback;
    long glDeleteTransformFeedbacks;
    long glGenTransformFeedbacks;
    long glIsTransformFeedback;
    long glPauseTransformFeedback;
    long glResumeTransformFeedback;
    long glDrawTransformFeedback;
    long glDrawTransformFeedbackStream;
    long glBeginQueryIndexed;
    long glEndQueryIndexed;
    long glGetQueryIndexediv;
    // GL41
    long glReleaseShaderCompiler;
    long glShaderBinary;
    long glGetShaderPrecisionFormat;
    long glDepthRangef;
    long glClearDepthf;
    long glGetProgramBinary;
    long glProgramBinary;
    long glProgramParameteri;
    long glUseProgramStages;
    long glActiveShaderProgram;
    long glCreateShaderProgramv;
    long glBindProgramPipeline;
    long glDeleteProgramPipelines;
    long glGenProgramPipelines;
    long glIsProgramPipeline;
    long glGetProgramPipelineiv;
    long glProgramUniform1i;
    long glProgramUniform2i;
    long glProgramUniform3i;
    long glProgramUniform4i;
    long glProgramUniform1f;
    long glProgramUniform2f;
    long glProgramUniform3f;
    long glProgramUniform4f;
    long glProgramUniform1d;
    long glProgramUniform2d;
    long glProgramUniform3d;
    long glProgramUniform4d;
    long glProgramUniform1iv;
    long glProgramUniform2iv;
    long glProgramUniform3iv;
    long glProgramUniform4iv;
    long glProgramUniform1fv;
    long glProgramUniform2fv;
    long glProgramUniform3fv;
    long glProgramUniform4fv;
    long glProgramUniform1dv;
    long glProgramUniform2dv;
    long glProgramUniform3dv;
    long glProgramUniform4dv;
    long glProgramUniform1ui;
    long glProgramUniform2ui;
    long glProgramUniform3ui;
    long glProgramUniform4ui;
    long glProgramUniform1uiv;
    long glProgramUniform2uiv;
    long glProgramUniform3uiv;
    long glProgramUniform4uiv;
    long glProgramUniformMatrix2fv;
    long glProgramUniformMatrix3fv;
    long glProgramUniformMatrix4fv;
    long glProgramUniformMatrix2dv;
    long glProgramUniformMatrix3dv;
    long glProgramUniformMatrix4dv;
    long glProgramUniformMatrix2x3fv;
    long glProgramUniformMatrix3x2fv;
    long glProgramUniformMatrix2x4fv;
    long glProgramUniformMatrix4x2fv;
    long glProgramUniformMatrix3x4fv;
    long glProgramUniformMatrix4x3fv;
    long glProgramUniformMatrix2x3dv;
    long glProgramUniformMatrix3x2dv;
    long glProgramUniformMatrix2x4dv;
    long glProgramUniformMatrix4x2dv;
    long glProgramUniformMatrix3x4dv;
    long glProgramUniformMatrix4x3dv;
    long glValidateProgramPipeline;
    long glGetProgramPipelineInfoLog;
    long glVertexAttribL1d;
    long glVertexAttribL2d;
    long glVertexAttribL3d;
    long glVertexAttribL4d;
    long glVertexAttribL1dv;
    long glVertexAttribL2dv;
    long glVertexAttribL3dv;
    long glVertexAttribL4dv;
    long glVertexAttribLPointer;
    long glGetVertexAttribLdv;
    long glViewportArrayv;
    long glViewportIndexedf;
    long glViewportIndexedfv;
    long glScissorArrayv;
    long glScissorIndexed;
    long glScissorIndexedv;
    long glDepthRangeArrayv;
    long glDepthRangeIndexed;
    long glGetFloati_v;
    long glGetDoublei_v;
    // GL42
    long glGetActiveAtomicCounterBufferiv;
    long glTexStorage1D;
    long glTexStorage2D;
    long glTexStorage3D;
    long glDrawTransformFeedbackInstanced;
    long glDrawTransformFeedbackStreamInstanced;
    long glDrawArraysInstancedBaseInstance;
    long glDrawElementsInstancedBaseInstance;
    long glDrawElementsInstancedBaseVertexBaseInstance;
    long glBindImageTexture;
    long glMemoryBarrier;
    long glGetInternalformativ;
    // GL43
    long glClearBufferData;
    long glClearBufferSubData;
    long glDispatchCompute;
    long glDispatchComputeIndirect;
    long glCopyImageSubData;
    long glDebugMessageControl;
    long glDebugMessageInsert;
    long glDebugMessageCallback;
    long glGetDebugMessageLog;
    long glPushDebugGroup;
    long glPopDebugGroup;
    long glObjectLabel;
    long glGetObjectLabel;
    long glObjectPtrLabel;
    long glGetObjectPtrLabel;
    long glFramebufferParameteri;
    long glGetFramebufferParameteriv;
    long glGetInternalformati64v;
    long glInvalidateTexSubImage;
    long glInvalidateTexImage;
    long glInvalidateBufferSubData;
    long glInvalidateBufferData;
    long glInvalidateFramebuffer;
    long glInvalidateSubFramebuffer;
    long glMultiDrawArraysIndirect;
    long glMultiDrawElementsIndirect;
    long glGetProgramInterfaceiv;
    long glGetProgramResourceIndex;
    long glGetProgramResourceName;
    long glGetProgramResourceiv;
    long glGetProgramResourceLocation;
    long glGetProgramResourceLocationIndex;
    long glShaderStorageBlockBinding;
    long glTexBufferRange;
    long glTexStorage2DMultisample;
    long glTexStorage3DMultisample;
    long glTextureView;
    long glBindVertexBuffer;
    long glVertexAttribFormat;
    long glVertexAttribIFormat;
    long glVertexAttribLFormat;
    long glVertexAttribBinding;
    long glVertexBindingDivisor;
    // GL44
    long glBufferStorage;
    long glClearTexImage;
    long glClearTexSubImage;
    long glBindBuffersBase;
    long glBindBuffersRange;
    long glBindTextures;
    long glBindSamplers;
    long glBindImageTextures;
    long glBindVertexBuffers;
    // GL45
    long glClipControl;
    long glCreateTransformFeedbacks;
    long glTransformFeedbackBufferBase;
    long glTransformFeedbackBufferRange;
    long glGetTransformFeedbackiv;
    long glGetTransformFeedbacki_v;
    long glGetTransformFeedbacki64_v;
    long glCreateBuffers;
    long glNamedBufferStorage;
    long glNamedBufferData;
    long glNamedBufferSubData;
    long glCopyNamedBufferSubData;
    long glClearNamedBufferData;
    long glClearNamedBufferSubData;
    long glMapNamedBuffer;
    long glMapNamedBufferRange;
    long glUnmapNamedBuffer;
    long glFlushMappedNamedBufferRange;
    long glGetNamedBufferParameteriv;
    long glGetNamedBufferParameteri64v;
    long glGetNamedBufferPointerv;
    long glGetNamedBufferSubData;
    long glCreateFramebuffers;
    long glNamedFramebufferRenderbuffer;
    long glNamedFramebufferParameteri;
    long glNamedFramebufferTexture;
    long glNamedFramebufferTextureLayer;
    long glNamedFramebufferDrawBuffer;
    long glNamedFramebufferDrawBuffers;
    long glNamedFramebufferReadBuffer;
    long glInvalidateNamedFramebufferData;
    long glInvalidateNamedFramebufferSubData;
    long glClearNamedFramebufferiv;
    long glClearNamedFramebufferuiv;
    long glClearNamedFramebufferfv;
    long glClearNamedFramebufferfi;
    long glBlitNamedFramebuffer;
    long glCheckNamedFramebufferStatus;
    long glGetNamedFramebufferParameteriv;
    long glGetNamedFramebufferAttachmentParameteriv;
    long glCreateRenderbuffers;
    long glNamedRenderbufferStorage;
    long glNamedRenderbufferStorageMultisample;
    long glGetNamedRenderbufferParameteriv;
    long glCreateTextures;
    long glTextureBuffer;
    long glTextureBufferRange;
    long glTextureStorage1D;
    long glTextureStorage2D;
    long glTextureStorage3D;
    long glTextureStorage2DMultisample;
    long glTextureStorage3DMultisample;
    long glTextureSubImage1D;
    long glTextureSubImage2D;
    long glTextureSubImage3D;
    long glCompressedTextureSubImage1D;
    long glCompressedTextureSubImage2D;
    long glCompressedTextureSubImage3D;
    long glCopyTextureSubImage1D;
    long glCopyTextureSubImage2D;
    long glCopyTextureSubImage3D;
    long glTextureParameterf;
    long glTextureParameterfv;
    long glTextureParameteri;
    long glTextureParameterIiv;
    long glTextureParameterIuiv;
    long glTextureParameteriv;
    long glGenerateTextureMipmap;
    long glBindTextureUnit;
    long glGetTextureImage;
    long glGetCompressedTextureImage;
    long glGetTextureLevelParameterfv;
    long glGetTextureLevelParameteriv;
    long glGetTextureParameterfv;
    long glGetTextureParameterIiv;
    long glGetTextureParameterIuiv;
    long glGetTextureParameteriv;
    long glCreateVertexArrays;
    long glDisableVertexArrayAttrib;
    long glEnableVertexArrayAttrib;
    long glVertexArrayElementBuffer;
    long glVertexArrayVertexBuffer;
    long glVertexArrayVertexBuffers;
    long glVertexArrayAttribFormat;
    long glVertexArrayAttribIFormat;
    long glVertexArrayAttribLFormat;
    long glVertexArrayAttribBinding;
    long glVertexArrayBindingDivisor;
    long glGetVertexArrayiv;
    long glGetVertexArrayIndexediv;
    long glGetVertexArrayIndexed64iv;
    long glCreateSamplers;
    long glCreateProgramPipelines;
    long glCreateQueries;
    long glMemoryBarrierByRegion;
    long glGetTextureSubImage;
    long glGetCompressedTextureSubImage;
    long glTextureBarrier;
    long glGetGraphicsResetStatus;
    long glReadnPixels;
    long glGetnUniformfv;
    long glGetnUniformiv;
    long glGetnUniformuiv;
    // GREMEDY_frame_terminator
    long glFrameTerminatorGREMEDY;
    // GREMEDY_string_marker
    long glStringMarkerGREMEDY;
    // INTEL_map_texture
    long glMapTexture2DINTEL;
    long glUnmapTexture2DINTEL;
    long glSyncTextureINTEL;
    // NV_bindless_multi_draw_indirect
    long glMultiDrawArraysIndirectBindlessNV;
    long glMultiDrawElementsIndirectBindlessNV;
    // NV_bindless_texture
    long glGetTextureHandleNV;
    long glGetTextureSamplerHandleNV;
    long glMakeTextureHandleResidentNV;
    long glMakeTextureHandleNonResidentNV;
    long glGetImageHandleNV;
    long glMakeImageHandleResidentNV;
    long glMakeImageHandleNonResidentNV;
    long glUniformHandleui64NV;
    long glUniformHandleui64vNV;
    long glProgramUniformHandleui64NV;
    long glProgramUniformHandleui64vNV;
    long glIsTextureHandleResidentNV;
    long glIsImageHandleResidentNV;
    // NV_blend_equation_advanced
    long glBlendParameteriNV;
    long glBlendBarrierNV;
    // NV_conditional_render
    long glBeginConditionalRenderNV;
    long glEndConditionalRenderNV;
    // NV_copy_image
    long glCopyImageSubDataNV;
    // NV_depth_buffer_float
    long glDepthRangedNV;
    long glClearDepthdNV;
    long glDepthBoundsdNV;
    // NV_draw_texture
    long glDrawTextureNV;
    // NV_evaluators
    long glGetMapControlPointsNV;
    long glMapControlPointsNV;
    long glMapParameterfvNV;
    long glMapParameterivNV;
    long glGetMapParameterfvNV;
    long glGetMapParameterivNV;
    long glGetMapAttribParameterfvNV;
    long glGetMapAttribParameterivNV;
    long glEvalMapsNV;
    // NV_explicit_multisample
    long glGetMultisamplefvNV;
    long glSampleMaskIndexedNV;
    long glTexRenderbufferNV;
    // NV_fence
    long glGenFencesNV;
    long glDeleteFencesNV;
    long glSetFenceNV;
    long glTestFenceNV;
    long glFinishFenceNV;
    long glIsFenceNV;
    long glGetFenceivNV;
    // NV_fragment_program
    long glProgramNamedParameter4fNV;
    long glProgramNamedParameter4dNV;
    long glGetProgramNamedParameterfvNV;
    long glGetProgramNamedParameterdvNV;
    // NV_framebuffer_multisample_coverage
    long glRenderbufferStorageMultisampleCoverageNV;
    // NV_geometry_program4
    long glProgramVertexLimitNV;
    // NV_gpu_program4
    long glProgramLocalParameterI4iNV;
    long glProgramLocalParameterI4ivNV;
    long glProgramLocalParametersI4ivNV;
    long glProgramLocalParameterI4uiNV;
    long glProgramLocalParameterI4uivNV;
    long glProgramLocalParametersI4uivNV;
    long glProgramEnvParameterI4iNV;
    long glProgramEnvParameterI4ivNV;
    long glProgramEnvParametersI4ivNV;
    long glProgramEnvParameterI4uiNV;
    long glProgramEnvParameterI4uivNV;
    long glProgramEnvParametersI4uivNV;
    long glGetProgramLocalParameterIivNV;
    long glGetProgramLocalParameterIuivNV;
    long glGetProgramEnvParameterIivNV;
    long glGetProgramEnvParameterIuivNV;
    // NV_gpu_shader5
    long glUniform1i64NV;
    long glUniform2i64NV;
    long glUniform3i64NV;
    long glUniform4i64NV;
    long glUniform1i64vNV;
    long glUniform2i64vNV;
    long glUniform3i64vNV;
    long glUniform4i64vNV;
    long glUniform1ui64NV;
    long glUniform2ui64NV;
    long glUniform3ui64NV;
    long glUniform4ui64NV;
    long glUniform1ui64vNV;
    long glUniform2ui64vNV;
    long glUniform3ui64vNV;
    long glUniform4ui64vNV;
    long glGetUniformi64vNV;
    long glGetUniformui64vNV;
    long glProgramUniform1i64NV;
    long glProgramUniform2i64NV;
    long glProgramUniform3i64NV;
    long glProgramUniform4i64NV;
    long glProgramUniform1i64vNV;
    long glProgramUniform2i64vNV;
    long glProgramUniform3i64vNV;
    long glProgramUniform4i64vNV;
    long glProgramUniform1ui64NV;
    long glProgramUniform2ui64NV;
    long glProgramUniform3ui64NV;
    long glProgramUniform4ui64NV;
    long glProgramUniform1ui64vNV;
    long glProgramUniform2ui64vNV;
    long glProgramUniform3ui64vNV;
    long glProgramUniform4ui64vNV;
    // NV_half_float
    long glVertex2hNV;
    long glVertex3hNV;
    long glVertex4hNV;
    long glNormal3hNV;
    long glColor3hNV;
    long glColor4hNV;
    long glTexCoord1hNV;
    long glTexCoord2hNV;
    long glTexCoord3hNV;
    long glTexCoord4hNV;
    long glMultiTexCoord1hNV;
    long glMultiTexCoord2hNV;
    long glMultiTexCoord3hNV;
    long glMultiTexCoord4hNV;
    long glFogCoordhNV;
    long glSecondaryColor3hNV;
    long glVertexWeighthNV;
    long glVertexAttrib1hNV;
    long glVertexAttrib2hNV;
    long glVertexAttrib3hNV;
    long glVertexAttrib4hNV;
    long glVertexAttribs1hvNV;
    long glVertexAttribs2hvNV;
    long glVertexAttribs3hvNV;
    long glVertexAttribs4hvNV;
    // NV_occlusion_query
    long glGenOcclusionQueriesNV;
    long glDeleteOcclusionQueriesNV;
    long glIsOcclusionQueryNV;
    long glBeginOcclusionQueryNV;
    long glEndOcclusionQueryNV;
    long glGetOcclusionQueryuivNV;
    long glGetOcclusionQueryivNV;
    // NV_parameter_buffer_object
    long glProgramBufferParametersfvNV;
    long glProgramBufferParametersIivNV;
    long glProgramBufferParametersIuivNV;
    // NV_path_rendering
    long glPathCommandsNV;
    long glPathCoordsNV;
    long glPathSubCommandsNV;
    long glPathSubCoordsNV;
    long glPathStringNV;
    long glPathGlyphsNV;
    long glPathGlyphRangeNV;
    long glWeightPathsNV;
    long glCopyPathNV;
    long glInterpolatePathsNV;
    long glTransformPathNV;
    long glPathParameterivNV;
    long glPathParameteriNV;
    long glPathParameterfvNV;
    long glPathParameterfNV;
    long glPathDashArrayNV;
    long glGenPathsNV;
    long glDeletePathsNV;
    long glIsPathNV;
    long glPathStencilFuncNV;
    long glPathStencilDepthOffsetNV;
    long glStencilFillPathNV;
    long glStencilStrokePathNV;
    long glStencilFillPathInstancedNV;
    long glStencilStrokePathInstancedNV;
    long glPathCoverDepthFuncNV;
    long glPathColorGenNV;
    long glPathTexGenNV;
    long glPathFogGenNV;
    long glCoverFillPathNV;
    long glCoverStrokePathNV;
    long glCoverFillPathInstancedNV;
    long glCoverStrokePathInstancedNV;
    long glGetPathParameterivNV;
    long glGetPathParameterfvNV;
    long glGetPathCommandsNV;
    long glGetPathCoordsNV;
    long glGetPathDashArrayNV;
    long glGetPathMetricsNV;
    long glGetPathMetricRangeNV;
    long glGetPathSpacingNV;
    long glGetPathColorGenivNV;
    long glGetPathColorGenfvNV;
    long glGetPathTexGenivNV;
    long glGetPathTexGenfvNV;
    long glIsPointInFillPathNV;
    long glIsPointInStrokePathNV;
    long glGetPathLengthNV;
    long glPointAlongPathNV;
    // NV_pixel_data_range
    long glPixelDataRangeNV;
    long glFlushPixelDataRangeNV;
    // NV_point_sprite
    long glPointParameteriNV;
    long glPointParameterivNV;
    // NV_present_video
    long glPresentFrameKeyedNV;
    long glPresentFrameDualFillNV;
    long glGetVideoivNV;
    long glGetVideouivNV;
    long glGetVideoi64vNV;
    long glGetVideoui64vNV;
    // NV_primitive_restart
    long glPrimitiveRestartNV;
    long glPrimitiveRestartIndexNV;
    // NV_program
    long glLoadProgramNV;
    long glBindProgramNV;
    long glDeleteProgramsNV;
    long glGenProgramsNV;
    long glGetProgramivNV;
    long glGetProgramStringNV;
    long glIsProgramNV;
    long glAreProgramsResidentNV;
    long glRequestResidentProgramsNV;
    // NV_register_combiners
    long glCombinerParameterfNV;
    long glCombinerParameterfvNV;
    long glCombinerParameteriNV;
    long glCombinerParameterivNV;
    long glCombinerInputNV;
    long glCombinerOutputNV;
    long glFinalCombinerInputNV;
    long glGetCombinerInputParameterfvNV;
    long glGetCombinerInputParameterivNV;
    long glGetCombinerOutputParameterfvNV;
    long glGetCombinerOutputParameterivNV;
    long glGetFinalCombinerInputParameterfvNV;
    long glGetFinalCombinerInputParameterivNV;
    // NV_register_combiners2
    long glCombinerStageParameterfvNV;
    long glGetCombinerStageParameterfvNV;
    // NV_shader_buffer_load
    long glMakeBufferResidentNV;
    long glMakeBufferNonResidentNV;
    long glIsBufferResidentNV;
    long glMakeNamedBufferResidentNV;
    long glMakeNamedBufferNonResidentNV;
    long glIsNamedBufferResidentNV;
    long glGetBufferParameterui64vNV;
    long glGetNamedBufferParameterui64vNV;
    long glGetIntegerui64vNV;
    long glUniformui64NV;
    long glUniformui64vNV;
    long glProgramUniformui64NV;
    long glProgramUniformui64vNV;
    // NV_texture_barrier
    long glTextureBarrierNV;
    // NV_texture_multisample
    long glTexImage2DMultisampleCoverageNV;
    long glTexImage3DMultisampleCoverageNV;
    long glTextureImage2DMultisampleNV;
    long glTextureImage3DMultisampleNV;
    long glTextureImage2DMultisampleCoverageNV;
    long glTextureImage3DMultisampleCoverageNV;
    // NV_transform_feedback
    long glBindBufferRangeNV;
    long glBindBufferOffsetNV;
    long glBindBufferBaseNV;
    long glTransformFeedbackAttribsNV;
    long glTransformFeedbackVaryingsNV;
    long glBeginTransformFeedbackNV;
    long glEndTransformFeedbackNV;
    long glGetVaryingLocationNV;
    long glGetActiveVaryingNV;
    long glActiveVaryingNV;
    long glGetTransformFeedbackVaryingNV;
    // NV_transform_feedback2
    long glBindTransformFeedbackNV;
    long glDeleteTransformFeedbacksNV;
    long glGenTransformFeedbacksNV;
    long glIsTransformFeedbackNV;
    long glPauseTransformFeedbackNV;
    long glResumeTransformFeedbackNV;
    long glDrawTransformFeedbackNV;
    // NV_vertex_array_range
    long glVertexArrayRangeNV;
    long glFlushVertexArrayRangeNV;
    long glAllocateMemoryNV;
    long glFreeMemoryNV;
    // NV_vertex_attrib_integer_64bit
    long glVertexAttribL1i64NV;
    long glVertexAttribL2i64NV;
    long glVertexAttribL3i64NV;
    long glVertexAttribL4i64NV;
    long glVertexAttribL1i64vNV;
    long glVertexAttribL2i64vNV;
    long glVertexAttribL3i64vNV;
    long glVertexAttribL4i64vNV;
    long glVertexAttribL1ui64NV;
    long glVertexAttribL2ui64NV;
    long glVertexAttribL3ui64NV;
    long glVertexAttribL4ui64NV;
    long glVertexAttribL1ui64vNV;
    long glVertexAttribL2ui64vNV;
    long glVertexAttribL3ui64vNV;
    long glVertexAttribL4ui64vNV;
    long glGetVertexAttribLi64vNV;
    long glGetVertexAttribLui64vNV;
    long glVertexAttribLFormatNV;
    // NV_vertex_buffer_unified_memory
    long glBufferAddressRangeNV;
    long glVertexFormatNV;
    long glNormalFormatNV;
    long glColorFormatNV;
    long glIndexFormatNV;
    long glTexCoordFormatNV;
    long glEdgeFlagFormatNV;
    long glSecondaryColorFormatNV;
    long glFogCoordFormatNV;
    long glVertexAttribFormatNV;
    long glVertexAttribIFormatNV;
    long glGetIntegerui64i_vNV;
    // NV_vertex_program
    long glExecuteProgramNV;
    long glGetProgramParameterfvNV;
    long glGetProgramParameterdvNV;
    long glGetTrackMatrixivNV;
    long glGetVertexAttribfvNV;
    long glGetVertexAttribdvNV;
    long glGetVertexAttribivNV;
    long glGetVertexAttribPointervNV;
    long glProgramParameter4fNV;
    long glProgramParameter4dNV;
    long glProgramParameters4fvNV;
    long glProgramParameters4dvNV;
    long glTrackMatrixNV;
    long glVertexAttribPointerNV;
    long glVertexAttrib1sNV;
    long glVertexAttrib1fNV;
    long glVertexAttrib1dNV;
    long glVertexAttrib2sNV;
    long glVertexAttrib2fNV;
    long glVertexAttrib2dNV;
    long glVertexAttrib3sNV;
    long glVertexAttrib3fNV;
    long glVertexAttrib3dNV;
    long glVertexAttrib4sNV;
    long glVertexAttrib4fNV;
    long glVertexAttrib4dNV;
    long glVertexAttrib4ubNV;
    long glVertexAttribs1svNV;
    long glVertexAttribs1fvNV;
    long glVertexAttribs1dvNV;
    long glVertexAttribs2svNV;
    long glVertexAttribs2fvNV;
    long glVertexAttribs2dvNV;
    long glVertexAttribs3svNV;
    long glVertexAttribs3fvNV;
    long glVertexAttribs3dvNV;
    long glVertexAttribs4svNV;
    long glVertexAttribs4fvNV;
    long glVertexAttribs4dvNV;
    // NV_video_capture
    long glBeginVideoCaptureNV;
    long glBindVideoCaptureStreamBufferNV;
    long glBindVideoCaptureStreamTextureNV;
    long glEndVideoCaptureNV;
    long glGetVideoCaptureivNV;
    long glGetVideoCaptureStreamivNV;
    long glGetVideoCaptureStreamfvNV;
    long glGetVideoCaptureStreamdvNV;
    long glVideoCaptureNV;
    long glVideoCaptureStreamParameterivNV;
    long glVideoCaptureStreamParameterfvNV;
    long glVideoCaptureStreamParameterdvNV;

    private boolean AMD_debug_output_initNativeFunctionAddresses() {
        return
                (glDebugMessageEnableAMD = getFunctionAddress(new String[] {"glDebugMessageEnableAMD","glDebugMessageEnableAMDX"})) != 0 &
                        (glDebugMessageInsertAMD = getFunctionAddress(new String[] {"glDebugMessageInsertAMD","glDebugMessageInsertAMDX"})) != 0 &
                        (glDebugMessageCallbackAMD = getFunctionAddress(new String[] {"glDebugMessageCallbackAMD","glDebugMessageCallbackAMDX"})) != 0 &
                        (glGetDebugMessageLogAMD = getFunctionAddress(new String[] {"glGetDebugMessageLogAMD","glGetDebugMessageLogAMDX"})) != 0;
    }

    private long getFunctionAddress(String[] strings){
        for(String string : strings){
            long address = GL.getFunctionProvider().getFunctionAddress(string);
            if(address != 0){
                return address;
            }
        }
        return 0;
    }

    private boolean AMD_draw_buffers_blend_initNativeFunctionAddresses() {
        return
                (glBlendFuncIndexedAMD = GL.getFunctionProvider().getFunctionAddress("glBlendFuncIndexedAMD")) != 0 &
                        (glBlendFuncSeparateIndexedAMD = GL.getFunctionProvider().getFunctionAddress("glBlendFuncSeparateIndexedAMD")) != 0 &
                        (glBlendEquationIndexedAMD = GL.getFunctionProvider().getFunctionAddress("glBlendEquationIndexedAMD")) != 0 &
                        (glBlendEquationSeparateIndexedAMD = GL.getFunctionProvider().getFunctionAddress("glBlendEquationSeparateIndexedAMD")) != 0;
    }

    private boolean AMD_interleaved_elements_initNativeFunctionAddresses() {
        return
                (glVertexAttribParameteriAMD = GL.getFunctionProvider().getFunctionAddress("glVertexAttribParameteriAMD")) != 0;
    }

    private boolean AMD_multi_draw_indirect_initNativeFunctionAddresses() {
        return
                (glMultiDrawArraysIndirectAMD = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArraysIndirectAMD")) != 0 &
                        (glMultiDrawElementsIndirectAMD = GL.getFunctionProvider().getFunctionAddress("glMultiDrawElementsIndirectAMD")) != 0;
    }

    private boolean AMD_name_gen_delete_initNativeFunctionAddresses() {
        return
                (glGenNamesAMD = GL.getFunctionProvider().getFunctionAddress("glGenNamesAMD")) != 0 &
                        (glDeleteNamesAMD = GL.getFunctionProvider().getFunctionAddress("glDeleteNamesAMD")) != 0 &
                        (glIsNameAMD = GL.getFunctionProvider().getFunctionAddress("glIsNameAMD")) != 0;
    }

    private boolean AMD_performance_monitor_initNativeFunctionAddresses() {
        return
                (glGetPerfMonitorGroupsAMD = GL.getFunctionProvider().getFunctionAddress("glGetPerfMonitorGroupsAMD")) != 0 &
                        (glGetPerfMonitorCountersAMD = GL.getFunctionProvider().getFunctionAddress("glGetPerfMonitorCountersAMD")) != 0 &
                        (glGetPerfMonitorGroupStringAMD = GL.getFunctionProvider().getFunctionAddress("glGetPerfMonitorGroupStringAMD")) != 0 &
                        (glGetPerfMonitorCounterStringAMD = GL.getFunctionProvider().getFunctionAddress("glGetPerfMonitorCounterStringAMD")) != 0 &
                        (glGetPerfMonitorCounterInfoAMD = GL.getFunctionProvider().getFunctionAddress("glGetPerfMonitorCounterInfoAMD")) != 0 &
                        (glGenPerfMonitorsAMD = GL.getFunctionProvider().getFunctionAddress("glGenPerfMonitorsAMD")) != 0 &
                        (glDeletePerfMonitorsAMD = GL.getFunctionProvider().getFunctionAddress("glDeletePerfMonitorsAMD")) != 0 &
                        (glSelectPerfMonitorCountersAMD = GL.getFunctionProvider().getFunctionAddress("glSelectPerfMonitorCountersAMD")) != 0 &
                        (glBeginPerfMonitorAMD = GL.getFunctionProvider().getFunctionAddress("glBeginPerfMonitorAMD")) != 0 &
                        (glEndPerfMonitorAMD = GL.getFunctionProvider().getFunctionAddress("glEndPerfMonitorAMD")) != 0 &
                        (glGetPerfMonitorCounterDataAMD = GL.getFunctionProvider().getFunctionAddress("glGetPerfMonitorCounterDataAMD")) != 0;
    }

    private boolean AMD_sample_positions_initNativeFunctionAddresses() {
        return
                (glSetMultisamplefvAMD = GL.getFunctionProvider().getFunctionAddress("glSetMultisamplefvAMD")) != 0;
    }

    private boolean AMD_sparse_texture_initNativeFunctionAddresses() {
        return
                (glTexStorageSparseAMD = GL.getFunctionProvider().getFunctionAddress("glTexStorageSparseAMD")) != 0 &
                        (glTextureStorageSparseAMD = GL.getFunctionProvider().getFunctionAddress("glTextureStorageSparseAMD")) != 0;
    }

    private boolean AMD_stencil_operation_extended_initNativeFunctionAddresses() {
        return
                (glStencilOpValueAMD = GL.getFunctionProvider().getFunctionAddress("glStencilOpValueAMD")) != 0;
    }

    private boolean AMD_vertex_shader_tessellator_initNativeFunctionAddresses() {
        return
                (glTessellationFactorAMD = GL.getFunctionProvider().getFunctionAddress("glTessellationFactorAMD")) != 0 &
                        (glTessellationModeAMD = GL.getFunctionProvider().getFunctionAddress("glTessellationModeAMD")) != 0;
    }

    private boolean APPLE_element_array_initNativeFunctionAddresses() {
        return
                (glElementPointerAPPLE = GL.getFunctionProvider().getFunctionAddress("glElementPointerAPPLE")) != 0 &
                        (glDrawElementArrayAPPLE = GL.getFunctionProvider().getFunctionAddress("glDrawElementArrayAPPLE")) != 0 &
                        (glDrawRangeElementArrayAPPLE = GL.getFunctionProvider().getFunctionAddress("glDrawRangeElementArrayAPPLE")) != 0 &
                        (glMultiDrawElementArrayAPPLE = GL.getFunctionProvider().getFunctionAddress("glMultiDrawElementArrayAPPLE")) != 0 &
                        (glMultiDrawRangeElementArrayAPPLE = GL.getFunctionProvider().getFunctionAddress("glMultiDrawRangeElementArrayAPPLE")) != 0;
    }

    private boolean APPLE_fence_initNativeFunctionAddresses() {
        return
                (glGenFencesAPPLE = GL.getFunctionProvider().getFunctionAddress("glGenFencesAPPLE")) != 0 &
                        (glDeleteFencesAPPLE = GL.getFunctionProvider().getFunctionAddress("glDeleteFencesAPPLE")) != 0 &
                        (glSetFenceAPPLE = GL.getFunctionProvider().getFunctionAddress("glSetFenceAPPLE")) != 0 &
                        (glIsFenceAPPLE = GL.getFunctionProvider().getFunctionAddress("glIsFenceAPPLE")) != 0 &
                        (glTestFenceAPPLE = GL.getFunctionProvider().getFunctionAddress("glTestFenceAPPLE")) != 0 &
                        (glFinishFenceAPPLE = GL.getFunctionProvider().getFunctionAddress("glFinishFenceAPPLE")) != 0 &
                        (glTestObjectAPPLE = GL.getFunctionProvider().getFunctionAddress("glTestObjectAPPLE")) != 0 &
                        (glFinishObjectAPPLE = GL.getFunctionProvider().getFunctionAddress("glFinishObjectAPPLE")) != 0;
    }

    private boolean APPLE_flush_buffer_range_initNativeFunctionAddresses() {
        return
                (glBufferParameteriAPPLE = GL.getFunctionProvider().getFunctionAddress("glBufferParameteriAPPLE")) != 0 &
                        (glFlushMappedBufferRangeAPPLE = GL.getFunctionProvider().getFunctionAddress("glFlushMappedBufferRangeAPPLE")) != 0;
    }

    private boolean APPLE_object_purgeable_initNativeFunctionAddresses() {
        return
                (glObjectPurgeableAPPLE = GL.getFunctionProvider().getFunctionAddress("glObjectPurgeableAPPLE")) != 0 &
                        (glObjectUnpurgeableAPPLE = GL.getFunctionProvider().getFunctionAddress("glObjectUnpurgeableAPPLE")) != 0 &
                        (glGetObjectParameterivAPPLE = GL.getFunctionProvider().getFunctionAddress("glGetObjectParameterivAPPLE")) != 0;
    }

    private boolean APPLE_texture_range_initNativeFunctionAddresses() {
        return
                (glTextureRangeAPPLE = GL.getFunctionProvider().getFunctionAddress("glTextureRangeAPPLE")) != 0 &
                        (glGetTexParameterPointervAPPLE = GL.getFunctionProvider().getFunctionAddress("glGetTexParameterPointervAPPLE")) != 0;
    }

    private boolean APPLE_vertex_array_object_initNativeFunctionAddresses() {
        return
                (glBindVertexArrayAPPLE = GL.getFunctionProvider().getFunctionAddress("glBindVertexArrayAPPLE")) != 0 &
                        (glDeleteVertexArraysAPPLE = GL.getFunctionProvider().getFunctionAddress("glDeleteVertexArraysAPPLE")) != 0 &
                        (glGenVertexArraysAPPLE = GL.getFunctionProvider().getFunctionAddress("glGenVertexArraysAPPLE")) != 0 &
                        (glIsVertexArrayAPPLE = GL.getFunctionProvider().getFunctionAddress("glIsVertexArrayAPPLE")) != 0;
    }

    private boolean APPLE_vertex_array_range_initNativeFunctionAddresses() {
        return
                (glVertexArrayRangeAPPLE = GL.getFunctionProvider().getFunctionAddress("glVertexArrayRangeAPPLE")) != 0 &
                        (glFlushVertexArrayRangeAPPLE = GL.getFunctionProvider().getFunctionAddress("glFlushVertexArrayRangeAPPLE")) != 0 &
                        (glVertexArrayParameteriAPPLE = GL.getFunctionProvider().getFunctionAddress("glVertexArrayParameteriAPPLE")) != 0;
    }

    private boolean APPLE_vertex_program_evaluators_initNativeFunctionAddresses() {
        return
                (glEnableVertexAttribAPPLE = GL.getFunctionProvider().getFunctionAddress("glEnableVertexAttribAPPLE")) != 0 &
                        (glDisableVertexAttribAPPLE = GL.getFunctionProvider().getFunctionAddress("glDisableVertexAttribAPPLE")) != 0 &
                        (glIsVertexAttribEnabledAPPLE = GL.getFunctionProvider().getFunctionAddress("glIsVertexAttribEnabledAPPLE")) != 0 &
                        (glMapVertexAttrib1dAPPLE = GL.getFunctionProvider().getFunctionAddress("glMapVertexAttrib1dAPPLE")) != 0 &
                        (glMapVertexAttrib1fAPPLE = GL.getFunctionProvider().getFunctionAddress("glMapVertexAttrib1fAPPLE")) != 0 &
                        (glMapVertexAttrib2dAPPLE = GL.getFunctionProvider().getFunctionAddress("glMapVertexAttrib2dAPPLE")) != 0 &
                        (glMapVertexAttrib2fAPPLE = GL.getFunctionProvider().getFunctionAddress("glMapVertexAttrib2fAPPLE")) != 0;
    }

    private boolean ARB_ES2_compatibility_initNativeFunctionAddresses() {
        return
                (glReleaseShaderCompiler = GL.getFunctionProvider().getFunctionAddress("glReleaseShaderCompiler")) != 0 &
                        (glShaderBinary = GL.getFunctionProvider().getFunctionAddress("glShaderBinary")) != 0 &
                        (glGetShaderPrecisionFormat = GL.getFunctionProvider().getFunctionAddress("glGetShaderPrecisionFormat")) != 0 &
                        (glDepthRangef = GL.getFunctionProvider().getFunctionAddress("glDepthRangef")) != 0 &
                        (glClearDepthf = GL.getFunctionProvider().getFunctionAddress("glClearDepthf")) != 0;
    }

    private boolean ARB_ES3_1_compatibility_initNativeFunctionAddresses() {
        return
                (glMemoryBarrierByRegion = GL.getFunctionProvider().getFunctionAddress("glMemoryBarrierByRegion")) != 0;
    }

    private boolean ARB_base_instance_initNativeFunctionAddresses() {
        return
                (glDrawArraysInstancedBaseInstance = GL.getFunctionProvider().getFunctionAddress("glDrawArraysInstancedBaseInstance")) != 0 &
                        (glDrawElementsInstancedBaseInstance = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedBaseInstance")) != 0 &
                        (glDrawElementsInstancedBaseVertexBaseInstance = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedBaseVertexBaseInstance")) != 0;
    }

    private boolean ARB_bindless_texture_initNativeFunctionAddresses() {
        return
                (glGetTextureHandleARB = GL.getFunctionProvider().getFunctionAddress("glGetTextureHandleARB")) != 0 &
                        (glGetTextureSamplerHandleARB = GL.getFunctionProvider().getFunctionAddress("glGetTextureSamplerHandleARB")) != 0 &
                        (glMakeTextureHandleResidentARB = GL.getFunctionProvider().getFunctionAddress("glMakeTextureHandleResidentARB")) != 0 &
                        (glMakeTextureHandleNonResidentARB = GL.getFunctionProvider().getFunctionAddress("glMakeTextureHandleNonResidentARB")) != 0 &
                        (glGetImageHandleARB = GL.getFunctionProvider().getFunctionAddress("glGetImageHandleARB")) != 0 &
                        (glMakeImageHandleResidentARB = GL.getFunctionProvider().getFunctionAddress("glMakeImageHandleResidentARB")) != 0 &
                        (glMakeImageHandleNonResidentARB = GL.getFunctionProvider().getFunctionAddress("glMakeImageHandleNonResidentARB")) != 0 &
                        (glUniformHandleui64ARB = GL.getFunctionProvider().getFunctionAddress("glUniformHandleui64ARB")) != 0 &
                        (glUniformHandleui64vARB = GL.getFunctionProvider().getFunctionAddress("glUniformHandleui64vARB")) != 0 &
                        (glProgramUniformHandleui64ARB = GL.getFunctionProvider().getFunctionAddress("glProgramUniformHandleui64ARB")) != 0 &
                        (glProgramUniformHandleui64vARB = GL.getFunctionProvider().getFunctionAddress("glProgramUniformHandleui64vARB")) != 0 &
                        (glIsTextureHandleResidentARB = GL.getFunctionProvider().getFunctionAddress("glIsTextureHandleResidentARB")) != 0 &
                        (glIsImageHandleResidentARB = GL.getFunctionProvider().getFunctionAddress("glIsImageHandleResidentARB")) != 0 &
                        (glVertexAttribL1ui64ARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1ui64ARB")) != 0 &
                        (glVertexAttribL1ui64vARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1ui64vARB")) != 0 &
                        (glGetVertexAttribLui64vARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribLui64vARB")) != 0;
    }

    private boolean ARB_blend_func_extended_initNativeFunctionAddresses() {
        return
                (glBindFragDataLocationIndexed = GL.getFunctionProvider().getFunctionAddress("glBindFragDataLocationIndexed")) != 0 &
                        (glGetFragDataIndex = GL.getFunctionProvider().getFunctionAddress("glGetFragDataIndex")) != 0;
    }

    private boolean ARB_buffer_object_initNativeFunctionAddresses() {
        return
                (glBindBufferARB = GL.getFunctionProvider().getFunctionAddress("glBindBufferARB")) != 0 &
                        (glDeleteBuffersARB = GL.getFunctionProvider().getFunctionAddress("glDeleteBuffersARB")) != 0 &
                        (glGenBuffersARB = GL.getFunctionProvider().getFunctionAddress("glGenBuffersARB")) != 0 &
                        (glIsBufferARB = GL.getFunctionProvider().getFunctionAddress("glIsBufferARB")) != 0 &
                        (glBufferDataARB = GL.getFunctionProvider().getFunctionAddress("glBufferDataARB")) != 0 &
                        (glBufferSubDataARB = GL.getFunctionProvider().getFunctionAddress("glBufferSubDataARB")) != 0 &
                        (glGetBufferSubDataARB = GL.getFunctionProvider().getFunctionAddress("glGetBufferSubDataARB")) != 0 &
                        (glMapBufferARB = GL.getFunctionProvider().getFunctionAddress("glMapBufferARB")) != 0 &
                        (glUnmapBufferARB = GL.getFunctionProvider().getFunctionAddress("glUnmapBufferARB")) != 0 &
                        (glGetBufferParameterivARB = GL.getFunctionProvider().getFunctionAddress("glGetBufferParameterivARB")) != 0 &
                        (glGetBufferPointervARB = GL.getFunctionProvider().getFunctionAddress("glGetBufferPointervARB")) != 0;
    }

    private boolean ARB_buffer_storage_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glBufferStorage = GL.getFunctionProvider().getFunctionAddress("glBufferStorage")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glNamedBufferStorageEXT = GL.getFunctionProvider().getFunctionAddress("glNamedBufferStorageEXT")) != 0);
    }

    private boolean ARB_cl_event_initNativeFunctionAddresses() {
        return
                (glCreateSyncFromCLeventARB = GL.getFunctionProvider().getFunctionAddress("glCreateSyncFromCLeventARB")) != 0;
    }

    private boolean ARB_clear_buffer_object_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glClearBufferData = GL.getFunctionProvider().getFunctionAddress("glClearBufferData")) != 0 &
                        (glClearBufferSubData = GL.getFunctionProvider().getFunctionAddress("glClearBufferSubData")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glClearNamedBufferDataEXT = GL.getFunctionProvider().getFunctionAddress("glClearNamedBufferDataEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glClearNamedBufferSubDataEXT = GL.getFunctionProvider().getFunctionAddress("glClearNamedBufferSubDataEXT")) != 0);
    }

    private boolean ARB_clear_texture_initNativeFunctionAddresses() {
        return
                (glClearTexImage = GL.getFunctionProvider().getFunctionAddress("glClearTexImage")) != 0 &
                        (glClearTexSubImage = GL.getFunctionProvider().getFunctionAddress("glClearTexSubImage")) != 0;
    }

    private boolean ARB_clip_control_initNativeFunctionAddresses() {
        return
                (glClipControl = GL.getFunctionProvider().getFunctionAddress("glClipControl")) != 0;
    }

    private boolean ARB_color_buffer_float_initNativeFunctionAddresses() {
        return
                (glClampColorARB = GL.getFunctionProvider().getFunctionAddress("glClampColorARB")) != 0;
    }

    private boolean ARB_compute_shader_initNativeFunctionAddresses() {
        return
                (glDispatchCompute = GL.getFunctionProvider().getFunctionAddress("glDispatchCompute")) != 0 &
                        (glDispatchComputeIndirect = GL.getFunctionProvider().getFunctionAddress("glDispatchComputeIndirect")) != 0;
    }

    private boolean ARB_compute_variable_group_size_initNativeFunctionAddresses() {
        return
                (glDispatchComputeGroupSizeARB = GL.getFunctionProvider().getFunctionAddress("glDispatchComputeGroupSizeARB")) != 0;
    }

    private boolean ARB_copy_buffer_initNativeFunctionAddresses() {
        return
                (glCopyBufferSubData = GL.getFunctionProvider().getFunctionAddress("glCopyBufferSubData")) != 0;
    }

    private boolean ARB_copy_image_initNativeFunctionAddresses() {
        return
                (glCopyImageSubData = GL.getFunctionProvider().getFunctionAddress("glCopyImageSubData")) != 0;
    }

    private boolean ARB_debug_output_initNativeFunctionAddresses() {
        return
                (glDebugMessageControlARB = GL.getFunctionProvider().getFunctionAddress("glDebugMessageControlARB")) != 0 &
                        (glDebugMessageInsertARB = GL.getFunctionProvider().getFunctionAddress("glDebugMessageInsertARB")) != 0 &
                        (glDebugMessageCallbackARB = GL.getFunctionProvider().getFunctionAddress("glDebugMessageCallbackARB")) != 0 &
                        (glGetDebugMessageLogARB = GL.getFunctionProvider().getFunctionAddress("glGetDebugMessageLogARB")) != 0;
    }

    private boolean ARB_direct_state_access_initNativeFunctionAddresses() {
        return
                (glCreateTransformFeedbacks = GL.getFunctionProvider().getFunctionAddress("glCreateTransformFeedbacks")) != 0 &
                        (glTransformFeedbackBufferBase = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackBufferBase")) != 0 &
                        (glTransformFeedbackBufferRange = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackBufferRange")) != 0 &
                        (glGetTransformFeedbackiv = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbackiv")) != 0 &
                        (glGetTransformFeedbacki_v = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbacki_v")) != 0 &
                        (glGetTransformFeedbacki64_v = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbacki64_v")) != 0 &
                        (glCreateBuffers = GL.getFunctionProvider().getFunctionAddress("glCreateBuffers")) != 0 &
                        (glNamedBufferStorage = GL.getFunctionProvider().getFunctionAddress("glNamedBufferStorage")) != 0 &
                        (glNamedBufferData = GL.getFunctionProvider().getFunctionAddress("glNamedBufferData")) != 0 &
                        (glNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glNamedBufferSubData")) != 0 &
                        (glCopyNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glCopyNamedBufferSubData")) != 0 &
                        (glClearNamedBufferData = GL.getFunctionProvider().getFunctionAddress("glClearNamedBufferData")) != 0 &
                        (glClearNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glClearNamedBufferSubData")) != 0 &
                        (glMapNamedBuffer = GL.getFunctionProvider().getFunctionAddress("glMapNamedBuffer")) != 0 &
                        (glMapNamedBufferRange = GL.getFunctionProvider().getFunctionAddress("glMapNamedBufferRange")) != 0 &
                        (glUnmapNamedBuffer = GL.getFunctionProvider().getFunctionAddress("glUnmapNamedBuffer")) != 0 &
                        (glFlushMappedNamedBufferRange = GL.getFunctionProvider().getFunctionAddress("glFlushMappedNamedBufferRange")) != 0 &
                        (glGetNamedBufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferParameteriv")) != 0 &
                        (glGetNamedBufferParameteri64v = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferParameteri64v")) != 0 &
                        (glGetNamedBufferPointerv = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferPointerv")) != 0 &
                        (glGetNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferSubData")) != 0 &
                        (glCreateFramebuffers = GL.getFunctionProvider().getFunctionAddress("glCreateFramebuffers")) != 0 &
                        (glNamedFramebufferRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferRenderbuffer")) != 0 &
                        (glNamedFramebufferParameteri = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferParameteri")) != 0 &
                        (glNamedFramebufferTexture = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTexture")) != 0 &
                        (glNamedFramebufferTextureLayer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTextureLayer")) != 0 &
                        (glNamedFramebufferDrawBuffer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferDrawBuffer")) != 0 &
                        (glNamedFramebufferDrawBuffers = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferDrawBuffers")) != 0 &
                        (glNamedFramebufferReadBuffer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferReadBuffer")) != 0 &
                        (glInvalidateNamedFramebufferData = GL.getFunctionProvider().getFunctionAddress("glInvalidateNamedFramebufferData")) != 0 &
                        (glInvalidateNamedFramebufferSubData = GL.getFunctionProvider().getFunctionAddress("glInvalidateNamedFramebufferSubData")) != 0 &
                        (glClearNamedFramebufferiv = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferiv")) != 0 &
                        (glClearNamedFramebufferuiv = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferuiv")) != 0 &
                        (glClearNamedFramebufferfv = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferfv")) != 0 &
                        (glClearNamedFramebufferfi = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferfi")) != 0 &
                        (glBlitNamedFramebuffer = GL.getFunctionProvider().getFunctionAddress("glBlitNamedFramebuffer")) != 0 &
                        (glCheckNamedFramebufferStatus = GL.getFunctionProvider().getFunctionAddress("glCheckNamedFramebufferStatus")) != 0 &
                        (glGetNamedFramebufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedFramebufferParameteriv")) != 0 &
                        (glGetNamedFramebufferAttachmentParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedFramebufferAttachmentParameteriv")) != 0 &
                        (glCreateRenderbuffers = GL.getFunctionProvider().getFunctionAddress("glCreateRenderbuffers")) != 0 &
                        (glNamedRenderbufferStorage = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorage")) != 0 &
                        (glNamedRenderbufferStorageMultisample = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorageMultisample")) != 0 &
                        (glGetNamedRenderbufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedRenderbufferParameteriv")) != 0 &
                        (glCreateTextures = GL.getFunctionProvider().getFunctionAddress("glCreateTextures")) != 0 &
                        (glTextureBuffer = GL.getFunctionProvider().getFunctionAddress("glTextureBuffer")) != 0 &
                        (glTextureBufferRange = GL.getFunctionProvider().getFunctionAddress("glTextureBufferRange")) != 0 &
                        (glTextureStorage1D = GL.getFunctionProvider().getFunctionAddress("glTextureStorage1D")) != 0 &
                        (glTextureStorage2D = GL.getFunctionProvider().getFunctionAddress("glTextureStorage2D")) != 0 &
                        (glTextureStorage3D = GL.getFunctionProvider().getFunctionAddress("glTextureStorage3D")) != 0 &
                        (glTextureStorage2DMultisample = GL.getFunctionProvider().getFunctionAddress("glTextureStorage2DMultisample")) != 0 &
                        (glTextureStorage3DMultisample = GL.getFunctionProvider().getFunctionAddress("glTextureStorage3DMultisample")) != 0 &
                        (glTextureSubImage1D = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage1D")) != 0 &
                        (glTextureSubImage2D = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage2D")) != 0 &
                        (glTextureSubImage3D = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage3D")) != 0 &
                        (glCompressedTextureSubImage1D = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage1D")) != 0 &
                        (glCompressedTextureSubImage2D = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage2D")) != 0 &
                        (glCompressedTextureSubImage3D = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage3D")) != 0 &
                        (glCopyTextureSubImage1D = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage1D")) != 0 &
                        (glCopyTextureSubImage2D = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage2D")) != 0 &
                        (glCopyTextureSubImage3D = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage3D")) != 0 &
                        (glTextureParameterf = GL.getFunctionProvider().getFunctionAddress("glTextureParameterf")) != 0 &
                        (glTextureParameterfv = GL.getFunctionProvider().getFunctionAddress("glTextureParameterfv")) != 0 &
                        (glTextureParameteri = GL.getFunctionProvider().getFunctionAddress("glTextureParameteri")) != 0 &
                        (glTextureParameterIiv = GL.getFunctionProvider().getFunctionAddress("glTextureParameterIiv")) != 0 &
                        (glTextureParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glTextureParameterIuiv")) != 0 &
                        (glTextureParameteriv = GL.getFunctionProvider().getFunctionAddress("glTextureParameteriv")) != 0 &
                        (glGenerateTextureMipmap = GL.getFunctionProvider().getFunctionAddress("glGenerateTextureMipmap")) != 0 &
                        (glBindTextureUnit = GL.getFunctionProvider().getFunctionAddress("glBindTextureUnit")) != 0 &
                        (glGetTextureImage = GL.getFunctionProvider().getFunctionAddress("glGetTextureImage")) != 0 &
                        (glGetCompressedTextureImage = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTextureImage")) != 0 &
                        (glGetTextureLevelParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetTextureLevelParameterfv")) != 0 &
                        (glGetTextureLevelParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetTextureLevelParameteriv")) != 0 &
                        (glGetTextureParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterfv")) != 0 &
                        (glGetTextureParameterIiv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterIiv")) != 0 &
                        (glGetTextureParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterIuiv")) != 0 &
                        (glGetTextureParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameteriv")) != 0 &
                        (glCreateVertexArrays = GL.getFunctionProvider().getFunctionAddress("glCreateVertexArrays")) != 0 &
                        (glDisableVertexArrayAttrib = GL.getFunctionProvider().getFunctionAddress("glDisableVertexArrayAttrib")) != 0 &
                        (glEnableVertexArrayAttrib = GL.getFunctionProvider().getFunctionAddress("glEnableVertexArrayAttrib")) != 0 &
                        (glVertexArrayElementBuffer = GL.getFunctionProvider().getFunctionAddress("glVertexArrayElementBuffer")) != 0 &
                        (glVertexArrayVertexBuffer = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexBuffer")) != 0 &
                        (glVertexArrayVertexBuffers = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexBuffers")) != 0 &
                        (glVertexArrayAttribFormat = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribFormat")) != 0 &
                        (glVertexArrayAttribIFormat = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribIFormat")) != 0 &
                        (glVertexArrayAttribLFormat = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribLFormat")) != 0 &
                        (glVertexArrayAttribBinding = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribBinding")) != 0 &
                        (glVertexArrayBindingDivisor = GL.getFunctionProvider().getFunctionAddress("glVertexArrayBindingDivisor")) != 0 &
                        (glGetVertexArrayiv = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayiv")) != 0 &
                        (glGetVertexArrayIndexediv = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayIndexediv")) != 0 &
                        (glGetVertexArrayIndexed64iv = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayIndexed64iv")) != 0 &
                        (glCreateSamplers = GL.getFunctionProvider().getFunctionAddress("glCreateSamplers")) != 0 &
                        (glCreateProgramPipelines = GL.getFunctionProvider().getFunctionAddress("glCreateProgramPipelines")) != 0 &
                        (glCreateQueries = GL.getFunctionProvider().getFunctionAddress("glCreateQueries")) != 0;
    }

    private boolean ARB_draw_buffers_initNativeFunctionAddresses() {
        return
                (glDrawBuffersARB = GL.getFunctionProvider().getFunctionAddress("glDrawBuffersARB")) != 0;
    }

    private boolean ARB_draw_buffers_blend_initNativeFunctionAddresses() {
        return
                (glBlendEquationiARB = GL.getFunctionProvider().getFunctionAddress("glBlendEquationiARB")) != 0 &
                        (glBlendEquationSeparateiARB = GL.getFunctionProvider().getFunctionAddress("glBlendEquationSeparateiARB")) != 0 &
                        (glBlendFunciARB = GL.getFunctionProvider().getFunctionAddress("glBlendFunciARB")) != 0 &
                        (glBlendFuncSeparateiARB = GL.getFunctionProvider().getFunctionAddress("glBlendFuncSeparateiARB")) != 0;
    }

    private boolean ARB_draw_elements_base_vertex_initNativeFunctionAddresses() {
        return
                (glDrawElementsBaseVertex = GL.getFunctionProvider().getFunctionAddress("glDrawElementsBaseVertex")) != 0 &
                        (glDrawRangeElementsBaseVertex = GL.getFunctionProvider().getFunctionAddress("glDrawRangeElementsBaseVertex")) != 0 &
                        (glDrawElementsInstancedBaseVertex = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedBaseVertex")) != 0;
    }

    private boolean ARB_draw_indirect_initNativeFunctionAddresses() {
        return
                (glDrawArraysIndirect = GL.getFunctionProvider().getFunctionAddress("glDrawArraysIndirect")) != 0 &
                        (glDrawElementsIndirect = GL.getFunctionProvider().getFunctionAddress("glDrawElementsIndirect")) != 0;
    }

    private boolean ARB_draw_instanced_initNativeFunctionAddresses() {
        return
                (glDrawArraysInstancedARB = GL.getFunctionProvider().getFunctionAddress("glDrawArraysInstancedARB")) != 0 &
                        (glDrawElementsInstancedARB = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedARB")) != 0;
    }

    private boolean ARB_framebuffer_no_attachments_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glFramebufferParameteri = GL.getFunctionProvider().getFunctionAddress("glFramebufferParameteri")) != 0 &
                        (glGetFramebufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetFramebufferParameteriv")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glNamedFramebufferParameteriEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferParameteriEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glGetNamedFramebufferParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedFramebufferParameterivEXT")) != 0);
    }

    private boolean ARB_framebuffer_object_initNativeFunctionAddresses() {
        return
                (glIsRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glIsRenderbuffer")) != 0 &
                        (glBindRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glBindRenderbuffer")) != 0 &
                        (glDeleteRenderbuffers = GL.getFunctionProvider().getFunctionAddress("glDeleteRenderbuffers")) != 0 &
                        (glGenRenderbuffers = GL.getFunctionProvider().getFunctionAddress("glGenRenderbuffers")) != 0 &
                        (glRenderbufferStorage = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorage")) != 0 &
                        (glRenderbufferStorageMultisample = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorageMultisample")) != 0 &
                        (glGetRenderbufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetRenderbufferParameteriv")) != 0 &
                        (glIsFramebuffer = GL.getFunctionProvider().getFunctionAddress("glIsFramebuffer")) != 0 &
                        (glBindFramebuffer = GL.getFunctionProvider().getFunctionAddress("glBindFramebuffer")) != 0 &
                        (glDeleteFramebuffers = GL.getFunctionProvider().getFunctionAddress("glDeleteFramebuffers")) != 0 &
                        (glGenFramebuffers = GL.getFunctionProvider().getFunctionAddress("glGenFramebuffers")) != 0 &
                        (glCheckFramebufferStatus = GL.getFunctionProvider().getFunctionAddress("glCheckFramebufferStatus")) != 0 &
                        (glFramebufferTexture1D = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture1D")) != 0 &
                        (glFramebufferTexture2D = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture2D")) != 0 &
                        (glFramebufferTexture3D = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture3D")) != 0 &
                        (glFramebufferTextureLayer = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureLayer")) != 0 &
                        (glFramebufferRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glFramebufferRenderbuffer")) != 0 &
                        (glGetFramebufferAttachmentParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetFramebufferAttachmentParameteriv")) != 0 &
                        (glBlitFramebuffer = GL.getFunctionProvider().getFunctionAddress("glBlitFramebuffer")) != 0 &
                        (glGenerateMipmap = GL.getFunctionProvider().getFunctionAddress("glGenerateMipmap")) != 0;
    }

    private boolean ARB_geometry_shader4_initNativeFunctionAddresses() {
        return
                (glProgramParameteriARB = GL.getFunctionProvider().getFunctionAddress("glProgramParameteriARB")) != 0 &
                        (glFramebufferTextureARB = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureARB")) != 0 &
                        (glFramebufferTextureLayerARB = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureLayerARB")) != 0 &
                        (glFramebufferTextureFaceARB = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureFaceARB")) != 0;
    }

    private boolean ARB_get_program_binary_initNativeFunctionAddresses() {
        return
                (glGetProgramBinary = GL.getFunctionProvider().getFunctionAddress("glGetProgramBinary")) != 0 &
                        (glProgramBinary = GL.getFunctionProvider().getFunctionAddress("glProgramBinary")) != 0 &
                        (glProgramParameteri = GL.getFunctionProvider().getFunctionAddress("glProgramParameteri")) != 0;
    }

    private boolean ARB_get_texture_sub_image_initNativeFunctionAddresses() {
        return
                (glGetTextureSubImage = GL.getFunctionProvider().getFunctionAddress("glGetTextureSubImage")) != 0 &
                        (glGetCompressedTextureSubImage = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTextureSubImage")) != 0;
    }

    private boolean ARB_gpu_shader_fp64_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glUniform1d = GL.getFunctionProvider().getFunctionAddress("glUniform1d")) != 0 &
                        (glUniform2d = GL.getFunctionProvider().getFunctionAddress("glUniform2d")) != 0 &
                        (glUniform3d = GL.getFunctionProvider().getFunctionAddress("glUniform3d")) != 0 &
                        (glUniform4d = GL.getFunctionProvider().getFunctionAddress("glUniform4d")) != 0 &
                        (glUniform1dv = GL.getFunctionProvider().getFunctionAddress("glUniform1dv")) != 0 &
                        (glUniform2dv = GL.getFunctionProvider().getFunctionAddress("glUniform2dv")) != 0 &
                        (glUniform3dv = GL.getFunctionProvider().getFunctionAddress("glUniform3dv")) != 0 &
                        (glUniform4dv = GL.getFunctionProvider().getFunctionAddress("glUniform4dv")) != 0 &
                        (glUniformMatrix2dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2dv")) != 0 &
                        (glUniformMatrix3dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3dv")) != 0 &
                        (glUniformMatrix4dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4dv")) != 0 &
                        (glUniformMatrix2x3dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2x3dv")) != 0 &
                        (glUniformMatrix2x4dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2x4dv")) != 0 &
                        (glUniformMatrix3x2dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3x2dv")) != 0 &
                        (glUniformMatrix3x4dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3x4dv")) != 0 &
                        (glUniformMatrix4x2dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4x2dv")) != 0 &
                        (glUniformMatrix4x3dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4x3dv")) != 0 &
                        (glGetUniformdv = GL.getFunctionProvider().getFunctionAddress("glGetUniformdv")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform1dEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1dEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform2dEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2dEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform3dEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3dEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform4dEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4dEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform1dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform2dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform3dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform4dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix2dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix3dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix4dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix2x3dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x3dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix2x4dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x4dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix3x2dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x2dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix3x4dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x4dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix4x2dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x2dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniformMatrix4x3dvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x3dvEXT")) != 0);
    }

    private boolean ARB_imaging_initNativeFunctionAddresses(boolean forwardCompatible) {
        return
                (forwardCompatible || (glColorTable = GL.getFunctionProvider().getFunctionAddress("glColorTable")) != 0) &
                        (forwardCompatible || (glColorSubTable = GL.getFunctionProvider().getFunctionAddress("glColorSubTable")) != 0) &
                        (forwardCompatible || (glColorTableParameteriv = GL.getFunctionProvider().getFunctionAddress("glColorTableParameteriv")) != 0) &
                        (forwardCompatible || (glColorTableParameterfv = GL.getFunctionProvider().getFunctionAddress("glColorTableParameterfv")) != 0) &
                        (forwardCompatible || (glCopyColorSubTable = GL.getFunctionProvider().getFunctionAddress("glCopyColorSubTable")) != 0) &
                        (forwardCompatible || (glCopyColorTable = GL.getFunctionProvider().getFunctionAddress("glCopyColorTable")) != 0) &
                        (forwardCompatible || (glGetColorTable = GL.getFunctionProvider().getFunctionAddress("glGetColorTable")) != 0) &
                        (forwardCompatible || (glGetColorTableParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetColorTableParameteriv")) != 0) &
                        (forwardCompatible || (glGetColorTableParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetColorTableParameterfv")) != 0) &
                        (glBlendEquation = GL.getFunctionProvider().getFunctionAddress("glBlendEquation")) != 0 &
                        (glBlendColor = GL.getFunctionProvider().getFunctionAddress("glBlendColor")) != 0 &
                        (forwardCompatible || (glHistogram = GL.getFunctionProvider().getFunctionAddress("glHistogram")) != 0) &
                        (forwardCompatible || (glResetHistogram = GL.getFunctionProvider().getFunctionAddress("glResetHistogram")) != 0) &
                        (forwardCompatible || (glGetHistogram = GL.getFunctionProvider().getFunctionAddress("glGetHistogram")) != 0) &
                        (forwardCompatible || (glGetHistogramParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetHistogramParameterfv")) != 0) &
                        (forwardCompatible || (glGetHistogramParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetHistogramParameteriv")) != 0) &
                        (forwardCompatible || (glMinmax = GL.getFunctionProvider().getFunctionAddress("glMinmax")) != 0) &
                        (forwardCompatible || (glResetMinmax = GL.getFunctionProvider().getFunctionAddress("glResetMinmax")) != 0) &
                        (forwardCompatible || (glGetMinmax = GL.getFunctionProvider().getFunctionAddress("glGetMinmax")) != 0) &
                        (forwardCompatible || (glGetMinmaxParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetMinmaxParameterfv")) != 0) &
                        (forwardCompatible || (glGetMinmaxParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetMinmaxParameteriv")) != 0) &
                        (forwardCompatible || (glConvolutionFilter1D = GL.getFunctionProvider().getFunctionAddress("glConvolutionFilter1D")) != 0) &
                        (forwardCompatible || (glConvolutionFilter2D = GL.getFunctionProvider().getFunctionAddress("glConvolutionFilter2D")) != 0) &
                        (forwardCompatible || (glConvolutionParameterf = GL.getFunctionProvider().getFunctionAddress("glConvolutionParameterf")) != 0) &
                        (forwardCompatible || (glConvolutionParameterfv = GL.getFunctionProvider().getFunctionAddress("glConvolutionParameterfv")) != 0) &
                        (forwardCompatible || (glConvolutionParameteri = GL.getFunctionProvider().getFunctionAddress("glConvolutionParameteri")) != 0) &
                        (forwardCompatible || (glConvolutionParameteriv = GL.getFunctionProvider().getFunctionAddress("glConvolutionParameteriv")) != 0) &
                        (forwardCompatible || (glCopyConvolutionFilter1D = GL.getFunctionProvider().getFunctionAddress("glCopyConvolutionFilter1D")) != 0) &
                        (forwardCompatible || (glCopyConvolutionFilter2D = GL.getFunctionProvider().getFunctionAddress("glCopyConvolutionFilter2D")) != 0) &
                        (forwardCompatible || (glGetConvolutionFilter = GL.getFunctionProvider().getFunctionAddress("glGetConvolutionFilter")) != 0) &
                        (forwardCompatible || (glGetConvolutionParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetConvolutionParameterfv")) != 0) &
                        (forwardCompatible || (glGetConvolutionParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetConvolutionParameteriv")) != 0) &
                        (forwardCompatible || (glSeparableFilter2D = GL.getFunctionProvider().getFunctionAddress("glSeparableFilter2D")) != 0) &
                        (forwardCompatible || (glGetSeparableFilter = GL.getFunctionProvider().getFunctionAddress("glGetSeparableFilter")) != 0);
    }

    private boolean ARB_indirect_parameters_initNativeFunctionAddresses() {
        return
                (glMultiDrawArraysIndirectCountARB = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArraysIndirectCountARB")) != 0 &
                        (glMultiDrawElementsIndirectCountARB = GL.getFunctionProvider().getFunctionAddress("glMultiDrawElementsIndirectCountARB")) != 0;
    }

    private boolean ARB_instanced_arrays_initNativeFunctionAddresses() {
        return
                (glVertexAttribDivisorARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttribDivisorARB")) != 0;
    }

    private boolean ARB_internalformat_query_initNativeFunctionAddresses() {
        return
                (glGetInternalformativ = GL.getFunctionProvider().getFunctionAddress("glGetInternalformativ")) != 0;
    }

    private boolean ARB_internalformat_query2_initNativeFunctionAddresses() {
        return
                (glGetInternalformati64v = GL.getFunctionProvider().getFunctionAddress("glGetInternalformati64v")) != 0;
    }

    private boolean ARB_invalidate_subdata_initNativeFunctionAddresses() {
        return
                (glInvalidateTexSubImage = GL.getFunctionProvider().getFunctionAddress("glInvalidateTexSubImage")) != 0 &
                        (glInvalidateTexImage = GL.getFunctionProvider().getFunctionAddress("glInvalidateTexImage")) != 0 &
                        (glInvalidateBufferSubData = GL.getFunctionProvider().getFunctionAddress("glInvalidateBufferSubData")) != 0 &
                        (glInvalidateBufferData = GL.getFunctionProvider().getFunctionAddress("glInvalidateBufferData")) != 0 &
                        (glInvalidateFramebuffer = GL.getFunctionProvider().getFunctionAddress("glInvalidateFramebuffer")) != 0 &
                        (glInvalidateSubFramebuffer = GL.getFunctionProvider().getFunctionAddress("glInvalidateSubFramebuffer")) != 0;
    }

    private boolean ARB_map_buffer_range_initNativeFunctionAddresses() {
        return
                (glMapBufferRange = GL.getFunctionProvider().getFunctionAddress("glMapBufferRange")) != 0 &
                        (glFlushMappedBufferRange = GL.getFunctionProvider().getFunctionAddress("glFlushMappedBufferRange")) != 0;
    }

    private boolean ARB_matrix_palette_initNativeFunctionAddresses() {
        return
                (glCurrentPaletteMatrixARB = GL.getFunctionProvider().getFunctionAddress("glCurrentPaletteMatrixARB")) != 0 &
                        (glMatrixIndexPointerARB = GL.getFunctionProvider().getFunctionAddress("glMatrixIndexPointerARB")) != 0 &
                        (glMatrixIndexubvARB = GL.getFunctionProvider().getFunctionAddress("glMatrixIndexubvARB")) != 0 &
                        (glMatrixIndexusvARB = GL.getFunctionProvider().getFunctionAddress("glMatrixIndexusvARB")) != 0 &
                        (glMatrixIndexuivARB = GL.getFunctionProvider().getFunctionAddress("glMatrixIndexuivARB")) != 0;
    }

    private boolean ARB_multi_bind_initNativeFunctionAddresses() {
        return
                (glBindBuffersBase = GL.getFunctionProvider().getFunctionAddress("glBindBuffersBase")) != 0 &
                        (glBindBuffersRange = GL.getFunctionProvider().getFunctionAddress("glBindBuffersRange")) != 0 &
                        (glBindTextures = GL.getFunctionProvider().getFunctionAddress("glBindTextures")) != 0 &
                        (glBindSamplers = GL.getFunctionProvider().getFunctionAddress("glBindSamplers")) != 0 &
                        (glBindImageTextures = GL.getFunctionProvider().getFunctionAddress("glBindImageTextures")) != 0 &
                        (glBindVertexBuffers = GL.getFunctionProvider().getFunctionAddress("glBindVertexBuffers")) != 0;
    }

    private boolean ARB_multi_draw_indirect_initNativeFunctionAddresses() {
        return
                (glMultiDrawArraysIndirect = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArraysIndirect")) != 0 &
                        (glMultiDrawElementsIndirect = GL.getFunctionProvider().getFunctionAddress("glMultiDrawElementsIndirect")) != 0;
    }

    private boolean ARB_multisample_initNativeFunctionAddresses() {
        return
                (glSampleCoverageARB = GL.getFunctionProvider().getFunctionAddress("glSampleCoverageARB")) != 0;
    }

    private boolean ARB_multitexture_initNativeFunctionAddresses() {
        return
                (glClientActiveTextureARB = GL.getFunctionProvider().getFunctionAddress("glClientActiveTextureARB")) != 0 &
                        (glActiveTextureARB = GL.getFunctionProvider().getFunctionAddress("glActiveTextureARB")) != 0 &
                        (glMultiTexCoord1fARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1fARB")) != 0 &
                        (glMultiTexCoord1dARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1dARB")) != 0 &
                        (glMultiTexCoord1iARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1iARB")) != 0 &
                        (glMultiTexCoord1sARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1sARB")) != 0 &
                        (glMultiTexCoord2fARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2fARB")) != 0 &
                        (glMultiTexCoord2dARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2dARB")) != 0 &
                        (glMultiTexCoord2iARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2iARB")) != 0 &
                        (glMultiTexCoord2sARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2sARB")) != 0 &
                        (glMultiTexCoord3fARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3fARB")) != 0 &
                        (glMultiTexCoord3dARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3dARB")) != 0 &
                        (glMultiTexCoord3iARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3iARB")) != 0 &
                        (glMultiTexCoord3sARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3sARB")) != 0 &
                        (glMultiTexCoord4fARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4fARB")) != 0 &
                        (glMultiTexCoord4dARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4dARB")) != 0 &
                        (glMultiTexCoord4iARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4iARB")) != 0 &
                        (glMultiTexCoord4sARB = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4sARB")) != 0;
    }

    private boolean ARB_occlusion_query_initNativeFunctionAddresses() {
        return
                (glGenQueriesARB = GL.getFunctionProvider().getFunctionAddress("glGenQueriesARB")) != 0 &
                        (glDeleteQueriesARB = GL.getFunctionProvider().getFunctionAddress("glDeleteQueriesARB")) != 0 &
                        (glIsQueryARB = GL.getFunctionProvider().getFunctionAddress("glIsQueryARB")) != 0 &
                        (glBeginQueryARB = GL.getFunctionProvider().getFunctionAddress("glBeginQueryARB")) != 0 &
                        (glEndQueryARB = GL.getFunctionProvider().getFunctionAddress("glEndQueryARB")) != 0 &
                        (glGetQueryivARB = GL.getFunctionProvider().getFunctionAddress("glGetQueryivARB")) != 0 &
                        (glGetQueryObjectivARB = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectivARB")) != 0 &
                        (glGetQueryObjectuivARB = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectuivARB")) != 0;
    }

    private boolean ARB_point_parameters_initNativeFunctionAddresses() {
        return
                (glPointParameterfARB = GL.getFunctionProvider().getFunctionAddress("glPointParameterfARB")) != 0 &
                        (glPointParameterfvARB = GL.getFunctionProvider().getFunctionAddress("glPointParameterfvARB")) != 0;
    }

    private boolean ARB_program_initNativeFunctionAddresses() {
        return
                (glProgramStringARB = GL.getFunctionProvider().getFunctionAddress("glProgramStringARB")) != 0 &
                        (glBindProgramARB = GL.getFunctionProvider().getFunctionAddress("glBindProgramARB")) != 0 &
                        (glDeleteProgramsARB = GL.getFunctionProvider().getFunctionAddress("glDeleteProgramsARB")) != 0 &
                        (glGenProgramsARB = GL.getFunctionProvider().getFunctionAddress("glGenProgramsARB")) != 0 &
                        (glProgramEnvParameter4fARB = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameter4fARB")) != 0 &
                        (glProgramEnvParameter4dARB = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameter4dARB")) != 0 &
                        (glProgramEnvParameter4fvARB = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameter4fvARB")) != 0 &
                        (glProgramEnvParameter4dvARB = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameter4dvARB")) != 0 &
                        (glProgramLocalParameter4fARB = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameter4fARB")) != 0 &
                        (glProgramLocalParameter4dARB = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameter4dARB")) != 0 &
                        (glProgramLocalParameter4fvARB = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameter4fvARB")) != 0 &
                        (glProgramLocalParameter4dvARB = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameter4dvARB")) != 0 &
                        (glGetProgramEnvParameterfvARB = GL.getFunctionProvider().getFunctionAddress("glGetProgramEnvParameterfvARB")) != 0 &
                        (glGetProgramEnvParameterdvARB = GL.getFunctionProvider().getFunctionAddress("glGetProgramEnvParameterdvARB")) != 0 &
                        (glGetProgramLocalParameterfvARB = GL.getFunctionProvider().getFunctionAddress("glGetProgramLocalParameterfvARB")) != 0 &
                        (glGetProgramLocalParameterdvARB = GL.getFunctionProvider().getFunctionAddress("glGetProgramLocalParameterdvARB")) != 0 &
                        (glGetProgramivARB = GL.getFunctionProvider().getFunctionAddress("glGetProgramivARB")) != 0 &
                        (glGetProgramStringARB = GL.getFunctionProvider().getFunctionAddress("glGetProgramStringARB")) != 0 &
                        (glIsProgramARB = GL.getFunctionProvider().getFunctionAddress("glIsProgramARB")) != 0;
    }

    private boolean ARB_program_interface_query_initNativeFunctionAddresses() {
        return
                (glGetProgramInterfaceiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramInterfaceiv")) != 0 &
                        (glGetProgramResourceIndex = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceIndex")) != 0 &
                        (glGetProgramResourceName = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceName")) != 0 &
                        (glGetProgramResourceiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceiv")) != 0 &
                        (glGetProgramResourceLocation = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceLocation")) != 0 &
                        (glGetProgramResourceLocationIndex = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceLocationIndex")) != 0;
    }

    private boolean ARB_provoking_vertex_initNativeFunctionAddresses() {
        return
                (glProvokingVertex = GL.getFunctionProvider().getFunctionAddress("glProvokingVertex")) != 0;
    }

    private boolean ARB_robustness_initNativeFunctionAddresses(boolean forwardCompatible,Set<String> supported_extensions) {
        return
                (glGetGraphicsResetStatusARB = GL.getFunctionProvider().getFunctionAddress("glGetGraphicsResetStatusARB")) != 0 &
                        (forwardCompatible || (glGetnMapdvARB = GL.getFunctionProvider().getFunctionAddress("glGetnMapdvARB")) != 0) &
                        (forwardCompatible || (glGetnMapfvARB = GL.getFunctionProvider().getFunctionAddress("glGetnMapfvARB")) != 0) &
                        (forwardCompatible || (glGetnMapivARB = GL.getFunctionProvider().getFunctionAddress("glGetnMapivARB")) != 0) &
                        (forwardCompatible || (glGetnPixelMapfvARB = GL.getFunctionProvider().getFunctionAddress("glGetnPixelMapfvARB")) != 0) &
                        (forwardCompatible || (glGetnPixelMapuivARB = GL.getFunctionProvider().getFunctionAddress("glGetnPixelMapuivARB")) != 0) &
                        (forwardCompatible || (glGetnPixelMapusvARB = GL.getFunctionProvider().getFunctionAddress("glGetnPixelMapusvARB")) != 0) &
                        (forwardCompatible || (glGetnPolygonStippleARB = GL.getFunctionProvider().getFunctionAddress("glGetnPolygonStippleARB")) != 0) &
                        (glGetnTexImageARB = GL.getFunctionProvider().getFunctionAddress("glGetnTexImageARB")) != 0 &
                        (glReadnPixelsARB = GL.getFunctionProvider().getFunctionAddress("glReadnPixelsARB")) != 0 &
                        (!supported_extensions.contains("GL_ARB_imaging") || (glGetnColorTableARB = GL.getFunctionProvider().getFunctionAddress("glGetnColorTableARB")) != 0) &
                        (!supported_extensions.contains("GL_ARB_imaging") || (glGetnConvolutionFilterARB = GL.getFunctionProvider().getFunctionAddress("glGetnConvolutionFilterARB")) != 0) &
                        (!supported_extensions.contains("GL_ARB_imaging") || (glGetnSeparableFilterARB = GL.getFunctionProvider().getFunctionAddress("glGetnSeparableFilterARB")) != 0) &
                        (!supported_extensions.contains("GL_ARB_imaging") || (glGetnHistogramARB = GL.getFunctionProvider().getFunctionAddress("glGetnHistogramARB")) != 0) &
                        (!supported_extensions.contains("GL_ARB_imaging") || (glGetnMinmaxARB = GL.getFunctionProvider().getFunctionAddress("glGetnMinmaxARB")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetnCompressedTexImageARB = GL.getFunctionProvider().getFunctionAddress("glGetnCompressedTexImageARB")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glGetnUniformfvARB = GL.getFunctionProvider().getFunctionAddress("glGetnUniformfvARB")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glGetnUniformivARB = GL.getFunctionProvider().getFunctionAddress("glGetnUniformivARB")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glGetnUniformuivARB = GL.getFunctionProvider().getFunctionAddress("glGetnUniformuivARB")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glGetnUniformdvARB = GL.getFunctionProvider().getFunctionAddress("glGetnUniformdvARB")) != 0);
    }

    private boolean ARB_sample_shading_initNativeFunctionAddresses() {
        return
                (glMinSampleShadingARB = GL.getFunctionProvider().getFunctionAddress("glMinSampleShadingARB")) != 0;
    }

    private boolean ARB_sampler_objects_initNativeFunctionAddresses() {
        return
                (glGenSamplers = GL.getFunctionProvider().getFunctionAddress("glGenSamplers")) != 0 &
                        (glDeleteSamplers = GL.getFunctionProvider().getFunctionAddress("glDeleteSamplers")) != 0 &
                        (glIsSampler = GL.getFunctionProvider().getFunctionAddress("glIsSampler")) != 0 &
                        (glBindSampler = GL.getFunctionProvider().getFunctionAddress("glBindSampler")) != 0 &
                        (glSamplerParameteri = GL.getFunctionProvider().getFunctionAddress("glSamplerParameteri")) != 0 &
                        (glSamplerParameterf = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterf")) != 0 &
                        (glSamplerParameteriv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameteriv")) != 0 &
                        (glSamplerParameterfv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterfv")) != 0 &
                        (glSamplerParameterIiv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterIiv")) != 0 &
                        (glSamplerParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterIuiv")) != 0 &
                        (glGetSamplerParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameteriv")) != 0 &
                        (glGetSamplerParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameterfv")) != 0 &
                        (glGetSamplerParameterIiv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameterIiv")) != 0 &
                        (glGetSamplerParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameterIuiv")) != 0;
    }

    private boolean ARB_separate_shader_objects_initNativeFunctionAddresses() {
        return
                (glUseProgramStages = GL.getFunctionProvider().getFunctionAddress("glUseProgramStages")) != 0 &
                        (glActiveShaderProgram = GL.getFunctionProvider().getFunctionAddress("glActiveShaderProgram")) != 0 &
                        (glCreateShaderProgramv = GL.getFunctionProvider().getFunctionAddress("glCreateShaderProgramv")) != 0 &
                        (glBindProgramPipeline = GL.getFunctionProvider().getFunctionAddress("glBindProgramPipeline")) != 0 &
                        (glDeleteProgramPipelines = GL.getFunctionProvider().getFunctionAddress("glDeleteProgramPipelines")) != 0 &
                        (glGenProgramPipelines = GL.getFunctionProvider().getFunctionAddress("glGenProgramPipelines")) != 0 &
                        (glIsProgramPipeline = GL.getFunctionProvider().getFunctionAddress("glIsProgramPipeline")) != 0 &
                        (glProgramParameteri = GL.getFunctionProvider().getFunctionAddress("glProgramParameteri")) != 0 &
                        (glGetProgramPipelineiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramPipelineiv")) != 0 &
                        (glProgramUniform1i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1i")) != 0 &
                        (glProgramUniform2i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2i")) != 0 &
                        (glProgramUniform3i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3i")) != 0 &
                        (glProgramUniform4i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4i")) != 0 &
                        (glProgramUniform1f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1f")) != 0 &
                        (glProgramUniform2f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2f")) != 0 &
                        (glProgramUniform3f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3f")) != 0 &
                        (glProgramUniform4f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4f")) != 0 &
                        (glProgramUniform1d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1d")) != 0 &
                        (glProgramUniform2d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2d")) != 0 &
                        (glProgramUniform3d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3d")) != 0 &
                        (glProgramUniform4d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4d")) != 0 &
                        (glProgramUniform1iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1iv")) != 0 &
                        (glProgramUniform2iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2iv")) != 0 &
                        (glProgramUniform3iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3iv")) != 0 &
                        (glProgramUniform4iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4iv")) != 0 &
                        (glProgramUniform1fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1fv")) != 0 &
                        (glProgramUniform2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2fv")) != 0 &
                        (glProgramUniform3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3fv")) != 0 &
                        (glProgramUniform4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4fv")) != 0 &
                        (glProgramUniform1dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1dv")) != 0 &
                        (glProgramUniform2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2dv")) != 0 &
                        (glProgramUniform3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3dv")) != 0 &
                        (glProgramUniform4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4dv")) != 0 &
                        (glProgramUniform1ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1ui")) != 0 &
                        (glProgramUniform2ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2ui")) != 0 &
                        (glProgramUniform3ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3ui")) != 0 &
                        (glProgramUniform4ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4ui")) != 0 &
                        (glProgramUniform1uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1uiv")) != 0 &
                        (glProgramUniform2uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2uiv")) != 0 &
                        (glProgramUniform3uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3uiv")) != 0 &
                        (glProgramUniform4uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4uiv")) != 0 &
                        (glProgramUniformMatrix2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2fv")) != 0 &
                        (glProgramUniformMatrix3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3fv")) != 0 &
                        (glProgramUniformMatrix4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4fv")) != 0 &
                        (glProgramUniformMatrix2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2dv")) != 0 &
                        (glProgramUniformMatrix3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3dv")) != 0 &
                        (glProgramUniformMatrix4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4dv")) != 0 &
                        (glProgramUniformMatrix2x3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x3fv")) != 0 &
                        (glProgramUniformMatrix3x2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x2fv")) != 0 &
                        (glProgramUniformMatrix2x4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x4fv")) != 0 &
                        (glProgramUniformMatrix4x2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x2fv")) != 0 &
                        (glProgramUniformMatrix3x4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x4fv")) != 0 &
                        (glProgramUniformMatrix4x3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x3fv")) != 0 &
                        (glProgramUniformMatrix2x3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x3dv")) != 0 &
                        (glProgramUniformMatrix3x2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x2dv")) != 0 &
                        (glProgramUniformMatrix2x4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x4dv")) != 0 &
                        (glProgramUniformMatrix4x2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x2dv")) != 0 &
                        (glProgramUniformMatrix3x4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x4dv")) != 0 &
                        (glProgramUniformMatrix4x3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x3dv")) != 0 &
                        (glValidateProgramPipeline = GL.getFunctionProvider().getFunctionAddress("glValidateProgramPipeline")) != 0 &
                        (glGetProgramPipelineInfoLog = GL.getFunctionProvider().getFunctionAddress("glGetProgramPipelineInfoLog")) != 0;
    }

    private boolean ARB_shader_atomic_counters_initNativeFunctionAddresses() {
        return
                (glGetActiveAtomicCounterBufferiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveAtomicCounterBufferiv")) != 0;
    }

    private boolean ARB_shader_image_load_store_initNativeFunctionAddresses() {
        return
                (glBindImageTexture = GL.getFunctionProvider().getFunctionAddress("glBindImageTexture")) != 0 &
                        (glMemoryBarrier = GL.getFunctionProvider().getFunctionAddress("glMemoryBarrier")) != 0;
    }

    private boolean ARB_shader_objects_initNativeFunctionAddresses() {
        return
                (glDeleteObjectARB = GL.getFunctionProvider().getFunctionAddress("glDeleteObjectARB")) != 0 &
                        (glGetHandleARB = GL.getFunctionProvider().getFunctionAddress("glGetHandleARB")) != 0 &
                        (glDetachObjectARB = GL.getFunctionProvider().getFunctionAddress("glDetachObjectARB")) != 0 &
                        (glCreateShaderObjectARB = GL.getFunctionProvider().getFunctionAddress("glCreateShaderObjectARB")) != 0 &
                        (glShaderSourceARB = GL.getFunctionProvider().getFunctionAddress("glShaderSourceARB")) != 0 &
                        (glCompileShaderARB = GL.getFunctionProvider().getFunctionAddress("glCompileShaderARB")) != 0 &
                        (glCreateProgramObjectARB = GL.getFunctionProvider().getFunctionAddress("glCreateProgramObjectARB")) != 0 &
                        (glAttachObjectARB = GL.getFunctionProvider().getFunctionAddress("glAttachObjectARB")) != 0 &
                        (glLinkProgramARB = GL.getFunctionProvider().getFunctionAddress("glLinkProgramARB")) != 0 &
                        (glUseProgramObjectARB = GL.getFunctionProvider().getFunctionAddress("glUseProgramObjectARB")) != 0 &
                        (glValidateProgramARB = GL.getFunctionProvider().getFunctionAddress("glValidateProgramARB")) != 0 &
                        (glUniform1fARB = GL.getFunctionProvider().getFunctionAddress("glUniform1fARB")) != 0 &
                        (glUniform2fARB = GL.getFunctionProvider().getFunctionAddress("glUniform2fARB")) != 0 &
                        (glUniform3fARB = GL.getFunctionProvider().getFunctionAddress("glUniform3fARB")) != 0 &
                        (glUniform4fARB = GL.getFunctionProvider().getFunctionAddress("glUniform4fARB")) != 0 &
                        (glUniform1iARB = GL.getFunctionProvider().getFunctionAddress("glUniform1iARB")) != 0 &
                        (glUniform2iARB = GL.getFunctionProvider().getFunctionAddress("glUniform2iARB")) != 0 &
                        (glUniform3iARB = GL.getFunctionProvider().getFunctionAddress("glUniform3iARB")) != 0 &
                        (glUniform4iARB = GL.getFunctionProvider().getFunctionAddress("glUniform4iARB")) != 0 &
                        (glUniform1fvARB = GL.getFunctionProvider().getFunctionAddress("glUniform1fvARB")) != 0 &
                        (glUniform2fvARB = GL.getFunctionProvider().getFunctionAddress("glUniform2fvARB")) != 0 &
                        (glUniform3fvARB = GL.getFunctionProvider().getFunctionAddress("glUniform3fvARB")) != 0 &
                        (glUniform4fvARB = GL.getFunctionProvider().getFunctionAddress("glUniform4fvARB")) != 0 &
                        (glUniform1ivARB = GL.getFunctionProvider().getFunctionAddress("glUniform1ivARB")) != 0 &
                        (glUniform2ivARB = GL.getFunctionProvider().getFunctionAddress("glUniform2ivARB")) != 0 &
                        (glUniform3ivARB = GL.getFunctionProvider().getFunctionAddress("glUniform3ivARB")) != 0 &
                        (glUniform4ivARB = GL.getFunctionProvider().getFunctionAddress("glUniform4ivARB")) != 0 &
                        (glUniformMatrix2fvARB = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2fvARB")) != 0 &
                        (glUniformMatrix3fvARB = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3fvARB")) != 0 &
                        (glUniformMatrix4fvARB = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4fvARB")) != 0 &
                        (glGetObjectParameterfvARB = GL.getFunctionProvider().getFunctionAddress("glGetObjectParameterfvARB")) != 0 &
                        (glGetObjectParameterivARB = GL.getFunctionProvider().getFunctionAddress("glGetObjectParameterivARB")) != 0 &
                        (glGetInfoLogARB = GL.getFunctionProvider().getFunctionAddress("glGetInfoLogARB")) != 0 &
                        (glGetAttachedObjectsARB = GL.getFunctionProvider().getFunctionAddress("glGetAttachedObjectsARB")) != 0 &
                        (glGetUniformLocationARB = GL.getFunctionProvider().getFunctionAddress("glGetUniformLocationARB")) != 0 &
                        (glGetActiveUniformARB = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformARB")) != 0 &
                        (glGetUniformfvARB = GL.getFunctionProvider().getFunctionAddress("glGetUniformfvARB")) != 0 &
                        (glGetUniformivARB = GL.getFunctionProvider().getFunctionAddress("glGetUniformivARB")) != 0 &
                        (glGetShaderSourceARB = GL.getFunctionProvider().getFunctionAddress("glGetShaderSourceARB")) != 0;
    }

    private boolean ARB_shader_storage_buffer_object_initNativeFunctionAddresses() {
        return
                (glShaderStorageBlockBinding = GL.getFunctionProvider().getFunctionAddress("glShaderStorageBlockBinding")) != 0;
    }

    private boolean ARB_shader_subroutine_initNativeFunctionAddresses() {
        return
                (glGetSubroutineUniformLocation = GL.getFunctionProvider().getFunctionAddress("glGetSubroutineUniformLocation")) != 0 &
                        (glGetSubroutineIndex = GL.getFunctionProvider().getFunctionAddress("glGetSubroutineIndex")) != 0 &
                        (glGetActiveSubroutineUniformiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveSubroutineUniformiv")) != 0 &
                        (glGetActiveSubroutineUniformName = GL.getFunctionProvider().getFunctionAddress("glGetActiveSubroutineUniformName")) != 0 &
                        (glGetActiveSubroutineName = GL.getFunctionProvider().getFunctionAddress("glGetActiveSubroutineName")) != 0 &
                        (glUniformSubroutinesuiv = GL.getFunctionProvider().getFunctionAddress("glUniformSubroutinesuiv")) != 0 &
                        (glGetUniformSubroutineuiv = GL.getFunctionProvider().getFunctionAddress("glGetUniformSubroutineuiv")) != 0 &
                        (glGetProgramStageiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramStageiv")) != 0;
    }

    private boolean ARB_shading_language_include_initNativeFunctionAddresses() {
        return
                (glNamedStringARB = GL.getFunctionProvider().getFunctionAddress("glNamedStringARB")) != 0 &
                        (glDeleteNamedStringARB = GL.getFunctionProvider().getFunctionAddress("glDeleteNamedStringARB")) != 0 &
                        (glCompileShaderIncludeARB = GL.getFunctionProvider().getFunctionAddress("glCompileShaderIncludeARB")) != 0 &
                        (glIsNamedStringARB = GL.getFunctionProvider().getFunctionAddress("glIsNamedStringARB")) != 0 &
                        (glGetNamedStringARB = GL.getFunctionProvider().getFunctionAddress("glGetNamedStringARB")) != 0 &
                        (glGetNamedStringivARB = GL.getFunctionProvider().getFunctionAddress("glGetNamedStringivARB")) != 0;
    }

    private boolean ARB_sparse_buffer_initNativeFunctionAddresses() {
        return
                (glBufferPageCommitmentARB = GL.getFunctionProvider().getFunctionAddress("glBufferPageCommitmentARB")) != 0;
    }

    private boolean ARB_sparse_texture_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glTexPageCommitmentARB = GL.getFunctionProvider().getFunctionAddress("glTexPageCommitmentARB")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTexturePageCommitmentEXT = GL.getFunctionProvider().getFunctionAddress("glTexturePageCommitmentEXT")) != 0);
    }

    private boolean ARB_sync_initNativeFunctionAddresses() {
        return
                (glFenceSync = GL.getFunctionProvider().getFunctionAddress("glFenceSync")) != 0 &
                        (glIsSync = GL.getFunctionProvider().getFunctionAddress("glIsSync")) != 0 &
                        (glDeleteSync = GL.getFunctionProvider().getFunctionAddress("glDeleteSync")) != 0 &
                        (glClientWaitSync = GL.getFunctionProvider().getFunctionAddress("glClientWaitSync")) != 0 &
                        (glWaitSync = GL.getFunctionProvider().getFunctionAddress("glWaitSync")) != 0 &
                        (glGetInteger64v = GL.getFunctionProvider().getFunctionAddress("glGetInteger64v")) != 0 &
                        (glGetSynciv = GL.getFunctionProvider().getFunctionAddress("glGetSynciv")) != 0;
    }

    private boolean ARB_tessellation_shader_initNativeFunctionAddresses() {
        return
                (glPatchParameteri = GL.getFunctionProvider().getFunctionAddress("glPatchParameteri")) != 0 &
                        (glPatchParameterfv = GL.getFunctionProvider().getFunctionAddress("glPatchParameterfv")) != 0;
    }

    private boolean ARB_texture_barrier_initNativeFunctionAddresses() {
        return
                (glTextureBarrier = GL.getFunctionProvider().getFunctionAddress("glTextureBarrier")) != 0;
    }

    private boolean ARB_texture_buffer_object_initNativeFunctionAddresses() {
        return
                (glTexBufferARB = GL.getFunctionProvider().getFunctionAddress("glTexBufferARB")) != 0;
    }

    private boolean ARB_texture_buffer_range_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glTexBufferRange = GL.getFunctionProvider().getFunctionAddress("glTexBufferRange")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTextureBufferRangeEXT = GL.getFunctionProvider().getFunctionAddress("glTextureBufferRangeEXT")) != 0);
    }

    private boolean ARB_texture_compression_initNativeFunctionAddresses() {
        return
                (glCompressedTexImage1DARB = GL.getFunctionProvider().getFunctionAddress("glCompressedTexImage1DARB")) != 0 &
                        (glCompressedTexImage2DARB = GL.getFunctionProvider().getFunctionAddress("glCompressedTexImage2DARB")) != 0 &
                        (glCompressedTexImage3DARB = GL.getFunctionProvider().getFunctionAddress("glCompressedTexImage3DARB")) != 0 &
                        (glCompressedTexSubImage1DARB = GL.getFunctionProvider().getFunctionAddress("glCompressedTexSubImage1DARB")) != 0 &
                        (glCompressedTexSubImage2DARB = GL.getFunctionProvider().getFunctionAddress("glCompressedTexSubImage2DARB")) != 0 &
                        (glCompressedTexSubImage3DARB = GL.getFunctionProvider().getFunctionAddress("glCompressedTexSubImage3DARB")) != 0 &
                        (glGetCompressedTexImageARB = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTexImageARB")) != 0;
    }

    private boolean ARB_texture_multisample_initNativeFunctionAddresses() {
        return
                (glTexImage2DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexImage2DMultisample")) != 0 &
                        (glTexImage3DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexImage3DMultisample")) != 0 &
                        (glGetMultisamplefv = GL.getFunctionProvider().getFunctionAddress("glGetMultisamplefv")) != 0 &
                        (glSampleMaski = GL.getFunctionProvider().getFunctionAddress("glSampleMaski")) != 0;
    }

    private boolean ARB_texture_storage_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glTexStorage1D = getFunctionAddress(new String[] {"glTexStorage1D","glTexStorage1DEXT"})) != 0 &
                        (glTexStorage2D = getFunctionAddress(new String[] {"glTexStorage2D","glTexStorage2DEXT"})) != 0 &
                        (glTexStorage3D = getFunctionAddress(new String[] {"glTexStorage3D","glTexStorage3DEXT"})) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTextureStorage1DEXT = getFunctionAddress(new String[] {"glTextureStorage1DEXT","glTextureStorage1DEXTEXT"})) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTextureStorage2DEXT = getFunctionAddress(new String[] {"glTextureStorage2DEXT","glTextureStorage2DEXTEXT"})) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTextureStorage3DEXT = getFunctionAddress(new String[] {"glTextureStorage3DEXT","glTextureStorage3DEXTEXT"})) != 0);
    }

    private boolean ARB_texture_storage_multisample_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glTexStorage2DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexStorage2DMultisample")) != 0 &
                        (glTexStorage3DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexStorage3DMultisample")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTextureStorage2DMultisampleEXT = GL.getFunctionProvider().getFunctionAddress("glTextureStorage2DMultisampleEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glTextureStorage3DMultisampleEXT = GL.getFunctionProvider().getFunctionAddress("glTextureStorage3DMultisampleEXT")) != 0);
    }

    private boolean ARB_texture_view_initNativeFunctionAddresses() {
        return
                (glTextureView = GL.getFunctionProvider().getFunctionAddress("glTextureView")) != 0;
    }

    private boolean ARB_timer_query_initNativeFunctionAddresses() {
        return
                (glQueryCounter = GL.getFunctionProvider().getFunctionAddress("glQueryCounter")) != 0 &
                        (glGetQueryObjecti64v = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjecti64v")) != 0 &
                        (glGetQueryObjectui64v = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectui64v")) != 0;
    }

    private boolean ARB_transform_feedback2_initNativeFunctionAddresses() {
        return
                (glBindTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glBindTransformFeedback")) != 0 &
                        (glDeleteTransformFeedbacks = GL.getFunctionProvider().getFunctionAddress("glDeleteTransformFeedbacks")) != 0 &
                        (glGenTransformFeedbacks = GL.getFunctionProvider().getFunctionAddress("glGenTransformFeedbacks")) != 0 &
                        (glIsTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glIsTransformFeedback")) != 0 &
                        (glPauseTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glPauseTransformFeedback")) != 0 &
                        (glResumeTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glResumeTransformFeedback")) != 0 &
                        (glDrawTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedback")) != 0;
    }

    private boolean ARB_transform_feedback3_initNativeFunctionAddresses() {
        return
                (glDrawTransformFeedbackStream = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackStream")) != 0 &
                        (glBeginQueryIndexed = GL.getFunctionProvider().getFunctionAddress("glBeginQueryIndexed")) != 0 &
                        (glEndQueryIndexed = GL.getFunctionProvider().getFunctionAddress("glEndQueryIndexed")) != 0 &
                        (glGetQueryIndexediv = GL.getFunctionProvider().getFunctionAddress("glGetQueryIndexediv")) != 0;
    }

    private boolean ARB_transform_feedback_instanced_initNativeFunctionAddresses() {
        return
                (glDrawTransformFeedbackInstanced = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackInstanced")) != 0 &
                        (glDrawTransformFeedbackStreamInstanced = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackStreamInstanced")) != 0;
    }

    private boolean ARB_transpose_matrix_initNativeFunctionAddresses() {
        return
                (glLoadTransposeMatrixfARB = GL.getFunctionProvider().getFunctionAddress("glLoadTransposeMatrixfARB")) != 0 &
                        (glMultTransposeMatrixfARB = GL.getFunctionProvider().getFunctionAddress("glMultTransposeMatrixfARB")) != 0;
    }

    private boolean ARB_uniform_buffer_object_initNativeFunctionAddresses() {
        return
                (glGetUniformIndices = GL.getFunctionProvider().getFunctionAddress("glGetUniformIndices")) != 0 &
                        (glGetActiveUniformsiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformsiv")) != 0 &
                        (glGetActiveUniformName = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformName")) != 0 &
                        (glGetUniformBlockIndex = GL.getFunctionProvider().getFunctionAddress("glGetUniformBlockIndex")) != 0 &
                        (glGetActiveUniformBlockiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformBlockiv")) != 0 &
                        (glGetActiveUniformBlockName = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformBlockName")) != 0 &
                        (glBindBufferRange = GL.getFunctionProvider().getFunctionAddress("glBindBufferRange")) != 0 &
                        (glBindBufferBase = GL.getFunctionProvider().getFunctionAddress("glBindBufferBase")) != 0 &
                        (glGetIntegeri_v = GL.getFunctionProvider().getFunctionAddress("glGetIntegeri_v")) != 0 &
                        (glUniformBlockBinding = GL.getFunctionProvider().getFunctionAddress("glUniformBlockBinding")) != 0;
    }

    private boolean ARB_vertex_array_object_initNativeFunctionAddresses() {
        return
                (glBindVertexArray = GL.getFunctionProvider().getFunctionAddress("glBindVertexArray")) != 0 &
                        (glDeleteVertexArrays = GL.getFunctionProvider().getFunctionAddress("glDeleteVertexArrays")) != 0 &
                        (glGenVertexArrays = GL.getFunctionProvider().getFunctionAddress("glGenVertexArrays")) != 0 &
                        (glIsVertexArray = GL.getFunctionProvider().getFunctionAddress("glIsVertexArray")) != 0;
    }

    private boolean ARB_vertex_attrib_64bit_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glVertexAttribL1d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1d")) != 0 &
                        (glVertexAttribL2d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2d")) != 0 &
                        (glVertexAttribL3d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3d")) != 0 &
                        (glVertexAttribL4d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4d")) != 0 &
                        (glVertexAttribL1dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1dv")) != 0 &
                        (glVertexAttribL2dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2dv")) != 0 &
                        (glVertexAttribL3dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3dv")) != 0 &
                        (glVertexAttribL4dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4dv")) != 0 &
                        (glVertexAttribLPointer = GL.getFunctionProvider().getFunctionAddress("glVertexAttribLPointer")) != 0 &
                        (glGetVertexAttribLdv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribLdv")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glVertexArrayVertexAttribLOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexAttribLOffsetEXT")) != 0);
    }

    private boolean ARB_vertex_attrib_binding_initNativeFunctionAddresses() {
        return
                (glBindVertexBuffer = GL.getFunctionProvider().getFunctionAddress("glBindVertexBuffer")) != 0 &
                        (glVertexAttribFormat = GL.getFunctionProvider().getFunctionAddress("glVertexAttribFormat")) != 0 &
                        (glVertexAttribIFormat = GL.getFunctionProvider().getFunctionAddress("glVertexAttribIFormat")) != 0 &
                        (glVertexAttribLFormat = GL.getFunctionProvider().getFunctionAddress("glVertexAttribLFormat")) != 0 &
                        (glVertexAttribBinding = GL.getFunctionProvider().getFunctionAddress("glVertexAttribBinding")) != 0 &
                        (glVertexBindingDivisor = GL.getFunctionProvider().getFunctionAddress("glVertexBindingDivisor")) != 0;
    }

    private boolean ARB_vertex_blend_initNativeFunctionAddresses() {
        return
                (glWeightbvARB = GL.getFunctionProvider().getFunctionAddress("glWeightbvARB")) != 0 &
                        (glWeightsvARB = GL.getFunctionProvider().getFunctionAddress("glWeightsvARB")) != 0 &
                        (glWeightivARB = GL.getFunctionProvider().getFunctionAddress("glWeightivARB")) != 0 &
                        (glWeightfvARB = GL.getFunctionProvider().getFunctionAddress("glWeightfvARB")) != 0 &
                        (glWeightdvARB = GL.getFunctionProvider().getFunctionAddress("glWeightdvARB")) != 0 &
                        (glWeightubvARB = GL.getFunctionProvider().getFunctionAddress("glWeightubvARB")) != 0 &
                        (glWeightusvARB = GL.getFunctionProvider().getFunctionAddress("glWeightusvARB")) != 0 &
                        (glWeightuivARB = GL.getFunctionProvider().getFunctionAddress("glWeightuivARB")) != 0 &
                        (glWeightPointerARB = GL.getFunctionProvider().getFunctionAddress("glWeightPointerARB")) != 0 &
                        (glVertexBlendARB = GL.getFunctionProvider().getFunctionAddress("glVertexBlendARB")) != 0;
    }

    private boolean ARB_vertex_program_initNativeFunctionAddresses() {
        return
                (glVertexAttrib1sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1sARB")) != 0 &
                        (glVertexAttrib1fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1fARB")) != 0 &
                        (glVertexAttrib1dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1dARB")) != 0 &
                        (glVertexAttrib2sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2sARB")) != 0 &
                        (glVertexAttrib2fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2fARB")) != 0 &
                        (glVertexAttrib2dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2dARB")) != 0 &
                        (glVertexAttrib3sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3sARB")) != 0 &
                        (glVertexAttrib3fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3fARB")) != 0 &
                        (glVertexAttrib3dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3dARB")) != 0 &
                        (glVertexAttrib4sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4sARB")) != 0 &
                        (glVertexAttrib4fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4fARB")) != 0 &
                        (glVertexAttrib4dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4dARB")) != 0 &
                        (glVertexAttrib4NubARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4NubARB")) != 0 &
                        (glVertexAttribPointerARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttribPointerARB")) != 0 &
                        (glEnableVertexAttribArrayARB = GL.getFunctionProvider().getFunctionAddress("glEnableVertexAttribArrayARB")) != 0 &
                        (glDisableVertexAttribArrayARB = GL.getFunctionProvider().getFunctionAddress("glDisableVertexAttribArrayARB")) != 0 &
                        (glGetVertexAttribfvARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribfvARB")) != 0 &
                        (glGetVertexAttribdvARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribdvARB")) != 0 &
                        (glGetVertexAttribivARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribivARB")) != 0 &
                        (glGetVertexAttribPointervARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribPointervARB")) != 0;
    }

    private boolean ARB_vertex_shader_initNativeFunctionAddresses() {
        return
                (glVertexAttrib1sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1sARB")) != 0 &
                        (glVertexAttrib1fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1fARB")) != 0 &
                        (glVertexAttrib1dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1dARB")) != 0 &
                        (glVertexAttrib2sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2sARB")) != 0 &
                        (glVertexAttrib2fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2fARB")) != 0 &
                        (glVertexAttrib2dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2dARB")) != 0 &
                        (glVertexAttrib3sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3sARB")) != 0 &
                        (glVertexAttrib3fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3fARB")) != 0 &
                        (glVertexAttrib3dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3dARB")) != 0 &
                        (glVertexAttrib4sARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4sARB")) != 0 &
                        (glVertexAttrib4fARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4fARB")) != 0 &
                        (glVertexAttrib4dARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4dARB")) != 0 &
                        (glVertexAttrib4NubARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4NubARB")) != 0 &
                        (glVertexAttribPointerARB = GL.getFunctionProvider().getFunctionAddress("glVertexAttribPointerARB")) != 0 &
                        (glEnableVertexAttribArrayARB = GL.getFunctionProvider().getFunctionAddress("glEnableVertexAttribArrayARB")) != 0 &
                        (glDisableVertexAttribArrayARB = GL.getFunctionProvider().getFunctionAddress("glDisableVertexAttribArrayARB")) != 0 &
                        (glBindAttribLocationARB = GL.getFunctionProvider().getFunctionAddress("glBindAttribLocationARB")) != 0 &
                        (glGetActiveAttribARB = GL.getFunctionProvider().getFunctionAddress("glGetActiveAttribARB")) != 0 &
                        (glGetAttribLocationARB = GL.getFunctionProvider().getFunctionAddress("glGetAttribLocationARB")) != 0 &
                        (glGetVertexAttribfvARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribfvARB")) != 0 &
                        (glGetVertexAttribdvARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribdvARB")) != 0 &
                        (glGetVertexAttribivARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribivARB")) != 0 &
                        (glGetVertexAttribPointervARB = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribPointervARB")) != 0;
    }

    private boolean ARB_vertex_type_2_10_10_10_rev_initNativeFunctionAddresses() {
        return
                (glVertexP2ui = GL.getFunctionProvider().getFunctionAddress("glVertexP2ui")) != 0 &
                        (glVertexP3ui = GL.getFunctionProvider().getFunctionAddress("glVertexP3ui")) != 0 &
                        (glVertexP4ui = GL.getFunctionProvider().getFunctionAddress("glVertexP4ui")) != 0 &
                        (glVertexP2uiv = GL.getFunctionProvider().getFunctionAddress("glVertexP2uiv")) != 0 &
                        (glVertexP3uiv = GL.getFunctionProvider().getFunctionAddress("glVertexP3uiv")) != 0 &
                        (glVertexP4uiv = GL.getFunctionProvider().getFunctionAddress("glVertexP4uiv")) != 0 &
                        (glTexCoordP1ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP1ui")) != 0 &
                        (glTexCoordP2ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP2ui")) != 0 &
                        (glTexCoordP3ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP3ui")) != 0 &
                        (glTexCoordP4ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP4ui")) != 0 &
                        (glTexCoordP1uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP1uiv")) != 0 &
                        (glTexCoordP2uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP2uiv")) != 0 &
                        (glTexCoordP3uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP3uiv")) != 0 &
                        (glTexCoordP4uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP4uiv")) != 0 &
                        (glMultiTexCoordP1ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP1ui")) != 0 &
                        (glMultiTexCoordP2ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP2ui")) != 0 &
                        (glMultiTexCoordP3ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP3ui")) != 0 &
                        (glMultiTexCoordP4ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP4ui")) != 0 &
                        (glMultiTexCoordP1uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP1uiv")) != 0 &
                        (glMultiTexCoordP2uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP2uiv")) != 0 &
                        (glMultiTexCoordP3uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP3uiv")) != 0 &
                        (glMultiTexCoordP4uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP4uiv")) != 0 &
                        (glNormalP3ui = GL.getFunctionProvider().getFunctionAddress("glNormalP3ui")) != 0 &
                        (glNormalP3uiv = GL.getFunctionProvider().getFunctionAddress("glNormalP3uiv")) != 0 &
                        (glColorP3ui = GL.getFunctionProvider().getFunctionAddress("glColorP3ui")) != 0 &
                        (glColorP4ui = GL.getFunctionProvider().getFunctionAddress("glColorP4ui")) != 0 &
                        (glColorP3uiv = GL.getFunctionProvider().getFunctionAddress("glColorP3uiv")) != 0 &
                        (glColorP4uiv = GL.getFunctionProvider().getFunctionAddress("glColorP4uiv")) != 0 &
                        (glSecondaryColorP3ui = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorP3ui")) != 0 &
                        (glSecondaryColorP3uiv = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorP3uiv")) != 0 &
                        (glVertexAttribP1ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP1ui")) != 0 &
                        (glVertexAttribP2ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP2ui")) != 0 &
                        (glVertexAttribP3ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP3ui")) != 0 &
                        (glVertexAttribP4ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP4ui")) != 0 &
                        (glVertexAttribP1uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP1uiv")) != 0 &
                        (glVertexAttribP2uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP2uiv")) != 0 &
                        (glVertexAttribP3uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP3uiv")) != 0 &
                        (glVertexAttribP4uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP4uiv")) != 0;
    }

    private boolean ARB_viewport_array_initNativeFunctionAddresses() {
        return
                (glViewportArrayv = GL.getFunctionProvider().getFunctionAddress("glViewportArrayv")) != 0 &
                        (glViewportIndexedf = GL.getFunctionProvider().getFunctionAddress("glViewportIndexedf")) != 0 &
                        (glViewportIndexedfv = GL.getFunctionProvider().getFunctionAddress("glViewportIndexedfv")) != 0 &
                        (glScissorArrayv = GL.getFunctionProvider().getFunctionAddress("glScissorArrayv")) != 0 &
                        (glScissorIndexed = GL.getFunctionProvider().getFunctionAddress("glScissorIndexed")) != 0 &
                        (glScissorIndexedv = GL.getFunctionProvider().getFunctionAddress("glScissorIndexedv")) != 0 &
                        (glDepthRangeArrayv = GL.getFunctionProvider().getFunctionAddress("glDepthRangeArrayv")) != 0 &
                        (glDepthRangeIndexed = GL.getFunctionProvider().getFunctionAddress("glDepthRangeIndexed")) != 0 &
                        (glGetFloati_v = GL.getFunctionProvider().getFunctionAddress("glGetFloati_v")) != 0 &
                        (glGetDoublei_v = GL.getFunctionProvider().getFunctionAddress("glGetDoublei_v")) != 0 &
                        (glGetIntegerIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetIntegerIndexedvEXT")) != 0 &
                        (glEnableIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glEnableIndexedEXT")) != 0 &
                        (glDisableIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glDisableIndexedEXT")) != 0 &
                        (glIsEnabledIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glIsEnabledIndexedEXT")) != 0;
    }

    private boolean ARB_window_pos_initNativeFunctionAddresses(boolean forwardCompatible) {
        return
                (forwardCompatible || (glWindowPos2fARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos2fARB")) != 0) &
                        (forwardCompatible || (glWindowPos2dARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos2dARB")) != 0) &
                        (forwardCompatible || (glWindowPos2iARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos2iARB")) != 0) &
                        (forwardCompatible || (glWindowPos2sARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos2sARB")) != 0) &
                        (forwardCompatible || (glWindowPos3fARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos3fARB")) != 0) &
                        (forwardCompatible || (glWindowPos3dARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos3dARB")) != 0) &
                        (forwardCompatible || (glWindowPos3iARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos3iARB")) != 0) &
                        (forwardCompatible || (glWindowPos3sARB = GL.getFunctionProvider().getFunctionAddress("glWindowPos3sARB")) != 0);
    }

    private boolean ATI_draw_buffers_initNativeFunctionAddresses() {
        return
                (glDrawBuffersATI = GL.getFunctionProvider().getFunctionAddress("glDrawBuffersATI")) != 0;
    }

    private boolean ATI_element_array_initNativeFunctionAddresses() {
        return
                (glElementPointerATI = GL.getFunctionProvider().getFunctionAddress("glElementPointerATI")) != 0 &
                        (glDrawElementArrayATI = GL.getFunctionProvider().getFunctionAddress("glDrawElementArrayATI")) != 0 &
                        (glDrawRangeElementArrayATI = GL.getFunctionProvider().getFunctionAddress("glDrawRangeElementArrayATI")) != 0;
    }

    private boolean ATI_envmap_bumpmap_initNativeFunctionAddresses() {
        return
                (glTexBumpParameterfvATI = GL.getFunctionProvider().getFunctionAddress("glTexBumpParameterfvATI")) != 0 &
                        (glTexBumpParameterivATI = GL.getFunctionProvider().getFunctionAddress("glTexBumpParameterivATI")) != 0 &
                        (glGetTexBumpParameterfvATI = GL.getFunctionProvider().getFunctionAddress("glGetTexBumpParameterfvATI")) != 0 &
                        (glGetTexBumpParameterivATI = GL.getFunctionProvider().getFunctionAddress("glGetTexBumpParameterivATI")) != 0;
    }

    private boolean ATI_fragment_shader_initNativeFunctionAddresses() {
        return
                (glGenFragmentShadersATI = GL.getFunctionProvider().getFunctionAddress("glGenFragmentShadersATI")) != 0 &
                        (glBindFragmentShaderATI = GL.getFunctionProvider().getFunctionAddress("glBindFragmentShaderATI")) != 0 &
                        (glDeleteFragmentShaderATI = GL.getFunctionProvider().getFunctionAddress("glDeleteFragmentShaderATI")) != 0 &
                        (glBeginFragmentShaderATI = GL.getFunctionProvider().getFunctionAddress("glBeginFragmentShaderATI")) != 0 &
                        (glEndFragmentShaderATI = GL.getFunctionProvider().getFunctionAddress("glEndFragmentShaderATI")) != 0 &
                        (glPassTexCoordATI = GL.getFunctionProvider().getFunctionAddress("glPassTexCoordATI")) != 0 &
                        (glSampleMapATI = GL.getFunctionProvider().getFunctionAddress("glSampleMapATI")) != 0 &
                        (glColorFragmentOp1ATI = GL.getFunctionProvider().getFunctionAddress("glColorFragmentOp1ATI")) != 0 &
                        (glColorFragmentOp2ATI = GL.getFunctionProvider().getFunctionAddress("glColorFragmentOp2ATI")) != 0 &
                        (glColorFragmentOp3ATI = GL.getFunctionProvider().getFunctionAddress("glColorFragmentOp3ATI")) != 0 &
                        (glAlphaFragmentOp1ATI = GL.getFunctionProvider().getFunctionAddress("glAlphaFragmentOp1ATI")) != 0 &
                        (glAlphaFragmentOp2ATI = GL.getFunctionProvider().getFunctionAddress("glAlphaFragmentOp2ATI")) != 0 &
                        (glAlphaFragmentOp3ATI = GL.getFunctionProvider().getFunctionAddress("glAlphaFragmentOp3ATI")) != 0 &
                        (glSetFragmentShaderConstantATI = GL.getFunctionProvider().getFunctionAddress("glSetFragmentShaderConstantATI")) != 0;
    }

    private boolean ATI_map_object_buffer_initNativeFunctionAddresses() {
        return
                (glMapObjectBufferATI = GL.getFunctionProvider().getFunctionAddress("glMapObjectBufferATI")) != 0 &
                        (glUnmapObjectBufferATI = GL.getFunctionProvider().getFunctionAddress("glUnmapObjectBufferATI")) != 0;
    }

    private boolean ATI_pn_triangles_initNativeFunctionAddresses() {
        return
                (glPNTrianglesfATI = GL.getFunctionProvider().getFunctionAddress("glPNTrianglesfATI")) != 0 &
                        (glPNTrianglesiATI = GL.getFunctionProvider().getFunctionAddress("glPNTrianglesiATI")) != 0;
    }

    private boolean ATI_separate_stencil_initNativeFunctionAddresses() {
        return
                (glStencilOpSeparateATI = GL.getFunctionProvider().getFunctionAddress("glStencilOpSeparateATI")) != 0 &
                        (glStencilFuncSeparateATI = GL.getFunctionProvider().getFunctionAddress("glStencilFuncSeparateATI")) != 0;
    }

    private boolean ATI_vertex_array_object_initNativeFunctionAddresses() {
        return
                (glNewObjectBufferATI = GL.getFunctionProvider().getFunctionAddress("glNewObjectBufferATI")) != 0 &
                        (glIsObjectBufferATI = GL.getFunctionProvider().getFunctionAddress("glIsObjectBufferATI")) != 0 &
                        (glUpdateObjectBufferATI = GL.getFunctionProvider().getFunctionAddress("glUpdateObjectBufferATI")) != 0 &
                        (glGetObjectBufferfvATI = GL.getFunctionProvider().getFunctionAddress("glGetObjectBufferfvATI")) != 0 &
                        (glGetObjectBufferivATI = GL.getFunctionProvider().getFunctionAddress("glGetObjectBufferivATI")) != 0 &
                        (glFreeObjectBufferATI = GL.getFunctionProvider().getFunctionAddress("glFreeObjectBufferATI")) != 0 &
                        (glArrayObjectATI = GL.getFunctionProvider().getFunctionAddress("glArrayObjectATI")) != 0 &
                        (glGetArrayObjectfvATI = GL.getFunctionProvider().getFunctionAddress("glGetArrayObjectfvATI")) != 0 &
                        (glGetArrayObjectivATI = GL.getFunctionProvider().getFunctionAddress("glGetArrayObjectivATI")) != 0 &
                        (glVariantArrayObjectATI = GL.getFunctionProvider().getFunctionAddress("glVariantArrayObjectATI")) != 0 &
                        (glGetVariantArrayObjectfvATI = GL.getFunctionProvider().getFunctionAddress("glGetVariantArrayObjectfvATI")) != 0 &
                        (glGetVariantArrayObjectivATI = GL.getFunctionProvider().getFunctionAddress("glGetVariantArrayObjectivATI")) != 0;
    }

    private boolean ATI_vertex_attrib_array_object_initNativeFunctionAddresses() {
        return
                (glVertexAttribArrayObjectATI = GL.getFunctionProvider().getFunctionAddress("glVertexAttribArrayObjectATI")) != 0 &
                        (glGetVertexAttribArrayObjectfvATI = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribArrayObjectfvATI")) != 0 &
                        (glGetVertexAttribArrayObjectivATI = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribArrayObjectivATI")) != 0;
    }

    private boolean ATI_vertex_streams_initNativeFunctionAddresses() {
        return
                (glVertexStream2fATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream2fATI")) != 0 &
                        (glVertexStream2dATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream2dATI")) != 0 &
                        (glVertexStream2iATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream2iATI")) != 0 &
                        (glVertexStream2sATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream2sATI")) != 0 &
                        (glVertexStream3fATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream3fATI")) != 0 &
                        (glVertexStream3dATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream3dATI")) != 0 &
                        (glVertexStream3iATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream3iATI")) != 0 &
                        (glVertexStream3sATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream3sATI")) != 0 &
                        (glVertexStream4fATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream4fATI")) != 0 &
                        (glVertexStream4dATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream4dATI")) != 0 &
                        (glVertexStream4iATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream4iATI")) != 0 &
                        (glVertexStream4sATI = GL.getFunctionProvider().getFunctionAddress("glVertexStream4sATI")) != 0 &
                        (glNormalStream3bATI = GL.getFunctionProvider().getFunctionAddress("glNormalStream3bATI")) != 0 &
                        (glNormalStream3fATI = GL.getFunctionProvider().getFunctionAddress("glNormalStream3fATI")) != 0 &
                        (glNormalStream3dATI = GL.getFunctionProvider().getFunctionAddress("glNormalStream3dATI")) != 0 &
                        (glNormalStream3iATI = GL.getFunctionProvider().getFunctionAddress("glNormalStream3iATI")) != 0 &
                        (glNormalStream3sATI = GL.getFunctionProvider().getFunctionAddress("glNormalStream3sATI")) != 0 &
                        (glClientActiveVertexStreamATI = GL.getFunctionProvider().getFunctionAddress("glClientActiveVertexStreamATI")) != 0 &
                        (glVertexBlendEnvfATI = GL.getFunctionProvider().getFunctionAddress("glVertexBlendEnvfATI")) != 0 &
                        (glVertexBlendEnviATI = GL.getFunctionProvider().getFunctionAddress("glVertexBlendEnviATI")) != 0;
    }

    private boolean EXT_bindable_uniform_initNativeFunctionAddresses() {
        return
                (glUniformBufferEXT = GL.getFunctionProvider().getFunctionAddress("glUniformBufferEXT")) != 0 &
                        (glGetUniformBufferSizeEXT = GL.getFunctionProvider().getFunctionAddress("glGetUniformBufferSizeEXT")) != 0 &
                        (glGetUniformOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glGetUniformOffsetEXT")) != 0;
    }

    private boolean EXT_blend_color_initNativeFunctionAddresses() {
        return
                (glBlendColorEXT = GL.getFunctionProvider().getFunctionAddress("glBlendColorEXT")) != 0;
    }

    private boolean EXT_blend_equation_separate_initNativeFunctionAddresses() {
        return
                (glBlendEquationSeparateEXT = GL.getFunctionProvider().getFunctionAddress("glBlendEquationSeparateEXT")) != 0;
    }

    private boolean EXT_blend_func_separate_initNativeFunctionAddresses() {
        return
                (glBlendFuncSeparateEXT = GL.getFunctionProvider().getFunctionAddress("glBlendFuncSeparateEXT")) != 0;
    }

    private boolean EXT_blend_minmax_initNativeFunctionAddresses() {
        return
                (glBlendEquationEXT = GL.getFunctionProvider().getFunctionAddress("glBlendEquationEXT")) != 0;
    }

    private boolean EXT_compiled_vertex_array_initNativeFunctionAddresses() {
        return
                (glLockArraysEXT = GL.getFunctionProvider().getFunctionAddress("glLockArraysEXT")) != 0 &
                        (glUnlockArraysEXT = GL.getFunctionProvider().getFunctionAddress("glUnlockArraysEXT")) != 0;
    }

    private boolean EXT_depth_bounds_test_initNativeFunctionAddresses() {
        return
                (glDepthBoundsEXT = GL.getFunctionProvider().getFunctionAddress("glDepthBoundsEXT")) != 0;
    }

    private boolean EXT_direct_state_access_initNativeFunctionAddresses(boolean forwardCompatible,Set<String> supported_extensions) {
        return
                (forwardCompatible || (glClientAttribDefaultEXT = GL.getFunctionProvider().getFunctionAddress("glClientAttribDefaultEXT")) != 0) &
                        (forwardCompatible || (glPushClientAttribDefaultEXT = GL.getFunctionProvider().getFunctionAddress("glPushClientAttribDefaultEXT")) != 0) &
                        (forwardCompatible || (glMatrixLoadfEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixLoadfEXT")) != 0) &
                        (forwardCompatible || (glMatrixLoaddEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixLoaddEXT")) != 0) &
                        (forwardCompatible || (glMatrixMultfEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixMultfEXT")) != 0) &
                        (forwardCompatible || (glMatrixMultdEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixMultdEXT")) != 0) &
                        (forwardCompatible || (glMatrixLoadIdentityEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixLoadIdentityEXT")) != 0) &
                        (forwardCompatible || (glMatrixRotatefEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixRotatefEXT")) != 0) &
                        (forwardCompatible || (glMatrixRotatedEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixRotatedEXT")) != 0) &
                        (forwardCompatible || (glMatrixScalefEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixScalefEXT")) != 0) &
                        (forwardCompatible || (glMatrixScaledEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixScaledEXT")) != 0) &
                        (forwardCompatible || (glMatrixTranslatefEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixTranslatefEXT")) != 0) &
                        (forwardCompatible || (glMatrixTranslatedEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixTranslatedEXT")) != 0) &
                        (forwardCompatible || (glMatrixOrthoEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixOrthoEXT")) != 0) &
                        (forwardCompatible || (glMatrixFrustumEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixFrustumEXT")) != 0) &
                        (forwardCompatible || (glMatrixPushEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixPushEXT")) != 0) &
                        (forwardCompatible || (glMatrixPopEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixPopEXT")) != 0) &
                        (glTextureParameteriEXT = GL.getFunctionProvider().getFunctionAddress("glTextureParameteriEXT")) != 0 &
                        (glTextureParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glTextureParameterivEXT")) != 0 &
                        (glTextureParameterfEXT = GL.getFunctionProvider().getFunctionAddress("glTextureParameterfEXT")) != 0 &
                        (glTextureParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glTextureParameterfvEXT")) != 0 &
                        (glTextureImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glTextureImage1DEXT")) != 0 &
                        (glTextureImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glTextureImage2DEXT")) != 0 &
                        (glTextureSubImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage1DEXT")) != 0 &
                        (glTextureSubImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage2DEXT")) != 0 &
                        (glCopyTextureImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyTextureImage1DEXT")) != 0 &
                        (glCopyTextureImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyTextureImage2DEXT")) != 0 &
                        (glCopyTextureSubImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage1DEXT")) != 0 &
                        (glCopyTextureSubImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage2DEXT")) != 0 &
                        (glGetTextureImageEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureImageEXT")) != 0 &
                        (glGetTextureParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterfvEXT")) != 0 &
                        (glGetTextureParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterivEXT")) != 0 &
                        (glGetTextureLevelParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureLevelParameterfvEXT")) != 0 &
                        (glGetTextureLevelParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureLevelParameterivEXT")) != 0 &
                        (!supported_extensions.contains("OpenGL12") || (glTextureImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glTextureImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL12") || (glTextureSubImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL12") || (glCopyTextureSubImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glBindMultiTextureEXT = GL.getFunctionProvider().getFunctionAddress("glBindMultiTextureEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexCoordPointerEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordPointerEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexEnvfEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexEnvfEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexEnvfvEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexEnvfvEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexEnviEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexEnviEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexEnvivEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexEnvivEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexGendEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexGendEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexGendvEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexGendvEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexGenfEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexGenfEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexGenfvEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexGenfvEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexGeniEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexGeniEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMultiTexGenivEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexGenivEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glGetMultiTexEnvfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexEnvfvEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glGetMultiTexEnvivEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexEnvivEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glGetMultiTexGendvEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexGendvEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glGetMultiTexGenfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexGenfvEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glGetMultiTexGenivEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexGenivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexParameteriEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexParameteriEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexParameterivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexParameterfEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexParameterfEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexParameterfvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexSubImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexSubImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexSubImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexSubImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCopyMultiTexImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyMultiTexImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCopyMultiTexImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyMultiTexImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCopyMultiTexSubImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyMultiTexSubImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCopyMultiTexSubImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyMultiTexSubImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetMultiTexImageEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexImageEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetMultiTexParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexParameterfvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetMultiTexParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexParameterivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetMultiTexLevelParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexLevelParameterfvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetMultiTexLevelParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexLevelParameterivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glMultiTexSubImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexSubImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCopyMultiTexSubImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glCopyMultiTexSubImage3DEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glEnableClientStateIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glEnableClientStateIndexedEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glDisableClientStateIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glDisableClientStateIndexedEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetFloatIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetFloatIndexedvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetDoubleIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetDoubleIndexedvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetPointerIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetPointerIndexedvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glEnableIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glEnableIndexedEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glDisableIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glDisableIndexedEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glIsEnabledIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glIsEnabledIndexedEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetIntegerIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetIntegerIndexedvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetBooleanIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetBooleanIndexedvEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glNamedProgramStringEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramStringEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glNamedProgramLocalParameter4dEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameter4dEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glNamedProgramLocalParameter4dvEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameter4dvEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glNamedProgramLocalParameter4fEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameter4fEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glNamedProgramLocalParameter4fvEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameter4fvEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glGetNamedProgramLocalParameterdvEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedProgramLocalParameterdvEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glGetNamedProgramLocalParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedProgramLocalParameterfvEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glGetNamedProgramivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedProgramivEXT")) != 0) &
                        (!supported_extensions.contains("GL_ARB_vertex_program") || (glGetNamedProgramStringEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedProgramStringEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedTextureImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedTextureImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedTextureImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedTextureSubImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedTextureSubImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedTextureSubImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetCompressedTextureImageEXT = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTextureImageEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedMultiTexImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedMultiTexImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedMultiTexImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedMultiTexImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedMultiTexImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedMultiTexImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedMultiTexSubImage3DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedMultiTexSubImage3DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedMultiTexSubImage2DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedMultiTexSubImage2DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glCompressedMultiTexSubImage1DEXT = GL.getFunctionProvider().getFunctionAddress("glCompressedMultiTexSubImage1DEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL13") || (glGetCompressedMultiTexImageEXT = GL.getFunctionProvider().getFunctionAddress("glGetCompressedMultiTexImageEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMatrixLoadTransposefEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixLoadTransposefEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMatrixLoadTransposedEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixLoadTransposedEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMatrixMultTransposefEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixMultTransposefEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL13") || (glMatrixMultTransposedEXT = GL.getFunctionProvider().getFunctionAddress("glMatrixMultTransposedEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glNamedBufferDataEXT = GL.getFunctionProvider().getFunctionAddress("glNamedBufferDataEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glNamedBufferSubDataEXT = GL.getFunctionProvider().getFunctionAddress("glNamedBufferSubDataEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glMapNamedBufferEXT = GL.getFunctionProvider().getFunctionAddress("glMapNamedBufferEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glUnmapNamedBufferEXT = GL.getFunctionProvider().getFunctionAddress("glUnmapNamedBufferEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glGetNamedBufferParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferParameterivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glGetNamedBufferPointervEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferPointervEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL15") || (glGetNamedBufferSubDataEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferSubDataEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform1fEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1fEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform2fEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2fEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform3fEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3fEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform4fEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4fEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform1iEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1iEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform2iEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2iEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform3iEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3iEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform4iEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4iEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform1fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform2fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform3fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform4fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform1ivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1ivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform2ivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2ivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform3ivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3ivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniform4ivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4ivEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniformMatrix2fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniformMatrix3fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL20") || (glProgramUniformMatrix4fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL21") || (glProgramUniformMatrix2x3fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x3fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL21") || (glProgramUniformMatrix3x2fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x2fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL21") || (glProgramUniformMatrix2x4fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x4fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL21") || (glProgramUniformMatrix4x2fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x2fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL21") || (glProgramUniformMatrix3x4fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x4fvEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL21") || (glProgramUniformMatrix4x3fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x3fvEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_buffer_object") || (glTextureBufferEXT = GL.getFunctionProvider().getFunctionAddress("glTextureBufferEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_buffer_object") || (glMultiTexBufferEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexBufferEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glTextureParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glTextureParameterIivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glTextureParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glTextureParameterIuivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glGetTextureParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterIivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glGetTextureParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterIuivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glMultiTexParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexParameterIivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glMultiTexParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexParameterIuivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glGetMultiTexParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexParameterIivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_texture_integer") || (glGetMultiTexParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glGetMultiTexParameterIuivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform1uiEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1uiEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform2uiEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2uiEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform3uiEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3uiEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform4uiEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4uiEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform1uivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1uivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform2uivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2uivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform3uivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3uivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_shader4") || (glProgramUniform4uivEXT = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4uivEXT")) != 0) &
                        (!supported_extensions.contains("GL_EXT_gpu_program_parameters") || (glNamedProgramLocalParameters4fvEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameters4fvEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glNamedProgramLocalParameterI4iEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameterI4iEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glNamedProgramLocalParameterI4ivEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameterI4ivEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glNamedProgramLocalParametersI4ivEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParametersI4ivEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glNamedProgramLocalParameterI4uiEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameterI4uiEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glNamedProgramLocalParameterI4uivEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParameterI4uivEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glNamedProgramLocalParametersI4uivEXT = GL.getFunctionProvider().getFunctionAddress("glNamedProgramLocalParametersI4uivEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glGetNamedProgramLocalParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedProgramLocalParameterIivEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_gpu_program4") || (glGetNamedProgramLocalParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedProgramLocalParameterIuivEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glNamedRenderbufferStorageEXT = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorageEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glGetNamedRenderbufferParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedRenderbufferParameterivEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_multisample")) || (glNamedRenderbufferStorageMultisampleEXT = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorageMultisampleEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_framebuffer_multisample_coverage") || (glNamedRenderbufferStorageMultisampleCoverageEXT = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorageMultisampleCoverageEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glCheckNamedFramebufferStatusEXT = GL.getFunctionProvider().getFunctionAddress("glCheckNamedFramebufferStatusEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glNamedFramebufferTexture1DEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTexture1DEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glNamedFramebufferTexture2DEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTexture2DEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glNamedFramebufferTexture3DEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTexture3DEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glNamedFramebufferRenderbufferEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferRenderbufferEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glGetNamedFramebufferAttachmentParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetNamedFramebufferAttachmentParameterivEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glGenerateTextureMipmapEXT = GL.getFunctionProvider().getFunctionAddress("glGenerateTextureMipmapEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glGenerateMultiTexMipmapEXT = GL.getFunctionProvider().getFunctionAddress("glGenerateMultiTexMipmapEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glFramebufferDrawBufferEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferDrawBufferEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glFramebufferDrawBuffersEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferDrawBuffersEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glFramebufferReadBufferEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferReadBufferEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL30") || supported_extensions.contains("GL_EXT_framebuffer_object")) || (glGetFramebufferParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetFramebufferParameterivEXT")) != 0) &
                        (!(supported_extensions.contains("OpenGL31") || supported_extensions.contains("GL_ARB_copy_buffer")) || (glNamedCopyBufferSubDataEXT = GL.getFunctionProvider().getFunctionAddress("glNamedCopyBufferSubDataEXT")) != 0) &
                        (!(supported_extensions.contains("GL_EXT_geometry_shader4") || supported_extensions.contains("GL_NV_geometry_program4")) || (glNamedFramebufferTextureEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTextureEXT")) != 0) &
                        (!(supported_extensions.contains("GL_EXT_geometry_shader4") || supported_extensions.contains("GL_NV_geometry_program4")) || (glNamedFramebufferTextureLayerEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTextureLayerEXT")) != 0) &
                        (!(supported_extensions.contains("GL_EXT_geometry_shader4") || supported_extensions.contains("GL_NV_geometry_program4")) || (glNamedFramebufferTextureFaceEXT = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTextureFaceEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_explicit_multisample") || (glTextureRenderbufferEXT = GL.getFunctionProvider().getFunctionAddress("glTextureRenderbufferEXT")) != 0) &
                        (!supported_extensions.contains("GL_NV_explicit_multisample") || (glMultiTexRenderbufferEXT = GL.getFunctionProvider().getFunctionAddress("glMultiTexRenderbufferEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayVertexOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayColorOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayColorOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayEdgeFlagOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayEdgeFlagOffsetEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glVertexArrayIndexOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayIndexOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayNormalOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayNormalOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayTexCoordOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayTexCoordOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayMultiTexCoordOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayMultiTexCoordOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArrayFogCoordOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayFogCoordOffsetEXT")) != 0) &
                        (forwardCompatible || !supported_extensions.contains("OpenGL30") || (glVertexArraySecondaryColorOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArraySecondaryColorOffsetEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glVertexArrayVertexAttribOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexAttribOffsetEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glVertexArrayVertexAttribIOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexAttribIOffsetEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glEnableVertexArrayEXT = GL.getFunctionProvider().getFunctionAddress("glEnableVertexArrayEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glDisableVertexArrayEXT = GL.getFunctionProvider().getFunctionAddress("glDisableVertexArrayEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glEnableVertexArrayAttribEXT = GL.getFunctionProvider().getFunctionAddress("glEnableVertexArrayAttribEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glDisableVertexArrayAttribEXT = GL.getFunctionProvider().getFunctionAddress("glDisableVertexArrayAttribEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glGetVertexArrayIntegervEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayIntegervEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glGetVertexArrayPointervEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayPointervEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glGetVertexArrayIntegeri_vEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayIntegeri_vEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glGetVertexArrayPointeri_vEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayPointeri_vEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glMapNamedBufferRangeEXT = GL.getFunctionProvider().getFunctionAddress("glMapNamedBufferRangeEXT")) != 0) &
                        (!supported_extensions.contains("OpenGL30") || (glFlushMappedNamedBufferRangeEXT = GL.getFunctionProvider().getFunctionAddress("glFlushMappedNamedBufferRangeEXT")) != 0);
    }

    private boolean EXT_draw_buffers2_initNativeFunctionAddresses() {
        return
                (glColorMaskIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glColorMaskIndexedEXT")) != 0 &
                        (glGetBooleanIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetBooleanIndexedvEXT")) != 0 &
                        (glGetIntegerIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetIntegerIndexedvEXT")) != 0 &
                        (glEnableIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glEnableIndexedEXT")) != 0 &
                        (glDisableIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glDisableIndexedEXT")) != 0 &
                        (glIsEnabledIndexedEXT = GL.getFunctionProvider().getFunctionAddress("glIsEnabledIndexedEXT")) != 0;
    }

    private boolean EXT_draw_instanced_initNativeFunctionAddresses() {
        return
                (glDrawArraysInstancedEXT = GL.getFunctionProvider().getFunctionAddress("glDrawArraysInstancedEXT")) != 0 &
                        (glDrawElementsInstancedEXT = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedEXT")) != 0;
    }

    private boolean EXT_draw_range_elements_initNativeFunctionAddresses() {
        return
                (glDrawRangeElementsEXT = GL.getFunctionProvider().getFunctionAddress("glDrawRangeElementsEXT")) != 0;
    }

    private boolean EXT_fog_coord_initNativeFunctionAddresses() {
        return
                (glFogCoordfEXT = GL.getFunctionProvider().getFunctionAddress("glFogCoordfEXT")) != 0 &
                        (glFogCoorddEXT = GL.getFunctionProvider().getFunctionAddress("glFogCoorddEXT")) != 0 &
                        (glFogCoordPointerEXT = GL.getFunctionProvider().getFunctionAddress("glFogCoordPointerEXT")) != 0;
    }

    private boolean EXT_framebuffer_blit_initNativeFunctionAddresses() {
        return
                (glBlitFramebufferEXT = GL.getFunctionProvider().getFunctionAddress("glBlitFramebufferEXT")) != 0;
    }

    private boolean EXT_framebuffer_multisample_initNativeFunctionAddresses() {
        return
                (glRenderbufferStorageMultisampleEXT = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorageMultisampleEXT")) != 0;
    }

    private boolean EXT_framebuffer_object_initNativeFunctionAddresses() {
        return
                (glIsRenderbufferEXT = GL.getFunctionProvider().getFunctionAddress("glIsRenderbufferEXT")) != 0 &
                        (glBindRenderbufferEXT = GL.getFunctionProvider().getFunctionAddress("glBindRenderbufferEXT")) != 0 &
                        (glDeleteRenderbuffersEXT = GL.getFunctionProvider().getFunctionAddress("glDeleteRenderbuffersEXT")) != 0 &
                        (glGenRenderbuffersEXT = GL.getFunctionProvider().getFunctionAddress("glGenRenderbuffersEXT")) != 0 &
                        (glRenderbufferStorageEXT = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorageEXT")) != 0 &
                        (glGetRenderbufferParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetRenderbufferParameterivEXT")) != 0 &
                        (glIsFramebufferEXT = GL.getFunctionProvider().getFunctionAddress("glIsFramebufferEXT")) != 0 &
                        (glBindFramebufferEXT = GL.getFunctionProvider().getFunctionAddress("glBindFramebufferEXT")) != 0 &
                        (glDeleteFramebuffersEXT = GL.getFunctionProvider().getFunctionAddress("glDeleteFramebuffersEXT")) != 0 &
                        (glGenFramebuffersEXT = GL.getFunctionProvider().getFunctionAddress("glGenFramebuffersEXT")) != 0 &
                        (glCheckFramebufferStatusEXT = GL.getFunctionProvider().getFunctionAddress("glCheckFramebufferStatusEXT")) != 0 &
                        (glFramebufferTexture1DEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture1DEXT")) != 0 &
                        (glFramebufferTexture2DEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture2DEXT")) != 0 &
                        (glFramebufferTexture3DEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture3DEXT")) != 0 &
                        (glFramebufferRenderbufferEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferRenderbufferEXT")) != 0 &
                        (glGetFramebufferAttachmentParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetFramebufferAttachmentParameterivEXT")) != 0 &
                        (glGenerateMipmapEXT = GL.getFunctionProvider().getFunctionAddress("glGenerateMipmapEXT")) != 0;
    }

    private boolean EXT_geometry_shader4_initNativeFunctionAddresses() {
        return
                (glProgramParameteriEXT = GL.getFunctionProvider().getFunctionAddress("glProgramParameteriEXT")) != 0 &
                        (glFramebufferTextureEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureEXT")) != 0 &
                        (glFramebufferTextureLayerEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureLayerEXT")) != 0 &
                        (glFramebufferTextureFaceEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureFaceEXT")) != 0;
    }

    private boolean EXT_gpu_program_parameters_initNativeFunctionAddresses() {
        return
                (glProgramEnvParameters4fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameters4fvEXT")) != 0 &
                        (glProgramLocalParameters4fvEXT = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameters4fvEXT")) != 0;
    }

    private boolean EXT_gpu_shader4_initNativeFunctionAddresses() {
        return
                (glVertexAttribI1iEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1iEXT")) != 0 &
                        (glVertexAttribI2iEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2iEXT")) != 0 &
                        (glVertexAttribI3iEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3iEXT")) != 0 &
                        (glVertexAttribI4iEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4iEXT")) != 0 &
                        (glVertexAttribI1uiEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1uiEXT")) != 0 &
                        (glVertexAttribI2uiEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2uiEXT")) != 0 &
                        (glVertexAttribI3uiEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3uiEXT")) != 0 &
                        (glVertexAttribI4uiEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4uiEXT")) != 0 &
                        (glVertexAttribI1ivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1ivEXT")) != 0 &
                        (glVertexAttribI2ivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2ivEXT")) != 0 &
                        (glVertexAttribI3ivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3ivEXT")) != 0 &
                        (glVertexAttribI4ivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4ivEXT")) != 0 &
                        (glVertexAttribI1uivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1uivEXT")) != 0 &
                        (glVertexAttribI2uivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2uivEXT")) != 0 &
                        (glVertexAttribI3uivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3uivEXT")) != 0 &
                        (glVertexAttribI4uivEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4uivEXT")) != 0 &
                        (glVertexAttribI4bvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4bvEXT")) != 0 &
                        (glVertexAttribI4svEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4svEXT")) != 0 &
                        (glVertexAttribI4ubvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4ubvEXT")) != 0 &
                        (glVertexAttribI4usvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4usvEXT")) != 0 &
                        (glVertexAttribIPointerEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribIPointerEXT")) != 0 &
                        (glGetVertexAttribIivEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribIivEXT")) != 0 &
                        (glGetVertexAttribIuivEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribIuivEXT")) != 0 &
                        (glUniform1uiEXT = GL.getFunctionProvider().getFunctionAddress("glUniform1uiEXT")) != 0 &
                        (glUniform2uiEXT = GL.getFunctionProvider().getFunctionAddress("glUniform2uiEXT")) != 0 &
                        (glUniform3uiEXT = GL.getFunctionProvider().getFunctionAddress("glUniform3uiEXT")) != 0 &
                        (glUniform4uiEXT = GL.getFunctionProvider().getFunctionAddress("glUniform4uiEXT")) != 0 &
                        (glUniform1uivEXT = GL.getFunctionProvider().getFunctionAddress("glUniform1uivEXT")) != 0 &
                        (glUniform2uivEXT = GL.getFunctionProvider().getFunctionAddress("glUniform2uivEXT")) != 0 &
                        (glUniform3uivEXT = GL.getFunctionProvider().getFunctionAddress("glUniform3uivEXT")) != 0 &
                        (glUniform4uivEXT = GL.getFunctionProvider().getFunctionAddress("glUniform4uivEXT")) != 0 &
                        (glGetUniformuivEXT = GL.getFunctionProvider().getFunctionAddress("glGetUniformuivEXT")) != 0 &
                        (glBindFragDataLocationEXT = GL.getFunctionProvider().getFunctionAddress("glBindFragDataLocationEXT")) != 0 &
                        (glGetFragDataLocationEXT = GL.getFunctionProvider().getFunctionAddress("glGetFragDataLocationEXT")) != 0;
    }

    private boolean EXT_multi_draw_arrays_initNativeFunctionAddresses() {
        return
                (glMultiDrawArraysEXT = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArraysEXT")) != 0;
    }

    private boolean EXT_paletted_texture_initNativeFunctionAddresses() {
        return
                (glColorTableEXT = GL.getFunctionProvider().getFunctionAddress("glColorTableEXT")) != 0 &
                        (glColorSubTableEXT = GL.getFunctionProvider().getFunctionAddress("glColorSubTableEXT")) != 0 &
                        (glGetColorTableEXT = GL.getFunctionProvider().getFunctionAddress("glGetColorTableEXT")) != 0 &
                        (glGetColorTableParameterivEXT = GL.getFunctionProvider().getFunctionAddress("glGetColorTableParameterivEXT")) != 0 &
                        (glGetColorTableParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glGetColorTableParameterfvEXT")) != 0;
    }

    private boolean EXT_point_parameters_initNativeFunctionAddresses() {
        return
                (glPointParameterfEXT = GL.getFunctionProvider().getFunctionAddress("glPointParameterfEXT")) != 0 &
                        (glPointParameterfvEXT = GL.getFunctionProvider().getFunctionAddress("glPointParameterfvEXT")) != 0;
    }

    private boolean EXT_provoking_vertex_initNativeFunctionAddresses() {
        return
                (glProvokingVertexEXT = GL.getFunctionProvider().getFunctionAddress("glProvokingVertexEXT")) != 0;
    }

    private boolean EXT_secondary_color_initNativeFunctionAddresses() {
        return
                (glSecondaryColor3bEXT = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3bEXT")) != 0 &
                        (glSecondaryColor3fEXT = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3fEXT")) != 0 &
                        (glSecondaryColor3dEXT = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3dEXT")) != 0 &
                        (glSecondaryColor3ubEXT = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3ubEXT")) != 0 &
                        (glSecondaryColorPointerEXT = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorPointerEXT")) != 0;
    }

    private boolean EXT_separate_shader_objects_initNativeFunctionAddresses() {
        return
                (glUseShaderProgramEXT = GL.getFunctionProvider().getFunctionAddress("glUseShaderProgramEXT")) != 0 &
                        (glActiveProgramEXT = GL.getFunctionProvider().getFunctionAddress("glActiveProgramEXT")) != 0 &
                        (glCreateShaderProgramEXT = GL.getFunctionProvider().getFunctionAddress("glCreateShaderProgramEXT")) != 0;
    }

    private boolean EXT_shader_image_load_store_initNativeFunctionAddresses() {
        return
                (glBindImageTextureEXT = GL.getFunctionProvider().getFunctionAddress("glBindImageTextureEXT")) != 0 &
                        (glMemoryBarrierEXT = GL.getFunctionProvider().getFunctionAddress("glMemoryBarrierEXT")) != 0;
    }

    private boolean EXT_stencil_clear_tag_initNativeFunctionAddresses() {
        return
                (glStencilClearTagEXT = GL.getFunctionProvider().getFunctionAddress("glStencilClearTagEXT")) != 0;
    }

    private boolean EXT_stencil_two_side_initNativeFunctionAddresses() {
        return
                (glActiveStencilFaceEXT = GL.getFunctionProvider().getFunctionAddress("glActiveStencilFaceEXT")) != 0;
    }

    private boolean EXT_texture_array_initNativeFunctionAddresses() {
        return
                (glFramebufferTextureLayerEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureLayerEXT")) != 0;
    }

    private boolean EXT_texture_buffer_object_initNativeFunctionAddresses() {
        return
                (glTexBufferEXT = GL.getFunctionProvider().getFunctionAddress("glTexBufferEXT")) != 0;
    }

    private boolean EXT_texture_integer_initNativeFunctionAddresses() {
        return
                (glClearColorIiEXT = GL.getFunctionProvider().getFunctionAddress("glClearColorIiEXT")) != 0 &
                        (glClearColorIuiEXT = GL.getFunctionProvider().getFunctionAddress("glClearColorIuiEXT")) != 0 &
                        (glTexParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glTexParameterIivEXT")) != 0 &
                        (glTexParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glTexParameterIuivEXT")) != 0 &
                        (glGetTexParameterIivEXT = GL.getFunctionProvider().getFunctionAddress("glGetTexParameterIivEXT")) != 0 &
                        (glGetTexParameterIuivEXT = GL.getFunctionProvider().getFunctionAddress("glGetTexParameterIuivEXT")) != 0;
    }

    private boolean EXT_timer_query_initNativeFunctionAddresses() {
        return
                (glGetQueryObjecti64vEXT = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjecti64vEXT")) != 0 &
                        (glGetQueryObjectui64vEXT = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectui64vEXT")) != 0;
    }

    private boolean EXT_transform_feedback_initNativeFunctionAddresses() {
        return
                (glBindBufferRangeEXT = GL.getFunctionProvider().getFunctionAddress("glBindBufferRangeEXT")) != 0 &
                        (glBindBufferOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glBindBufferOffsetEXT")) != 0 &
                        (glBindBufferBaseEXT = GL.getFunctionProvider().getFunctionAddress("glBindBufferBaseEXT")) != 0 &
                        (glBeginTransformFeedbackEXT = GL.getFunctionProvider().getFunctionAddress("glBeginTransformFeedbackEXT")) != 0 &
                        (glEndTransformFeedbackEXT = GL.getFunctionProvider().getFunctionAddress("glEndTransformFeedbackEXT")) != 0 &
                        (glTransformFeedbackVaryingsEXT = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackVaryingsEXT")) != 0 &
                        (glGetTransformFeedbackVaryingEXT = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbackVaryingEXT")) != 0;
    }

    private boolean EXT_vertex_attrib_64bit_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glVertexAttribL1dEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1dEXT")) != 0 &
                        (glVertexAttribL2dEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2dEXT")) != 0 &
                        (glVertexAttribL3dEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3dEXT")) != 0 &
                        (glVertexAttribL4dEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4dEXT")) != 0 &
                        (glVertexAttribL1dvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1dvEXT")) != 0 &
                        (glVertexAttribL2dvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2dvEXT")) != 0 &
                        (glVertexAttribL3dvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3dvEXT")) != 0 &
                        (glVertexAttribL4dvEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4dvEXT")) != 0 &
                        (glVertexAttribLPointerEXT = GL.getFunctionProvider().getFunctionAddress("glVertexAttribLPointerEXT")) != 0 &
                        (glGetVertexAttribLdvEXT = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribLdvEXT")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glVertexArrayVertexAttribLOffsetEXT = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexAttribLOffsetEXT")) != 0);
    }

    private boolean EXT_vertex_shader_initNativeFunctionAddresses() {
        return
                (glBeginVertexShaderEXT = GL.getFunctionProvider().getFunctionAddress("glBeginVertexShaderEXT")) != 0 &
                        (glEndVertexShaderEXT = GL.getFunctionProvider().getFunctionAddress("glEndVertexShaderEXT")) != 0 &
                        (glBindVertexShaderEXT = GL.getFunctionProvider().getFunctionAddress("glBindVertexShaderEXT")) != 0 &
                        (glGenVertexShadersEXT = GL.getFunctionProvider().getFunctionAddress("glGenVertexShadersEXT")) != 0 &
                        (glDeleteVertexShaderEXT = GL.getFunctionProvider().getFunctionAddress("glDeleteVertexShaderEXT")) != 0 &
                        (glShaderOp1EXT = GL.getFunctionProvider().getFunctionAddress("glShaderOp1EXT")) != 0 &
                        (glShaderOp2EXT = GL.getFunctionProvider().getFunctionAddress("glShaderOp2EXT")) != 0 &
                        (glShaderOp3EXT = GL.getFunctionProvider().getFunctionAddress("glShaderOp3EXT")) != 0 &
                        (glSwizzleEXT = GL.getFunctionProvider().getFunctionAddress("glSwizzleEXT")) != 0 &
                        (glWriteMaskEXT = GL.getFunctionProvider().getFunctionAddress("glWriteMaskEXT")) != 0 &
                        (glInsertComponentEXT = GL.getFunctionProvider().getFunctionAddress("glInsertComponentEXT")) != 0 &
                        (glExtractComponentEXT = GL.getFunctionProvider().getFunctionAddress("glExtractComponentEXT")) != 0 &
                        (glGenSymbolsEXT = GL.getFunctionProvider().getFunctionAddress("glGenSymbolsEXT")) != 0 &
                        (glSetInvariantEXT = GL.getFunctionProvider().getFunctionAddress("glSetInvariantEXT")) != 0 &
                        (glSetLocalConstantEXT = GL.getFunctionProvider().getFunctionAddress("glSetLocalConstantEXT")) != 0 &
                        (glVariantbvEXT = GL.getFunctionProvider().getFunctionAddress("glVariantbvEXT")) != 0 &
                        (glVariantsvEXT = GL.getFunctionProvider().getFunctionAddress("glVariantsvEXT")) != 0 &
                        (glVariantivEXT = GL.getFunctionProvider().getFunctionAddress("glVariantivEXT")) != 0 &
                        (glVariantfvEXT = GL.getFunctionProvider().getFunctionAddress("glVariantfvEXT")) != 0 &
                        (glVariantdvEXT = GL.getFunctionProvider().getFunctionAddress("glVariantdvEXT")) != 0 &
                        (glVariantubvEXT = GL.getFunctionProvider().getFunctionAddress("glVariantubvEXT")) != 0 &
                        (glVariantusvEXT = GL.getFunctionProvider().getFunctionAddress("glVariantusvEXT")) != 0 &
                        (glVariantuivEXT = GL.getFunctionProvider().getFunctionAddress("glVariantuivEXT")) != 0 &
                        (glVariantPointerEXT = GL.getFunctionProvider().getFunctionAddress("glVariantPointerEXT")) != 0 &
                        (glEnableVariantClientStateEXT = GL.getFunctionProvider().getFunctionAddress("glEnableVariantClientStateEXT")) != 0 &
                        (glDisableVariantClientStateEXT = GL.getFunctionProvider().getFunctionAddress("glDisableVariantClientStateEXT")) != 0 &
                        (glBindLightParameterEXT = GL.getFunctionProvider().getFunctionAddress("glBindLightParameterEXT")) != 0 &
                        (glBindMaterialParameterEXT = GL.getFunctionProvider().getFunctionAddress("glBindMaterialParameterEXT")) != 0 &
                        (glBindTexGenParameterEXT = GL.getFunctionProvider().getFunctionAddress("glBindTexGenParameterEXT")) != 0 &
                        (glBindTextureUnitParameterEXT = GL.getFunctionProvider().getFunctionAddress("glBindTextureUnitParameterEXT")) != 0 &
                        (glBindParameterEXT = GL.getFunctionProvider().getFunctionAddress("glBindParameterEXT")) != 0 &
                        (glIsVariantEnabledEXT = GL.getFunctionProvider().getFunctionAddress("glIsVariantEnabledEXT")) != 0 &
                        (glGetVariantBooleanvEXT = GL.getFunctionProvider().getFunctionAddress("glGetVariantBooleanvEXT")) != 0 &
                        (glGetVariantIntegervEXT = GL.getFunctionProvider().getFunctionAddress("glGetVariantIntegervEXT")) != 0 &
                        (glGetVariantFloatvEXT = GL.getFunctionProvider().getFunctionAddress("glGetVariantFloatvEXT")) != 0 &
                        (glGetVariantPointervEXT = GL.getFunctionProvider().getFunctionAddress("glGetVariantPointervEXT")) != 0 &
                        (glGetInvariantBooleanvEXT = GL.getFunctionProvider().getFunctionAddress("glGetInvariantBooleanvEXT")) != 0 &
                        (glGetInvariantIntegervEXT = GL.getFunctionProvider().getFunctionAddress("glGetInvariantIntegervEXT")) != 0 &
                        (glGetInvariantFloatvEXT = GL.getFunctionProvider().getFunctionAddress("glGetInvariantFloatvEXT")) != 0 &
                        (glGetLocalConstantBooleanvEXT = GL.getFunctionProvider().getFunctionAddress("glGetLocalConstantBooleanvEXT")) != 0 &
                        (glGetLocalConstantIntegervEXT = GL.getFunctionProvider().getFunctionAddress("glGetLocalConstantIntegervEXT")) != 0 &
                        (glGetLocalConstantFloatvEXT = GL.getFunctionProvider().getFunctionAddress("glGetLocalConstantFloatvEXT")) != 0;
    }

    private boolean EXT_vertex_weighting_initNativeFunctionAddresses() {
        return
                (glVertexWeightfEXT = GL.getFunctionProvider().getFunctionAddress("glVertexWeightfEXT")) != 0 &
                        (glVertexWeightPointerEXT = GL.getFunctionProvider().getFunctionAddress("glVertexWeightPointerEXT")) != 0;
    }

    private boolean GL11_initNativeFunctionAddresses(boolean forwardCompatible) {
        return
                (forwardCompatible || (glAccum = GL.getFunctionProvider().getFunctionAddress("glAccum")) != 0) &
                        (forwardCompatible || (glAlphaFunc = GL.getFunctionProvider().getFunctionAddress("glAlphaFunc")) != 0) &
                        (glClearColor = GL.getFunctionProvider().getFunctionAddress("glClearColor")) != 0 &
                        (forwardCompatible || (glClearAccum = GL.getFunctionProvider().getFunctionAddress("glClearAccum")) != 0) &
                        (glClear = GL.getFunctionProvider().getFunctionAddress("glClear")) != 0 &
                        (forwardCompatible || (glCallLists = GL.getFunctionProvider().getFunctionAddress("glCallLists")) != 0) &
                        (forwardCompatible || (glCallList = GL.getFunctionProvider().getFunctionAddress("glCallList")) != 0) &
                        (glBlendFunc = GL.getFunctionProvider().getFunctionAddress("glBlendFunc")) != 0 &
                        (forwardCompatible || (glBitmap = GL.getFunctionProvider().getFunctionAddress("glBitmap")) != 0) &
                        (glBindTexture = GL.getFunctionProvider().getFunctionAddress("glBindTexture")) != 0 &
                        (forwardCompatible || (glPrioritizeTextures = GL.getFunctionProvider().getFunctionAddress("glPrioritizeTextures")) != 0) &
                        (forwardCompatible || (glAreTexturesResident = GL.getFunctionProvider().getFunctionAddress("glAreTexturesResident")) != 0) &
                        (forwardCompatible || (glBegin = GL.getFunctionProvider().getFunctionAddress("glBegin")) != 0) &
                        (forwardCompatible || (glEnd = GL.getFunctionProvider().getFunctionAddress("glEnd")) != 0) &
                        (glArrayElement = GL.getFunctionProvider().getFunctionAddress("glArrayElement")) != 0 &
                        (glClearDepth = GL.getFunctionProvider().getFunctionAddress("glClearDepth")) != 0 &
                        (forwardCompatible || (glDeleteLists = GL.getFunctionProvider().getFunctionAddress("glDeleteLists")) != 0) &
                        (glDeleteTextures = GL.getFunctionProvider().getFunctionAddress("glDeleteTextures")) != 0 &
                        (glCullFace = GL.getFunctionProvider().getFunctionAddress("glCullFace")) != 0 &
                        (glCopyTexSubImage2D = GL.getFunctionProvider().getFunctionAddress("glCopyTexSubImage2D")) != 0 &
                        (glCopyTexSubImage1D = GL.getFunctionProvider().getFunctionAddress("glCopyTexSubImage1D")) != 0 &
                        (glCopyTexImage2D = GL.getFunctionProvider().getFunctionAddress("glCopyTexImage2D")) != 0 &
                        (glCopyTexImage1D = GL.getFunctionProvider().getFunctionAddress("glCopyTexImage1D")) != 0 &
                        (glCopyPixels = GL.getFunctionProvider().getFunctionAddress("glCopyPixels")) != 0 &
                        (forwardCompatible || (glColorPointer = GL.getFunctionProvider().getFunctionAddress("glColorPointer")) != 0) &
                        (forwardCompatible || (glColorMaterial = GL.getFunctionProvider().getFunctionAddress("glColorMaterial")) != 0) &
                        (glColorMask = GL.getFunctionProvider().getFunctionAddress("glColorMask")) != 0 &
                        (forwardCompatible || (glColor3b = GL.getFunctionProvider().getFunctionAddress("glColor3b")) != 0) &
                        (forwardCompatible || (glColor3f = GL.getFunctionProvider().getFunctionAddress("glColor3f")) != 0) &
                        (forwardCompatible || (glColor3d = GL.getFunctionProvider().getFunctionAddress("glColor3d")) != 0) &
                        (forwardCompatible || (glColor3ub = GL.getFunctionProvider().getFunctionAddress("glColor3ub")) != 0) &
                        (forwardCompatible || (glColor4b = GL.getFunctionProvider().getFunctionAddress("glColor4b")) != 0) &
                        (forwardCompatible || (glColor4f = GL.getFunctionProvider().getFunctionAddress("glColor4f")) != 0) &
                        (forwardCompatible || (glColor4d = GL.getFunctionProvider().getFunctionAddress("glColor4d")) != 0) &
                        (forwardCompatible || (glColor4ub = GL.getFunctionProvider().getFunctionAddress("glColor4ub")) != 0) &
                        (glClipPlane = GL.getFunctionProvider().getFunctionAddress("glClipPlane")) != 0 &
                        (glClearStencil = GL.getFunctionProvider().getFunctionAddress("glClearStencil")) != 0 &
                        (forwardCompatible || (glEvalPoint1 = GL.getFunctionProvider().getFunctionAddress("glEvalPoint1")) != 0) &
                        (forwardCompatible || (glEvalPoint2 = GL.getFunctionProvider().getFunctionAddress("glEvalPoint2")) != 0) &
                        (forwardCompatible || (glEvalMesh1 = GL.getFunctionProvider().getFunctionAddress("glEvalMesh1")) != 0) &
                        (forwardCompatible || (glEvalMesh2 = GL.getFunctionProvider().getFunctionAddress("glEvalMesh2")) != 0) &
                        (forwardCompatible || (glEvalCoord1f = GL.getFunctionProvider().getFunctionAddress("glEvalCoord1f")) != 0) &
                        (forwardCompatible || (glEvalCoord1d = GL.getFunctionProvider().getFunctionAddress("glEvalCoord1d")) != 0) &
                        (forwardCompatible || (glEvalCoord2f = GL.getFunctionProvider().getFunctionAddress("glEvalCoord2f")) != 0) &
                        (forwardCompatible || (glEvalCoord2d = GL.getFunctionProvider().getFunctionAddress("glEvalCoord2d")) != 0) &
                        (forwardCompatible || (glEnableClientState = GL.getFunctionProvider().getFunctionAddress("glEnableClientState")) != 0) &
                        (forwardCompatible || (glDisableClientState = GL.getFunctionProvider().getFunctionAddress("glDisableClientState")) != 0) &
                        (glEnable = GL.getFunctionProvider().getFunctionAddress("glEnable")) != 0 &
                        (glDisable = GL.getFunctionProvider().getFunctionAddress("glDisable")) != 0 &
                        (forwardCompatible || (glEdgeFlagPointer = GL.getFunctionProvider().getFunctionAddress("glEdgeFlagPointer")) != 0) &
                        (forwardCompatible || (glEdgeFlag = GL.getFunctionProvider().getFunctionAddress("glEdgeFlag")) != 0) &
                        (forwardCompatible || (glDrawPixels = GL.getFunctionProvider().getFunctionAddress("glDrawPixels")) != 0) &
                        (glDrawElements = GL.getFunctionProvider().getFunctionAddress("glDrawElements")) != 0 &
                        (glDrawBuffer = GL.getFunctionProvider().getFunctionAddress("glDrawBuffer")) != 0 &
                        (glDrawArrays = GL.getFunctionProvider().getFunctionAddress("glDrawArrays")) != 0 &
                        (glDepthRange = GL.getFunctionProvider().getFunctionAddress("glDepthRange")) != 0 &
                        (glDepthMask = GL.getFunctionProvider().getFunctionAddress("glDepthMask")) != 0 &
                        (glDepthFunc = GL.getFunctionProvider().getFunctionAddress("glDepthFunc")) != 0 &
                        (forwardCompatible || (glFeedbackBuffer = GL.getFunctionProvider().getFunctionAddress("glFeedbackBuffer")) != 0) &
                        (forwardCompatible || (glGetPixelMapfv = GL.getFunctionProvider().getFunctionAddress("glGetPixelMapfv")) != 0) &
                        (forwardCompatible || (glGetPixelMapuiv = GL.getFunctionProvider().getFunctionAddress("glGetPixelMapuiv")) != 0) &
                        (forwardCompatible || (glGetPixelMapusv = GL.getFunctionProvider().getFunctionAddress("glGetPixelMapusv")) != 0) &
                        (forwardCompatible || (glGetMaterialfv = GL.getFunctionProvider().getFunctionAddress("glGetMaterialfv")) != 0) &
                        (forwardCompatible || (glGetMaterialiv = GL.getFunctionProvider().getFunctionAddress("glGetMaterialiv")) != 0) &
                        (forwardCompatible || (glGetMapfv = GL.getFunctionProvider().getFunctionAddress("glGetMapfv")) != 0) &
                        (forwardCompatible || (glGetMapdv = GL.getFunctionProvider().getFunctionAddress("glGetMapdv")) != 0) &
                        (forwardCompatible || (glGetMapiv = GL.getFunctionProvider().getFunctionAddress("glGetMapiv")) != 0) &
                        (forwardCompatible || (glGetLightfv = GL.getFunctionProvider().getFunctionAddress("glGetLightfv")) != 0) &
                        (forwardCompatible || (glGetLightiv = GL.getFunctionProvider().getFunctionAddress("glGetLightiv")) != 0) &
                        (glGetError = GL.getFunctionProvider().getFunctionAddress("glGetError")) != 0 &
                        (glGetClipPlane = GL.getFunctionProvider().getFunctionAddress("glGetClipPlane")) != 0 &
                        (glGetBooleanv = GL.getFunctionProvider().getFunctionAddress("glGetBooleanv")) != 0 &
                        (glGetDoublev = GL.getFunctionProvider().getFunctionAddress("glGetDoublev")) != 0 &
                        (glGetFloatv = GL.getFunctionProvider().getFunctionAddress("glGetFloatv")) != 0 &
                        (glGetIntegerv = GL.getFunctionProvider().getFunctionAddress("glGetIntegerv")) != 0 &
                        (glGenTextures = GL.getFunctionProvider().getFunctionAddress("glGenTextures")) != 0 &
                        (forwardCompatible || (glGenLists = GL.getFunctionProvider().getFunctionAddress("glGenLists")) != 0) &
                        (forwardCompatible || (glFrustum = GL.getFunctionProvider().getFunctionAddress("glFrustum")) != 0) &
                        (glFrontFace = GL.getFunctionProvider().getFunctionAddress("glFrontFace")) != 0 &
                        (forwardCompatible || (glFogf = GL.getFunctionProvider().getFunctionAddress("glFogf")) != 0) &
                        (forwardCompatible || (glFogi = GL.getFunctionProvider().getFunctionAddress("glFogi")) != 0) &
                        (forwardCompatible || (glFogfv = GL.getFunctionProvider().getFunctionAddress("glFogfv")) != 0) &
                        (forwardCompatible || (glFogiv = GL.getFunctionProvider().getFunctionAddress("glFogiv")) != 0) &
                        (glFlush = GL.getFunctionProvider().getFunctionAddress("glFlush")) != 0 &
                        (glFinish = GL.getFunctionProvider().getFunctionAddress("glFinish")) != 0 &
                        (glGetPointerv = GL.getFunctionProvider().getFunctionAddress("glGetPointerv")) != 0 &
                        (glIsEnabled = GL.getFunctionProvider().getFunctionAddress("glIsEnabled")) != 0 &
                        (glInterleavedArrays = GL.getFunctionProvider().getFunctionAddress("glInterleavedArrays")) != 0 &
                        (forwardCompatible || (glInitNames = GL.getFunctionProvider().getFunctionAddress("glInitNames")) != 0) &
                        (glHint = GL.getFunctionProvider().getFunctionAddress("glHint")) != 0 &
                        (glGetTexParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetTexParameterfv")) != 0 &
                        (glGetTexParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetTexParameteriv")) != 0 &
                        (glGetTexLevelParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetTexLevelParameterfv")) != 0 &
                        (glGetTexLevelParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetTexLevelParameteriv")) != 0 &
                        (glGetTexImage = GL.getFunctionProvider().getFunctionAddress("glGetTexImage")) != 0 &
                        (forwardCompatible || (glGetTexGeniv = GL.getFunctionProvider().getFunctionAddress("glGetTexGeniv")) != 0) &
                        (forwardCompatible || (glGetTexGenfv = GL.getFunctionProvider().getFunctionAddress("glGetTexGenfv")) != 0) &
                        (forwardCompatible || (glGetTexGendv = GL.getFunctionProvider().getFunctionAddress("glGetTexGendv")) != 0) &
                        (glGetTexEnviv = GL.getFunctionProvider().getFunctionAddress("glGetTexEnviv")) != 0 &
                        (glGetTexEnvfv = GL.getFunctionProvider().getFunctionAddress("glGetTexEnvfv")) != 0 &
                        (glGetString = GL.getFunctionProvider().getFunctionAddress("glGetString")) != 0 &
                        (forwardCompatible || (glGetPolygonStipple = GL.getFunctionProvider().getFunctionAddress("glGetPolygonStipple")) != 0) &
                        (forwardCompatible || (glIsList = GL.getFunctionProvider().getFunctionAddress("glIsList")) != 0) &
                        (forwardCompatible || (glMaterialf = GL.getFunctionProvider().getFunctionAddress("glMaterialf")) != 0) &
                        (forwardCompatible || (glMateriali = GL.getFunctionProvider().getFunctionAddress("glMateriali")) != 0) &
                        (forwardCompatible || (glMaterialfv = GL.getFunctionProvider().getFunctionAddress("glMaterialfv")) != 0) &
                        (forwardCompatible || (glMaterialiv = GL.getFunctionProvider().getFunctionAddress("glMaterialiv")) != 0) &
                        (forwardCompatible || (glMapGrid1f = GL.getFunctionProvider().getFunctionAddress("glMapGrid1f")) != 0) &
                        (forwardCompatible || (glMapGrid1d = GL.getFunctionProvider().getFunctionAddress("glMapGrid1d")) != 0) &
                        (forwardCompatible || (glMapGrid2f = GL.getFunctionProvider().getFunctionAddress("glMapGrid2f")) != 0) &
                        (forwardCompatible || (glMapGrid2d = GL.getFunctionProvider().getFunctionAddress("glMapGrid2d")) != 0) &
                        (forwardCompatible || (glMap2f = GL.getFunctionProvider().getFunctionAddress("glMap2f")) != 0) &
                        (forwardCompatible || (glMap2d = GL.getFunctionProvider().getFunctionAddress("glMap2d")) != 0) &
                        (forwardCompatible || (glMap1f = GL.getFunctionProvider().getFunctionAddress("glMap1f")) != 0) &
                        (forwardCompatible || (glMap1d = GL.getFunctionProvider().getFunctionAddress("glMap1d")) != 0) &
                        (glLogicOp = GL.getFunctionProvider().getFunctionAddress("glLogicOp")) != 0 &
                        (forwardCompatible || (glLoadName = GL.getFunctionProvider().getFunctionAddress("glLoadName")) != 0) &
                        (forwardCompatible || (glLoadMatrixf = GL.getFunctionProvider().getFunctionAddress("glLoadMatrixf")) != 0) &
                        (forwardCompatible || (glLoadMatrixd = GL.getFunctionProvider().getFunctionAddress("glLoadMatrixd")) != 0) &
                        (forwardCompatible || (glLoadIdentity = GL.getFunctionProvider().getFunctionAddress("glLoadIdentity")) != 0) &
                        (forwardCompatible || (glListBase = GL.getFunctionProvider().getFunctionAddress("glListBase")) != 0) &
                        (glLineWidth = GL.getFunctionProvider().getFunctionAddress("glLineWidth")) != 0 &
                        (forwardCompatible || (glLineStipple = GL.getFunctionProvider().getFunctionAddress("glLineStipple")) != 0) &
                        (forwardCompatible || (glLightModelf = GL.getFunctionProvider().getFunctionAddress("glLightModelf")) != 0) &
                        (forwardCompatible || (glLightModeli = GL.getFunctionProvider().getFunctionAddress("glLightModeli")) != 0) &
                        (forwardCompatible || (glLightModelfv = GL.getFunctionProvider().getFunctionAddress("glLightModelfv")) != 0) &
                        (forwardCompatible || (glLightModeliv = GL.getFunctionProvider().getFunctionAddress("glLightModeliv")) != 0) &
                        (forwardCompatible || (glLightf = GL.getFunctionProvider().getFunctionAddress("glLightf")) != 0) &
                        (forwardCompatible || (glLighti = GL.getFunctionProvider().getFunctionAddress("glLighti")) != 0) &
                        (forwardCompatible || (glLightfv = GL.getFunctionProvider().getFunctionAddress("glLightfv")) != 0) &
                        (forwardCompatible || (glLightiv = GL.getFunctionProvider().getFunctionAddress("glLightiv")) != 0) &
                        (glIsTexture = GL.getFunctionProvider().getFunctionAddress("glIsTexture")) != 0 &
                        (forwardCompatible || (glMatrixMode = GL.getFunctionProvider().getFunctionAddress("glMatrixMode")) != 0) &
                        (forwardCompatible || (glPolygonStipple = GL.getFunctionProvider().getFunctionAddress("glPolygonStipple")) != 0) &
                        (glPolygonOffset = GL.getFunctionProvider().getFunctionAddress("glPolygonOffset")) != 0 &
                        (glPolygonMode = GL.getFunctionProvider().getFunctionAddress("glPolygonMode")) != 0 &
                        (glPointSize = GL.getFunctionProvider().getFunctionAddress("glPointSize")) != 0 &
                        (forwardCompatible || (glPixelZoom = GL.getFunctionProvider().getFunctionAddress("glPixelZoom")) != 0) &
                        (forwardCompatible || (glPixelTransferf = GL.getFunctionProvider().getFunctionAddress("glPixelTransferf")) != 0) &
                        (forwardCompatible || (glPixelTransferi = GL.getFunctionProvider().getFunctionAddress("glPixelTransferi")) != 0) &
                        (glPixelStoref = GL.getFunctionProvider().getFunctionAddress("glPixelStoref")) != 0 &
                        (glPixelStorei = GL.getFunctionProvider().getFunctionAddress("glPixelStorei")) != 0 &
                        (forwardCompatible || (glPixelMapfv = GL.getFunctionProvider().getFunctionAddress("glPixelMapfv")) != 0) &
                        (forwardCompatible || (glPixelMapuiv = GL.getFunctionProvider().getFunctionAddress("glPixelMapuiv")) != 0) &
                        (forwardCompatible || (glPixelMapusv = GL.getFunctionProvider().getFunctionAddress("glPixelMapusv")) != 0) &
                        (forwardCompatible || (glPassThrough = GL.getFunctionProvider().getFunctionAddress("glPassThrough")) != 0) &
                        (forwardCompatible || (glOrtho = GL.getFunctionProvider().getFunctionAddress("glOrtho")) != 0) &
                        (forwardCompatible || (glNormalPointer = GL.getFunctionProvider().getFunctionAddress("glNormalPointer")) != 0) &
                        (forwardCompatible || (glNormal3b = GL.getFunctionProvider().getFunctionAddress("glNormal3b")) != 0) &
                        (forwardCompatible || (glNormal3f = GL.getFunctionProvider().getFunctionAddress("glNormal3f")) != 0) &
                        (forwardCompatible || (glNormal3d = GL.getFunctionProvider().getFunctionAddress("glNormal3d")) != 0) &
                        (forwardCompatible || (glNormal3i = GL.getFunctionProvider().getFunctionAddress("glNormal3i")) != 0) &
                        (forwardCompatible || (glNewList = GL.getFunctionProvider().getFunctionAddress("glNewList")) != 0) &
                        (forwardCompatible || (glEndList = GL.getFunctionProvider().getFunctionAddress("glEndList")) != 0) &
                        (forwardCompatible || (glMultMatrixf = GL.getFunctionProvider().getFunctionAddress("glMultMatrixf")) != 0) &
                        (forwardCompatible || (glMultMatrixd = GL.getFunctionProvider().getFunctionAddress("glMultMatrixd")) != 0) &
                        (glShadeModel = GL.getFunctionProvider().getFunctionAddress("glShadeModel")) != 0 &
                        (forwardCompatible || (glSelectBuffer = GL.getFunctionProvider().getFunctionAddress("glSelectBuffer")) != 0) &
                        (glScissor = GL.getFunctionProvider().getFunctionAddress("glScissor")) != 0 &
                        (forwardCompatible || (glScalef = GL.getFunctionProvider().getFunctionAddress("glScalef")) != 0) &
                        (forwardCompatible || (glScaled = GL.getFunctionProvider().getFunctionAddress("glScaled")) != 0) &
                        (forwardCompatible || (glRotatef = GL.getFunctionProvider().getFunctionAddress("glRotatef")) != 0) &
                        (forwardCompatible || (glRotated = GL.getFunctionProvider().getFunctionAddress("glRotated")) != 0) &
                        (forwardCompatible || (glRenderMode = GL.getFunctionProvider().getFunctionAddress("glRenderMode")) != 0) &
                        (forwardCompatible || (glRectf = GL.getFunctionProvider().getFunctionAddress("glRectf")) != 0) &
                        (forwardCompatible || (glRectd = GL.getFunctionProvider().getFunctionAddress("glRectd")) != 0) &
                        (forwardCompatible || (glRecti = GL.getFunctionProvider().getFunctionAddress("glRecti")) != 0) &
                        (glReadPixels = GL.getFunctionProvider().getFunctionAddress("glReadPixels")) != 0 &
                        (glReadBuffer = GL.getFunctionProvider().getFunctionAddress("glReadBuffer")) != 0 &
                        (forwardCompatible || (glRasterPos2f = GL.getFunctionProvider().getFunctionAddress("glRasterPos2f")) != 0) &
                        (forwardCompatible || (glRasterPos2d = GL.getFunctionProvider().getFunctionAddress("glRasterPos2d")) != 0) &
                        (forwardCompatible || (glRasterPos2i = GL.getFunctionProvider().getFunctionAddress("glRasterPos2i")) != 0) &
                        (forwardCompatible || (glRasterPos3f = GL.getFunctionProvider().getFunctionAddress("glRasterPos3f")) != 0) &
                        (forwardCompatible || (glRasterPos3d = GL.getFunctionProvider().getFunctionAddress("glRasterPos3d")) != 0) &
                        (forwardCompatible || (glRasterPos3i = GL.getFunctionProvider().getFunctionAddress("glRasterPos3i")) != 0) &
                        (forwardCompatible || (glRasterPos4f = GL.getFunctionProvider().getFunctionAddress("glRasterPos4f")) != 0) &
                        (forwardCompatible || (glRasterPos4d = GL.getFunctionProvider().getFunctionAddress("glRasterPos4d")) != 0) &
                        (forwardCompatible || (glRasterPos4i = GL.getFunctionProvider().getFunctionAddress("glRasterPos4i")) != 0) &
                        (forwardCompatible || (glPushName = GL.getFunctionProvider().getFunctionAddress("glPushName")) != 0) &
                        (forwardCompatible || (glPopName = GL.getFunctionProvider().getFunctionAddress("glPopName")) != 0) &
                        (forwardCompatible || (glPushMatrix = GL.getFunctionProvider().getFunctionAddress("glPushMatrix")) != 0) &
                        (forwardCompatible || (glPopMatrix = GL.getFunctionProvider().getFunctionAddress("glPopMatrix")) != 0) &
                        (forwardCompatible || (glPushClientAttrib = GL.getFunctionProvider().getFunctionAddress("glPushClientAttrib")) != 0) &
                        (forwardCompatible || (glPopClientAttrib = GL.getFunctionProvider().getFunctionAddress("glPopClientAttrib")) != 0) &
                        (forwardCompatible || (glPushAttrib = GL.getFunctionProvider().getFunctionAddress("glPushAttrib")) != 0) &
                        (forwardCompatible || (glPopAttrib = GL.getFunctionProvider().getFunctionAddress("glPopAttrib")) != 0) &
                        (glStencilFunc = GL.getFunctionProvider().getFunctionAddress("glStencilFunc")) != 0 &
                        (forwardCompatible || (glVertexPointer = GL.getFunctionProvider().getFunctionAddress("glVertexPointer")) != 0) &
                        (forwardCompatible || (glVertex2f = GL.getFunctionProvider().getFunctionAddress("glVertex2f")) != 0) &
                        (forwardCompatible || (glVertex2d = GL.getFunctionProvider().getFunctionAddress("glVertex2d")) != 0) &
                        (forwardCompatible || (glVertex2i = GL.getFunctionProvider().getFunctionAddress("glVertex2i")) != 0) &
                        (forwardCompatible || (glVertex3f = GL.getFunctionProvider().getFunctionAddress("glVertex3f")) != 0) &
                        (forwardCompatible || (glVertex3d = GL.getFunctionProvider().getFunctionAddress("glVertex3d")) != 0) &
                        (forwardCompatible || (glVertex3i = GL.getFunctionProvider().getFunctionAddress("glVertex3i")) != 0) &
                        (forwardCompatible || (glVertex4f = GL.getFunctionProvider().getFunctionAddress("glVertex4f")) != 0) &
                        (forwardCompatible || (glVertex4d = GL.getFunctionProvider().getFunctionAddress("glVertex4d")) != 0) &
                        (forwardCompatible || (glVertex4i = GL.getFunctionProvider().getFunctionAddress("glVertex4i")) != 0) &
                        (forwardCompatible || (glTranslatef = GL.getFunctionProvider().getFunctionAddress("glTranslatef")) != 0) &
                        (forwardCompatible || (glTranslated = GL.getFunctionProvider().getFunctionAddress("glTranslated")) != 0) &
                        (glTexImage1D = GL.getFunctionProvider().getFunctionAddress("glTexImage1D")) != 0 &
                        (glTexImage2D = GL.getFunctionProvider().getFunctionAddress("glTexImage2D")) != 0 &
                        (glTexSubImage1D = GL.getFunctionProvider().getFunctionAddress("glTexSubImage1D")) != 0 &
                        (glTexSubImage2D = GL.getFunctionProvider().getFunctionAddress("glTexSubImage2D")) != 0 &
                        (glTexParameterf = GL.getFunctionProvider().getFunctionAddress("glTexParameterf")) != 0 &
                        (glTexParameteri = GL.getFunctionProvider().getFunctionAddress("glTexParameteri")) != 0 &
                        (glTexParameterfv = GL.getFunctionProvider().getFunctionAddress("glTexParameterfv")) != 0 &
                        (glTexParameteriv = GL.getFunctionProvider().getFunctionAddress("glTexParameteriv")) != 0 &
                        (forwardCompatible || (glTexGenf = GL.getFunctionProvider().getFunctionAddress("glTexGenf")) != 0) &
                        (forwardCompatible || (glTexGend = GL.getFunctionProvider().getFunctionAddress("glTexGend")) != 0) &
                        (forwardCompatible || (glTexGenfv = GL.getFunctionProvider().getFunctionAddress("glTexGenfv")) != 0) &
                        (forwardCompatible || (glTexGendv = GL.getFunctionProvider().getFunctionAddress("glTexGendv")) != 0) &
                        (forwardCompatible || (glTexGeni = GL.getFunctionProvider().getFunctionAddress("glTexGeni")) != 0) &
                        (forwardCompatible || (glTexGeniv = GL.getFunctionProvider().getFunctionAddress("glTexGeniv")) != 0) &
                        (glTexEnvf = GL.getFunctionProvider().getFunctionAddress("glTexEnvf")) != 0 &
                        (glTexEnvi = GL.getFunctionProvider().getFunctionAddress("glTexEnvi")) != 0 &
                        (glTexEnvfv = GL.getFunctionProvider().getFunctionAddress("glTexEnvfv")) != 0 &
                        (glTexEnviv = GL.getFunctionProvider().getFunctionAddress("glTexEnviv")) != 0 &
                        (forwardCompatible || (glTexCoordPointer = GL.getFunctionProvider().getFunctionAddress("glTexCoordPointer")) != 0) &
                        (forwardCompatible || (glTexCoord1f = GL.getFunctionProvider().getFunctionAddress("glTexCoord1f")) != 0) &
                        (forwardCompatible || (glTexCoord1d = GL.getFunctionProvider().getFunctionAddress("glTexCoord1d")) != 0) &
                        (forwardCompatible || (glTexCoord2f = GL.getFunctionProvider().getFunctionAddress("glTexCoord2f")) != 0) &
                        (forwardCompatible || (glTexCoord2d = GL.getFunctionProvider().getFunctionAddress("glTexCoord2d")) != 0) &
                        (forwardCompatible || (glTexCoord3f = GL.getFunctionProvider().getFunctionAddress("glTexCoord3f")) != 0) &
                        (forwardCompatible || (glTexCoord3d = GL.getFunctionProvider().getFunctionAddress("glTexCoord3d")) != 0) &
                        (forwardCompatible || (glTexCoord4f = GL.getFunctionProvider().getFunctionAddress("glTexCoord4f")) != 0) &
                        (forwardCompatible || (glTexCoord4d = GL.getFunctionProvider().getFunctionAddress("glTexCoord4d")) != 0) &
                        (glStencilOp = GL.getFunctionProvider().getFunctionAddress("glStencilOp")) != 0 &
                        (glStencilMask = GL.getFunctionProvider().getFunctionAddress("glStencilMask")) != 0 &
                        (glViewport = GL.getFunctionProvider().getFunctionAddress("glViewport")) != 0;
    }

    private boolean GL12_initNativeFunctionAddresses() {
        return
                (glDrawRangeElements = GL.getFunctionProvider().getFunctionAddress("glDrawRangeElements")) != 0 &
                        (glTexImage3D = GL.getFunctionProvider().getFunctionAddress("glTexImage3D")) != 0 &
                        (glTexSubImage3D = GL.getFunctionProvider().getFunctionAddress("glTexSubImage3D")) != 0 &
                        (glCopyTexSubImage3D = GL.getFunctionProvider().getFunctionAddress("glCopyTexSubImage3D")) != 0;
    }

    private boolean GL13_initNativeFunctionAddresses(boolean forwardCompatible) {
        return
                (glActiveTexture = GL.getFunctionProvider().getFunctionAddress("glActiveTexture")) != 0 &
                        (forwardCompatible || (glClientActiveTexture = GL.getFunctionProvider().getFunctionAddress("glClientActiveTexture")) != 0) &
                        (glCompressedTexImage1D = GL.getFunctionProvider().getFunctionAddress("glCompressedTexImage1D")) != 0 &
                        (glCompressedTexImage2D = GL.getFunctionProvider().getFunctionAddress("glCompressedTexImage2D")) != 0 &
                        (glCompressedTexImage3D = GL.getFunctionProvider().getFunctionAddress("glCompressedTexImage3D")) != 0 &
                        (glCompressedTexSubImage1D = GL.getFunctionProvider().getFunctionAddress("glCompressedTexSubImage1D")) != 0 &
                        (glCompressedTexSubImage2D = GL.getFunctionProvider().getFunctionAddress("glCompressedTexSubImage2D")) != 0 &
                        (glCompressedTexSubImage3D = GL.getFunctionProvider().getFunctionAddress("glCompressedTexSubImage3D")) != 0 &
                        (glGetCompressedTexImage = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTexImage")) != 0 &
                        (forwardCompatible || (glMultiTexCoord1f = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1f")) != 0) &
                        (forwardCompatible || (glMultiTexCoord1d = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1d")) != 0) &
                        (forwardCompatible || (glMultiTexCoord2f = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2f")) != 0) &
                        (forwardCompatible || (glMultiTexCoord2d = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2d")) != 0) &
                        (forwardCompatible || (glMultiTexCoord3f = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3f")) != 0) &
                        (forwardCompatible || (glMultiTexCoord3d = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3d")) != 0) &
                        (forwardCompatible || (glMultiTexCoord4f = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4f")) != 0) &
                        (forwardCompatible || (glMultiTexCoord4d = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4d")) != 0) &
                        (forwardCompatible || (glLoadTransposeMatrixf = GL.getFunctionProvider().getFunctionAddress("glLoadTransposeMatrixf")) != 0) &
                        (forwardCompatible || (glLoadTransposeMatrixd = GL.getFunctionProvider().getFunctionAddress("glLoadTransposeMatrixd")) != 0) &
                        (forwardCompatible || (glMultTransposeMatrixf = GL.getFunctionProvider().getFunctionAddress("glMultTransposeMatrixf")) != 0) &
                        (forwardCompatible || (glMultTransposeMatrixd = GL.getFunctionProvider().getFunctionAddress("glMultTransposeMatrixd")) != 0) &
                        (glSampleCoverage = GL.getFunctionProvider().getFunctionAddress("glSampleCoverage")) != 0;
    }

    private boolean GL14_initNativeFunctionAddresses(boolean forwardCompatible) {
        return
                (glBlendEquation = GL.getFunctionProvider().getFunctionAddress("glBlendEquation")) != 0 &
                        (glBlendColor = GL.getFunctionProvider().getFunctionAddress("glBlendColor")) != 0 &
                        (forwardCompatible || (glFogCoordf = GL.getFunctionProvider().getFunctionAddress("glFogCoordf")) != 0) &
                        (forwardCompatible || (glFogCoordd = GL.getFunctionProvider().getFunctionAddress("glFogCoordd")) != 0) &
                        (forwardCompatible || (glFogCoordPointer = GL.getFunctionProvider().getFunctionAddress("glFogCoordPointer")) != 0) &
                        (glMultiDrawArrays = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArrays")) != 0 &
                        (glPointParameteri = GL.getFunctionProvider().getFunctionAddress("glPointParameteri")) != 0 &
                        (glPointParameterf = GL.getFunctionProvider().getFunctionAddress("glPointParameterf")) != 0 &
                        (glPointParameteriv = GL.getFunctionProvider().getFunctionAddress("glPointParameteriv")) != 0 &
                        (glPointParameterfv = GL.getFunctionProvider().getFunctionAddress("glPointParameterfv")) != 0 &
                        (forwardCompatible || (glSecondaryColor3b = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3b")) != 0) &
                        (forwardCompatible || (glSecondaryColor3f = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3f")) != 0) &
                        (forwardCompatible || (glSecondaryColor3d = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3d")) != 0) &
                        (forwardCompatible || (glSecondaryColor3ub = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3ub")) != 0) &
                        (forwardCompatible || (glSecondaryColorPointer = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorPointer")) != 0) &
                        (glBlendFuncSeparate = GL.getFunctionProvider().getFunctionAddress("glBlendFuncSeparate")) != 0 &
                        (forwardCompatible || (glWindowPos2f = GL.getFunctionProvider().getFunctionAddress("glWindowPos2f")) != 0) &
                        (forwardCompatible || (glWindowPos2d = GL.getFunctionProvider().getFunctionAddress("glWindowPos2d")) != 0) &
                        (forwardCompatible || (glWindowPos2i = GL.getFunctionProvider().getFunctionAddress("glWindowPos2i")) != 0) &
                        (forwardCompatible || (glWindowPos3f = GL.getFunctionProvider().getFunctionAddress("glWindowPos3f")) != 0) &
                        (forwardCompatible || (glWindowPos3d = GL.getFunctionProvider().getFunctionAddress("glWindowPos3d")) != 0) &
                        (forwardCompatible || (glWindowPos3i = GL.getFunctionProvider().getFunctionAddress("glWindowPos3i")) != 0);
    }

    private boolean GL15_initNativeFunctionAddresses() {
        return
                (glBindBuffer = GL.getFunctionProvider().getFunctionAddress("glBindBuffer")) != 0 &
                        (glDeleteBuffers = GL.getFunctionProvider().getFunctionAddress("glDeleteBuffers")) != 0 &
                        (glGenBuffers = GL.getFunctionProvider().getFunctionAddress("glGenBuffers")) != 0 &
                        (glIsBuffer = GL.getFunctionProvider().getFunctionAddress("glIsBuffer")) != 0 &
                        (glBufferData = GL.getFunctionProvider().getFunctionAddress("glBufferData")) != 0 &
                        (glBufferSubData = GL.getFunctionProvider().getFunctionAddress("glBufferSubData")) != 0 &
                        (glGetBufferSubData = GL.getFunctionProvider().getFunctionAddress("glGetBufferSubData")) != 0 &
                        (glMapBuffer = GL.getFunctionProvider().getFunctionAddress("glMapBuffer")) != 0 &
                        (glUnmapBuffer = GL.getFunctionProvider().getFunctionAddress("glUnmapBuffer")) != 0 &
                        (glGetBufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetBufferParameteriv")) != 0 &
                        (glGetBufferPointerv = GL.getFunctionProvider().getFunctionAddress("glGetBufferPointerv")) != 0 &
                        (glGenQueries = GL.getFunctionProvider().getFunctionAddress("glGenQueries")) != 0 &
                        (glDeleteQueries = GL.getFunctionProvider().getFunctionAddress("glDeleteQueries")) != 0 &
                        (glIsQuery = GL.getFunctionProvider().getFunctionAddress("glIsQuery")) != 0 &
                        (glBeginQuery = GL.getFunctionProvider().getFunctionAddress("glBeginQuery")) != 0 &
                        (glEndQuery = GL.getFunctionProvider().getFunctionAddress("glEndQuery")) != 0 &
                        (glGetQueryiv = GL.getFunctionProvider().getFunctionAddress("glGetQueryiv")) != 0 &
                        (glGetQueryObjectiv = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectiv")) != 0 &
                        (glGetQueryObjectuiv = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectuiv")) != 0;
    }

    private boolean GL20_initNativeFunctionAddresses() {
        return
                (glShaderSource = GL.getFunctionProvider().getFunctionAddress("glShaderSource")) != 0 &
                        (glCreateShader = GL.getFunctionProvider().getFunctionAddress("glCreateShader")) != 0 &
                        (glIsShader = GL.getFunctionProvider().getFunctionAddress("glIsShader")) != 0 &
                        (glCompileShader = GL.getFunctionProvider().getFunctionAddress("glCompileShader")) != 0 &
                        (glDeleteShader = GL.getFunctionProvider().getFunctionAddress("glDeleteShader")) != 0 &
                        (glCreateProgram = GL.getFunctionProvider().getFunctionAddress("glCreateProgram")) != 0 &
                        (glIsProgram = GL.getFunctionProvider().getFunctionAddress("glIsProgram")) != 0 &
                        (glAttachShader = GL.getFunctionProvider().getFunctionAddress("glAttachShader")) != 0 &
                        (glDetachShader = GL.getFunctionProvider().getFunctionAddress("glDetachShader")) != 0 &
                        (glLinkProgram = GL.getFunctionProvider().getFunctionAddress("glLinkProgram")) != 0 &
                        (glUseProgram = GL.getFunctionProvider().getFunctionAddress("glUseProgram")) != 0 &
                        (glValidateProgram = GL.getFunctionProvider().getFunctionAddress("glValidateProgram")) != 0 &
                        (glDeleteProgram = GL.getFunctionProvider().getFunctionAddress("glDeleteProgram")) != 0 &
                        (glUniform1f = GL.getFunctionProvider().getFunctionAddress("glUniform1f")) != 0 &
                        (glUniform2f = GL.getFunctionProvider().getFunctionAddress("glUniform2f")) != 0 &
                        (glUniform3f = GL.getFunctionProvider().getFunctionAddress("glUniform3f")) != 0 &
                        (glUniform4f = GL.getFunctionProvider().getFunctionAddress("glUniform4f")) != 0 &
                        (glUniform1i = GL.getFunctionProvider().getFunctionAddress("glUniform1i")) != 0 &
                        (glUniform2i = GL.getFunctionProvider().getFunctionAddress("glUniform2i")) != 0 &
                        (glUniform3i = GL.getFunctionProvider().getFunctionAddress("glUniform3i")) != 0 &
                        (glUniform4i = GL.getFunctionProvider().getFunctionAddress("glUniform4i")) != 0 &
                        (glUniform1fv = GL.getFunctionProvider().getFunctionAddress("glUniform1fv")) != 0 &
                        (glUniform2fv = GL.getFunctionProvider().getFunctionAddress("glUniform2fv")) != 0 &
                        (glUniform3fv = GL.getFunctionProvider().getFunctionAddress("glUniform3fv")) != 0 &
                        (glUniform4fv = GL.getFunctionProvider().getFunctionAddress("glUniform4fv")) != 0 &
                        (glUniform1iv = GL.getFunctionProvider().getFunctionAddress("glUniform1iv")) != 0 &
                        (glUniform2iv = GL.getFunctionProvider().getFunctionAddress("glUniform2iv")) != 0 &
                        (glUniform3iv = GL.getFunctionProvider().getFunctionAddress("glUniform3iv")) != 0 &
                        (glUniform4iv = GL.getFunctionProvider().getFunctionAddress("glUniform4iv")) != 0 &
                        (glUniformMatrix2fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2fv")) != 0 &
                        (glUniformMatrix3fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3fv")) != 0 &
                        (glUniformMatrix4fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4fv")) != 0 &
                        (glGetShaderiv = GL.getFunctionProvider().getFunctionAddress("glGetShaderiv")) != 0 &
                        (glGetProgramiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramiv")) != 0 &
                        (glGetShaderInfoLog = GL.getFunctionProvider().getFunctionAddress("glGetShaderInfoLog")) != 0 &
                        (glGetProgramInfoLog = GL.getFunctionProvider().getFunctionAddress("glGetProgramInfoLog")) != 0 &
                        (glGetAttachedShaders = GL.getFunctionProvider().getFunctionAddress("glGetAttachedShaders")) != 0 &
                        (glGetUniformLocation = GL.getFunctionProvider().getFunctionAddress("glGetUniformLocation")) != 0 &
                        (glGetActiveUniform = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniform")) != 0 &
                        (glGetUniformfv = GL.getFunctionProvider().getFunctionAddress("glGetUniformfv")) != 0 &
                        (glGetUniformiv = GL.getFunctionProvider().getFunctionAddress("glGetUniformiv")) != 0 &
                        (glGetShaderSource = GL.getFunctionProvider().getFunctionAddress("glGetShaderSource")) != 0 &
                        (glVertexAttrib1s = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1s")) != 0 &
                        (glVertexAttrib1f = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1f")) != 0 &
                        (glVertexAttrib1d = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1d")) != 0 &
                        (glVertexAttrib2s = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2s")) != 0 &
                        (glVertexAttrib2f = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2f")) != 0 &
                        (glVertexAttrib2d = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2d")) != 0 &
                        (glVertexAttrib3s = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3s")) != 0 &
                        (glVertexAttrib3f = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3f")) != 0 &
                        (glVertexAttrib3d = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3d")) != 0 &
                        (glVertexAttrib4s = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4s")) != 0 &
                        (glVertexAttrib4f = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4f")) != 0 &
                        (glVertexAttrib4d = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4d")) != 0 &
                        (glVertexAttrib4Nub = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4Nub")) != 0 &
                        (glVertexAttribPointer = GL.getFunctionProvider().getFunctionAddress("glVertexAttribPointer")) != 0 &
                        (glEnableVertexAttribArray = GL.getFunctionProvider().getFunctionAddress("glEnableVertexAttribArray")) != 0 &
                        (glDisableVertexAttribArray = GL.getFunctionProvider().getFunctionAddress("glDisableVertexAttribArray")) != 0 &
                        (glGetVertexAttribfv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribfv")) != 0 &
                        (glGetVertexAttribdv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribdv")) != 0 &
                        (glGetVertexAttribiv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribiv")) != 0 &
                        (glGetVertexAttribPointerv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribPointerv")) != 0 &
                        (glBindAttribLocation = GL.getFunctionProvider().getFunctionAddress("glBindAttribLocation")) != 0 &
                        (glGetActiveAttrib = GL.getFunctionProvider().getFunctionAddress("glGetActiveAttrib")) != 0 &
                        (glGetAttribLocation = GL.getFunctionProvider().getFunctionAddress("glGetAttribLocation")) != 0 &
                        (glDrawBuffers = GL.getFunctionProvider().getFunctionAddress("glDrawBuffers")) != 0 &
                        (glStencilOpSeparate = GL.getFunctionProvider().getFunctionAddress("glStencilOpSeparate")) != 0 &
                        (glStencilFuncSeparate = GL.getFunctionProvider().getFunctionAddress("glStencilFuncSeparate")) != 0 &
                        (glStencilMaskSeparate = GL.getFunctionProvider().getFunctionAddress("glStencilMaskSeparate")) != 0 &
                        (glBlendEquationSeparate = GL.getFunctionProvider().getFunctionAddress("glBlendEquationSeparate")) != 0;
    }

    private boolean GL21_initNativeFunctionAddresses() {
        return
                (glUniformMatrix2x3fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2x3fv")) != 0 &
                        (glUniformMatrix3x2fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3x2fv")) != 0 &
                        (glUniformMatrix2x4fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2x4fv")) != 0 &
                        (glUniformMatrix4x2fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4x2fv")) != 0 &
                        (glUniformMatrix3x4fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3x4fv")) != 0 &
                        (glUniformMatrix4x3fv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4x3fv")) != 0;
    }

    private boolean GL30_initNativeFunctionAddresses() {
        return
                (glGetStringi = GL.getFunctionProvider().getFunctionAddress("glGetStringi")) != 0 &
                        (glClearBufferfv = GL.getFunctionProvider().getFunctionAddress("glClearBufferfv")) != 0 &
                        (glClearBufferiv = GL.getFunctionProvider().getFunctionAddress("glClearBufferiv")) != 0 &
                        (glClearBufferuiv = GL.getFunctionProvider().getFunctionAddress("glClearBufferuiv")) != 0 &
                        (glClearBufferfi = GL.getFunctionProvider().getFunctionAddress("glClearBufferfi")) != 0 &
                        (glVertexAttribI1i = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1i")) != 0 &
                        (glVertexAttribI2i = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2i")) != 0 &
                        (glVertexAttribI3i = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3i")) != 0 &
                        (glVertexAttribI4i = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4i")) != 0 &
                        (glVertexAttribI1ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1ui")) != 0 &
                        (glVertexAttribI2ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2ui")) != 0 &
                        (glVertexAttribI3ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3ui")) != 0 &
                        (glVertexAttribI4ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4ui")) != 0 &
                        (glVertexAttribI1iv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1iv")) != 0 &
                        (glVertexAttribI2iv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2iv")) != 0 &
                        (glVertexAttribI3iv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3iv")) != 0 &
                        (glVertexAttribI4iv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4iv")) != 0 &
                        (glVertexAttribI1uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI1uiv")) != 0 &
                        (glVertexAttribI2uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI2uiv")) != 0 &
                        (glVertexAttribI3uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI3uiv")) != 0 &
                        (glVertexAttribI4uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4uiv")) != 0 &
                        (glVertexAttribI4bv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4bv")) != 0 &
                        (glVertexAttribI4sv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4sv")) != 0 &
                        (glVertexAttribI4ubv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4ubv")) != 0 &
                        (glVertexAttribI4usv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribI4usv")) != 0 &
                        (glVertexAttribIPointer = GL.getFunctionProvider().getFunctionAddress("glVertexAttribIPointer")) != 0 &
                        (glGetVertexAttribIiv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribIiv")) != 0 &
                        (glGetVertexAttribIuiv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribIuiv")) != 0 &
                        (glUniform1ui = GL.getFunctionProvider().getFunctionAddress("glUniform1ui")) != 0 &
                        (glUniform2ui = GL.getFunctionProvider().getFunctionAddress("glUniform2ui")) != 0 &
                        (glUniform3ui = GL.getFunctionProvider().getFunctionAddress("glUniform3ui")) != 0 &
                        (glUniform4ui = GL.getFunctionProvider().getFunctionAddress("glUniform4ui")) != 0 &
                        (glUniform1uiv = GL.getFunctionProvider().getFunctionAddress("glUniform1uiv")) != 0 &
                        (glUniform2uiv = GL.getFunctionProvider().getFunctionAddress("glUniform2uiv")) != 0 &
                        (glUniform3uiv = GL.getFunctionProvider().getFunctionAddress("glUniform3uiv")) != 0 &
                        (glUniform4uiv = GL.getFunctionProvider().getFunctionAddress("glUniform4uiv")) != 0 &
                        (glGetUniformuiv = GL.getFunctionProvider().getFunctionAddress("glGetUniformuiv")) != 0 &
                        (glBindFragDataLocation = GL.getFunctionProvider().getFunctionAddress("glBindFragDataLocation")) != 0 &
                        (glGetFragDataLocation = GL.getFunctionProvider().getFunctionAddress("glGetFragDataLocation")) != 0 &
                        (glBeginConditionalRender = GL.getFunctionProvider().getFunctionAddress("glBeginConditionalRender")) != 0 &
                        (glEndConditionalRender = GL.getFunctionProvider().getFunctionAddress("glEndConditionalRender")) != 0 &
                        (glMapBufferRange = GL.getFunctionProvider().getFunctionAddress("glMapBufferRange")) != 0 &
                        (glFlushMappedBufferRange = GL.getFunctionProvider().getFunctionAddress("glFlushMappedBufferRange")) != 0 &
                        (glClampColor = GL.getFunctionProvider().getFunctionAddress("glClampColor")) != 0 &
                        (glIsRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glIsRenderbuffer")) != 0 &
                        (glBindRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glBindRenderbuffer")) != 0 &
                        (glDeleteRenderbuffers = GL.getFunctionProvider().getFunctionAddress("glDeleteRenderbuffers")) != 0 &
                        (glGenRenderbuffers = GL.getFunctionProvider().getFunctionAddress("glGenRenderbuffers")) != 0 &
                        (glRenderbufferStorage = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorage")) != 0 &
                        (glGetRenderbufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetRenderbufferParameteriv")) != 0 &
                        (glIsFramebuffer = GL.getFunctionProvider().getFunctionAddress("glIsFramebuffer")) != 0 &
                        (glBindFramebuffer = GL.getFunctionProvider().getFunctionAddress("glBindFramebuffer")) != 0 &
                        (glDeleteFramebuffers = GL.getFunctionProvider().getFunctionAddress("glDeleteFramebuffers")) != 0 &
                        (glGenFramebuffers = GL.getFunctionProvider().getFunctionAddress("glGenFramebuffers")) != 0 &
                        (glCheckFramebufferStatus = GL.getFunctionProvider().getFunctionAddress("glCheckFramebufferStatus")) != 0 &
                        (glFramebufferTexture1D = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture1D")) != 0 &
                        (glFramebufferTexture2D = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture2D")) != 0 &
                        (glFramebufferTexture3D = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture3D")) != 0 &
                        (glFramebufferRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glFramebufferRenderbuffer")) != 0 &
                        (glGetFramebufferAttachmentParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetFramebufferAttachmentParameteriv")) != 0 &
                        (glGenerateMipmap = GL.getFunctionProvider().getFunctionAddress("glGenerateMipmap")) != 0 &
                        (glRenderbufferStorageMultisample = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorageMultisample")) != 0 &
                        (glBlitFramebuffer = GL.getFunctionProvider().getFunctionAddress("glBlitFramebuffer")) != 0 &
                        (glTexParameterIiv = GL.getFunctionProvider().getFunctionAddress("glTexParameterIiv")) != 0 &
                        (glTexParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glTexParameterIuiv")) != 0 &
                        (glGetTexParameterIiv = GL.getFunctionProvider().getFunctionAddress("glGetTexParameterIiv")) != 0 &
                        (glGetTexParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glGetTexParameterIuiv")) != 0 &
                        (glFramebufferTextureLayer = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureLayer")) != 0 &
                        (glColorMaski = GL.getFunctionProvider().getFunctionAddress("glColorMaski")) != 0 &
                        (glGetBooleani_v = GL.getFunctionProvider().getFunctionAddress("glGetBooleani_v")) != 0 &
                        (glGetIntegeri_v = GL.getFunctionProvider().getFunctionAddress("glGetIntegeri_v")) != 0 &
                        (glEnablei = GL.getFunctionProvider().getFunctionAddress("glEnablei")) != 0 &
                        (glDisablei = GL.getFunctionProvider().getFunctionAddress("glDisablei")) != 0 &
                        (glIsEnabledi = GL.getFunctionProvider().getFunctionAddress("glIsEnabledi")) != 0 &
                        (glBindBufferRange = GL.getFunctionProvider().getFunctionAddress("glBindBufferRange")) != 0 &
                        (glBindBufferBase = GL.getFunctionProvider().getFunctionAddress("glBindBufferBase")) != 0 &
                        (glBeginTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glBeginTransformFeedback")) != 0 &
                        (glEndTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glEndTransformFeedback")) != 0 &
                        (glTransformFeedbackVaryings = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackVaryings")) != 0 &
                        (glGetTransformFeedbackVarying = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbackVarying")) != 0 &
                        (glBindVertexArray = GL.getFunctionProvider().getFunctionAddress("glBindVertexArray")) != 0 &
                        (glDeleteVertexArrays = GL.getFunctionProvider().getFunctionAddress("glDeleteVertexArrays")) != 0 &
                        (glGenVertexArrays = GL.getFunctionProvider().getFunctionAddress("glGenVertexArrays")) != 0 &
                        (glIsVertexArray = GL.getFunctionProvider().getFunctionAddress("glIsVertexArray")) != 0;
    }

    private boolean GL31_initNativeFunctionAddresses() {
        return
                (glDrawArraysInstanced = GL.getFunctionProvider().getFunctionAddress("glDrawArraysInstanced")) != 0 &
                        (glDrawElementsInstanced = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstanced")) != 0 &
                        (glCopyBufferSubData = GL.getFunctionProvider().getFunctionAddress("glCopyBufferSubData")) != 0 &
                        (glPrimitiveRestartIndex = GL.getFunctionProvider().getFunctionAddress("glPrimitiveRestartIndex")) != 0 &
                        (glTexBuffer = GL.getFunctionProvider().getFunctionAddress("glTexBuffer")) != 0 &
                        (glGetUniformIndices = GL.getFunctionProvider().getFunctionAddress("glGetUniformIndices")) != 0 &
                        (glGetActiveUniformsiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformsiv")) != 0 &
                        (glGetActiveUniformName = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformName")) != 0 &
                        (glGetUniformBlockIndex = GL.getFunctionProvider().getFunctionAddress("glGetUniformBlockIndex")) != 0 &
                        (glGetActiveUniformBlockiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformBlockiv")) != 0 &
                        (glGetActiveUniformBlockName = GL.getFunctionProvider().getFunctionAddress("glGetActiveUniformBlockName")) != 0 &
                        (glUniformBlockBinding = GL.getFunctionProvider().getFunctionAddress("glUniformBlockBinding")) != 0;
    }

    private boolean GL32_initNativeFunctionAddresses() {
        return
                (glGetBufferParameteri64v = GL.getFunctionProvider().getFunctionAddress("glGetBufferParameteri64v")) != 0 &
                        (glDrawElementsBaseVertex = GL.getFunctionProvider().getFunctionAddress("glDrawElementsBaseVertex")) != 0 &
                        (glDrawRangeElementsBaseVertex = GL.getFunctionProvider().getFunctionAddress("glDrawRangeElementsBaseVertex")) != 0 &
                        (glDrawElementsInstancedBaseVertex = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedBaseVertex")) != 0 &
                        (glProvokingVertex = GL.getFunctionProvider().getFunctionAddress("glProvokingVertex")) != 0 &
                        (glTexImage2DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexImage2DMultisample")) != 0 &
                        (glTexImage3DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexImage3DMultisample")) != 0 &
                        (glGetMultisamplefv = GL.getFunctionProvider().getFunctionAddress("glGetMultisamplefv")) != 0 &
                        (glSampleMaski = GL.getFunctionProvider().getFunctionAddress("glSampleMaski")) != 0 &
                        (glFramebufferTexture = GL.getFunctionProvider().getFunctionAddress("glFramebufferTexture")) != 0 &
                        (glFenceSync = GL.getFunctionProvider().getFunctionAddress("glFenceSync")) != 0 &
                        (glIsSync = GL.getFunctionProvider().getFunctionAddress("glIsSync")) != 0 &
                        (glDeleteSync = GL.getFunctionProvider().getFunctionAddress("glDeleteSync")) != 0 &
                        (glClientWaitSync = GL.getFunctionProvider().getFunctionAddress("glClientWaitSync")) != 0 &
                        (glWaitSync = GL.getFunctionProvider().getFunctionAddress("glWaitSync")) != 0 &
                        (glGetInteger64v = GL.getFunctionProvider().getFunctionAddress("glGetInteger64v")) != 0 &
                        (glGetInteger64i_v = GL.getFunctionProvider().getFunctionAddress("glGetInteger64i_v")) != 0 &
                        (glGetSynciv = GL.getFunctionProvider().getFunctionAddress("glGetSynciv")) != 0;
    }

    private boolean GL33_initNativeFunctionAddresses(boolean forwardCompatible) {
        return
                (glBindFragDataLocationIndexed = GL.getFunctionProvider().getFunctionAddress("glBindFragDataLocationIndexed")) != 0 &
                        (glGetFragDataIndex = GL.getFunctionProvider().getFunctionAddress("glGetFragDataIndex")) != 0 &
                        (glGenSamplers = GL.getFunctionProvider().getFunctionAddress("glGenSamplers")) != 0 &
                        (glDeleteSamplers = GL.getFunctionProvider().getFunctionAddress("glDeleteSamplers")) != 0 &
                        (glIsSampler = GL.getFunctionProvider().getFunctionAddress("glIsSampler")) != 0 &
                        (glBindSampler = GL.getFunctionProvider().getFunctionAddress("glBindSampler")) != 0 &
                        (glSamplerParameteri = GL.getFunctionProvider().getFunctionAddress("glSamplerParameteri")) != 0 &
                        (glSamplerParameterf = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterf")) != 0 &
                        (glSamplerParameteriv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameteriv")) != 0 &
                        (glSamplerParameterfv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterfv")) != 0 &
                        (glSamplerParameterIiv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterIiv")) != 0 &
                        (glSamplerParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glSamplerParameterIuiv")) != 0 &
                        (glGetSamplerParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameteriv")) != 0 &
                        (glGetSamplerParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameterfv")) != 0 &
                        (glGetSamplerParameterIiv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameterIiv")) != 0 &
                        (glGetSamplerParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glGetSamplerParameterIuiv")) != 0 &
                        (glQueryCounter = GL.getFunctionProvider().getFunctionAddress("glQueryCounter")) != 0 &
                        (glGetQueryObjecti64v = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjecti64v")) != 0 &
                        (glGetQueryObjectui64v = GL.getFunctionProvider().getFunctionAddress("glGetQueryObjectui64v")) != 0 &
                        (glVertexAttribDivisor = GL.getFunctionProvider().getFunctionAddress("glVertexAttribDivisor")) != 0 &
                        (forwardCompatible || (glVertexP2ui = GL.getFunctionProvider().getFunctionAddress("glVertexP2ui")) != 0) &
                        (forwardCompatible || (glVertexP3ui = GL.getFunctionProvider().getFunctionAddress("glVertexP3ui")) != 0) &
                        (forwardCompatible || (glVertexP4ui = GL.getFunctionProvider().getFunctionAddress("glVertexP4ui")) != 0) &
                        (forwardCompatible || (glVertexP2uiv = GL.getFunctionProvider().getFunctionAddress("glVertexP2uiv")) != 0) &
                        (forwardCompatible || (glVertexP3uiv = GL.getFunctionProvider().getFunctionAddress("glVertexP3uiv")) != 0) &
                        (forwardCompatible || (glVertexP4uiv = GL.getFunctionProvider().getFunctionAddress("glVertexP4uiv")) != 0) &
                        (forwardCompatible || (glTexCoordP1ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP1ui")) != 0) &
                        (forwardCompatible || (glTexCoordP2ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP2ui")) != 0) &
                        (forwardCompatible || (glTexCoordP3ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP3ui")) != 0) &
                        (forwardCompatible || (glTexCoordP4ui = GL.getFunctionProvider().getFunctionAddress("glTexCoordP4ui")) != 0) &
                        (forwardCompatible || (glTexCoordP1uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP1uiv")) != 0) &
                        (forwardCompatible || (glTexCoordP2uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP2uiv")) != 0) &
                        (forwardCompatible || (glTexCoordP3uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP3uiv")) != 0) &
                        (forwardCompatible || (glTexCoordP4uiv = GL.getFunctionProvider().getFunctionAddress("glTexCoordP4uiv")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP1ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP1ui")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP2ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP2ui")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP3ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP3ui")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP4ui = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP4ui")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP1uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP1uiv")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP2uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP2uiv")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP3uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP3uiv")) != 0) &
                        (forwardCompatible || (glMultiTexCoordP4uiv = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoordP4uiv")) != 0) &
                        (forwardCompatible || (glNormalP3ui = GL.getFunctionProvider().getFunctionAddress("glNormalP3ui")) != 0) &
                        (forwardCompatible || (glNormalP3uiv = GL.getFunctionProvider().getFunctionAddress("glNormalP3uiv")) != 0) &
                        (forwardCompatible || (glColorP3ui = GL.getFunctionProvider().getFunctionAddress("glColorP3ui")) != 0) &
                        (forwardCompatible || (glColorP4ui = GL.getFunctionProvider().getFunctionAddress("glColorP4ui")) != 0) &
                        (forwardCompatible || (glColorP3uiv = GL.getFunctionProvider().getFunctionAddress("glColorP3uiv")) != 0) &
                        (forwardCompatible || (glColorP4uiv = GL.getFunctionProvider().getFunctionAddress("glColorP4uiv")) != 0) &
                        (forwardCompatible || (glSecondaryColorP3ui = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorP3ui")) != 0) &
                        (forwardCompatible || (glSecondaryColorP3uiv = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorP3uiv")) != 0) &
                        (forwardCompatible || (glVertexAttribP1ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP1ui")) != 0) &
                        (forwardCompatible || (glVertexAttribP2ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP2ui")) != 0) &
                        (forwardCompatible || (glVertexAttribP3ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP3ui")) != 0) &
                        (forwardCompatible || (glVertexAttribP4ui = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP4ui")) != 0) &
                        (forwardCompatible || (glVertexAttribP1uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP1uiv")) != 0) &
                        (forwardCompatible || (glVertexAttribP2uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP2uiv")) != 0) &
                        (forwardCompatible || (glVertexAttribP3uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP3uiv")) != 0) &
                        (forwardCompatible || (glVertexAttribP4uiv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribP4uiv")) != 0);
    }

    private boolean GL40_initNativeFunctionAddresses() {
        return
                (glBlendEquationi = GL.getFunctionProvider().getFunctionAddress("glBlendEquationi")) != 0 &
                        (glBlendEquationSeparatei = GL.getFunctionProvider().getFunctionAddress("glBlendEquationSeparatei")) != 0 &
                        (glBlendFunci = GL.getFunctionProvider().getFunctionAddress("glBlendFunci")) != 0 &
                        (glBlendFuncSeparatei = GL.getFunctionProvider().getFunctionAddress("glBlendFuncSeparatei")) != 0 &
                        (glDrawArraysIndirect = GL.getFunctionProvider().getFunctionAddress("glDrawArraysIndirect")) != 0 &
                        (glDrawElementsIndirect = GL.getFunctionProvider().getFunctionAddress("glDrawElementsIndirect")) != 0 &
                        (glUniform1d = GL.getFunctionProvider().getFunctionAddress("glUniform1d")) != 0 &
                        (glUniform2d = GL.getFunctionProvider().getFunctionAddress("glUniform2d")) != 0 &
                        (glUniform3d = GL.getFunctionProvider().getFunctionAddress("glUniform3d")) != 0 &
                        (glUniform4d = GL.getFunctionProvider().getFunctionAddress("glUniform4d")) != 0 &
                        (glUniform1dv = GL.getFunctionProvider().getFunctionAddress("glUniform1dv")) != 0 &
                        (glUniform2dv = GL.getFunctionProvider().getFunctionAddress("glUniform2dv")) != 0 &
                        (glUniform3dv = GL.getFunctionProvider().getFunctionAddress("glUniform3dv")) != 0 &
                        (glUniform4dv = GL.getFunctionProvider().getFunctionAddress("glUniform4dv")) != 0 &
                        (glUniformMatrix2dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2dv")) != 0 &
                        (glUniformMatrix3dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3dv")) != 0 &
                        (glUniformMatrix4dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4dv")) != 0 &
                        (glUniformMatrix2x3dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2x3dv")) != 0 &
                        (glUniformMatrix2x4dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix2x4dv")) != 0 &
                        (glUniformMatrix3x2dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3x2dv")) != 0 &
                        (glUniformMatrix3x4dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix3x4dv")) != 0 &
                        (glUniformMatrix4x2dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4x2dv")) != 0 &
                        (glUniformMatrix4x3dv = GL.getFunctionProvider().getFunctionAddress("glUniformMatrix4x3dv")) != 0 &
                        (glGetUniformdv = GL.getFunctionProvider().getFunctionAddress("glGetUniformdv")) != 0 &
                        (glMinSampleShading = GL.getFunctionProvider().getFunctionAddress("glMinSampleShading")) != 0 &
                        (glGetSubroutineUniformLocation = GL.getFunctionProvider().getFunctionAddress("glGetSubroutineUniformLocation")) != 0 &
                        (glGetSubroutineIndex = GL.getFunctionProvider().getFunctionAddress("glGetSubroutineIndex")) != 0 &
                        (glGetActiveSubroutineUniformiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveSubroutineUniformiv")) != 0 &
                        (glGetActiveSubroutineUniformName = GL.getFunctionProvider().getFunctionAddress("glGetActiveSubroutineUniformName")) != 0 &
                        (glGetActiveSubroutineName = GL.getFunctionProvider().getFunctionAddress("glGetActiveSubroutineName")) != 0 &
                        (glUniformSubroutinesuiv = GL.getFunctionProvider().getFunctionAddress("glUniformSubroutinesuiv")) != 0 &
                        (glGetUniformSubroutineuiv = GL.getFunctionProvider().getFunctionAddress("glGetUniformSubroutineuiv")) != 0 &
                        (glGetProgramStageiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramStageiv")) != 0 &
                        (glPatchParameteri = GL.getFunctionProvider().getFunctionAddress("glPatchParameteri")) != 0 &
                        (glPatchParameterfv = GL.getFunctionProvider().getFunctionAddress("glPatchParameterfv")) != 0 &
                        (glBindTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glBindTransformFeedback")) != 0 &
                        (glDeleteTransformFeedbacks = GL.getFunctionProvider().getFunctionAddress("glDeleteTransformFeedbacks")) != 0 &
                        (glGenTransformFeedbacks = GL.getFunctionProvider().getFunctionAddress("glGenTransformFeedbacks")) != 0 &
                        (glIsTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glIsTransformFeedback")) != 0 &
                        (glPauseTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glPauseTransformFeedback")) != 0 &
                        (glResumeTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glResumeTransformFeedback")) != 0 &
                        (glDrawTransformFeedback = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedback")) != 0 &
                        (glDrawTransformFeedbackStream = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackStream")) != 0 &
                        (glBeginQueryIndexed = GL.getFunctionProvider().getFunctionAddress("glBeginQueryIndexed")) != 0 &
                        (glEndQueryIndexed = GL.getFunctionProvider().getFunctionAddress("glEndQueryIndexed")) != 0 &
                        (glGetQueryIndexediv = GL.getFunctionProvider().getFunctionAddress("glGetQueryIndexediv")) != 0;
    }

    private boolean GL41_initNativeFunctionAddresses() {
        return
                (glReleaseShaderCompiler = GL.getFunctionProvider().getFunctionAddress("glReleaseShaderCompiler")) != 0 &
                        (glShaderBinary = GL.getFunctionProvider().getFunctionAddress("glShaderBinary")) != 0 &
                        (glGetShaderPrecisionFormat = GL.getFunctionProvider().getFunctionAddress("glGetShaderPrecisionFormat")) != 0 &
                        (glDepthRangef = GL.getFunctionProvider().getFunctionAddress("glDepthRangef")) != 0 &
                        (glClearDepthf = GL.getFunctionProvider().getFunctionAddress("glClearDepthf")) != 0 &
                        (glGetProgramBinary = GL.getFunctionProvider().getFunctionAddress("glGetProgramBinary")) != 0 &
                        (glProgramBinary = GL.getFunctionProvider().getFunctionAddress("glProgramBinary")) != 0 &
                        (glProgramParameteri = GL.getFunctionProvider().getFunctionAddress("glProgramParameteri")) != 0 &
                        (glUseProgramStages = GL.getFunctionProvider().getFunctionAddress("glUseProgramStages")) != 0 &
                        (glActiveShaderProgram = GL.getFunctionProvider().getFunctionAddress("glActiveShaderProgram")) != 0 &
                        (glCreateShaderProgramv = GL.getFunctionProvider().getFunctionAddress("glCreateShaderProgramv")) != 0 &
                        (glBindProgramPipeline = GL.getFunctionProvider().getFunctionAddress("glBindProgramPipeline")) != 0 &
                        (glDeleteProgramPipelines = GL.getFunctionProvider().getFunctionAddress("glDeleteProgramPipelines")) != 0 &
                        (glGenProgramPipelines = GL.getFunctionProvider().getFunctionAddress("glGenProgramPipelines")) != 0 &
                        (glIsProgramPipeline = GL.getFunctionProvider().getFunctionAddress("glIsProgramPipeline")) != 0 &
                        (glGetProgramPipelineiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramPipelineiv")) != 0 &
                        (glProgramUniform1i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1i")) != 0 &
                        (glProgramUniform2i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2i")) != 0 &
                        (glProgramUniform3i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3i")) != 0 &
                        (glProgramUniform4i = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4i")) != 0 &
                        (glProgramUniform1f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1f")) != 0 &
                        (glProgramUniform2f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2f")) != 0 &
                        (glProgramUniform3f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3f")) != 0 &
                        (glProgramUniform4f = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4f")) != 0 &
                        (glProgramUniform1d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1d")) != 0 &
                        (glProgramUniform2d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2d")) != 0 &
                        (glProgramUniform3d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3d")) != 0 &
                        (glProgramUniform4d = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4d")) != 0 &
                        (glProgramUniform1iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1iv")) != 0 &
                        (glProgramUniform2iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2iv")) != 0 &
                        (glProgramUniform3iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3iv")) != 0 &
                        (glProgramUniform4iv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4iv")) != 0 &
                        (glProgramUniform1fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1fv")) != 0 &
                        (glProgramUniform2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2fv")) != 0 &
                        (glProgramUniform3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3fv")) != 0 &
                        (glProgramUniform4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4fv")) != 0 &
                        (glProgramUniform1dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1dv")) != 0 &
                        (glProgramUniform2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2dv")) != 0 &
                        (glProgramUniform3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3dv")) != 0 &
                        (glProgramUniform4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4dv")) != 0 &
                        (glProgramUniform1ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1ui")) != 0 &
                        (glProgramUniform2ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2ui")) != 0 &
                        (glProgramUniform3ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3ui")) != 0 &
                        (glProgramUniform4ui = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4ui")) != 0 &
                        (glProgramUniform1uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1uiv")) != 0 &
                        (glProgramUniform2uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2uiv")) != 0 &
                        (glProgramUniform3uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3uiv")) != 0 &
                        (glProgramUniform4uiv = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4uiv")) != 0 &
                        (glProgramUniformMatrix2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2fv")) != 0 &
                        (glProgramUniformMatrix3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3fv")) != 0 &
                        (glProgramUniformMatrix4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4fv")) != 0 &
                        (glProgramUniformMatrix2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2dv")) != 0 &
                        (glProgramUniformMatrix3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3dv")) != 0 &
                        (glProgramUniformMatrix4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4dv")) != 0 &
                        (glProgramUniformMatrix2x3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x3fv")) != 0 &
                        (glProgramUniformMatrix3x2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x2fv")) != 0 &
                        (glProgramUniformMatrix2x4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x4fv")) != 0 &
                        (glProgramUniformMatrix4x2fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x2fv")) != 0 &
                        (glProgramUniformMatrix3x4fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x4fv")) != 0 &
                        (glProgramUniformMatrix4x3fv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x3fv")) != 0 &
                        (glProgramUniformMatrix2x3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x3dv")) != 0 &
                        (glProgramUniformMatrix3x2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x2dv")) != 0 &
                        (glProgramUniformMatrix2x4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix2x4dv")) != 0 &
                        (glProgramUniformMatrix4x2dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x2dv")) != 0 &
                        (glProgramUniformMatrix3x4dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix3x4dv")) != 0 &
                        (glProgramUniformMatrix4x3dv = GL.getFunctionProvider().getFunctionAddress("glProgramUniformMatrix4x3dv")) != 0 &
                        (glValidateProgramPipeline = GL.getFunctionProvider().getFunctionAddress("glValidateProgramPipeline")) != 0 &
                        (glGetProgramPipelineInfoLog = GL.getFunctionProvider().getFunctionAddress("glGetProgramPipelineInfoLog")) != 0 &
                        (glVertexAttribL1d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1d")) != 0 &
                        (glVertexAttribL2d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2d")) != 0 &
                        (glVertexAttribL3d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3d")) != 0 &
                        (glVertexAttribL4d = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4d")) != 0 &
                        (glVertexAttribL1dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1dv")) != 0 &
                        (glVertexAttribL2dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2dv")) != 0 &
                        (glVertexAttribL3dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3dv")) != 0 &
                        (glVertexAttribL4dv = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4dv")) != 0 &
                        (glVertexAttribLPointer = GL.getFunctionProvider().getFunctionAddress("glVertexAttribLPointer")) != 0 &
                        (glGetVertexAttribLdv = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribLdv")) != 0 &
                        (glViewportArrayv = GL.getFunctionProvider().getFunctionAddress("glViewportArrayv")) != 0 &
                        (glViewportIndexedf = GL.getFunctionProvider().getFunctionAddress("glViewportIndexedf")) != 0 &
                        (glViewportIndexedfv = GL.getFunctionProvider().getFunctionAddress("glViewportIndexedfv")) != 0 &
                        (glScissorArrayv = GL.getFunctionProvider().getFunctionAddress("glScissorArrayv")) != 0 &
                        (glScissorIndexed = GL.getFunctionProvider().getFunctionAddress("glScissorIndexed")) != 0 &
                        (glScissorIndexedv = GL.getFunctionProvider().getFunctionAddress("glScissorIndexedv")) != 0 &
                        (glDepthRangeArrayv = GL.getFunctionProvider().getFunctionAddress("glDepthRangeArrayv")) != 0 &
                        (glDepthRangeIndexed = GL.getFunctionProvider().getFunctionAddress("glDepthRangeIndexed")) != 0 &
                        (glGetFloati_v = GL.getFunctionProvider().getFunctionAddress("glGetFloati_v")) != 0 &
                        (glGetDoublei_v = GL.getFunctionProvider().getFunctionAddress("glGetDoublei_v")) != 0;
    }

    private boolean GL42_initNativeFunctionAddresses() {
        return
                (glGetActiveAtomicCounterBufferiv = GL.getFunctionProvider().getFunctionAddress("glGetActiveAtomicCounterBufferiv")) != 0 &
                        (glTexStorage1D = GL.getFunctionProvider().getFunctionAddress("glTexStorage1D")) != 0 &
                        (glTexStorage2D = GL.getFunctionProvider().getFunctionAddress("glTexStorage2D")) != 0 &
                        (glTexStorage3D = GL.getFunctionProvider().getFunctionAddress("glTexStorage3D")) != 0 &
                        (glDrawTransformFeedbackInstanced = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackInstanced")) != 0 &
                        (glDrawTransformFeedbackStreamInstanced = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackStreamInstanced")) != 0 &
                        (glDrawArraysInstancedBaseInstance = GL.getFunctionProvider().getFunctionAddress("glDrawArraysInstancedBaseInstance")) != 0 &
                        (glDrawElementsInstancedBaseInstance = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedBaseInstance")) != 0 &
                        (glDrawElementsInstancedBaseVertexBaseInstance = GL.getFunctionProvider().getFunctionAddress("glDrawElementsInstancedBaseVertexBaseInstance")) != 0 &
                        (glBindImageTexture = GL.getFunctionProvider().getFunctionAddress("glBindImageTexture")) != 0 &
                        (glMemoryBarrier = GL.getFunctionProvider().getFunctionAddress("glMemoryBarrier")) != 0 &
                        (glGetInternalformativ = GL.getFunctionProvider().getFunctionAddress("glGetInternalformativ")) != 0;
    }

    private boolean GL43_initNativeFunctionAddresses() {
        return
                (glClearBufferData = GL.getFunctionProvider().getFunctionAddress("glClearBufferData")) != 0 &
                        (glClearBufferSubData = GL.getFunctionProvider().getFunctionAddress("glClearBufferSubData")) != 0 &
                        (glDispatchCompute = GL.getFunctionProvider().getFunctionAddress("glDispatchCompute")) != 0 &
                        (glDispatchComputeIndirect = GL.getFunctionProvider().getFunctionAddress("glDispatchComputeIndirect")) != 0 &
                        (glCopyImageSubData = GL.getFunctionProvider().getFunctionAddress("glCopyImageSubData")) != 0 &
                        (glDebugMessageControl = GL.getFunctionProvider().getFunctionAddress("glDebugMessageControl")) != 0 &
                        (glDebugMessageInsert = GL.getFunctionProvider().getFunctionAddress("glDebugMessageInsert")) != 0 &
                        (glDebugMessageCallback = GL.getFunctionProvider().getFunctionAddress("glDebugMessageCallback")) != 0 &
                        (glGetDebugMessageLog = GL.getFunctionProvider().getFunctionAddress("glGetDebugMessageLog")) != 0 &
                        (glPushDebugGroup = GL.getFunctionProvider().getFunctionAddress("glPushDebugGroup")) != 0 &
                        (glPopDebugGroup = GL.getFunctionProvider().getFunctionAddress("glPopDebugGroup")) != 0 &
                        (glObjectLabel = GL.getFunctionProvider().getFunctionAddress("glObjectLabel")) != 0 &
                        (glGetObjectLabel = GL.getFunctionProvider().getFunctionAddress("glGetObjectLabel")) != 0 &
                        (glObjectPtrLabel = GL.getFunctionProvider().getFunctionAddress("glObjectPtrLabel")) != 0 &
                        (glGetObjectPtrLabel = GL.getFunctionProvider().getFunctionAddress("glGetObjectPtrLabel")) != 0 &
                        (glFramebufferParameteri = GL.getFunctionProvider().getFunctionAddress("glFramebufferParameteri")) != 0 &
                        (glGetFramebufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetFramebufferParameteriv")) != 0 &
                        (glGetInternalformati64v = GL.getFunctionProvider().getFunctionAddress("glGetInternalformati64v")) != 0 &
                        (glInvalidateTexSubImage = GL.getFunctionProvider().getFunctionAddress("glInvalidateTexSubImage")) != 0 &
                        (glInvalidateTexImage = GL.getFunctionProvider().getFunctionAddress("glInvalidateTexImage")) != 0 &
                        (glInvalidateBufferSubData = GL.getFunctionProvider().getFunctionAddress("glInvalidateBufferSubData")) != 0 &
                        (glInvalidateBufferData = GL.getFunctionProvider().getFunctionAddress("glInvalidateBufferData")) != 0 &
                        (glInvalidateFramebuffer = GL.getFunctionProvider().getFunctionAddress("glInvalidateFramebuffer")) != 0 &
                        (glInvalidateSubFramebuffer = GL.getFunctionProvider().getFunctionAddress("glInvalidateSubFramebuffer")) != 0 &
                        (glMultiDrawArraysIndirect = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArraysIndirect")) != 0 &
                        (glMultiDrawElementsIndirect = GL.getFunctionProvider().getFunctionAddress("glMultiDrawElementsIndirect")) != 0 &
                        (glGetProgramInterfaceiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramInterfaceiv")) != 0 &
                        (glGetProgramResourceIndex = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceIndex")) != 0 &
                        (glGetProgramResourceName = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceName")) != 0 &
                        (glGetProgramResourceiv = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceiv")) != 0 &
                        (glGetProgramResourceLocation = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceLocation")) != 0 &
                        (glGetProgramResourceLocationIndex = GL.getFunctionProvider().getFunctionAddress("glGetProgramResourceLocationIndex")) != 0 &
                        (glShaderStorageBlockBinding = GL.getFunctionProvider().getFunctionAddress("glShaderStorageBlockBinding")) != 0 &
                        (glTexBufferRange = GL.getFunctionProvider().getFunctionAddress("glTexBufferRange")) != 0 &
                        (glTexStorage2DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexStorage2DMultisample")) != 0 &
                        (glTexStorage3DMultisample = GL.getFunctionProvider().getFunctionAddress("glTexStorage3DMultisample")) != 0 &
                        (glTextureView = GL.getFunctionProvider().getFunctionAddress("glTextureView")) != 0 &
                        (glBindVertexBuffer = GL.getFunctionProvider().getFunctionAddress("glBindVertexBuffer")) != 0 &
                        (glVertexAttribFormat = GL.getFunctionProvider().getFunctionAddress("glVertexAttribFormat")) != 0 &
                        (glVertexAttribIFormat = GL.getFunctionProvider().getFunctionAddress("glVertexAttribIFormat")) != 0 &
                        (glVertexAttribLFormat = GL.getFunctionProvider().getFunctionAddress("glVertexAttribLFormat")) != 0 &
                        (glVertexAttribBinding = GL.getFunctionProvider().getFunctionAddress("glVertexAttribBinding")) != 0 &
                        (glVertexBindingDivisor = GL.getFunctionProvider().getFunctionAddress("glVertexBindingDivisor")) != 0;
    }

    private boolean GL44_initNativeFunctionAddresses() {
        return
                (glBufferStorage = GL.getFunctionProvider().getFunctionAddress("glBufferStorage")) != 0 &
                        (glClearTexImage = GL.getFunctionProvider().getFunctionAddress("glClearTexImage")) != 0 &
                        (glClearTexSubImage = GL.getFunctionProvider().getFunctionAddress("glClearTexSubImage")) != 0 &
                        (glBindBuffersBase = GL.getFunctionProvider().getFunctionAddress("glBindBuffersBase")) != 0 &
                        (glBindBuffersRange = GL.getFunctionProvider().getFunctionAddress("glBindBuffersRange")) != 0 &
                        (glBindTextures = GL.getFunctionProvider().getFunctionAddress("glBindTextures")) != 0 &
                        (glBindSamplers = GL.getFunctionProvider().getFunctionAddress("glBindSamplers")) != 0 &
                        (glBindImageTextures = GL.getFunctionProvider().getFunctionAddress("glBindImageTextures")) != 0 &
                        (glBindVertexBuffers = GL.getFunctionProvider().getFunctionAddress("glBindVertexBuffers")) != 0;
    }

    private boolean GL45_initNativeFunctionAddresses() {
        return
                (glClipControl = GL.getFunctionProvider().getFunctionAddress("glClipControl")) != 0 &
                        (glCreateTransformFeedbacks = GL.getFunctionProvider().getFunctionAddress("glCreateTransformFeedbacks")) != 0 &
                        (glTransformFeedbackBufferBase = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackBufferBase")) != 0 &
                        (glTransformFeedbackBufferRange = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackBufferRange")) != 0 &
                        (glGetTransformFeedbackiv = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbackiv")) != 0 &
                        (glGetTransformFeedbacki_v = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbacki_v")) != 0 &
                        (glGetTransformFeedbacki64_v = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbacki64_v")) != 0 &
                        (glCreateBuffers = GL.getFunctionProvider().getFunctionAddress("glCreateBuffers")) != 0 &
                        (glNamedBufferStorage = GL.getFunctionProvider().getFunctionAddress("glNamedBufferStorage")) != 0 &
                        (glNamedBufferData = GL.getFunctionProvider().getFunctionAddress("glNamedBufferData")) != 0 &
                        (glNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glNamedBufferSubData")) != 0 &
                        (glCopyNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glCopyNamedBufferSubData")) != 0 &
                        (glClearNamedBufferData = GL.getFunctionProvider().getFunctionAddress("glClearNamedBufferData")) != 0 &
                        (glClearNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glClearNamedBufferSubData")) != 0 &
                        (glMapNamedBuffer = GL.getFunctionProvider().getFunctionAddress("glMapNamedBuffer")) != 0 &
                        (glMapNamedBufferRange = GL.getFunctionProvider().getFunctionAddress("glMapNamedBufferRange")) != 0 &
                        (glUnmapNamedBuffer = GL.getFunctionProvider().getFunctionAddress("glUnmapNamedBuffer")) != 0 &
                        (glFlushMappedNamedBufferRange = GL.getFunctionProvider().getFunctionAddress("glFlushMappedNamedBufferRange")) != 0 &
                        (glGetNamedBufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferParameteriv")) != 0 &
                        (glGetNamedBufferParameteri64v = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferParameteri64v")) != 0 &
                        (glGetNamedBufferPointerv = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferPointerv")) != 0 &
                        (glGetNamedBufferSubData = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferSubData")) != 0 &
                        (glCreateFramebuffers = GL.getFunctionProvider().getFunctionAddress("glCreateFramebuffers")) != 0 &
                        (glNamedFramebufferRenderbuffer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferRenderbuffer")) != 0 &
                        (glNamedFramebufferParameteri = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferParameteri")) != 0 &
                        (glNamedFramebufferTexture = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTexture")) != 0 &
                        (glNamedFramebufferTextureLayer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferTextureLayer")) != 0 &
                        (glNamedFramebufferDrawBuffer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferDrawBuffer")) != 0 &
                        (glNamedFramebufferDrawBuffers = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferDrawBuffers")) != 0 &
                        (glNamedFramebufferReadBuffer = GL.getFunctionProvider().getFunctionAddress("glNamedFramebufferReadBuffer")) != 0 &
                        (glInvalidateNamedFramebufferData = GL.getFunctionProvider().getFunctionAddress("glInvalidateNamedFramebufferData")) != 0 &
                        (glInvalidateNamedFramebufferSubData = GL.getFunctionProvider().getFunctionAddress("glInvalidateNamedFramebufferSubData")) != 0 &
                        (glClearNamedFramebufferiv = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferiv")) != 0 &
                        (glClearNamedFramebufferuiv = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferuiv")) != 0 &
                        (glClearNamedFramebufferfv = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferfv")) != 0 &
                        (glClearNamedFramebufferfi = GL.getFunctionProvider().getFunctionAddress("glClearNamedFramebufferfi")) != 0 &
                        (glBlitNamedFramebuffer = GL.getFunctionProvider().getFunctionAddress("glBlitNamedFramebuffer")) != 0 &
                        (glCheckNamedFramebufferStatus = GL.getFunctionProvider().getFunctionAddress("glCheckNamedFramebufferStatus")) != 0 &
                        (glGetNamedFramebufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedFramebufferParameteriv")) != 0 &
                        (glGetNamedFramebufferAttachmentParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedFramebufferAttachmentParameteriv")) != 0 &
                        (glCreateRenderbuffers = GL.getFunctionProvider().getFunctionAddress("glCreateRenderbuffers")) != 0 &
                        (glNamedRenderbufferStorage = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorage")) != 0 &
                        (glNamedRenderbufferStorageMultisample = GL.getFunctionProvider().getFunctionAddress("glNamedRenderbufferStorageMultisample")) != 0 &
                        (glGetNamedRenderbufferParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetNamedRenderbufferParameteriv")) != 0 &
                        (glCreateTextures = GL.getFunctionProvider().getFunctionAddress("glCreateTextures")) != 0 &
                        (glTextureBuffer = GL.getFunctionProvider().getFunctionAddress("glTextureBuffer")) != 0 &
                        (glTextureBufferRange = GL.getFunctionProvider().getFunctionAddress("glTextureBufferRange")) != 0 &
                        (glTextureStorage1D = GL.getFunctionProvider().getFunctionAddress("glTextureStorage1D")) != 0 &
                        (glTextureStorage2D = GL.getFunctionProvider().getFunctionAddress("glTextureStorage2D")) != 0 &
                        (glTextureStorage3D = GL.getFunctionProvider().getFunctionAddress("glTextureStorage3D")) != 0 &
                        (glTextureStorage2DMultisample = GL.getFunctionProvider().getFunctionAddress("glTextureStorage2DMultisample")) != 0 &
                        (glTextureStorage3DMultisample = GL.getFunctionProvider().getFunctionAddress("glTextureStorage3DMultisample")) != 0 &
                        (glTextureSubImage1D = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage1D")) != 0 &
                        (glTextureSubImage2D = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage2D")) != 0 &
                        (glTextureSubImage3D = GL.getFunctionProvider().getFunctionAddress("glTextureSubImage3D")) != 0 &
                        (glCompressedTextureSubImage1D = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage1D")) != 0 &
                        (glCompressedTextureSubImage2D = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage2D")) != 0 &
                        (glCompressedTextureSubImage3D = GL.getFunctionProvider().getFunctionAddress("glCompressedTextureSubImage3D")) != 0 &
                        (glCopyTextureSubImage1D = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage1D")) != 0 &
                        (glCopyTextureSubImage2D = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage2D")) != 0 &
                        (glCopyTextureSubImage3D = GL.getFunctionProvider().getFunctionAddress("glCopyTextureSubImage3D")) != 0 &
                        (glTextureParameterf = GL.getFunctionProvider().getFunctionAddress("glTextureParameterf")) != 0 &
                        (glTextureParameterfv = GL.getFunctionProvider().getFunctionAddress("glTextureParameterfv")) != 0 &
                        (glTextureParameteri = GL.getFunctionProvider().getFunctionAddress("glTextureParameteri")) != 0 &
                        (glTextureParameterIiv = GL.getFunctionProvider().getFunctionAddress("glTextureParameterIiv")) != 0 &
                        (glTextureParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glTextureParameterIuiv")) != 0 &
                        (glTextureParameteriv = GL.getFunctionProvider().getFunctionAddress("glTextureParameteriv")) != 0 &
                        (glGenerateTextureMipmap = GL.getFunctionProvider().getFunctionAddress("glGenerateTextureMipmap")) != 0 &
                        (glBindTextureUnit = GL.getFunctionProvider().getFunctionAddress("glBindTextureUnit")) != 0 &
                        (glGetTextureImage = GL.getFunctionProvider().getFunctionAddress("glGetTextureImage")) != 0 &
                        (glGetCompressedTextureImage = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTextureImage")) != 0 &
                        (glGetTextureLevelParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetTextureLevelParameterfv")) != 0 &
                        (glGetTextureLevelParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetTextureLevelParameteriv")) != 0 &
                        (glGetTextureParameterfv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterfv")) != 0 &
                        (glGetTextureParameterIiv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterIiv")) != 0 &
                        (glGetTextureParameterIuiv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameterIuiv")) != 0 &
                        (glGetTextureParameteriv = GL.getFunctionProvider().getFunctionAddress("glGetTextureParameteriv")) != 0 &
                        (glCreateVertexArrays = GL.getFunctionProvider().getFunctionAddress("glCreateVertexArrays")) != 0 &
                        (glDisableVertexArrayAttrib = GL.getFunctionProvider().getFunctionAddress("glDisableVertexArrayAttrib")) != 0 &
                        (glEnableVertexArrayAttrib = GL.getFunctionProvider().getFunctionAddress("glEnableVertexArrayAttrib")) != 0 &
                        (glVertexArrayElementBuffer = GL.getFunctionProvider().getFunctionAddress("glVertexArrayElementBuffer")) != 0 &
                        (glVertexArrayVertexBuffer = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexBuffer")) != 0 &
                        (glVertexArrayVertexBuffers = GL.getFunctionProvider().getFunctionAddress("glVertexArrayVertexBuffers")) != 0 &
                        (glVertexArrayAttribFormat = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribFormat")) != 0 &
                        (glVertexArrayAttribIFormat = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribIFormat")) != 0 &
                        (glVertexArrayAttribLFormat = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribLFormat")) != 0 &
                        (glVertexArrayAttribBinding = GL.getFunctionProvider().getFunctionAddress("glVertexArrayAttribBinding")) != 0 &
                        (glVertexArrayBindingDivisor = GL.getFunctionProvider().getFunctionAddress("glVertexArrayBindingDivisor")) != 0 &
                        (glGetVertexArrayiv = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayiv")) != 0 &
                        (glGetVertexArrayIndexediv = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayIndexediv")) != 0 &
                        (glGetVertexArrayIndexed64iv = GL.getFunctionProvider().getFunctionAddress("glGetVertexArrayIndexed64iv")) != 0 &
                        (glCreateSamplers = GL.getFunctionProvider().getFunctionAddress("glCreateSamplers")) != 0 &
                        (glCreateProgramPipelines = GL.getFunctionProvider().getFunctionAddress("glCreateProgramPipelines")) != 0 &
                        (glCreateQueries = GL.getFunctionProvider().getFunctionAddress("glCreateQueries")) != 0 &
                        (glMemoryBarrierByRegion = GL.getFunctionProvider().getFunctionAddress("glMemoryBarrierByRegion")) != 0 &
                        (glGetTextureSubImage = GL.getFunctionProvider().getFunctionAddress("glGetTextureSubImage")) != 0 &
                        (glGetCompressedTextureSubImage = GL.getFunctionProvider().getFunctionAddress("glGetCompressedTextureSubImage")) != 0 &
                        (glTextureBarrier = GL.getFunctionProvider().getFunctionAddress("glTextureBarrier")) != 0 &
                        (glGetGraphicsResetStatus = GL.getFunctionProvider().getFunctionAddress("glGetGraphicsResetStatus")) != 0 &
                        (glReadnPixels = GL.getFunctionProvider().getFunctionAddress("glReadnPixels")) != 0 &
                        (glGetnUniformfv = GL.getFunctionProvider().getFunctionAddress("glGetnUniformfv")) != 0 &
                        (glGetnUniformiv = GL.getFunctionProvider().getFunctionAddress("glGetnUniformiv")) != 0 &
                        (glGetnUniformuiv = GL.getFunctionProvider().getFunctionAddress("glGetnUniformuiv")) != 0;
    }

    private boolean GREMEDY_frame_terminator_initNativeFunctionAddresses() {
        return
                (glFrameTerminatorGREMEDY = GL.getFunctionProvider().getFunctionAddress("glFrameTerminatorGREMEDY")) != 0;
    }

    private boolean GREMEDY_string_marker_initNativeFunctionAddresses() {
        return
                (glStringMarkerGREMEDY = GL.getFunctionProvider().getFunctionAddress("glStringMarkerGREMEDY")) != 0;
    }

    private boolean INTEL_map_texture_initNativeFunctionAddresses() {
        return
                (glMapTexture2DINTEL = GL.getFunctionProvider().getFunctionAddress("glMapTexture2DINTEL")) != 0 &
                        (glUnmapTexture2DINTEL = GL.getFunctionProvider().getFunctionAddress("glUnmapTexture2DINTEL")) != 0 &
                        (glSyncTextureINTEL = GL.getFunctionProvider().getFunctionAddress("glSyncTextureINTEL")) != 0;
    }

    private boolean KHR_debug_initNativeFunctionAddresses() {
        return
                (glDebugMessageControl = GL.getFunctionProvider().getFunctionAddress("glDebugMessageControl")) != 0 &
                        (glDebugMessageInsert = GL.getFunctionProvider().getFunctionAddress("glDebugMessageInsert")) != 0 &
                        (glDebugMessageCallback = GL.getFunctionProvider().getFunctionAddress("glDebugMessageCallback")) != 0 &
                        (glGetDebugMessageLog = GL.getFunctionProvider().getFunctionAddress("glGetDebugMessageLog")) != 0 &
                        (glPushDebugGroup = GL.getFunctionProvider().getFunctionAddress("glPushDebugGroup")) != 0 &
                        (glPopDebugGroup = GL.getFunctionProvider().getFunctionAddress("glPopDebugGroup")) != 0 &
                        (glObjectLabel = GL.getFunctionProvider().getFunctionAddress("glObjectLabel")) != 0 &
                        (glGetObjectLabel = GL.getFunctionProvider().getFunctionAddress("glGetObjectLabel")) != 0 &
                        (glObjectPtrLabel = GL.getFunctionProvider().getFunctionAddress("glObjectPtrLabel")) != 0 &
                        (glGetObjectPtrLabel = GL.getFunctionProvider().getFunctionAddress("glGetObjectPtrLabel")) != 0;
    }

    private boolean KHR_robustness_initNativeFunctionAddresses() {
        return
                (glGetGraphicsResetStatus = GL.getFunctionProvider().getFunctionAddress("glGetGraphicsResetStatus")) != 0 &
                        (glReadnPixels = GL.getFunctionProvider().getFunctionAddress("glReadnPixels")) != 0 &
                        (glGetnUniformfv = GL.getFunctionProvider().getFunctionAddress("glGetnUniformfv")) != 0 &
                        (glGetnUniformiv = GL.getFunctionProvider().getFunctionAddress("glGetnUniformiv")) != 0 &
                        (glGetnUniformuiv = GL.getFunctionProvider().getFunctionAddress("glGetnUniformuiv")) != 0;
    }

    private boolean NV_bindless_multi_draw_indirect_initNativeFunctionAddresses() {
        return
                (glMultiDrawArraysIndirectBindlessNV = GL.getFunctionProvider().getFunctionAddress("glMultiDrawArraysIndirectBindlessNV")) != 0 &
                        (glMultiDrawElementsIndirectBindlessNV = GL.getFunctionProvider().getFunctionAddress("glMultiDrawElementsIndirectBindlessNV")) != 0;
    }

    private boolean NV_bindless_texture_initNativeFunctionAddresses() {
        return
                (glGetTextureHandleNV = GL.getFunctionProvider().getFunctionAddress("glGetTextureHandleNV")) != 0 &
                        (glGetTextureSamplerHandleNV = GL.getFunctionProvider().getFunctionAddress("glGetTextureSamplerHandleNV")) != 0 &
                        (glMakeTextureHandleResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeTextureHandleResidentNV")) != 0 &
                        (glMakeTextureHandleNonResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeTextureHandleNonResidentNV")) != 0 &
                        (glGetImageHandleNV = GL.getFunctionProvider().getFunctionAddress("glGetImageHandleNV")) != 0 &
                        (glMakeImageHandleResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeImageHandleResidentNV")) != 0 &
                        (glMakeImageHandleNonResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeImageHandleNonResidentNV")) != 0 &
                        (glUniformHandleui64NV = GL.getFunctionProvider().getFunctionAddress("glUniformHandleui64NV")) != 0 &
                        (glUniformHandleui64vNV = GL.getFunctionProvider().getFunctionAddress("glUniformHandleui64vNV")) != 0 &
                        (glProgramUniformHandleui64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniformHandleui64NV")) != 0 &
                        (glProgramUniformHandleui64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniformHandleui64vNV")) != 0 &
                        (glIsTextureHandleResidentNV = GL.getFunctionProvider().getFunctionAddress("glIsTextureHandleResidentNV")) != 0 &
                        (glIsImageHandleResidentNV = GL.getFunctionProvider().getFunctionAddress("glIsImageHandleResidentNV")) != 0;
    }

    private boolean NV_blend_equation_advanced_initNativeFunctionAddresses() {
        return
                (glBlendParameteriNV = GL.getFunctionProvider().getFunctionAddress("glBlendParameteriNV")) != 0 &
                        (glBlendBarrierNV = GL.getFunctionProvider().getFunctionAddress("glBlendBarrierNV")) != 0;
    }

    private boolean NV_conditional_render_initNativeFunctionAddresses() {
        return
                (glBeginConditionalRenderNV = GL.getFunctionProvider().getFunctionAddress("glBeginConditionalRenderNV")) != 0 &
                        (glEndConditionalRenderNV = GL.getFunctionProvider().getFunctionAddress("glEndConditionalRenderNV")) != 0;
    }

    private boolean NV_copy_image_initNativeFunctionAddresses() {
        return
                (glCopyImageSubDataNV = GL.getFunctionProvider().getFunctionAddress("glCopyImageSubDataNV")) != 0;
    }

    private boolean NV_depth_buffer_float_initNativeFunctionAddresses() {
        return
                (glDepthRangedNV = GL.getFunctionProvider().getFunctionAddress("glDepthRangedNV")) != 0 &
                        (glClearDepthdNV = GL.getFunctionProvider().getFunctionAddress("glClearDepthdNV")) != 0 &
                        (glDepthBoundsdNV = GL.getFunctionProvider().getFunctionAddress("glDepthBoundsdNV")) != 0;
    }

    private boolean NV_draw_texture_initNativeFunctionAddresses() {
        return
                (glDrawTextureNV = GL.getFunctionProvider().getFunctionAddress("glDrawTextureNV")) != 0;
    }

    private boolean NV_evaluators_initNativeFunctionAddresses() {
        return
                (glGetMapControlPointsNV = GL.getFunctionProvider().getFunctionAddress("glGetMapControlPointsNV")) != 0 &
                        (glMapControlPointsNV = GL.getFunctionProvider().getFunctionAddress("glMapControlPointsNV")) != 0 &
                        (glMapParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glMapParameterfvNV")) != 0 &
                        (glMapParameterivNV = GL.getFunctionProvider().getFunctionAddress("glMapParameterivNV")) != 0 &
                        (glGetMapParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetMapParameterfvNV")) != 0 &
                        (glGetMapParameterivNV = GL.getFunctionProvider().getFunctionAddress("glGetMapParameterivNV")) != 0 &
                        (glGetMapAttribParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetMapAttribParameterfvNV")) != 0 &
                        (glGetMapAttribParameterivNV = GL.getFunctionProvider().getFunctionAddress("glGetMapAttribParameterivNV")) != 0 &
                        (glEvalMapsNV = GL.getFunctionProvider().getFunctionAddress("glEvalMapsNV")) != 0;
    }

    private boolean NV_explicit_multisample_initNativeFunctionAddresses() {
        return
                (glGetBooleanIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetBooleanIndexedvEXT")) != 0 &
                        (glGetIntegerIndexedvEXT = GL.getFunctionProvider().getFunctionAddress("glGetIntegerIndexedvEXT")) != 0 &
                        (glGetMultisamplefvNV = GL.getFunctionProvider().getFunctionAddress("glGetMultisamplefvNV")) != 0 &
                        (glSampleMaskIndexedNV = GL.getFunctionProvider().getFunctionAddress("glSampleMaskIndexedNV")) != 0 &
                        (glTexRenderbufferNV = GL.getFunctionProvider().getFunctionAddress("glTexRenderbufferNV")) != 0;
    }

    private boolean NV_fence_initNativeFunctionAddresses() {
        return
                (glGenFencesNV = GL.getFunctionProvider().getFunctionAddress("glGenFencesNV")) != 0 &
                        (glDeleteFencesNV = GL.getFunctionProvider().getFunctionAddress("glDeleteFencesNV")) != 0 &
                        (glSetFenceNV = GL.getFunctionProvider().getFunctionAddress("glSetFenceNV")) != 0 &
                        (glTestFenceNV = GL.getFunctionProvider().getFunctionAddress("glTestFenceNV")) != 0 &
                        (glFinishFenceNV = GL.getFunctionProvider().getFunctionAddress("glFinishFenceNV")) != 0 &
                        (glIsFenceNV = GL.getFunctionProvider().getFunctionAddress("glIsFenceNV")) != 0 &
                        (glGetFenceivNV = GL.getFunctionProvider().getFunctionAddress("glGetFenceivNV")) != 0;
    }

    private boolean NV_fragment_program_initNativeFunctionAddresses() {
        return
                (glProgramNamedParameter4fNV = GL.getFunctionProvider().getFunctionAddress("glProgramNamedParameter4fNV")) != 0 &
                        (glProgramNamedParameter4dNV = GL.getFunctionProvider().getFunctionAddress("glProgramNamedParameter4dNV")) != 0 &
                        (glGetProgramNamedParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramNamedParameterfvNV")) != 0 &
                        (glGetProgramNamedParameterdvNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramNamedParameterdvNV")) != 0;
    }

    private boolean NV_framebuffer_multisample_coverage_initNativeFunctionAddresses() {
        return
                (glRenderbufferStorageMultisampleCoverageNV = GL.getFunctionProvider().getFunctionAddress("glRenderbufferStorageMultisampleCoverageNV")) != 0;
    }

    private boolean NV_geometry_program4_initNativeFunctionAddresses() {
        return
                (glProgramVertexLimitNV = GL.getFunctionProvider().getFunctionAddress("glProgramVertexLimitNV")) != 0 &
                        (glFramebufferTextureEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureEXT")) != 0 &
                        (glFramebufferTextureLayerEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureLayerEXT")) != 0 &
                        (glFramebufferTextureFaceEXT = GL.getFunctionProvider().getFunctionAddress("glFramebufferTextureFaceEXT")) != 0;
    }

    private boolean NV_gpu_program4_initNativeFunctionAddresses() {
        return
                (glProgramLocalParameterI4iNV = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameterI4iNV")) != 0 &
                        (glProgramLocalParameterI4ivNV = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameterI4ivNV")) != 0 &
                        (glProgramLocalParametersI4ivNV = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParametersI4ivNV")) != 0 &
                        (glProgramLocalParameterI4uiNV = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameterI4uiNV")) != 0 &
                        (glProgramLocalParameterI4uivNV = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParameterI4uivNV")) != 0 &
                        (glProgramLocalParametersI4uivNV = GL.getFunctionProvider().getFunctionAddress("glProgramLocalParametersI4uivNV")) != 0 &
                        (glProgramEnvParameterI4iNV = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameterI4iNV")) != 0 &
                        (glProgramEnvParameterI4ivNV = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameterI4ivNV")) != 0 &
                        (glProgramEnvParametersI4ivNV = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParametersI4ivNV")) != 0 &
                        (glProgramEnvParameterI4uiNV = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameterI4uiNV")) != 0 &
                        (glProgramEnvParameterI4uivNV = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParameterI4uivNV")) != 0 &
                        (glProgramEnvParametersI4uivNV = GL.getFunctionProvider().getFunctionAddress("glProgramEnvParametersI4uivNV")) != 0 &
                        (glGetProgramLocalParameterIivNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramLocalParameterIivNV")) != 0 &
                        (glGetProgramLocalParameterIuivNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramLocalParameterIuivNV")) != 0 &
                        (glGetProgramEnvParameterIivNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramEnvParameterIivNV")) != 0 &
                        (glGetProgramEnvParameterIuivNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramEnvParameterIuivNV")) != 0;
    }

    private boolean NV_gpu_shader5_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glUniform1i64NV = GL.getFunctionProvider().getFunctionAddress("glUniform1i64NV")) != 0 &
                        (glUniform2i64NV = GL.getFunctionProvider().getFunctionAddress("glUniform2i64NV")) != 0 &
                        (glUniform3i64NV = GL.getFunctionProvider().getFunctionAddress("glUniform3i64NV")) != 0 &
                        (glUniform4i64NV = GL.getFunctionProvider().getFunctionAddress("glUniform4i64NV")) != 0 &
                        (glUniform1i64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform1i64vNV")) != 0 &
                        (glUniform2i64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform2i64vNV")) != 0 &
                        (glUniform3i64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform3i64vNV")) != 0 &
                        (glUniform4i64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform4i64vNV")) != 0 &
                        (glUniform1ui64NV = GL.getFunctionProvider().getFunctionAddress("glUniform1ui64NV")) != 0 &
                        (glUniform2ui64NV = GL.getFunctionProvider().getFunctionAddress("glUniform2ui64NV")) != 0 &
                        (glUniform3ui64NV = GL.getFunctionProvider().getFunctionAddress("glUniform3ui64NV")) != 0 &
                        (glUniform4ui64NV = GL.getFunctionProvider().getFunctionAddress("glUniform4ui64NV")) != 0 &
                        (glUniform1ui64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform1ui64vNV")) != 0 &
                        (glUniform2ui64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform2ui64vNV")) != 0 &
                        (glUniform3ui64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform3ui64vNV")) != 0 &
                        (glUniform4ui64vNV = GL.getFunctionProvider().getFunctionAddress("glUniform4ui64vNV")) != 0 &
                        (glGetUniformi64vNV = GL.getFunctionProvider().getFunctionAddress("glGetUniformi64vNV")) != 0 &
                        (glGetUniformui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetUniformui64vNV")) != 0 &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform1i64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1i64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform2i64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2i64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform3i64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3i64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform4i64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4i64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform1i64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1i64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform2i64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2i64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform3i64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3i64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform4i64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4i64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform1ui64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1ui64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform2ui64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2ui64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform3ui64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3ui64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform4ui64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4ui64NV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform1ui64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform1ui64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform2ui64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform2ui64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform3ui64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform3ui64vNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_direct_state_access") || (glProgramUniform4ui64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniform4ui64vNV")) != 0);
    }

    private boolean NV_half_float_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glVertex2hNV = GL.getFunctionProvider().getFunctionAddress("glVertex2hNV")) != 0 &
                        (glVertex3hNV = GL.getFunctionProvider().getFunctionAddress("glVertex3hNV")) != 0 &
                        (glVertex4hNV = GL.getFunctionProvider().getFunctionAddress("glVertex4hNV")) != 0 &
                        (glNormal3hNV = GL.getFunctionProvider().getFunctionAddress("glNormal3hNV")) != 0 &
                        (glColor3hNV = GL.getFunctionProvider().getFunctionAddress("glColor3hNV")) != 0 &
                        (glColor4hNV = GL.getFunctionProvider().getFunctionAddress("glColor4hNV")) != 0 &
                        (glTexCoord1hNV = GL.getFunctionProvider().getFunctionAddress("glTexCoord1hNV")) != 0 &
                        (glTexCoord2hNV = GL.getFunctionProvider().getFunctionAddress("glTexCoord2hNV")) != 0 &
                        (glTexCoord3hNV = GL.getFunctionProvider().getFunctionAddress("glTexCoord3hNV")) != 0 &
                        (glTexCoord4hNV = GL.getFunctionProvider().getFunctionAddress("glTexCoord4hNV")) != 0 &
                        (glMultiTexCoord1hNV = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord1hNV")) != 0 &
                        (glMultiTexCoord2hNV = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord2hNV")) != 0 &
                        (glMultiTexCoord3hNV = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord3hNV")) != 0 &
                        (glMultiTexCoord4hNV = GL.getFunctionProvider().getFunctionAddress("glMultiTexCoord4hNV")) != 0 &
                        (!supported_extensions.contains("GL_EXT_fog_coord") || (glFogCoordhNV = GL.getFunctionProvider().getFunctionAddress("glFogCoordhNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_secondary_color") || (glSecondaryColor3hNV = GL.getFunctionProvider().getFunctionAddress("glSecondaryColor3hNV")) != 0) &
                        (!supported_extensions.contains("GL_EXT_vertex_weighting") || (glVertexWeighthNV = GL.getFunctionProvider().getFunctionAddress("glVertexWeighthNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttrib1hNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1hNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttrib2hNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2hNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttrib3hNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3hNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttrib4hNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4hNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttribs1hvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs1hvNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttribs2hvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs2hvNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttribs3hvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs3hvNV")) != 0) &
                        (!supported_extensions.contains("GL_NV_vertex_program") || (glVertexAttribs4hvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs4hvNV")) != 0);
    }

    private boolean NV_occlusion_query_initNativeFunctionAddresses() {
        return
                (glGenOcclusionQueriesNV = GL.getFunctionProvider().getFunctionAddress("glGenOcclusionQueriesNV")) != 0 &
                        (glDeleteOcclusionQueriesNV = GL.getFunctionProvider().getFunctionAddress("glDeleteOcclusionQueriesNV")) != 0 &
                        (glIsOcclusionQueryNV = GL.getFunctionProvider().getFunctionAddress("glIsOcclusionQueryNV")) != 0 &
                        (glBeginOcclusionQueryNV = GL.getFunctionProvider().getFunctionAddress("glBeginOcclusionQueryNV")) != 0 &
                        (glEndOcclusionQueryNV = GL.getFunctionProvider().getFunctionAddress("glEndOcclusionQueryNV")) != 0 &
                        (glGetOcclusionQueryuivNV = GL.getFunctionProvider().getFunctionAddress("glGetOcclusionQueryuivNV")) != 0 &
                        (glGetOcclusionQueryivNV = GL.getFunctionProvider().getFunctionAddress("glGetOcclusionQueryivNV")) != 0;
    }

    private boolean NV_parameter_buffer_object_initNativeFunctionAddresses() {
        return
                (glProgramBufferParametersfvNV = GL.getFunctionProvider().getFunctionAddress("glProgramBufferParametersfvNV")) != 0 &
                        (glProgramBufferParametersIivNV = GL.getFunctionProvider().getFunctionAddress("glProgramBufferParametersIivNV")) != 0 &
                        (glProgramBufferParametersIuivNV = GL.getFunctionProvider().getFunctionAddress("glProgramBufferParametersIuivNV")) != 0;
    }

    private boolean NV_path_rendering_initNativeFunctionAddresses() {
        return
                (glPathCommandsNV = GL.getFunctionProvider().getFunctionAddress("glPathCommandsNV")) != 0 &
                        (glPathCoordsNV = GL.getFunctionProvider().getFunctionAddress("glPathCoordsNV")) != 0 &
                        (glPathSubCommandsNV = GL.getFunctionProvider().getFunctionAddress("glPathSubCommandsNV")) != 0 &
                        (glPathSubCoordsNV = GL.getFunctionProvider().getFunctionAddress("glPathSubCoordsNV")) != 0 &
                        (glPathStringNV = GL.getFunctionProvider().getFunctionAddress("glPathStringNV")) != 0 &
                        (glPathGlyphsNV = GL.getFunctionProvider().getFunctionAddress("glPathGlyphsNV")) != 0 &
                        (glPathGlyphRangeNV = GL.getFunctionProvider().getFunctionAddress("glPathGlyphRangeNV")) != 0 &
                        (glWeightPathsNV = GL.getFunctionProvider().getFunctionAddress("glWeightPathsNV")) != 0 &
                        (glCopyPathNV = GL.getFunctionProvider().getFunctionAddress("glCopyPathNV")) != 0 &
                        (glInterpolatePathsNV = GL.getFunctionProvider().getFunctionAddress("glInterpolatePathsNV")) != 0 &
                        (glTransformPathNV = GL.getFunctionProvider().getFunctionAddress("glTransformPathNV")) != 0 &
                        (glPathParameterivNV = GL.getFunctionProvider().getFunctionAddress("glPathParameterivNV")) != 0 &
                        (glPathParameteriNV = GL.getFunctionProvider().getFunctionAddress("glPathParameteriNV")) != 0 &
                        (glPathParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glPathParameterfvNV")) != 0 &
                        (glPathParameterfNV = GL.getFunctionProvider().getFunctionAddress("glPathParameterfNV")) != 0 &
                        (glPathDashArrayNV = GL.getFunctionProvider().getFunctionAddress("glPathDashArrayNV")) != 0 &
                        (glGenPathsNV = GL.getFunctionProvider().getFunctionAddress("glGenPathsNV")) != 0 &
                        (glDeletePathsNV = GL.getFunctionProvider().getFunctionAddress("glDeletePathsNV")) != 0 &
                        (glIsPathNV = GL.getFunctionProvider().getFunctionAddress("glIsPathNV")) != 0 &
                        (glPathStencilFuncNV = GL.getFunctionProvider().getFunctionAddress("glPathStencilFuncNV")) != 0 &
                        (glPathStencilDepthOffsetNV = GL.getFunctionProvider().getFunctionAddress("glPathStencilDepthOffsetNV")) != 0 &
                        (glStencilFillPathNV = GL.getFunctionProvider().getFunctionAddress("glStencilFillPathNV")) != 0 &
                        (glStencilStrokePathNV = GL.getFunctionProvider().getFunctionAddress("glStencilStrokePathNV")) != 0 &
                        (glStencilFillPathInstancedNV = GL.getFunctionProvider().getFunctionAddress("glStencilFillPathInstancedNV")) != 0 &
                        (glStencilStrokePathInstancedNV = GL.getFunctionProvider().getFunctionAddress("glStencilStrokePathInstancedNV")) != 0 &
                        (glPathCoverDepthFuncNV = GL.getFunctionProvider().getFunctionAddress("glPathCoverDepthFuncNV")) != 0 &
                        (glPathColorGenNV = GL.getFunctionProvider().getFunctionAddress("glPathColorGenNV")) != 0 &
                        (glPathTexGenNV = GL.getFunctionProvider().getFunctionAddress("glPathTexGenNV")) != 0 &
                        (glPathFogGenNV = GL.getFunctionProvider().getFunctionAddress("glPathFogGenNV")) != 0 &
                        (glCoverFillPathNV = GL.getFunctionProvider().getFunctionAddress("glCoverFillPathNV")) != 0 &
                        (glCoverStrokePathNV = GL.getFunctionProvider().getFunctionAddress("glCoverStrokePathNV")) != 0 &
                        (glCoverFillPathInstancedNV = GL.getFunctionProvider().getFunctionAddress("glCoverFillPathInstancedNV")) != 0 &
                        (glCoverStrokePathInstancedNV = GL.getFunctionProvider().getFunctionAddress("glCoverStrokePathInstancedNV")) != 0 &
                        (glGetPathParameterivNV = GL.getFunctionProvider().getFunctionAddress("glGetPathParameterivNV")) != 0 &
                        (glGetPathParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetPathParameterfvNV")) != 0 &
                        (glGetPathCommandsNV = GL.getFunctionProvider().getFunctionAddress("glGetPathCommandsNV")) != 0 &
                        (glGetPathCoordsNV = GL.getFunctionProvider().getFunctionAddress("glGetPathCoordsNV")) != 0 &
                        (glGetPathDashArrayNV = GL.getFunctionProvider().getFunctionAddress("glGetPathDashArrayNV")) != 0 &
                        (glGetPathMetricsNV = GL.getFunctionProvider().getFunctionAddress("glGetPathMetricsNV")) != 0 &
                        (glGetPathMetricRangeNV = GL.getFunctionProvider().getFunctionAddress("glGetPathMetricRangeNV")) != 0 &
                        (glGetPathSpacingNV = GL.getFunctionProvider().getFunctionAddress("glGetPathSpacingNV")) != 0 &
                        (glGetPathColorGenivNV = GL.getFunctionProvider().getFunctionAddress("glGetPathColorGenivNV")) != 0 &
                        (glGetPathColorGenfvNV = GL.getFunctionProvider().getFunctionAddress("glGetPathColorGenfvNV")) != 0 &
                        (glGetPathTexGenivNV = GL.getFunctionProvider().getFunctionAddress("glGetPathTexGenivNV")) != 0 &
                        (glGetPathTexGenfvNV = GL.getFunctionProvider().getFunctionAddress("glGetPathTexGenfvNV")) != 0 &
                        (glIsPointInFillPathNV = GL.getFunctionProvider().getFunctionAddress("glIsPointInFillPathNV")) != 0 &
                        (glIsPointInStrokePathNV = GL.getFunctionProvider().getFunctionAddress("glIsPointInStrokePathNV")) != 0 &
                        (glGetPathLengthNV = GL.getFunctionProvider().getFunctionAddress("glGetPathLengthNV")) != 0 &
                        (glPointAlongPathNV = GL.getFunctionProvider().getFunctionAddress("glPointAlongPathNV")) != 0;
    }

    private boolean NV_pixel_data_range_initNativeFunctionAddresses() {
        return
                (glPixelDataRangeNV = GL.getFunctionProvider().getFunctionAddress("glPixelDataRangeNV")) != 0 &
                        (glFlushPixelDataRangeNV = GL.getFunctionProvider().getFunctionAddress("glFlushPixelDataRangeNV")) != 0;
    }

    private boolean NV_point_sprite_initNativeFunctionAddresses() {
        return
                (glPointParameteriNV = GL.getFunctionProvider().getFunctionAddress("glPointParameteriNV")) != 0 &
                        (glPointParameterivNV = GL.getFunctionProvider().getFunctionAddress("glPointParameterivNV")) != 0;
    }

    private boolean NV_present_video_initNativeFunctionAddresses() {
        return
                (glPresentFrameKeyedNV = GL.getFunctionProvider().getFunctionAddress("glPresentFrameKeyedNV")) != 0 &
                        (glPresentFrameDualFillNV = GL.getFunctionProvider().getFunctionAddress("glPresentFrameDualFillNV")) != 0 &
                        (glGetVideoivNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoivNV")) != 0 &
                        (glGetVideouivNV = GL.getFunctionProvider().getFunctionAddress("glGetVideouivNV")) != 0 &
                        (glGetVideoi64vNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoi64vNV")) != 0 &
                        (glGetVideoui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoui64vNV")) != 0;
    }

    private boolean NV_primitive_restart_initNativeFunctionAddresses() {
        return
                (glPrimitiveRestartNV = GL.getFunctionProvider().getFunctionAddress("glPrimitiveRestartNV")) != 0 &
                        (glPrimitiveRestartIndexNV = GL.getFunctionProvider().getFunctionAddress("glPrimitiveRestartIndexNV")) != 0;
    }

    private boolean NV_program_initNativeFunctionAddresses() {
        return
                (glLoadProgramNV = GL.getFunctionProvider().getFunctionAddress("glLoadProgramNV")) != 0 &
                        (glBindProgramNV = GL.getFunctionProvider().getFunctionAddress("glBindProgramNV")) != 0 &
                        (glDeleteProgramsNV = GL.getFunctionProvider().getFunctionAddress("glDeleteProgramsNV")) != 0 &
                        (glGenProgramsNV = GL.getFunctionProvider().getFunctionAddress("glGenProgramsNV")) != 0 &
                        (glGetProgramivNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramivNV")) != 0 &
                        (glGetProgramStringNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramStringNV")) != 0 &
                        (glIsProgramNV = GL.getFunctionProvider().getFunctionAddress("glIsProgramNV")) != 0 &
                        (glAreProgramsResidentNV = GL.getFunctionProvider().getFunctionAddress("glAreProgramsResidentNV")) != 0 &
                        (glRequestResidentProgramsNV = GL.getFunctionProvider().getFunctionAddress("glRequestResidentProgramsNV")) != 0;
    }

    private boolean NV_register_combiners_initNativeFunctionAddresses() {
        return
                (glCombinerParameterfNV = GL.getFunctionProvider().getFunctionAddress("glCombinerParameterfNV")) != 0 &
                        (glCombinerParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glCombinerParameterfvNV")) != 0 &
                        (glCombinerParameteriNV = GL.getFunctionProvider().getFunctionAddress("glCombinerParameteriNV")) != 0 &
                        (glCombinerParameterivNV = GL.getFunctionProvider().getFunctionAddress("glCombinerParameterivNV")) != 0 &
                        (glCombinerInputNV = GL.getFunctionProvider().getFunctionAddress("glCombinerInputNV")) != 0 &
                        (glCombinerOutputNV = GL.getFunctionProvider().getFunctionAddress("glCombinerOutputNV")) != 0 &
                        (glFinalCombinerInputNV = GL.getFunctionProvider().getFunctionAddress("glFinalCombinerInputNV")) != 0 &
                        (glGetCombinerInputParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetCombinerInputParameterfvNV")) != 0 &
                        (glGetCombinerInputParameterivNV = GL.getFunctionProvider().getFunctionAddress("glGetCombinerInputParameterivNV")) != 0 &
                        (glGetCombinerOutputParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetCombinerOutputParameterfvNV")) != 0 &
                        (glGetCombinerOutputParameterivNV = GL.getFunctionProvider().getFunctionAddress("glGetCombinerOutputParameterivNV")) != 0 &
                        (glGetFinalCombinerInputParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetFinalCombinerInputParameterfvNV")) != 0 &
                        (glGetFinalCombinerInputParameterivNV = GL.getFunctionProvider().getFunctionAddress("glGetFinalCombinerInputParameterivNV")) != 0;
    }

    private boolean NV_register_combiners2_initNativeFunctionAddresses() {
        return
                (glCombinerStageParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glCombinerStageParameterfvNV")) != 0 &
                        (glGetCombinerStageParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetCombinerStageParameterfvNV")) != 0;
    }

    private boolean NV_shader_buffer_load_initNativeFunctionAddresses() {
        return
                (glMakeBufferResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeBufferResidentNV")) != 0 &
                        (glMakeBufferNonResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeBufferNonResidentNV")) != 0 &
                        (glIsBufferResidentNV = GL.getFunctionProvider().getFunctionAddress("glIsBufferResidentNV")) != 0 &
                        (glMakeNamedBufferResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeNamedBufferResidentNV")) != 0 &
                        (glMakeNamedBufferNonResidentNV = GL.getFunctionProvider().getFunctionAddress("glMakeNamedBufferNonResidentNV")) != 0 &
                        (glIsNamedBufferResidentNV = GL.getFunctionProvider().getFunctionAddress("glIsNamedBufferResidentNV")) != 0 &
                        (glGetBufferParameterui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetBufferParameterui64vNV")) != 0 &
                        (glGetNamedBufferParameterui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetNamedBufferParameterui64vNV")) != 0 &
                        (glGetIntegerui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetIntegerui64vNV")) != 0 &
                        (glUniformui64NV = GL.getFunctionProvider().getFunctionAddress("glUniformui64NV")) != 0 &
                        (glUniformui64vNV = GL.getFunctionProvider().getFunctionAddress("glUniformui64vNV")) != 0 &
                        (glGetUniformui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetUniformui64vNV")) != 0 &
                        (glProgramUniformui64NV = GL.getFunctionProvider().getFunctionAddress("glProgramUniformui64NV")) != 0 &
                        (glProgramUniformui64vNV = GL.getFunctionProvider().getFunctionAddress("glProgramUniformui64vNV")) != 0;
    }

    private boolean NV_texture_barrier_initNativeFunctionAddresses() {
        return
                (glTextureBarrierNV = GL.getFunctionProvider().getFunctionAddress("glTextureBarrierNV")) != 0;
    }

    private boolean NV_texture_multisample_initNativeFunctionAddresses() {
        return
                (glTexImage2DMultisampleCoverageNV = GL.getFunctionProvider().getFunctionAddress("glTexImage2DMultisampleCoverageNV")) != 0 &
                        (glTexImage3DMultisampleCoverageNV = GL.getFunctionProvider().getFunctionAddress("glTexImage3DMultisampleCoverageNV")) != 0 &
                        (glTextureImage2DMultisampleNV = GL.getFunctionProvider().getFunctionAddress("glTextureImage2DMultisampleNV")) != 0 &
                        (glTextureImage3DMultisampleNV = GL.getFunctionProvider().getFunctionAddress("glTextureImage3DMultisampleNV")) != 0 &
                        (glTextureImage2DMultisampleCoverageNV = GL.getFunctionProvider().getFunctionAddress("glTextureImage2DMultisampleCoverageNV")) != 0 &
                        (glTextureImage3DMultisampleCoverageNV = GL.getFunctionProvider().getFunctionAddress("glTextureImage3DMultisampleCoverageNV")) != 0;
    }

    private boolean NV_transform_feedback_initNativeFunctionAddresses() {
        return
                (glBindBufferRangeNV = GL.getFunctionProvider().getFunctionAddress("glBindBufferRangeNV")) != 0 &
                        (glBindBufferOffsetNV = GL.getFunctionProvider().getFunctionAddress("glBindBufferOffsetNV")) != 0 &
                        (glBindBufferBaseNV = GL.getFunctionProvider().getFunctionAddress("glBindBufferBaseNV")) != 0 &
                        (glTransformFeedbackAttribsNV = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackAttribsNV")) != 0 &
                        (glTransformFeedbackVaryingsNV = GL.getFunctionProvider().getFunctionAddress("glTransformFeedbackVaryingsNV")) != 0 &
                        (glBeginTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glBeginTransformFeedbackNV")) != 0 &
                        (glEndTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glEndTransformFeedbackNV")) != 0 &
                        (glGetVaryingLocationNV = GL.getFunctionProvider().getFunctionAddress("glGetVaryingLocationNV")) != 0 &
                        (glGetActiveVaryingNV = GL.getFunctionProvider().getFunctionAddress("glGetActiveVaryingNV")) != 0 &
                        (glActiveVaryingNV = GL.getFunctionProvider().getFunctionAddress("glActiveVaryingNV")) != 0 &
                        (glGetTransformFeedbackVaryingNV = GL.getFunctionProvider().getFunctionAddress("glGetTransformFeedbackVaryingNV")) != 0;
    }

    private boolean NV_transform_feedback2_initNativeFunctionAddresses() {
        return
                (glBindTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glBindTransformFeedbackNV")) != 0 &
                        (glDeleteTransformFeedbacksNV = GL.getFunctionProvider().getFunctionAddress("glDeleteTransformFeedbacksNV")) != 0 &
                        (glGenTransformFeedbacksNV = GL.getFunctionProvider().getFunctionAddress("glGenTransformFeedbacksNV")) != 0 &
                        (glIsTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glIsTransformFeedbackNV")) != 0 &
                        (glPauseTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glPauseTransformFeedbackNV")) != 0 &
                        (glResumeTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glResumeTransformFeedbackNV")) != 0 &
                        (glDrawTransformFeedbackNV = GL.getFunctionProvider().getFunctionAddress("glDrawTransformFeedbackNV")) != 0;
    }

    private boolean NV_vertex_array_range_initNativeFunctionAddresses() {
        return
                (glVertexArrayRangeNV = GL.getFunctionProvider().getFunctionAddress("glVertexArrayRangeNV")) != 0 &
                        (glFlushVertexArrayRangeNV = GL.getFunctionProvider().getFunctionAddress("glFlushVertexArrayRangeNV")) != 0 &
                        (glAllocateMemoryNV = getPlatformSpecificFunctionAddress("gl", new String[]{"Windows", "Linux"}, new String[]{"wgl", "glX"}, "glAllocateMemoryNV")) != 0 &
                        (glFreeMemoryNV = getPlatformSpecificFunctionAddress("gl", new String[]{"Windows", "Linux"}, new String[]{"wgl", "glX"}, "glFreeMemoryNV")) != 0;
    }

    static long getPlatformSpecificFunctionAddress(String function_prefix, String[] os_prefixes, String[] os_function_prefixes, String function) {
        String os_name = AccessController.doPrivileged((PrivilegedAction<String>)()->System.getProperty("os.name"));
        for ( int i = 0; i < os_prefixes.length; i++ )
            if ( os_name.startsWith(os_prefixes[i]) ) {
                String platform_function_name = function.replaceFirst(function_prefix, os_function_prefixes[i]);
                long address = GL.getFunctionProvider().getFunctionAddress(platform_function_name);
                return address;
            }
        return 0;
    }

    private boolean NV_vertex_attrib_integer_64bit_initNativeFunctionAddresses(Set<String> supported_extensions) {
        return
                (glVertexAttribL1i64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1i64NV")) != 0 &
                        (glVertexAttribL2i64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2i64NV")) != 0 &
                        (glVertexAttribL3i64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3i64NV")) != 0 &
                        (glVertexAttribL4i64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4i64NV")) != 0 &
                        (glVertexAttribL1i64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1i64vNV")) != 0 &
                        (glVertexAttribL2i64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2i64vNV")) != 0 &
                        (glVertexAttribL3i64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3i64vNV")) != 0 &
                        (glVertexAttribL4i64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4i64vNV")) != 0 &
                        (glVertexAttribL1ui64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1ui64NV")) != 0 &
                        (glVertexAttribL2ui64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2ui64NV")) != 0 &
                        (glVertexAttribL3ui64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3ui64NV")) != 0 &
                        (glVertexAttribL4ui64NV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4ui64NV")) != 0 &
                        (glVertexAttribL1ui64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL1ui64vNV")) != 0 &
                        (glVertexAttribL2ui64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL2ui64vNV")) != 0 &
                        (glVertexAttribL3ui64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL3ui64vNV")) != 0 &
                        (glVertexAttribL4ui64vNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribL4ui64vNV")) != 0 &
                        (glGetVertexAttribLi64vNV = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribLi64vNV")) != 0 &
                        (glGetVertexAttribLui64vNV = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribLui64vNV")) != 0 &
                        (!supported_extensions.contains("GL_NV_vertex_buffer_unified_memory") || (glVertexAttribLFormatNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribLFormatNV")) != 0);
    }

    private boolean NV_vertex_buffer_unified_memory_initNativeFunctionAddresses() {
        return
                (glBufferAddressRangeNV = GL.getFunctionProvider().getFunctionAddress("glBufferAddressRangeNV")) != 0 &
                        (glVertexFormatNV = GL.getFunctionProvider().getFunctionAddress("glVertexFormatNV")) != 0 &
                        (glNormalFormatNV = GL.getFunctionProvider().getFunctionAddress("glNormalFormatNV")) != 0 &
                        (glColorFormatNV = GL.getFunctionProvider().getFunctionAddress("glColorFormatNV")) != 0 &
                        (glIndexFormatNV = GL.getFunctionProvider().getFunctionAddress("glIndexFormatNV")) != 0 &
                        (glTexCoordFormatNV = GL.getFunctionProvider().getFunctionAddress("glTexCoordFormatNV")) != 0 &
                        (glEdgeFlagFormatNV = GL.getFunctionProvider().getFunctionAddress("glEdgeFlagFormatNV")) != 0 &
                        (glSecondaryColorFormatNV = GL.getFunctionProvider().getFunctionAddress("glSecondaryColorFormatNV")) != 0 &
                        (glFogCoordFormatNV = GL.getFunctionProvider().getFunctionAddress("glFogCoordFormatNV")) != 0 &
                        (glVertexAttribFormatNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribFormatNV")) != 0 &
                        (glVertexAttribIFormatNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribIFormatNV")) != 0 &
                        (glGetIntegerui64i_vNV = GL.getFunctionProvider().getFunctionAddress("glGetIntegerui64i_vNV")) != 0;
    }

    private boolean NV_vertex_program_initNativeFunctionAddresses() {
        return
                (glExecuteProgramNV = GL.getFunctionProvider().getFunctionAddress("glExecuteProgramNV")) != 0 &
                        (glGetProgramParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramParameterfvNV")) != 0 &
                        (glGetProgramParameterdvNV = GL.getFunctionProvider().getFunctionAddress("glGetProgramParameterdvNV")) != 0 &
                        (glGetTrackMatrixivNV = GL.getFunctionProvider().getFunctionAddress("glGetTrackMatrixivNV")) != 0 &
                        (glGetVertexAttribfvNV = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribfvNV")) != 0 &
                        (glGetVertexAttribdvNV = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribdvNV")) != 0 &
                        (glGetVertexAttribivNV = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribivNV")) != 0 &
                        (glGetVertexAttribPointervNV = GL.getFunctionProvider().getFunctionAddress("glGetVertexAttribPointervNV")) != 0 &
                        (glProgramParameter4fNV = GL.getFunctionProvider().getFunctionAddress("glProgramParameter4fNV")) != 0 &
                        (glProgramParameter4dNV = GL.getFunctionProvider().getFunctionAddress("glProgramParameter4dNV")) != 0 &
                        (glProgramParameters4fvNV = GL.getFunctionProvider().getFunctionAddress("glProgramParameters4fvNV")) != 0 &
                        (glProgramParameters4dvNV = GL.getFunctionProvider().getFunctionAddress("glProgramParameters4dvNV")) != 0 &
                        (glTrackMatrixNV = GL.getFunctionProvider().getFunctionAddress("glTrackMatrixNV")) != 0 &
                        (glVertexAttribPointerNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribPointerNV")) != 0 &
                        (glVertexAttrib1sNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1sNV")) != 0 &
                        (glVertexAttrib1fNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1fNV")) != 0 &
                        (glVertexAttrib1dNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib1dNV")) != 0 &
                        (glVertexAttrib2sNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2sNV")) != 0 &
                        (glVertexAttrib2fNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2fNV")) != 0 &
                        (glVertexAttrib2dNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib2dNV")) != 0 &
                        (glVertexAttrib3sNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3sNV")) != 0 &
                        (glVertexAttrib3fNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3fNV")) != 0 &
                        (glVertexAttrib3dNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib3dNV")) != 0 &
                        (glVertexAttrib4sNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4sNV")) != 0 &
                        (glVertexAttrib4fNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4fNV")) != 0 &
                        (glVertexAttrib4dNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4dNV")) != 0 &
                        (glVertexAttrib4ubNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttrib4ubNV")) != 0 &
                        (glVertexAttribs1svNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs1svNV")) != 0 &
                        (glVertexAttribs1fvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs1fvNV")) != 0 &
                        (glVertexAttribs1dvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs1dvNV")) != 0 &
                        (glVertexAttribs2svNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs2svNV")) != 0 &
                        (glVertexAttribs2fvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs2fvNV")) != 0 &
                        (glVertexAttribs2dvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs2dvNV")) != 0 &
                        (glVertexAttribs3svNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs3svNV")) != 0 &
                        (glVertexAttribs3fvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs3fvNV")) != 0 &
                        (glVertexAttribs3dvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs3dvNV")) != 0 &
                        (glVertexAttribs4svNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs4svNV")) != 0 &
                        (glVertexAttribs4fvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs4fvNV")) != 0 &
                        (glVertexAttribs4dvNV = GL.getFunctionProvider().getFunctionAddress("glVertexAttribs4dvNV")) != 0;
    }

    private boolean NV_video_capture_initNativeFunctionAddresses() {
        return
                (glBeginVideoCaptureNV = GL.getFunctionProvider().getFunctionAddress("glBeginVideoCaptureNV")) != 0 &
                        (glBindVideoCaptureStreamBufferNV = GL.getFunctionProvider().getFunctionAddress("glBindVideoCaptureStreamBufferNV")) != 0 &
                        (glBindVideoCaptureStreamTextureNV = GL.getFunctionProvider().getFunctionAddress("glBindVideoCaptureStreamTextureNV")) != 0 &
                        (glEndVideoCaptureNV = GL.getFunctionProvider().getFunctionAddress("glEndVideoCaptureNV")) != 0 &
                        (glGetVideoCaptureivNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoCaptureivNV")) != 0 &
                        (glGetVideoCaptureStreamivNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoCaptureStreamivNV")) != 0 &
                        (glGetVideoCaptureStreamfvNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoCaptureStreamfvNV")) != 0 &
                        (glGetVideoCaptureStreamdvNV = GL.getFunctionProvider().getFunctionAddress("glGetVideoCaptureStreamdvNV")) != 0 &
                        (glVideoCaptureNV = GL.getFunctionProvider().getFunctionAddress("glVideoCaptureNV")) != 0 &
                        (glVideoCaptureStreamParameterivNV = GL.getFunctionProvider().getFunctionAddress("glVideoCaptureStreamParameterivNV")) != 0 &
                        (glVideoCaptureStreamParameterfvNV = GL.getFunctionProvider().getFunctionAddress("glVideoCaptureStreamParameterfvNV")) != 0 &
                        (glVideoCaptureStreamParameterdvNV = GL.getFunctionProvider().getFunctionAddress("glVideoCaptureStreamParameterdvNV")) != 0;
    }


    private static void remove(Set supported_extensions, String extension) {
        LWJGLUtil.log(extension + " was reported as available but an entry point is missing");
        supported_extensions.remove(extension);
    }

    private Set<String> initAllStubs(boolean forwardCompatible) throws LWJGLException {
        glGetError = GL.getFunctionProvider().getFunctionAddress("glGetError");
        glGetString = GL.getFunctionProvider().getFunctionAddress("glGetString");
        glGetIntegerv = GL.getFunctionProvider().getFunctionAddress("glGetIntegerv");
        glGetStringi = GL.getFunctionProvider().getFunctionAddress("glGetStringi");
        //FIXME?
        //GL.getFunctionProvider().setCapabilities(this);
        Set<String> supported_extensions = new HashSet<>(256);
        int profileMask = getSupportedExtensions(supported_extensions);
        if ( supported_extensions.contains("OpenGL31") && !(supported_extensions.contains("GL_ARB_compatibility") || (profileMask & GL_CONTEXT_COMPATIBILITY_PROFILE_BIT) != 0) )
            forwardCompatible = true;
        if (!GL11_initNativeFunctionAddresses(forwardCompatible))
            throw new LWJGLException("GL11 not supported");
        if (supported_extensions.contains("GL_ARB_fragment_program"))
            supported_extensions.add("GL_ARB_program");
        if (supported_extensions.contains("GL_ARB_pixel_buffer_object"))
            supported_extensions.add("GL_ARB_buffer_object");
        if (supported_extensions.contains("GL_ARB_vertex_buffer_object"))
            supported_extensions.add("GL_ARB_buffer_object");
        if (supported_extensions.contains("GL_ARB_vertex_program"))
            supported_extensions.add("GL_ARB_program");
        if (supported_extensions.contains("GL_EXT_pixel_buffer_object"))
            supported_extensions.add("GL_ARB_buffer_object");
        if (supported_extensions.contains("GL_NV_fragment_program"))
            supported_extensions.add("GL_NV_program");
        if (supported_extensions.contains("GL_NV_vertex_program"))
            supported_extensions.add("GL_NV_program");
        if ((supported_extensions.contains("GL_AMD_debug_output") || supported_extensions.contains("GL_AMDX_debug_output")) && !AMD_debug_output_initNativeFunctionAddresses()) {
            remove(supported_extensions, "GL_AMDX_debug_output");
            remove(supported_extensions, "GL_AMD_debug_output");
        }
        if (supported_extensions.contains("GL_AMD_draw_buffers_blend") && !AMD_draw_buffers_blend_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_draw_buffers_blend");
        if (supported_extensions.contains("GL_AMD_interleaved_elements") && !AMD_interleaved_elements_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_interleaved_elements");
        if (supported_extensions.contains("GL_AMD_multi_draw_indirect") && !AMD_multi_draw_indirect_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_multi_draw_indirect");
        if (supported_extensions.contains("GL_AMD_name_gen_delete") && !AMD_name_gen_delete_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_name_gen_delete");
        if (supported_extensions.contains("GL_AMD_performance_monitor") && !AMD_performance_monitor_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_performance_monitor");
        if (supported_extensions.contains("GL_AMD_sample_positions") && !AMD_sample_positions_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_sample_positions");
        if (supported_extensions.contains("GL_AMD_sparse_texture") && !AMD_sparse_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_sparse_texture");
        if (supported_extensions.contains("GL_AMD_stencil_operation_extended") && !AMD_stencil_operation_extended_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_stencil_operation_extended");
        if (supported_extensions.contains("GL_AMD_vertex_shader_tessellator") && !AMD_vertex_shader_tessellator_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_AMD_vertex_shader_tessellator");
        if (supported_extensions.contains("GL_APPLE_element_array") && !APPLE_element_array_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_element_array");
        if (supported_extensions.contains("GL_APPLE_fence") && !APPLE_fence_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_fence");
        if (supported_extensions.contains("GL_APPLE_flush_buffer_range") && !APPLE_flush_buffer_range_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_flush_buffer_range");
        if (supported_extensions.contains("GL_APPLE_object_purgeable") && !APPLE_object_purgeable_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_object_purgeable");
        if (supported_extensions.contains("GL_APPLE_texture_range") && !APPLE_texture_range_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_texture_range");
        if (supported_extensions.contains("GL_APPLE_vertex_array_object") && !APPLE_vertex_array_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_vertex_array_object");
        if (supported_extensions.contains("GL_APPLE_vertex_array_range") && !APPLE_vertex_array_range_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_vertex_array_range");
        if (supported_extensions.contains("GL_APPLE_vertex_program_evaluators") && !APPLE_vertex_program_evaluators_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_APPLE_vertex_program_evaluators");
        if (supported_extensions.contains("GL_ARB_ES2_compatibility") && !ARB_ES2_compatibility_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_ES2_compatibility");
        if (supported_extensions.contains("GL_ARB_ES3_1_compatibility") && !ARB_ES3_1_compatibility_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_ES3_1_compatibility");
        if (supported_extensions.contains("GL_ARB_base_instance") && !ARB_base_instance_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_base_instance");
        if (supported_extensions.contains("GL_ARB_bindless_texture") && !ARB_bindless_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_bindless_texture");
        if (supported_extensions.contains("GL_ARB_blend_func_extended") && !ARB_blend_func_extended_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_blend_func_extended");
        if (supported_extensions.contains("GL_ARB_buffer_object") && !ARB_buffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_buffer_object");
        if (supported_extensions.contains("GL_ARB_buffer_storage") && !ARB_buffer_storage_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_buffer_storage");
        if (supported_extensions.contains("GL_ARB_cl_event") && !ARB_cl_event_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_cl_event");
        if (supported_extensions.contains("GL_ARB_clear_buffer_object") && !ARB_clear_buffer_object_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_clear_buffer_object");
        if (supported_extensions.contains("GL_ARB_clear_texture") && !ARB_clear_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_clear_texture");
        if (supported_extensions.contains("GL_ARB_clip_control") && !ARB_clip_control_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_clip_control");
        if (supported_extensions.contains("GL_ARB_color_buffer_float") && !ARB_color_buffer_float_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_color_buffer_float");
        if (supported_extensions.contains("GL_ARB_compute_shader") && !ARB_compute_shader_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_compute_shader");
        if (supported_extensions.contains("GL_ARB_compute_variable_group_size") && !ARB_compute_variable_group_size_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_compute_variable_group_size");
        if (supported_extensions.contains("GL_ARB_copy_buffer") && !ARB_copy_buffer_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_copy_buffer");
        if (supported_extensions.contains("GL_ARB_copy_image") && !ARB_copy_image_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_copy_image");
        if (supported_extensions.contains("GL_ARB_debug_output") && !ARB_debug_output_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_debug_output");
        if (supported_extensions.contains("GL_ARB_direct_state_access") && !ARB_direct_state_access_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_direct_state_access");
        if (supported_extensions.contains("GL_ARB_draw_buffers") && !ARB_draw_buffers_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_draw_buffers");
        if (supported_extensions.contains("GL_ARB_draw_buffers_blend") && !ARB_draw_buffers_blend_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_draw_buffers_blend");
        if (supported_extensions.contains("GL_ARB_draw_elements_base_vertex") && !ARB_draw_elements_base_vertex_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_draw_elements_base_vertex");
        if (supported_extensions.contains("GL_ARB_draw_indirect") && !ARB_draw_indirect_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_draw_indirect");
        if (supported_extensions.contains("GL_ARB_draw_instanced") && !ARB_draw_instanced_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_draw_instanced");
        if (supported_extensions.contains("GL_ARB_framebuffer_no_attachments") && !ARB_framebuffer_no_attachments_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_framebuffer_no_attachments");
        if (supported_extensions.contains("GL_ARB_framebuffer_object") && !ARB_framebuffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_framebuffer_object");
        if (supported_extensions.contains("GL_ARB_geometry_shader4") && !ARB_geometry_shader4_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_geometry_shader4");
        if (supported_extensions.contains("GL_ARB_get_program_binary") && !ARB_get_program_binary_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_get_program_binary");
        if (supported_extensions.contains("GL_ARB_get_texture_sub_image") && !ARB_get_texture_sub_image_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_get_texture_sub_image");
        if (supported_extensions.contains("GL_ARB_gpu_shader_fp64") && !ARB_gpu_shader_fp64_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_gpu_shader_fp64");
        if (supported_extensions.contains("GL_ARB_imaging") && !ARB_imaging_initNativeFunctionAddresses(forwardCompatible))
            remove(supported_extensions, "GL_ARB_imaging");
        if (supported_extensions.contains("GL_ARB_indirect_parameters") && !ARB_indirect_parameters_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_indirect_parameters");
        if (supported_extensions.contains("GL_ARB_instanced_arrays") && !ARB_instanced_arrays_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_instanced_arrays");
        if (supported_extensions.contains("GL_ARB_internalformat_query") && !ARB_internalformat_query_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_internalformat_query");
        if (supported_extensions.contains("GL_ARB_internalformat_query2") && !ARB_internalformat_query2_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_internalformat_query2");
        if (supported_extensions.contains("GL_ARB_invalidate_subdata") && !ARB_invalidate_subdata_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_invalidate_subdata");
        if (supported_extensions.contains("GL_ARB_map_buffer_range") && !ARB_map_buffer_range_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_map_buffer_range");
        if (supported_extensions.contains("GL_ARB_matrix_palette") && !ARB_matrix_palette_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_matrix_palette");
        if (supported_extensions.contains("GL_ARB_multi_bind") && !ARB_multi_bind_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_multi_bind");
        if (supported_extensions.contains("GL_ARB_multi_draw_indirect") && !ARB_multi_draw_indirect_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_multi_draw_indirect");
        if (supported_extensions.contains("GL_ARB_multisample") && !ARB_multisample_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_multisample");
        if (supported_extensions.contains("GL_ARB_multitexture") && !ARB_multitexture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_multitexture");
        if (supported_extensions.contains("GL_ARB_occlusion_query") && !ARB_occlusion_query_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_occlusion_query");
        if (supported_extensions.contains("GL_ARB_point_parameters") && !ARB_point_parameters_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_point_parameters");
        if (supported_extensions.contains("GL_ARB_program") && !ARB_program_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_program");
        if (supported_extensions.contains("GL_ARB_program_interface_query") && !ARB_program_interface_query_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_program_interface_query");
        if (supported_extensions.contains("GL_ARB_provoking_vertex") && !ARB_provoking_vertex_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_provoking_vertex");
        if (supported_extensions.contains("GL_ARB_robustness") && !ARB_robustness_initNativeFunctionAddresses(forwardCompatible,supported_extensions))
            remove(supported_extensions, "GL_ARB_robustness");
        if (supported_extensions.contains("GL_ARB_sample_shading") && !ARB_sample_shading_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_sample_shading");
        if (supported_extensions.contains("GL_ARB_sampler_objects") && !ARB_sampler_objects_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_sampler_objects");
        if (supported_extensions.contains("GL_ARB_separate_shader_objects") && !ARB_separate_shader_objects_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_separate_shader_objects");
        if (supported_extensions.contains("GL_ARB_shader_atomic_counters") && !ARB_shader_atomic_counters_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_shader_atomic_counters");
        if (supported_extensions.contains("GL_ARB_shader_image_load_store") && !ARB_shader_image_load_store_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_shader_image_load_store");
        if (supported_extensions.contains("GL_ARB_shader_objects") && !ARB_shader_objects_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_shader_objects");
        if (supported_extensions.contains("GL_ARB_shader_storage_buffer_object") && !ARB_shader_storage_buffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_shader_storage_buffer_object");
        if (supported_extensions.contains("GL_ARB_shader_subroutine") && !ARB_shader_subroutine_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_shader_subroutine");
        if (supported_extensions.contains("GL_ARB_shading_language_include") && !ARB_shading_language_include_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_shading_language_include");
        if (supported_extensions.contains("GL_ARB_sparse_buffer") && !ARB_sparse_buffer_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_sparse_buffer");
        if (supported_extensions.contains("GL_ARB_sparse_texture") && !ARB_sparse_texture_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_sparse_texture");
        if (supported_extensions.contains("GL_ARB_sync") && !ARB_sync_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_sync");
        if (supported_extensions.contains("GL_ARB_tessellation_shader") && !ARB_tessellation_shader_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_tessellation_shader");
        if (supported_extensions.contains("GL_ARB_texture_barrier") && !ARB_texture_barrier_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_texture_barrier");
        if (supported_extensions.contains("GL_ARB_texture_buffer_object") && !ARB_texture_buffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_texture_buffer_object");
        if (supported_extensions.contains("GL_ARB_texture_buffer_range") && !ARB_texture_buffer_range_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_texture_buffer_range");
        if (supported_extensions.contains("GL_ARB_texture_compression") && !ARB_texture_compression_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_texture_compression");
        if (supported_extensions.contains("GL_ARB_texture_multisample") && !ARB_texture_multisample_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_texture_multisample");
        if ((supported_extensions.contains("GL_ARB_texture_storage") || supported_extensions.contains("GL_EXT_texture_storage")) && !ARB_texture_storage_initNativeFunctionAddresses(supported_extensions)) {
            remove(supported_extensions, "GL_EXT_texture_storage");
            remove(supported_extensions, "GL_ARB_texture_storage");
        }
        if (supported_extensions.contains("GL_ARB_texture_storage_multisample") && !ARB_texture_storage_multisample_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_texture_storage_multisample");
        if (supported_extensions.contains("GL_ARB_texture_view") && !ARB_texture_view_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_texture_view");
        if (supported_extensions.contains("GL_ARB_timer_query") && !ARB_timer_query_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_timer_query");
        if (supported_extensions.contains("GL_ARB_transform_feedback2") && !ARB_transform_feedback2_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_transform_feedback2");
        if (supported_extensions.contains("GL_ARB_transform_feedback3") && !ARB_transform_feedback3_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_transform_feedback3");
        if (supported_extensions.contains("GL_ARB_transform_feedback_instanced") && !ARB_transform_feedback_instanced_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_transform_feedback_instanced");
        if (supported_extensions.contains("GL_ARB_transpose_matrix") && !ARB_transpose_matrix_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_transpose_matrix");
        if (supported_extensions.contains("GL_ARB_uniform_buffer_object") && !ARB_uniform_buffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_uniform_buffer_object");
        if (supported_extensions.contains("GL_ARB_vertex_array_object") && !ARB_vertex_array_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_vertex_array_object");
        if (supported_extensions.contains("GL_ARB_vertex_attrib_64bit") && !ARB_vertex_attrib_64bit_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_ARB_vertex_attrib_64bit");
        if (supported_extensions.contains("GL_ARB_vertex_attrib_binding") && !ARB_vertex_attrib_binding_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_vertex_attrib_binding");
        if (supported_extensions.contains("GL_ARB_vertex_blend") && !ARB_vertex_blend_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_vertex_blend");
        if (supported_extensions.contains("GL_ARB_vertex_program") && !ARB_vertex_program_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_vertex_program");
        if (supported_extensions.contains("GL_ARB_vertex_shader") && !ARB_vertex_shader_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_vertex_shader");
        if (supported_extensions.contains("GL_ARB_vertex_type_2_10_10_10_rev") && !ARB_vertex_type_2_10_10_10_rev_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_vertex_type_2_10_10_10_rev");
        if (supported_extensions.contains("GL_ARB_viewport_array") && !ARB_viewport_array_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ARB_viewport_array");
        if (supported_extensions.contains("GL_ARB_window_pos") && !ARB_window_pos_initNativeFunctionAddresses(forwardCompatible))
            remove(supported_extensions, "GL_ARB_window_pos");
        if (supported_extensions.contains("GL_ATI_draw_buffers") && !ATI_draw_buffers_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_draw_buffers");
        if (supported_extensions.contains("GL_ATI_element_array") && !ATI_element_array_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_element_array");
        if (supported_extensions.contains("GL_ATI_envmap_bumpmap") && !ATI_envmap_bumpmap_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_envmap_bumpmap");
        if (supported_extensions.contains("GL_ATI_fragment_shader") && !ATI_fragment_shader_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_fragment_shader");
        if (supported_extensions.contains("GL_ATI_map_object_buffer") && !ATI_map_object_buffer_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_map_object_buffer");
        if (supported_extensions.contains("GL_ATI_pn_triangles") && !ATI_pn_triangles_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_pn_triangles");
        if (supported_extensions.contains("GL_ATI_separate_stencil") && !ATI_separate_stencil_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_separate_stencil");
        if (supported_extensions.contains("GL_ATI_vertex_array_object") && !ATI_vertex_array_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_vertex_array_object");
        if (supported_extensions.contains("GL_ATI_vertex_attrib_array_object") && !ATI_vertex_attrib_array_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_vertex_attrib_array_object");
        if (supported_extensions.contains("GL_ATI_vertex_streams") && !ATI_vertex_streams_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_ATI_vertex_streams");
        if (supported_extensions.contains("GL_EXT_bindable_uniform") && !EXT_bindable_uniform_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_bindable_uniform");
        if (supported_extensions.contains("GL_EXT_blend_color") && !EXT_blend_color_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_blend_color");
        if (supported_extensions.contains("GL_EXT_blend_equation_separate") && !EXT_blend_equation_separate_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_blend_equation_separate");
        if (supported_extensions.contains("GL_EXT_blend_func_separate") && !EXT_blend_func_separate_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_blend_func_separate");
        if (supported_extensions.contains("GL_EXT_blend_minmax") && !EXT_blend_minmax_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_blend_minmax");
        if (supported_extensions.contains("GL_EXT_compiled_vertex_array") && !EXT_compiled_vertex_array_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_compiled_vertex_array");
        if (supported_extensions.contains("GL_EXT_depth_bounds_test") && !EXT_depth_bounds_test_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_depth_bounds_test");
        supported_extensions.add("GL_EXT_direct_state_access");
        if (supported_extensions.contains("GL_EXT_direct_state_access") && !EXT_direct_state_access_initNativeFunctionAddresses(forwardCompatible,supported_extensions))
            remove(supported_extensions, "GL_EXT_direct_state_access");
        if (supported_extensions.contains("GL_EXT_draw_buffers2") && !EXT_draw_buffers2_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_draw_buffers2");
        if (supported_extensions.contains("GL_EXT_draw_instanced") && !EXT_draw_instanced_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_draw_instanced");
        if (supported_extensions.contains("GL_EXT_draw_range_elements") && !EXT_draw_range_elements_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_draw_range_elements");
        if (supported_extensions.contains("GL_EXT_fog_coord") && !EXT_fog_coord_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_fog_coord");
        if (supported_extensions.contains("GL_EXT_framebuffer_blit") && !EXT_framebuffer_blit_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_framebuffer_blit");
        if (supported_extensions.contains("GL_EXT_framebuffer_multisample") && !EXT_framebuffer_multisample_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_framebuffer_multisample");
        if (supported_extensions.contains("GL_EXT_framebuffer_object") && !EXT_framebuffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_framebuffer_object");
        if (supported_extensions.contains("GL_EXT_geometry_shader4") && !EXT_geometry_shader4_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_geometry_shader4");
        if (supported_extensions.contains("GL_EXT_gpu_program_parameters") && !EXT_gpu_program_parameters_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_gpu_program_parameters");
        if (supported_extensions.contains("GL_EXT_gpu_shader4") && !EXT_gpu_shader4_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_gpu_shader4");
        if (supported_extensions.contains("GL_EXT_multi_draw_arrays") && !EXT_multi_draw_arrays_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_multi_draw_arrays");
        if (supported_extensions.contains("GL_EXT_paletted_texture") && !EXT_paletted_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_paletted_texture");
        if (supported_extensions.contains("GL_EXT_point_parameters") && !EXT_point_parameters_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_point_parameters");
        if (supported_extensions.contains("GL_EXT_provoking_vertex") && !EXT_provoking_vertex_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_provoking_vertex");
        if (supported_extensions.contains("GL_EXT_secondary_color") && !EXT_secondary_color_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_secondary_color");
        if (supported_extensions.contains("GL_EXT_separate_shader_objects") && !EXT_separate_shader_objects_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_separate_shader_objects");
        if (supported_extensions.contains("GL_EXT_shader_image_load_store") && !EXT_shader_image_load_store_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_shader_image_load_store");
        if (supported_extensions.contains("GL_EXT_stencil_clear_tag") && !EXT_stencil_clear_tag_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_stencil_clear_tag");
        if (supported_extensions.contains("GL_EXT_stencil_two_side") && !EXT_stencil_two_side_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_stencil_two_side");
        if (supported_extensions.contains("GL_EXT_texture_array") && !EXT_texture_array_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_texture_array");
        if (supported_extensions.contains("GL_EXT_texture_buffer_object") && !EXT_texture_buffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_texture_buffer_object");
        if (supported_extensions.contains("GL_EXT_texture_integer") && !EXT_texture_integer_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_texture_integer");
        if (supported_extensions.contains("GL_EXT_timer_query") && !EXT_timer_query_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_timer_query");
        if (supported_extensions.contains("GL_EXT_transform_feedback") && !EXT_transform_feedback_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_transform_feedback");
        if (supported_extensions.contains("GL_EXT_vertex_attrib_64bit") && !EXT_vertex_attrib_64bit_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_EXT_vertex_attrib_64bit");
        if (supported_extensions.contains("GL_EXT_vertex_shader") && !EXT_vertex_shader_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_vertex_shader");
        if (supported_extensions.contains("GL_EXT_vertex_weighting") && !EXT_vertex_weighting_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_EXT_vertex_weighting");
        if (supported_extensions.contains("OpenGL12") && !GL12_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL12");
        if (supported_extensions.contains("OpenGL13") && !GL13_initNativeFunctionAddresses(forwardCompatible))
            remove(supported_extensions, "OpenGL13");
        if (supported_extensions.contains("OpenGL14") && !GL14_initNativeFunctionAddresses(forwardCompatible))
            remove(supported_extensions, "OpenGL14");
        if (supported_extensions.contains("OpenGL15") && !GL15_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL15");
        if (supported_extensions.contains("OpenGL20") && !GL20_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL20");
        if (supported_extensions.contains("OpenGL21") && !GL21_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL21");
        if (supported_extensions.contains("OpenGL30") && !GL30_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL30");
        if (supported_extensions.contains("OpenGL31") && !GL31_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL31");
        if (supported_extensions.contains("OpenGL32") && !GL32_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL32");
        if (supported_extensions.contains("OpenGL33") && !GL33_initNativeFunctionAddresses(forwardCompatible))
            remove(supported_extensions, "OpenGL33");
        if (supported_extensions.contains("OpenGL40") && !GL40_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL40");
        if (supported_extensions.contains("OpenGL41") && !GL41_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL41");
        if (supported_extensions.contains("OpenGL42") && !GL42_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL42");
        if (supported_extensions.contains("OpenGL43") && !GL43_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL43");
        if (supported_extensions.contains("OpenGL44") && !GL44_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL44");
        if (supported_extensions.contains("OpenGL45") && !GL45_initNativeFunctionAddresses())
            remove(supported_extensions, "OpenGL45");
        if (supported_extensions.contains("GL_GREMEDY_frame_terminator") && !GREMEDY_frame_terminator_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_GREMEDY_frame_terminator");
        if (supported_extensions.contains("GL_GREMEDY_string_marker") && !GREMEDY_string_marker_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_GREMEDY_string_marker");
        if (supported_extensions.contains("GL_INTEL_map_texture") && !INTEL_map_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_INTEL_map_texture");
        if (supported_extensions.contains("GL_KHR_debug") && !KHR_debug_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_KHR_debug");
        if (supported_extensions.contains("GL_KHR_robustness") && !KHR_robustness_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_KHR_robustness");
        if (supported_extensions.contains("GL_NV_bindless_multi_draw_indirect") && !NV_bindless_multi_draw_indirect_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_bindless_multi_draw_indirect");
        if (supported_extensions.contains("GL_NV_bindless_texture") && !NV_bindless_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_bindless_texture");
        if (supported_extensions.contains("GL_NV_blend_equation_advanced") && !NV_blend_equation_advanced_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_blend_equation_advanced");
        if (supported_extensions.contains("GL_NV_conditional_render") && !NV_conditional_render_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_conditional_render");
        if (supported_extensions.contains("GL_NV_copy_image") && !NV_copy_image_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_copy_image");
        if (supported_extensions.contains("GL_NV_depth_buffer_float") && !NV_depth_buffer_float_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_depth_buffer_float");
        if (supported_extensions.contains("GL_NV_draw_texture") && !NV_draw_texture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_draw_texture");
        if (supported_extensions.contains("GL_NV_evaluators") && !NV_evaluators_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_evaluators");
        if (supported_extensions.contains("GL_NV_explicit_multisample") && !NV_explicit_multisample_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_explicit_multisample");
        if (supported_extensions.contains("GL_NV_fence") && !NV_fence_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_fence");
        if (supported_extensions.contains("GL_NV_fragment_program") && !NV_fragment_program_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_fragment_program");
        if (supported_extensions.contains("GL_NV_framebuffer_multisample_coverage") && !NV_framebuffer_multisample_coverage_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_framebuffer_multisample_coverage");
        if (supported_extensions.contains("GL_NV_geometry_program4") && !NV_geometry_program4_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_geometry_program4");
        if (supported_extensions.contains("GL_NV_gpu_program4") && !NV_gpu_program4_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_gpu_program4");
        if (supported_extensions.contains("GL_NV_gpu_shader5") && !NV_gpu_shader5_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_NV_gpu_shader5");
        if (supported_extensions.contains("GL_NV_half_float") && !NV_half_float_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_NV_half_float");
        if (supported_extensions.contains("GL_NV_occlusion_query") && !NV_occlusion_query_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_occlusion_query");
        if (supported_extensions.contains("GL_NV_parameter_buffer_object") && !NV_parameter_buffer_object_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_parameter_buffer_object");
        if (supported_extensions.contains("GL_NV_path_rendering") && !NV_path_rendering_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_path_rendering");
        if (supported_extensions.contains("GL_NV_pixel_data_range") && !NV_pixel_data_range_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_pixel_data_range");
        if (supported_extensions.contains("GL_NV_point_sprite") && !NV_point_sprite_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_point_sprite");
        if (supported_extensions.contains("GL_NV_present_video") && !NV_present_video_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_present_video");
        supported_extensions.add("GL_NV_primitive_restart");
        if (supported_extensions.contains("GL_NV_primitive_restart") && !NV_primitive_restart_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_primitive_restart");
        if (supported_extensions.contains("GL_NV_program") && !NV_program_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_program");
        if (supported_extensions.contains("GL_NV_register_combiners") && !NV_register_combiners_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_register_combiners");
        if (supported_extensions.contains("GL_NV_register_combiners2") && !NV_register_combiners2_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_register_combiners2");
        if (supported_extensions.contains("GL_NV_shader_buffer_load") && !NV_shader_buffer_load_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_shader_buffer_load");
        if (supported_extensions.contains("GL_NV_texture_barrier") && !NV_texture_barrier_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_texture_barrier");
        if (supported_extensions.contains("GL_NV_texture_multisample") && !NV_texture_multisample_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_texture_multisample");
        if (supported_extensions.contains("GL_NV_transform_feedback") && !NV_transform_feedback_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_transform_feedback");
        if (supported_extensions.contains("GL_NV_transform_feedback2") && !NV_transform_feedback2_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_transform_feedback2");
        if (supported_extensions.contains("GL_NV_vertex_array_range") && !NV_vertex_array_range_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_vertex_array_range");
        if (supported_extensions.contains("GL_NV_vertex_attrib_integer_64bit") && !NV_vertex_attrib_integer_64bit_initNativeFunctionAddresses(supported_extensions))
            remove(supported_extensions, "GL_NV_vertex_attrib_integer_64bit");
        if (supported_extensions.contains("GL_NV_vertex_buffer_unified_memory") && !NV_vertex_buffer_unified_memory_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_vertex_buffer_unified_memory");
        if (supported_extensions.contains("GL_NV_vertex_program") && !NV_vertex_program_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_vertex_program");
        if (supported_extensions.contains("GL_NV_video_capture") && !NV_video_capture_initNativeFunctionAddresses())
            remove(supported_extensions, "GL_NV_video_capture");
        return supported_extensions;
    }

    static int getSupportedExtensions(final Set<String> supported_extensions) {
        // Detect OpenGL version first

        final String version = glGetString(GL_VERSION);
        if ( version == null )
            throw new IllegalStateException("glGetString(GL_VERSION) returned null - possibly caused by missing current context.");

        final StringTokenizer version_tokenizer = new StringTokenizer(version, ". ");
        final String major_string = version_tokenizer.nextToken();
        final String minor_string = version_tokenizer.nextToken();

        int majorVersion = 0;
        int minorVersion = 0;
        try {
            majorVersion = Integer.parseInt(major_string);
            minorVersion = Integer.parseInt(minor_string);
        } catch (NumberFormatException e) {
            LWJGLUtil.log("The major and/or minor OpenGL version is malformed: " + e.getMessage());
        }

        final int[][] GL_VERSIONS = {
                { 1, 2, 3, 4, 5 },      // OpenGL 1
                { 0, 1 },               // OpenGL 2
                { 0, 1, 2, 3 },         // OpenGL 3
                { 0, 1, 2, 3, 4, 5 },   // OpenGL 4
        };

        for ( int major = 1; major <= GL_VERSIONS.length; major++ ) {
            int[] minors = GL_VERSIONS[major - 1];
            for ( int minor : minors ) {
                if ( major < majorVersion || (major == majorVersion && minor <= minorVersion) )
                    supported_extensions.add("OpenGL" + Integer.toString(major) + Integer.toString(minor));
            }
        }

        int profileMask = 0;

        if ( majorVersion < 3 ) {
            // Parse EXTENSIONS string
            final String extensions_string = glGetString(GL_EXTENSIONS);
            if ( extensions_string == null )
                throw new IllegalStateException("glGetString(GL_EXTENSIONS) returned null - is there a context current?");

            final StringTokenizer tokenizer = new StringTokenizer(extensions_string);
            while ( tokenizer.hasMoreTokens() )
                supported_extensions.add(tokenizer.nextToken());
        } else {
            // Use forward compatible indexed EXTENSIONS
            final int extensionCount = glGetInteger(GL_NUM_EXTENSIONS);

            for ( int i = 0; i < extensionCount; i++ )
                supported_extensions.add(glGetStringi(GL_EXTENSIONS, i));

            // Get the context profile mask for versions >= 3.2
            if ( 3 < majorVersion || 2 <= minorVersion ) {
                Util.checkGLError(); // Make sure we have no errors up to this point

                try {
                    profileMask = glGetInteger(GL_CONTEXT_PROFILE_MASK);
                    // Retrieving GL_CONTEXT_PROFILE_MASK may generate an INVALID_OPERATION error on certain implementations, ignore.
                    // Happens on pre10.1 ATI drivers, when ContextAttribs.withProfileCompatibility is not used
                    Util.checkGLError();
                } catch (OpenGLException e) {
                    LWJGLUtil.log("Failed to retrieve CONTEXT_PROFILE_MASK");
                }
            }
        }

        return profileMask;
    }

    static void unloadAllStubs() {}

    ContextCapabilities(boolean forwardCompatible) throws LWJGLException {
        /*Set<String> supported_extensions = initAllStubs(forwardCompatible);
        this.GL_AMD_blend_minmax_factor = supported_extensions.contains("GL_AMD_blend_minmax_factor");
        this.GL_AMD_conservative_depth = supported_extensions.contains("GL_AMD_conservative_depth");
        this.GL_AMD_debug_output = supported_extensions.contains("GL_AMD_debug_output")
            || supported_extensions.contains("GL_AMDX_debug_output");
        this.GL_AMD_depth_clamp_separate = supported_extensions.contains("GL_AMD_depth_clamp_separate");
        this.GL_AMD_draw_buffers_blend = supported_extensions.contains("GL_AMD_draw_buffers_blend");
        this.GL_AMD_interleaved_elements = supported_extensions.contains("GL_AMD_interleaved_elements");
        this.GL_AMD_multi_draw_indirect = supported_extensions.contains("GL_AMD_multi_draw_indirect");
        this.GL_AMD_name_gen_delete = supported_extensions.contains("GL_AMD_name_gen_delete");
        this.GL_AMD_performance_monitor = supported_extensions.contains("GL_AMD_performance_monitor");
        this.GL_AMD_pinned_memory = supported_extensions.contains("GL_AMD_pinned_memory");
        this.GL_AMD_query_buffer_object = supported_extensions.contains("GL_AMD_query_buffer_object");
        this.GL_AMD_sample_positions = supported_extensions.contains("GL_AMD_sample_positions");
        this.GL_AMD_seamless_cubemap_per_texture = supported_extensions.contains("GL_AMD_seamless_cubemap_per_texture");
        this.GL_AMD_shader_atomic_counter_ops = supported_extensions.contains("GL_AMD_shader_atomic_counter_ops");
        this.GL_AMD_shader_stencil_export = supported_extensions.contains("GL_AMD_shader_stencil_export");
        this.GL_AMD_shader_trinary_minmax = supported_extensions.contains("GL_AMD_shader_trinary_minmax");
        this.GL_AMD_sparse_texture = supported_extensions.contains("GL_AMD_sparse_texture");
        this.GL_AMD_stencil_operation_extended = supported_extensions.contains("GL_AMD_stencil_operation_extended");
        this.GL_AMD_texture_texture4 = supported_extensions.contains("GL_AMD_texture_texture4");
        this.GL_AMD_transform_feedback3_lines_triangles = supported_extensions.contains("GL_AMD_transform_feedback3_lines_triangles");
        this.GL_AMD_vertex_shader_layer = supported_extensions.contains("GL_AMD_vertex_shader_layer");
        this.GL_AMD_vertex_shader_tessellator = supported_extensions.contains("GL_AMD_vertex_shader_tessellator");
        this.GL_AMD_vertex_shader_viewport_index = supported_extensions.contains("GL_AMD_vertex_shader_viewport_index");
        this.GL_APPLE_aux_depth_stencil = supported_extensions.contains("GL_APPLE_aux_depth_stencil");
        this.GL_APPLE_client_storage = supported_extensions.contains("GL_APPLE_client_storage");
        this.GL_APPLE_element_array = supported_extensions.contains("GL_APPLE_element_array");
        this.GL_APPLE_fence = supported_extensions.contains("GL_APPLE_fence");
        this.GL_APPLE_float_pixels = supported_extensions.contains("GL_APPLE_float_pixels");
        this.GL_APPLE_flush_buffer_range = supported_extensions.contains("GL_APPLE_flush_buffer_range");
        this.GL_APPLE_object_purgeable = supported_extensions.contains("GL_APPLE_object_purgeable");
        this.GL_APPLE_packed_pixels = supported_extensions.contains("GL_APPLE_packed_pixels");
        this.GL_APPLE_rgb_422 = supported_extensions.contains("GL_APPLE_rgb_422");
        this.GL_APPLE_row_bytes = supported_extensions.contains("GL_APPLE_row_bytes");
        this.GL_APPLE_texture_range = supported_extensions.contains("GL_APPLE_texture_range");
        this.GL_APPLE_vertex_array_object = supported_extensions.contains("GL_APPLE_vertex_array_object");
        this.GL_APPLE_vertex_array_range = supported_extensions.contains("GL_APPLE_vertex_array_range");
        this.GL_APPLE_vertex_program_evaluators = supported_extensions.contains("GL_APPLE_vertex_program_evaluators");
        this.GL_APPLE_ycbcr_422 = supported_extensions.contains("GL_APPLE_ycbcr_422");
        this.GL_ARB_ES2_compatibility = supported_extensions.contains("GL_ARB_ES2_compatibility");
        this.GL_ARB_ES3_1_compatibility = supported_extensions.contains("GL_ARB_ES3_1_compatibility");
        this.GL_ARB_ES3_compatibility = supported_extensions.contains("GL_ARB_ES3_compatibility");
        this.GL_ARB_arrays_of_arrays = supported_extensions.contains("GL_ARB_arrays_of_arrays");
        this.GL_ARB_base_instance = supported_extensions.contains("GL_ARB_base_instance");
        this.GL_ARB_bindless_texture = supported_extensions.contains("GL_ARB_bindless_texture");
        this.GL_ARB_blend_func_extended = supported_extensions.contains("GL_ARB_blend_func_extended");
        this.GL_ARB_buffer_storage = supported_extensions.contains("GL_ARB_buffer_storage");
        this.GL_ARB_cl_event = supported_extensions.contains("GL_ARB_cl_event");
        this.GL_ARB_clear_buffer_object = supported_extensions.contains("GL_ARB_clear_buffer_object");
        this.GL_ARB_clear_texture = supported_extensions.contains("GL_ARB_clear_texture");
        this.GL_ARB_clip_control = supported_extensions.contains("GL_ARB_clip_control");
        this.GL_ARB_color_buffer_float = supported_extensions.contains("GL_ARB_color_buffer_float");
        this.GL_ARB_compatibility = supported_extensions.contains("GL_ARB_compatibility");
        this.GL_ARB_compressed_texture_pixel_storage = supported_extensions.contains("GL_ARB_compressed_texture_pixel_storage");
        this.GL_ARB_compute_shader = supported_extensions.contains("GL_ARB_compute_shader");
        this.GL_ARB_compute_variable_group_size = supported_extensions.contains("GL_ARB_compute_variable_group_size");
        this.GL_ARB_conditional_render_inverted = supported_extensions.contains("GL_ARB_conditional_render_inverted");
        this.GL_ARB_conservative_depth = supported_extensions.contains("GL_ARB_conservative_depth");
        this.GL_ARB_copy_buffer = supported_extensions.contains("GL_ARB_copy_buffer");
        this.GL_ARB_copy_image = supported_extensions.contains("GL_ARB_copy_image");
        this.GL_ARB_cull_distance = supported_extensions.contains("GL_ARB_cull_distance");
        this.GL_ARB_debug_output = supported_extensions.contains("GL_ARB_debug_output");
        this.GL_ARB_depth_buffer_float = supported_extensions.contains("GL_ARB_depth_buffer_float");
        this.GL_ARB_depth_clamp = supported_extensions.contains("GL_ARB_depth_clamp");
        this.GL_ARB_depth_texture = supported_extensions.contains("GL_ARB_depth_texture");
        this.GL_ARB_derivative_control = supported_extensions.contains("GL_ARB_derivative_control");
        this.GL_ARB_direct_state_access = supported_extensions.contains("GL_ARB_direct_state_access");
        this.GL_ARB_draw_buffers = supported_extensions.contains("GL_ARB_draw_buffers");
        this.GL_ARB_draw_buffers_blend = supported_extensions.contains("GL_ARB_draw_buffers_blend");
        this.GL_ARB_draw_elements_base_vertex = supported_extensions.contains("GL_ARB_draw_elements_base_vertex");
        this.GL_ARB_draw_indirect = supported_extensions.contains("GL_ARB_draw_indirect");
        this.GL_ARB_draw_instanced = supported_extensions.contains("GL_ARB_draw_instanced");
        this.GL_ARB_enhanced_layouts = supported_extensions.contains("GL_ARB_enhanced_layouts");
        this.GL_ARB_explicit_attrib_location = supported_extensions.contains("GL_ARB_explicit_attrib_location");
        this.GL_ARB_explicit_uniform_location = supported_extensions.contains("GL_ARB_explicit_uniform_location");
        this.GL_ARB_fragment_coord_conventions = supported_extensions.contains("GL_ARB_fragment_coord_conventions");
        this.GL_ARB_fragment_layer_viewport = supported_extensions.contains("GL_ARB_fragment_layer_viewport");
        this.GL_ARB_fragment_program = supported_extensions.contains("GL_ARB_fragment_program")
            && supported_extensions.contains("GL_ARB_program");
        this.GL_ARB_fragment_program_shadow = supported_extensions.contains("GL_ARB_fragment_program_shadow");
        this.GL_ARB_fragment_shader = supported_extensions.contains("GL_ARB_fragment_shader");
        this.GL_ARB_framebuffer_no_attachments = supported_extensions.contains("GL_ARB_framebuffer_no_attachments");
        this.GL_ARB_framebuffer_object = supported_extensions.contains("GL_ARB_framebuffer_object");
        this.GL_ARB_framebuffer_sRGB = supported_extensions.contains("GL_ARB_framebuffer_sRGB");
        this.GL_ARB_geometry_shader4 = supported_extensions.contains("GL_ARB_geometry_shader4");
        this.GL_ARB_get_program_binary = supported_extensions.contains("GL_ARB_get_program_binary");
        this.GL_ARB_get_texture_sub_image = supported_extensions.contains("GL_ARB_get_texture_sub_image");
        this.GL_ARB_gpu_shader5 = supported_extensions.contains("GL_ARB_gpu_shader5");
        this.GL_ARB_gpu_shader_fp64 = supported_extensions.contains("GL_ARB_gpu_shader_fp64");
        this.GL_ARB_half_float_pixel = supported_extensions.contains("GL_ARB_half_float_pixel");
        this.GL_ARB_half_float_vertex = supported_extensions.contains("GL_ARB_half_float_vertex");
        this.GL_ARB_imaging = supported_extensions.contains("GL_ARB_imaging");
        this.GL_ARB_indirect_parameters = supported_extensions.contains("GL_ARB_indirect_parameters");
        this.GL_ARB_instanced_arrays = supported_extensions.contains("GL_ARB_instanced_arrays");
        this.GL_ARB_internalformat_query = supported_extensions.contains("GL_ARB_internalformat_query");
        this.GL_ARB_internalformat_query2 = supported_extensions.contains("GL_ARB_internalformat_query2");
        this.GL_ARB_invalidate_subdata = supported_extensions.contains("GL_ARB_invalidate_subdata");
        this.GL_ARB_map_buffer_alignment = supported_extensions.contains("GL_ARB_map_buffer_alignment");
        this.GL_ARB_map_buffer_range = supported_extensions.contains("GL_ARB_map_buffer_range");
        this.GL_ARB_matrix_palette = supported_extensions.contains("GL_ARB_matrix_palette");
        this.GL_ARB_multi_bind = supported_extensions.contains("GL_ARB_multi_bind");
        this.GL_ARB_multi_draw_indirect = supported_extensions.contains("GL_ARB_multi_draw_indirect");
        this.GL_ARB_multisample = supported_extensions.contains("GL_ARB_multisample");
        this.GL_ARB_multitexture = supported_extensions.contains("GL_ARB_multitexture");
        this.GL_ARB_occlusion_query = supported_extensions.contains("GL_ARB_occlusion_query");
        this.GL_ARB_occlusion_query2 = supported_extensions.contains("GL_ARB_occlusion_query2");
        this.GL_ARB_pipeline_statistics_query = supported_extensions.contains("GL_ARB_pipeline_statistics_query");
        this.GL_ARB_pixel_buffer_object = supported_extensions.contains("GL_ARB_pixel_buffer_object")
            && supported_extensions.contains("GL_ARB_buffer_object");
        this.GL_ARB_point_parameters = supported_extensions.contains("GL_ARB_point_parameters");
        this.GL_ARB_point_sprite = supported_extensions.contains("GL_ARB_point_sprite");
        this.GL_ARB_program_interface_query = supported_extensions.contains("GL_ARB_program_interface_query");
        this.GL_ARB_provoking_vertex = supported_extensions.contains("GL_ARB_provoking_vertex");
        this.GL_ARB_query_buffer_object = supported_extensions.contains("GL_ARB_query_buffer_object");
        this.GL_ARB_robust_buffer_access_behavior = supported_extensions.contains("GL_ARB_robust_buffer_access_behavior");
        this.GL_ARB_robustness = supported_extensions.contains("GL_ARB_robustness");
        this.GL_ARB_robustness_isolation = supported_extensions.contains("GL_ARB_robustness_isolation");
        this.GL_ARB_sample_shading = supported_extensions.contains("GL_ARB_sample_shading");
        this.GL_ARB_sampler_objects = supported_extensions.contains("GL_ARB_sampler_objects");
        this.GL_ARB_seamless_cube_map = supported_extensions.contains("GL_ARB_seamless_cube_map");
        this.GL_ARB_seamless_cubemap_per_texture = supported_extensions.contains("GL_ARB_seamless_cubemap_per_texture");
        this.GL_ARB_separate_shader_objects = supported_extensions.contains("GL_ARB_separate_shader_objects");
        this.GL_ARB_shader_atomic_counters = supported_extensions.contains("GL_ARB_shader_atomic_counters");
        this.GL_ARB_shader_bit_encoding = supported_extensions.contains("GL_ARB_shader_bit_encoding");
        this.GL_ARB_shader_draw_parameters = supported_extensions.contains("GL_ARB_shader_draw_parameters");
        this.GL_ARB_shader_group_vote = supported_extensions.contains("GL_ARB_shader_group_vote");
        this.GL_ARB_shader_image_load_store = supported_extensions.contains("GL_ARB_shader_image_load_store");
        this.GL_ARB_shader_image_size = supported_extensions.contains("GL_ARB_shader_image_size");
        this.GL_ARB_shader_objects = supported_extensions.contains("GL_ARB_shader_objects");
        this.GL_ARB_shader_precision = supported_extensions.contains("GL_ARB_shader_precision");
        this.GL_ARB_shader_stencil_export = supported_extensions.contains("GL_ARB_shader_stencil_export");
        this.GL_ARB_shader_storage_buffer_object = supported_extensions.contains("GL_ARB_shader_storage_buffer_object");
        this.GL_ARB_shader_subroutine = supported_extensions.contains("GL_ARB_shader_subroutine");
        this.GL_ARB_shader_texture_image_samples = supported_extensions.contains("GL_ARB_shader_texture_image_samples");
        this.GL_ARB_shader_texture_lod = supported_extensions.contains("GL_ARB_shader_texture_lod");
        this.GL_ARB_shading_language_100 = supported_extensions.contains("GL_ARB_shading_language_100");
        this.GL_ARB_shading_language_420pack = supported_extensions.contains("GL_ARB_shading_language_420pack");
        this.GL_ARB_shading_language_include = supported_extensions.contains("GL_ARB_shading_language_include");
        this.GL_ARB_shading_language_packing = supported_extensions.contains("GL_ARB_shading_language_packing");
        this.GL_ARB_shadow = supported_extensions.contains("GL_ARB_shadow");
        this.GL_ARB_shadow_ambient = supported_extensions.contains("GL_ARB_shadow_ambient");
        this.GL_ARB_sparse_buffer = supported_extensions.contains("GL_ARB_sparse_buffer");
        this.GL_ARB_sparse_texture = supported_extensions.contains("GL_ARB_sparse_texture");
        this.GL_ARB_stencil_texturing = supported_extensions.contains("GL_ARB_stencil_texturing");
        this.GL_ARB_sync = supported_extensions.contains("GL_ARB_sync");
        this.GL_ARB_tessellation_shader = supported_extensions.contains("GL_ARB_tessellation_shader");
        this.GL_ARB_texture_barrier = supported_extensions.contains("GL_ARB_texture_barrier");
        this.GL_ARB_texture_border_clamp = supported_extensions.contains("GL_ARB_texture_border_clamp");
        this.GL_ARB_texture_buffer_object = supported_extensions.contains("GL_ARB_texture_buffer_object");
        this.GL_ARB_texture_buffer_object_rgb32 = supported_extensions.contains("GL_ARB_texture_buffer_object_rgb32")
            || supported_extensions.contains("GL_EXT_texture_buffer_object_rgb32");
        this.GL_ARB_texture_buffer_range = supported_extensions.contains("GL_ARB_texture_buffer_range");
        this.GL_ARB_texture_compression = supported_extensions.contains("GL_ARB_texture_compression");
        this.GL_ARB_texture_compression_bptc = supported_extensions.contains("GL_ARB_texture_compression_bptc")
            || supported_extensions.contains("GL_EXT_texture_compression_bptc");
        this.GL_ARB_texture_compression_rgtc = supported_extensions.contains("GL_ARB_texture_compression_rgtc");
        this.GL_ARB_texture_cube_map = supported_extensions.contains("GL_ARB_texture_cube_map");
        this.GL_ARB_texture_cube_map_array = supported_extensions.contains("GL_ARB_texture_cube_map_array");
        this.GL_ARB_texture_env_add = supported_extensions.contains("GL_ARB_texture_env_add");
        this.GL_ARB_texture_env_combine = supported_extensions.contains("GL_ARB_texture_env_combine");
        this.GL_ARB_texture_env_crossbar = supported_extensions.contains("GL_ARB_texture_env_crossbar");
        this.GL_ARB_texture_env_dot3 = supported_extensions.contains("GL_ARB_texture_env_dot3");
        this.GL_ARB_texture_float = supported_extensions.contains("GL_ARB_texture_float");
        this.GL_ARB_texture_gather = supported_extensions.contains("GL_ARB_texture_gather");
        this.GL_ARB_texture_mirror_clamp_to_edge = supported_extensions.contains("GL_ARB_texture_mirror_clamp_to_edge");
        this.GL_ARB_texture_mirrored_repeat = supported_extensions.contains("GL_ARB_texture_mirrored_repeat");
        this.GL_ARB_texture_multisample = supported_extensions.contains("GL_ARB_texture_multisample");
        this.GL_ARB_texture_non_power_of_two = supported_extensions.contains("GL_ARB_texture_non_power_of_two");
        this.GL_ARB_texture_query_levels = supported_extensions.contains("GL_ARB_texture_query_levels");
        this.GL_ARB_texture_query_lod = supported_extensions.contains("GL_ARB_texture_query_lod");
        this.GL_ARB_texture_rectangle = supported_extensions.contains("GL_ARB_texture_rectangle");
        this.GL_ARB_texture_rg = supported_extensions.contains("GL_ARB_texture_rg");
        this.GL_ARB_texture_rgb10_a2ui = supported_extensions.contains("GL_ARB_texture_rgb10_a2ui");
        this.GL_ARB_texture_stencil8 = supported_extensions.contains("GL_ARB_texture_stencil8");
        this.GL_ARB_texture_storage = supported_extensions.contains("GL_ARB_texture_storage")
            || supported_extensions.contains("GL_EXT_texture_storage");
        this.GL_ARB_texture_storage_multisample = supported_extensions.contains("GL_ARB_texture_storage_multisample");
        this.GL_ARB_texture_swizzle = supported_extensions.contains("GL_ARB_texture_swizzle");
        this.GL_ARB_texture_view = supported_extensions.contains("GL_ARB_texture_view");
        this.GL_ARB_timer_query = supported_extensions.contains("GL_ARB_timer_query");
        this.GL_ARB_transform_feedback2 = supported_extensions.contains("GL_ARB_transform_feedback2");
        this.GL_ARB_transform_feedback3 = supported_extensions.contains("GL_ARB_transform_feedback3");
        this.GL_ARB_transform_feedback_instanced = supported_extensions.contains("GL_ARB_transform_feedback_instanced");
        this.GL_ARB_transform_feedback_overflow_query = supported_extensions.contains("GL_ARB_transform_feedback_overflow_query");
        this.GL_ARB_transpose_matrix = supported_extensions.contains("GL_ARB_transpose_matrix");
        this.GL_ARB_uniform_buffer_object = supported_extensions.contains("GL_ARB_uniform_buffer_object");
        this.GL_ARB_vertex_array_bgra = supported_extensions.contains("GL_ARB_vertex_array_bgra");
        this.GL_ARB_vertex_array_object = supported_extensions.contains("GL_ARB_vertex_array_object");
        this.GL_ARB_vertex_attrib_64bit = supported_extensions.contains("GL_ARB_vertex_attrib_64bit");
        this.GL_ARB_vertex_attrib_binding = supported_extensions.contains("GL_ARB_vertex_attrib_binding");
        this.GL_ARB_vertex_blend = supported_extensions.contains("GL_ARB_vertex_blend");
        this.GL_ARB_vertex_buffer_object = supported_extensions.contains("GL_ARB_vertex_buffer_object")
            && supported_extensions.contains("GL_ARB_buffer_object");
        this.GL_ARB_vertex_program = supported_extensions.contains("GL_ARB_vertex_program")
            && supported_extensions.contains("GL_ARB_program");
        this.GL_ARB_vertex_shader = supported_extensions.contains("GL_ARB_vertex_shader");
        this.GL_ARB_vertex_type_10f_11f_11f_rev = supported_extensions.contains("GL_ARB_vertex_type_10f_11f_11f_rev");
        this.GL_ARB_vertex_type_2_10_10_10_rev = supported_extensions.contains("GL_ARB_vertex_type_2_10_10_10_rev");
        this.GL_ARB_viewport_array = supported_extensions.contains("GL_ARB_viewport_array");
        this.GL_ARB_window_pos = supported_extensions.contains("GL_ARB_window_pos");
        this.GL_ATI_draw_buffers = supported_extensions.contains("GL_ATI_draw_buffers");
        this.GL_ATI_element_array = supported_extensions.contains("GL_ATI_element_array");
        this.GL_ATI_envmap_bumpmap = supported_extensions.contains("GL_ATI_envmap_bumpmap");
        this.GL_ATI_fragment_shader = supported_extensions.contains("GL_ATI_fragment_shader");
        this.GL_ATI_map_object_buffer = supported_extensions.contains("GL_ATI_map_object_buffer");
        this.GL_ATI_meminfo = supported_extensions.contains("GL_ATI_meminfo");
        this.GL_ATI_pn_triangles = supported_extensions.contains("GL_ATI_pn_triangles");
        this.GL_ATI_separate_stencil = supported_extensions.contains("GL_ATI_separate_stencil");
        this.GL_ATI_shader_texture_lod = supported_extensions.contains("GL_ATI_shader_texture_lod");
        this.GL_ATI_text_fragment_shader = supported_extensions.contains("GL_ATI_text_fragment_shader");
        this.GL_ATI_texture_compression_3dc = supported_extensions.contains("GL_ATI_texture_compression_3dc");
        this.GL_ATI_texture_env_combine3 = supported_extensions.contains("GL_ATI_texture_env_combine3");
        this.GL_ATI_texture_float = supported_extensions.contains("GL_ATI_texture_float");
        this.GL_ATI_texture_mirror_once = supported_extensions.contains("GL_ATI_texture_mirror_once");
        this.GL_ATI_vertex_array_object = supported_extensions.contains("GL_ATI_vertex_array_object");
        this.GL_ATI_vertex_attrib_array_object = supported_extensions.contains("GL_ATI_vertex_attrib_array_object");
        this.GL_ATI_vertex_streams = supported_extensions.contains("GL_ATI_vertex_streams");
        this.GL_EXT_Cg_shader = supported_extensions.contains("GL_EXT_Cg_shader");
        this.GL_EXT_abgr = supported_extensions.contains("GL_EXT_abgr");
        this.GL_EXT_bgra = supported_extensions.contains("GL_EXT_bgra");
        this.GL_EXT_bindable_uniform = supported_extensions.contains("GL_EXT_bindable_uniform");
        this.GL_EXT_blend_color = supported_extensions.contains("GL_EXT_blend_color");
        this.GL_EXT_blend_equation_separate = supported_extensions.contains("GL_EXT_blend_equation_separate");
        this.GL_EXT_blend_func_separate = supported_extensions.contains("GL_EXT_blend_func_separate");
        this.GL_EXT_blend_minmax = supported_extensions.contains("GL_EXT_blend_minmax");
        this.GL_EXT_blend_subtract = supported_extensions.contains("GL_EXT_blend_subtract");
        this.GL_EXT_compiled_vertex_array = supported_extensions.contains("GL_EXT_compiled_vertex_array");
        this.GL_EXT_depth_bounds_test = supported_extensions.contains("GL_EXT_depth_bounds_test");
        this.GL_EXT_direct_state_access = supported_extensions.contains("GL_EXT_direct_state_access");
        this.GL_EXT_draw_buffers2 = supported_extensions.contains("GL_EXT_draw_buffers2");
        this.GL_EXT_draw_instanced = supported_extensions.contains("GL_EXT_draw_instanced");
        this.GL_EXT_draw_range_elements = supported_extensions.contains("GL_EXT_draw_range_elements");
        this.GL_EXT_fog_coord = supported_extensions.contains("GL_EXT_fog_coord");
        this.GL_EXT_framebuffer_blit = supported_extensions.contains("GL_EXT_framebuffer_blit");
        this.GL_EXT_framebuffer_multisample = supported_extensions.contains("GL_EXT_framebuffer_multisample");
        this.GL_EXT_framebuffer_multisample_blit_scaled = supported_extensions.contains("GL_EXT_framebuffer_multisample_blit_scaled");
        this.GL_EXT_framebuffer_object = supported_extensions.contains("GL_EXT_framebuffer_object");
        this.GL_EXT_framebuffer_sRGB = supported_extensions.contains("GL_EXT_framebuffer_sRGB");
        this.GL_EXT_geometry_shader4 = supported_extensions.contains("GL_EXT_geometry_shader4");
        this.GL_EXT_gpu_program_parameters = supported_extensions.contains("GL_EXT_gpu_program_parameters");
        this.GL_EXT_gpu_shader4 = supported_extensions.contains("GL_EXT_gpu_shader4");
        this.GL_EXT_multi_draw_arrays = supported_extensions.contains("GL_EXT_multi_draw_arrays");
        this.GL_EXT_packed_depth_stencil = supported_extensions.contains("GL_EXT_packed_depth_stencil");
        this.GL_EXT_packed_float = supported_extensions.contains("GL_EXT_packed_float");
        this.GL_EXT_packed_pixels = supported_extensions.contains("GL_EXT_packed_pixels");
        this.GL_EXT_paletted_texture = supported_extensions.contains("GL_EXT_paletted_texture");
        this.GL_EXT_pixel_buffer_object = supported_extensions.contains("GL_EXT_pixel_buffer_object")
            && supported_extensions.contains("GL_ARB_buffer_object");
        this.GL_EXT_point_parameters = supported_extensions.contains("GL_EXT_point_parameters");
        this.GL_EXT_provoking_vertex = supported_extensions.contains("GL_EXT_provoking_vertex");
        this.GL_EXT_rescale_normal = supported_extensions.contains("GL_EXT_rescale_normal");
        this.GL_EXT_secondary_color = supported_extensions.contains("GL_EXT_secondary_color");
        this.GL_EXT_separate_shader_objects = supported_extensions.contains("GL_EXT_separate_shader_objects");
        this.GL_EXT_separate_specular_color = supported_extensions.contains("GL_EXT_separate_specular_color");
        this.GL_EXT_shader_image_load_store = supported_extensions.contains("GL_EXT_shader_image_load_store");
        this.GL_EXT_shadow_funcs = supported_extensions.contains("GL_EXT_shadow_funcs");
        this.GL_EXT_shared_texture_palette = supported_extensions.contains("GL_EXT_shared_texture_palette");
        this.GL_EXT_stencil_clear_tag = supported_extensions.contains("GL_EXT_stencil_clear_tag");
        this.GL_EXT_stencil_two_side = supported_extensions.contains("GL_EXT_stencil_two_side");
        this.GL_EXT_stencil_wrap = supported_extensions.contains("GL_EXT_stencil_wrap");
        this.GL_EXT_texture_3d = supported_extensions.contains("GL_EXT_texture_3d");
        this.GL_EXT_texture_array = supported_extensions.contains("GL_EXT_texture_array");
        this.GL_EXT_texture_buffer_object = supported_extensions.contains("GL_EXT_texture_buffer_object");
        this.GL_EXT_texture_compression_latc = supported_extensions.contains("GL_EXT_texture_compression_latc");
        this.GL_EXT_texture_compression_rgtc = supported_extensions.contains("GL_EXT_texture_compression_rgtc");
        this.GL_EXT_texture_compression_s3tc = supported_extensions.contains("GL_EXT_texture_compression_s3tc");
        this.GL_EXT_texture_env_combine = supported_extensions.contains("GL_EXT_texture_env_combine");
        this.GL_EXT_texture_env_dot3 = supported_extensions.contains("GL_EXT_texture_env_dot3");
        this.GL_EXT_texture_filter_anisotropic = supported_extensions.contains("GL_EXT_texture_filter_anisotropic");
        this.GL_EXT_texture_integer = supported_extensions.contains("GL_EXT_texture_integer");
        this.GL_EXT_texture_lod_bias = supported_extensions.contains("GL_EXT_texture_lod_bias");
        this.GL_EXT_texture_mirror_clamp = supported_extensions.contains("GL_EXT_texture_mirror_clamp");
        this.GL_EXT_texture_rectangle = supported_extensions.contains("GL_EXT_texture_rectangle");
        this.GL_EXT_texture_sRGB = supported_extensions.contains("GL_EXT_texture_sRGB");
        this.GL_EXT_texture_sRGB_decode = supported_extensions.contains("GL_EXT_texture_sRGB_decode");
        this.GL_EXT_texture_shared_exponent = supported_extensions.contains("GL_EXT_texture_shared_exponent");
        this.GL_EXT_texture_snorm = supported_extensions.contains("GL_EXT_texture_snorm");
        this.GL_EXT_texture_swizzle = supported_extensions.contains("GL_EXT_texture_swizzle");
        this.GL_EXT_timer_query = supported_extensions.contains("GL_EXT_timer_query");
        this.GL_EXT_transform_feedback = supported_extensions.contains("GL_EXT_transform_feedback");
        this.GL_EXT_vertex_array_bgra = supported_extensions.contains("GL_EXT_vertex_array_bgra");
        this.GL_EXT_vertex_attrib_64bit = supported_extensions.contains("GL_EXT_vertex_attrib_64bit");
        this.GL_EXT_vertex_shader = supported_extensions.contains("GL_EXT_vertex_shader");
        this.GL_EXT_vertex_weighting = supported_extensions.contains("GL_EXT_vertex_weighting");
        this.OpenGL11 = supported_extensions.contains("OpenGL11");
        this.OpenGL12 = supported_extensions.contains("OpenGL12");
        this.OpenGL13 = supported_extensions.contains("OpenGL13");
        this.OpenGL14 = supported_extensions.contains("OpenGL14");
        this.OpenGL15 = supported_extensions.contains("OpenGL15");
        this.OpenGL20 = supported_extensions.contains("OpenGL20");
        this.OpenGL21 = supported_extensions.contains("OpenGL21");
        this.OpenGL30 = supported_extensions.contains("OpenGL30");
        this.OpenGL31 = supported_extensions.contains("OpenGL31");
        this.OpenGL32 = supported_extensions.contains("OpenGL32");
        this.OpenGL33 = supported_extensions.contains("OpenGL33");
        this.OpenGL40 = supported_extensions.contains("OpenGL40");
        this.OpenGL41 = supported_extensions.contains("OpenGL41");
        this.OpenGL42 = supported_extensions.contains("OpenGL42");
        this.OpenGL43 = supported_extensions.contains("OpenGL43");
        this.OpenGL44 = supported_extensions.contains("OpenGL44");
        this.OpenGL45 = supported_extensions.contains("OpenGL45");
        this.GL_GREMEDY_frame_terminator = supported_extensions.contains("GL_GREMEDY_frame_terminator");
        this.GL_GREMEDY_string_marker = supported_extensions.contains("GL_GREMEDY_string_marker");
        this.GL_HP_occlusion_test = supported_extensions.contains("GL_HP_occlusion_test");
        this.GL_IBM_rasterpos_clip = supported_extensions.contains("GL_IBM_rasterpos_clip");
        this.GL_INTEL_map_texture = supported_extensions.contains("GL_INTEL_map_texture");
        this.GL_KHR_context_flush_control = supported_extensions.contains("GL_KHR_context_flush_control");
        this.GL_KHR_debug = supported_extensions.contains("GL_KHR_debug");
        this.GL_KHR_robust_buffer_access_behavior = supported_extensions.contains("GL_KHR_robust_buffer_access_behavior");
        this.GL_KHR_robustness = supported_extensions.contains("GL_KHR_robustness");
        this.GL_KHR_texture_compression_astc_ldr = supported_extensions.contains("GL_KHR_texture_compression_astc_ldr");
        this.GL_NVX_gpu_memory_info = supported_extensions.contains("GL_NVX_gpu_memory_info");
        this.GL_NV_bindless_multi_draw_indirect = supported_extensions.contains("GL_NV_bindless_multi_draw_indirect");
        this.GL_NV_bindless_texture = supported_extensions.contains("GL_NV_bindless_texture");
        this.GL_NV_blend_equation_advanced = supported_extensions.contains("GL_NV_blend_equation_advanced");
        this.GL_NV_blend_square = supported_extensions.contains("GL_NV_blend_square");
        this.GL_NV_compute_program5 = supported_extensions.contains("GL_NV_compute_program5");
        this.GL_NV_conditional_render = supported_extensions.contains("GL_NV_conditional_render");
        this.GL_NV_copy_depth_to_color = supported_extensions.contains("GL_NV_copy_depth_to_color");
        this.GL_NV_copy_image = supported_extensions.contains("GL_NV_copy_image");
        this.GL_NV_deep_texture3D = supported_extensions.contains("GL_NV_deep_texture3D");
        this.GL_NV_depth_buffer_float = supported_extensions.contains("GL_NV_depth_buffer_float");
        this.GL_NV_depth_clamp = supported_extensions.contains("GL_NV_depth_clamp");
        this.GL_NV_draw_texture = supported_extensions.contains("GL_NV_draw_texture");
        this.GL_NV_evaluators = supported_extensions.contains("GL_NV_evaluators");
        this.GL_NV_explicit_multisample = supported_extensions.contains("GL_NV_explicit_multisample");
        this.GL_NV_fence = supported_extensions.contains("GL_NV_fence");
        this.GL_NV_float_buffer = supported_extensions.contains("GL_NV_float_buffer");
        this.GL_NV_fog_distance = supported_extensions.contains("GL_NV_fog_distance");
        this.GL_NV_fragment_program = supported_extensions.contains("GL_NV_fragment_program")
            && supported_extensions.contains("GL_NV_program");
        this.GL_NV_fragment_program2 = supported_extensions.contains("GL_NV_fragment_program2");
        this.GL_NV_fragment_program4 = supported_extensions.contains("GL_NV_fragment_program4");
        this.GL_NV_fragment_program_option = supported_extensions.contains("GL_NV_fragment_program_option");
        this.GL_NV_framebuffer_multisample_coverage = supported_extensions.contains("GL_NV_framebuffer_multisample_coverage");
        this.GL_NV_geometry_program4 = supported_extensions.contains("GL_NV_geometry_program4");
        this.GL_NV_geometry_shader4 = supported_extensions.contains("GL_NV_geometry_shader4");
        this.GL_NV_gpu_program4 = supported_extensions.contains("GL_NV_gpu_program4");
        this.GL_NV_gpu_program5 = supported_extensions.contains("GL_NV_gpu_program5");
        this.GL_NV_gpu_program5_mem_extended = supported_extensions.contains("GL_NV_gpu_program5_mem_extended");
        this.GL_NV_gpu_shader5 = supported_extensions.contains("GL_NV_gpu_shader5");
        this.GL_NV_half_float = supported_extensions.contains("GL_NV_half_float");
        this.GL_NV_light_max_exponent = supported_extensions.contains("GL_NV_light_max_exponent");
        this.GL_NV_multisample_coverage = supported_extensions.contains("GL_NV_multisample_coverage");
        this.GL_NV_multisample_filter_hint = supported_extensions.contains("GL_NV_multisample_filter_hint");
        this.GL_NV_occlusion_query = supported_extensions.contains("GL_NV_occlusion_query");
        this.GL_NV_packed_depth_stencil = supported_extensions.contains("GL_NV_packed_depth_stencil");
        this.GL_NV_parameter_buffer_object = supported_extensions.contains("GL_NV_parameter_buffer_object");
        this.GL_NV_parameter_buffer_object2 = supported_extensions.contains("GL_NV_parameter_buffer_object2");
        this.GL_NV_path_rendering = supported_extensions.contains("GL_NV_path_rendering");
        this.GL_NV_pixel_data_range = supported_extensions.contains("GL_NV_pixel_data_range");
        this.GL_NV_point_sprite = supported_extensions.contains("GL_NV_point_sprite");
        this.GL_NV_present_video = supported_extensions.contains("GL_NV_present_video");
        this.GL_NV_primitive_restart = supported_extensions.contains("GL_NV_primitive_restart");
        this.GL_NV_register_combiners = supported_extensions.contains("GL_NV_register_combiners");
        this.GL_NV_register_combiners2 = supported_extensions.contains("GL_NV_register_combiners2");
        this.GL_NV_shader_atomic_counters = supported_extensions.contains("GL_NV_shader_atomic_counters");
        this.GL_NV_shader_atomic_float = supported_extensions.contains("GL_NV_shader_atomic_float");
        this.GL_NV_shader_buffer_load = supported_extensions.contains("GL_NV_shader_buffer_load");
        this.GL_NV_shader_buffer_store = supported_extensions.contains("GL_NV_shader_buffer_store");
        this.GL_NV_shader_storage_buffer_object = supported_extensions.contains("GL_NV_shader_storage_buffer_object");
        this.GL_NV_tessellation_program5 = supported_extensions.contains("GL_NV_tessellation_program5");
        this.GL_NV_texgen_reflection = supported_extensions.contains("GL_NV_texgen_reflection");
        this.GL_NV_texture_barrier = supported_extensions.contains("GL_NV_texture_barrier");
        this.GL_NV_texture_compression_vtc = supported_extensions.contains("GL_NV_texture_compression_vtc");
        this.GL_NV_texture_env_combine4 = supported_extensions.contains("GL_NV_texture_env_combine4");
        this.GL_NV_texture_expand_normal = supported_extensions.contains("GL_NV_texture_expand_normal");
        this.GL_NV_texture_multisample = supported_extensions.contains("GL_NV_texture_multisample");
        this.GL_NV_texture_rectangle = supported_extensions.contains("GL_NV_texture_rectangle");
        this.GL_NV_texture_shader = supported_extensions.contains("GL_NV_texture_shader");
        this.GL_NV_texture_shader2 = supported_extensions.contains("GL_NV_texture_shader2");
        this.GL_NV_texture_shader3 = supported_extensions.contains("GL_NV_texture_shader3");
        this.GL_NV_transform_feedback = supported_extensions.contains("GL_NV_transform_feedback");
        this.GL_NV_transform_feedback2 = supported_extensions.contains("GL_NV_transform_feedback2");
        this.GL_NV_vertex_array_range = supported_extensions.contains("GL_NV_vertex_array_range");
        this.GL_NV_vertex_array_range2 = supported_extensions.contains("GL_NV_vertex_array_range2");
        this.GL_NV_vertex_attrib_integer_64bit = supported_extensions.contains("GL_NV_vertex_attrib_integer_64bit");
        this.GL_NV_vertex_buffer_unified_memory = supported_extensions.contains("GL_NV_vertex_buffer_unified_memory");
        this.GL_NV_vertex_program = supported_extensions.contains("GL_NV_vertex_program") && supported_extensions.contains("GL_NV_program");
        this.GL_NV_vertex_program1_1 = supported_extensions.contains("GL_NV_vertex_program1_1");
        this.GL_NV_vertex_program2 = supported_extensions.contains("GL_NV_vertex_program2");
        this.GL_NV_vertex_program2_option = supported_extensions.contains("GL_NV_vertex_program2_option");
        this.GL_NV_vertex_program3 = supported_extensions.contains("GL_NV_vertex_program3");
        this.GL_NV_vertex_program4 = supported_extensions.contains("GL_NV_vertex_program4");
        this.GL_NV_video_capture = supported_extensions.contains("GL_NV_video_capture");
        this.GL_SGIS_generate_mipmap = supported_extensions.contains("GL_SGIS_generate_mipmap");
        this.GL_SGIS_texture_lod = supported_extensions.contains("GL_SGIS_texture_lod");
        this.GL_SUN_slice_accum = supported_extensions.contains("GL_SUN_slice_accum");*/
        //FIXME?
        //tracker.initGLFW();




        try{
            GLCapabilities capabilities = GL.getCapabilities();
            Field[] thereFields = GLCapabilities.class.getDeclaredFields();
            Field[] ourFields = ContextCapabilities.class.getDeclaredFields();

            Arrays.stream(thereFields).forEach(field->field.setAccessible(true));
            Arrays.stream(ourFields).forEach(field->field.setAccessible(true));

            for(Field ourField : ourFields){
                int mods = ourField.getModifiers();
                if(ourField.getType() == Boolean.TYPE &&
                        !Modifier.isStatic(mods) &&
                        Modifier.isPublic(mods) &&
                        Modifier.isFinal(mods)){

                    String name = ourField.getName();
                    Optional<Field> optional = Arrays.stream(thereFields).filter(field -> name.equals(field.getName())).findAny();
                    if(optional.isPresent()){
                        Field thereField = optional.get();
                        ourField.setBoolean(this, thereField.getBoolean(capabilities));
                    }
                }
            }
        }catch(ReflectiveOperationException e){
            throw new RuntimeException(e);
        }
    }
}