package com.yollo.dmlreforged.common.util.container.sync;

import com.yollo.dmlreforged.common.blocks.entity.BlockEntityExtractionChamber;
import net.minecraft.world.inventory.SimpleContainerData;

public class ExtractionChamberContainerData extends SimpleContainerData{
	private final BlockEntityExtractionChamber blockEntity;
	
	public ExtractionChamberContainerData(BlockEntityExtractionChamber be, int amount) {
		super(amount);
		this.blockEntity = be;
	}
	
	@Override
	public int get(int key) {
		return switch (key) {
		case 0 -> this.blockEntity.getProgress();
		case 1 -> this.blockEntity.getEnergy();
		case 2 -> this.blockEntity.energyStorage.getMaxEnergyStored();
		default -> throw new UnsupportedOperationException("Unable to get key: '" + key + "' for block entity: '"
				+ this.blockEntity + "' at pos: '" + this.blockEntity.getBlockPos() + "'");
		};
	}

}
