package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class BlazeMeta extends MobMetaData {
    static String[] mobTrivia = {"Bring buckets, and watch in despair", "as it evaporates, and everything is on fire", "You are on fire"};

    BlazeMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Blaze getEntity(Level world) {
        return new Blaze(EntityType.BLAZE, world);
    }
}
