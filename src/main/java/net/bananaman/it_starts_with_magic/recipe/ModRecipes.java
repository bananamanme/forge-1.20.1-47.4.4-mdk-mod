package net.bananaman.it_starts_with_magic.recipe;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ItStartsWithMagicMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<TheBlockEntityRecipe>> INFUSING_SERIALIZER =
            SERIALIZERS.register("infusing", () -> TheBlockEntityRecipe.Serializer.INSTANCE);



    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
