package com.yollo.dmlreforged.common.loot;

import org.jetbrains.annotations.NotNull;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.items.ItemDeepLearner;
import com.yollo.dmlreforged.common.items.ItemGlitchArmor;
import com.yollo.dmlreforged.common.mobmetas.MobMetaData;
import com.yollo.dmlreforged.core.configs.BalanceConfigs;
import com.yollo.dmlreforged.core.init.ItemInit;
import com.yollo.dmlreforged.core.util.DataModelHelper;

import io.netty.util.internal.ThreadLocalRandom;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlitchFragmentModifier extends LootModifier{
	
	private final Item fragment;
	private final Item heart;
	private final int chanceFragment;
	private final float chanceHeart;
	private final boolean enabled;
	

	protected GlitchFragmentModifier(LootItemCondition[] conditionsIn, int chanceFragment, float chanceHeart,boolean enabled) {
		super(conditionsIn);
		this.fragment = ItemInit.GLITCH_FRAGMENT.get();
		this.heart = ItemInit.GLITCH_HEART.get();
		this.chanceFragment = chanceFragment;
		this.chanceHeart = chanceHeart;
		this.enabled = enabled;
		
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot,
			LootContext ctx) {
		if(enabled && ctx.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Enemy) {
	        
			if(ThreadLocalRandom.current().nextInt(1, 100) <= chanceFragment) {
				generatedLoot.add(new ItemStack(fragment, ThreadLocalRandom.current().nextInt(1, 3)));
			}
			if(ThreadLocalRandom.current().nextFloat(0, 100) <= chanceHeart) {
				generatedLoot.add(new ItemStack(heart, 1));
			}
			//Bow works
			if(ctx.getParamOrNull(LootContextParams.KILLER_ENTITY) instanceof ServerPlayer player) {
					NonNullList<ItemStack> inventory = NonNullList.create();
			        inventory.addAll(player.getInventory().items);
			        inventory.addAll(player.getInventory().offhand);
			
			        // Grab the deep learners and combat trial items from a players inventory
			        NonNullList<ItemStack> deepLearners = getDeepLearners(inventory);
			        NonNullList<ItemStack> updatedModels = NonNullList.create();
			
			        // Update every data model in every deeplearner that match the kill event
			        deepLearners.forEach(stack -> {
			            NonNullList<ItemStack> models = updateAndReturnModels(stack, (LivingEntity) ctx.getParamOrNull(LootContextParams.THIS_ENTITY), player);
			            updatedModels.addAll(models);
			        });
			        
			        // Return early if no models were affected
			        if(updatedModels.size() == 0) {
			            return generatedLoot;
			        }
			        
			        // Chance to drop pristine matter from the model that gained data
			        // Can be toggled in Configs	        
			        if(BalanceConfigs.isGlitchArmorExtraDropsEnabled.get() && ItemGlitchArmor.isSetEquippedByPlayer(player) && ThreadLocalRandom.current().nextInt(1, 100) <= 16) {
			        	MobMetaData meta = DataModelHelper.getMobMetaData(updatedModels.get(0));
		                generatedLoot.add(meta.getPristineMatterStack(2));
			        }
			}
		}
        return generatedLoot;
	}
	
    private static NonNullList<ItemStack> updateAndReturnModels(ItemStack deepLearner, LivingEntity entity, ServerPlayer player) {
        NonNullList<ItemStack> deepLearnerItems = ItemDeepLearner.getContainedItems(deepLearner);
        NonNullList<ItemStack> result = NonNullList.create();

        deepLearnerItems.forEach(stack -> {
            if (stack.getItem() instanceof ItemDataModel) {
                MobMetaData meta = DataModelHelper.getMobMetaData(stack);
                if(meta.entityLivingMatchesMob(entity)) {
                    DataModelHelper.increaseMobKillCount(stack, player);
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
    
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, DeepMobLearning.MOD_ID);
	
	public static final RegistryObject<Codec<GlitchFragmentModifier>> glitchCodec = GLM.register("glitch_codec",() -> 
		RecordCodecBuilder.create(
			inst -> LootModifier.codecStart(inst).and(
				      inst.group(
				    	Codec.INT.fieldOf("chanceFragment").forGetter(m -> m.chanceFragment),
				    	Codec.FLOAT.fieldOf("chanceHeart").forGetter(m -> m.chanceHeart),
				    	Codec.BOOL.fieldOf("enabled").forGetter(m -> m.enabled)
				    	)
				
		).apply(inst, GlitchFragmentModifier::new)));
	
	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return glitchCodec.get();
	}
}
