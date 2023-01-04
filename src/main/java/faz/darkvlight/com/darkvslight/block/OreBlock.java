package faz.darkvlight.com.darkvslight.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class OreBlock extends Block {
    public OreBlock(float Hardness, float Resistance) {
        super(Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(Hardness, Resistance).sound(SoundType.STONE));
    }
}
