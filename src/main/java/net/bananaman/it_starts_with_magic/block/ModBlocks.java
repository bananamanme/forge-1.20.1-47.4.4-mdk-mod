package net.bananaman.it_starts_with_magic.block;
import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.custom.TheEntityBlock;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ItStartsWithMagicMod.MOD_ID);

    public static final RegistryObject<Block> PEll = registerBlock("pell",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).sound(SoundType.AMETHYST).strength(6).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> THE_ENTITY_BLOCK = registerBlock("the_entity_block",
            () -> new TheEntityBlock(BlockBehaviour.Properties.copy(Blocks.GLOWSTONE).sound(SoundType.AMETHYST).strength(6).requiresCorrectToolForDrops().noOcclusion()));






    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
