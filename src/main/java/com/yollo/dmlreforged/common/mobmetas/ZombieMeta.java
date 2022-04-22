package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ZombieMeta extends MobMetaData {
    static String[] mobTrivia = {"They go moan in the night.", "Does not understand the need for", "personal space"};

    ZombieMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Zombie getEntity(Level world) {
        return new Zombie(world);
    }

    public Zombie getExtraEntity(Level world) {
        Zombie childEntity = new Zombie(world);
        childEntity.setBaby(true);

        return childEntity;
    }

    public int getExtraInterfaceOffsetX() {
        return 21;
    }

    public int getExtraInterfaceOffsetY() {
        return 6;
    }
}
