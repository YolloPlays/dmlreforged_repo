package com.yollo.dmlreforged.core.util;



import com.yollo.dmlreforged.core.configs.BalanceConfigs;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class TierHelper {
	
    public static int getPristineChance(int tier) {
        switch(tier) {
            case 0: return 0;
            case 1: return BalanceConfigs.tier1.get();
            case 2: return BalanceConfigs.tier2.get();
            case 3: return BalanceConfigs.tier3.get();
            case 4: return BalanceConfigs.tier4.get();
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

