package com.yollo.dmlreforged.common.energy;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.energy.EnergyStorage;

public class DeepEnergyStorage extends EnergyStorage{
	private final BlockEntity blockEntity;

	public DeepEnergyStorage(BlockEntity be, int capacity, int maxReceive, int maxExtract, int energy) {
		super(capacity, maxReceive, maxExtract, energy);
		this.blockEntity = be;
	}
	
    public void voidEnergy(int energyVoiding) {
        this.energy = this.energy - energyVoiding;
    }
    
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        this.blockEntity.setChanged();
        return super.receiveEnergy(maxReceive, simulate);
    }
}
