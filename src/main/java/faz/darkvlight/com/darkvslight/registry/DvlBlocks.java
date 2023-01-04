package faz.darkvlight.com.darkvslight.registry;

import com.mojang.logging.LogUtils;
import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.block.*;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class DvlBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Darkvslight.MODID);


    public static final RegistryObject<Block> Darkend_Portal = BLOCKS.register("darkend_portal", DarkendPortalBlock::new);

    public static final RegistryObject<Block> Darkend_Stone = BLOCKS.register("dark_stone", DarkendStone::new);

    public static final RegistryObject<Block> BurnOre = BLOCKS.register("burn_ore", () -> new OreBlock(5, 10));

    public static final RegistryObject<Block> StartileOre = BLOCKS.register("startile_ore", () -> new EmissiveOreBlock(5, 10));

    public static final RegistryObject<Block> BlindOre = BLOCKS.register("blind_ore", () -> new EmissiveOreBlock(1, 2));

    public static final RegistryObject<Block> BlindStone = BLOCKS.register("blind_stone", () -> new EmissiveOreBlock(1, 2));

    public static final RegistryObject<Block> DarkCorrosion = BLOCKS.register("dark_corrosion", DarkCorrosion::new);

    public static final RegistryObject<Block> UpgradeBlock = BLOCKS.register("upgrade_block", () -> new UpgradeBlock());





    public static void Register(IEventBus bus)
    {
        BLOCKS.register(bus);
    }
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> returnStuff = BLOCKS.register(name, block);
        LogUtils.getLogger().info("Registered block " + name);
        registerBlockItem(name, returnStuff);
        return returnStuff;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        LogUtils.getLogger().info("Registered ItemBlock " + name);

        return DvlItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));

    }
}
