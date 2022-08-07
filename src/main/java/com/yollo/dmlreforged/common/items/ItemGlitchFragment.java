package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.entity.ItemEntityGlitchFragment;
import com.yollo.dmlreforged.core.configs.BalanceConfigs;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemGlitchFragment extends Item {
    public ItemGlitchFragment() {
        super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flagIn) {
    	if(BalanceConfigs.isSootedRedstoneCraftingEnabled.get()) {
    		list.add(Component.translatable("dmlreforged.hover_text.glitch_fragment_1", Component.translatable("item.dmlreforged.glitch_heart").withStyle(t->t.withColor(ChatFormatting.AQUA))).withStyle(t->t.withColor(ChatFormatting.GRAY)));
            list.add(Component.translatable("dmlreforged.hover_text.glitch_fragment_2", Component.literal(new ItemStack(Items.OBSIDIAN).getHoverName().getString()).withStyle(t->t.withColor(ChatFormatting.DARK_PURPLE))).withStyle(t->t.withColor(ChatFormatting.GRAY)));
            list.add(Component.translatable("dmlreforged.hover_text.glitch_fragment_3").withStyle(t->t.withColor(ChatFormatting.GRAY)));
            list.add(Component.translatable("dmlreforged.hover_text.glitch_fragment_4").withStyle(t->t.withColor(ChatFormatting.GRAY)));
    	}
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
    	return Component.translatable(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.AQUA));
    }
    
    @Override
    public boolean hasCustomEntity(ItemStack stack) {
    	return true;
    }
   
    @Override
    public Entity createEntity(Level level, Entity location, ItemStack stack) {
        ItemEntity result = new ItemEntityGlitchFragment(level, location.position().x, location.position().y, location.position().z, new ItemStack(this, stack.getCount()), location.getDeltaMovement().x(), location.getDeltaMovement().y(), location.getDeltaMovement().z());
        return result;
    }
    
}
