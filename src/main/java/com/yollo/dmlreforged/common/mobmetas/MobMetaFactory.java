package com.yollo.dmlreforged.common.mobmetas;

import com.yollo.dmlreforged.common.items.init.ItemInit;

public class MobMetaFactory {
    public static MobMetaData createMobMetaData(String key) {
        MobMetaData meta;

        if(key.equals("zombie")) {
            meta = new ZombieMeta(
                "zombie",
                "Zombie",
                "Zombies",
                10,
                35,
                -2,
                6,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_ZOMBIE.get()
            );
        } else if(key.equals("skeleton")) {
            meta = new SkeletonMeta(
                "skeleton",
                "Skeleton",
                "Skeletons",
                10,
                38,
                6,
                10,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_SKELETON.get()
            );
        } else if(key.equals("blaze")) {
            meta = new BlazeMeta(
                "blaze",
                "Blaze",
                "Blazes",
                10,
                48,
                10,
                20,
                ItemInit.LM_HELLISH.get(),
                ItemInit.PM_BLAZE.get()                
            );
        } else if(key.equals("enderman")) {
            meta = new EndermanMeta(
                "enderman",
                "Enderman",
                "Endermen",
                20,
                30,
                5,
                11,
                ItemInit.LM_EXTRATERRESTRIAL.get(),
                ItemInit.PM_ENDERMAN.get()
            );
        } else if(key.equals("wither")) {
            meta = new WitherMeta(
                "wither",
                "Wither",
                "Withers",
                150,
                22,
                3,
                18,
                ItemInit.LM_EXTRATERRESTRIAL.get(),
                ItemInit.PM_WITHER.get()
            );
        } else if(key.equals("witch")) {
            meta = new WitchMeta(
                "witch",
                "Witch",
                "Witches",
                13,
                34,
                4,
                11,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_WITCH.get()
            );
        } else if(key.equals("spider")) {
            meta = new SpiderMeta(
                "spider",
                "Spider",
                "Spiders",
                8,
                30,
                5,
                0,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_SPIDER.get()
            );
        } else if(key.equals("creeper")) {
            meta = new CreeperMeta(
                "creeper",
                "Creeper",
                "Creepers",
                10,
                42,
                5,
                5,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_CREEPER.get()
            );
        } else if(key.equals("ghast")) {
            meta = new GhastMeta(
                "ghast",
                "Ghast",
                "Ghasts",
                5,
                10,
                0,
                -20,
                ItemInit.LM_HELLISH.get(),
                ItemInit.PM_GHAST.get()
            );
        } else if(key.equals("witherskeleton")) {
            meta = new WitherSkeletonMeta(
                "witherskeleton",
                "Wither Skeleton",
                "Wither Skeletons",
                10,
                33,
                5,
                10,
                ItemInit.LM_HELLISH.get(),
                ItemInit.PM_WITHERSKELETON.get()
            );
        } else if(key.equals("slime")) {
            meta = new SlimeMeta(
                "slime",
                "Slime",
                "Slimes",
                8,
                60,
                10,
                -16,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_SLIME.get()
            );
        } else if(key.equals("dragon")) {
            meta = new DragonMeta(
                "dragon",
                "Ender Dragon",
                "Ender Dragons",
                100,
                7,
                0,
                -20,
                ItemInit.LM_EXTRATERRESTRIAL.get(),
                ItemInit.PM_DRAGON.get()
            );
        } else if(key.equals("shulker")) {
            meta = new ShulkerMeta(
                "shulker",
                "Shulker",
                "Shulkers",
                15,
                36,
                5,
                -5,
                ItemInit.LM_EXTRATERRESTRIAL.get(),
                ItemInit.PM_SHULKER.get()
            );
        }
        else if(key.equals("guardian")) {
            meta = new GuardianMeta(
                "guardian",
                "Guardian",
                "Guardians",
                15,
                36,
                5,
                -5,
                ItemInit.LM_OVERWORLDIAN.get(),
                ItemInit.PM_GUARDIAN.get()
            );
        }
        else {
        	 /* Fallback if key does not match any entry */
            meta = new ZombieMeta("zombie", "Zombie", "Zombies", 0, 0, 0, 0, ItemInit.LM_OVERWORLDIAN.get(), ItemInit.PM_ZOMBIE.get());
        }
        return meta;
    }
}
