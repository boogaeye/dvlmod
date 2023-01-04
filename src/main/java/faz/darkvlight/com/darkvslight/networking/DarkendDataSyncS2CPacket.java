package faz.darkvlight.com.darkvslight.networking;

import faz.darkvlight.com.darkvslight.client.ClientDarkendData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class DarkendDataSyncS2CPacket {
    private final int darkend;

    public DarkendDataSyncS2CPacket(int dark)
    {
        darkend = dark;
    }

    public DarkendDataSyncS2CPacket(FriendlyByteBuf buf)
    {
        darkend = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf)
    {
        buf.writeInt(darkend);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier)
    {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() ->
        {

            ClientDarkendData.set(darkend);
        });
        return true;
    }
}
