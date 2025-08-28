package net.bananaman.it_starts_with_magic.spells.api.spellcooldowns;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

public class SpellCooldownProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<ISpellCooldown> SPELL_COOLDOWN_CAPABILITY = CapabilityManager.get(new CapabilityToken<ISpellCooldown>() {});

    private ISpellCooldown spellCooldown = null;
    private final LazyOptional<ISpellCooldown> instance = LazyOptional.of(this::createSpellCooldown);

    private ISpellCooldown createSpellCooldown() {
        if (this.spellCooldown == null) {
            this.spellCooldown = new SpellCooldown();
        }
        return this.spellCooldown;
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        if (cap == SPELL_COOLDOWN_CAPABILITY) {
            return instance.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        // You would serialize your cooldown map here
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        // You would deserialize your cooldown map here
    }
}
