package com.yollo.dmlreforged.core.init;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.loot.GlitchFragmentModifier;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GlobalLootModifier {
	
	public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLM = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS.get(), DeepMobLearning.MOD_ID);
	
	public static final RegistryObject<GlitchFragmentModifier.Serializer> FRAGMENT_LOOT = GLM.register("glitch_fragment_all_entities", GlitchFragmentModifier.Serializer::new);
}
