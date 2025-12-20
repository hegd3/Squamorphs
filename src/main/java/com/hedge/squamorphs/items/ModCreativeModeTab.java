package com.hedge.squamorphs.items;

import com.hedge.squamorphs.Squamorphs;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Squamorphs.MODID);

    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = CREATIVE_MODE_TABS.register("squamorphs_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.GROPPY.get()))
                    .title(Component.translatable("creativetab.squamorphs_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.GROPPY.get());

                        output.accept(ModItems.SQUAMORPH_SPAWN_EGG.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
