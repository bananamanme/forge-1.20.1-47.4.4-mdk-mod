package net.bananaman.it_starts_with_magic.item;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ItStartsWithMagicMod.MOD_ID);
    public static final RegistryObject<CreativeModeTab> IT_STARTS_WITH_MAGIC_TAB = CREATIVE_MOD_TABS.register("it_starts_with_magic_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.THESPELLBOOK.get()))
                    .title(Component.translatable("creativetab.it_starts_with_magic_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.SPELLSHARD.get());
                        output.accept(ModBlocks.PEll.get());
                        output.accept(ModItems.THESPELLBOOK.get());
                        output.accept(ModItems.TECH_MUSIC_DISC.get());
                    })

                    .build());

    public static void  register(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
