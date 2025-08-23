package net.bananaman.it_starts_with_magic.modstuff;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SpellBookHolderProvider implements ICapabilitySerializable<CompoundTag> {
    public static final Capability<ISpellbookHolder> SPELLBOOK_HOLDER_CAPABILITY = CapabilityManager.get(new CapabilityToken<ISpellbookHolder>() {});
    private final SpellBookHolder holder = new SpellBookHolder();
    private final LazyOptional<ISpellbookHolder> optional = LazyOptional.of(() -> holder);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == SPELLBOOK_HOLDER_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return holder.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        holder.deserializeNBT(nbt);
    }
}
