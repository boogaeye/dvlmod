package faz.darkvlight.com.darkvslight.registry;
import com.google.common.collect.ImmutableSet;
import faz.darkvlight.com.darkvslight.Darkvslight;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class DvlPointOfInterests {

    public static final DeferredRegister<PoiType> POI = DeferredRegister.create(ForgeRegistries.POI_TYPES, Darkvslight.MODID);

    public static final RegistryObject<PoiType> DARKEND_PORTAL = POI.register("darkend_portal", () -> new PoiType(getBlockStates(DvlBlocks.Darkend_Portal.get()), 0, 1));

    private static Set<BlockState> getBlockStates(Block block) {
        return ImmutableSet.copyOf(block.getStateDefinition().getPossibleStates());
    }
}