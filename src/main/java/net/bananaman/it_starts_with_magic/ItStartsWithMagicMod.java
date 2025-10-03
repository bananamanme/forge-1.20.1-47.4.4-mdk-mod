package net.bananaman.it_starts_with_magic;


import com.mojang.logging.LogUtils;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.bananaman.it_starts_with_magic.block.entity.ModBlocksEntities;
import net.bananaman.it_starts_with_magic.compact.Curios;
import net.bananaman.it_starts_with_magic.entity.ModEntityTypes;
import net.bananaman.it_starts_with_magic.entity.models.MagicBulletModel;
import net.bananaman.it_starts_with_magic.entity.models.SacrificeArrowModel;
import net.bananaman.it_starts_with_magic.entity.renderer.MagicBulletRenderer;
import net.bananaman.it_starts_with_magic.entity.renderer.SacrificeArrowRenderer;
import net.bananaman.it_starts_with_magic.item.ModCreativeModTabs;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.bananaman.it_starts_with_magic.modloot.ModLootModifiers;
import net.bananaman.it_starts_with_magic.mana.ManaOverlay;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.bananaman.it_starts_with_magic.particle.ModSonicBoomParticle;
import net.bananaman.it_starts_with_magic.recipe.ModRecipes;
import net.bananaman.it_starts_with_magic.screen.ModMenuTypes;
import net.bananaman.it_starts_with_magic.screen.TheEntityBlockScreen;
import net.bananaman.it_starts_with_magic.sound.ModSounds;
import net.bananaman.it_starts_with_magic.spells.api.SpellRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ItStartsWithMagicMod.MOD_ID)
public class ItStartsWithMagicMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "it_starts_with_magic";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public ItStartsWithMagicMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeModTabs.register(modEventBus);
        ModParticles.register(modEventBus);
        ModSounds.register(modEventBus);
        ModBlocksEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        ModEntityTypes.register(modEventBus);


        SpellRegistry.values();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::clientSetup);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }


    private void clientSetup(final FMLClientSetupEvent event) {
    }





    private void commonSetup(final FMLCommonSetupEvent event)
    {
        Curios.registerCurioSlot(Curios.RING_SLOT, 2, false, null);
        Curios.registerCurioSlot(Curios.SPELLBOOK_SLOT, 1, false, ResourceLocation.parse("curios:slot/spellbook_slot"));

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {


        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModMenuTypes.THE_ENTITY_BLOCK_MENU.get(), TheEntityBlockScreen::new);
            EntityRenderers.register(ModEntityTypes.MAGIC_BULLET.get(), MagicBulletRenderer::new);


            ModMessages.register();


        }


        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAbove(new ResourceLocation("minecraft", "hotbar"), "mana_overlay", ManaOverlay.MANA_OVERLAY);
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event){
            event.registerSpriteSet(ModParticles.MODSONICBOOMPARTICLE.get(), ModSonicBoomParticle.Provider::new);

        }
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(ModEntityTypes.MAGIC_BULLET.get(), MagicBulletRenderer::new);
            event.registerEntityRenderer(ModEntityTypes.SACRIFICE_ARROW.get(), SacrificeArrowRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(MagicBulletModel.LAYER_LOCATION, MagicBulletModel::createBodyLayer);
            event.registerLayerDefinition(SacrificeArrowModel.LAYER_LOCATION, SacrificeArrowModel::createBodyLayer);

        }

    }
}
