package net.bananaman.it_starts_with_magic.item;


import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.item.custom.TheSpellBook;
import net.bananaman.it_starts_with_magic.sound.ModSounds;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ItStartsWithMagicMod.MOD_ID);

    public static final RegistryObject<Item> SPELLSHARD = ITEMS.register("spell_shard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THESPELLBOOK = ITEMS.register("the_spell_book",
            () -> new TheSpellBook(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> TECH_MUSIC_DISC = ITEMS.register("tech_music_disc",
            () -> new RecordItem(6, ModSounds.TECH, new Item.Properties().stacksTo(1),1920));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
