package com.yollo.dmlreforged.common.items.init;

import com.google.common.base.Supplier;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.blocks.BlockExtractionChamber;
import com.yollo.dmlreforged.common.blocks.BlockSimulationChamber;
import com.yollo.dmlreforged.common.items.ItemGlitchSword;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {
	
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, DeepMobLearning.MOD_ID);
	
	public static final RegistryObject<Block> MACHINE_CASING = register("machine_casing", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
	public static final RegistryObject<Block> INFUSED_INGOT_BLOCK = register("infused_ingot_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(4f, 10.0f)));
	public static final RegistryObject<Block> SIMULATION_CHAMBER = register("simulation_chamber", () -> new BlockSimulationChamber());
	public static final RegistryObject<Block> EXTRACTION_CHAMBER = register("extraction_chamber", () -> new BlockExtractionChamber());
	
	public static <T extends Block> RegistryObject<T> register(final String name, final Supplier<T> block){
		return BLOCKS.register(name, block);
	}

}
