package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DvlSoundEvents
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Darkvslight.MODID);

    public static final RegistryObject<SoundEvent> DARK_PORTAL = register("block.dark-portal.ambient");

    public static final RegistryObject<SoundEvent> MeatballAmbient = register("entity.darkend-biter.ambient");

    public static final RegistryObject<SoundEvent> MeatballHurt = register("entity.darkend-biter.hurt");

    public static final RegistryObject<SoundEvent> UpgradePrestart = register("block.upgrade.prestart");

    public static final RegistryObject<SoundEvent> UpgradeStart = register("block.upgrade.start");

    public static final RegistryObject<SoundEvent> UpgradeLoop = register("block.upgrade.loop");

    public static final RegistryObject<SoundEvent> UpgradeEnd = register("block.upgrade.end");

    private static RegistryObject<SoundEvent> register(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(Darkvslight.MODID, name)));
    }
}
