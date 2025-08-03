package net.bananaman.it_starts_with_magic.screen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ItStartsWithMagicMod.MOD_ID);

    public static final RegistryObject<MenuType<TheEntityBlockMenu>> THE_ENTITY_BLOCK_MENU =
            registerManuType("the_entity_block_menu",TheEntityBlockMenu::new);


    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerManuType(String name, IContainerFactory<T> factory){
        return MENUS.register(name, ()-> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
