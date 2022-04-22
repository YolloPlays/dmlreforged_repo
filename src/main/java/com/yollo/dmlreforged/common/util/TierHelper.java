package com.yollo.dmlreforged.common.util;

import com.yollo.dmlreforged.DeepMobLearning;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class TierHelper {
	
    public static int getPristineChance(int tier) {
        switch(tier) {
            case 0: return 0;
            case 1: return DeepMobLearning.tier1;
            case 2: return DeepMobLearning.tier2;
            case 3: return DeepMobLearning.tier3;
            case 4: return DeepMobLearning.tier4;
            default: return 0;
        }
    }
	
    public static MutableComponent getTierName(int tier, boolean getNextTierName) {
        int addTiers = getNextTierName ? 1 : 0;
        switch(tier + addTiers) {
            case 0: return new TranslatableComponent("dmlreforged.tiers.tier_1").withStyle(t -> t.withColor(ChatFormatting.DARK_GRAY));
            case 1: return new TranslatableComponent("dmlreforged.tiers.tier_2").withStyle(t -> t.withColor(ChatFormatting.GREEN));
            case 2: return new TranslatableComponent("dmlreforged.tiers.tier_3").withStyle(t -> t.withColor(ChatFormatting.BLUE));
            case 3: return new TranslatableComponent("dmlreforged.tiers.tier_4").withStyle(t -> t.withColor(ChatFormatting.LIGHT_PURPLE));
            case 4: return new TranslatableComponent("dmlreforged.tiers.tier_5").withStyle(t -> t.withColor(ChatFormatting.GOLD));
            default: return new TranslatableComponent("dmlreforged.tiers.tier_1").withStyle(t -> t.withColor(ChatFormatting.DARK_GRAY));
        }
    }

    public static boolean isMaxTier(int tier) {
        return tier == 4;
    }
    
}

