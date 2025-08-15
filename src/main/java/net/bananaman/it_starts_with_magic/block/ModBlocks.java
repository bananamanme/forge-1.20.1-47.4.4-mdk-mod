package net.bananaman.it_starts_with_magic.block;
import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.custom.TheEntityBlock;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
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

    public static final RegistryObject<Block> RUBY_ORE = registerBlock("ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(6).requiresCorrectToolForDrops().noOcclusion(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> DEEPSLATE_RUBY_ORE = registerBlock("deepslate_ruby_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(8).requiresCorrectToolForDrops().noOcclusion(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> RUBY_BLOCK = registerBlock("ruby_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.EMERALD_BLOCK).strength(8).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AMETRINE_ORE = registerBlock("ametrine_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(6).requiresCorrectToolForDrops().noOcclusion(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> DEEPSLATE_AMETRINE_ORE = registerBlock("deepslate_ametrine_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(8).requiresCorrectToolForDrops().noOcclusion(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> AZURITE_ORE = registerBlock("azurite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(6).requiresCorrectToolForDrops().noOcclusion(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> DEEPSLATE_AZURITE_ORE = registerBlock("deepslate_azurite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE).strength(8).requiresCorrectToolForDrops().noOcclusion(), UniformInt.of(3,6)));

    public static final RegistryObject<Block> CHISELED_TUFF = registerBlock("chiseled_tuff",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(1.5f).requiresCorrectToolForDrops().noOcclusion()));

    public static final RegistryObject<Block> TUFF_BRICKS = registerBlock("tuff_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF).strength(1.5f).requiresCorrectToolForDrops().noOcclusion()));


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
