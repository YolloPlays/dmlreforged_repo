package com.yollo.dmlreforged.common.mobmetas;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemLivingMatter;
import com.yollo.dmlreforged.common.items.ItemPristineMatter;
import com.yollo.dmlreforged.common.util.MathHelper;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class MobMetaData {
    protected String name;
    private String pluralName;
    protected String key;
    protected int numberOfHearts;
    protected int interfaceScale;
    protected int interfaceOffsetX;
    protected int interfaceOffsetY;
    protected ItemLivingMatter livingMatter;
    protected ItemPristineMatter pristineMatter;
    protected String[] mobTrivia;

    private String[] mobRegistryNames;

    public MobMetaData(String key, String name, String pluralName, int numberOfHearts, int interfaceScale, int interfaceOffsetX, int interfaceOffsetY, Item livingMatter, Item pristineMatter, String[] mobTrivia) {
        this.key = key;
        this.name = name;
        this.pluralName = pluralName;
        this.numberOfHearts = numberOfHearts;
        this.interfaceScale = interfaceScale;
        this.interfaceOffsetX = interfaceOffsetX;
        this.interfaceOffsetY = interfaceOffsetY;
        this.livingMatter = (ItemLivingMatter) livingMatter;
        this.pristineMatter = (ItemPristineMatter) pristineMatter;
        this.mobTrivia = mobTrivia;
    }

    public int getSimulationTickCost() {
        int cost = DeepMobLearning.getCost(getKey());
        cost = MathHelper.ensureRange(cost, 1, DeepMobLearning.MAX_DATA_MODEL_COST);
        return cost;
    }
    public ItemStack getLivingMatterStack(int amount) {
        return new ItemStack(livingMatter, amount);
    }

    public ItemStack getPristineMatterStack(int amount) {
        return new ItemStack(pristineMatter, amount);
    }

    public String getName() {
        return name;
    }

    public String getPluralName() {
        return pluralName;
    }

    public String getKey() {
        return key;
    }

    public int getNumberOfHearts() {
        return numberOfHearts;
    }

    public int getInterfaceScale() {
        return interfaceScale;
    }

    public int getInterfaceOffsetX() {
        return interfaceOffsetX;
    }

    public int getInterfaceOffsetY() {
        return interfaceOffsetY;
    }

    public String getType() {
        return livingMatter.getType();
    }

    public ItemLivingMatter getLivingMatter() {
        return livingMatter;
    }

    public ItemPristineMatter getPristineMatter() {
        return pristineMatter;
    }

    public String[] getMobTrivia() {
        return mobTrivia;
    }

    public boolean entityLivingMatchesMob(LivingEntity entity) {      
        ResourceLocation registryName = entity.getType().getRegistryName();
        if (registryName != null) {
            String name = registryName.toString();
            for (String mobRegname : DeepMobLearning.getMobs(getKey())) {
                if (mobRegname.equals(name)) {
                    return true;
                }
            }
        }

        return false;
    }
    

    // Have to implement, different for every Meta
    public abstract LivingEntity getEntity(Level world);

    // Optional fields
    public Entity getExtraEntity(Level world) {
        return null;
    }

    public int getExtraInterfaceOffsetX() {
        return 0;
    }

    public int getExtraInterfaceOffsetY() {
        return 0;
    }

    public String getExtraTooltip() {
        return null;
    }
}