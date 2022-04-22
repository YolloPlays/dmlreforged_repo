package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class WitherSkeletonMeta extends MobMetaData {
    static String[] mobTrivia = {"Inflicts the wither effect", "Bring milk"};

    WitherSkeletonMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public WitherSkeleton getEntity(Level world) {
        WitherSkeleton entity = new WitherSkeleton(EntityType.WITHER_SKELETON,world);
        entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.STONE_SWORD));;
        return entity;
    }
}
