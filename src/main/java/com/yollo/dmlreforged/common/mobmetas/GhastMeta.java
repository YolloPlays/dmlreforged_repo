package com.yollo.dmlreforged.common.mobmetas;


import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class GhastMeta extends MobMetaData {
    static String[] mobTrivia = {"If you hear something that sounds like", "a crying llama, you're probably hearing a ghast"};

    GhastMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public Ghast getEntity(Level world) {
        return new Ghast(EntityType.GHAST, world);
    }

}
