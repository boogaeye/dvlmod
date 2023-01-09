package faz.darkvlight.com.darkvslight.mixin;

import faz.darkvlight.com.darkvslight.client.PlayerDarkendProvider;
import faz.darkvlight.com.darkvslight.networking.DarkendDataSyncS2CPacket;
import faz.darkvlight.com.darkvslight.networking.ModMessages;
import faz.darkvlight.com.darkvslight.registry.DvlTags;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerMixin {

    @Inject(method = "tick", at = @At(value = "HEAD"), remap = false)
    private void tick(CallbackInfo ci){
        if (!Minecraft.getInstance().isLocalServer()) return;
        LivingEntity ply = ((LivingEntity) (Object) this);
        if (!(!ply.isSpectator() && !((ServerPlayer)ply).isCreative())) return;

        if ((ply.level.getBiome(ply.getOnPos())).containsTag(DvlTags.Biomes.IS_DARKEND))
        {
            ply.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 10, 0));
        }
        ply.getCapability(PlayerDarkendProvider.PLAYER_DARKEND).ifPresent(dark ->
        {
            if (ply.tickCount % 60 == 0)
            {
                if (ply.level.getRawBrightness(ply.blockPosition(), 0) <= 4 || (ply.level.isNight() && ply.level.canSeeSky(ply.blockPosition()) && ply.level.getRawBrightness(ply.blockPosition(), 0) <= 4)) {
                    dark.addDarkness(1);
                } else {
                    dark.subDarkness(1);
                    if (dark.getDark() < 1){
                        ply.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 20 * 3, 0));
                        ply.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20 * 3, 2));
                        ply.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20 * 3, 2));
                        ply.level.playSound(null, ply.blockPosition(), SoundEvents.SCULK_SHRIEKER_SHRIEK, SoundSource.PLAYERS, 5, 1);
                        ply.level.playSound(null, ply.blockPosition(), SoundEvents.WARDEN_HEARTBEAT, SoundSource.PLAYERS, 5, 1);
                        ply.hurt(new DamageSource("Darkend"), 2);
                    }
                }
            }
            ModMessages.sendToPlayer(new DarkendDataSyncS2CPacket(dark.getDark()), (ServerPlayer) ply);
        });

    }

}
