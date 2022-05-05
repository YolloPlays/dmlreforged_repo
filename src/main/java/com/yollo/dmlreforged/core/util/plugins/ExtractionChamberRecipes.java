package com.yollo.dmlreforged.core.util.plugins;

import java.util.ArrayList;
import net.minecraft.world.item.ItemStack;

public class ExtractionChamberRecipes {
    public static ArrayList<ExtractionChamberRecipes> recipes = new ArrayList<>();
    public final ItemStack input;
    public final ItemStack output;


    public ExtractionChamberRecipes(ItemStack input, ItemStack output) {
        this.input = input;
        this.output = output;
    }

    public static void addRecipe(ItemStack input, ItemStack output) {
        recipes.add(new ExtractionChamberRecipes(input, output));
    }
}
