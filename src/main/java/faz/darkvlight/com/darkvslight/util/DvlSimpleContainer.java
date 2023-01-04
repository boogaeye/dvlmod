package faz.darkvlight.com.darkvslight.util;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Optional;

public class DvlSimpleContainer extends SimpleContainer
{
    public Optional<FluidStack> fluid;
    public DvlSimpleContainer(int pSize, Optional<FluidStack> f)
    {
        super(pSize);
        fluid = f;
    }

    public DvlSimpleContainer(Optional<FluidStack> f, ItemStack... pItems) {
        super(pItems);
        fluid = f;
    }
}
