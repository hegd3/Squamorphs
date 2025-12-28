package com.hedge.squamorphs.entity;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalSpiritEntity;
import com.hedge.squamorphs.entity.projectile.BlastProjectile;
import com.hedge.squamorphs.entity.projectile.BoltProjectile;
import com.hedge.squamorphs.entity.projectile.SonarProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Squamorphs.MODID);

    public static final RegistryObject<EntityType<SquamorphEntity>> SQUAMORPH =
            ENTITY_TYPES.register("squamorph", () -> EntityType.Builder.of(SquamorphEntity::new, MobCategory.CREATURE)
                            .sized(0.9f, 0.6f).build("squamorph"));

    public static final RegistryObject<EntityType<ElementalFlyEntity>> ELEMENTAL_FLY =
            ENTITY_TYPES.register("elemental_fly", () -> EntityType.Builder.of(ElementalFlyEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.4f).build("elemental_fly"));

    public static final RegistryObject<EntityType<ElementalSpiritEntity>> ELEMENTAL_SPIRIT =
            ENTITY_TYPES.register("elemental_spirit", () -> EntityType.Builder.of(ElementalSpiritEntity::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.9f).build("elemental_spirit"));

    public static final RegistryObject<EntityType<BoltProjectile>> BOLT =
            ENTITY_TYPES.register("bolt", () -> EntityType.Builder.of(BoltProjectile::new, MobCategory.MISC)
                    .sized(0.4f, 0.4f).build("bolt"));

    public static final RegistryObject<EntityType<SonarProjectile>> SONAR =
            ENTITY_TYPES.register("sonar", () -> EntityType.Builder.of(SonarProjectile::new, MobCategory.MISC)
                    .sized(0.2f, 0.5f).build("sonar"));

    public static final RegistryObject<EntityType<BlastProjectile>> BLAST =
            ENTITY_TYPES.register("blast", () -> EntityType.Builder.of(BlastProjectile::new, MobCategory.MISC)
                    .sized(0.6f, 0.5f).build("blast"));


    public static void register(IEventBus eventbus) {
        ENTITY_TYPES.register(eventbus);
    }
}
