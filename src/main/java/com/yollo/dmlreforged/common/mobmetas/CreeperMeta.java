package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

/**
 * Created by xt9 on 2017-06-12.
 */
public class CreeperMeta extends MobMetaData {
    static String[] mobTrivia = {"Will blow up your base if", "left unattended."};

    CreeperMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public LivingEntity getEntity(Level world) {
        return new Creeper(EntityType.CREEPER, world);
    }
}
