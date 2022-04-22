package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemSootedRedstone extends Item{

	public ItemSootedRedstone() {
		super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
	}
	
	@Override
	public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> list, TooltipFlag p_41424_) {
		if(DeepMobLearning.isSootedRedstoneCraftingEnabled) {
			list.add(new TranslatableComponent("dmlreforged.hover_text.soot_covered_redstone_1", new TextComponent(new ItemStack(Items.REDSTONE).getHoverName().getString()).withStyle(t->t.withColor(ChatFormatting.RED))).withStyle(t->t.withColor(ChatFormatting.GRAY)));
	        list.add(new TranslatableComponent("dmlreforged.hover_text.soot_covered_redstone_2", new TextComponent(new ItemStack(Items.COAL_BLOCK).getHoverName().getString()).withStyle(t->t.withColor(ChatFormatting.WHITE))).withStyle(t->t.withColor(ChatFormatting.GRAY)));
		}
	}
}
