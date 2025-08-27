package net.bananaman.it_starts_with_magic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.bananaman.it_starts_with_magic.spells.BoomBeam;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;
import top.theillusivec4.curios.api.CuriosApi;

import java.security.Key;
import java.util.Optional;

public class keyBinding {
    public static final String KEY_CATEGORY_MAGIC = "key.category.it_starts_with_magic.magic";
    public static final String KEY_CAST_SPELL = "key.it_starts_with_magic.magic.cast_spell";
    public static final String KEY_OPEN_SPELLBOOK_GUI = "key.it_starts_with_magic.magic.open_spellbook_gui";


    public static final KeyMapping OPEN_GUI_KEY = new KeyMapping(KEY_OPEN_SPELLBOOK_GUI, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_MAGIC);

    public static final KeyMapping CASTING_KEY = new KeyMapping(KEY_CAST_SPELL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_MAGIC);

    public static final KeyMapping NEXT_SPELL_KEY =
            new KeyMapping("key.it_starts_with_magic.next_spell",
                    GLFW.GLFW_KEY_R,   // pick any key
                    "category.it_starts_with_magic");



}
