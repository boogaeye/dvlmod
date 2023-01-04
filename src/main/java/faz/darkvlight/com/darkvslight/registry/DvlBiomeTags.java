package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DvlBiomeTags extends BiomeTagsProvider {

    public DvlBiomeTags(DataGenerator p_256205_, String modId, @org.jetbrains.annotations.Nullable ExistingFileHelper existingFileHelper) {
        super(p_256205_, modId, existingFileHelper);
    }
    @Override
    protected void addTags()
    {
        tag(DvlTags.Biomes.IS_DARKEND).add(DvlBiomes.HEATED_FOREST).add(DvlBiomes.FROZEN_FOREST);
    }
}
