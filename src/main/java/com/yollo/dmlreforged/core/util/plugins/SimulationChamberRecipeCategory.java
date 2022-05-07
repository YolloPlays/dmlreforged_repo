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
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class SimulationChamberRecipeCategory implements IRecipeCategory<SimulationChamberRecipes>{
	
	public static TranslatableComponent title = new TranslatableComponent("block.dmlreforged.simulation_chamber");
	public static ResourceLocation id = new ResourceLocation(DeepMobLearning.MOD_ID, "simulation_chamber");
	public static RecipeType<SimulationChamberRecipes> type = RecipeType.create(DeepMobLearning.MOD_ID, "simulation_chamber", SimulationChamberRecipes.class);
	private IDrawable icon;
    private IDrawable background;
    private IDrawableAnimated progress;

	public SimulationChamberRecipeCategory(IGuiHelper guiHelper) {
        ResourceLocation base = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/jei/simulation_chamber.png");
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ItemInit.SIMULATION_CHAMBER_ITEM.get()));
        
        background = guiHelper.createDrawable(base, 0, 0, 116, 43);
        IDrawableStatic progressStatic = guiHelper.createDrawable(base, 0, 43, 35, 6);
        this.progress = guiHelper.createAnimatedDrawable(progressStatic, 120, IDrawableAnimated.StartDirection.LEFT, false);
	}
	
	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, SimulationChamberRecipes recipe, IFocusGroup focuses) {
		builder.addSlot(RecipeIngredientRole.CATALYST, 4, 4).addItemStack(recipe.getDataModel());
		builder.addSlot(RecipeIngredientRole.INPUT, 28, 4).addItemStack(new ItemStack(ItemInit.POLYEMR_CLAY.get()));
		builder.addSlot(RecipeIngredientRole.OUTPUT, 96, 4).addItemStack(recipe.getMatter());
		builder.addSlot(RecipeIngredientRole.OUTPUT, 76, 26).addItemStack(recipe.getPristine());
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
		return icon;
	}
	
	@Override
	public void draw(SimulationChamberRecipes recipe, IRecipeSlotsView recipeSlotsView, PoseStack stack, double mouseX, double mouseY) {
		progress.draw(stack,52,9);
	}

	@Override
	public ResourceLocation getUid() {
		return id;
	}
	
	@Override
	public RecipeType<SimulationChamberRecipes> getRecipeType() {
		return type;
	}

	@Override
	public Class<? extends SimulationChamberRecipes> getRecipeClass() {
		return SimulationChamberRecipes.class;
	}

}
