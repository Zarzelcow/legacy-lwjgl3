package com.github.zarzelcow.legacylwjgl3;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LegacyLWJGL3 implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public void onInitialize() {
		LOGGER.info("This is definitely a 100% legit legacy LWJGL2 mod!");
	}
}
