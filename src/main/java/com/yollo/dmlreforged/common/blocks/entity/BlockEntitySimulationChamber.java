package com.yollo.dmlreforged.common.blocks.entity;

import java.util.HashMap;
import java.util.Random;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.energy.DeepEnergyStorage;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.items.init.BlockEntityInit;
import com.yollo.dmlreforged.common.items.init.ItemInit;
import com.yollo.dmlreforged.common.mobmetas.MobMetaData;
import com.yollo.dmlreforged.common.mobmetas.MobMetaFactory;
import com.yollo.dmlreforged.common.util.DataModelHelper;
import com.yollo.dmlreforged.common.util.MathHelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;

public class BlockEntitySimulationChamber extends InventoryBlockEntity {

	
	public static final int DATA_MODEL_SLOT = 0;
	public static final int POLYMER_SLOT = 1;
	public static final int LIVING_SLOT = 2;
	public static final int PRISTINE_SLOT = 3;
	public int percentDone = 0;
	public int currentEnergy = 300000;
	public DeepEnergyStorage energyStorage;
	public int ticks = 0;
	private boolean isCrafting = false;
	private boolean byproductSuccess = false;
	private LazyOptional<DeepEnergyStorage> energy;
    private String currentDataModelType = "";
    private MobMetaData mobMetaData;
    private HashMap<String, String> simulationText = new HashMap<>();
    
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
		tag.put("simulationText", getNBTForSimulationText());
	}

	@Override
	public void load(CompoundTag pTag) {
		super.load(pTag);
		setSimulationTextFromNBT(pTag.getCompound("simulationText"));
		currentEnergy = pTag.contains("energy") ? pTag.getInt("energy") : 300000;
		percentDone = pTag.contains("simulationProgress") ? pTag.getInt("simulationProgress") : 0;
		isCrafting = pTag.contains("isCrafting") ? pTag.getBoolean("isCrafting") : isCrafting;
		byproductSuccess = pTag.contains("craftSuccess") ? pTag.getBoolean("craftSuccess") : (isCrafting);
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

	public void tick(Level pLevel, BlockEntitySimulationChamber be) {
        ticks++;
        
        if(!pLevel.isClientSide) {
            energyStorage.receiveEnergy(520, false);
            if(!isCrafting()) {
                if(canStartSimulation()) {
                    startSimulation(be);
                }
            } else {
                if (!canContinueSimulation() || dataModelTypeChanged()) {
                    finishSimulation(true, pLevel, be);
                    return;
                }

                //updateSimulationText(getDataModel());

                if (percentDone == 0) {
                    Random rand = new Random();
                    int num = rand.nextInt(100);
                    int chance = DataModelHelper.getPristineChance(getDataModel());
                    byproductSuccess = num <= MathHelper.ensureRange(chance, 1, 100);
                }

                int rfTickCost = mobMetaData.getSimulationTickCost();
                energyStorage.voidEnergy(rfTickCost);
                
                // It takes 15 seconds to complete one cycle
				if (ticks % ((DeepMobLearning.TICKS_TO_SECOND * 15) / 100) == 0) {
				    percentDone++;
				}

                // Notify while crafting every other second, this is done more frequently when the container is open
                if (ticks % (DeepMobLearning.TICKS_TO_SECOND * 2) == 0) {
                    update();
                }
            }

            if(percentDone == 100) {
            	System.out.println("done");
                finishSimulation(false, pLevel, be);
                return;
            } 
            
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

	private void startSimulation(BlockEntitySimulationChamber be) {
        isCrafting = true;
        currentDataModelType = DataModelHelper.getMobMetaData(getDataModel()).getKey();
        mobMetaData = MobMetaFactory.createMobMetaData(currentDataModelType);
        ItemStack oldInput = getPolymerClay();
        ItemStack newInput = new ItemStack(ItemInit.POLYEMR_CLAY.get(), oldInput.getCount() - 1);
        inventory.setStackInSlot(POLYMER_SLOT, newInput);
        //resetSimulationAnimations();
    }

    private void finishSimulation(boolean abort, Level world, BlockEntitySimulationChamber be) {
        //resetSimulationAnimations();
        percentDone = 0;
        isCrafting = false;
        // Only decrease input and increase output if not aborted, and only if on the server's TE
        if(!abort && !world.isClientSide) {
            DataModelHelper.increaseSimulationCount(getDataModel());

            ItemStack oldOutput = getLiving();
            ItemStack newOutput = mobMetaData.getLivingMatterStack(oldOutput.getCount() + 1);
            inventory.setStackInSlot(LIVING_SLOT, newOutput);

            if(byproductSuccess) {
                // If Byproduct roll was successful
                byproductSuccess = false;
                ItemStack oldPristine = getPristine();
                ItemStack newPristine = mobMetaData.getPristineMatterStack(oldPristine.getCount() + 1);

                inventory.setStackInSlot(PRISTINE_SLOT, newPristine);
            }
            update();
        }
    }
    
    private boolean canStartSimulation() {
        return hasEnergyForSimulation() && canContinueSimulation() && !outputIsFull() && !pristineIsFull() && hasPolymerClay();
    }

    private boolean canContinueSimulation() {
        return hasDataModel() && DataModelHelper.getTier(getDataModel()) != 0;
    }

    private boolean dataModelTypeChanged() {
        return !currentDataModelType.equals(DataModelHelper.getMobMetaData(getDataModel()).getKey());
    }

    public boolean hasEnergyForSimulation() {
        if(hasDataModel()) {
            int ticksPerSimulation = 300;
            return energyStorage.getEnergyStored() > (ticksPerSimulation * DataModelHelper.getSimulationTickCost(getDataModel()));
        } else {
            return false;
        }
    }

    public ItemStack getDataModel() {
        return inventory.getStackInSlot(DATA_MODEL_SLOT);
    }

    private ItemStack getPolymerClay() {
        return inventory.getStackInSlot(POLYMER_SLOT);
    }

    private ItemStack getLiving() {
        return inventory.getStackInSlot(LIVING_SLOT);
    }

    private ItemStack getPristine() {
        return inventory.getStackInSlot(PRISTINE_SLOT);
    }

    public boolean hasPolymerClay() {
        ItemStack stack = getPolymerClay();
        return stack.getItem() == ItemInit.POLYEMR_CLAY.get() && stack.getCount() > 0;
    }

    public boolean hasDataModel() {
        return getDataModel().getItem() instanceof ItemDataModel;
    }

    public boolean outputIsFull() {
        ItemStack stack = getLiving();
        if(stack.isEmpty()) {
            return false;
        }

        boolean stackLimitReached = stack.getCount() == inventory.getSlotLimit(LIVING_SLOT);
        boolean outputMatches = dataModelMatchesOutput(getDataModel(), getLiving());

        return stackLimitReached || !outputMatches;
    }

    public boolean pristineIsFull() {
        ItemStack stack = getPristine();
        if(stack.isEmpty()) {
            return false;
        }

        boolean stackLimitReached = stack.getCount() == inventory.getSlotLimit(POLYMER_SLOT);
        boolean outputMatches = dataModelMatchesPristine(getDataModel(), getPristine());

        return stackLimitReached || !outputMatches;
    }
    
    public boolean getByproductSuccess() {
		return byproductSuccess;
    }

    private static boolean dataModelMatchesOutput(ItemStack stack, ItemStack output) {
        Item livingMatter = DataModelHelper.getMobMetaData(stack).getLivingMatter();
        return livingMatter.getClass().equals(output.getItem().getClass());
    }

    private static boolean dataModelMatchesPristine(ItemStack stack, ItemStack pristine) {
        Item pristineMatter = DataModelHelper.getMobMetaData(stack).getPristineMatter();
        return pristineMatter.getClass().equals(pristine.getItem().getClass());
    }
    
    private CompoundTag getNBTForSimulationText() {
        CompoundTag tag = new CompoundTag();
        simulationText.forEach(tag::putString);
        return tag;
    }

    private void setSimulationTextFromNBT(CompoundTag tag) {
        simulationText.forEach((key, text) -> simulationText.put(key, tag.getString(key)));
    }
   
    public void resetAnimations() {
        new HashMap<>();
        simulationText = new HashMap<>();
    }
}
