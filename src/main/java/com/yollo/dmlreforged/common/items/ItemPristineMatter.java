package com.yollo.dmlreforged.common.items;

import com.yollo.dmlreforged.DeepMobLearning;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemPristineMatter extends Item{
	
	private String mobKey;
	private ItemPristineMatter(String mobKey) {
        super(new Item.Properties().tab(DeepMobLearning.Deep_Mob_Learning_TAB));
        this.mobKey = mobKey;
    }

    public String getMobKey() {
        return mobKey;
    }
    
    @Override
    public boolean isFoil(ItemStack p_41453_) {
    	return true;
    }

    public static class Blaze extends ItemPristineMatter {
        public Blaze() {
            super("blaze");
        }
    }

    public static class Creeper extends ItemPristineMatter {
        public Creeper() {
            super("creeper");
        }
    }

    public static class Dragon extends ItemPristineMatter {
        public Dragon() {
            super("dragon");
        }
    }

    public static class Enderman extends ItemPristineMatter {
        public Enderman() {
            super("enderman");
        }
    }

    public static class Ghast extends ItemPristineMatter {
        public Ghast() {
            super("ghast");
        }
    }

    public static class Guardian extends ItemPristineMatter {
        public Guardian() {
            super("guardian");
        }
    }

    public static class Shulker extends ItemPristineMatter {
        public Shulker() {
            super("shulker");
        }
    }

    public static class Skeleton extends ItemPristineMatter {
        public Skeleton() {
            super("skeleton");
        }
    }

    public static class Slime extends ItemPristineMatter {
        public Slime() {
            super("slime");
        }
    }

    public static class Spider extends ItemPristineMatter {
        public Spider() {
            super("spider");
        }
    }

    public static class Witch extends ItemPristineMatter {
        public Witch() {
            super("witch");
        }
    }

    public static class Wither extends ItemPristineMatter {
        public Wither() {
            super("wither");
        }
    }

    public static class WitherSkeleton extends ItemPristineMatter {
        public WitherSkeleton() {
            super("witherskeleton");
        }
    }

    public static class Zombie extends ItemPristineMatter {
        public Zombie() {
            super("zombie");
        }
    }
}
