package faz.darkvlight.com.darkvslight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;

public class DarkCorrosion extends SculkCatalystBlock {
    public DarkCorrosion() {
        super(Properties.of(Material.PLANT).strength(1.5F, 6.0F));
    }

}
