package com.github.zarzelcow.legacylwjgl3;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

/*
 * Used only to load [EarlyRiser] as soon as possible, so it can define classes before anything else.
 * Idea taken from https://github.com/Chocohead/Fabric-ASM 🥰
 */
public class EarlyRiserLoader implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        // no op since we don't need to load anything
    }

    @Override
    public String getRefMapperConfig() {
        return null; // use inherit config
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
        // dont need to do anything
    }

    @Override
    public List<String> getMixins() {
        // taken from https://github.com/Chocohead/Fabric-ASM
        new EarlyRiser().run();
        return null; // no additional mixins needed
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // dont need to do anything
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        // dont need to do anything
    }
}
