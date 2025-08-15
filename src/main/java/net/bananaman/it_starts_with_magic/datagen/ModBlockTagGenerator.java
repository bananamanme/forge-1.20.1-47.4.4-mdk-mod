package net.bananaman.it_starts_with_magic.datagen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,  @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ItStartsWithMagicMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PEll.get(),
                        ModBlocks.RUBY_ORE.get(),
                        ModBlocks.DEEPSLATE_RUBY_ORE.get(),
                        ModBlocks.RUBY_BLOCK.get(),
                        ModBlocks.AMETRINE_ORE.get(),
                        ModBlocks.DEEPSLATE_AMETRINE_ORE.get(),
                        ModBlocks.AZURITE_ORE.get(),
                        ModBlocks.DEEPSLATE_AZURITE_ORE.get()
                );

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PEll.get(),
                        ModBlocks.RUBY_ORE.get(),
                        ModBlocks.DEEPSLATE_RUBY_ORE.get(),
                        ModBlocks.RUBY_BLOCK.get(),
                        ModBlocks.AMETRINE_ORE.get(),
                        ModBlocks.DEEPSLATE_AMETRINE_ORE.get(),
                        ModBlocks.AZURITE_ORE.get(),
                        ModBlocks.DEEPSLATE_AZURITE_ORE.get(),
                        ModBlocks.TUFF_SLAB.get(),
                        ModBlocks.TUFF_STAIRS.get(),
                        ModBlocks.CHISELED_TUFF.get(),
                        ModBlocks.TUFF_BRICKS.get(),
                        ModBlocks.TUFF_BRICK_SLAB.get(),
                        ModBlocks.TUFF_BRICK_STAIRS.get(),
                        ModBlocks.CHISELED_TUFF_BRICKS.get(),
                        ModBlocks.POLISHED_TUFF.get(),
                        ModBlocks.POLISHED_TUFF_SLAB.get(),
                        ModBlocks.POLISHED_TUFF_STAIRS.get()
                );

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.PEll_WALL.get())
                .add(ModBlocks.TUFF_WALL.get())
                .add(ModBlocks.TUFF_BRICK_WALL.get())
                .add(ModBlocks.POLISHED_TUFF_WALL.get()



                );

    }
}
