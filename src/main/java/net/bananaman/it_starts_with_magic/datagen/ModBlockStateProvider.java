package net.bananaman.it_starts_with_magic.datagen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ItStartsWithMagicMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PEll);
        blockWithItem(ModBlocks.RUBY_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_RUBY_ORE);
        blockWithItem(ModBlocks.RUBY_BLOCK);
        blockWithItem(ModBlocks.AMETRINE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_AMETRINE_ORE);
        blockWithItem(ModBlocks.AZURITE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_AZURITE_ORE);
        blockWithItem(ModBlocks.TUFF_BRICKS);
        blockWithItem(ModBlocks.POLISHED_TUFF);

        simpleBlockWithItem(ModBlocks.CHISELED_TUFF.get(),
                models().cubeBottomTop(
                        "chiseled_tuff",
                        modLoc("block/chiseled_tuff_side"), // Texture for the sides
                        modLoc("block/chiseled_tuff_bottom"),   // Texture for the bottom
                        modLoc("block/chiseled_tuff_top")));   // Texture for the top

        simpleBlockWithItem(ModBlocks.CHISELED_TUFF_BRICKS.get(),
                models().cubeBottomTop(
                        "chiseled_tuff_bricks",
                        modLoc("block/chiseled_tuff_bricks_side"), // Texture for the sides
                        modLoc("block/chiseled_tuff_bricks_bottom"),   // Texture for the bottom
                        modLoc("block/chiseled_tuff_bricks_top")));   // Texture for the top



        simpleBlockWithItem(ModBlocks.THE_ENTITY_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/the_entity_block")));

        simpleBlockWithItem(ModBlocks.THE_PEDESTAL_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/the_pedestal_block")));


        stairsBlock(((StairBlock) ModBlocks.PEll_STAIRS.get()), blockTexture(ModBlocks.PEll.get()));
        stairsBlock(((StairBlock) ModBlocks.TUFF_STAIRS.get()), blockTexture(Blocks.TUFF));
        stairsBlock(((StairBlock) ModBlocks.TUFF_BRICK_STAIRS.get()), blockTexture(ModBlocks.TUFF_BRICKS.get()));
        stairsBlock(((StairBlock) ModBlocks.POLISHED_TUFF_STAIRS.get()), blockTexture(ModBlocks.POLISHED_TUFF.get()));

        slabBlock((SlabBlock) ModBlocks.PEll_SLAB.get(), blockTexture(ModBlocks.PEll.get()), blockTexture(ModBlocks.PEll.get()));
        slabBlock((SlabBlock) ModBlocks.TUFF_SLAB.get(), blockTexture(Blocks.TUFF), blockTexture(Blocks.TUFF));
        slabBlock((SlabBlock) ModBlocks.TUFF_BRICK_SLAB.get(), blockTexture(ModBlocks.TUFF_BRICKS.get()), blockTexture(ModBlocks.TUFF_BRICKS.get()));
        slabBlock((SlabBlock) ModBlocks.POLISHED_TUFF_SLAB.get(), blockTexture(ModBlocks.POLISHED_TUFF.get()), blockTexture(ModBlocks.POLISHED_TUFF.get()));

        wallBlock(((WallBlock) ModBlocks.PEll_WALL.get()), blockTexture(ModBlocks.PEll.get()));
        wallBlock(((WallBlock) ModBlocks.TUFF_WALL.get()), blockTexture(Blocks.TUFF));
        wallBlock(((WallBlock) ModBlocks.TUFF_BRICK_WALL.get()), blockTexture(ModBlocks.TUFF_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.POLISHED_TUFF_WALL.get()), blockTexture(ModBlocks.POLISHED_TUFF.get()));

    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
