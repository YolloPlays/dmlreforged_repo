package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class GuardianMeta extends MobMetaData {
    static String[] mobTrivia = {"Lurking in the oceans.", "Uses some sort of sonar beam as", "a means of attack"};

    GuardianMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    @Override
    public Guardian getEntity(Level world) {
        return new Guardian(EntityType.GUARDIAN, world);
    }
}

