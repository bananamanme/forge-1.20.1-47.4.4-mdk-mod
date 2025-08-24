package net.bananaman.it_starts_with_magic.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import java.security.Key;

public class keyBinding {
    public static final String KEY_CATEGORY_MAGIC = "key.category.it_starts_with_magic.magic";
    public static final String KEY_CAST_SPELL = "key.it_starts_with_magic.magic.cast_spell";
    public static final String KEY_OPEN_SPELLBOOK_GUI = "key.it_starts_with_magic.magic.open_spellbook_gui";

    public static final KeyMapping OPEN_GUI_KEY = new KeyMapping(KEY_OPEN_SPELLBOOK_GUI, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_MAGIC);

    public static final KeyMapping CASTING_KEY = new KeyMapping(KEY_CAST_SPELL, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, KEY_CATEGORY_MAGIC);
}
