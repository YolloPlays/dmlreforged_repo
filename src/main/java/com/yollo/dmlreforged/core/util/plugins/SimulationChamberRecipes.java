package com.yollo.dmlreforged.core.util.plugins;

import com.yollo.dmlreforged.core.util.DataModelHelper;

import net.minecraft.world.item.ItemStack;

public class SimulationChamberRecipes {

	private ItemStack pristine;
	private ItemStack data;
	private ItemStack matter;
	
	public SimulationChamberRecipes(ItemStack data) {
		this.data = data;
		this.pristine = DataModelHelper.getMobMetaData(data).getPristineMatterStack(1);
		this.matter = DataModelHelper.getMobMetaData(data).getLivingMatterStack(1);
	}

	public ItemStack getPristine() {
		return pristine;
	}

	public ItemStack getDataModel() {
		return data;
	}

	public ItemStack getMatter() {
		return matter;
	}

}
