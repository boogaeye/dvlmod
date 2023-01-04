package faz.darkvlight.com.darkvslight.networking;

import com.mojang.logging.LogUtils;
import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import faz.darkvlight.com.darkvslight.screen.UpgradeMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FluidSyncS2CPacket {
    private final FluidStack fluidStack;
    private final BlockPos pos;

    public FluidSyncS2CPacket(FluidStack fluid, BlockPos p)
    {
        fluidStack = fluid;
        pos = p;
    }

    public FluidSyncS2CPacket(FriendlyByteBuf buf)
    {
        fluidStack = buf.readFluidStack();
        pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeFluidStack(fluidStack);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->
        {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof UpgradeBlockEntity be)
            {
                be.setFluid(fluidStack);

                if (Minecraft.getInstance().player.containerMenu instanceof UpgradeMenu menu && menu.getBlockEntity().getBlockPos().equals(pos))
                {
                    menu.setFluid(this.fluidStack);
                }
            }
        });
        return true;
    }
}
