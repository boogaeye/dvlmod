package faz.darkvlight.com.darkvslight.registry;
import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
public class DvlBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Darkvslight.MODID);

    public static final ResourceKey<Biome> HEATED_FOREST = register("heated_forest");

    public static final ResourceKey<Biome> FROZEN_FOREST = register("frozen_forest");

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Darkvslight.MODID, name);
    }

    private static ResourceKey<Biome> register(String name) {
        //BIOMES.register(name, OverworldBiomes::theVoid);
        return ResourceKey.create(Registries.BIOME, name(name));
    }
}
