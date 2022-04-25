package com.yollo.dmlreforged.common.blocks.entity;

import java.util.Arrays;
import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.energy.DeepEnergyStorage;
import com.yollo.dmlreforged.common.items.ItemPristineMatter;
import com.yollo.dmlreforged.common.items.init.BlockEntityInit;
import com.yollo.dmlreforged.common.util.MathHelper;
import com.yollo.dmlreforged.common.util.Pagination;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockEntityExtractionChamber extends InventoryBlockEntity{
	
	   public DeepEnergyStorage energyStorage;

	    public boolean isCrafting = false;
	    public int currentEnergy = 0;
	    private LazyOptional<DeepEnergyStorage> energy;
	    public int ticks = 0;
	    public int percentDone = 0;
	    public Pagination pageHandler = new Pagination(0, getLootFromPristine().size(), 9);
	    private String currentPristineMatter = "";
	    public ItemStack resultingItem = ItemStack.EMPTY;
	    public int energyCost = MathHelper.ensureRange(DeepMobLearning.rfCostExtractionChamber, 1, 18000);

	public BlockEntityExtractionChamber(BlockPos pos, BlockState state) {
		super(BlockEntityInit.ENTITY_EXTRACTION_CHAMBER.get(), pos, state, 17);
		this.energyStorage = createEnergyStorage();
		this.energy = LazyOptional.of(() -> this.energyStorage);
	}
	
	private DeepEnergyStorage createEnergyStorage() {
		return new DeepEnergyStorage(this, 1000000, 25600 , 0, 0);
	}
	
    public NonNullList<ItemStack> getLootFromPristine() {
        NonNullList<ItemStack> list = NonNullList.create();

        List<? extends String> toParseList;
        if(getPristine().getItem() instanceof ItemPristineMatter pristine) {
            toParseList = Arrays.asList(DeepMobLearning.getPristineLoot(pristine.getMobKey()));
        } else {
            return list;
        }

        for (String line : toParseList) {
            if (!getStackFromConfigLine(line).isEmpty()) {
                list.add(getStackFromConfigLine(line));
            }
        }

        return list;
    }
	
    private static ItemStack getStackFromConfigLine(String line) {
        String[] vals = line.split(",");

        if (vals.length < 2) {
            return ItemStack.EMPTY;
        }


        ResourceLocation itemLocation = new ResourceLocation(vals[0]);
        int amount;

        try {
            amount = Integer.parseInt(vals[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number for amount");
            return ItemStack.EMPTY;
        }


        Item item = ForgeRegistries.ITEMS.getValue(itemLocation);
        if(item != null) {
            return new ItemStack(item, amount);
        } else {
            return ItemStack.EMPTY;
        }
    }
    
    
    public ItemStack getPristine() {
        return inventory.getStackInSlot(0);
    }
    

	public int getProgress() {
		return percentDone;
	}

	public int getEnergy() {
		return currentEnergy;
	}
	
    public boolean pristineChanged() {
        return !getPristine().isEmpty() && !currentPristineMatter.equals(((ItemPristineMatter) getPristine().getItem()).getMobKey());
    }
    
    public void updatePageHandler(int currentPage) {
        pageHandler.update(currentPage, getLootFromPristine().size());
    }
    
    private boolean canStartCraft() {
        return canContinueCraft() && canInsertItem();
    }
    
    private boolean canContinueCraft() {
        return !resultingItem.isEmpty() && getPristine().getItem() instanceof ItemPristineMatter;
    }
    
    private boolean canInsertItem() {
        return inventory.canInsertItem(resultingItem);
    }
    
    private boolean hasEnergyForNextTick() {
        return energyStorage.getEnergyStored() >= energyCost;
    }
	
    public void tick(Level pLevel, BlockEntitySimulationChamber be) {
        ticks++;

        if(!pLevel.isClientSide) {
            if(pristineChanged()) {
                finishCraft(true);
                updatePageHandler(0);

                currentPristineMatter = ((ItemPristineMatter) getPristine().getItem()).getMobKey();
                resultingItem = ItemStack.EMPTY;
                update();
                return;
            }

            if (!isCrafting) {
                if (canStartCraft()) {
                    isCrafting = true;
                }
            } else {
                if (!canContinueCraft()) {
                    finishCraft(true);
                    return;
                }

                if(hasEnergyForNextTick()) {
                    energyStorage.voidEnergy(energyCost);
                    percentDone++;
                }

                // Notify while crafting every 5sec, this is done more frequently when the container is open
                if (ticks % (DeepMobLearning.TICKS_TO_SECOND * 5) == 0) {
                    update();
                }

                if (percentDone == 50) {
                    finishCraft(false);
                }
            }

            // Save to disk every 5 seconds if energy changed
            doStaggeredDiskSave(100);
        }
    }
    
    private void doStaggeredDiskSave(int divisor) {
        if(ticks % divisor == 0) {
            if(currentEnergy != energyStorage.getEnergyStored()) {
                // Save to disk every 5 seconds if energy changed
                currentEnergy = energyStorage.getEnergyStored();
                setChanged();
            }
        }
    }
    
    public void finishCraft(boolean abort) {
        isCrafting = false;
        percentDone = 0;
        if(!abort) {
            ItemStack remainder = inventory.setInFirstAvailableSlot(resultingItem);
            while (!remainder.isEmpty()) {
                remainder = inventory.setInFirstAvailableSlot(remainder);
            }

            getPristine().shrink(1);
        }
        setChanged();
        update();
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
		tag.putInt("craftingProgress", percentDone);
		tag.put("pageHandler", pageHandler.serializeNBT());
		tag.put("resultingItem", resultingItem.serializeNBT());
		tag.putBoolean("isCrafting", isCrafting);
		tag.putString("currentPristine", currentPristineMatter);
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		currentEnergy = pTag.contains("energy") ? pTag.getInt("energy") : 0;
		percentDone = pTag.contains("craftingProgress") ? pTag.getInt("craftingProgress") : 0;
		pageHandler.deserializeNBT(pTag.getCompound("pageHandler"));
		resultingItem = ItemStack.of(pTag.getCompound("resultingItem"));
		currentPristineMatter = pTag.contains("currentPristine") ? pTag.getString("currentPristine") : "";
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		return cap == CapabilityEnergy.ENERGY ? this.energy.cast() : super.getCapability(cap, side);
	}

}
