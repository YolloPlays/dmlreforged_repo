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

}
