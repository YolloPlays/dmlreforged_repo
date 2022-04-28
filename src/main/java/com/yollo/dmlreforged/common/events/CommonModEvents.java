package com.yollo.dmlreforged.common.events;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.init.PacketHandler;

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

}
