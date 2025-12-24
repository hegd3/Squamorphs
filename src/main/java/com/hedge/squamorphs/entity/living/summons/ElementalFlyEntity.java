package com.hedge.squamorphs.entity.living.summons;


import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.util.goals.GenericFlyingGoal;
import com.hedge.squamorphs.entity.util.goals.GenericMeleeGoal;
import com.hedge.squamorphs.entity.util.goals.OwnableEntityOwnerHurtByTargetGoal;
import com.hedge.squamorphs.entity.util.goals.OwnableEntityOwnerHurtTargetGoal;
import com.hedge.squamorphs.entity.util.navigation.SmoothFlyingMoveControl;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ElementalFlyEntity extends SquamorphSummon {

    private int attackCD = 0;
    private int animTicks = 0;

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
        this.goalSelector.addGoal(0, new GenericMeleeGoal<>(this, 1.2, true));


        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new OwnableEntityOwnerHurtByTargetGoal<>(this, false));
        this.targetSelector.addGoal(2, new OwnableEntityOwnerHurtTargetGoal<>(this, false));
    }

    @Override
    public void tick() {
        super.tick();
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
            if (animTicks >= 10) {
                LivingEntity target = this.getTarget();
                if (target != null && this.distanceToSqr(target) <= this.getAttackRangeSqr(target)) {
                    this.doHurtTarget(target);
                    this.getElement().applyElement(target, this, 0, 5);
                }
                this.setAttackState(0);
                this.animTicks = 0;
                this.resetAttackCD();
            }
        }
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
    public void summon(SquamorphEntity entity, int elementIndex, int summonLevel) {
        super.summon(entity, elementIndex, summonLevel);
        this.setLifespan(200 + summonLevel * 30);
        this.moveTo(entity.getX(), entity.getY(), entity.getZ());
        this.setYRot(entity.getYRot());
        this.yRotO = entity.yRotO;
        this.setXRot(entity.getXRot());
        this.xRotO = entity.xRotO;
        this.setDeltaMovement(this.getLookAngle());
        this.setTarget(entity.getTarget());
    }

    @Override
    protected int calculateFallDamage(float pFallDistance, float pDamageMultiplier) {
        return 0;
    }




    @Override
    public void applyLevelStats() {

    }

    @Override
    public double getAttackRangeSqr(LivingEntity target) {
        return this.getBbWidth() * 2.0F * this.getBbWidth() * 2.0F + target.getBbWidth();
    }

    @Override
    public int getAttackCD() {
        return this.attackCD;
    }

    @Override
    public void resetAttackCD() {
        this.attackCD = 30;
    }
}
