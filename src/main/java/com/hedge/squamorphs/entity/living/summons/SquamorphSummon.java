package com.hedge.squamorphs.entity.living.summons;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphElement;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.scores.Team;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.UUID;

public abstract class SquamorphSummon extends PathfinderMob implements OwnableEntity {

    protected static final EntityDataAccessor<Integer> ELEMENT_INDEX = SynchedEntityData.defineId(SquamorphSummon.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Integer> LEVEL = SynchedEntityData.defineId(SquamorphSummon.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Optional<UUID>> DATA_OWNERUUID_ID = SynchedEntityData.defineId(SquamorphSummon.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(SquamorphSummon.class, EntityDataSerializers.INT);

    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();


    private LivingEntity owner;
    private SquamorphElement element = SquamorphElement.FIRE;
    private int lifespan = 200;

    protected SquamorphSummon(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ELEMENT_INDEX, 0);
        this.entityData.define(LEVEL, 0);
        this.entityData.define(DATA_OWNERUUID_ID, null);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("summon_level", this.getEntityData().get(LEVEL));
        tag.putInt("attack_state", this.getEntityData().get(ATTACK_STATE));
        tag.putInt("elementIndex", this.getEntityData().get(ELEMENT_INDEX));
        if (this.getOwnerUUID() != null)
            tag.putUUID("owner", this.getOwnerUUID());

    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setOwnerUUID(tag.getUUID("owner"));
        this.setAttackState(tag.getInt("attack_state"));
        this.setSummonLevel(tag.getInt("summon_level"));
        this.setElementIndex(tag.getInt("elementIndex"));
    }


    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        super.onSyncedDataUpdated(pKey);
        if (DATA_OWNERUUID_ID == pKey) {
            Entity potentialOwner = ((ServerLevel)this.level()).getEntity(this.getOwnerUUID());
            if (potentialOwner instanceof LivingEntity e) {
                this.owner = e;
            }
        } else if (ELEMENT_INDEX == pKey) {
            this.element = SquamorphElement.ALL_ELEMENTS[this.getElementIndex()];
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide()) {
            this.lifespan--;
            if (this.lifespan <= 0) {
                this.discard();
            }
        } else {
            this.setUpAnimStates();
        }
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


    @Override
    public Team getTeam() {
        if (this.owner != null)
            return this.owner.getTeam();

        return super.getTeam();
    }
    @Override
    public boolean isAlliedTo(Entity pEntity) {
        if (this.owner != null) {
            if (pEntity == owner) {
                return true;
            }

            if (owner != null) {
                return owner.isAlliedTo(pEntity);
            }
        }

        return super.isAlliedTo(pEntity);
    }

    public void setUpAnimStates() {

        boolean isMoving = this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6;
        this.idleAnimationState.animateWhen(!isMoving, this.tickCount);
    }

        public void summon(SquamorphEntity entity, int elementIndex, int summonLevel) {
        this.setOwnerUUID(entity.getUUID());
        this.setElementIndex(elementIndex);
        this.setSummonLevel(summonLevel);
    }

    public abstract void applyLevelStats();

    public void setElementIndex(int i) {
        this.entityData.set(ELEMENT_INDEX, i);
    }

    public int getElementIndex() {
        return this.entityData.get(ELEMENT_INDEX);
    }

    public void setSummonLevel(int i) {
        this.entityData.set(LEVEL, i);
    }

    public int getSummonLevel() {
        return this.entityData.get(LEVEL);
    }

    public int getLifespan() {
        return this.lifespan;
    }

    public void setLifespan(int i) {
        this.lifespan = i;
    }

    public int getAttackState() {
        return this.entityData.get(ATTACK_STATE);
    }

    public void setAttackState(int i) {
        this.entityData.set(ATTACK_STATE, i);
    }

    @Override
    public @Nullable UUID getOwnerUUID() {
        return this.entityData.get(DATA_OWNERUUID_ID).orElse((UUID)null);
    }

    public void setOwnerUUID(@Nullable UUID pUuid) {
        this.entityData.set(DATA_OWNERUUID_ID, Optional.ofNullable(pUuid));
    }

    @Override
    public @Nullable LivingEntity getOwner() {
        return this.owner;
    }


}
