package net.bananaman.it_starts_with_magic.spells.spellcooldowns;

import java.util.HashMap;
import java.util.Map;

public class SpellCooldown implements ISpellCooldown{
    private final Map<String, Long> cooldowns = new HashMap<>();

    @Override
    public long getCooldown(String spellId) {
        return cooldowns.getOrDefault(spellId, 0L);
    }

    @Override
    public void setCooldown(String spellId, long cooldownEndTick) {
        cooldowns.put(spellId, cooldownEndTick);
    }

    @Override
    public boolean isOnCooldown(String spellId, long worldTime) {
        return getCooldown(spellId) > worldTime;
    }

    @Override
    public void copyFrom(ISpellCooldown source) {
        if (source instanceof SpellCooldown) {
            this.cooldowns.putAll(((SpellCooldown) source).cooldowns);
        }
    }
}
