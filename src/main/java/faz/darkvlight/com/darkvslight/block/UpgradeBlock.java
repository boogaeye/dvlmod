package faz.darkvlight.com.darkvslight.block;

import faz.darkvlight.com.darkvslight.blockentities.UpgradeBlockEntity;
import faz.darkvlight.com.darkvslight.registry.DvlBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.apache.logging.log4j.core.jmx.Server;

import javax.annotation.Nullable;

public class UpgradeBlock extends Block implements EntityBlock
{

    public UpgradeBlock() {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops());
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state)
    {
        return DvlBlockEntities.UpgradeBlockAsEntity.get().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_60550_) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        if (p_60515_.getBlock() != p_60518_.getBlock())
        {
            BlockEntity ent = p_60516_.getBlockEntity(p_60517_);
            if (ent instanceof UpgradeBlockEntity upgradeBlockEntity)
            {
                upgradeBlockEntity.drops();
            }
        }
        super.onRemove(p_60515_, p_60516_, p_60517_, p_60518_, p_60519_);
    }

    @Nullable
    @Override
    public <T extends BlockEntity>BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return level.isClientSide ? null : ($0, $1, $2, blockEnt) -> {
            if (blockEnt instanceof UpgradeBlockEntity upgradeBlockEntity)
            {
                upgradeBlockEntity.tick();
            }
        };
    }

    @Nullable
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player ply, InteractionHand hand, BlockHitResult hitRes)
    {
        if (!level.isClientSide())
        {
            BlockEntity ent = level.getBlockEntity(pos);
            if (ent instanceof UpgradeBlockEntity)
            {
                NetworkHooks.openScreen((ServerPlayer)ply, (UpgradeBlockEntity)ent, pos);
            }
            else
            {
                throw new IllegalStateException("Lmao imagine not having a container provider");
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
