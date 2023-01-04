package faz.darkvlight.com.darkvslight.networking;

import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import faz.darkvlight.com.darkvslight.screen.UpgradeMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MethodReasonSyncS2CPacket {
    private final String methodReason;
    private final BlockPos pos;

    public MethodReasonSyncS2CPacket(String methodReason, BlockPos p)
    {
        this.methodReason = methodReason;
        pos = p;
    }

    public MethodReasonSyncS2CPacket(FriendlyByteBuf buf)
    {
        methodReason = buf.readUtf();
        pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeUtf(methodReason);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->
        {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof UpgradeBlockEntity be)
            {
                be.setMethodReason(methodReason);

                if (Minecraft.getInstance().player.containerMenu instanceof UpgradeMenu menu && menu.getBlockEntity().getBlockPos().equals(pos))
                {
                    be.setMethodReason(methodReason);
                }
            }
        });
        return true;
    }
}
