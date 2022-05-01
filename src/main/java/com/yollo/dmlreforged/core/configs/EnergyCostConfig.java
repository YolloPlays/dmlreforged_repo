package com.yollo.dmlreforged.core.configs;

import net.minecraftforge.common.ForgeConfigSpec;

public class EnergyCostConfig{
	public static ForgeConfigSpec.IntValue BLAZE; //256
	public static ForgeConfigSpec.IntValue CREEPER; //80
	public static ForgeConfigSpec.IntValue DRAGON; //4096
	public static ForgeConfigSpec.IntValue ENDERMAN; //512
	public static ForgeConfigSpec.IntValue GHAST; //372
	public static ForgeConfigSpec.IntValue GUARDIAN; //340
	public static ForgeConfigSpec.IntValue SHULKER; //512
	public static ForgeConfigSpec.IntValue SKELETON; //80
	public static ForgeConfigSpec.IntValue SLIME; //150
	public static ForgeConfigSpec.IntValue SPIDER; //80
	public static ForgeConfigSpec.IntValue WITCH; //120
	public static ForgeConfigSpec.IntValue WITHER; //2048
	public static ForgeConfigSpec.IntValue WITHERSKELETON; //880
    public static ForgeConfigSpec.IntValue FECOSTEXTRACTIONCHAMBER; //256
	
	
	public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
		SERVER_BUILDER.comment("Data Model cost (FE/t)").push("data_model");
		
		BLAZE = SERVER_BUILDER.defineInRange("blazeCost", 256, 1, 6666);
		CREEPER = SERVER_BUILDER.defineInRange("creeperCost", 80, 1, 6666);
		DRAGON = SERVER_BUILDER.defineInRange("dragonCost", 4096, 1, 6666);
		ENDERMAN = SERVER_BUILDER.defineInRange("endermanCost", 512, 1, 6666);
		GHAST = SERVER_BUILDER.defineInRange("ghastCost", 372, 1, 6666);
		GUARDIAN = SERVER_BUILDER.defineInRange("guardianCost", 340, 1, 6666);
		SHULKER = SERVER_BUILDER.defineInRange("shulkerCost", 512, 1, 6666);
		SKELETON = SERVER_BUILDER.defineInRange("skeletonCost", 80, 1, 6666);
		SLIME = SERVER_BUILDER.defineInRange("slimeCost", 150, 1, 6666);
		SPIDER = SERVER_BUILDER.defineInRange("spiderCost", 80, 1, 6666);
		WITCH = SERVER_BUILDER.defineInRange("witchCost", 120, 1, 6666);
		WITHER = SERVER_BUILDER.defineInRange("witherCost", 2048, 1, 6666);
		WITHERSKELETON = SERVER_BUILDER.defineInRange("witherSkeletonCost", 880, 1, 6666);
		FECOSTEXTRACTIONCHAMBER = SERVER_BUILDER.comment("Balance the energy cost of the Loot Fabricator (FE/t)")
				.defineInRange("lootFabricatorCost", 256, 1, 18000);
		
		
		SERVER_BUILDER.pop();
	}
	
	public static final int getCost(String type) {
		if(type == "blaze") {
			return BLAZE.get();
		}
		else if(type == "creeper") {
			return CREEPER.get();
		}
		else if(type == "dragon") {
			return DRAGON.get();
		}
		else if(type == "enderman") {
			return ENDERMAN.get();
		}
		else if(type == "ghast") {
			return GHAST.get();
		}
		else if(type == "guardian") {
			return GUARDIAN.get();
		}
		else if(type == "shulker") {
			return SHULKER.get();
		}
		else if(type == "skeleton") {
			return SKELETON.get();
		}
		else if(type == "slime") {
			return SLIME.get();
		}
		else if(type == "spider") {
			return SPIDER.get();
		}
		else if(type == "witch") {
			return WITCH.get();
		}
		else if(type == "wither") {
			return WITHER.get();
		}
		else if(type == "witherskeleton") {
			return WITHERSKELETON.get();
		}
		else {
			return 80;
		}
	}

}