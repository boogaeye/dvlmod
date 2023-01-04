package faz.darkvlight.com.darkvslight.client.renderer;

import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.client.models.MeatballModel;
import faz.darkvlight.com.darkvslight.entities.MeatballEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.resources.ResourceLocation;

public class MeatballRenderer extends MobRenderer<MeatballEntity, MeatballModel>
{
    private static final ResourceLocation TEXTURE = new ResourceLocation(Darkvslight.MODID, "textures/entities/meatball.png");
    public MeatballRenderer(EntityRendererProvider.Context ctx)
    {
        super(ctx, new MeatballModel(ctx.bakeLayer(MeatballModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(MeatballEntity entity)
    {
        return TEXTURE;
    }
}
