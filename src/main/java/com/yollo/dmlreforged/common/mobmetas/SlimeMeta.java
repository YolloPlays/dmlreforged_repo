package com.yollo.dmlreforged.common.mobmetas;



import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class SlimeMeta extends MobMetaData {
    static String[] mobTrivia = {"The bounce", "bounce his bounce", "squish - \"A slime haiku\""};

    SlimeMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Slime getEntity(Level world) {
        return new Slime(EntityType.SLIME,world);
    }
}
