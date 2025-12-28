package com.hedge.squamorphs.entity.living.summons;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.util.goals.GenericFlyingGoal;
import com.hedge.squamorphs.entity.util.goals.GenericMeleeGoal;
import com.hedge.squamorphs.entity.util.goals.OwnableEntityOwnerHurtByTargetGoal;
import com.hedge.squamorphs.entity.util.goals.OwnableEntityOwnerHurtTargetGoal;
import com.hedge.squamorphs.entity.util.navigation.SmoothFlyingMoveControl;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ElementalSpiritEntity extends SquamorphSummon {

    private int attackCD = 0;
    private int animTicks = 0;
    protected static final EntityDataAccessor<Boolean> LEFT = SynchedEntityData.defineId(ElementalSpiritEntity.class, EntityDataSerializers.BOOLEAN);


    public ElementalSpiritEntity(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.navigation = new FlyingPathNavigation(this, this.level());
        this.moveControl = new SmoothFlyingMoveControl(this, 80, 60);
        this.lookControl = new SmoothSwimmingLookControl(this, 80);
        this.noPhysics = true;

    }

    public static AttributeSupplier.Builder bakeAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 40F)
                .add(Attributes.MOVEMENT_SPEED, 0.35F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(LEFT, false);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new GenericFlyingGoal(this));
        this.goalSelector.addGoal(0, new GenericMeleeGoal<>(this, 1.2, true));


        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnableEntityOwnerHurtByTargetGoal<>(this, false));
        this.targetSelector.addGoal(2, new OwnableEntityOwnerHurtTargetGoal<>(this, false));
    }


    @Override
    public void tickClient() {

    }

    @Override
    public void tickServer() {
        if (this.getAttackState() == 0) {
            this.attackCD = Math.max(this.attackCD - 1, 0);
        } else {
            animTicks++;
            switch (getAttackState()) {
                case 1:
                    if (animTicks == 15) {
                        LivingEntity target = this.getTarget();
                        if (target != null && this.distanceToSqr(target) <= this.getAttackRangeSqr(target)) {
                            this.doHurtTarget(target);
                            this.getElement().applyElement(target, this, this.getSummonLevel(), 5);
                        }
                    } else if (animTicks >= 18) {
                        this.setAttackState(0);
                        this.entityData.set(LEFT, !this.swingingLeft());
                        this.animTicks = 0;
                        this.resetAttackCD();
                    }
                    break;
                case 2:
                    if (animTicks >= 12) {
                        this.setAttackState(0);
                        this.animTicks = 0;
                    }
                    break;
            }
        }
    }

    @Override
    public void summon(SquamorphEntity entity, int elementIndex, int summonLevel) {
        super.summon(entity, elementIndex, summonLevel);
        this.setLifespan(600 + summonLevel * 100);
        this.moveTo(entity.getX(), entity.getY(), entity.getZ());
        this.setTarget(entity.getTarget());
        this.setAttackState(2);
    }

    public void summonFromCircle(SquamorphEntity entity, int elementIndex, int summonLevel, double xPos, double yPos, double zPos) {
        super.summon(entity, elementIndex, summonLevel);
        this.setLifespan(600 + summonLevel * 100);
        this.moveTo(xPos, yPos, zPos);
        this.setTarget(entity.getTarget());
        this.setAttackState(2);
    }

    @Override
    protected boolean isImmobile() {
        return this.getAttackState() == 2 || super.isImmobile();
    }

    public boolean swingingLeft() {
        return this.entityData.get(LEFT);
    }


    @Override
    public void applyLevelStats() {

    }

    @Override
    public double getAttackRangeSqr(LivingEntity target) {
        return this.getBbWidth() * 2.5F * this.getBbWidth() * 2.5F + target.getBbWidth();
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isControlledByLocalInstance()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.6D));
        } else {
            super.travel(pTravelVector);
        }
    }

    @Override
    public int getAttackCD() {
        return this.attackCD;
    }

    @Override
    public void resetAttackCD() {
        this.attackCD = 5;
    }
}
