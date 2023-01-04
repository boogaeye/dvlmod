package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DvlBiomeTags extends BiomeTagsProvider {

    public DvlBiomeTags(PackOutput p_255800_, CompletableFuture<HolderLookup.Provider> p_256205_, String modId, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
        super(p_255800_, p_256205_, modId, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider p_256485_)
    {
        tag(DvlTags.Biomes.IS_DARKEND).add(DvlBiomes.HEATED_FOREST).add(DvlBiomes.FROZEN_FOREST);
    }
}
