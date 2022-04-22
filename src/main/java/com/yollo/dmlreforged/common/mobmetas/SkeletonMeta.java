package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;


public class SkeletonMeta extends MobMetaData {
    static String[] mobTrivia = {"A formidable archer, which seem to be running", "some sort of cheat engine", "A shield could prove useful"};

    SkeletonMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Skeleton getEntity(Level world) {
        Skeleton entity = new Skeleton(EntityType.SKELETON, world);
        entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
        return entity;
    }
}
