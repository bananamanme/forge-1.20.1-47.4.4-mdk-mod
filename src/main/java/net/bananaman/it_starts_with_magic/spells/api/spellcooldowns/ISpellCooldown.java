package net.bananaman.it_starts_with_magic.spells.api.spellcooldowns;

public interface ISpellCooldown {
    long getCooldown(String spellId);
    void setCooldown(String spellId, long cooldownEndTick);
    boolean isOnCooldown(String spellId, long worldTime);
    void copyFrom(ISpellCooldown source);
}
