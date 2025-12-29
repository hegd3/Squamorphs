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
import com.hedge.squamorphs.entity.util.EntityHelpers;
import com.hedge.squamorphs.entity.util.SquamorphHelpers;
import com.hedge.squamorphs.entity.util.goals.SquamorphAttackGoal;
import com.hedge.squamorphs.entity.util.goals.SquamorphWanderGoal;
import com.hedge.squamorphs.entity.util.navigation.SquamorphLookControl;
import com.hedge.squamorphs.entity.util.navigation.SquamorphMoveControl;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.hedge.squamorphs.entity.squamorphparts.AllParts.*;
import static com.hedge.squamorphs.entity.squamorphparts.SquamorphElement.ALL_ELEMENTS;

public class SquamorphEntity extends Animal {

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
    private static final EntityDataAccessor<Boolean> LEFT = SynchedEntityData.defineId(SquamorphEntity.class, EntityDataSerializers.BOOLEAN);


    public final AnimationState idleAnimationState = new AnimationState();

    public final AnimationState wingIdleAnimationState = new AnimationState();
    public final AnimationState wingWalkAnimationState = new AnimationState();


    public final AnimationState currentMoveAnimationState = new AnimationState();

    private SquamorphHead head = ALL_HEADS[0];
    private SquamorphMouth mouth = ALL_MOUTHS[0];
    private SquamorphBody body = ALL_BODIES[0];
    private SquamorphLeg leg = ALL_LEGS[0];
    private SquamorphTail tail = ALL_TAILS[0];
    private SquamorphPart currentMove;
    private ArrayList<SquamorphPart> ranged_parts = new ArrayList<SquamorphPart>();
    private ArrayList<SquamorphPart> melee_parts = new ArrayList<SquamorphPart>();

    private SquamorphElement primaryElement = SquamorphElement.FIRE;
    private SquamorphElement secondaryElement = SquamorphElement.FIRE;
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
        this.lookControl = new SquamorphLookControl(this, 40);
        this.moveControl = new SquamorphMoveControl(this, 60, 40, 0.9f);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f);
        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0f);
        this.setMaxUpStep(1);
    }

    public static AttributeSupplier.Builder bakeAttributes(){
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.0D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D)
                .add(Attributes.FOLLOW_RANGE, 40F)
                .add(Attributes.MOVEMENT_SPEED, 0.3F);
    }

    private void resetStats() {
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20);
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2.0);
        this.getAttribute(Attributes.ATTACK_KNOCKBACK).setBaseValue(0.5D);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.3D);
        this.setHealth(20);
        this.head.applyStats(this);
        this.mouth.applyStats(this);
        this.body.applyStats(this);
        this.leg.applyStats(this);
        this.tail.applyStats(this);
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
            if (this.getAttackState() > 0) {
                animTicks++;
            } else {
                animTicks = 0;
            }
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
            if (!this.isAggressive() && this.tickCount % 100 == 0) {
                this.heal(2);
            }
        }
    }

    public void startAttackAnim(int animIndex) {

        animTicks = 0;

        this.setAttackState(animIndex);
    }

    private void tickAttack(LivingEntity target) {

        if (this.getAttackState() > 0) {
            if (target != null) {
                animTicks++;
                if (this.getCurrentMove() != null) {
                    double d0 = this.getPerceivedTargetDistanceSquareForMeleeAttack(target);
                    this.currentMove.tickAttack(this, animTicks, target, d0);
                }
            }
            else {
                this.setAttackState(0);
                this.addCooldowns();
                this.resetMove();
            }
        }


    }


    private void tickCooldown() {
        mouthAbilityCD = Math.max(mouthAbilityCD - 1, 0);
        headAbilityCD = Math.max(headAbilityCD - 1, 0);
        bodyAbilityCD = Math.max(bodyAbilityCD - 1, 0);
        tailAbilityCD = Math.max(tailAbilityCD - 1, 0);
        legAbilityCD = Math.max(legAbilityCD - 1, 0);
    }

    public void addCooldowns() {
        mouthAbilityCD+=5;
        headAbilityCD+=5;
        bodyAbilityCD+=5;
        tailAbilityCD+=5;
        legAbilityCD+=5;
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

    /*
    @Override
    public boolean isAlliedTo(Entity pEntity) {
        return pEntity.isAlliedTo(this) || super.isAlliedTo(pEntity);
    }

     */

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HEAD_TYPE, -1);
        this.entityData.define(BODY_TYPE, -1);
        this.entityData.define(MOUTH_TYPE, -1);
        this.entityData.define(LEGS_TYPE, -1);
        this.entityData.define(TAIL_TYPE, -1);
        this.entityData.define(PATTERN_TYPE, -1);
        this.entityData.define(HEAD_LEVEL, 0);
        this.entityData.define(MOUTH_LEVEL, 0);
        this.entityData.define(BODY_LEVEL, 0);
        this.entityData.define(LEGS_LEVEL, 0);
        this.entityData.define(TAIL_LEVEL, 0);
        this.entityData.define(PRIMARY_ELEMENT, 0);
        this.entityData.define(SECONDARY_ELEMENT, 0);
        this.entityData.define(PRIMARY_COLOR, 0);
        this.entityData.define(SECONDARY_COLOR, 0);
        this.entityData.define(EYE_COLOR, 0);
        this.entityData.define(IS_FLYING, false);
        this.entityData.define(ATTACK_STATE, 0);
        this.entityData.define(LEFT, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("primary_element", this.getPrimaryElementIndex());
        tag.putInt("secondary_element", this.getSecondaryElementIndex());
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
        tag.putInt("head_level", this.getHeadLevel());
        tag.putInt("mouth_level", this.getMouthLevel());
        tag.putInt("body_level", this.getBodyLevel());
        tag.putInt("legs_level", this.getLegsLevel());
        tag.putInt("tail_level", this.getTailLevel());

        tag.putBoolean("is_flying", this.isFlying());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setPrimaryElementIndex(tag.getInt("primary_element"));
        this.setSecondaryElementIndex(tag.getInt("secondary_element"));
        this.setHeadType(tag.getInt("head_type"));
        this.setBodyType(tag.getInt("body_type"));
        this.setMouthType(tag.getInt("mouth_type"));
        this.setLegType(tag.getInt("legs_type"));
        this.setTailType(tag.getInt("tail_type"));
        this.setPatternType(tag.getInt("pattern_type"));
        this.setPrimaryColor(tag.getInt("primary_color"));
        this.setSecondaryColor(tag.getInt("secondary_color"));
        this.setEyeColor(tag.getInt("eye_color"));
        this.setAttackState(tag.getInt("attack_state"));
        this.setFlying(tag.getBoolean("is_flying"));
        this.setHeadLevel(tag.getInt("head_level"));
        this.setMouthLevel(tag.getInt("mouth_level"));
        this.setBodyLevel(tag.getInt("body_level"));
        this.setLegsLevel(tag.getInt("legs_level"));
        this.setTailLevel(tag.getInt("tail_level"));

    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob ageableMob) {
        SquamorphEntity offspring = ModEntities.SQUAMORPH.get().create(pLevel);
        offspring.copyTraits(this);
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
            this.head.applyStats(this);
        }

        else if (key == BODY_TYPE) {
            this.refreshBodyType();
            this.refreshMoves(this.body);
            this.body.applyStats(this);
        }
        else if (key == LEGS_TYPE) {
            this.refreshLegType();
            this.refreshMoves(this.leg);
            this.leg.applyStats(this);
        }
        else if (key == TAIL_TYPE) {
            this.refreshTailType();
            this.refreshMoves(this.tail);
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
                this.setDeltaMovement(this.getDeltaMovement().add(0, 0.5, 0));
            }
            else {
                this.navigation = new AmphibiousPathNavigation(this, this.level());
            }
        }


    }

    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
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
    public boolean isImmobile() {
        if (this.getCurrentMove() != null) {
            return this.getCurrentMove().isImmobileWhenUsing(this) || super.isImmobile();
        }
        return super.isImmobile();
    }

    @Override
    public boolean isInvulnerable() {
        if (this.getCurrentMove() != null) {
            return this.getCurrentMove().isInvulernableWhenUsing(this) || super.isInvulnerable();
        }
        return super.isInvulnerable();
    }

    @Override
    public boolean isAlliedTo(Entity pEntity) {
        if (pEntity instanceof OwnableEntity e && e.getOwner() == this) {
            return true;
        }
        return super.isAlliedTo(pEntity);
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
        if (part.hasMelee()) {
            this.melee_parts.add(part);
            this.melee_parts.sort(Comparator.comparing(SquamorphPart::getCooldown));
        } else if (part.hasRanged()) {
            this.ranged_parts.add(part);
            this.ranged_parts.sort(Comparator.comparing(SquamorphPart::getCooldown));

        }

    }

    public List<LivingEntity> aoeAttack(double vecScale, double pX, double pY, double pZ, float damage, float kbMultiplier, int maxHit, SquamorphElement element, int level) {

        Vec3 lookVec = this.getLookAngle();
        Vec3 origin = this.position().add(lookVec.scale(vecScale));
        int count = 0;
        AABB aoe = new AABB(origin.subtract(pX, pY, pZ), origin.add(pX, pY, pZ));

        List<LivingEntity> hit = this.level().getEntitiesOfClass(LivingEntity.class, aoe, (target) ->
                target.isAlive() && this.hasLineOfSight(target) && !this.isAlliedTo(target) && this != target);

        for (LivingEntity target: hit) {
            target.hurt(target.damageSources().mobAttack(this), damage);
            target.knockback(0.8D + 0.5D * kbMultiplier, this.getX() - target.getX(), this.getZ() - target.getZ());
            element.applyElement(target, this, level, 1);
            EntityHelpers.particleOnhitEffect(element.getTrailParticle(), target, this.level(), 1);

            count++;
                if (count >= maxHit)
                    break;

        }
        return hit;
    }

    public void betterDoHurt(LivingEntity target, float damage, float kbMultiplier) {
        target.hurt(target.damageSources().mobAttack(this), damage);
        target.knockback(0.8D + 0.5D * kbMultiplier, this.getX() - target.getX(), this.getZ() - target.getZ());

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

    public void setAttackDirection(boolean b) {
        this.entityData.set(LEFT, b);
    }

    public boolean isAttackingLeft() {
        return this.entityData.get(LEFT);
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

    public int getHeadLevel() {
        return this.entityData.get(HEAD_LEVEL);
    }

    public int getMouthLevel() {
        return this.entityData.get(MOUTH_LEVEL);
    }

    public int getBodyLevel() {
        return this.entityData.get(BODY_LEVEL);
    }

    public int getLegsLevel() {
        return this.entityData.get(LEGS_LEVEL);
    }

    public int getTailLevel() {
        return this.entityData.get(TAIL_LEVEL);
    }

    public void setHeadLevel(int i) {
        this.entityData.set(HEAD_LEVEL, i);
    }

    public void setMouthLevel(int i) {
        this.entityData.set(MOUTH_LEVEL, i);
    }

    public void setBodyLevel(int i) {
        this.entityData.set(BODY_LEVEL, i);
    }

    public void setLegsLevel(int i) {
        this.entityData.set(LEGS_LEVEL, i);
    }

    public void setTailLevel(int i) {
        this.entityData.set(TAIL_LEVEL, i);
    }

    public void setAnimTicks(int i) {
        this.animTicks = i;
    }

    public int getAnimTicks() {
        return this.animTicks;
    }

    public SquamorphPart getCurrentMove() {
        return this.currentMove;
    }

    public void resetMove() {
        this.currentMove = null;
    }

    public void selectPart(LivingEntity target) {
        for (SquamorphPart part: this.getMeleeParts()) {
            if (part.canUseAbility(this, target)) {
                this.currentMove = part;
                return;
            }
        }
        for (SquamorphPart part: this.getRangedParts()) {
            if (part.canUseAbility(this, target)) {
                this.currentMove = part;
                return;
            }
        }
        this.currentMove = null;
    }

    public ArrayList<SquamorphPart> getMeleeParts() {
        return this.melee_parts;
    }

    public ArrayList<SquamorphPart> getRangedParts() {
        return this.ranged_parts;
    }

    // TO DO: BABIES INHERIT TRAITS INSTEAD
    @Override
    public void finalizeSpawnChildFromBreeding(ServerLevel pLevel, Animal pAnimal, @javax.annotation.Nullable AgeableMob pBaby) {
        super.finalizeSpawnChildFromBreeding(pLevel, pAnimal, pBaby);
        if (!this.level().isClientSide() && pBaby instanceof SquamorphEntity baby && pAnimal instanceof SquamorphEntity parent) {
            baby.getTraitsFromParents(this, parent, this.getRandom());
        }
    }

    public void getTraitsFromParents(SquamorphEntity parent1, SquamorphEntity parent2, RandomSource random) {

        this.setHeadType(random.nextInt(2) == 0 ? parent1.getHeadType() : parent2.getHeadType());
        this.setMouthType(random.nextInt(2) == 0 ? parent1.getMouthType() : parent2.getMouthType());
        this.setBodyType(random.nextInt(2) == 0 ? parent1.getBodyType() : parent2.getBodyType());
        this.setLegType(random.nextInt(2) == 0 ? parent1.getLegType() : parent2.getLegType());
        this.setTailType(random.nextInt(2) == 0 ? parent1.getTailType() : parent2.getTailType());
        this.setEyeColor(parent1.getEyeColor() + parent2.getEyeColor());
        this.setPrimaryElementIndex(random.nextInt(2) == 0 ? parent1.getPrimaryElementIndex() : parent2.getPrimaryElementIndex());
        this.setSecondaryElementIndex(random.nextInt(2) == 0 ? parent1.getSecondaryElementIndex() : parent2.getSecondaryElementIndex());
        this.setPrimaryColor(SquamorphHelpers.generateBodyColor(this.getPrimaryElementIndex(), random));
        this.setSecondaryColor(SquamorphHelpers.generateBodyColor(this.getSecondaryElementIndex(), random));


    }


    public void copyTraits(SquamorphEntity entity) {

        this.setHeadType(entity.getHeadType());
        this.setMouthType(entity.getMouthType());
        this.setBodyType(entity.getBodyType());
        this.setLegType(entity.getLegType());
        this.setTailType(entity.getTailType());
        this.setPrimaryColor(entity.getPrimaryColor());
        this.setSecondaryColor(entity.getSecondaryColor());
        this.setEyeColor(entity.getEyeColor());
        this.setPrimaryElementIndex(entity.getPrimaryElementIndex());
        this.setSecondaryElementIndex(entity.getSecondaryElementIndex());
        this.setPatternType(entity.getPatternType());
    }


}
