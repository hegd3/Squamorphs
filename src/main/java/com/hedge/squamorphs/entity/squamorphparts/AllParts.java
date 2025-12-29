package com.hedge.squamorphs.entity.squamorphparts;

import com.hedge.squamorphs.client.animations.SquamorphAbilityAnimation;
import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.client.particle.ModParticles;
import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalSpiritEntity;
import com.hedge.squamorphs.entity.projectile.BlastProjectile;
import com.hedge.squamorphs.entity.projectile.BoltProjectile;
import com.hedge.squamorphs.entity.projectile.SonarProjectile;
import com.hedge.squamorphs.entity.projectile.SquamorphProjectile;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphBody;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphWings;
import com.hedge.squamorphs.entity.squamorphparts.head.SquamorphHead;
import com.hedge.squamorphs.entity.squamorphparts.head.StrongSquamorphHead;
import com.hedge.squamorphs.entity.squamorphparts.legs.*;
import com.hedge.squamorphs.entity.squamorphparts.mouth.SquamorphMouth;
import com.hedge.squamorphs.entity.squamorphparts.tail.SquamorphSwingingTail;
import com.hedge.squamorphs.entity.squamorphparts.tail.SquamorphTail;
import com.hedge.squamorphs.entity.squamorphparts.tail.SquamorphWaggingTail;
import com.hedge.squamorphs.entity.util.EntityHelpers;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class AllParts {


    // HEADS




    public static final SquamorphHead HEAD0 = new SquamorphHead(0, 60, "basic head", false);
    public static final SquamorphHead HEAD1 = new SquamorphHead(1, 60, "croc eyes", true) {

    };
    public static final SquamorphHead HEAD2 = new SquamorphHead(2, 200, "spiky horns", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }

        @Override
        public boolean hasMelee() {return true;}

        @Override
        public boolean hasRanged() {return false;}

        @Override
        public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
            return SquamorphAbilityAnimation.head_charge;
        }

        @Override
        public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
            if (animTicks >= 100) {
                entity.addCooldowns();
                entity.setHeadCD(this.getCooldown());
                entity.setAttackState(0);
                entity.resetMove();
            } else if (animTicks % 5 == 0 ) {
                entity.setDeltaMovement(entity.getDeltaMovement().add(entity.getLookAngle().scale(0.5)));
                entity.aoeAttack(0.5, 1, 1, 1, this.getDamage(entity), 0, 8, entity.getPrimaryElement(), entity.getHeadLevel());

            }
        }

        @Override
        public double getRange() {
            return 250;
        }

        @Override
        public float getDamage(SquamorphEntity owner) {
            return (float)owner.getAttribute(Attributes.MOVEMENT_SPEED).getValue() * 2 + (float)Math.pow(owner.getHeadLevel(), 1.5) * 0.6f;
        }
    };
    public static final SquamorphHead HEAD3 = new SquamorphHead(3, 60, "bat ears", false) {

        @Override
        public SquamorphProjectile getProjectile(SquamorphEntity entity, Level level) {
            SonarProjectile projectile = ModEntities.SONAR.get().create(level);
            if (projectile != null) {
                projectile.setOwner(entity);
                projectile.setLvl(entity.getHeadLevel());
                projectile.setElementIndex(entity.getPrimaryElementIndex());
            }
            return projectile;
        }
    };
    public static final SquamorphHead HEAD4 = new StrongSquamorphHead(4, 100, "crowned head", false) {
        @Override
        public SquamorphProjectile getProjectile(SquamorphEntity entity, Level level) {
            BlastProjectile projectile = ModEntities.BLAST.get().create(level);
            if (projectile != null) {
                projectile.setOwner(entity);
                projectile.setLvl(entity.getHeadLevel());
                projectile.setElementIndex(entity.getPrimaryElementIndex());
            }
            return projectile;
        }
    };
    public static final SquamorphHead HEAD5 = new SquamorphHead(5, 60, "head spike", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphHead HEAD6 = new SquamorphHead(6, 60, "hairy head", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphHead HEAD7 = new SquamorphHead(7, 60, "head frills", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphHead HEAD8 = new SquamorphHead(8, 60, "arachnid eyes", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getEyeColor();
        }
    };
    public static final SquamorphHead HEAD9 = new SquamorphHead(9, 60, "tall eyes", true) {

    };
    public static final SquamorphHead HEAD10 = new SquamorphHead(10, 60, "chameleon eyes", true) {

    };
    public static final SquamorphHead HEAD11 = new SquamorphHead(11, 60, "whirl stalk", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphHead HEAD12 = new SquamorphHead(12, 60, "feathered crest", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphHead HEAD13 = new SquamorphHead(13, 60, "one eye", true) {

    };
    public static final SquamorphHead HEAD14 = new SquamorphHead(14, 200, "compound eyes", true) {

        @Override
        public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
            ElementalFlyEntity fly = ModEntities.ELEMENTAL_FLY.get().create(owner.level());
            if (fly != null) {
                fly.summon(owner, owner.getPrimaryElementIndex(), owner.getHeadLevel());
                owner.level().addFreshEntity(fly);
            }
        }

    };
    public static final SquamorphHead HEAD15 = new SquamorphHead(15, 60, "four eyes", true) {

    };
    public static final SquamorphHead HEAD16 = new SquamorphHead(16, 60, "nocturnal eyes", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getEyeColor();
        }
    };
    public static final SquamorphHead HEAD17 = new SquamorphHead(17, 60, "six eyes", true) {

    };
    public static final SquamorphHead HEAD18 = new SquamorphHead(18, 60, "frog eyes", true) {

    };
    public static final SquamorphHead HEAD19 = new StrongSquamorphHead(19, 160, "drab antennae", false) {
        @Override
        public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
            Vec3 vec3 = owner.getEyePosition();
            Vec3 vec31 = pTarget.getEyePosition().subtract(vec3);
            Vec3 vec32 = vec31.normalize();

            for(int i = 1; i < Mth.floor(vec31.length()) + 7; ++i) {
                Vec3 vec33 = vec3.add(vec32.scale((double)i));
                ((ServerLevel)(owner.level())).sendParticles(owner.getPrimaryElement().getBoomParticle(), vec33.x, vec33.y, vec33.z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
            }
            pTarget.hurt(owner.level().damageSources().sonicBoom(owner), this.getDamage(owner));
            owner.getPrimaryElement().applyElement(pTarget, owner, owner.getHeadLevel(), 10);
        }

        @Override
        public float getDamage(SquamorphEntity owner) {
            return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 5 + (float)Math.pow(owner.getHeadLevel(), 1.5) * 1.5f;
        }

        @Override
        public boolean isImmobileWhenUsing(SquamorphEntity entity) {
            return entity.getAnimTicks() < 7;
        }
    };

    public static final SquamorphHead[] ALL_HEADS = {HEAD0, HEAD1, HEAD2, HEAD3, HEAD4, HEAD5, HEAD6, HEAD7, HEAD8, HEAD9,
            HEAD10, HEAD11, HEAD12, HEAD13, HEAD14, HEAD15, HEAD16, HEAD17, HEAD18, HEAD19};

    // MOUTHS

    public static final SquamorphMouth MOUTH0 = new SquamorphMouth(0,20, "basic mouth", false, false);

    public static final SquamorphMouth MOUTH1 = new SquamorphMouth(1,20, "piscivore mouth", true, true);

    public static final SquamorphMouth MOUTH2 = new SquamorphMouth(2,20, "beaky mouth", false, false);

    public static final SquamorphMouth MOUTH3 = new SquamorphMouth(3,20, "vampiric fangs", false, true) {
        @Override
        public int getColor(SquamorphEntity entity) {return entity.getSecondaryColor();}

        @Override
        public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
            double d0 = owner.getMeleeAttackRangeSqr(target);
            if (dist <= d0) {
                owner.swing(InteractionHand.MAIN_HAND);
                if (owner.doHurtTarget(target)) {
                    owner.getPrimaryElement().applyElement(target, owner, owner.getMouthLevel(), 5);
                    owner.heal(4.0f + (float)Math.pow(owner.getMouthLevel() * 2, 1.5));
                    EntityHelpers.particleOnhitEffect(owner.getPrimaryElement().getTrailParticle(), target, owner.level(), 1);
                }
            }
        }


    };

    public static final SquamorphMouth MOUTH4 = new SquamorphMouth(4,20, "venomous teeth", true, true);

    public static final SquamorphMouth MOUTH5 = new SquamorphMouth(5,20, "hatchet snout", true, true);

    public static final SquamorphMouth MOUTH6 = new SquamorphMouth(6,30, "tusked snout", true, true) {
        @Override
        public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
            List<LivingEntity> aoe = owner.aoeAttack(1.0, 1.5, 2, 1, this.getDamage(owner), 1.1f, 4, owner.getPrimaryElement(), owner.getMouthLevel());
            for (LivingEntity e : aoe) {
                double f = 2.0 - e.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue();
                if (f > 0)
                    e.setDeltaMovement(e.getDeltaMovement().add(0, f, 0));
            }
        }

        @Override
        public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
            if (animTicks == 10) {
                this.performMeleeAttack(entity, target, dist);
            } else if (animTicks >= 15) {
                entity.addCooldowns();
                entity.setMouthCD(this.getCooldown());
                entity.setAttackState(0);
                entity.resetMove();
            }
        }

        @Override
        public float getDamage(SquamorphEntity owner) {
            return super.getDamage(owner) + owner.getMouthLevel() * owner.getMouthLevel() * 1.2f;
        }

        @Override
        public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
            return SquamorphAbilityAnimation.head_flick;
        }
    };

    public static final SquamorphMouth MOUTH7 = new SquamorphMouth(7,80, "sawtooth", false, true) {
        @Override
        public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
            return owner.getMouthAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) <= owner.getBbWidth() * 4.0F * owner.getBbWidth() * 4.0F + target.getBbWidth();
        }

        @Override
        public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
            return squamorphAnimation.sawing;
        }

        @Override
        public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {

            if (animTicks >= 100) {
                entity.addCooldowns();
                entity.setMouthCD(this.getCooldown());
                entity.setAttackState(0);
                entity.resetMove();
            } else if (animTicks % 5 == 0) {
                this.performMeleeAttack(entity, target, dist);
            }
        }

        @Override
        public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
            owner.aoeAttack(0.65, 1, 1, 0.65, this.getDamage(owner), 0, 8, owner.getPrimaryElement(), owner.getMouthLevel());

        }

        @Override
        public float getDamage(SquamorphEntity owner) {
            return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 0.25f + owner.getMouthLevel() * owner.getMouthLevel() * 0.5f;
        }
    };


    public static final SquamorphMouth MOUTH8 = new SquamorphMouth(8,20, "arachnid fangs", false, false);

    public static final SquamorphMouth MOUTH9 = new SquamorphMouth(9,20, "lower teeth", false, false);

    public static final SquamorphMouth MOUTH10 = new SquamorphMouth(10,20, "horned snout", true, false);

    public static final SquamorphMouth MOUTH11 = new SquamorphMouth(11,20, "big mouth", true, false);

    public static final SquamorphMouth MOUTH12 = new SquamorphMouth(12,20, "avian beak", false, false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }

    };

    public static final SquamorphMouth MOUTH13 = new SquamorphMouth(13,20, "monster mouth", true, true);

    public static final SquamorphMouth MOUTH14 = new SquamorphMouth(14,20, "proboscis", false, false);

    public static final SquamorphMouth MOUTH15 = new SquamorphMouth(15,20, "underbite", true, true);

    public static final SquamorphMouth MOUTH16 = new SquamorphMouth(16,20, "knife snout", false, false);

    public static final SquamorphMouth MOUTH17 = new SquamorphMouth(17,20, "simple snout", true, false);

    public static final SquamorphMouth MOUTH18 = new SquamorphMouth(18,20, "saber teeth", false, false);

    public static final SquamorphMouth MOUTH19 = new SquamorphMouth(19,40, "mandibles", false, false) {
        @Override
        public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
            if (animTicks == 12) {
                this.performMeleeAttack(entity, target, dist);
            } else if (animTicks >= 15) {
                entity.addCooldowns();
                entity.setMouthCD(this.getCooldown());
                entity.setAttackState(0);
                entity.resetMove();
            }
        }

        @Override
        public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
            return squamorphAnimation.bite_mandibles;
        }

    };



    public static final SquamorphMouth[] ALL_MOUTHS = {MOUTH0, MOUTH1, MOUTH2, MOUTH3, MOUTH4, MOUTH5, MOUTH6, MOUTH7, MOUTH8, MOUTH9, MOUTH10,
            MOUTH11, MOUTH12, MOUTH13, MOUTH14, MOUTH15, MOUTH16, MOUTH17, MOUTH18, MOUTH19};

    // BODY

    public static final SquamorphBody BODY0 = new SquamorphBody(0, 0, "basic body");
    public static final SquamorphBody BODY1 = new SquamorphBody(1, 0, "dorsal fin");
    public static final SquamorphBody BODY2 = new SquamorphBody(2, 0, "armored shell") {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphBody BODY3 = new SquamorphWings(3, 0, "bat wings");
    public static final SquamorphBody BODY4 = new SquamorphBody(4, 0, "cobra flaps");
    public static final SquamorphBody BODY5 = new SquamorphBody(5, 0, "curved spikes") {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphBody BODY6 = new SquamorphBody(6, 0, "sparse hairs");
    public static final SquamorphBody BODY7 = new SquamorphBody(7, 0, "hearts") {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphBody BODY8 = new SquamorphWings(8, 0, "lace wings") {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphBody BODY9 = new SquamorphBody(9, 0, "shark fin");
    public static final SquamorphBody BODY10 = new SquamorphBody(10, 0, "osteoderms");
    public static final SquamorphBody BODY11 = new SquamorphBody(11, 0, "whirl growths") {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphBody BODY12 = new SquamorphWings(12, 0, "bird wings");
    public static final SquamorphBody BODY13 = new SquamorphBody(13, 0, "neural spines");
    public static final SquamorphBody BODY14 = new SquamorphWings(14, 0, "butterfly wings") {
        @Override
        public AnimationDefinition getWalk() {
            return squamorphAnimation.wings_walk2;
        }

        @Override
        public AnimationDefinition getIdle() {
            return squamorphAnimation.wings_idle2;
        }

        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }

    };
    public static final SquamorphBody BODY15 = new SquamorphBody(15, 0, "dorsal spikes") {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }

    };
    public static final SquamorphBody BODY16 = new SquamorphWings(16, 0, "monstrous wings") {

    };
    public static final SquamorphBody BODY17 = new SquamorphBody(17, 0, "sail");
    public static final SquamorphBody BODY18 = new SquamorphBody(18, 0, "tall hairs");
    public static final SquamorphBody BODY19 = new SquamorphBody(19, 10, "back cannon") {

        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }

        @Override
        public boolean hasRanged() {
            return true;
        }

        @Override
        public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {return squamorphAnimation.cannon_shoot;}


        @Override
        public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
            BoltProjectile projectile = ModEntities.BOLT.get().create(owner.level());
            if (projectile != null) {
                projectile.setOwner(owner);
                projectile.setLvl(owner.getBodyLevel());
                projectile.setElementIndex(owner.getSecondaryElementIndex());
                projectile.moveTo(owner.getX(), owner.getY() + 0.5, owner.getZ());
                double d0 = pTarget.getX() - owner.getX();
                double d1 = pTarget.getY() - projectile.getY();

                double d2 = pTarget.getZ() - owner.getZ();
                double d3 = Math.sqrt(d0 * d0 + d2 * d2);
                projectile.shoot(d0, d1 + d3 * 0.05, d2, 1F, 0);
                owner.level().addFreshEntity(projectile);
            }
        }

        @Override
        public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
            double d0 = owner.getPerceivedTargetDistanceSquareForMeleeAttack(target);
            return owner.getBodyAbilityCD() <= 0 && d0 < owner.getHead().getRange();
        }

        @Override
        public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
            if (animTicks == 3) {
                this.performRangedAttack(entity, target);
            } else if (animTicks >= 7) {
                entity.addCooldowns();
                entity.setBodyCD(this.getCooldown());
                entity.setAttackState(0);
                entity.resetMove();
            }
        }
    };
    public static final SquamorphBody BODY20 = new SquamorphBody(20, 0, "shiny shell") {

    };

    public static final SquamorphBody[] ALL_BODIES = {BODY0, BODY1, BODY2, BODY3, BODY4, BODY5, BODY6, BODY7, BODY8, BODY9, BODY10,
    BODY11, BODY12, BODY13, BODY14, BODY15, BODY16, BODY17, BODY18, BODY19, BODY20};





        // LEGS

    public static final SquamorphLeg LEG0 = new SquamorphLegless();
    public static final SquamorphLeg LEG1 = new SquamorphLeg(1, 0, "basic legs") {

    };
    public static final SquamorphLeg LEG2 = new SquamorphLeg(2, 0, "fish legs") {

    };
    public static final SquamorphLeg LEG3 = new SquamorphCubicLeg(3, 100, "bulky legs") {

    };
    public static final SquamorphLeg LEG4 = new SquamorphLeg(4, 0, "fork legs") {

    };
    public static final SquamorphLeg LEG5 = new SquamorphLeg(5, 0, "webbed legs") {

    };
    public static final SquamorphLeg LEG6 = new SquamorphLeg(6, 0, "short legs") {

    };

    public static final SquamorphLeg LEG7 = new SquamorphDiggingLeg(7, 300, "front claws") {

    };

    public static final SquamorphLeg LEG8 = new SquamorphLeg(8, 0, "tall hexapod") {
        public float getHeight() {
            return 22.5f;
        }
    };

    public static final SquamorphLeg LEG9 = new SquamorphLeg(9, 0, "webbed claws") {

    };

    public static final SquamorphLeg LEG10 = new SquamorphLeg(10, 0, "sai legs") {

    };

    public static final SquamorphLeg LEG11 = new SquamorphLeg(11, 0, "stubby legs") {

    };

    public static final SquamorphLeg LEG12 = new SquamorphBipedLeg(12, 0, "bird legs") {

    };

    public static final SquamorphLeg LEG13 = new SquamorphLeg(13, 0, "paddle legs") {

    };

    public static final SquamorphLeg LEG14 = new SquamorphLeg(14, 0, "hexapod") {

    };

    public static final SquamorphLeg LEG15 = new SquamorphBipedClawedLeg(15, 15, "tyrant claws") {

    };

    public static final SquamorphLeg LEG16 = new SquamorphLeg(16, 0, "weirdy legs") {

    };

    public static final SquamorphLeg LEG17 = new SquamorphLeg(17, 0, "stubs") {

    };

    public static final SquamorphLeg LEG18 = new SquamorphBipedClawedLeg(18, 15, "slasher claws") {

    };

    public static final SquamorphLeg LEG19 = new SquamorphBipedClawedLeg(19, 15, "hooked claws") {

    };

    public static final SquamorphLeg LEG20 = new SquamorphCubicLeg(20, 100, "knuckle walking") {

    };

    public static final SquamorphLeg[] ALL_LEGS = {LEG0, LEG1, LEG2, LEG3, LEG4, LEG5, LEG6, LEG7, LEG8, LEG9, LEG10,
            LEG11, LEG12, LEG13, LEG14, LEG15, LEG16, LEG17, LEG18, LEG19, LEG20};

    // TAILS

    public static final SquamorphTail TAIL0 = new SquamorphTail(0, 0, "tailless") {

    };

    public static final SquamorphTail TAIL1 = new SquamorphTail(1, 0, "basic tail") {

    };

    public static final SquamorphTail TAIL2 = new SquamorphTail(2, 0, "paddle fin") {

    };

    public static final SquamorphTail TAIL3 = new SquamorphSwingingTail(3, 60, "tail club") {

    };

    public static final SquamorphTail TAIL4 = new SquamorphTail(4, 0, "stubby tail") {

    };

    public static final SquamorphTail TAIL5 = new SquamorphTail(5, 300, "snake rattle") {

    };

    public static final SquamorphTail TAIL6 = new SquamorphTail(6, 0, "tail spikes") {

    };

    public static final SquamorphTail TAIL7 = new SquamorphTail(7, 0, "tail hairs") {

    };

    public static final SquamorphTail TAIL8 = new SquamorphTail(8, 0, "heart fin") {

    };

    public static final SquamorphTail TAIL9 = new SquamorphTail(9, 40, "stinger") {
        public boolean hasMelee() {return true;}

        @Override
        public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
            owner.setAttackDirection(!EntityHelpers.rightOfEntity(owner, target));
            return owner.getTailAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) <= owner.getBbWidth() * 2.5F * owner.getBbWidth() * 2.5F + target.getBbWidth();
        }

        @Override
        public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
            if (animTicks == 5) {
                this.performMeleeAttack(entity, target, dist);
            } else if (animTicks >= 17) {
                entity.addCooldowns();
                entity.setTailCD(this.getCooldown());
                entity.setAttackState(0);
                entity.resetMove();
            }
        }

        @Override
        public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
            if (dist <= owner.getBbWidth() * 2.5F * owner.getBbWidth() * 2.5F + target.getBbWidth()) {
                owner.betterDoHurt(target, this.getDamage(owner), 1);
                target.addEffect(new MobEffectInstance(MobEffects.POISON, 100 + owner.getTailLevel() * 20, owner.getTailLevel()));
                owner.getSecondaryElement().applyElement(target, owner, 1, 5);
                EntityHelpers.particleOnhitEffect(owner.getSecondaryElement().getTrailParticle(), target, owner.level(), 1);
            }
        }

        @Override
        public float getDamage(SquamorphEntity owner) {
            return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 1.5f + (float)Math.pow(owner.getTailLevel(), 1.5) * 0.6f;
        }

        @Override
        public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
            return SquamorphAbilityAnimation.tail_sting;
        }

    };

    public static final SquamorphTail TAIL10 = new SquamorphTail(10, 0, "shark tail") {

    };

    public static final SquamorphTail TAIL11 = new SquamorphTail(11, 0, "chameleon tail") {

    };

    public static final SquamorphTail TAIL12 = new SquamorphTail(12, 0, "whirly tail") {

    };

    public static final SquamorphTail TAIL13 = new SquamorphTail(13, 0, "bird tail") {

    };

    public static final SquamorphTail TAIL14 = new SquamorphTail(14, 0, "spiny tail") {

    };

    public static final SquamorphTail TAIL15 = new SquamorphSwingingTail(15, 60, "plated tail") {

    };

    public static final SquamorphTail TAIL16 = new SquamorphTail(16, 0, "longer tail") {

    };

    public static final SquamorphTail TAIL17 = new SquamorphTail(17, 0, "funky fin") {

    };

    public static final SquamorphTail TAIL18 = new SquamorphTail(18, 0, "tall hairy tail") {

    };

    public static final SquamorphTail TAIL19 = new SquamorphSwingingTail(19, 60, "whip-tail") {

    };

    public static final SquamorphTail TAIL20 = new SquamorphWaggingTail(20, 400, "hand tail") {
        @Override
        public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
            ElementalSpiritEntity spirit = ModEntities.ELEMENTAL_SPIRIT.get().create(owner.level());
            if (spirit != null) {
                spirit.summon(owner, owner.getSecondaryElementIndex(), owner.getTailLevel());
                owner.level().addFreshEntity(spirit);
            }
        }
    };

    public static final SquamorphTail[] ALL_TAILS = {TAIL0, TAIL1, TAIL2, TAIL3, TAIL4, TAIL5, TAIL6, TAIL7, TAIL8, TAIL9, TAIL10,
    TAIL11, TAIL12, TAIL13, TAIL14, TAIL15, TAIL16, TAIL17, TAIL18, TAIL19, TAIL20};

}
