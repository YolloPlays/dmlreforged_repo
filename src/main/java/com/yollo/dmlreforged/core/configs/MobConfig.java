package com.yollo.dmlreforged.core.configs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class MobConfig {
	
    private static HashMap<String, ForgeConfigSpec.ConfigValue<List<? extends String>>> PRISTINELOOT = new HashMap<>();
    private static HashMap<String, ForgeConfigSpec.ConfigValue<List<? extends String>>> ACCEPTEDMOBS = new HashMap<>();
	
	public static void registerCommonConfig(ForgeConfigSpec.Builder COMMON_BUILDER) {
		
			COMMON_BUILDER.comment("Add custom mob to count for data learning").push("mob_registry");
            COMMON_BUILDER.push("mobs");
            ACCEPTEDMOBS.put("blaze", COMMON_BUILDER.defineList("blaze", Arrays.asList(MOBS.BLAZE), o -> o instanceof String));
            ACCEPTEDMOBS.put("creeper", COMMON_BUILDER.defineList("creeper", Arrays.asList(MOBS.CREEPER), o -> o instanceof String));
            ACCEPTEDMOBS.put("dragon", COMMON_BUILDER.defineList("dragon", Arrays.asList(MOBS.DRAGON), o -> o instanceof String));
            ACCEPTEDMOBS.put("enderman", COMMON_BUILDER.defineList("enderman", Arrays.asList(MOBS.ENDERMAN), o -> o instanceof String));
            ACCEPTEDMOBS.put("ghast", COMMON_BUILDER.defineList("ghast", Arrays.asList(MOBS.GHAST), o -> o instanceof String));
            ACCEPTEDMOBS.put("skeleton", COMMON_BUILDER.defineList("skeleton", Arrays.asList(MOBS.SKELETON), o -> o instanceof String));
            ACCEPTEDMOBS.put("slime", COMMON_BUILDER.defineList("slime", Arrays.asList(MOBS.SLIME), o -> o instanceof String));
            ACCEPTEDMOBS.put("spider", COMMON_BUILDER.defineList("spider", Arrays.asList(MOBS.SPIDER), o -> o instanceof String));
            ACCEPTEDMOBS.put("witch", COMMON_BUILDER.defineList("witch", Arrays.asList(MOBS.WITCH), o -> o instanceof String));
            ACCEPTEDMOBS.put("wither", COMMON_BUILDER.defineList("wither", Arrays.asList(MOBS.WITHER), o -> o instanceof String));
            ACCEPTEDMOBS.put("zombie", COMMON_BUILDER.defineList("zombie", Arrays.asList(MOBS.ZOMBIE), o -> o instanceof String));
            ACCEPTEDMOBS.put("shulker", COMMON_BUILDER.defineList("shulker", Arrays.asList(MOBS.SHULKER), o -> o instanceof String));
            ACCEPTEDMOBS.put("guardian", COMMON_BUILDER.defineList("guardian", Arrays.asList(MOBS.GUARDIAN), o -> o instanceof String));
            ACCEPTEDMOBS.put("witherskeleton", COMMON_BUILDER.defineList("witherskeleton", Arrays.asList(MOBS.WITHERSKELETON), o -> o instanceof String));
            COMMON_BUILDER.pop();
            COMMON_BUILDER.comment("Add generated loot from fabricating pristine matter").push("loot_assign");
            PRISTINELOOT.put("blaze", COMMON_BUILDER.defineList("blaze", Arrays.asList(LOOT.BLAZE), o -> o instanceof String));
            PRISTINELOOT.put("creeper", COMMON_BUILDER.defineList("creeper", Arrays.asList(LOOT.CREEPER), o -> o instanceof String));
            PRISTINELOOT.put("dragon", COMMON_BUILDER.defineList("dragon", Arrays.asList(LOOT.DRAGON), o -> o instanceof String));
            PRISTINELOOT.put("enderman", COMMON_BUILDER.defineList("enderman", Arrays.asList(LOOT.ENDERMAN), o -> o instanceof String));
            PRISTINELOOT.put("ghast", COMMON_BUILDER.defineList("ghast", Arrays.asList(LOOT.GHAST), o -> o instanceof String));
            PRISTINELOOT.put("skeleton", COMMON_BUILDER.defineList("skeleton", Arrays.asList(LOOT.SKELETON), o -> o instanceof String));
            PRISTINELOOT.put("slime", COMMON_BUILDER.defineList("slime", Arrays.asList(LOOT.SLIME), o -> o instanceof String));
            PRISTINELOOT.put("spider", COMMON_BUILDER.defineList("spider", Arrays.asList(LOOT.SPIDER), o -> o instanceof String));
            PRISTINELOOT.put("witch", COMMON_BUILDER.defineList("witch", Arrays.asList(LOOT.WITCH), o -> o instanceof String));
            PRISTINELOOT.put("wither", COMMON_BUILDER.defineList("wither", Arrays.asList(LOOT.WITHER), o -> o instanceof String));
            PRISTINELOOT.put("zombie", COMMON_BUILDER.defineList("zombie", Arrays.asList(LOOT.ZOMBIE), o -> o instanceof String));
            PRISTINELOOT.put("shulker", COMMON_BUILDER.defineList("shulker", Arrays.asList(LOOT.SHULKER), o -> o instanceof String));
            PRISTINELOOT.put("guardian", COMMON_BUILDER.defineList("guardian", Arrays.asList(LOOT.GUARDIAN), o -> o instanceof String));
            PRISTINELOOT.put("witherskeleton", COMMON_BUILDER.defineList("witherskeleton", Arrays.asList(LOOT.WITHERSKELETON), o -> o instanceof String));
            COMMON_BUILDER.pop(2);
		
	}
	     
        public static final class MOBS {
            public static final String[] CREEPER = {
                    "minecraft:creeper"
            };

            public static final String[] BLAZE = {
                    "minecraft:blaze"
            };

            public static final String[] DRAGON = {
                    "minecraft:ender_dragon"
            };

            public static final String[] ENDERMAN = {
                    "minecraft:enderman",
                    "deepmoblearning:trial_enderman"
            };

            public static final String[] GHAST = {
                    "minecraft:ghast"
            };

            public static final String[] SKELETON = {
                    "minecraft:stray",
                    "minecraft:skeleton",
                    "twilightforest:skeleton_druid"
            };

            public static final String[] SLIME = {
                    "minecraft:slime",
                    "minecraft:magma_cube",
                    "deepmoblearning:trial_slime",
            };

            public static final String[] SPIDER = {
                    "minecraft:spider",
                    "minecraft:cave_spider",
                    "twilightforest:hedge_spider",
                    "twilightforest:king_spider",
                    "deepmoblearning:trial_spider",
                    "deepmoblearning:trial_cave_spider",
            };

            public static final String[] WITCH = {
                    "minecraft:witch"
            };

            public static final String[] WITHERSKELETON = {
                    "minecraft:wither_skeleton"
            };

            public static final String[] WITHER = {
                    "minecraft:wither"
            };

            public static final String[] ZOMBIE = {
                    "minecraft:husk",
                    "minecraft:zombie",
                    "minecraft:zombie_villager",
                    "minecraft:zombie_pigman",
            };

            public static final String[] SHULKER = {
                    "minecraft:shulker"
            };

            public static final String[] GUARDIAN = {
                    "minecraft:elder_guardian",
                    "minecraft:guardian",
            };
        }
        

        public static final class LOOT {
            public static final String[] CREEPER = {
                "minecraft:gunpowder,64",
                "minecraft:skull,6"
            };

            public static final String[] BLAZE = {
                "minecraft:blaze_rod,22",
                "thermalfoundation:material,32",
            };

            public static final String[] DRAGON = {
                "minecraft:dragon_breath,32",
                "minecraft:dragon_egg,1",
                "draconicevolution:dragon_heart,1",
                "draconicevolution:draconium_dust,64"
            };

            public static final String[] ENDERMAN = {
                "minecraft:ender_pearl,6",
                "minecraft:end_crystal,1",
                "enderio:block_enderman_skull,2"
            };

            public static final String[] GHAST = {
                "minecraft:ghast_tear,8"
            };

            public static final String[] SKELETON = {
                "minecraft:bone,64",
                "minecraft:arrow,64",
                "minecraft:skull,6",
            };

            public static final String[] SLIME = {
                "minecraft:slime_ball,32",
            };

            public static final String[] SPIDER = {
                "minecraft:spider_eye,16",
                "minecraft:string,64",
                "minecraft:web,8",
            };

            public static final String[] WITCH = {
                "minecraft:redstone,32",
                "minecraft:glowstone_dust,32",
                "minecraft:sugar,64",
            };

            public static final String[] WITHERSKELETON = {
                "minecraft:skull,18",
                "minecraft:coal,64"
            };

            public static final String[] WITHER = {
                "minecraft:nether_star,3",
            };

            public static final String[] ZOMBIE = {
                "minecraft:rotten_flesh,64",
                "minecraft:iron_ingot,16",
                "minecraft:carrot,32",
                "minecraft:potato,32"
            };

            public static final String[] SHULKER = {
                "minecraft:shulker_shell,18",
                "minecraft:diamond,2"
            };

            public static final String[] GUARDIAN = {
                "minecraft:prismarine_shard,32",
                "minecraft:prismarine_crystals,32",
                "minecraft:fish,64"
            };
        }
        
        public static final List<? extends String> getMobs(String name) {
    		return ACCEPTEDMOBS.get(name).get();
    	}

		public static List<? extends String> getPristineLoot(String key) {
			return PRISTINELOOT.get(key).get();
		}
}
