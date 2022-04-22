package com.yollo.dmlreforged.common.events;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.items.ItemDeepLearner;
import com.yollo.dmlreforged.common.items.ItemGlitchArmor;
import com.yollo.dmlreforged.common.items.ItemGlitchSword;
import com.yollo.dmlreforged.common.mobmetas.MobMetaData;
import com.yollo.dmlreforged.common.util.DataModelHelper;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = DeepMobLearning.MOD_ID, bus = Bus.FORGE)
public class EntityDeathHandler {
	
	@SubscribeEvent
    public static void handlePlayerKilledEntity(LivingDeathEvent event) {
		
		if(event.getSource().getEntity() instanceof ServerPlayer player) {
	        NonNullList<ItemStack> inventory = NonNullList.create();
	        inventory.addAll(player.getInventory().items);
	        inventory.addAll(player.getInventory().offhand);
	
	        // Grab the deep learners and combat trial items from a players inventory
	        NonNullList<ItemStack> deepLearners = getDeepLearners(inventory);
	        NonNullList<ItemStack> updatedModels = NonNullList.create();
	
	        // Update every data model in every deeplearner that match the kill event
	        deepLearners.forEach(stack -> {
	            NonNullList<ItemStack> models = updateAndReturnModels(stack, event, player);
	            updatedModels.addAll(models);
	        });
	
	        // Return early if no models were affected
	        if(updatedModels.size() == 0) {
	            return;
	        }
	        
	        // Chance to drop pristine matter from the model that gained data
	        if(ItemGlitchArmor.isSetEquippedByPlayer(player)) {
                ItemGlitchArmor.dropPristineMatter(event.getEntityLiving().level, event.getEntityLiving().blockPosition(), updatedModels.get(0), player);
	        }
	
	        if(player.getMainHandItem().getItem() instanceof ItemGlitchSword) {
                ItemStack sword = player.getItemInHand(InteractionHand.MAIN_HAND);
                if(ItemGlitchSword.canIncreaseDamage(sword)) {
                    ItemGlitchSword.increaseDamage(sword, player);
                } 
	        }
	    }
	}
    
    private static NonNullList<ItemStack> updateAndReturnModels(ItemStack deepLearner, LivingDeathEvent event, ServerPlayer player) {
        NonNullList<ItemStack> deepLearnerItems = ItemDeepLearner.getContainedItems(deepLearner);
        NonNullList<ItemStack> result = NonNullList.create();

        deepLearnerItems.forEach(stack -> {
            if (stack.getItem() instanceof ItemDataModel) {
                MobMetaData meta = DataModelHelper.getMobMetaData(stack);
                if(meta.entityLivingMatchesMob(event.getEntityLiving())) {
                    DataModelHelper.increaseMobKillCount(stack, player);
                    if(player.getMainHandItem().getItem() instanceof ItemGlitchSword) {
                    	DataModelHelper.increaseMobKillCount(stack, player);
                    }
                    result.add(stack);
                }
            }
            ItemDeepLearner.setContainedItems(deepLearner, deepLearnerItems);
        });

        return result;
    }
    
    private static NonNullList<ItemStack> getDeepLearners(NonNullList<ItemStack> inventory) {
        NonNullList<ItemStack> result = NonNullList.create();
        inventory.forEach(stack -> {
            if(stack.getItem() instanceof ItemDeepLearner) {
                result.add(stack);
            }
        });

        return result;
    }
	
}
