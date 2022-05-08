package com.yollo.dmlreforged.core.configs;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class Config {
	
	public static void register() {
		registerClientConfigs();
		registerServerConfigs();
		registerCommonConfigs();
	}

	private static void registerServerConfigs() {
		ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
		BalanceConfigs.registerServerConfig(SERVER_BUILDER);
		EnergyCostConfig.registerServerConfig(SERVER_BUILDER);
		LivingExpConfig.registerServerConfig(SERVER_BUILDER);
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build());
	}
	
	private static void registerCommonConfigs() {
		ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
		MobConfig.registerCommonConfig(COMMON_BUILDER);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_BUILDER.build());
	}
	
	private static void registerClientConfigs() {
		ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
		ClientConfig.registerClientConfig(CLIENT_BUILDER);
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build());
	}
}
