package com.hedge.squamorphs.entity.squamorphparts;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import com.hedge.squamorphs.entity.projectile.BlastProjectile;
import com.hedge.squamorphs.entity.projectile.BoltProjectile;
import com.hedge.squamorphs.entity.projectile.SonarProjectile;
import com.hedge.squamorphs.entity.projectile.SquamorphProjectile;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphBody;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphWings;
import com.hedge.squamorphs.entity.squamorphparts.head.SquamorphHead;
import com.hedge.squamorphs.entity.squamorphparts.legs.SquamorphBipedLeg;
import com.hedge.squamorphs.entity.squamorphparts.legs.SquamorphCubicLeg;
import com.hedge.squamorphs.entity.squamorphparts.legs.SquamorphLeg;
import com.hedge.squamorphs.entity.squamorphparts.legs.SquamorphLegless;
import com.hedge.squamorphs.entity.squamorphparts.mouth.SquamorphMouth;
import com.hedge.squamorphs.entity.squamorphparts.tail.SquamorphTail;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class AllParts {


    // HEADS




    public static final SquamorphHead HEAD0 = new SquamorphHead(0, 60, "basic head", false);
    public static final SquamorphHead HEAD1 = new SquamorphHead(1, 60, "croc eyes", true) {

    };
    public static final SquamorphHead HEAD2 = new SquamorphHead(2, 60, "spiky horns", false) {
        @Override
        public int getColor(SquamorphEntity owner) {
            return owner.getSecondaryColor();
        }
    };
    public static final SquamorphHead HEAD3 = new SquamorphHead(3, 60, "bat ears", false) {

        @Override
        public SquamorphProjectile getProjectile(SquamorphEntity entity, Level level) {
            SonarProjectile projectile = ModEntities.SONAR.get().create(level);
            if (projectile != null) {
                projectile.setOwner(entity);
                projectile.setElementIndex(entity.getPrimaryElementIndex());
            }
            return projectile;
        }
    };
    public static final SquamorphHead HEAD4 = new SquamorphHead(4, 100, "crowned head", false) {
        @Override
        public SquamorphProjectile getProjectile(SquamorphEntity entity, Level level) {
            BlastProjectile projectile = ModEntities.BLAST.get().create(level);
            if (projectile != null) {
                projectile.setOwner(entity);
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
                fly.summon(owner, owner.getPrimaryElementIndex(), 1);
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
    public static final SquamorphHead HEAD19 = new SquamorphHead(19, 60, "drab antennae", false) {

    };

    public static final SquamorphHead[] ALL_HEADS = {HEAD0, HEAD1, HEAD2, HEAD3, HEAD4, HEAD5, HEAD6, HEAD7, HEAD8, HEAD9,
            HEAD10, HEAD11, HEAD12, HEAD13, HEAD14, HEAD15, HEAD16, HEAD17, HEAD18, HEAD19};

    // MOUTHS

    public static final SquamorphMouth MOUTH0 = new SquamorphMouth(0,20, "basic mouth", false, false);

    public static final SquamorphMouth MOUTH1 = new SquamorphMouth(1,20, "piscivore mouth", true, true);

    public static final SquamorphMouth MOUTH2 = new SquamorphMouth(2,20, "beaky mouth", false, false);

    public static final SquamorphMouth MOUTH3 = new SquamorphMouth(3,20, "vampiric fangs", false, true) {
        public int getColor(SquamorphEntity entity) {return entity.getSecondaryColor();}
    };

    public static final SquamorphMouth MOUTH4 = new SquamorphMouth(4,20, "venomous teeth", true, true);

    public static final SquamorphMouth MOUTH5 = new SquamorphMouth(5,20, "hatchet snout", true, true);

    public static final SquamorphMouth MOUTH6 = new SquamorphMouth(6,20, "tusked snout", true, true);

    public static final SquamorphMouth MOUTH7 = new SquamorphMouth(7,20, "sawtooth", false, true);

    public static final SquamorphMouth MOUTH8 = new SquamorphMouth(8,20, "arachnid fangs", false, false);

    public static final SquamorphMouth MOUTH9 = new SquamorphMouth(9,20, "lower teeth", false, false);

    public static final SquamorphMouth MOUTH10 = new SquamorphMouth(10,20, "horned snout", true, false);

    public static final SquamorphMouth MOUTH11 = new SquamorphMouth(11,20, "big mouth", true, false);

    public static final SquamorphMouth MOUTH12 = new SquamorphMouth(12,20, "avian beak", false, false);

    public static final SquamorphMouth MOUTH13 = new SquamorphMouth(13,20, "monster mouth", true, true);

    public static final SquamorphMouth MOUTH14 = new SquamorphMouth(14,20, "proboscis", false, false);

    public static final SquamorphMouth MOUTH15 = new SquamorphMouth(15,20, "underbite", true, true);

    public static final SquamorphMouth MOUTH16 = new SquamorphMouth(16,20, "knife snout", false, false);

    public static final SquamorphMouth MOUTH17 = new SquamorphMouth(17,20, "simple snout", true, false);

    public static final SquamorphMouth MOUTH18 = new SquamorphMouth(18,20, "saber teeth", false, false);

    public static final SquamorphMouth MOUTH19 = new SquamorphMouth(19,40, "mandibles", false, false);



    public static final SquamorphMouth[] ALL_MOUTHS = {MOUTH0, MOUTH1, MOUTH2, MOUTH3, MOUTH4, MOUTH5, MOUTH6, MOUTH7, MOUTH8, MOUTH9, MOUTH10,
            MOUTH11, MOUTH12, MOUTH13, MOUTH14, MOUTH15, MOUTH16, MOUTH17, MOUTH18, MOUTH19};

    // BODY

    public static final SquamorphBody BODY0 = new SquamorphBody(0, 0, "basic body");
    public static final SquamorphBody BODY1 = new SquamorphBody(1, 0, "dorsal fin");
    public static final SquamorphBody BODY2 = new SquamorphBody(2, 0, "armored shell");
    public static final SquamorphBody BODY3 = new SquamorphWings(3, 0, "bat wings");
    public static final SquamorphBody BODY4 = new SquamorphBody(4, 0, "cobra flaps");
    public static final SquamorphBody BODY5 = new SquamorphBody(5, 0, "curved spikes");
    public static final SquamorphBody BODY6 = new SquamorphBody(6, 0, "sparse hairs");
    public static final SquamorphBody BODY7 = new SquamorphBody(7, 0, "hearts");
    public static final SquamorphBody BODY8 = new SquamorphWings(8, 0, "lace wings");
    public static final SquamorphBody BODY9 = new SquamorphBody(9, 0, "shark fin");
    public static final SquamorphBody BODY10 = new SquamorphBody(10, 0, "osteoderms");
    public static final SquamorphBody BODY11 = new SquamorphBody(11, 0, "whirl growths");
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

    };
    public static final SquamorphBody BODY15 = new SquamorphBody(15, 0, "dorsal spikes");
    public static final SquamorphBody BODY16 = new SquamorphWings(16, 0, "monstrous wings") {
        @Override
        public int getColor(SquamorphEntity entity) {return entity.getPrimaryColor();}
    };
    public static final SquamorphBody BODY17 = new SquamorphBody(17, 0, "sail");
    public static final SquamorphBody BODY18 = new SquamorphBody(18, 0, "tall hairs");
    public static final SquamorphBody BODY19 = new SquamorphBody(19, 0, "back cannon");
    public static final SquamorphBody BODY20 = new SquamorphBody(20, 0, "shiny shell");

    public static final SquamorphBody[] ALL_BODIES = {BODY0, BODY1, BODY2, BODY3, BODY4, BODY5, BODY6, BODY7, BODY8, BODY9, BODY10,
    BODY11, BODY12, BODY13, BODY14, BODY15, BODY16, BODY17, BODY18, BODY19, BODY20};





        // LEGS

    public static final SquamorphLeg LEG0 = new SquamorphLegless();
    public static final SquamorphLeg LEG1 = new SquamorphLeg(1, 0, "basic legs") {

    };
    public static final SquamorphLeg LEG2 = new SquamorphLeg(2, 0, "fish legs") {

    };
    public static final SquamorphLeg LEG3 = new SquamorphCubicLeg(3, 0, "bulky legs") {

    };
    public static final SquamorphLeg LEG4 = new SquamorphLeg(4, 0, "fork legs") {

    };
    public static final SquamorphLeg LEG5 = new SquamorphLeg(5, 0, "webbed legs") {

    };
    public static final SquamorphLeg LEG6 = new SquamorphLeg(6, 0, "short legs") {

    };

    public static final SquamorphLeg LEG7 = new SquamorphLeg(7, 0, "front claws") {

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

    public static final SquamorphLeg LEG15 = new SquamorphBipedLeg(15, 0, "tyrant claws") {

    };

    public static final SquamorphLeg LEG16 = new SquamorphLeg(16, 0, "weirdy legs") {

    };

    public static final SquamorphLeg LEG17 = new SquamorphLeg(17, 0, "stubs") {

    };

    public static final SquamorphLeg LEG18 = new SquamorphBipedLeg(18, 0, "slasher claws") {

    };

    public static final SquamorphLeg LEG19 = new SquamorphBipedLeg(19, 0, "hooked claws") {

    };

    public static final SquamorphLeg LEG20 = new SquamorphCubicLeg(20, 0, "knuckle walking") {

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

    public static final SquamorphTail TAIL3 = new SquamorphTail(3, 80, "tail club") {

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

    public static final SquamorphTail TAIL9 = new SquamorphTail(9, 100, "stinger") {
        public boolean hasMelee() {return true;}

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

    public static final SquamorphTail TAIL15 = new SquamorphTail(15, 0, "plated tail") {

    };

    public static final SquamorphTail TAIL16 = new SquamorphTail(16, 0, "longer tail") {

    };

    public static final SquamorphTail TAIL17 = new SquamorphTail(17, 0, "funky fin") {

    };

    public static final SquamorphTail TAIL18 = new SquamorphTail(18, 0, "tall hairy tail") {

    };

    public static final SquamorphTail TAIL19 = new SquamorphTail(19, 100, "whip-tail") {

    };

    public static final SquamorphTail TAIL20 = new SquamorphTail(20, 400, "hand tail") {

    };

    public static final SquamorphTail[] ALL_TAILS = {TAIL0, TAIL1, TAIL2, TAIL3, TAIL4, TAIL5, TAIL6, TAIL7, TAIL8, TAIL9, TAIL10,
    TAIL11, TAIL12, TAIL13, TAIL14, TAIL15, TAIL16, TAIL17, TAIL18, TAIL19, TAIL20};

}
