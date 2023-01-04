package faz.darkvlight.com.darkvslight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class DarkendLiquid extends FlowingFluid
{
    @Override
    public Fluid getFlowing() {
        return null;
    }

    @Override
    public Fluid getSource() {
        return null;
    }

    @Override
    protected boolean canConvertToSource(Level p_256009_) {
        return false;
    }

    @Override
    protected void beforeDestroyingBlock(LevelAccessor p_76002_, BlockPos p_76003_, BlockState p_76004_) {

    }

    @Override
    protected int getSlopeFindDistance(LevelReader p_76074_) {
        return 0;
    }

    @Override
    protected int getDropOff(LevelReader p_76087_) {
        return 0;
    }

    @Override
    public Item getBucket() {
        return null;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState p_76127_, BlockGetter p_76128_, BlockPos p_76129_, Fluid p_76130_, Direction p_76131_) {
        return false;
    }

    @Override
    public int getTickDelay(LevelReader p_76120_) {
        return 0;
    }

    @Override
    protected float getExplosionResistance() {
        return 0;
    }

    @Override
    protected BlockState createLegacyBlock(FluidState p_76136_) {
        return null;
    }

    @Override
    public boolean isSource(FluidState p_76140_) {
        return false;
    }

    @Override
    public int getAmount(FluidState p_164509_) {
        return 0;
    }

    public static class Flowing extends WaterFluid {
        protected void createFluidStateDefinition(StateDefinition.Builder<Fluid, FluidState> p_76476_) {
            super.createFluidStateDefinition(p_76476_);
            p_76476_.add(LEVEL);
        }

        public int getAmount(FluidState p_76480_) {
            return p_76480_.getValue(LEVEL);
        }

        public boolean isSource(FluidState p_76478_) {
            return false;
        }
    }

    public static class Source extends DarkendLiquid
    {
        @Override
        public int getAmount(FluidState p_164509_) {
            return 8;
        }

        @Override
        public boolean isSource(FluidState p_76140_) {
            return true;
        }
    }
}
