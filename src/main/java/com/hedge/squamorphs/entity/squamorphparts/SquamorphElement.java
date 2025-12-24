package com.hedge.squamorphs.entity.squamorphparts;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobType;

public class SquamorphElement {


    public static final SquamorphElement FIRE = new SquamorphElement("fire") {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            affected.setSecondsOnFire(5 + level * 2);
        }

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.FLAME;
        }

    };

    public static final SquamorphElement ICE = new SquamorphElement("ice") {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
                affected.setTicksFrozen(100 + level * 40);
        }
    };

    public static final SquamorphElement CAUSTIC = new SquamorphElement("caustic") {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (owner.getRandom().nextInt(11) <= chance && affected instanceof LivingEntity e)
                e.addEffect(new MobEffectInstance(MobEffects.POISON, 100 + level * 20, level)); // temporary: replace poison w/ custom effect
        }

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.ITEM_SLIME;
        }
    };

    public static final SquamorphElement ENDER = new SquamorphElement("ender") {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (owner.getRandom().nextInt(11) <= chance && affected instanceof LivingEntity e)
                e.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40 + level * 20, level));
        }

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.DRAGON_BREATH;
        }
    };

    public static final SquamorphElement SOUL = new SquamorphElement("soul") {

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.SCULK_SOUL;
        }
    };

    public static final SquamorphElement UMBRA = new SquamorphElement("umbra") {

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.SQUID_INK;
        }
    };

    public static final SquamorphElement RADIANT = new SquamorphElement("radiant") {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (affected instanceof LivingEntity e && e.getMobType() == MobType.UNDEAD) {
                e.hurt(owner.damageSources().magic(), level * 2 + 3);
            }
        }

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.TOTEM_OF_UNDYING;
        }
    };

    public static final SquamorphElement METAL = new SquamorphElement("metal") {
        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.MYCELIUM;
        }
    };

    public static final SquamorphElement FORCE = new SquamorphElement("force") {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (affected instanceof LivingEntity e) {
                e.knockback(level * 0.1, owner.getX() - affected.getX(), owner.getZ() - affected.getZ());
            }
        }

        @Override
        public SimpleParticleType getParticle() {
            return ParticleTypes.ASH;
        }
    };

    public static final SquamorphElement[] ALL_ELEMENTS = {FIRE, ICE, SOUL, ENDER, CAUSTIC, UMBRA, RADIANT, METAL, FORCE};




    private final String name;

    public SquamorphElement(String name) {
        this.name = name;
    }

    public String getElementName() {
        return this.name;
    }

    public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {

    }

    public void applyPassive(LivingEntity entity) {

    }

    public SimpleParticleType getParticle() {
        return ParticleTypes.SNOWFLAKE;
    }



}
