package faz.darkvlight.com.darkvslight.recipe;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.util.DvlSimpleContainer;
import faz.darkvlight.com.darkvslight.util.FluidJSONUtil;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public class UpgradeBlockRecipe implements Recipe<SimpleContainer>
{
    private final ResourceLocation id;
    private final ItemStack out;
    private final NonNullList<Ingredient> itemsRecipe;

    private final int EnergyOutput;

    private final int MaxProg;

    private final FluidStack fluid;

    private final String method;


    public UpgradeBlockRecipe(ResourceLocation rl, ItemStack out, NonNullList<Ingredient> recipe, int energy, int MaxProg, FluidStack f, String meth)
    {
        id = rl;
        this.out = out;
        itemsRecipe = recipe;
        EnergyOutput = energy;
        this.MaxProg = MaxProg;
        fluid = f;
        method = meth;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;

        DvlSimpleContainer dContain = (DvlSimpleContainer) pContainer;

        if (dContain.isEmpty()) return false;
        return itemsRecipe.get(0).test(pContainer.getItem(1)) && dContain.fluid.get().getFluid() == fluid.getFluid();
    }

    public String getMethod()
    {
        return method;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer) {
        return out;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return out.copy();
    }

    public int getEnergyOutput()
    {
        return EnergyOutput;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public int getHardness()
    {
        return MaxProg;
    }

    public FluidStack getFluid()
    {
        return fluid;
    }

    public static class Type implements RecipeType<UpgradeBlockRecipe>
    {
        private Type(){}
        public static final Type INSTANCE = new Type();
        public static final String ID = "upgrading";
    }

    public static class Serializer implements RecipeSerializer<UpgradeBlockRecipe>
    {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Darkvslight.MODID, "upgrading");

        @Override
        public UpgradeBlockRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));
            int energy = GsonHelper.getAsInt(pSerializedRecipe, "energy");
            int harden = GsonHelper.getAsInt(pSerializedRecipe, "hardness");
            String meth = GsonHelper.getAsString(pSerializedRecipe, "method");
            FluidStack fluidStack = FluidJSONUtil.readFluid(pSerializedRecipe.get("fluid").getAsJsonObject());
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new UpgradeBlockRecipe(pRecipeId, output, inputs, energy, harden, fluidStack, meth);
        }

        @Override
        public @Nullable UpgradeBlockRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }
            int hardening = pBuffer.readInt();
            int energy = pBuffer.readInt();
            ItemStack output = pBuffer.readItem();
            FluidStack fs = pBuffer.readFluidStack();
            int methInt = pBuffer.readInt();
            String meth = pBuffer.readUtf(methInt);
            return new UpgradeBlockRecipe(pRecipeId, output, inputs, energy, hardening, fs, meth);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, UpgradeBlockRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());

            for (Ingredient ing : pRecipe.getIngredients()) {
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeInt(pRecipe.MaxProg);
            pBuffer.writeInt(pRecipe.EnergyOutput);
            pBuffer.writeItemStack(pRecipe.getResultItem(), false);
            pBuffer.writeFluidStack(pRecipe.getFluid());
            pBuffer.writeInt(pRecipe.method.length());
            pBuffer.writeUtf(pRecipe.method, pRecipe.method.length());
        }
    }
}
