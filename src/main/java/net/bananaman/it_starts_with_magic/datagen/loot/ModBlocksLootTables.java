package net.bananaman.it_starts_with_magic.datagen.loot;

import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlocksLootTables extends BlockLootSubProvider {
    public ModBlocksLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.PEll.get());
        this.dropSelf(ModBlocks.PEll_STAIRS.get());
        this.dropSelf(ModBlocks.PEll_WALL.get());
        this.dropSelf(ModBlocks.THE_ENTITY_BLOCK.get());
        this.dropSelf(ModBlocks.RUBY_BLOCK.get());
        this.dropSelf(ModBlocks.TUFF_STAIRS.get());
        this.dropSelf(ModBlocks.TUFF_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_TUFF.get());
        this.dropSelf(ModBlocks.TUFF_BRICKS.get());
        this.dropSelf(ModBlocks.TUFF_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.TUFF_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CHISELED_TUFF_BRICKS.get());
        this.dropSelf(ModBlocks.POLISHED_TUFF.get());
        this.dropSelf(ModBlocks.POLISHED_TUFF_STAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_TUFF_WALL.get());


        this.add(ModBlocks.RUBY_ORE.get(),
                block -> createRubyOreDrop(ModBlocks.RUBY_ORE.get(),ModItems.RUBY.get()));
        this.add(ModBlocks.DEEPSLATE_RUBY_ORE.get(),
                block -> createRubyOreDrop(ModBlocks.DEEPSLATE_RUBY_ORE.get(),ModItems.RUBY.get()));
        this.add(ModBlocks.AMETRINE_ORE.get(),
                block -> createAmetrineOreDrop(ModBlocks.AMETRINE_ORE.get(),ModItems.AMETRINE.get()));
        this.add(ModBlocks.DEEPSLATE_AMETRINE_ORE.get(),
                block -> createAmetrineOreDrop(ModBlocks.DEEPSLATE_AMETRINE_ORE.get(),ModItems.AMETRINE.get()));
        this.add(ModBlocks.AZURITE_ORE.get(),
                block -> createAzuriteOreDrop(ModBlocks.AZURITE_ORE.get(),ModItems.LAPIS_LAZULI_SHARD.get()));
        this.add(ModBlocks.DEEPSLATE_AZURITE_ORE.get(),
                block -> createAzuriteOreDrop(ModBlocks.DEEPSLATE_AZURITE_ORE.get(),ModItems.LAPIS_LAZULI_SHARD.get()));



        this.add(ModBlocks.PEll_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.PEll_SLAB.get()));
        this.add(ModBlocks.TUFF_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TUFF_SLAB.get()));
        this.add(ModBlocks.TUFF_BRICK_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.TUFF_BRICK_SLAB.get()));
        this.add(ModBlocks.POLISHED_TUFF_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_TUFF_SLAB.get()));
    }


    protected LootTable.Builder createRubyOreDrop(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionCondition(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,5.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));


    }

    protected LootTable.Builder createAmetrineOreDrop(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionCondition(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));


    }

    protected LootTable.Builder createAzuriteOreDrop(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionCondition(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0f,4.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));


    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
