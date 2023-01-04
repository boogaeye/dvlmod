package faz.darkvlight.com.darkvslight.registry;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.entities.MeatballEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DvlEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Darkvslight.MODID);

    public static final RegistryObject<EntityType<MeatballEntity>> DARKEND_BITER = ENTITIES.register("darkend_biter", () -> EntityType.Builder.of(MeatballEntity::new, MobCategory.MONSTER).sized(0.6f,1.8f).build(Darkvslight.MODID + ":Darkend Biter"));
}
