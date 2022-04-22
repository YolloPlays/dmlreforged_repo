package com.yollo.dmlreforged.common.mobmetas;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * Created by xt9 on 2017-06-15.
 */
public class DragonMeta extends MobMetaData {
    static String[] mobTrivia = {"Resides in the end, does not harbor treasure", "Destroy its crystals, break the cycle."};

    DragonMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public EnderDragon getEntity(Level world) {
        return new EnderDragon(EntityType.ENDER_DRAGON ,world);
    }
}
