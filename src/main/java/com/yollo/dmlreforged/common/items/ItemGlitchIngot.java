package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemGlitchIngot extends Item{
    public ItemGlitchIngot() {
        super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
    }
 
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flagIn) {
    	if(DeepMobLearning.isSootedRedstoneCraftingEnabled) {
    		list.add(new TranslatableComponent("dmlreforged.hover_text.glitchingot_1", new TranslatableComponent("item.dmlreforged.glitch_heart").withStyle(t->t.withColor(ChatFormatting.AQUA))).withStyle(t->t.withColor(ChatFormatting.GRAY)));
            list.add(new TranslatableComponent("dmlreforged.hover_text.more_info").withStyle(t->t.withColor(ChatFormatting.GRAY)));
    	}
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
    	return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.AQUA));
    }
}