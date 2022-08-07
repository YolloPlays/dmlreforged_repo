package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemGlitchHeart extends Item{
    public ItemGlitchHeart() {
        super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
    }
    
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flagIn) {
    	list.add(Component.translatable("dmlreforged.hover_text.glitch_heart").withStyle(t->t.withColor(ChatFormatting.GRAY)));
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
    	return Component.translatable(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.AQUA));
    }
}
