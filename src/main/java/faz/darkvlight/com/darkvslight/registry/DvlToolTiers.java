package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class DvlToolTiers {
    public static Tier Blind;
    public static Tier Startile;

    static
    {
        Blind = TierSortingRegistry.registerTier(new ForgeTier(7, 100, 9f, 3f, 60, DvlTags.Blocks.NEEDS_BLIND_TOOL, () -> Ingredient.of(DvlItems.StartileGem.get())), new ResourceLocation(Darkvslight.MODID, "blind"), List.of(Tiers.NETHERITE), List.of());
        Startile = TierSortingRegistry.registerTier(new ForgeTier(8, 2000, 9f, 3f, 24, DvlTags.Blocks.NEEDS_STARTILE_TOOL, () -> Ingredient.of(DvlItems.StartileGem.get())), new ResourceLocation(Darkvslight.MODID, "startile"), List.of(Blind), List.of());
    }
}
