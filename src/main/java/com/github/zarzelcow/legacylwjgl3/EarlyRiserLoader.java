package com.github.zarzelcow.legacylwjgl3;

import net.fabricmc.loader.impl.launch.FabricLauncherBase;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
 * Attempts to fix issues from LWJGL 2 and LWJGL 3 being in the classpath at the same time
 * and runs [EarlyRiser] which uses a very hacky way to modify LWJGL 3 classes
 *
 * Uses a MixinConfigPlugin to transform classes before they get loaded
 * Idea taken from https://github.com/Chocohead/Fabric-ASM 🥰
 */
public class EarlyRiserLoader implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
        // Add paulscode to the knot classpath (specifically the librarylwjglopenal as that load lwjgl classes).
        // by default, paulscode is a part of the system class loader which only has LWJGL 2 in its classpath
        // adding it to the knot classpath means paulscode will instead load classes with the knot class loader
        // meaning it will load LWJGL 3 classes from the knot classpath instead of LWJGL 2 from the system classpath
        //
        // This was such a pain to figure out and caused me so much trouble
        getPaulscodePath().ifPresent(FabricLauncherBase.getLauncher()::addToClassPath);
        // taken from https://github.com/Chocohead/Fabric-ASM
        new EarlyRiser().run();
    }

    public static Optional<Path> getPaulscodePath() {
        for (Path path : FabricLauncherBase.getLauncher().getClassPath()) {
            if (path.toString().contains("librarylwjglopenal")) {
                return Optional.of(path);
            }
        }
        return Optional.empty();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return Collections.emptyList();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }
}
