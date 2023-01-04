package faz.darkvlight.com.darkvslight.plugins.betterf3;

import faz.darkvlight.com.darkvslight.client.PlayerDarkend;
import faz.darkvlight.com.darkvslight.client.PlayerDarkendProvider;
import me.cominixo.betterf3.modules.BaseModule;
import me.cominixo.betterf3.utils.DebugLine;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextColor;

public class DarkVsLightModule extends BaseModule
{
    public DarkVsLightModule()
    {
        this.defaultNameColor = TextColor.fromRgb(0xE6E6FA);
        this.defaultValueColor = TextColor.fromLegacyFormat(ChatFormatting.AQUA);
    }

    @Override
    public void update(Minecraft client)
    {
        client.player.getCapability(PlayerDarkendProvider.PLAYER_DARKEND).ifPresent(handle -> {
            lines.add(new DebugLine("darkend", String.valueOf(handle.getDark()), false));
                });

    }
}
