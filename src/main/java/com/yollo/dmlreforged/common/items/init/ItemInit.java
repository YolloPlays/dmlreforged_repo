package com.yollo.dmlreforged.common.items.init;

import com.google.common.base.Supplier;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.items.ItemDeepLearner;
import com.yollo.dmlreforged.common.items.ItemGlitchArmor;
import com.yollo.dmlreforged.common.items.ItemGlitchArmor.ItemGlitchHelmet;
import com.yollo.dmlreforged.common.items.ItemGlitchFragment;
import com.yollo.dmlreforged.common.items.ItemGlitchHeart;
import com.yollo.dmlreforged.common.items.ItemGlitchIngot;
import com.yollo.dmlreforged.common.items.ItemGlitchSword;
import com.yollo.dmlreforged.common.items.ItemLivingMatter;
import com.yollo.dmlreforged.common.items.ItemPristineMatter;
import com.yollo.dmlreforged.common.items.ItemSootedRedstone;

import net.minecraft.core.NonNullList;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeepMobLearning.MOD_ID);
	
    public static NonNullList<ItemDataModel> dataModels = NonNullList.create();
    public static NonNullList<ItemPristineMatter> pristineMatter = NonNullList.create();


	//Items  
	public static final RegistryObject<Item> DM_BLANK = register("data_model_blank", () -> new Item(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	public static final RegistryObject<Item> DM_BLAZE = register("data_model_blaze", () -> new ItemDataModel.Blaze());
	public static final RegistryObject<Item> DM_CREEPER = register("data_model_creeper", () -> new ItemDataModel.Creeper());
	public static final RegistryObject<Item> DM_DRAGON = register("data_model_dragon", () -> new ItemDataModel.Dragon());
	public static final RegistryObject<Item> DM_ENDERMAN = register("data_model_enderman", () -> new ItemDataModel.Enderman());
	public static final RegistryObject<Item> DM_GHAST = register("data_model_ghast", () -> new ItemDataModel.Ghast());
	public static final RegistryObject<Item> DM_GUARDIAN = register("data_model_guardian", () -> new ItemDataModel.Guardian());
	public static final RegistryObject<Item> DM_SHULKER = register("data_model_shulker", () -> new ItemDataModel.Shulker());
	public static final RegistryObject<Item> DM_SKELETON = register("data_model_skeleton", () -> new ItemDataModel.Skeleton());
	public static final RegistryObject<Item> DM_SLIME = register("data_model_slime", () -> new ItemDataModel.Slime());
	public static final RegistryObject<Item> DM_SPIDER = register("data_model_spider", () -> new ItemDataModel.Spider());
	public static final RegistryObject<Item> DM_WITCH = register("data_model_witch", () -> new ItemDataModel.Witch());
	public static final RegistryObject<Item> DM_WITHER = register("data_model_wither", () -> new ItemDataModel.Wither());
	public static final RegistryObject<Item> DM_WITHERSKELETON = register("data_model_wither_skeleton", () -> new ItemDataModel.WitherSkeleton());
	public static final RegistryObject<Item> DM_ZOMBIE = register("data_model_zombie", () -> new ItemDataModel.Zombie());
	
	public static final RegistryObject<Item> PM_BLAZE = register("pristine_matter_blaze", () -> new ItemPristineMatter.Blaze());
	public static final RegistryObject<Item> PM_CREEPER = register("pristine_matter_creeper", () -> new ItemPristineMatter.Creeper());
	public static final RegistryObject<Item> PM_DRAGON = register("pristine_matter_dragon", () -> new ItemPristineMatter.Dragon());
	public static final RegistryObject<Item> PM_ENDERMAN = register("pristine_matter_enderman", () -> new ItemPristineMatter.Enderman());
	public static final RegistryObject<Item> PM_GHAST = register("pristine_matter_ghast", () -> new ItemPristineMatter.Ghast());
	public static final RegistryObject<Item> PM_GUARDIAN = register("pristine_matter_guardian", () -> new ItemPristineMatter.Guardian());
	public static final RegistryObject<Item> PM_SHULKER = register("pristine_matter_shulker", () -> new ItemPristineMatter.Shulker());
	public static final RegistryObject<Item> PM_SKELETON = register("pristine_matter_skeleton", () -> new ItemPristineMatter.Skeleton());
	public static final RegistryObject<Item> PM_SLIME = register("pristine_matter_slime", () -> new ItemPristineMatter.Slime());
	public static final RegistryObject<Item> PM_SPIDER = register("pristine_matter_spider", () -> new ItemPristineMatter.Spider());
	public static final RegistryObject<Item> PM_WITCH = register("pristine_matter_witch", () -> new ItemPristineMatter.Witch());
	public static final RegistryObject<Item> PM_WITHER = register("pristine_matter_wither", () -> new ItemPristineMatter.Wither());
	public static final RegistryObject<Item> PM_WITHERSKELETON = register("pristine_matter_wither_skeleton", () -> new ItemPristineMatter.WitherSkeleton());
	public static final RegistryObject<Item> PM_ZOMBIE = register("pristine_matter_zombie", () -> new ItemPristineMatter.Zombie());
	
	public static final RegistryObject<Item> LM_HELLISH = register("living_matter_hellish", () -> new ItemLivingMatter.Hellish());
	public static final RegistryObject<Item> LM_EXTRATERRESTRIAL = register("living_matter_extraterrestrial", () -> new ItemLivingMatter.Extraterrestrial());
	public static final RegistryObject<Item> LM_OVERWORLDIAN = register("living_matter_overworldian", () -> new ItemLivingMatter.Overworldian());
	
	public static final RegistryObject<Item> DEEP_LEARNER= register("deep_learner", () -> new ItemDeepLearner());
	
	public static final RegistryObject<Item> POLYEMR_CLAY= register("polymer_clay", () -> new Item(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	public static final RegistryObject<Item> SOOT_COVERED_PLATE= register("soot_covered_plate", () -> new Item(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	public static final RegistryObject<Item> SOOT_COVERED_REDSTONE= register("soot_covered_redstone", () -> new ItemSootedRedstone());
	
	public static final RegistryObject<Item> GLITCH_INGOT = register("glitch_infused_ingot", () -> new ItemGlitchIngot());
	public static final RegistryObject<Item> GLITCH_HEART = register("glitch_heart", () -> new ItemGlitchHeart());
	public static final RegistryObject<Item> GLITCH_FRAGMENT = register("glitch_fragment", () -> new ItemGlitchFragment());
	
	public static final RegistryObject<Item> GLITCH_SWORD = register("glitch_infused_sword", () -> new ItemGlitchSword());
	public static final RegistryObject<Item> GLITCH_HELMET = register("glitch_infused_helmet", () -> new ItemGlitchArmor.ItemGlitchHelmet());
	public static final RegistryObject<Item> GLITCH_CHEST = register("glitch_infused_chestplate", () -> new ItemGlitchArmor.ItemGlitchChestplate());
	public static final RegistryObject<Item> GLITCH_LEGGINGS = register("glitch_infused_leggings", () -> new ItemGlitchArmor.ItemGlitchLeggings());
	public static final RegistryObject<Item> GLITCH_BOOTS = register("glitch_infused_boots", () -> new ItemGlitchArmor.ItemGlitchBoots());
	
	//Blocks
	public static final RegistryObject<BlockItem> SIMULATION_CHAMBER_ITEM = register("simulation_chamber", () -> new BlockItem(BlockInit.SIMULATION_CHAMBER.get(), new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	public static final RegistryObject<BlockItem> EXTRACTION_CHAMBER_ITEM = register("extraction_chamber", () -> new BlockItem(BlockInit.EXTRACTION_CHAMBER.get(), new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	public static final RegistryObject<BlockItem> MACHINE_CASING_ITEM = register("machine_casing", () -> new BlockItem(BlockInit.MACHINE_CASING.get(), new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	public static final RegistryObject<BlockItem> INFUSED_INGOT_BLOCK_ITEM = register("infused_ingot_block", () -> new BlockItem(BlockInit.INFUSED_INGOT_BLOCK.get(), new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB)));
	
	public static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item){
		return ITEMS.register(name, item);
	}
	
	public void populateLists() {
	    dataModels.add(new ItemDataModel.Blaze());
	    dataModels.add(new ItemDataModel.Creeper());
	    dataModels.add(new ItemDataModel.Dragon());
	    dataModels.add(new ItemDataModel.Enderman());
	    dataModels.add(new ItemDataModel.Ghast());
	    dataModels.add(new ItemDataModel.Guardian());
	    dataModels.add(new ItemDataModel.Shulker());
	    dataModels.add(new ItemDataModel.Skeleton());
	    dataModels.add(new ItemDataModel.Slime());
	    dataModels.add(new ItemDataModel.Spider());
	    dataModels.add(new ItemDataModel.Witch());
	    dataModels.add(new ItemDataModel.Wither());
	    dataModels.add(new ItemDataModel.WitherSkeleton());
	    dataModels.add(new ItemDataModel.Zombie());
	    
	    pristineMatter.add(new ItemPristineMatter.Blaze());
	    pristineMatter.add(new ItemPristineMatter.Creeper());
	    pristineMatter.add(new ItemPristineMatter.Dragon());
		pristineMatter.add(new ItemPristineMatter.Enderman());
		pristineMatter.add(new ItemPristineMatter.Ghast());
		pristineMatter.add(new ItemPristineMatter.Guardian());
		pristineMatter.add(new ItemPristineMatter.Shulker());
		pristineMatter.add(new ItemPristineMatter.Skeleton());
		pristineMatter.add(new ItemPristineMatter.Slime());
		pristineMatter.add(new ItemPristineMatter.Spider());
		pristineMatter.add(new ItemPristineMatter.Witch());
		pristineMatter.add(new ItemPristineMatter.WitherSkeleton());
		pristineMatter.add(new ItemPristineMatter.Zombie());
	    
	}
	
}
	

