package com.hedge.squamorphs.events;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.client.models.SquamorphProjectileModel;
import com.hedge.squamorphs.client.models.SquamorphModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = Squamorphs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelLayers.SQUAMORPH_LAYER, SquamorphModel::createBodyLayer);
        event.registerLayerDefinition(ModelLayers.PROJECTILE_LAYER, SquamorphProjectileModel::createBodyLayer);

    }
}
