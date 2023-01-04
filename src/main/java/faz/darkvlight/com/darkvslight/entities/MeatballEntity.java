package faz.darkvlight.com.darkvslight.entities;

import faz.darkvlight.com.darkvslight.registry.DvlSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Attr;

public class MeatballEntity extends Monster implements Enemy
{
    public MeatballEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return DvlSoundEvents.MeatballAmbient.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_33034_) {
        return DvlSoundEvents.MeatballHurt.get();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.2f));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.1f, false));
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0f));
        this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder getMeatballAttributes()
    {
        return Monster.createMonsterAttributes().add(Attributes.ATTACK_SPEED, 8).add(Attributes.ATTACK_DAMAGE, 4).add(Attributes.MOVEMENT_SPEED).add(Attributes.MAX_HEALTH, 30).add(Attributes.FOLLOW_RANGE, 16);
    }

}
