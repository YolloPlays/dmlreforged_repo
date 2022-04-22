package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EndermanMeta extends MobMetaData {
    static String[] mobTrivia = {"Friendly unless provoked, dislikes rain.", "Teleports short distances"};

    EndermanMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public EnderMan getEntity(Level world) {
        return new EnderMan(EntityType.ENDERMAN, world);
    }

}
