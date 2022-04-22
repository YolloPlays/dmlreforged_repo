package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.util.MathHelper;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemLivingMatter extends Item{
	
    private String type;

	public ItemLivingMatter(String type) {
		super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flag) {
		list.add(new TranslatableComponent("dmlreforged.living_matter.exp_consume", new TextComponent("(Right-Click)").withStyle(t -> t.withColor(ChatFormatting.WHITE))).withStyle(t -> t.withColor(ChatFormatting.GRAY)));
		list.add(new TranslatableComponent("dmlreforged.living_matter.exp_consume_stack", new TextComponent("SHIFT").withStyle(t -> t.withColor(ChatFormatting.WHITE))).withStyle(t -> t.withColor(ChatFormatting.GRAY)));
		list.add(new TranslatableComponent("dmlreforged.living_matter.exp", new TextComponent(Integer.toString(DeepMobLearning.getExp(getType()))).withStyle(t -> t.withColor(ChatFormatting.GREEN))));
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if(!world.isClientSide()) {
			int exp = MathHelper.ensureRange(DeepMobLearning.getExp(getType()), 1, 999);
			if(player.isCrouching()) {
				int size = player.getItemInHand(hand).getCount();
				player.getItemInHand(hand).shrink(size);
				player.giveExperiencePoints(exp * size);
			}
			else {
				player.getItemInHand(hand).shrink(1);
				player.giveExperiencePoints(exp);		
			}
		}
		
		return super.use(world, player, hand);
	}
	
	public static class Hellish extends ItemLivingMatter {
		
		@Override
		public Component getName(ItemStack p_41458_) {

			return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.DARK_RED));
		}
		
		public Hellish() {
			super("living_matter_hellish");
		}		
	}
	public static class Extraterrestrial extends ItemLivingMatter {
		
		@Override
		public Component getName(ItemStack p_41458_) {

			return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.LIGHT_PURPLE));
		}

		public Extraterrestrial() {
			super("living_matter_extraterrestrial");
		}		
	}
	public static class Overworldian extends ItemLivingMatter {
		
		@Override
		public Component getName(ItemStack p_41458_) {

			return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.GREEN));
		}

		public Overworldian() {
			super("living_matter_overworldian");
		}		
	}
	
	


}
