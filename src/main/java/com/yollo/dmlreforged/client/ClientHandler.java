package com.yollo.dmlreforged.client;

import org.apache.commons.lang3.function.TriFunction;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.client.screen.DeepLearnerScreen;
import com.yollo.dmlreforged.client.screen.SimulationChamberScreen;
import com.yollo.dmlreforged.common.blocks.entity.BlockEntitySimulationChamber;
import com.yollo.dmlreforged.common.items.init.BlockInit;
import com.yollo.dmlreforged.common.items.init.ContainerInit;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(modid = DeepMobLearning.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT) 
public class ClientHandler {
	@SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) 
	{
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerInit.DEEP_LEARNER.get(), DeepLearnerScreen::new);
        });
        
        event.enqueueWork(() -> {
            MenuScreens.register(ContainerInit.SIMULATION_CHAMBER.get(), SimulationChamberScreen::new);
        });

        ItemBlockRenderTypes.setRenderLayer(BlockInit.SIMULATION_CHAMBER.get(), RenderType.solid());
    }
	
	public static <T extends AbstractContainerMenu> MenuType<T> createMenuType(TriFunction<Integer, Inventory, FriendlyByteBuf, T> constructor) {
		return IForgeMenuType.create(constructor::apply);
	}
}

