package faz.darkvlight.com.darkvslight.registry;
import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class DvlDimensions {
    public static final ResourceKey<Level> DARKEND_LEVEL = ResourceKey.create(Registries.DIMENSION, name("darkend"));

    private static ResourceLocation name(String name) {
        return new ResourceLocation(Darkvslight.MODID, name);
    }
}
