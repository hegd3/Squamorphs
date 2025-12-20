package com.hedge.squamorphs.entity.living;


import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphElement;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphBody;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphWings;
import com.hedge.squamorphs.entity.squamorphparts.head.SquamorphHead;
import com.hedge.squamorphs.entity.squamorphparts.legs.SquamorphLeg;
import com.hedge.squamorphs.entity.squamorphparts.mouth.SquamorphMouth;
import com.hedge.squamorphs.entity.squamorphparts.tail.SquamorphTail;
import com.hedge.squamorphs.entity.util.SquamorphHelpers;
import com.hedge.squamorphs.entity.util.goals.SquamorphAttackGoal;
import com.hedge.squamorphs.entity.util.goals.SquamorphWanderGoal;
import com.hedge.squamorphs.entity.util.navigation.SmoothFlyingMoveControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

import static com.hedge.squamorphs.entity.squamorphparts.AllParts.*;
import static com.hedge.squamorphs.entity.squamorphparts.SquamorphElement.ALL_ELEMENTS;

public class SquamorphEntity extends Animal implements RangedAttackMob {

    private static final EntityDataAccessor<Integer> HEAD_TYPE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BODY_TYPE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOUTH_TYPE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LEGS_TYPE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TAIL_TYPE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_TYPE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> PRIMARY_COLOR = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SECONDARY_COLOR = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EYE_COLOR = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Integer> PRIMARY_ELEMENT = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SECONDARY_ELEMENT = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Boolean> IS_FLYING = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);


    private static final EntityDataAccessor<Integer> HEAD_LEVEL = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> MOUTH_LEVEL = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BODY_LEVEL = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> LEGS_LEVEL = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> TAIL_LEVEL = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.INT);


    public final AnimationState idleAnimationState = new AnimationState();

    public final AnimationState wingIdleAnimationState = new AnimationState();
    public final AnimationState wingWalkAnimationState = new AnimationState();


    public final AnimationState currentMoveAnimationState = new AnimationState();

    private SquamorphHead head = ALL_HEADS[0];
    private SquamorphMouth mouth = ALL_MOUTHS[0];
    private SquamorphBody body = ALL_BODIES[0];
    private SquamorphLeg leg = ALL_LEGS[0];
    private SquamorphTail tail = ALL_TAILS[0];
    private ArrayList<SquamorphPart> ranged_parts = new ArrayList<SquamorphPart>();
    private ArrayList<SquamorphPart> melee_parts = new ArrayList<SquamorphPart>();

    private SquamorphElement primaryElement = SquamorphElement.ICE;
    private SquamorphElement secondaryElement = SquamorphElement.ICE;
    private int animTicks = 0;
    private int headAbilityCD = 0;
    private int mouthAbilityCD = 0;
    private int bodyAbilityCD = 0;
    private int legAbilityCD = 0;
    private int tailAbilityCD = 0;

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.CARROT, Items.POTATO, Items.BEETROOT);


    public SquamorphEntity(EntityType<? extends Animal> animal, Level pLevel) {
        super(animal, pLevel);
        this.navigation = new AmphibiousPathNavigation(this, this.level());
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0f);
        this.setMaxUpStep(1);
    }

    public static AttributeSupplier.Builder bakeAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 40F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new SquamorphWanderGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, LivingEntity.class, 8.0F));
        this.goalSelector.addGoal(1, new SquamorphAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1, this.getClass()));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, FOOD_ITEMS, false));



        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, FlyingMob.class, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Villager.class, false));

    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setUpAnimStates();
        } else {
            /*
            this.head.tick();
            this.mouth.tick();
            this.body.tick();
            this.tail.tick();
            this.tail.tick();

             */
            tickAttack(this.getTarget());
            tickCooldown();
        }
    }

    public void startAttackAnim(double dist, LivingEntity target, int animIndex) {

        animTicks = 0;

        switch (animIndex) {
            case 1:
                if (dist <= this.getMeleeAttackRangeSqr(target)) {
                    this.setAttackState(1);
                }
                break;
            case 2:
                this.setAttackState(2);
                break;
        }
    }

    private void tickAttack(LivingEntity target) {

        if (this.getAttackState() > 0) {
            if (target != null) {
                animTicks++;
                double d0 = this.getPerceivedTargetDistanceSquareForMeleeAttack(target);
                switch (this.getAttackState()) {
                    case 1:
                        if (animTicks >= 8) {
                            this.checkAndPerformAttack(target, d0);
                        }
                        break;
                    case 2:
                        this.head.tickAttack(this, animTicks, target, d0);
                }
            }
            else {
                this.setAttackState(0);
            }
        }


    }

    public void checkAndPerformAttack(LivingEntity target, double dist) {
        double d0 = this.getMeleeAttackRangeSqr(target);
        if (dist <= d0) {
            this.swing(InteractionHand.MAIN_HAND);
            this.doHurtTarget(target);
            this.primaryElement.applyElement(target, this, 1, 5);
            this.animTicks = 0;
        }
        //this.addCooldowns();
        this.mouthAbilityCD = 20;
        this.setAttackState(0);

    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {
        this.head.performRangedAttack(this, pTarget);
        this.headAbilityCD = this.head.getCooldown();
        this.setAttackState(0);
    }


    private void tickCooldown() {
        mouthAbilityCD = Math.max(mouthAbilityCD - 1, 0);
        headAbilityCD = Math.max(headAbilityCD - 1, 0);
    }

    private void addCooldowns() {
        mouthAbilityCD+=10;
        headAbilityCD+=10;
    }


    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isControlledByLocalInstance() && (this.isInFluidType() || this.isFlying())) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.6D));
        } else {
            super.travel(pTravelVector);
        }

    }



    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HEAD_TYPE, -1);
        this.entityData.define(BODY_TYPE, -1);
        this.entityData.define(MOUTH_TYPE, -1);
        this.entityData.define(LEGS_TYPE, -1);
        this.entityData.define(TAIL_TYPE, -1);
        this.entityData.define(PATTERN_TYPE, -1);
        this.entityData.define(PRIMARY_ELEMENT, -1);
        this.entityData.define(SECONDARY_ELEMENT, -1);
        this.entityData.define(PRIMARY_COLOR, 0);
        this.entityData.define(SECONDARY_COLOR, 0);
        this.entityData.define(EYE_COLOR, 0);
        this.entityData.define(IS_FLYING, false);
        this.entityData.define(ATTACK_STATE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("head_type", this.getHeadType());
        tag.putInt("body_type", this.getBodyType());
        tag.putInt("mouth_type", this.getMouthType());
        tag.putInt("legs_type", this.getLegType());
        tag.putInt("tail_type", this.getTailType());
        tag.putInt("pattern_type", this.getPatternType());
        tag.putInt("primary_color", this.getPrimaryColor());
        tag.putInt("secondary_color", this.getSecondaryColor());
        tag.putInt("eye_color", this.getEyeColor());
        tag.putInt("attack_state", this.getAttackState());
        tag.putBoolean("is_flying", this.isFlying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setHeadType(tag.getInt("head_type"));
        this.setBodyType(tag.getInt("body_type"));
        this.setMouthType(tag.getInt("mouth_type"));
        this.setLegType(tag.getInt("legs_type"));
        this.setPatternType(tag.getInt("pattern_type"));
        this.setPrimaryColor(tag.getInt("primary_color"));
        this.setSecondaryColor(tag.getInt("secondary_color"));
        this.setEyeColor(tag.getInt("eye_color"));
        this.setAttackState(tag.getInt("attack_state"));
        this.setFlying(tag.getBoolean("is_flying"));
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {
        SquamorphEntity offspring = ModEntities.SQUAMORPH.get().create(pLevel);
        if (ageableMob instanceof SquamorphEntity e) {
            offspring.getTraitsFromParents(this, e, this.getRandom());
        } else {
            SquamorphHelpers.randomizeParts(offspring, this.getRandom());
        }
        return offspring;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {


        SquamorphHelpers.randomizeParts(this, this.getRandom());

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6f, 1f);
        } else {
            f = 0;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    private void setUpAnimStates() {

        boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6;

        this.wingWalkAnimationState.animateWhen(isMoving && !isFlying(), this.tickCount);
        this.wingIdleAnimationState.animateWhen(!isMoving && !isFlying(), this.tickCount);

        this.idleAnimationState.animateWhen(!isMoving, this.tickCount);
        this.currentMoveAnimationState.animateWhen(this.getAttackState() > 0, this.tickCount);
    }



    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);


        if (key == MOUTH_TYPE) {
            this.refreshMouthType();
            this.refreshMoves(this.mouth);
        }

        else if (key == HEAD_TYPE) {
            this.refreshHeadType();
            this.refreshMoves(this.head);
            if (!this.level().isClientSide())
                this.head.applyStats(this);
        }

        else if (key == BODY_TYPE) {
            this.refreshBodyType();
            this.refreshMoves(this.body);
            if (!this.level().isClientSide())
                this.body.applyStats(this);
        }
        else if (key == LEGS_TYPE) {
            this.refreshLegType();
            this.refreshMoves(this.leg);
            if (!this.level().isClientSide())
                this.leg.applyStats(this);
        }
        else if (key == TAIL_TYPE) {
            this.refreshTailType();
            this.refreshMoves(this.tail);
            if (!this.level().isClientSide())
                this.tail.applyStats(this);
        }
        else if (key == PRIMARY_ELEMENT) {
            this.refreshPrElement();

        }
        else if (key == SECONDARY_ELEMENT) {
            this.refreshScElement();
        }

        else if (key == IS_FLYING) {
            if (this.isFlying()) {
                this.navigation = new FlyingPathNavigation(this, this.level());
                this.moveControl = new SmoothFlyingMoveControl(this, 50, 40);
                this.lookControl = new SmoothSwimmingLookControl(this, 40);
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.5, 0));
            }
            else {
                this.navigation = new AmphibiousPathNavigation(this, this.level());
                this.moveControl = new MoveControl(this);
                this.lookControl = new LookControl(this);
            }
        }


    }

    // passive overrides
    @Override
    public void setAggressive(boolean pAggressive) {
        super.setAggressive(pAggressive);
        if (this.body instanceof SquamorphWings && pAggressive) {
            setFlying(true);
        }
    }

    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pDimensions) {
        return 0.4f;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public boolean fireImmune() {
        if (this.primaryElement == SquamorphElement.FIRE || this.secondaryElement == SquamorphElement.FIRE) return true;
        return super.fireImmune();
    }

    @Override
    public boolean canFreeze() {
        if (this.primaryElement == SquamorphElement.ICE || this.secondaryElement == SquamorphElement.ICE) return true;
        return super.canFreeze();
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public int getAirSupply() {
        return 300;
    }

    @Override
    protected int calculateFallDamage(float pFallDistance, float pDamageMultiplier) {
        return 0;
    }

    private void refreshMoves(SquamorphPart part) {
        if (part.hasMelee()) this.melee_parts.add(part);
        else if (part.hasRanged()) this.ranged_parts.add(part);
    }

    // synched data getters/setters

    public int getHeadType() {
        return this.entityData.get(HEAD_TYPE);
    }

    public void setHeadType(int i) {
        this.entityData.set(HEAD_TYPE, i);
    }

    public int getMouthType() {
        return this.entityData.get(MOUTH_TYPE);
    }

    public void setMouthType(int i) {
        this.entityData.set(MOUTH_TYPE, i);
    }

    public int getBodyType() {
        return this.entityData.get(BODY_TYPE);
    }

    public void setBodyType(int i) {
        this.entityData.set(BODY_TYPE, i);
    }

    public int getLegType() {
        return this.entityData.get(LEGS_TYPE);
    }

    public SquamorphMouth getMouth() {
        return this.mouth;
    }

    public SquamorphHead getHead() {
        return this.head;
    }

    public SquamorphLeg getLeg() {
        return this.leg;
    }

    public SquamorphBody getBody() {
        return this.body;
    }

    public SquamorphTail getTail() {return this.tail;}

    public void setLegType(int i) {
        this.entityData.set(LEGS_TYPE, i);
    }

    private void refreshMouthType() {
        this.mouth = ALL_MOUTHS[this.getMouthType()];
    }

    private void refreshHeadType() {
        this.head = ALL_HEADS[this.getHeadType()];
    }

    private void refreshLegType() {
        this.leg = ALL_LEGS[this.getLegType()];
    }

    private void refreshBodyType() {
        this.body = ALL_BODIES[this.getBodyType()];
    }

    private void refreshTailType() {
        this.tail = ALL_TAILS[this.getTailType()];
    }

    private void refreshPrElement() {
       this.primaryElement = ALL_ELEMENTS[getPrimaryElementIndex()];
    }

    private void refreshScElement() {
        this.secondaryElement = ALL_ELEMENTS[getSecondaryElementIndex()];
    }

    public SquamorphElement getPrimaryElement() {
        return this.primaryElement;
    }

    public SquamorphElement getSecondaryElement() {
        return this.secondaryElement;
    }

    public void setPrimaryElementIndex(int i) {
        this.entityData.set(PRIMARY_ELEMENT, i);
    }

    public void setSecondaryElementIndex(int i) {
        this.entityData.set(SECONDARY_ELEMENT, i);
    }

    public int getPrimaryElementIndex() {
        return this.entityData.get(PRIMARY_ELEMENT);
    }

    public int getSecondaryElementIndex() {
        return this.entityData.get(SECONDARY_ELEMENT);
    }


    public int getTailType() {
        return this.entityData.get(TAIL_TYPE);
    }

    public void setTailType(int i) {
        this.entityData.set(TAIL_TYPE, i);
    }

    public int getPatternType() {
        return this.entityData.get(PATTERN_TYPE);
    }

    public void setPatternType(int i) {
        this.entityData.set(PATTERN_TYPE, i);
    }

    public int getPrimaryColor() {
        return this.entityData.get(PRIMARY_COLOR);
    }

    public void setPrimaryColor(int i) {
        this.entityData.set(PRIMARY_COLOR, i);
    }

    public int getSecondaryColor() {
        return this.entityData.get(SECONDARY_COLOR);
    }

    public void setSecondaryColor(int i) {
        this.entityData.set(SECONDARY_COLOR, i);
    }

    public int getEyeColor() {
        return this.entityData.get(EYE_COLOR);
    }

    public void setEyeColor(int i) {
        this.entityData.set(EYE_COLOR, i);
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int i) {
        this.entityData.set(ATTACK_STATE, i);
    }

    public boolean isFlying() {
        return this.entityData.get(IS_FLYING);
    }

    public void setFlying(boolean b) {
        this.entityData.set(IS_FLYING, b);

    }

    public int getHeadCD() {
        return this.headAbilityCD;
    }

    public int getBodyAbilityCD() {
        return this.bodyAbilityCD;
    }


    public int getMouthAbilityCD() {
        return this.mouthAbilityCD;
    }

    public int getTailAbilityCD() {
        return this.tailAbilityCD;
    }

    public int getLegAbilityCD() {
        return this.legAbilityCD;
    }

    public void setHeadCD(int i) {
        this.headAbilityCD = i;
    }

    public void setMouthCD(int i) {
        this.mouthAbilityCD = i;
    }

    public void setBodyCD(int i) {
        this.bodyAbilityCD = i;
    }

    public void setLegCD(int i) {
        this.legAbilityCD = i;
    }

    public void setTailCD(int i) {
        this.tailAbilityCD = i;
    }


    // TO DO: BABIES INHERIT TRAITS INSTEAD
    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @javax.annotation.Nullable AgeableMob pBaby) {
        if (pBaby instanceof SquamorphEntity baby && pAnimal instanceof SquamorphEntity parent) {
            baby.getTraitsFromParents(this, parent, this.getRandom());
            super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
        }
    }

    public void getTraitsFromParents(SquamorphEntity parent1, SquamorphEntity parent2, RandomSource random) {
        this.setHeadType(random.nextInt(2) == 0 ? parent1.getHeadType() : parent2.getHeadType());
        this.setMouthType(random.nextInt(2) == 0 ? parent1.getMouthType() : parent2.getMouthType());
        this.setBodyType(random.nextInt(2) == 0 ? parent1.getBodyType() : parent2.getBodyType());
        this.setLegType(random.nextInt(2) == 0 ? parent1.getLegType() : parent2.getLegType());
        this.setTailType(random.nextInt(2) == 0 ? parent1.getTailType() : parent2.getTailType());
        this.setPrimaryColor(random.nextInt(2) == 0 ? parent1.getPrimaryColor() : parent2.getPrimaryColor());
        this.setSecondaryColor(random.nextInt(2) == 0 ? parent1.getSecondaryColor() : parent2.getSecondaryColor());
        this.setEyeColor(random.nextInt(2) == 0 ? parent1.getEyeColor() : parent2.getEyeColor());
        this.setPrimaryElementIndex(random.nextInt(2) == 0 ? parent1.getPrimaryElementIndex() : parent2.getPrimaryElementIndex());
        this.setSecondaryElementIndex(random.nextInt(2) == 0 ? parent1.getSecondaryElementIndex() : parent2.getSecondaryElementIndex());

    }


}
