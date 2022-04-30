package com.yollo.dmlreforged.common.events;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemGlitchSword;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = DeepMobLearning.MOD_ID, bus = Bus.FORGE)
public class EntityDeathHandler {

	@SubscribeEvent
    public static void handlePlayerKilledEntity(LivingDeathEvent event) {
		if(event.getSource().getEntity() instanceof ServerPlayer player) {
			if(player.getMainHandItem().getItem() instanceof ItemGlitchSword) {
			    ItemStack sword = player.getItemInHand(InteractionHand.MAIN_HAND);
			    if(ItemGlitchSword.canIncreaseDamage(sword)) {
			        ItemGlitchSword.increaseDamage(sword, player);
			    } 
			}
	    }
	}	
}
