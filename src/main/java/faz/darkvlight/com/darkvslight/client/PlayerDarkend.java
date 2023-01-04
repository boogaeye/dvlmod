package faz.darkvlight.com.darkvslight.client;

import net.minecraft.nbt.CompoundTag;

public class PlayerDarkend {
    private int darkend;
    private final int MIN_DARK = 0;
    private final int MAX_DARK = 20;

    public int getDark()
    {
        return darkend;
    }

    public void addDarkness(int add)
    {
        darkend = Math.min(darkend + add, MAX_DARK);
    }

    public void subDarkness(int sub)
    {
        darkend = Math.max(darkend - sub, MIN_DARK);
    }

    public void copyFrom(PlayerDarkend source)
    {
        darkend = source.darkend;
    }

    public void saveNBTData(CompoundTag nbt)
    {
        nbt.putInt("darkend", darkend);
    }

    public void loadNBTData(CompoundTag nbt)
    {
        darkend = nbt.getInt("darkend");
    }
}
