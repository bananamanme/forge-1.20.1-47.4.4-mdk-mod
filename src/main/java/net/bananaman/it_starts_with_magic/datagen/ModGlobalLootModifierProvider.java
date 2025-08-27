package net.bananaman.it_starts_with_magic.datagen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.bananaman.it_starts_with_magic.modloot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, ItStartsWithMagicMod.MOD_ID);
    }

    @Override
    protected void start() {
        for (int i =0; i<4;i++) {
            String number = String.valueOf(i);
            add("refined_ametrine_from_simple_dungeon"+number, new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                    LootItemRandomChanceCondition.randomChance(0.25f).build()
            }, ModItems.SPELLSHARD.get()));
        }

        for (int i =0; i<5;i++) {
            String number = String.valueOf(i);
            add("refined_ametrine_from_shipwreck_treasure"+number, new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                    LootItemRandomChanceCondition.randomChance(0.25f).build()
            }, ModItems.SPELLSHARD.get()));
        }

        for (int i =0; i<3;i++) {
            String number = String.valueOf(i);
            add("refined_ametrine_from_buried_treasure"+number, new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                    LootItemRandomChanceCondition.randomChance(0.5f).build()
            }, ModItems.SPELLSHARD.get()));
        }
        for (int i =0; i<6;i++) {
            String number = String.valueOf(i);
            add("refined_ametrine_from_desert_pyramid"+number, new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build(),
                    LootItemRandomChanceCondition.randomChance(0.5f).build()
            }, ModItems.SPELLSHARD.get()));
        }
        for (int i =0; i<6;i++) {
            String number = String.valueOf(i);
            add("refined_ametrine_from_jungle_temple"+number, new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build(),
                    LootItemRandomChanceCondition.randomChance(0.5f).build()
            }, ModItems.SPELLSHARD.get()));
        }
        for (int i =0; i<6;i++) {
            String number = String.valueOf(i);
            add("refined_ametrine_from_pillager_outpost"+number, new AddItemModifier(new LootItemCondition[]{
                    new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                    LootItemRandomChanceCondition.randomChance(0.5f).build()
            }, ModItems.SPELLSHARD.get()));
        }

    }
}