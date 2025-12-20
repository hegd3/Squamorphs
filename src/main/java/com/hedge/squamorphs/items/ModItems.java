package com.hedge.squamorphs.items;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Squamorphs.MODID);

    public static final RegistryObject<Item> GROPPY = ITEMS.register("groppy",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SQUAMORPH_SPAWN_EGG = ITEMS.register("squamorph_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SQUAMORPH, 0xB9B7C7, 0xD0CCF0, new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
