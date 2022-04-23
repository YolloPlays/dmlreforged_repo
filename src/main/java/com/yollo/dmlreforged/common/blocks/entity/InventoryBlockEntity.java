package com.yollo.dmlreforged.common.blocks.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public abstract class InventoryBlockEntity extends RandomizableContainerBlockEntity {
	public final int size;
	protected int timer;
	protected boolean requiresUpdate;
	private NonNullList<ItemStack> items;
	
    public InventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        if (size <= 0) {
            size = 1;
        }
        
        this.size = size;
        this.items = NonNullList.<ItemStack>withSize(size, ItemStack.EMPTY);
    }


    @Override
    public int getContainerSize() {
      return this.size;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
      return this.items;
    }

    @Override
    public void setItems(NonNullList<ItemStack> itemsIn) {
      this.items = NonNullList.<ItemStack>withSize(this.size, ItemStack.EMPTY);

      for (int i = 0; i < itemsIn.size(); i++) {
        if (i < this.items.size()) {
          this.getItems().set(i, itemsIn.get(i));
        }
      }
    }

	@Override
	protected Component getDefaultName() {
		return TextComponent.EMPTY;
	}
	
	  @Override
	  public void load(CompoundTag compoundTag) {
	    super.load(compoundTag);

	    this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);

	    if (!this.tryLoadLootTable(compoundTag)) {
	      ContainerHelper.loadAllItems(compoundTag, this.items);
	    }
	  }

	  @Override
	  public void saveAdditional(CompoundTag compoundTag) {
	    super.saveAdditional(compoundTag);

	    if (!this.trySaveLootTable(compoundTag)) {
	      ContainerHelper.saveAllItems(compoundTag, this.items);
	    }
	  }
}
