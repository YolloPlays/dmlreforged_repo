package com.yollo.dmlreforged.common.loot;

import java.util.List;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class GlitchFragmentModifier extends LootModifier{
	
	private final Item addition;

	protected GlitchFragmentModifier(LootItemCondition[] conditionsIn, Item additon) {
		super(conditionsIn);
		this.addition = additon;
		
	}

	@Override
	protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		generatedLoot.add(new ItemStack(addition, 1));
        return generatedLoot;
	}
	
	  public static class Serializer extends GlobalLootModifierSerializer<GlitchFragmentModifier> {

		    @Override
		    public GlitchFragmentModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions) {
		      Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "addition"))));
		      return new GlitchFragmentModifier(conditions, addition);
		    }

		    @Override
		    public JsonObject write(GlitchFragmentModifier instance) {
		      JsonObject json = makeConditions(instance.conditions);
		      json.addProperty("replacement", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
		      return json;
		    }
		  }
}
