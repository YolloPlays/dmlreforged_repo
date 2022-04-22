package com.yollo.dmlreforged.common.items.init;

import com.google.common.base.Supplier;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.blocks.entity.BlockEntitySimulationChamber;
import com.yollo.dmlreforged.common.util.container.DeepLearnerContainer;

import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, DeepMobLearning.MOD_ID);

	 public static final RegistryObject<BlockEntityType<BlockEntitySimulationChamber>> ENTITY_SIMULATION_CHAMBER = BLOCK_ENTITIES
	            .register("simulation_chamber", () -> BlockEntityType.Builder
	                    .of(BlockEntitySimulationChamber::new, BlockInit.SIMULATION_CHAMBER.get()).build(null));
}
