package faz.darkvlight.com.darkvslight.plugins.jade;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.extensions.IForgeBlockEntity;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;

public enum DvlComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockEntity>
{
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig)
    {
        if (blockAccessor.getServerData().contains("Upgrade"))
        {
            iTooltip.append(Component.translatable("darkvslight.upgrade", blockAccessor.getServerData().getInt("Upgrade")));
        }

    }

    @Override
    public ResourceLocation getUid() {
        return new ResourceLocation(Darkvslight.MODID, "upgrade_block");
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, ServerPlayer serverPlayer, Level level, BlockEntity o, boolean b) {
        UpgradeBlockEntity ub = (UpgradeBlockEntity) o;
        compoundTag.putInt("Upgrade", ((UpgradeBlockEntity) o).getData().get(0));
    }
}
