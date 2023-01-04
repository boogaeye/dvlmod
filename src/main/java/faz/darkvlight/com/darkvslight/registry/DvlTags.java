package faz.darkvlight.com.darkvslight.registry;
import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.versions.forge.ForgeVersion;
public class DvlTags {
    public static class Blocks {
        public static final TagKey<Block> DARKEND_PORTAL_FRAME_BLOCKS = tag("dark_portal_frame_blocks");

        public static final TagKey<Block> DARKEND_CARVER_REPLACE = tag("darkend_carver_replaceables");

        public static final TagKey<Block> BASE_STONE_DARKEND = tag("base_stone_darkend");

        public static final TagKey<Block> NEEDS_BLIND_TOOL = tag("needs_blind_tool");
        public static final TagKey<Block> NEEDS_STARTILE_TOOL = tag("needs_startile_tool");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(Darkvslight.MODID, name));
        }
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation(ForgeVersion.MOD_ID, name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> IS_DARKEND = tag("is_darkend");

        private static TagKey<Biome> tag(String name) {

            return TagKey.create(Registries.BIOME, new ResourceLocation(Darkvslight.MODID, name));
        }
    }
}
