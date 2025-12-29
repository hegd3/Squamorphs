package com.hedge.squamorphs.client.particle;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.client.particle.sonic_boom.ElementalSonicBoomParticleOptions;
import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Squamorphs.MODID);

    public static final RegistryObject<SimpleParticleType> FORCE_TRAIL = DEF_REG.register("force_trail", ()-> new SimpleParticleType(false));

    public static final RegistryObject<ParticleType<ElementalSonicBoomParticleOptions>> GENERIC_SONIC_BOOM = DEF_REG.register("generic_sonic_boom", ()-> new ParticleType<>(true, ElementalSonicBoomParticleOptions.DESERIALIZER) {
        @Override
        public Codec<ElementalSonicBoomParticleOptions> codec() {
            return ElementalSonicBoomParticleOptions.CODEC;
        }

    });

    public static void register(IEventBus eventbus) {
        DEF_REG.register(eventbus);
    }
}
