package com.yollo.dmlreforged.common.blocks.entity;

import com.yollo.dmlreforged.common.energy.DeepEnergyStorage;
import com.yollo.dmlreforged.common.items.init.BlockEntityInit;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public class BlockEntitySimulationChamber extends InventoryBlockEntity /*implements BlockEntityTicker<BlockEntitySimulationChamber>*/{

	public int percentDone = 0;
	public int currentEnergy = 300000;
	public DeepEnergyStorage energyStorage;
	private boolean isCrafting = false;
	private boolean byproductSuccess = false;
	private LazyOptional<DeepEnergyStorage> energy;

	public BlockEntitySimulationChamber(BlockPos pWorldPosition, BlockState pBlockState) {
		super(BlockEntityInit.ENTITY_SIMULATION_CHAMBER.get(), pWorldPosition, pBlockState, 4);
		this.energyStorage = createEnergyStorage();
		this.energy = LazyOptional.of(() -> this.energyStorage);
	}
	
	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		this.energy.invalidate();
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("energy", currentEnergy);
		tag.putInt("simulationProgress", percentDone);
		tag.putBoolean("isCrafting", isCrafting);
		tag.putBoolean("craftSuccess", byproductSuccess);
		// tag.put("simulationText", getNBTForSimulationText());
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		// setSimulationTextFromNBT(pTag.getCompound("simulationText"));
		currentEnergy = pTag.contains("energy") ? pTag.getInt("energy") : 300000;
		percentDone = pTag.contains("simulationProgress") ? pTag.getInt("simulationProgress") : 0;
		isCrafting = pTag.contains("isCrafting") ? pTag.getBoolean("isCrafting") : isCrafting;
		byproductSuccess = pTag.contains("craftSuccess") ? pTag.getBoolean("craftSuccess") : isCrafting;
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == CapabilityEnergy.ENERGY ? this.energy.cast() : super.getCapability(cap, side);
	}
	
	private DeepEnergyStorage createEnergyStorage() {
		return new DeepEnergyStorage(this, 2000000, 25600, 0, currentEnergy);
	}
	
    public boolean isCrafting() {
        return isCrafting;
    }

	public int getProgress() {
		return percentDone;
	}

	public int getEnergy() {
		return currentEnergy;
	}

}
