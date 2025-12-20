package com.hedge.squamorphs.entity.projectile;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.util.EntityHelpers;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;


public class SonarProjectile extends SquamorphProjectile {

    private int bounces = 3;

    public SonarProjectile(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setNoGravity(true);
    }


    protected void onHitEntity(EntityHitResult hit) {
        super.onHitEntity(hit);
        if (this.getOwner() != null) {
            hit.getEntity().hurt(this.getOwner().damageSources().mobProjectile(this, (LivingEntity) this.getOwner()), 3);
            this.element.applyElement(hit.getEntity(), (SquamorphEntity)this.getOwner(), 5, 5);
        }
    }


        @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        bounces--;
        super.onHitBlock(blockHitResult);
        if (bounces < 0)
            discard();
        else {
            this.setDeltaMovement(this.getDeltaMovement().scale(-1).offsetRandom(this.random, 0.5f));
            this.setYRot(this.getYRot() + 180.0F);
            this.yRotO += 180.0F;
        }
    }

    @Override
    public float getSpeed() {
        return 2.5f;
    }


    @Override
    public void trailParticles() {
        /* Vec3 v = getDeltaMovement();
        double length = v.length();
        int c = (int)Math.min(20, Math.round(length) * 3) + 1;
        float f = (float)length / c;
        for (int i = 0; i < c; i++) {
            Vec3 rand = EntityHelpers.getRandomVec3(0.02);
            Vec3 p = v.scale(f * i);
            this.level().addParticle(this.element.getParticle(), this.getX() + rand.x + p.x,
                    this.getY() + rand.y + p.y, this.getZ() + rand.z + p.z, rand.x, rand.z, rand.y);
        }

         */
    }




}
