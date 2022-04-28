package com.yollo.dmlreforged.client.screen;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.yollo.dmlreforged.DeepMobLearning;
import com.yollo.dmlreforged.common.items.ItemDataModel;
import com.yollo.dmlreforged.common.mobmetas.MobMetaData;
import com.yollo.dmlreforged.common.util.Animation;
import com.yollo.dmlreforged.common.util.DataModelHelper;
import com.yollo.dmlreforged.common.util.MathHelper;
import com.yollo.dmlreforged.common.util.container.SimulationChamberContainer;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SimulationChamberScreen extends AbstractContainerScreen<SimulationChamberContainer> {
	
	private HashMap<String, Animation> animationList;
    private HashMap<String, String> simulationText = new HashMap<>();
	private Level level;
	private ItemStack currentDataModel = ItemStack.EMPTY;
	private static final ResourceLocation base = new ResourceLocation(DeepMobLearning.MOD_ID,
			"textures/gui/simulation_chamber_base.png");
	private static final ResourceLocation defaultGui = new ResourceLocation(DeepMobLearning.MOD_ID,
			"textures/gui/default_gui.png");

	public SimulationChamberScreen(SimulationChamberContainer container, Inventory playerInv, Component component) {
		super(container, playerInv, component);
		
		this.animationList = new HashMap<>();
		this.level = playerInv.player.level;
	}

	@Override
	protected void renderBg(PoseStack pose, float pPartialTick, int pMouseX, int pMouseY) {
		int left = getGuiLeft();
		int top = getGuiTop();
        int topStart = top - 39;
		int spacing = 12;
		MobMetaData data = DataModelHelper.getMobMetaData(getMenu().getDataModel());

		// Render base
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		RenderSystem.setShaderTexture(0, base);
		blit(pose, left - 20, top - 36, 0, 0, 216, 141);

		// Render data model slot
		blit(pose, left - 41, top - 36, 0, 141, 18, 18);
		
		// Render current energy
        int energyBarHeight = MathHelper.ensureRange((int) ((float) this.menu.data.get(1) / (this.menu.data.get(2) - data.getSimulationTickCost()) * 87), 0, 87);
        int energyBarOffset = 87 - energyBarHeight;
        blit(pose, left + 183,  top + 12 + energyBarOffset, 25, 141, 7, energyBarHeight);
       
        // Render current data
        if (DataModelHelper
				.getTier(getMenu().getDataModel()) != DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
			int dataBarHeight = MathHelper.ensureRange((int) ((float) DataModelHelper.getCurrentTierSimulationCountWithKills(getMenu().getDataModel()) / DataModelHelper.getTierRoof(getMenu().getDataModel()) * 87), 0, 87);
			int dataBarOffset = 87 - dataBarHeight;
			blit(pose, left-14,  top + 12 + dataBarOffset, 18, 141, 7, dataBarHeight);
		} else {
			blit(pose, left-14,  top+12, 18, 141, 7, 87);
		}
        
         
		// Render playerInv
		RenderSystem.setShaderTexture(0, defaultGui);
		blit(pose, left + 0, top + 111, 0, 0, 176, 90);
		
		if(dataModelChanged()) {
            resetAnimations();
        }


		NumberFormat f = NumberFormat.getNumberInstance(Locale.ENGLISH);
		List<Component> tooltip = new ArrayList<>();
		final int energyStored = this.menu.data.get(1);
		final int maxEnergy = this.menu.data.get(2);
		
		int x = pMouseX - getGuiLeft();
        int y = pMouseY - getGuiTop();

        if(11 <= y && y < 100) {
            if(-15 <= x && x < -6) {
				// Tooltip for data model data bar
				if (!getMenu().getDataModel().isEmpty()) {
					if (DataModelHelper
							.getTier(getMenu().getDataModel()) != DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
						tooltip.add(new TextComponent(DataModelHelper
								.getCurrentTierSimulationCountWithKills(getMenu().getDataModel()) + "/"
								+ DataModelHelper.getTierRoof(getMenu().getDataModel())
								+ " Data collected"));
					} else {
						tooltip.add(new TextComponent("This data model has reached the max tier."));
					}
				} else {
					tooltip.add(new TextComponent("Machine is missing a data model"));
				}
				renderComponentTooltip(pose, tooltip, pMouseX + 2, pMouseY + 2);
			} else if(182 <= x && x < 191) {
				// Tooltip for energy
				tooltip.add(new TextComponent(f.format(energyStored) + "/" + f.format(maxEnergy) + " RF"));
				if (!getMenu().getDataModel().isEmpty()) {
					MobMetaData meta = DataModelHelper.getMobMetaData(getMenu().getDataModel());
					tooltip.add(new TextComponent("Simulations with current data model drains "
							+ f.format(meta.getSimulationTickCost()) + "RF/t"));
				}
				renderComponentTooltip(pose, tooltip, pMouseX - 90, pMouseY - 16);
			}
		}
		
		//Animate Strings
		String[] lines;

		int leftTopConsole = left - 11;
		if(!(getMenu().getDataModel().getItem() instanceof ItemDataModel)) {
            lines = new String[] {"Please insert a data model", "to begin the simulation"};

            Animation a1 = getAnimation("pleaseInsert1");
            Animation a2 = getAnimation("pleaseInsert2");

            animateString(pose, lines[0], a1, null, 1, false, leftTopConsole , topStart + spacing, 0xFFFFFF);
            animateString(pose, lines[1], a2, a1, 1, false, leftTopConsole, topStart + (spacing * 2), 0xFFFFFF);

        } else if(DataModelHelper.getTier(getMenu().getDataModel()) == 0) {

            lines = new String[] {"Insufficient data in model", "please insert a basic model", "or better "};

            Animation insufData = getAnimation("insufData1");
            Animation insufData2 = getAnimation("insufData2");
            Animation insufData3 = getAnimation("insufData3");

            animateString(pose, lines[0], insufData, null, 1, false, leftTopConsole, topStart + spacing, 0xFFFFFF);
            animateString(pose, lines[1], insufData2, insufData, 1, false,  leftTopConsole, topStart + (spacing * 2), 0xFFFFFF);
            animateString(pose, lines[2], insufData3, insufData2, 1, false,  leftTopConsole, topStart + (spacing * 3), 0xFFFFFF);

        } else {
            // Draw current data model data
            if(DataModelHelper.getTier(getMenu().getDataModel()) == DeepMobLearning.DATA_MODEL_MAXIMUM_TIER) {
                blit(pose, leftTopConsole + 6,  top + 48, 18, 141, 7, 87);
            } else {
                int collectedData = DataModelHelper.getCurrentTierSimulationCountWithKills(getMenu().getDataModel());
                int tierRoof = DataModelHelper.getTierRoof(getMenu().getDataModel());

                int experienceBarHeight = (int) (((float) collectedData / tierRoof * 87));
                int experienceBarOffset = 87 - experienceBarHeight;
                blit(pose, leftTopConsole + 6,  top + 48 + experienceBarOffset, 18, 141, 7, experienceBarHeight);
            }

            drawString(pose, font, new TranslatableComponent("dmlreforged.tiers.tier", DataModelHelper.getTierName(getMenu().getDataModel(), false)), leftTopConsole, topStart + spacing, 0xFFFFFF);
            drawString(pose, font, "Iterations: " + f.format(DataModelHelper.getTotalSimulationCount(getMenu().getDataModel())), leftTopConsole, topStart + spacing * 2, 0xFFFFFF);
            drawString(pose, font, "Pristine chance: " + DataModelHelper.getPristineChance(getMenu().getDataModel()) + "%", leftTopConsole, topStart + spacing * 3, 0xFFFFFF);
        }
		
		drawConsoleText(pose, spacing);

	}

    private void drawConsoleText(PoseStack pose, int spacing) {
        String[] lines;
        int leftStart = getGuiLeft();
        int topStart = getGuiTop()+14;
        if(getMenu().getDataModel().isEmpty() || DataModelHelper.getTier(getMenu().getDataModel()) == 0) {
            animateString(pose, "_", getAnimation("blinkingUnderline"), null, 16, true, leftStart, topStart, 0xFFFFFF);
        } else if(getMenu().getPolymerClay().isEmpty() /*&& !tile.isCrafting()*/) {
            lines = new String[] {"Cannot begin simulation", "Missing polymer medium", "_"};
            Animation a1 = getAnimation("inputSlotEmpty1");
            Animation a2 = getAnimation("inputSlotEmpty2");
            Animation a3 = getAnimation("blinkingUnderline1");

            animateString(pose, lines[0], a1, null, 1, false, leftStart, topStart, 0xFFFFFF);
            animateString(pose, lines[1], a2, a1, 1, false, leftStart, topStart + spacing, 0xFFFFFF);
            animateString(pose, lines[2], a3, a2, 16, true, leftStart, topStart + (spacing * 2), 0xFFFFFF);

        } else if(getMenu().data.get(1) < (300 * DataModelHelper.getSimulationTickCost(getMenu().getDataModel())) /* && !tile.isCrafting()*/) {
            lines = new String[] {"Cannot begin simulation", "System energy levels critical", "_"};
            Animation a1 = getAnimation("lowEnergy1");
            Animation a2 = getAnimation("lowEnergy2");
            Animation a3 = getAnimation("blinkingUnderline2");

            animateString(pose, lines[0], a1, null, 1, false, leftStart, topStart, 0xFFFFFF);
            animateString(pose, lines[1], a2, a1, 1, false, leftStart, topStart + spacing, 0xFFFFFF);
            animateString(pose, lines[2], a3, a2, 16, true, leftStart, topStart + (spacing * 2), 0xFFFFFF);
        } else if(getMenu().outputIsFull() || getMenu().pristineIsFull()) {
            lines = new String[] {"Cannot begin simulation", "Output or pristine buffer is full", "_"};
            Animation a1 = getAnimation("outputSlotFilled1");
            Animation a2 = getAnimation("outputSlotFilled2");
            Animation a3 = getAnimation("blinkingUnderline3");

            animateString(pose, lines[0], a1, null, 1, false, leftStart, topStart, 0xFFFFFF);
            animateString(pose, lines[1], a2, a1, 1, false, leftStart, topStart + spacing, 0xFFFFFF);
            animateString(pose, lines[2], a3, a2, 16, true, leftStart, topStart + (spacing * 2), 0xFFFFFF);
        } else /*if(tile.isCrafting())*/ {
            updateSimulationText(getMenu().getDataModel(), this.menu.data.get(3));
            
            drawString(pose, font, getMenu().data.get(0) + "%", leftStart+158, 140, 0x55FFFF);

            drawString(pose, font, getSimulationText("simulationProgressLine1"), leftStart, topStart, 0xFFFFFF);
            drawString(pose, font, getSimulationText("simulationProgressLine1Version"), leftStart + 102, topStart, 0xFFFFFF);

            drawString(pose, font, getSimulationText("simulationProgressLine2"), leftStart, topStart + spacing, 0xFFFFFF);

            drawString(pose, font, getSimulationText("simulationProgressLine3"), leftStart, topStart + (spacing * 2), 0xFFFFFF);
            drawString(pose, font, getSimulationText("simulationProgressLine4"), leftStart, topStart + (spacing * 3), 0xFFFFFF);
            drawString(pose, font, getSimulationText("simulationProgressLine5"), leftStart, topStart + (spacing * 4), 0xFFFFFF);

            drawString(pose, font, getSimulationText("simulationProgressLine6"), leftStart, topStart + (spacing * 5), 0xFFFFFF);
            drawString(pose, font, getSimulationText("simulationProgressLine6Result"), leftStart + 118, topStart + (spacing * 5), 0xFFFFFF);

            drawString(pose, font, getSimulationText("simulationProgressLine7"), leftStart, topStart + (spacing * 6), 0xFFFFFF);
            drawString(pose, font, getSimulationText("blinkingDots1"), getGuiLeft() + 109, topStart + (spacing * 6), 0xFFFFFF);
            
        } /*else {
            animateString(pose, "_", getAnimation("blinkingUnderline"), null, 16, true, leftStart, top + 49, 0xFFFFFF);
        }*/
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
	
    private void resetAnimations() {
        this.animationList = new HashMap<>();
    }
    
	private boolean dataModelChanged() {
		if(ItemStack.matches(currentDataModel, getMenu().getDataModel())) {
            return false;
        } else {
            this.currentDataModel = getMenu().getDataModel();
            return true;
        }
	}

    private Animation getAnimation(String key) {
        if(animationList.containsKey(key)) {
            return animationList.get(key);
        } else {
            animationList.put(key, new Animation());
            return animationList.get(key);
        }
    }
	
    private void animateString(PoseStack pose, String string, Animation anim, Animation precedingAnim, int delay, boolean loop, int left, int top, int color) {
        if(precedingAnim != null) {
            if (precedingAnim.hasFinished()) {
                String result = anim.animate(string, delay, level.getGameTime(), loop);
                drawString(pose, font, result, left, top, color);
            } else {
                return;
            }
        }
        String result = anim.animate(string, delay, level.getGameTime(), loop);
        drawString(pose, font, result, left, top, color);
    }
    
    public String getSimulationText(String key) {
        if(simulationText.containsKey(key)) {
            return simulationText.get(key);
        } else {
            simulationText.put(key, "");
            return simulationText.get(key);
        }
    }
    
    private String animate(String string, Animation anim, @Nullable Animation precedingAnim, int delayInTicks, boolean loop, Level world) {
        if(precedingAnim != null) {
            if (precedingAnim.hasFinished()) {
                return anim.animate(string, delayInTicks, world.getGameTime(), loop);
            } else {
                return "";
            }
        }
        return  anim.animate(string, delayInTicks, world.getGameTime(), loop);
    }
    
    private void updateSimulationText(ItemStack stack, int succesInt) {
		boolean byproductSuccess = succesInt == 1;
		String[] lines = new String[] {
                "> Launching runtime",
                "v1.4.7",
                "> Iteration #" + (DataModelHelper.getTotalSimulationCount(stack) + 1) + " started",
                "> Loading model from chip memory",
                "> Assessing threat level",
                "> Engaged enemy",
                "> Pristine procurement",
                byproductSuccess ? "succeeded" : "failed",
                "> Processing results",
                "..."
        };

        String resultPrefix = byproductSuccess ? "§a" : "§c";

        Animation aLine1 = getAnimation("simulationProgressLine1");
        Animation aLine1Version = getAnimation("simulationProgressLine1Version");

        Animation aLine2 = getAnimation("simulationProgressLine2");

        Animation aLine3 = getAnimation("simulationProgressLine3");
        Animation aLine4 = getAnimation("simulationProgressLine4");
        Animation aLine5 = getAnimation("simulationProgressLine5");

        Animation aLine6 = getAnimation("simulationProgressLine6");
        Animation aLine6Result = getAnimation("simulationProgressLine6Result");

        Animation aLine7 = getAnimation("simulationProgressLine7");
        Animation aLine8 = getAnimation("blinkingDots1");

        simulationText.put("simulationProgressLine1", animate(lines[0], aLine1, null, 1, false, level));
        simulationText.put("simulationProgressLine1Version", "§6" + animate(lines[1], aLine1Version, aLine1, 1, false, level));

        simulationText.put("simulationProgressLine2", animate(lines[2], aLine2, aLine1Version, 1, false, level));

        simulationText.put("simulationProgressLine3", animate(lines[3], aLine3, aLine2, 2, false, level));
        simulationText.put("simulationProgressLine4", animate(lines[4], aLine4, aLine3, 1, false, level));
        simulationText.put("simulationProgressLine5", animate(lines[5], aLine5, aLine4, 2, false, level));

        simulationText.put("simulationProgressLine6", animate(lines[6], aLine6, aLine5, 2, false, level));
        simulationText.put("simulationProgressLine6Result", resultPrefix + animate(lines[7], aLine6Result, aLine6, 2, false, level));

        simulationText.put("simulationProgressLine7", animate(lines[8], aLine7, aLine6Result, 1, false, level));
        simulationText.put("blinkingDots1", animate(lines[9], aLine8, aLine7, 8, true, level));
    }
}
