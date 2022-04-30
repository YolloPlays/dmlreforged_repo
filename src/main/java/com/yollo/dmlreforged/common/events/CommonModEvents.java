package com.yollo.dmlreforged.common.events;

import javax.annotation.Nonnull;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.loot.GlitchFragmentModifier;
import com.yollo.dmlreforged.core.init.PacketHandler;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(modid = DeepMobLearning.MOD_ID, bus = Bus.MOD)
public class CommonModEvents {
	
	@SubscribeEvent
	public static void commonmSetup(FMLCommonSetupEvent evt) {
		evt.enqueueWork(PacketHandler::init);
	}
	
	@SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(
                new GlitchFragmentModifier.Serializer().setRegistryName
                        (new ResourceLocation(DeepMobLearning.MOD_ID,"glitch_fragment_all_entities")));
	}
}
