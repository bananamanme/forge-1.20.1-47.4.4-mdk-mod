package net.bananaman.it_starts_with_magic.datagen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
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

        simpleBlockWithItem(ModBlocks.CHISELED_TUFF.get(),
                models().cubeBottomTop(
                        "chiseled_tuff",
                        modLoc("block/chiseled_tuff_side"), // Texture for the sides
                        modLoc("block/chiseled_tuff_bottom"),   // Texture for the bottom
                        modLoc("block/chiseled_tuff_top")));



        simpleBlockWithItem(ModBlocks.THE_ENTITY_BLOCK.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/the_entity_block")));


        stairsBlock(((StairBlock) ModBlocks.PEll_STAIRS.get()), blockTexture(ModBlocks.PEll.get()));

        slabBlock((SlabBlock) ModBlocks.PEll_SLAB.get(), blockTexture(ModBlocks.PEll.get()), blockTexture(ModBlocks.PEll.get()));

        wallBlock(((WallBlock) ModBlocks.PEll_WALL.get()), blockTexture(ModBlocks.PEll.get()));

    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(),cubeAll(blockRegistryObject.get()));
    }
}
