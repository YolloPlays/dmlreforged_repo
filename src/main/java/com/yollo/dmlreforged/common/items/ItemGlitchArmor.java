package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.core.GlitchArmorMaterial;
import com.yollo.dmlreforged.common.mobmetas.MobMetaData;
import com.yollo.dmlreforged.common.util.DataModelHelper;

import io.netty.util.internal.ThreadLocalRandom;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemGlitchArmor extends ArmorItem{
	
	private static final int PRISTINE_SET_CHANCE = 16;
    private static final int PRISTINE_SET_NUMBER_OF_DROPS = 2;
	
	public ItemGlitchArmor(EquipmentSlot slot) {
		super(new GlitchArmorMaterial(), slot, new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
	}

	public static boolean isSetEquippedByPlayer(ServerPlayer player) {
		return player.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ItemGlitchArmor &&
	            player.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ItemGlitchArmor &&
	            player.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ItemGlitchArmor &&
	            player.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ItemGlitchArmor;
	}

	public static void dropPristineMatter(Level level, BlockPos blockPos, ItemStack stack, ServerPlayer player) {
		 if(ThreadLocalRandom.current().nextInt(1, 100) <= PRISTINE_SET_CHANCE) {
	            MobMetaData meta = DataModelHelper.getMobMetaData(stack);
	            ItemEntity drop = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), meta.getPristineMatterStack(PRISTINE_SET_NUMBER_OF_DROPS));
	            drop.setDefaultPickUpDelay();
	            level.addFreshEntity(drop);
	        }
	}
	
    @Override
    public Component getName(ItemStack p_41458_) {
    	return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.AQUA));
    }
    
    @Override
    public void appendHoverText(ItemStack p_41421_, Level p_41422_, List<Component> list, TooltipFlag p_41424_) {
        list.add(new TranslatableComponent("dmlreforged.hover_text.glitch_infused_armor_1"));
        list.add(new TranslatableComponent("dmlreforged.hover_text.glitch_infused_armor_2"));
        list.add(new TranslatableComponent("dmlreforged.hover_text.glitch_infused_armor_3", new TextComponent(Integer.toString(PRISTINE_SET_CHANCE)), new TextComponent(Integer.toString(PRISTINE_SET_NUMBER_OF_DROPS))).withStyle(t->t.withColor(ChatFormatting.GOLD)));
        list.add(new TranslatableComponent("dmlreforged.hover_text.glitch_infused_armor_4").withStyle(t->t.withColor(ChatFormatting.GOLD)));
        list.add(new TranslatableComponent("dmlreforged.hover_text.glitch_infused_armor_5").withStyle(t->t.withColor(ChatFormatting.GOLD)));
    }
	
	public static class ItemGlitchHelmet extends ItemGlitchArmor {
        public ItemGlitchHelmet() {
            super(EquipmentSlot.HEAD);
        }
    }

    public static class ItemGlitchChestplate extends ItemGlitchArmor {
        public ItemGlitchChestplate() {
            super(EquipmentSlot.CHEST);
        }
    }

    public static class ItemGlitchLeggings extends ItemGlitchArmor {
        public ItemGlitchLeggings() {
            super(EquipmentSlot.LEGS);
        }
    }

    public static class ItemGlitchBoots extends ItemGlitchArmor {
        public ItemGlitchBoots() {
            super(EquipmentSlot.FEET);
        }
    }

}
