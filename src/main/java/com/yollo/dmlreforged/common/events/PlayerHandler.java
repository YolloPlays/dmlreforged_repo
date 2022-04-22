package com.yollo.dmlreforged.common.events;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemGlitchArmor;
import com.yollo.dmlreforged.common.items.ItemGlitchHeart;
import com.yollo.dmlreforged.common.items.init.ItemInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = DeepMobLearning.MOD_ID, bus = Bus.FORGE)
public class PlayerHandler {
	
    @SubscribeEvent
    public static void playerTickUpdate(LivingEquipmentChangeEvent event) {
    	if(event.getEntity() instanceof Player player) {
	    	Abilities cap = player.getAbilities();
	        if(!player.level.isClientSide) {
	            if(!cap.mayfly && ItemGlitchArmor.isSetEquippedByPlayer((ServerPlayer) player)) {
	                cap.mayfly = true;
	                player.onUpdateAbilities();
	            }
	
	            if(!ItemGlitchArmor.isSetEquippedByPlayer((ServerPlayer) player) && cap.mayfly && !player.isSpectator() && !player.isCreative()) {
                    cap.mayfly = false;
                    cap.flying = false;
                    player.onUpdateAbilities();
	            }
	        }
	    }
	}
    
    @SubscribeEvent
    public static void playerLogIn(PlayerLoggedInEvent event) {
    	if(event.getEntity() instanceof Player player) {
	    	Abilities cap = player.getAbilities();
	        if(!player.level.isClientSide) {
	            if(!cap.mayfly && ItemGlitchArmor.isSetEquippedByPlayer((ServerPlayer) player)) {
	                cap.mayfly = true;
	                player.onUpdateAbilities();
	            }
	
	            if(!ItemGlitchArmor.isSetEquippedByPlayer((ServerPlayer) player) && cap.mayfly && !player.isSpectator() && !player.isCreative()) {
                    cap.mayfly = false;
                    cap.flying = false;
                    player.onUpdateAbilities();
	            }
	        }
	    }
    }
    
    @SubscribeEvent
    public static void playerLeftClickedBlock(PlayerInteractEvent.LeftClickBlock event) {
    	if(event.getItemStack().getItem() instanceof ItemGlitchHeart) {
    		if(event.getPlayer().getLevel().getBlockState(event.getPos()).getBlock() == Blocks.OBSIDIAN) {
	 			BlockPos blockPos = event.getPos();
	 			ItemEntity drop = new ItemEntity(event.getPlayer().getLevel(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(ItemInit.GLITCH_FRAGMENT.get(), 3));
	 			drop.setDefaultPickUpDelay();
	 			event.getPlayer().getLevel().addFreshEntity(drop);
	 			event.getItemStack().shrink(1);
	 			event.getPlayer().getLevel().addParticle(ParticleTypes.POOF, (double) blockPos.getX()+ 0.5d,(double) blockPos.getY()+1,(double) blockPos.getZ()+0.5d, 0d, 0.03d, 0d);
	 			event.getPlayer().getLevel().playSound(null, blockPos, SoundEvents.ANCIENT_DEBRIS_BREAK, SoundSource.NEUTRAL, 1f, 1.1f);
    		}
    	}
    	else if(event.getItemStack().getItem() == Items.REDSTONE) {
    		if(event.getPlayer().getLevel().getBlockState(event.getPos()).getBlock() == Blocks.COAL_BLOCK) {
 			BlockPos blockPos = event.getPos();
 			ItemEntity drop = new ItemEntity(event.getPlayer().getLevel(), blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(ItemInit.SOOT_COVERED_REDSTONE.get(), 1));
 			drop.setDefaultPickUpDelay();
 			event.getPlayer().getLevel().addFreshEntity(drop);
 			event.getItemStack().shrink(1);
 			event.getPlayer().getLevel().addParticle(ParticleTypes.FLAME, (double) blockPos.getX()+ 0.5d,(double) blockPos.getY()+1,(double) blockPos.getZ()+0.5d, 0d, 0.03d, 0d);
 			event.getPlayer().getLevel().playSound(null, blockPos, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.NEUTRAL, 1f, 0.1f);
 			
    		}
		}
	}
}
