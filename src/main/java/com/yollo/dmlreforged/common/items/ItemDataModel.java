package com.yollo.dmlreforged.common.items;

import java.util.List;

import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.util.DataModelHelper;
import com.yollo.dmlreforged.common.util.DataModelLevelupHelper;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ItemDataModel extends Item{
	
	private String mobKey;

	public ItemDataModel(String mobKey) {
		super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB).stacksTo(1));
		this.mobKey = mobKey;
	}
	
	public String getMobKey() {
		return mobKey;
	}
	
	@Override
	public Component getName(ItemStack p_41458_) {
		return new TranslatableComponent(super.getName(p_41458_).getString()).withStyle(t -> t.withColor(ChatFormatting.AQUA));
	}
	
	
	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> list, TooltipFlag flagIn) {
		if(!Screen.hasShiftDown())
		{
			list.add(new TranslatableComponent("dmlreforged.holdshift", new TextComponent("SHIFT").withStyle(t -> t.withColor(ChatFormatting.WHITE).withItalic(true))).withStyle(t -> t.withColor(ChatFormatting.GRAY)));
		}
		else {
			list.add(new TranslatableComponent("dmlreforged.data_model.tier", DataModelHelper.getTierName(stack, false)));
			int tier = DataModelHelper.getTier(stack);
			if(tier != DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
				list.add(new TranslatableComponent("dmlreforged.data_model.data.collected", 
						new TextComponent(Integer.toString(DataModelHelper.getCurrentTierSimulationCountWithKills(stack))).withStyle(t -> t.withColor(ChatFormatting.GRAY)), 
						new TextComponent(Integer.toString(DataModelHelper.getTierRoof(stack))).withStyle(t -> t.withColor(ChatFormatting.GRAY))));
				list.add(new TranslatableComponent("dmlreforged.data_model.data.killmultiplier", 
						new TextComponent(Integer.toString(DataModelLevelupHelper.getKillMultiplier(DataModelHelper.getTier(stack)))).withStyle(t -> t.withColor(ChatFormatting.GRAY))));
			}
			list.add(new TranslatableComponent("dmlreforged.data_model.rfcost", new TextComponent(Integer.toString(DataModelHelper.getSimulationTickCost(stack))).withStyle(t -> t.withColor(ChatFormatting.GRAY))));
			if(DataModelHelper.getMatterTypeName(stack) == "living_matter_hellish") {
				list.add(new TranslatableComponent("dmlreforged.data_model.type_text", new TranslatableComponent("dmlreforged.living_matter.hellish").withStyle(t -> t.withColor(ChatFormatting.DARK_RED))));
			}
			if(DataModelHelper.getMatterTypeName(stack) == "living_matter_extraterrestrial") {
				list.add(new TranslatableComponent("dmlreforged.data_model.type_text", new TranslatableComponent("dmlreforged.living_matter.extraterrestrial").withStyle(t -> t.withColor(ChatFormatting.LIGHT_PURPLE))));
			}
			if (DataModelHelper.getMatterTypeName(stack) == "living_matter_overworldian"){
				list.add(new TranslatableComponent("dmlreforged.data_model.type_text", new TranslatableComponent("dmlreforged.living_matter.overworldian").withStyle(t -> t.withColor(ChatFormatting.GREEN))));
			}
			
		}
	}
		
    public static class Blaze extends ItemDataModel {
        public Blaze() {
            super("blaze");
        }

    }
	
	 public static class Creeper extends ItemDataModel {
	        public Creeper() {
	            super("creeper");
	        }

	    }

	    public static class Dragon extends ItemDataModel {
	        public Dragon() {
	            super("dragon");
	        }

	    }

	    public static class Enderman extends ItemDataModel {
	        public Enderman() {
	            super("enderman");
	        }

	    }

	    public static class Ghast extends ItemDataModel {
	        public Ghast() {
	            super("ghast");
	        }

	    }

	    public static class Guardian extends ItemDataModel {
	        public Guardian() {
	            super("guardian");
	        }

	    }

	    public static class Shulker extends ItemDataModel {
	        public Shulker() {
	            super("shulker");
	        }

	    }

	    public static class Skeleton extends ItemDataModel {
	        public Skeleton() {
	            super("skeleton");
	        }

	    }

	    public static class Slime extends ItemDataModel {
	        public Slime() {
	            super("slime");
	        }

	    }

	    public static class Spider extends ItemDataModel {
	        public Spider() {
	            super("spider");
	        }

	    }

	    public static class Witch extends ItemDataModel {
	        public Witch() {
	            super("witch");
	        }

	    }

	    public static class Wither extends ItemDataModel {
	        public Wither() {
	            super("wither");
	        }

	    }

	    public static class WitherSkeleton extends ItemDataModel {
	        public WitherSkeleton() {
	            super("witherskeleton");
	        }

	    }

	    public static class Zombie extends ItemDataModel {
	        public Zombie() {
	            super("zombie");
	        }


	    }
	    
	   //Tinker, Thermal and Twilight extension
	   /*public static class TE extends ItemDataModel {
	        public TE() {
	            super("data_model_thermal_elemental", "thermalelement");
	        }

	    }

	    public static class TwilightForest extends ItemDataModel {
	        public TwilightForest() {
	            super("data_model_twilight_forest", "twilightforest");
	        }

	    }

	    public static class TwilightSwamp extends ItemDataModel {
	        public TwilightSwamp() {
	            super("data_model_twilight_swamp", "twilightswamp");
	        }

	    }

	    public static class TwilightDarkwood extends ItemDataModel {
	        public TwilightDarkwood() {
	            super("data_model_twilight_darkwood", "twilightdarkwood");
	        }

	    }

	    public static class TwilightGlacier extends ItemDataModel {
	        public TwilightGlacier() {
	            super("data_model_twilight_glacier", "twilightglacier");
	        }

	    }

	    public static class TinkerSlime extends ItemDataModel {
	        public TinkerSlime() {
	            super("data_model_tinker_slime", "tinkerslime");
	        }

	    }
	*/
	
}	