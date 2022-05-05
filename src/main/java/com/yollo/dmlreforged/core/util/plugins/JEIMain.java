package com.yollo.dmlreforged.core.util.plugins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.compress.utils.Lists;

import com.mojang.datafixers.types.templates.List;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.core.configs.MobConfig;
import com.yollo.dmlreforged.core.init.ItemInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;


@JeiPlugin
public class JEIMain implements IModPlugin{
	
	private static ResourceLocation id = new ResourceLocation(DeepMobLearning.MOD_ID);
    //private static SimulationChamberRecipeCategory simChamberCategory;
    
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {


        registration.addRecipeCategories(new ExtractionChamberRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        								

	}
	
	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		 Map<ItemStack, NonNullList<ItemStack>> pristineTables = new HashMap<>();
		 
	        ItemInit.pristineMatter.forEach(
	            pristineItem -> pristineTables.put(new ItemStack(pristineItem, 1), MobConfig.getLootTable(pristineItem.getMobKey()))
	        );

	        pristineTables.forEach(
	                (input, outputs) -> outputs.forEach(
	                    (output) -> ExtractionChamberRecipes.addRecipe(input, output)
	                )
	         );
	        
		registration.addRecipes(new ArrayList(ExtractionChamberRecipes.recipes), ExtractionChamberRecipeCategory.id);
	}

	@Override
	public ResourceLocation getPluginUid() {
		return id;
	}

}
