package faz.darkvlight.com.darkvslight;

import com.mojang.logging.LogUtils;
import faz.darkvlight.com.darkvslight.client.DarkendHudOverlay;
import faz.darkvlight.com.darkvslight.client.PlayerDarkend;
import faz.darkvlight.com.darkvslight.client.PlayerDarkendProvider;
import faz.darkvlight.com.darkvslight.client.models.MeatballModel;
import faz.darkvlight.com.darkvslight.client.renderer.MeatballRenderer;
import faz.darkvlight.com.darkvslight.entities.MeatballEntity;
import faz.darkvlight.com.darkvslight.networking.ModMessages;
import faz.darkvlight.com.darkvslight.registry.*;
import faz.darkvlight.com.darkvslight.screen.UpgradeMenu;
import faz.darkvlight.com.darkvslight.screen.UpgradeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.loading.ClientModLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.data.ForgeBlockTagsProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Locale;
import java.util.stream.Stream;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Darkvslight.MODID)
public class Darkvslight {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "darkvslight";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();


    public Darkvslight() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::entityAttribute);
        modEventBus.addListener(this::entityRenderers);
        modEventBus.addListener(this::registerLayerDef);
        DvlBlocks.Register(modEventBus);
        DvlItems.ITEMS.register(modEventBus);
        DvlSoundEvents.SOUNDS.register(modEventBus);
        DvlPointOfInterests.POI.register(modEventBus);
        DvlBiomes.BIOMES.register(modEventBus);
        DvlEntities.ENTITIES.register(modEventBus);
        DvlBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        DvlModMenuTypes.MENUS.register(modEventBus);
        DvlRecipes.RECIPES.register(modEventBus);
        //DvlLiquids.FLUID.register(modEventBus);
        //DvlLiquids.FLUID_TYPES.register(modEventBus);



        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        ModMessages.register();
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }




    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");

    }

    @SubscribeEvent
    public void entityAttribute(EntityAttributeCreationEvent ev)
    {
        ev.put(DvlEntities.DARKEND_BITER.get(), MeatballEntity.getMeatballAttributes().build());
    }

    @SubscribeEvent
    public void entityRenderers(EntityRenderersEvent.RegisterRenderers ev)
    {
        ev.registerEntityRenderer(DvlEntities.DARKEND_BITER.get(), MeatballRenderer::new);
    }

    @SubscribeEvent
    public void registerLayerDef(EntityRenderersEvent.RegisterLayerDefinitions ev)
    {
        ev.registerLayerDefinition(MeatballModel.LAYER_LOCATION, MeatballModel::createBodyLayer);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ModSubscriber
    {
        @SubscribeEvent
        public static void attachCapabilityToPlayer(AttachCapabilitiesEvent<Entity> ev)
        {
            if (ev.getObject() instanceof Player)
            {
                if (!ev.getObject().getCapability(PlayerDarkendProvider.PLAYER_DARKEND).isPresent())
                {
                    ev.addCapability(new ResourceLocation(MODID, "properties"), new PlayerDarkendProvider());
                }
            }
        }

        @SubscribeEvent
        public static void playerCloned(PlayerEvent.Clone ev)
        {
            if (ev.isWasDeath())
            {
                ev.getOriginal().getCapability(PlayerDarkendProvider.PLAYER_DARKEND).ifPresent(old ->
                {
                    ev.getOriginal().getCapability(PlayerDarkendProvider.PLAYER_DARKEND).ifPresent(newStore ->
                    {
                        newStore.copyFrom(old);
                    });
                });
            }
        }

        @SubscribeEvent
        public static void playerSpawned(PlayerEvent.PlayerChangeGameModeEvent ev)
        {
            if (ev.getNewGameMode() == GameType.CREATIVE || ev.getNewGameMode() == GameType.SPECTATOR)
            {
                ev.getEntity().getCapability(PlayerDarkendProvider.PLAYER_DARKEND).ifPresent(old ->
                {
                    old.addDarkness(20);
                });
            }
        }

        @SubscribeEvent
        public static void RegisterCap(RegisterCapabilitiesEvent ev)
        {
            ev.register(PlayerDarkend.class);
        }


    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class GatherDataSubscriber
    {
        @SubscribeEvent
        public static void gatherTheFuckingData(GatherDataEvent ev)
        {
            LOGGER.info("IM GATHERING THE FUCKING DATA WALTER");
            if (ev.includeServer()){
                ev.getGenerator().addProvider(true, new DvlBiomeTags(ev.getGenerator(), MODID, ev.getExistingFileHelper()));
                ev.getGenerator().addProvider(true, new DvlBlockTags(ev.getGenerator(), MODID, ev.getExistingFileHelper()));
            }
        }


    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            MenuScreens.register(DvlModMenuTypes.UpgradeMenu.get(), UpgradeScreen::new);
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent ev){
            ev.registerAboveAll("darkend", DarkendHudOverlay.HUD_DARKEND);
        }





    }


}
