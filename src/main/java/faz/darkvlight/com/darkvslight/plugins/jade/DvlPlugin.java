package faz.darkvlight.com.darkvslight.plugins.jade;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.block.UpgradeBlock;
import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin(Darkvslight.MODID)
public class DvlPlugin implements IWailaPlugin
{
    @Override
    public void register(IWailaCommonRegistration reg)
    {
        reg.registerBlockDataProvider(DvlComponentProvider.INSTANCE, UpgradeBlockEntity.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(DvlComponentProvider.INSTANCE, UpgradeBlock.class);
    }
}
