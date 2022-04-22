package com.yollo.dmlreforged.common.util;

import com.yollo.dmlreforged.DeepMobLearning;

public class DataModelLevelupHelper {
    // Simulations have no multipliers, they are always 1x
    private static final int[] killMultiplier = {
        MathHelper.ensureRange(DeepMobLearning.killMultiplierTier0, 1, 100),
        MathHelper.ensureRange(DeepMobLearning.killMultiplierTier1, 1, 100),
        MathHelper.ensureRange(DeepMobLearning.killMultiplierTier2, 1, 100),
        MathHelper.ensureRange(DeepMobLearning.killMultiplierTier3, 1, 100),
        0 // Max tier, no kill multiplier
    };

    private static final int[] maxExperience = {
        MathHelper.ensureRange(DeepMobLearning.killsToTier1, 1, 500) * killMultiplier[0],
        MathHelper.ensureRange(DeepMobLearning.killsToTier2, 1, 500) * killMultiplier[1],
        MathHelper.ensureRange(DeepMobLearning.killsToTier3, 1, 500) * killMultiplier[2],
        MathHelper.ensureRange(DeepMobLearning.killsToTier4, 1, 500) * killMultiplier[3],
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