package net.bananaman.it_starts_with_magic.spells.api;

import net.bananaman.it_starts_with_magic.spells.BoomBeam;
import net.bananaman.it_starts_with_magic.spells.FireBeam;
import net.bananaman.it_starts_with_magic.spells.MagicBullet;
import net.bananaman.it_starts_with_magic.spells.Spell;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public final class SpellRegistry {
    private static final Map<String, Spell> SPELLS = new LinkedHashMap<>();

    public static void register(Spell spell) {
        SPELLS.put(spell.getId(), spell);
    }

    public static Collection<Spell> values() { return SPELLS.values(); }

    public static Spell byId(String id) { return SPELLS.get(id); }

    static {
        register(new BoomBeam());
        register(new FireBeam());
        register(new MagicBullet());
    }
}
