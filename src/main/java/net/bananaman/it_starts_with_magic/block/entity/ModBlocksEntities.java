package net.bananaman.it_starts_with_magic.block.entity;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocksEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ItStartsWithMagicMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<TheEntityBlockBlockEntity>> THE_BLOCK_ENTITY_BE =
            BLOCK_ENTITIES.register("the_entity_block_be",()->
                    BlockEntityType.Builder.of(TheEntityBlockBlockEntity::new,
                            ModBlocks.THE_ENTITY_BLOCK.get()).build(null));



    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
