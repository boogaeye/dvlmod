package faz.darkvlight.com.darkvslight.client;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerDarkendProvider implements ICapabilityProvider, INBTSerializable<CompoundTag>
{
    public static Capability<PlayerDarkend> PLAYER_DARKEND = CapabilityManager.get(new CapabilityToken<PlayerDarkend>() { });

    private PlayerDarkend darkend = null;
    private final LazyOptional<PlayerDarkend> darkendLazyOptional = LazyOptional.of(this::createPlayerDarkend);

    private PlayerDarkend createPlayerDarkend()
    {
        if (this.darkend == null)
            this.darkend = new PlayerDarkend();
        return this.darkend;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_DARKEND)
            return darkendLazyOptional.cast();
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerDarkend().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerDarkend().loadNBTData(nbt);
    }
}
