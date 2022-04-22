package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class WitchMeta extends MobMetaData {
    static String[] mobTrivia = {"Affinity with potions and concoctions", "Beware!"};

    WitchMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Witch getEntity(Level world) {
        Witch entity = new Witch(EntityType.WITCH,world);
        entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.POTION));
        return entity;
    }
}
