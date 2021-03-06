package com.yollo.dmlreforged.core.init;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.client.ClientHandler;
import com.yollo.dmlreforged.core.container.DeepLearnerContainer;
import com.yollo.dmlreforged.core.container.ExtractionChamberContainer;
import com.yollo.dmlreforged.core.container.SimulationChamberContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContainerInit {
	
	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, DeepMobLearning.MOD_ID);
	
	//Container
	public static final RegistryObject<MenuType<DeepLearnerContainer>> DEEP_LEARNER = CONTAINERS.register("deep_learner", () -> ClientHandler.createMenuType(DeepLearnerContainer::fromNetwork));
	public static final RegistryObject<MenuType<SimulationChamberContainer>> SIMULATION_CHAMBER = CONTAINERS.register("simulation_chamber", () -> new MenuType<>(SimulationChamberContainer::new));
	public static final RegistryObject<MenuType<ExtractionChamberContainer>> EXTRACTION_CHAMBER = CONTAINERS.register("extraction_chamber", () -> ClientHandler.createMenuType(ExtractionChamberContainer::new));
	
	/*public static <T extends MenuType<DeepLearnerContainer>> RegistryObject<T> register(final String name, final Supplier<T> container){
		return CONTAINERS.register(name, container);
	}*/
}