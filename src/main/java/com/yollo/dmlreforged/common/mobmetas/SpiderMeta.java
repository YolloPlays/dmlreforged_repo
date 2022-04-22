package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

 public class SpiderMeta extends MobMetaData {
    static String[] mobTrivia = {"Nocturnal douchebags, beware", "Drops strands of string for some reason.."};

    SpiderMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Spider getEntity(Level world) {
        return new Spider(EntityType.SPIDER,world);
    }

    public Spider getExtraEntity(Level world) {
        return new CaveSpider(EntityType.CAVE_SPIDER, world);
    }

    public int getExtraInterfaceOffsetX() {
        return 5;
    }

    public int getExtraInterfaceOffsetY() {
        return -25;
    }
}
