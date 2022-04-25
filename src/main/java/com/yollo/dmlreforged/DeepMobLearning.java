package com.yollo.dmlreforged;

import com.yollo.dmlreforged.common.items.init.BlockEntityInit;
import com.yollo.dmlreforged.common.items.init.BlockInit;
import com.yollo.dmlreforged.common.items.init.ContainerInit;
import com.yollo.dmlreforged.common.items.init.ItemInit;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("dmlreforged")
public class DeepMobLearning {
	
	public static final String MOD_ID = "dmlreforged";
    // Minecraft logic
    public static final int TICKS_TO_SECOND = 20;
    // Internal inventory sizes
    public static final int DEEP_LEARNER_INTERNAL_SLOTS_SIZE = 4;
    // Data model max tier
    public static final int DATA_MODEL_MAXIMUM_TIER = 4;
    // Config restraints
    public static final int MAX_DATA_MODEL_COST = 6666;
    //Config pre-Constants
    public static final int killMultiplierTier0 = 1;
    public static final int killMultiplierTier1 = 4;
    public static final int killMultiplierTier2 = 10;
    public static final int killMultiplierTier3 = 18;
    public static final int killsToTier1 = 6;
    public static final int killsToTier2 = 12;
    public static final int killsToTier3 = 30;
    public static final int killsToTier4 = 50;
    public static final int tier1=5;
    public static final int tier2=11;
    public static final int tier3=24;
    public static final int tier4=42;
    public static int rfCostExtractionChamber = 256;
    public static final boolean isSootedRedstoneCraftingEnabled = true;
    

    public static final int getExp(String type) {
    	if(type == "living_matter_extraterrestrial") {
    		return 20;
    	}
    	else if(type == "living_matter_hellish") {
    		return 14;
    	}
    	else {
    		return 10;
    	}   	
    }
    
    public static final int getCost(String type) {
    	if(type == "blaze") {
    		return 256;
    	}
    	else if(type == "creeper") {
    		return 80;
    	}
    	else if(type == "dragon") {
    		return 4096;
    	}
    	else if(type == "enderman") {
    		return 512;
    	}
    	else if(type == "ghast") {
    		return 372;
    	}
    	else if(type == "guardian") {
    		return 340;
    	}
    	else if(type == "shulker") {
    		return 512;
    	}
    	else if(type == "skeleton") {
    		return 80;
    	}
    	else if(type == "slime") {
    		return 150;
    	}
    	else if(type == "spider") {
    		return 80;
    	}
    	else if(type == "witch") {
    		return 120;
    	}
    	else if(type == "wither") {
    		return 2048;
    	}
    	else if(type == "witherskeleton") {
    		return 880;
    	}
    	else {
    		return 80;
    	}
    }
	
	public static final CreativeModeTab Deep_Mob_Learning_TAB = new CreativeModeTab(MOD_ID) {	
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.DM_BLANK.get());
		}
	};
	
	public DeepMobLearning() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		BlockInit.BLOCKS.register(bus);
		ContainerInit.CONTAINERS.register(bus);
		ItemInit.ITEMS.register(bus);
		BlockEntityInit.BLOCK_ENTITIES.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
	}
	
	public static final String[] getMobs(String name) {
		if(name == "creeper") {
			return MOBS.CREEPER;
		}
		else if(name == "blaze") {
			return MOBS.BLAZE;
		}		
		else if(name == "dragon") {
			return MOBS.DRAGON;
		}
		else if(name == "enderman") {
			return MOBS.ENDERMAN;
		}
		else if(name == "ghast") {
			return MOBS.GHAST;
		}
		else if(name == "skeleton") {
			return MOBS.SKELETON;
		}
		else if(name == "slime") {
			return MOBS.SLIME;
		}
		else if(name == "spider") {
			return MOBS.SPIDER;
		}
		else if(name == "witch") {
			return MOBS.WITCH;
		}
		else if(name == "wither") {
			return MOBS.WITHER;
		}
		else if(name == "witherskeleton") {
			return MOBS.WITHERSKELETON;
		}
		else if(name == "wither") {
			return MOBS.WITHER;
		}
		else if(name == "shulker") {
			return MOBS.SHULKER;
		}
		else if(name == "guardian") {
			return MOBS.GUARDIAN;
		}
		else {
			return MOBS.ZOMBIE;
		}
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
        };

        public static final String[] GHAST = {
                "minecraft:ghast"
        };

        public static final String[] SKELETON = {
                "minecraft:stray",
                "minecraft:skeleton",
        };

        public static final String[] SLIME = {
                "minecraft:slime",
                "minecraft:magma_cube",
        };

        public static final String[] SPIDER = {
                "minecraft:spider",
                "minecraft:cave_spider",
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
	
	public static final String[] getPristineLoot(String name) {
		if(name == "creeper") {
			return LOOT.CREEPER;
		}
		else if(name == "blaze") {
			return LOOT.BLAZE;
		}		
		else if(name == "dragon") {
			return LOOT.DRAGON;
		}
		else if(name == "enderman") {
			return LOOT.ENDERMAN;
		}
		else if(name == "ghast") {
			return LOOT.GHAST;
		}
		else if(name == "skeleton") {
			return LOOT.SKELETON;
		}
		else if(name == "slime") {
			return LOOT.SLIME;
		}
		else if(name == "spider") {
			return LOOT.SPIDER;
		}
		else if(name == "witch") {
			return LOOT.WITCH;
		}
		else if(name == "wither") {
			return LOOT.WITHER;
		}
		else if(name == "witherskeleton") {
			return LOOT.WITHERSKELETON;
		}
		else if(name == "wither") {
			return LOOT.WITHER;
		}
		else if(name == "shulker") {
			return LOOT.SHULKER;
		}
		else if(name == "guardian") {
			return LOOT.GUARDIAN;
		}
		else {
			return LOOT.ZOMBIE;
		}
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

	        public static final String[] THERMALELEMENTAL = {
	            "thermalfoundation:material,16",
	            "thermalfoundation:material,16",
	            "minecraft:snowball,16",
	            "thermalfoundation:material,8",
	            "thermalfoundation:material,8",
	            "thermalfoundation:material,8"
	        };

	        public static final String[] TINKERSLIME = {
	            "tconstruct:edible,18",
	            "tconstruct:edible,18",
	            "tconstruct:edible,18",
	            "tconstruct:slime_sapling,4",
	            "tconstruct:slime_sapling,4",
	            "tconstruct:slime_sapling,4",
	        };

	        public static final String[] TWILIGHTFOREST = {
	            "twilightforest:naga_scale,16",
	            "twilightforest:charm_of_life_1,2",
	            "twilightforest:charm_of_keeping_1,2",
	            "minecraft:paper,64",
	            "minecraft:book,32",
	        };

	        public static final String[] TWILIGHTSWAMP = {
	            "twilightforest:steeleaf_ingot,16",
	            "twilightforest:ironwood_raw,8",
	            "twilightforest:fiery_ingot,5",
	            "twilightforest:hydra_chop,16",
	            "minecraft:gold_ingot,22",
	            "minecraft:red_mushroom,32",
	            "minecraft:slime_ball,16"
	        };

	        public static final String[] TWILIGHTDARKWOOD = {
	            "twilightforest:armor_shard_cluster,5",
	            "twilightforest:carminite,16",
	            "minecraft:diamond_ore,3",
	            "minecraft:emerald_ore,2",
	            "minecraft:fish,32"
	        };

	        public static final String[] TWILIGHTGLACIER = {
	            "twilightforest:arctic_fur,16",
	            "twilightforest:alpha_fur,8",
	            "minecraft:packed_ice,16",
	            "minecraft:feather,32",
	            "twilightforest:charm_of_life_2,1",
	            "twilightforest:charm_of_keeping_2,1",
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

}
