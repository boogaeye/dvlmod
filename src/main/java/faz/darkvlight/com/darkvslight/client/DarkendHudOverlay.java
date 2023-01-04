package faz.darkvlight.com.darkvslight.client;

import com.mojang.blaze3d.systems.RenderSystem;
import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class DarkendHudOverlay {
    private static final ResourceLocation FILLED_DARKEND = new ResourceLocation(Darkvslight.MODID, "textures/darkingberry.png");

    private static final ResourceLocation EMPTY_DARKEND = new ResourceLocation(Darkvslight.MODID, "textures/darkingberrybg.png");

    public static final IGuiOverlay HUD_DARKEND = (((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_DARKEND);
        for (int i = 0; i < 10; i++){
            GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
        }
        RenderSystem.setShaderTexture(0, FILLED_DARKEND);
        for (int i = 0; i < ClientDarkendData.getPlayerDarkend() / 2; i++){
            GuiComponent.blit(poseStack, x - 94 + (i * 9), y - 54, 0, 0, 12, 12, 12, 12);
        }
    }));
}
