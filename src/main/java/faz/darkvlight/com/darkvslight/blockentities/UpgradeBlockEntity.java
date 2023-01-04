package faz.darkvlight.com.darkvslight.blockentities;

import com.mojang.logging.LogUtils;
import faz.darkvlight.com.darkvslight.networking.EnergySyncS2CPacket;
import faz.darkvlight.com.darkvslight.networking.FluidSyncS2CPacket;
import faz.darkvlight.com.darkvslight.networking.MethodReasonSyncS2CPacket;
import faz.darkvlight.com.darkvslight.networking.ModMessages;
import faz.darkvlight.com.darkvslight.recipe.UpgradeBlockRecipe;
import faz.darkvlight.com.darkvslight.registry.DvlBlockEntities;
import faz.darkvlight.com.darkvslight.registry.DvlItems;
import faz.darkvlight.com.darkvslight.registry.DvlSoundEvents;
import faz.darkvlight.com.darkvslight.screen.UpgradeMenu;
import faz.darkvlight.com.darkvslight.util.DvlEnergyStorage;
import faz.darkvlight.com.darkvslight.util.DvlSimpleContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class UpgradeBlockEntity extends BlockEntity implements MenuProvider
{
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;
    private int ticksRunning;
    private final FluidTank FLUID = new FluidTank(64000){
        @Override
        protected void onContentsChanged() {
            setChanged();
            if (!level.isClientSide()) ModMessages.sendToClients(new FluidSyncS2CPacket(fluid, worldPosition));
        }

        @Override
        public boolean isFluidValid(FluidStack stack) {
            return stack.getFluid() == Fluids.WATER || stack.getFluid() == Fluids.LAVA;
        }
    };

    public void setFluid(FluidStack stack)
    {
        this.FLUID.setFluid(stack);
    }

    public FluidStack getFluidStack()
    {
        return this.FLUID.getFluid();
    }

    private final DvlEnergyStorage EnergyStorage = new DvlEnergyStorage(60000, 256) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ModMessages.sendToClients(new EnergySyncS2CPacket(this.energy, getBlockPos()));
        }
    };

    private String methodReason = "darkvslight.upgrade_block.NAMR";

    public void setMethodReason(String meth)
    {
        methodReason = meth;
    }

    public String getMethodReason()
    {
        return methodReason;
    }

    public static int EnergyRequirement = 32;
    private final ItemStackHandler itemHandler = new ItemStackHandler(3)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot)
                    {
                        case 0 -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                        case 1 -> true;
                        case 2 -> false;
                        default -> super.isItemValid(slot, stack);
                    };
        }
    };

    private LazyOptional<IEnergyStorage> EnergyHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> FluidHandler = LazyOptional.empty();

    public ContainerData getData()
    {
        return data;
    }

    public ItemStackHandler getItemHandler()
    {
        return itemHandler;
    }

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public UpgradeBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(DvlBlockEntities.UpgradeBlockAsEntity.get(), p_155229_, p_155230_);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index)
                        {
                            case 0 -> UpgradeBlockEntity.this.progress;
                            case 1 -> UpgradeBlockEntity.this.maxProgress;
                            default -> 0;
                        };
            }

            @Override
            public void set(int index, int value) {
                switch (index)
                {
                    case 0 -> UpgradeBlockEntity.this.progress = value;
                    case 1 -> UpgradeBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void tick()
    {
        if (level.isClientSide()) return;
        if (itemHandler.getStackInSlot(1).getItem() == Items.WATER_BUCKET)
        {
            EnergyStorage.receiveEnergy(128, false);
        }
        if (hasRecipe() && hasEnergy() && hasFluid())
        {
            if (ticksRunning == 0)
            {
                level.playSound(null, getBlockPos(), DvlSoundEvents.UpgradePrestart.get(), SoundSource.BLOCKS);
            }
            if (ticksRunning == 20)
            {
                level.playSound(null, getBlockPos(), DvlSoundEvents.UpgradeStart.get(), SoundSource.BLOCKS);
            }
            else if (ticksRunning % 38 == 0 && ticksRunning > 20)
            {
                level.playSound(null, getBlockPos(), DvlSoundEvents.UpgradeLoop.get(), SoundSource.BLOCKS);
            }
            progress++;
            ticksRunning++;
            extractEnergy();
            setChanged(level, getBlockPos(), getBlockState());
            if (progress >= maxProgress)
            {
                engageItem();
            }
        }
        else
        {
            if (ticksRunning != 0)
            {
                level.playSound(null, getBlockPos(), DvlSoundEvents.UpgradeEnd.get(), SoundSource.BLOCKS);
            }
            progress = 0;
            ticksRunning = 0;
            setChanged(level, getBlockPos(), getBlockState());
        }

        if (hasFluidItem())
        {
            transforFluidToTank();
        }
    }

    private boolean hasFluid()
    {
        SimpleContainer inv = new DvlSimpleContainer(itemHandler.getSlots(), Optional.of(FLUID.getFluid()));
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Optional<UpgradeBlockRecipe> recipe = level.getRecipeManager().getRecipeFor(UpgradeBlockRecipe.Type.INSTANCE, inv, level);
        if (!recipe.isPresent()) return false;
        return FLUID.getFluidAmount() >= recipe.get().getFluid().getAmount();
    }

    private void transforFluidToTank()
    {
        itemHandler.getStackInSlot(0).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(handle -> {
            int drainAmount = Math.min(FLUID.getSpace(), 1000);
            FluidStack stack = handle.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if (FLUID.isFluidValid(stack))
            {
                stack = handle.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                FillTank(stack, handle.getContainer());
            }
        });
    }

    private void FillTank(FluidStack stack, ItemStack container)
    {
        FLUID.fill(stack, IFluidHandler.FluidAction.EXECUTE);

        itemHandler.extractItem(0, 1, false);
        itemHandler.insertItem(0, container, false);
    }

    private boolean hasFluidItem() {
        return itemHandler.getStackInSlot(0).getCount() > 0;
    }

    private void extractEnergy()
    {
        EnergyStorage.extractEnergy(EnergyRequirement, false);
    }

    private boolean hasEnergy()
    {
        return EnergyRequirement <= EnergyStorage.getEnergyStored();
    }

    private void engageItem()
    {
        Level level = getLevel();
        SimpleContainer inv = new DvlSimpleContainer(itemHandler.getSlots(), Optional.of(FLUID.getFluid()));
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Optional<UpgradeBlockRecipe> recipe = level.getRecipeManager().getRecipeFor(UpgradeBlockRecipe.Type.INSTANCE, inv, level);

        if (hasRecipe())
        {
            FLUID.drain(recipe.get().getFluid().getAmount(), IFluidHandler.FluidAction.EXECUTE);
            itemHandler.extractItem(1, 1, false);
            itemHandler.setStackInSlot(2, new ItemStack(recipe.get().getResultItem().getItem(), itemHandler.getStackInSlot(2).getCount() + 1));
            progress = 0;
        }
    }

    private boolean hasRecipe() 
    {
        Level level = getLevel();
        SimpleContainer inv = new DvlSimpleContainer(itemHandler.getSlots(), Optional.of(FLUID.getFluid()));
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }
        Optional<UpgradeBlockRecipe> recipe = level.getRecipeManager().getRecipeFor(UpgradeBlockRecipe.Type.INSTANCE, inv, level);
        if (recipe.isPresent())
        {
            EnergyRequirement = recipe.get().getEnergyOutput();
            maxProgress = recipe.get().getHardness();
            methodReason = recipe.get().getMethod();
            ModMessages.sendToClients(new MethodReasonSyncS2CPacket(methodReason, getBlockPos()));
        }

        return recipe.isPresent() && canInsert(inv) && canOutput(inv, recipe.get().getResultItem());
    }

    private boolean canOutput(SimpleContainer inv, ItemStack itemStack)
    {
        return inv.getItem(2).getItem() == itemStack.getItem() || inv.getItem(2).isEmpty();
    }

    private boolean canInsert(SimpleContainer inv)
    {
        return inv.getItem(2).getMaxStackSize() > inv.getItem(2).getCount();
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY)
        {
            return EnergyHandler.cast();
        }
        if (cap == ForgeCapabilities.ITEM_HANDLER)
        {
            return lazyItemHandler.cast();
        }

        if (cap == ForgeCapabilities.FLUID_HANDLER)
        {
            return FluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        EnergyHandler = LazyOptional.of(() -> EnergyStorage);
        FluidHandler = LazyOptional.of(() -> FLUID);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        EnergyHandler.invalidate();
        FluidHandler.invalidate();
    }

    @Override
    public void load(CompoundTag nbt)
    {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("upgrade_block.upgrading");
        EnergyStorage.setEnergy(nbt.getInt("upgrade_block.energy"));
        FLUID.readFromNBT(nbt);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("upgrade_block.upgrading", progress);
        nbt.putInt("upgrade_block.energy", EnergyStorage.getEnergyStored());
        nbt = FLUID.writeToNBT(nbt);
        super.saveAdditional(nbt);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Upgrade Block");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player ply) {
        ModMessages.sendToClients(new EnergySyncS2CPacket(EnergyStorage.getEnergyStored(), getBlockPos()));
        ModMessages.sendToClients(new FluidSyncS2CPacket(getFluidStack(), worldPosition));

        return new UpgradeMenu(id, inv, this, this.data);
    }

    public void drops()
    {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
        {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public IEnergyStorage getEnergyStorage()
    {
        return EnergyStorage;
    }

    public void setEnergyLevel(int energy)
    {
        this.EnergyStorage.setEnergy(energy);
    }
}
