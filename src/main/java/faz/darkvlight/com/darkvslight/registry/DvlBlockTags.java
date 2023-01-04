package faz.darkvlight.com.darkvslight.registry;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class DvlBlockTags extends BlockTagsProvider {

    public DvlBlockTags(PackOutput p_255800_, CompletableFuture<HolderLookup.Provider> p_256205_, String modId, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
        super(p_255800_, p_256205_, modId, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider p_256485_)
    {
        tag(DvlTags.Blocks.DARKEND_CARVER_REPLACE).addTag(BlockTags.OVERWORLD_CARVER_REPLACEABLES).addTag(DvlTags.Blocks.BASE_STONE_DARKEND).add(Blocks.LAVA).add(Blocks.WATER);
        tag(DvlTags.Blocks.BASE_STONE_DARKEND).addTag(BlockTags.BASE_STONE_OVERWORLD).add(DvlBlocks.Darkend_Stone.get());
        tag(DvlTags.Blocks.DARKEND_PORTAL_FRAME_BLOCKS).add(Blocks.STONE);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(DvlBlocks.Darkend_Stone.get()).add(DvlBlocks.StartileOre.get());
        tag(BlockTags.MINEABLE_WITH_HOE).add(DvlBlocks.DarkCorrosion.get());
        tag(DvlTags.Blocks.NEEDS_BLIND_TOOL).add(DvlBlocks.StartileOre.get());
    }
}
