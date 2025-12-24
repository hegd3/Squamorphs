package com.hedge.squamorphs.events;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
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
    }

    @SubscribeEvent
    public static void entitySpawn(SpawnPlacementRegisterEvent event) {


    }



}