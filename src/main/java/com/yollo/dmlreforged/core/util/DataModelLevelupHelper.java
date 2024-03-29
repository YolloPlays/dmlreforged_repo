package com.yollo.dmlreforged.core.util;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.core.configs.BalanceConfigs;

public class DataModelLevelupHelper {
    // Simulations have no multipliers, they are always 1x
    private static final int[] killMultiplier = {
        BalanceConfigs.killMultiplierTier0.get(),
        BalanceConfigs.killMultiplierTier1.get(),
        BalanceConfigs.killMultiplierTier2.get(),
        BalanceConfigs.killMultiplierTier3.get(),
        0 // Max tier, no kill multiplier
    };

    private static final int[] maxExperience = {
        BalanceConfigs.killsToTier1.get() * killMultiplier[0],
        BalanceConfigs.killsToTier2.get() * killMultiplier[1],
        BalanceConfigs.killsToTier3.get() * killMultiplier[2],
        BalanceConfigs.killsToTier4.get() * killMultiplier[3],
    };

    /* tier is CURRENT tier, kc is kill count for CURRENT tier, sc is simulation count for CURRENT  tier */
    public static boolean shouldIncreaseTier(int tier, int kc, int sc) {
        if(tier == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return false;
        }
        int roof = maxExperience[tier];
        int killExperience = kc * killMultiplier[tier];

        return killExperience + sc >= roof;
    }

    public static double getCurrentTierKillCountWithSims(int tier, int kc, int sc) {
        if(tier == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        return kc + ((double) sc / killMultiplier[tier]);
    }

    public static int getCurrentTierSimulationCountWithKills(int tier, int kc, int sc) {
        if(tier == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        return sc + (kc * killMultiplier[tier]);
    }

    public static double getKillsToNextTier(int tier, int kc, int sc) {
        if(tier == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        int killRoof = getTierRoof(tier, true);
        return killRoof - getCurrentTierKillCountWithSims(tier, kc, sc);
    }

    public static int getSimulationsToNextTier(int tier, int kc, int sc) {
        if(tier == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        int roof = getTierRoof(tier, false);
        return roof - getCurrentTierSimulationCountWithKills(tier, kc, sc);
    }

    public static int getTierRoof(int tier, boolean asKills) {
        if(tier == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
            return 0;
        }
        if(!asKills) {
            return maxExperience[tier];
        } else {
            return maxExperience[tier] / killMultiplier[tier];
        }
    }

    public static int getKillMultiplier(int tier) {
        return killMultiplier[tier];
    }
}