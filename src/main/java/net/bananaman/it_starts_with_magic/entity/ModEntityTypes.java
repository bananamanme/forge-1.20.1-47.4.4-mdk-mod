package net.bananaman.it_starts_with_magic.entity;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.entity.entities.MagicBulletEntity;
import net.bananaman.it_starts_with_magic.entity.entities.SacrificeArrowEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ItStartsWithMagicMod.MOD_ID);


    public static final RegistryObject<EntityType<MagicBulletEntity>> MAGIC_BULLET =
            ENTITY_TYPES.register("magic_bullet",
                    () -> EntityType.Builder.<MagicBulletEntity>of(MagicBulletEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F) // Hitbox size
                            .build(new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "magic_bullet").toString()));

    public static final RegistryObject<EntityType<SacrificeArrowEntity>> SACRIFICE_ARROW =
            ENTITY_TYPES.register("sacrifice_arrow",
                    () -> EntityType.Builder.<SacrificeArrowEntity>of(SacrificeArrowEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F) // Hitbox size
                            .build(new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "sacrifice_arrow").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
