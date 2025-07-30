package net.bananaman.it_starts_with_magic;


import com.mojang.logging.LogUtils;
import net.bananaman.it_starts_with_magic.block.ModBlocks;
import net.bananaman.it_starts_with_magic.item.ModCreativeModTabs;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.bananaman.it_starts_with_magic.particle.ModSonicBoomParticle;
import net.bananaman.it_starts_with_magic.sound.ModSounds;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraftforge.api.distmarker.Dist;
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
        }

        @SubscribeEvent
        public static void registerParticleProvider(RegisterParticleProvidersEvent event){
            event.registerSpriteSet(ModParticles.MODSONICBOOMPARTICLE.get(), ModSonicBoomParticle.Provider::new);

        }
    }
}
