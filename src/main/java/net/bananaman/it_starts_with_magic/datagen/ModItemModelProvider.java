package net.bananaman.it_starts_with_magic.datagen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output,  ExistingFileHelper existingFileHelper) {
        super(output, ItStartsWithMagicMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SPELLSHARD);
        simpleItem(ModItems.THESPELLBOOK);
        simpleItem(ModItems.TECH_MUSIC_DISC);
        simpleItem(ModItems.WEIRD_RYTHEM_MUSIC_DISC);
        simpleItem(ModItems.RUSSSSIAN_MUSIC_DISC);
        simpleItem(ModItems.LAPIS_LAZULI_SHARD);
        simpleItem(ModItems.RUBY);
        simpleItem(ModItems.AMETRINE);

        wallItem(ModBlocks.PEll_WALL,ModBlocks.PEll);
        wallItem(ModBlocks.TUFF_BRICK_WALL,ModBlocks.TUFF_BRICKS);
        wallItem(ModBlocks.POLISHED_TUFF_WALL,ModBlocks.POLISHED_TUFF);

        evenSimplerBlockItem(ModBlocks.PEll_STAIRS);
        evenSimplerBlockItem(ModBlocks.PEll_SLAB);
        evenSimplerBlockItem(ModBlocks.TUFF_STAIRS);
        evenSimplerBlockItem(ModBlocks.TUFF_SLAB);
        evenSimplerBlockItem(ModBlocks.TUFF_BRICK_STAIRS);
        evenSimplerBlockItem(ModBlocks.TUFF_BRICK_SLAB);
        evenSimplerBlockItem(ModBlocks.POLISHED_TUFF_STAIRS);
        evenSimplerBlockItem(ModBlocks.POLISHED_TUFF_SLAB);



    }
    private ItemModelBuilder simpleItem(RegistryObject<Item>item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ItStartsWithMagicMod.MOD_ID,"item/"+item.getId().getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),mcLoc("block/wall_inventory"))
                .texture("wall",new ResourceLocation(ItStartsWithMagicMod.MOD_ID,"block/"+ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(ItStartsWithMagicMod.MOD_ID+ ":" +ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/"+ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }


}
