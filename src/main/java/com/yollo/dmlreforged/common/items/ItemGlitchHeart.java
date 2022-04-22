package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.init.ItemInit;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class ItemGlitchHeart extends Item{
    public ItemGlitchHeart() {
        super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
    }
    
    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> list, TooltipFlag flagIn) {
    	list.add(new TranslatableComponent("dmlreforged.hover_text.glitch_heart").withStyle(t->t.withColor(ChatFormatting.GRAY)));
    }
    
    @Override
    public Component getName(ItemStack p_41458_) {
    	return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.AQUA));
    }
}
