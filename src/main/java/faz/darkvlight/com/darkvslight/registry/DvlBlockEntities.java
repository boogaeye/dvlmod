package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.block.UpgradeBlock;
import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.lwjgl.system.SharedLibrary;

public class DvlBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Darkvslight.MODID);

    public static final RegistryObject<BlockEntityType<UpgradeBlockEntity>> UpgradeBlockAsEntity = BLOCK_ENTITIES.register("upgrade_block", () -> (BlockEntityType.Builder.of(UpgradeBlockEntity::new, DvlBlocks.UpgradeBlock.get()).build(null)));
}
