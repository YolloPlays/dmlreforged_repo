package com.yollo.dmlreforged.common.mobmetas;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;

/**
 * Created by xt9 on 2017-06-10.
 */
@Mixin(WitherBoss.class)
public class WitherMeta extends MobMetaData {
    static String[] mobTrivia = {"Do not approach this enemy. Run!", "I mean it has 3 heads, what could", "possibly go wrong?"};

    WitherMeta(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter) {
        super(key, name, pluralName, numberOfHearts, interfaceScale, interfaceOffsetX, interfaceOffsetY, livingMatter, pristineMatter, mobTrivia);
    }

    public WitherBoss getEntity(Level world) {
        return new WitherBoss(EntityType.WITHER,world) {
        }; 
    }
}
