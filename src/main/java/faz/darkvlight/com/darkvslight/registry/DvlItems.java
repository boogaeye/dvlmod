package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.block.DarkendPortalBlock;
import faz.darkvlight.com.darkvslight.block.DarkendStone;
import faz.darkvlight.com.darkvslight.item.StartileGem;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class DvlItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Darkvslight.MODID);

    public static CreativeModeTab DarkendTab;


    public static final RegistryObject<Item> DarkendStoneBlock = ITEMS.register("dark_stone", () -> new BlockItem(DvlBlocks.Darkend_Stone.get(), new Item.Properties()));

    public static final RegistryObject<Item> StartileOreBlock = ITEMS.register("startile_ore", () -> new BlockItem(DvlBlocks.StartileOre.get(), new Item.Properties()));

    public static final RegistryObject<Item> BurnOreBlock = ITEMS.register("burn_ore", () -> new BlockItem(DvlBlocks.BurnOre.get(), new Item.Properties()));

    public static final RegistryObject<Item> BlindOreBlock = ITEMS.register("blind_ore", () -> new BlockItem(DvlBlocks.BlindOre.get(), new Item.Properties()));

    public static final RegistryObject<Item> BlindStoneBlock = ITEMS.register("blind_stone", () -> new BlockItem(DvlBlocks.BlindStone.get(), new Item.Properties()));

    public static final RegistryObject<Item> DarkCorrosionBlock = ITEMS.register("dark_corrosion", () -> new BlockItem(DvlBlocks.DarkCorrosion.get(), new Item.Properties()));

    public static final RegistryObject<Item> StartileGem = ITEMS.register("startilegem", () -> new StartileGem(new Item.Properties()));

    public static final RegistryObject<Item> RawStartile = ITEMS.register("rawstartile", () -> new StartileGem(new Item.Properties()));

    public static final RegistryObject<Item> Darkend_Biter_Spawn_Egg = ITEMS.register("darkend_biter_spawn_egg", () -> new ForgeSpawnEggItem(DvlEntities.DARKEND_BITER, 0xFFFFFF, 0x000000, new Item.Properties().stacksTo(16)));


}
