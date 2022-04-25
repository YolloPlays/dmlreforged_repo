package com.yollo.dmlreforged.client.screen;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.util.MathHelper;
import com.yollo.dmlreforged.common.util.container.ExtractionChamberContainer;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.Level;

public class ExtractionChamberScreen extends AbstractContainerScreen<ExtractionChamberContainer>{
	
	private Level level;
    private static final ResourceLocation base = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/extraction_chamber_base.png");
    private static final ResourceLocation defaultGui = new ResourceLocation(DeepMobLearning.MOD_ID, "textures/gui/default_gui.png");

	public ExtractionChamberScreen(ExtractionChamberContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
		super(pMenu, pPlayerInventory, pTitle);
		this.level = pPlayerInventory.player.level;
	}

	@Override
	protected void renderBg(PoseStack pose, float pPartialTick, int pMouseX, int pMouseY) {
		int left = getGuiLeft();
		int top = getGuiTop();
		
		// Render base
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, base);
		blit(pose, left - 20, top - 36, 0, 0, 176, 83);
		
		// Render current energy
        int energyBarHeight = MathHelper.ensureRange((int) ((float) this.menu.data.get(1) / (this.menu.data.get(2) - DeepMobLearning.rfCostExtractionChamber) * 53), 0, 53);
        int energyBarOffset = 53 - energyBarHeight;
        blit(pose, left + 183,  top + 12 + energyBarOffset, 0, 83, 7, energyBarHeight);
        
        // Render crafting progress
        int craftingBarHeight = (int) (((float) this.menu.data.get(0) / 50 * 36));
        int craftingBarOffset = 36 - craftingBarHeight;
        blit(pose, left + 84,  top + 22 + craftingBarOffset, 7, 83, 6, craftingBarHeight);
        
		// Render playerInv
		RenderSystem.setShaderTexture(0, defaultGui);
		blit(pose, left + 0, top + 111, 0, 0, 176, 90);
        
	}

}
