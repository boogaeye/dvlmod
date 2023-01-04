package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.recipe.UpgradeBlockRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.UpgradeRecipe;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DvlRecipes
{
    public static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Darkvslight.MODID);
    public static final RegistryObject<RecipeSerializer<UpgradeBlockRecipe>> UpgradeSerializer = RECIPES.register("upgrading", () -> UpgradeBlockRecipe.Serializer.INSTANCE);
}
