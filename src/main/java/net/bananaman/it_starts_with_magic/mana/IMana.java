package net.bananaman.it_starts_with_magic.mana;

import org.jetbrains.annotations.NotNull;

public interface IMana {
    int getMana();
    void setMana(int mana);
    void addMana(int mana);
    boolean consumeMana(int mana);
    int getMaxMana();

    void copyFrom(@NotNull IMana source);
}
