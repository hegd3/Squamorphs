package com.hedge.squamorphs.entity.squamorphparts.head;

import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.projectile.BoltProjectile;
import com.hedge.squamorphs.entity.projectile.SquamorphProjectile;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class SquamorphHead extends SquamorphPart {


    private final boolean hasEyes;


    public SquamorphHead(int index, int cooldown, String name, boolean hasEyes) {
        super(index, cooldown, name);
        this.hasEyes = hasEyes;
    }

    public SquamorphProjectile getProjectile(SquamorphEntity entity, Level level) {
        BoltProjectile projectile = ModEntities.BOLT.get().create(level);
        if (projectile != null) {
            projectile.setOwner(entity);
            projectile.setElementIndex(entity.getPrimaryElementIndex());
        }
        return projectile;
    }

    @Override
    public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
        SquamorphProjectile projectile = this.getProjectile(owner, owner.level());
        if (projectile != null) {
            projectile.moveTo(owner.getX(), owner.getY(), owner.getZ());
            double d0 = pTarget.getX() - owner.getX();
            double d1 = pTarget.getY() - projectile.getY();

            double d2 = pTarget.getZ() - owner.getZ();
            double d3 = Math.sqrt(d0 * d0 + d2 * d2);
            projectile.shoot(d0, d1 + d3 * 0.05, d2, 1F, 0);
            owner.level().addFreshEntity(projectile);
        }
    }

    public int getAbilityAnimState() {return 2;}

    public double getRange() {
        return 400;
    }

    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        double d0 = owner.getPerceivedTargetDistanceSquareForMeleeAttack(target);
        return owner.getHeadCD() <= 0 && d0 < getRange();
    }

    @Override
    public String getName() {
        return "basic head";
    }

    @Override
    public boolean hasRanged() {
        return true;
    }

    public boolean hasEyes() {
        return this.hasEyes;
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks >= 8) {
            this.performRangedAttack(entity, target);
            entity.setHeadCD(this.getCooldown());
            entity.setAttackState(0);
        }
    }
}
