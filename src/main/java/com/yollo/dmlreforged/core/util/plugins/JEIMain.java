//package com.yollo.dmlreforged.core.util.plugins;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import com.google.common.collect.Lists;
//import com.yollo.dmlreforged.DeepMobLearning;
//import com.yollo.dmlreforged.common.items.ItemDataModel;
//import com.yollo.dmlreforged.common.items.ItemPristineMatter;
//import com.yollo.dmlreforged.core.configs.MobConfig;
//import com.yollo.dmlreforged.core.init.ItemInit;
//import com.yollo.dmlreforged.core.util.DataModelHelper;
//
//import mezz.jei.api.IModPlugin;
//import mezz.jei.api.JeiPlugin;
//import mezz.jei.api.constants.VanillaTypes;
//import mezz.jei.api.registration.IRecipeCatalystRegistration;
//import mezz.jei.api.registration.IRecipeCategoryRegistration;
//import mezz.jei.api.registration.IRecipeRegistration;
//import net.minecraft.core.NonNullList;
//import net.minecraft.network.chat.TranslatableComponent;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.item.ItemStack;
//
//
//@JeiPlugin
//public class JEIMain implements IModPlugin{
//	
//	private static ResourceLocation id = new ResourceLocation(DeepMobLearning.MOD_ID, "jei_plugin");
//
//	@Override
//	public void registerCategories(IRecipeCategoryRegistration registration) {
//        registration.addRecipeCategories(new ExtractionChamberRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
//        registration.addRecipeCategories(new SimulationChamberRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
//	}
//	
//	@Override
//	public void registerRecipes(IRecipeRegistration registration) {
//		addExtractionChamberRecipe(registration);
//		addSimulationChamberRecipe(registration);
//		registration.addIngredientInfo(new ItemStack(ItemInit.GLITCH_INGOT.get()), VanillaTypes.ITEM, new TranslatableComponent("jei.dmlreforged.glitch_ingot"));
//	}
//	
//	private void addSimulationChamberRecipe(IRecipeRegistration registration) {
//		List<SimulationChamberRecipes> list = Lists.newArrayList();
//		
//		ItemInit.ITEMS.getEntries().forEach(stack -> {
//			if(stack.get() instanceof ItemDataModel dataModel) {
//				ItemStack dataStack = new ItemStack(dataModel);
//				DataModelHelper.setTier(dataStack, 1);
//				list.add(new SimulationChamberRecipes(dataStack));
//			}
//		});
//		registration.addRecipes(SimulationChamberRecipeCategory.type, list);
//	}
//	
//	private void addExtractionChamberRecipe(IRecipeRegistration registration) {
//		 Map<ItemStack, NonNullList<ItemStack>> pristineTables = new HashMap<>();
//		 List<ExtractionChamberRecipes> list = Lists.newArrayList();
//		 
//		 ItemInit.ITEMS.getEntries().forEach(stack -> {
//			 if(stack.get() instanceof ItemPristineMatter matter) {
//				 pristineTables.put(new ItemStack(matter, 1), MobConfig.getLootTable(matter.getMobKey()));
//			 }
//		 });
//       pristineTables.forEach(
//	        (input, outputs) -> outputs.forEach((output) -> {
//	            	list.add(new ExtractionChamberRecipes(input, output));
//	        })
//        );
//       registration.addRecipes(ExtractionChamberRecipeCategory.type, list);
//	}
//	
//	@Override
//	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
//		registration.addRecipeCatalyst(new ItemStack(ItemInit.EXTRACTION_CHAMBER_ITEM.get()), ExtractionChamberRecipeCategory.type);
//		registration.addRecipeCatalyst(new ItemStack(ItemInit.SIMULATION_CHAMBER_ITEM.get()), SimulationChamberRecipeCategory.type);
//	}
//	
//	@Override
//	public ResourceLocation getPluginUid() {
//		return id;
//	}
//}
