package net.bananaman.it_starts_with_magic.mana;

public class Mana implements IMana{
    private int mana;
    private static final int MAX_MANA = 100;

    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    public void addMana(int mana) { this.mana = Math.min(this.mana + mana, MAX_MANA); }
    public boolean consumeMana(int mana) {
        if (this.mana >= mana) {
            this.mana -= mana;
            return true;
        }
        return false;
    }
    public int getMaxMana() { return MAX_MANA; }

    public void copyFrom(IMana source) {
        this.mana = source.getMana();
    }
}
