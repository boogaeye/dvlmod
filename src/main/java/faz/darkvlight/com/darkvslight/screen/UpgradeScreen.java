package faz.darkvlight.com.darkvslight.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.logging.LogUtils;
import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.recipe.UpgradeBlockRecipe;
import faz.darkvlight.com.darkvslight.screen.renderer.EnergyInfoArea;
import faz.darkvlight.com.darkvslight.screen.renderer.FluidTankRenderer;
import faz.darkvlight.com.darkvslight.util.MouseUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class UpgradeScreen extends AbstractContainerScreen<UpgradeMenu>
{

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Darkvslight.MODID,"textures/gui/gem_infusing_station_gui.png");

    private EnergyInfoArea energyInfoArea;
    private FluidTankRenderer fluidTankRenderer;

    public UpgradeScreen(UpgradeMenu p_97741_, Inventory p_97742_, Component p_97743_) {
        super(p_97741_, p_97742_, p_97743_);
    }

    @Override
    public void init()
    {
        super.init();
        assignEnergyInfoArea();
        assignFluidRender();
    }

    private void assignFluidRender()
    {
        fluidTankRenderer = new FluidTankRenderer(64000, true, 16, 61);
    }

    private void assignEnergyInfoArea()
    {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        energyInfoArea = new EnergyInfoArea(x + 156, y + 13, menu.upgradeBlockEntity.getEnergyStorage());
    }

    @Override
    protected void renderLabels(PoseStack pPoseStack, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        renderEnergyAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
        renderFluidAreaTooltips(pPoseStack, pMouseX, pMouseY, x, y);
    }

    private void renderFluidAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y)
    {
        if (IsMouseAboveArea(pMouseX, pMouseY, x, y, 16, 16))
        {
            renderTooltip(pPoseStack, fluidTankRenderer.getTooltip(menu.getFluidStack(), TooltipFlag.Default.NORMAL), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private boolean IsMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int i, int i1)
    {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + i, y + i1, fluidTankRenderer.getWidth(), fluidTankRenderer.getHeight());
    }

    private void renderEnergyAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y)
    {
        if (IsMouseAboveArea(pMouseX, pMouseY, x, y, 156, 13, 8, 64))
        {
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
        }
    }

    private boolean IsMouseAboveArea(int pMouseX, int pMouseY, int x, int y, int i, int i1, int i2, int i3)
    {
        return MouseUtil.isMouseOver(pMouseX, pMouseY, x + i, y + i1, i2, i3);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
        renderProgressArrow(pPoseStack, x, y);
        energyInfoArea.draw(pPoseStack);
        fluidTankRenderer.render(pPoseStack, x + 16, y + 16, menu.getFluidStack());
        if (menu.isCrafting())
        {
            Minecraft.getInstance().font.draw(pPoseStack, Component.translatable(menu.upgradeBlockEntity.getMethodReason()), x + 65, y + 40, 0xFFFF00);
        }
        else
            Minecraft.getInstance().font.draw(pPoseStack, Component.translatable("darkvslight.upgrade_block.idle"), x + 65, y + 40, 0xFFFFFF);
    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 103, y + 33, 176, 0, 8, menu.getScaledProgress());
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
