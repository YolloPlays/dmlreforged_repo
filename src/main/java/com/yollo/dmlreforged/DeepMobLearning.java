package com.yollo.dmlreforged;

import com.yollo.dmlreforged.common.loot.GlitchFragmentModifier;
import com.yollo.dmlreforged.core.configs.Config;
import com.yollo.dmlreforged.core.init.BlockEntityInit;
import com.yollo.dmlreforged.core.init.BlockInit;
import com.yollo.dmlreforged.core.init.ContainerInit;
import com.yollo.dmlreforged.core.init.ItemInit;

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

    public static final CreativeModeTab Deep_Mob_Learning_TAB = new CreativeModeTab(MOD_ID) {	
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {
			return new ItemStack(ItemInit.DEEP_LEARNER.get());
		}
	};
	
	public DeepMobLearning() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		Config.register();
		
		BlockInit.BLOCKS.register(bus);
		ContainerInit.CONTAINERS.register(bus);
		ItemInit.ITEMS.register(bus);
		BlockEntityInit.BLOCK_ENTITIES.register(bus);
		GlitchFragmentModifier.GLM.register(bus);
		

		MinecraftForge.EVENT_BUS.register(this);
	}
}
