package com.yollo.dmlreforged.common.util.container;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.items.ItemDeepLearner;
import com.yollo.dmlreforged.common.items.init.ContainerInit;
import com.yollo.dmlreforged.common.items.init.ItemInit;
import com.yollo.dmlreforged.common.util.ItemBackedInventory;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class DeepLearnerContainer extends AbstractContainerMenu{
	public static DeepLearnerContainer fromNetwork(int windowId, Inventory inv, FriendlyByteBuf buf) {
		InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		return new DeepLearnerContainer(windowId, inv, inv.player.getItemInHand(hand));
	}
	
	protected Player player;
	protected InteractionHand usedHand;
	protected ItemStack deepLearner;
	public final Container deepLearnerInv;
	
	//Server
	public DeepLearnerContainer(int id, Inventory playerInv, ItemStack deepLearner) {
		super(ContainerInit.DEEP_LEARNER.get(), id);
		ContainerLevelAccess.create(playerInv.player.level, BlockPos.ZERO);
		this.player = playerInv.player;
		this.usedHand = player.getMainHandItem().getItem() instanceof ItemDeepLearner ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
		ItemStack heldItem = player.getMainHandItem().getItem() instanceof ItemDeepLearner ? player.getMainHandItem() : player.getOffhandItem();
		this.deepLearner = heldItem;
		
		this.deepLearner = deepLearner;
		if (!playerInv.player.level.isClientSide) {
			deepLearnerInv = ItemDeepLearner.getInventory(deepLearner);
		} else {
			deepLearnerInv = new ItemBackedInventory(deepLearner, DeepMobLearning.DEEP_LEARNER_INTERNAL_SLOTS_SIZE);
		}
		
		addInventorySlots();
		addDataModelSlots();
	}
	
	 private void addDataModelSlots() {
		 int index = 0;
		 for(int column = 0; column < 2; column++){
			  for(int row = 0; row < 2; row++){
				addSlot(new Slot(deepLearnerInv, index, 193- (row*18), 82 - (column*18)) {
		            @Override
		            public boolean mayPlace(ItemStack stack)
		            {
		                return !stack.isEmpty() && stack.getItem() instanceof ItemDataModel;
		            }
		            @Override
		            public int getMaxStackSize() {
		                return 1;
		            }            
				});
				index++;
			 }
		 }
	 }
	       
	        /*addSlot(new Slot(deepLearnerInv, 1, 275, 100));
	        addSlot(new Slot(deepLearnerInv, 2, 257, 118));
	        addSlot(new Slot(deepLearnerInv, 3, 275, 118));*/
	  

	    private void addInventorySlots() {
	        
	        for (int row = 0; row < 3; row++) {
	            for (int column = 0; column < 9; column++) {
	                int x = 8 + column * 18;
	                int y = 119 + row * 18;
	                int index = column + row * 9 + 9;
	                Slot slot = new Slot(player.getInventory(), index, x, y);
	                addSlot(slot);
	            }
	        }
	        // Bind actionbar
	        for (int row = 0; row < 9; row++) {
	            Slot slot = new Slot(player.getInventory(), row, 8 + row * 18, 177);
	            addSlot(slot);
	        }
	    }

	@Override
	public boolean stillValid(Player player) {
		if(player.getMainHandItem().getItem() == ItemInit.DEEP_LEARNER.get()) {
			return true;
		}
		else if(player.getOffhandItem().getItem() == ItemInit.DEEP_LEARNER.get()){
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		var retStack = ItemStack.EMPTY;
		final Slot slot = getSlot(index);
		if(slot.hasItem()) {
			final ItemStack item = slot.getItem();
			retStack = item.copy();
			if(index < 36) {
				if(!moveItemStackTo(item, 36, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!moveItemStackTo(item, 0, 36, false)) {
				return ItemStack.EMPTY;
			}
			
			if(item.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			}
			else slot.setChanged();
		}
		return retStack;
	}
	
	public Container getDeepInv() {
		return deepLearnerInv;
	}
}
