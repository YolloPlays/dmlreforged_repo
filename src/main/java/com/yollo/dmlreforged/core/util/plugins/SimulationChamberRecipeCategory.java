package com.yollo.dmlreforged.core.util.plugins;

import com.mojang.blaze3d.vertex.PoseStack;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.core.init.ItemInit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SimulationChamberRecipeCategory implements IRecipeCategory<ExtractionChamberRecipeCategory>{
	
	public static TranslatableComponent title = new TranslatableComponent("block.dmlreforged.simulation_chamber");
	private static ResourceLocation id = new ResourceLocation(DeepMobLearning.MOD_ID, "simulation_chamber");
	private IDrawable catalyst;
    private IDrawable background;
    private IDrawableAnimated progress;

	public SimulationChamberRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation base = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/jei/simulation_chamber.png");
        this.catalyst = guiHelper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ItemInit.SIMULATION_CHAMBER_ITEM.get()));

        background = guiHelper.createDrawable(base, 0, 0, 116, 43);
        IDrawableStatic progress = guiHelper.createDrawable(base, 0, 43, 35, 6);
        this.progress = guiHelper.createAnimatedDrawable(progress, 120, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ExtractionChamberRecipeCategory recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.INPUT, 3, 3);
		builder.addSlot(RecipeIngredientRole.INPUT, 27, 3);
		builder.addSlot(RecipeIngredientRole.INPUT, 95, 3);
		builder.addSlot(RecipeIngredientRole.INPUT, 75, 25);
		IRecipeCategory.super.setRecipe(builder, recipe, focuses);
	}
	
	@Override
	public void draw(ExtractionChamberRecipeCategory recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		progress.draw(stack);
		IRecipeCategory.super.draw(recipe, recipeSlotsView, stack, mouseX, mouseY);
	}

	@Override
	public Component getTitle() {
		return title;
	}

	@Override
	public IDrawable getBackground() {
		return background;
	}

	@Override
	public IDrawable getIcon() {
		return catalyst;
	}

	@Override
	public ResourceLocation getUid() {
		return id;
	}

	@Override
	public Class<? extends ExtractionChamberRecipeCategory> getRecipeClass() {
		return ExtractionChamberRecipeCategory.class;
	}

}
