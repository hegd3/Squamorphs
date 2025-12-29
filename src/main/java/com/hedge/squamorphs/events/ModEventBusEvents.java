package com.hedge.squamorphs.events;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.client.particle.ForceTrailParticle;
import com.hedge.squamorphs.client.particle.ModParticles;
import com.hedge.squamorphs.client.particle.sonic_boom.ElementalSonicBoomParticle;
import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalSpiritEntity;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Squamorphs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {


    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.SQUAMORPH.get(), SquamorphEntity.bakeAttributes().build());
        event.put(ModEntities.ELEMENTAL_FLY.get(), ElementalFlyEntity.bakeAttributes().build());
        event.put(ModEntities.ELEMENTAL_SPIRIT.get(), ElementalSpiritEntity.bakeAttributes().build());

    }

    @SubscribeEvent
    public static void entitySpawn(SpawnPlacementRegisterEvent event) {


    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticles.FORCE_TRAIL.get(), ForceTrailParticle.Provider::new);
        event.registerSpriteSet(ModParticles.GENERIC_SONIC_BOOM.get(), ElementalSonicBoomParticle.Provider::new);
    }



}