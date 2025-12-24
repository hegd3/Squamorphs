package com.hedge.squamorphs;

import com.hedge.squamorphs.client.renderer.BoltRenderer;
import com.hedge.squamorphs.client.renderer.ElementalFlyRenderer;
import com.hedge.squamorphs.client.renderer.SonarRenderer;
import com.hedge.squamorphs.client.renderer.SquamorphRenderer;
import com.hedge.squamorphs.entity.ModEntities;
import com.hedge.squamorphs.items.ModCreativeModeTab;
import com.hedge.squamorphs.items.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Squamorphs.MODID)
public class Squamorphs
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "squamorphs";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Squamorphs(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();


        ModCreativeModeTab.register(modEventBus);

        ModItems.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        ModEntities.register(modEventBus);
        //context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        /*
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

         */
    }

    // Add the example block item to the building blocks tab

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.SQUAMORPH.get(), SquamorphRenderer::new);

            EntityRenderers.register(ModEntities.ELEMENTAL_FLY.get(), ElementalFlyRenderer::new);

            EntityRenderers.register(ModEntities.BOLT.get(), BoltRenderer::new);

            EntityRenderers.register(ModEntities.SONAR.get(), SonarRenderer::new);

        }
    }
}
