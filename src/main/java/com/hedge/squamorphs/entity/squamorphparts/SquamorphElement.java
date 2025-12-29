package com.hedge.squamorphs.entity.squamorphparts;

import com.hedge.squamorphs.client.particle.ModParticles;
import com.hedge.squamorphs.client.particle.sonic_boom.ElementalSonicBoomParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import org.joml.Vector3f;

import java.util.Vector;

public class SquamorphElement {


    public static final SquamorphElement FIRE = new SquamorphElement("fire", 0xFF9633) {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            affected.setSecondsOnFire(5 + level * 2);
        }

        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.FLAME;
        }

    };

    public static final SquamorphElement ICE = new SquamorphElement("ice", 0x57B9FF) {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
                affected.setTicksFrozen(100 + level * 40);
        }
    };

    public static final SquamorphElement CAUSTIC = new SquamorphElement("caustic", 0x70FF6E) {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (owner.getRandom().nextInt(11) <= chance && affected instanceof LivingEntity e)
                e.addEffect(new MobEffectInstance(MobEffects.POISON, 100 + level * 20, level)); // temporary: replace poison w/ custom effect
        }

        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.ITEM_SLIME;
        }
    };

    public static final SquamorphElement ENDER = new SquamorphElement("ender", 0xF39CFF) {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (owner.getRandom().nextInt(11) <= chance && affected instanceof LivingEntity e)
                e.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 40 + level * 20, level));
        }

        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.DRAGON_BREATH;
        }
    };

    public static final SquamorphElement SOUL = new SquamorphElement("soul", 0x9CEFFF) {

        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.SCULK_SOUL;
        }
    };

    public static final SquamorphElement UMBRA = new SquamorphElement("umbra", 0x4F202F) {

        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.SQUID_INK;
        }
    };

    public static final SquamorphElement RADIANT = new SquamorphElement("radiant", 0xFDFF99) {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (affected instanceof LivingEntity e && e.getMobType() == MobType.UNDEAD) {
                e.hurt(owner.damageSources().magic(), level * 2 + 3);
            }
        }

        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.TOTEM_OF_UNDYING;
        }
    };

    public static final SquamorphElement METAL = new SquamorphElement("metal", 0xA9B5D4) {
        @Override
        public SimpleParticleType getTrailParticle() {
            return ParticleTypes.MYCELIUM;
        }
    };

    public static final SquamorphElement FORCE = new SquamorphElement("force", 0xFF4A4A) {
        @Override
        public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {
            if (affected instanceof LivingEntity e) {
                e.knockback(level * 0.3, owner.getX() - affected.getX(), owner.getZ() - affected.getZ());
            }
        }

        @Override
        public SimpleParticleType getTrailParticle() {
            return ModParticles.FORCE_TRAIL.get();
        }

        @Override
        public String getDescription() {
            return "§fAttacks using the §C§nFORCE §f§relement knock enemies farther back and additionally inflict the §C§nUNBRACED" +
                    " §f§reffect, which increases the amount of fall damage the afflicted experiences.  Squamorphs with the element also" +
                    " heal upon falling, scaling with the height of their descent.";
        }
    };

    public static final SquamorphElement[] ALL_ELEMENTS = {FIRE, ICE, SOUL, ENDER, CAUSTIC, UMBRA, RADIANT, METAL, FORCE};




    private final String name;

    private final int color;

    public SquamorphElement(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public String getElementName() {
        return this.name;
    }

    public String getDescription() {
        return "";
    }

    public void applyElement(Entity affected, LivingEntity owner, int level, int chance) {

    }

    public void applyPassive(LivingEntity entity) {

    }

    public SimpleParticleType getTrailParticle() {
        return ParticleTypes.SNOWFLAKE;
    }

    public ElementalSonicBoomParticleOptions getBoomParticle() {
        return new ElementalSonicBoomParticleOptions(color);
    }


}
