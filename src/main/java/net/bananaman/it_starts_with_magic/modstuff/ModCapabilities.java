package net.bananaman.it_starts_with_magic.modstuff;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.mana.IMana;
import net.bananaman.it_starts_with_magic.mana.ManaProvider;
import net.bananaman.it_starts_with_magic.spells.spellcooldowns.SpellCooldownProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.entity.Entity;

@Mod.EventBusSubscriber(modid = "it_starts_with_magic")
public class ModCapabilities {
    @SubscribeEvent
    public static void  onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(IMana.class);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation("it_starts_with_magic","mana"),new ManaProvider());
            event.addCapability(new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "spell_cooldown"), new SpellCooldownProvider());
        }
    }
}
