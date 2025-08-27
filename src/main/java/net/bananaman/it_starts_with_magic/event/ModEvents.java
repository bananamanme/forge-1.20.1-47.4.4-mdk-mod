package net.bananaman.it_starts_with_magic.event;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.mana.ManaProvider;
import net.bananaman.it_starts_with_magic.mana.ManaSyncPacket;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioEquipEvent;

@Mod.EventBusSubscriber(modid = ItStartsWithMagicMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();



        if (!event.getEntity().level().isClientSide) {
            event.getEntity().getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
                if (mana.getMana() == 0) {
                    mana.setMana(mana.getMaxMana());
                }
                ModMessages.sendToPlayer(new ManaSyncPacket(mana.getMana()), (ServerPlayer) event.getEntity());
            });
        }

    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ManaProvider.MANA_CAPABILITY)
                    .ifPresent(oldMana -> {
                        event.getEntity().getCapability(ManaProvider.MANA_CAPABILITY)
                                .ifPresent(newMana -> {
                                    newMana.copyFrom(oldMana);
                                });
                    });


        }
    }

    @SubscribeEvent
    public static void onPlayerTick(LivingEvent.LivingTickEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
                if (mana.getMana() < mana.getMaxMana()) {
                    if (player.tickCount % 20 == 0) {
                        mana.addMana(1);
                        ModMessages.sendToPlayer(new ManaSyncPacket(mana.getMana()), player);
                    }
                }
            });
        }
    }

}
