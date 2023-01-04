package faz.darkvlight.com.darkvslight.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class EmissiveOreBlock extends Block {
    public EmissiveOreBlock(float Hardness, float Resistance) {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(Hardness, Resistance).sound(SoundType.STONE).hasPostProcess((p_61036_, p_61037_, p_61038_) -> true).emissiveRendering((p_61036_, p_61037_, p_61038_) -> true).lightLevel((e) -> {
            return 3;
        }));
    }
}
