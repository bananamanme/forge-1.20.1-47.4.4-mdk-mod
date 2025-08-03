package net.bananaman.it_starts_with_magic.datagen;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PEll.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SPELLSHARD.get())
                .unlockedBy(getHasName(ModItems.SPELLSHARD.get()),has(ModItems.SPELLSHARD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.THESPELLBOOK.get())
                .pattern("SES")
                .pattern("EBE")
                .pattern("SES")
                .define('S', ModItems.SPELLSHARD.get())
                .define('E', Items.ECHO_SHARD)
                .define('B', Items.BOOK)
                .unlockedBy(getHasName(ModItems.SPELLSHARD.get()),has(ModItems.SPELLSHARD.get()))
                .save(consumer);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.SPELLSHARD.get(), 9)
                .requires(ModBlocks.PEll.get())
                .unlockedBy(getHasName(ModBlocks.PEll.get()),has(ModBlocks.PEll.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC,ModItems.LAPIS_LAZULI_SHARD.get())
                .requires(Items.LAPIS_LAZULI,2)
                .requires(Items.GOLD_NUGGET,2)
                .unlockedBy(getHasName(Items.LAPIS_LAZULI),has(Items.LAPIS_LAZULI))
                .unlockedBy(getHasName(Items.GOLD_NUGGET),has(Items.GOLD_NUGGET))
                .save(consumer);

    }


    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike})
                    , pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike)).save(pFinishedRecipeConsumer,
                            ItStartsWithMagicMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
