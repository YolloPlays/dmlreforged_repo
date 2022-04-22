package com.yollo.dmlreforged.common.util;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.items.ItemGlitchSword;
import com.yollo.dmlreforged.common.items.init.ItemInit;
import com.yollo.dmlreforged.common.mobmetas.MobMetaData;
import com.yollo.dmlreforged.common.mobmetas.MobMetaFactory;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class DataModelHelper {
	
    public static NonNullList<ItemStack> getValidFromList(NonNullList<ItemStack> list) {
        NonNullList<ItemStack> filteredList = NonNullList.create();

        for(ItemStack stack : list) {
            Item item = stack.getItem();

            if(item instanceof ItemDataModel) {
                filteredList.add(stack);
            }
        }

        return filteredList;
    }

    public static ItemStack getModelFromMobKey(String mobKey) {
        ItemStack result = ItemStack.EMPTY;
        for (ItemDataModel dataModel : ItemInit.dataModels) {
            if(dataModel.getMobKey().equals(mobKey)) {
                result = new ItemStack(dataModel);
            }
        }
        return result;
    }

    public static boolean hasExtraTooltip(ItemStack stack) {
        return getExtraTooltip(stack) != null;
    }

    public static String getExtraTooltip(ItemStack stack) {
        return getMobMetaData(stack).getExtraTooltip();
    }

    public static String getMatterTypeName(ItemStack stack) {
        return getMobMetaData(stack).getType();
    }

    public static int getSimulationTickCost(ItemStack stack) {
        return getMobMetaData(stack).getSimulationTickCost();
    }

    public static String getMobKey(ItemStack stack) {
        if(stack.getItem() instanceof ItemDataModel model) {
            return (model.getMobKey());
        } else {
            return "zombie";
        }
    }

    public static MobMetaData getMobMetaData(ItemStack stack) {
        return MobMetaFactory.createMobMetaData(getMobKey(stack));
    }

    public static int getPristineChance(ItemStack stack) {
        return TierHelper.getPristineChance(getTier(stack));
    }

    public static MutableComponent getTierName(ItemStack stack, boolean getNextTierName) {
        return TierHelper.getTierName(getTier(stack), getNextTierName);
    }

    /* Called by deep learners */
    public static void increaseMobKillCount(ItemStack stack, ServerPlayer player) {
        // Get our current tier before increasing the kill count;
        int tier = getTier(stack);
        int i = getCurrentTierKillCount(stack);
        boolean isGlitchSwordEquipped = player.getMainHandItem().getItem() instanceof ItemGlitchSword;


        i = i + (isGlitchSwordEquipped ? 2 : 1);
        setCurrentTierKillCount(stack, i);

        // Update the totals
        setTotalKillCount(stack, getTotalKillCount(stack) + 1);

        if(DataModelLevelupHelper.shouldIncreaseTier(tier, i, getCurrentTierSimulationCount(stack))) {
            player.displayClientMessage(new TranslatableComponent("dmlreforged.tiers.increase_tier", stack.getHoverName(), getTierName(stack, true)), true);

            setCurrentTierKillCount(stack, 0);
            setCurrentTierSimulationCount(stack, 0);
            setTier(stack, tier + 1);
        }
    }

    /* Called by simulation chamber */
    public static void increaseSimulationCount(ItemStack stack) {
        int tier = getTier(stack);
        int i = getCurrentTierSimulationCount(stack);
        i = i + 1;
        setCurrentTierSimulationCount(stack, i);

        // Update the totals
        setTotalSimulationCount(stack, getTotalSimulationCount(stack) + 1);

        if(DataModelLevelupHelper.shouldIncreaseTier(tier, getCurrentTierKillCount(stack), i)) {
            setCurrentTierKillCount(stack, 0);
            setCurrentTierSimulationCount(stack, 0);
            setTier(stack, tier + 1);
        }
    }



    public static int getTier(ItemStack stack) {
        return NBTHelper.getInt(stack, "tier", 0);
    }

    public static void setTier(ItemStack stack, int tier) {
        NBTHelper.setInt(stack, "tier", tier);
    }

    public static int getCurrentTierKillCount(ItemStack stack) {
        return NBTHelper.getInt(stack, "killCount", 0);
    }

    public static void setCurrentTierKillCount(ItemStack stack, int count) {
        NBTHelper.setInt(stack, "killCount", count);
    }

    public static int getCurrentTierSimulationCount(ItemStack stack) {
        return NBTHelper.getInt(stack, "simulationCount", 0);
    }

    public static void setCurrentTierSimulationCount(ItemStack stack, int count) {
        NBTHelper.setInt(stack, "simulationCount", count);
    }

    public static int getTotalKillCount(ItemStack stack) {
        return NBTHelper.getInt(stack, "totalKillCount", 0);
    }

    public static void setTotalKillCount(ItemStack stack, int count) {
        NBTHelper.setInt(stack, "totalKillCount", count);
    }

    public static int getTotalSimulationCount(ItemStack stack) {
        return NBTHelper.getInt(stack, "totalSimulationCount", 0);
    }

    public static void setTotalSimulationCount(ItemStack stack, int count) {
        NBTHelper.setInt(stack, "totalSimulationCount", count);
    }


    /*
     The functions below are not NBT getters/setters
    */
    public static double getCurrentTierKillCountWithSims(ItemStack stack) {
        return DataModelLevelupHelper.getCurrentTierKillCountWithSims(getTier(stack), getCurrentTierKillCount(stack), getCurrentTierSimulationCount(stack));
    }

    public static int getCurrentTierSimulationCountWithKills(ItemStack stack) {
        return DataModelLevelupHelper.getCurrentTierSimulationCountWithKills(getTier(stack), getCurrentTierKillCount(stack), getCurrentTierSimulationCount(stack));
    }

    public static double getKillsToNextTier(ItemStack stack) {
        return DataModelLevelupHelper.getKillsToNextTier(getTier(stack), getCurrentTierKillCount(stack), getCurrentTierSimulationCount(stack));
    }

    public static int getSimulationsToNextTier(ItemStack stack) {
        return DataModelLevelupHelper.getSimulationsToNextTier(getTier(stack), getCurrentTierKillCount(stack), getCurrentTierSimulationCount(stack));
    }

    public static int getTierRoofAsKills(ItemStack stack) {
        if(getTier(stack) == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        return DataModelLevelupHelper.getTierRoof(getTier(stack), true);
    }

    public static int getTierRoof(ItemStack stack) {
        if(getTier(stack) == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        return DataModelLevelupHelper.getTierRoof(getTier(stack), false);
    }
	       
}
