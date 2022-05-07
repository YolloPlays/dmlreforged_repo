package com.yollo.dmlreforged.core.util.plugins;

import net.minecraft.world.item.ItemStack;

public class ExtractionChamberRecipes {
	
    public final ItemStack input;
    public final ItemStack output;


    public ExtractionChamberRecipes(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }
   
    public ItemStack getInputs() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

}
