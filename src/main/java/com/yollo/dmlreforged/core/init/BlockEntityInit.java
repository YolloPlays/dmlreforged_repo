package com.yollo.dmlreforged.core.init;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.blocks.entity.BlockEntityExtractionChamber;
import com.yollo.dmlreforged.common.blocks.entity.BlockEntitySimulationChamber;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
	
	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, DeepMobLearning.MOD_ID);

	 public static final RegistryObject<BlockEntityType<BlockEntitySimulationChamber>> ENTITY_SIMULATION_CHAMBER = BLOCK_ENTITIES
	            .register("simulation_chamber", () -> BlockEntityType.Builder
	                    .of(BlockEntitySimulationChamber::new, BlockInit.SIMULATION_CHAMBER.get()).build(null));
	 
	 public static final RegistryObject<BlockEntityType<BlockEntityExtractionChamber>> ENTITY_EXTRACTION_CHAMBER = BLOCK_ENTITIES
	            .register("extraction_chamber", () -> BlockEntityType.Builder
	                    .of(BlockEntityExtractionChamber::new, BlockInit.EXTRACTION_CHAMBER.get()).build(null));
}
