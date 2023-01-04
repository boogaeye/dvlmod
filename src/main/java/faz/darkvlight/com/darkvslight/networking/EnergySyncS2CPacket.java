package faz.darkvlight.com.darkvslight.networking;

import com.mojang.logging.LogUtils;
import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import faz.darkvlight.com.darkvslight.client.ClientDarkendData;
import faz.darkvlight.com.darkvslight.screen.UpgradeMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySyncS2CPacket {
    private final int energy;
    private final BlockPos pos;

    public EnergySyncS2CPacket(int ener, BlockPos p)
    {
        energy = ener;
        pos = p;
    }

    public EnergySyncS2CPacket(FriendlyByteBuf buf)
    {
        energy = buf.readInt();
        pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->
        {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof UpgradeBlockEntity be)
            {
                be.setEnergyLevel(energy);

                if (Minecraft.getInstance().player.containerMenu instanceof UpgradeMenu menu && menu.getBlockEntity().getBlockPos().equals(pos))
                {
                    be.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }
}
