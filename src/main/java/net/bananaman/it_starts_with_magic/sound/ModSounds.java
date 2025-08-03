package net.bananaman.it_starts_with_magic.sound;

import com.ibm.icu.text.AlphabeticIndex;
import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ItStartsWithMagicMod.MOD_ID);

        public static final RegistryObject<SoundEvent> TECH = registerSoundEvents("tech");

    public static final RegistryObject<SoundEvent> WEIRD_RYTHEM = registerSoundEvents("weird_rythem");

    public static final RegistryObject<SoundEvent> RUSSSSIAN = registerSoundEvents("russssian");


    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
            return SOUND_EVENTS.register(name,()->SoundEvent.createVariableRangeEvent(new ResourceLocation(ItStartsWithMagicMod.MOD_ID, name)));
    }


    public static void  register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }


}
