package com.yollo.dmlreforged.client.screen;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.client.screen.buttons.SelectButton;
import com.yollo.dmlreforged.common.items.ItemPristineMatter;
import com.yollo.dmlreforged.common.items.init.PacketHandler;
import com.yollo.dmlreforged.common.network.ServerboundResultingItemPacket;
import com.yollo.dmlreforged.common.util.MathHelper;
import com.yollo.dmlreforged.common.util.Pagination;
import com.yollo.dmlreforged.common.util.container.ExtractionChamberContainer;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public class ExtractionChamberScreen extends AbstractContainerScreen<ExtractionChamberContainer>{
	
    private static final ResourceLocation base = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/extraction_chamber_base.png");
    private static final ResourceLocation extras = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/buttons/button_select.png");
    private static final ResourceLocation defaultGui = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/default_gui.png");
	boolean elementsAdded = false;
	public Pagination pageHandler = new Pagination(0, getLootFromPristine().size(), 9);
	private int startIndex;
	private NonNullList<SelectButton> buttons = NonNullList.create();
	private int selectedIndex;
	private boolean tileSelectData;
	
	public ExtractionChamberScreen(ExtractionChamberContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
	}

	@Override
	protected void renderBg(PoseStack pose, float pPartialTick, int pMouseX, int pMouseY) {
		int left = getGuiLeft();
		int top = getGuiTop();
		selectedIndex = this.menu.data.get(3);
		tileSelectData = this.menu.data.get(4)==1 ? true: false;
		
		// Render base
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, base);
		blit(pose, left , top , 0, 0, 176, 83);
		
		// Render current energy
        int energyBarHeight = MathHelper.ensureRange((int) ((float) this.menu.data.get(1) / (this.menu.data.get(2) - DeepMobLearning.rfCostExtractionChamber) * 53), 0, 53);
        int energyBarOffset = 53 - energyBarHeight;
        blit(pose, left + 6,  top + 10 + energyBarOffset, 0, 83, 7, energyBarHeight);
        
        // Render crafting progress
        int craftingBarHeight = (int) (((float) this.menu.data.get(0) / 50 * 36));
        int craftingBarOffset = 36 - craftingBarHeight;
        blit(pose, left + 84,  top + 22 + craftingBarOffset, 7, 83, 6, craftingBarHeight);
        
		// Render playerInv
		RenderSystem.setShaderTexture(0, defaultGui);
		blit(pose, left + 0, top + 111, 0, 0, 176, 90);
        
		
		NumberFormat f = NumberFormat.getNumberInstance(Locale.ENGLISH);
		
		final int progress = this.menu.data.get(0);
		final int energyStored = this.menu.data.get(1);
		final int maxEnergy = this.menu.data.get(2);
		
        int x = pMouseX - getGuiLeft();
        int y = pMouseY - getGuiTop();
        if(10 <= y && y < 63) {
            if(6 <= x && x < 13) {
                // Tooltip for energy
            	List<Component> tooltip = new ArrayList<>();
                tooltip.add(new TextComponent(f.format(energyStored) + "/" + f.format(maxEnergy) + " RF"));
                tooltip.add(new TextComponent("Operational cost: " + f.format(DeepMobLearning.rfCostExtractionChamber) + " RF/t"));
                renderComponentTooltip(pose, tooltip, pMouseX + 1, pMouseY - 11);
            }
        } 
        if (23 <= y && y < 58) {
        	if(84 <= x && x < 90) {
				List<Component> tooltip = new ArrayList<>();
	        	tooltip.add(new TextComponent(progress + "/" + 100));
	            renderComponentTooltip(pose, tooltip, pMouseX + 1, pMouseY - 11);
        	}
        }
        
        if(!getPristine().isEmpty()) {
    		renderLootList();
    		if(!elementsAdded) {
    			renderButtons();
        		this.elementsAdded = true;
    		}
        }
        else {
        	if(elementsAdded) {
        		buttons.forEach((Button btn) -> {
        			removeWidget(btn);
        		});
        		this.elementsAdded = false;
        	}
        }
	}

    private ItemStack getPristine() {
        return this.menu.getPristine();
    }
    
    private void renderButtons() {
        for(int i = this.startIndex ; i < getLootFromPristine().size(); i++) {
        	int l = i / 3 * 18;
        	int resultingIndex = i;
        	SelectButton btn;
        	System.out.println(getLootFromPristine().size());
        	if(selectedIndex == resultingIndex && tileSelectData) {
        		btn = new SelectButton(getGuiLeft()+ 16 + i % 3 * 19, 8 + getGuiTop() + l, 18, 18, 0, 0, 18, 18, true, extras, button -> {
            		if(!((SelectButton) button).isSelected()) {
            			PacketHandler.INSTANCE.sendToServer(new ServerboundResultingItemPacket(this.menu.pos, getItemFromList(resultingIndex), resultingIndex, true));
            			buttons.forEach((SelectButton buuton) -> {
            				buuton.setSelected(false);
            			});
            			((SelectButton) button).selection();
            		}
            		else if(((SelectButton) button).isSelected()){
            			PacketHandler.INSTANCE.sendToServer(new ServerboundResultingItemPacket(this.menu.pos, ItemStack.EMPTY, resultingIndex, false));
            			((SelectButton) button).selection();
            		}
            		
                });
        	}
        	else {
        		btn = new SelectButton(getGuiLeft()+ 16 + i % 3 * 19, 8 + getGuiTop() + l, 18, 18, 0, 0, 18, 18, extras, button -> {
            		if(!((SelectButton) button).isSelected()) {
            			PacketHandler.INSTANCE.sendToServer(new ServerboundResultingItemPacket(this.menu.pos, getItemFromList(resultingIndex), resultingIndex, true));
            			buttons.forEach((SelectButton buuton) -> {
            				buuton.setSelected(false);
            			});
            			((SelectButton) button).selection();
            		}
            		else if(((SelectButton) button).isSelected()){
            			PacketHandler.INSTANCE.sendToServer(new ServerboundResultingItemPacket(this.menu.pos, ItemStack.EMPTY, resultingIndex, false));
            			((SelectButton) button).selection();
            		}
            		
                });
        	}
        	buttons.add(btn);
            addRenderableWidget(btn);
         }
     }
    
    private void renderLootList() {
    	int index = 0;
    	for(int row = 0; row < 3; row++) {
    		for(int column = 0; column < 3; column++) {
    			renderItemStackWithCount(getItemFromList(index), 17 + getGuiLeft() + 19 * column, 8+ getGuiTop() + 18 * row, getItemFromList(index).getCount());
    			index++;
    		}
    	}
    }
    
    private ItemStack getItemFromList(int index) {
        if(index >= 0 && index < getLootFromPristine().size()) {
            return getLootFromPristine().get(index);
        } else {
            return ItemStack.EMPTY;
        }
    }
    
    private NonNullList<ItemStack> getLootFromPristine() {
        ItemStack stack = getPristine();

        if(stack.getItem() instanceof ItemPristineMatter pristine) {
            return getLootTable(pristine.getMobKey());
        } else {
           return NonNullList.create();
        }
    }
    
    private static NonNullList<ItemStack> getLootTable(String key) {
        NonNullList<ItemStack> list = NonNullList.create();

        List<? extends String> toParseList;
        toParseList = Arrays.asList(DeepMobLearning.getPristineLoot(key));

        for (String line : toParseList) {
            if (!getStackFromConfigLine(line).isEmpty()) {
                list.add(getStackFromConfigLine(line));
            }
        }

        return list;
    }
    
    private static ItemStack getStackFromConfigLine(String line) {
        String[] vals = line.split(",");

        if (vals.length < 2) {
            return ItemStack.EMPTY;
        }


        ResourceLocation itemLocation = new ResourceLocation(vals[0]);
        int amount;

        try {
            amount = Integer.parseInt(vals[1]);
        } catch (NumberFormatException e) {
            System.out.println("Not a valid number for amount");
            return ItemStack.EMPTY;
        }


        Item item = ForgeRegistries.ITEMS.getValue(itemLocation);
        if(item != null) {
            return new ItemStack(item, amount);
        } else {
            return ItemStack.EMPTY;
        }
    }
    
    private void renderItemStackWithCount(ItemStack stack, int x, int y, int count) {
    	getMinecraft().getItemRenderer().renderAndDecorateItem(stack, x, y);
    	getMinecraft().getItemRenderer().renderGuiItemDecorations(font, stack, x - 1, y - 1, count != 1 ? count + ""  : "");
    }
	
	@Override
	protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
	}

	@Override
	public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
		this.renderBackground(pPoseStack);
		super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
		this.renderTooltip(pPoseStack, pMouseX, pMouseY);
	}

}
