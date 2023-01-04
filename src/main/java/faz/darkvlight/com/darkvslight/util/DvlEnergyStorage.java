package faz.darkvlight.com.darkvslight.util;

import net.minecraftforge.energy.EnergyStorage;

public abstract class DvlEnergyStorage extends EnergyStorage
{
    public DvlEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int ener = super.extractEnergy(maxExtract, simulate);
        if (ener != 0)
        {
            onEnergyChanged();
        }

        return ener;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int ener = super.receiveEnergy(maxReceive, simulate);
        if (ener != 0)
        {
            onEnergyChanged();
        }

        return ener;
    }

    public int setEnergy(int energy)
    {
        this.energy = energy;
        return energy;
    }

    public abstract void onEnergyChanged();
}
