package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ShulkerMeta extends MobMetaData {
    static String[] mobTrivia = {"Found in End cities", "Sneaky little buggers"};

    ShulkerMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Shulker getEntity(Level world) {
        return new Shulker(EntityType.SHULKER,world);
    }

}
