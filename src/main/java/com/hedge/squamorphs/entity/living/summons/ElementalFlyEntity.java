package com.hedge.squamorphs.entity.living.summons;


import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.util.goals.GenericFlyingGoal;
import com.hedge.squamorphs.entity.util.navigation.SmoothFlyingMoveControl;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ElementalFlyEntity extends SquamorphSummon {



    public ElementalFlyEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.navigation = new FlyingPathNavigation(this, this.level());
        this.moveControl = new SmoothFlyingMoveControl(this, 80, 60);
        this.lookControl = new SmoothSwimmingLookControl(this, 80);
    }



    public static AttributeSupplier.Builder bakeAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 40F)
                .add(Attributes.MOVEMENT_SPEED, 0.4F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new GenericFlyingGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
    }

        @Override
    public void travel(Vec3 pTravelVector) {
        this.moveRelative(this.getSpeed(), pTravelVector);
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.6D));
    }

    @Override
    public void summon(SquamorphEntity entity, int elementIndex, int summonLevel) {
        super.summon(entity, elementIndex, summonLevel);
        this.setLifespan(200 + summonLevel * 30);
        this.moveTo(entity.getX(), entity.getY(), entity.getZ());
        this.setTarget(entity.getTarget());
    }

    @Override
    public void applyLevelStats() {

    }
}
