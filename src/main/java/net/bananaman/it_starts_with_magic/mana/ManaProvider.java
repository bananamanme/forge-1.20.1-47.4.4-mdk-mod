package net.bananaman.it_starts_with_magic.mana;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ManaProvider implements ICapabilityProvider {
    public static Capability<IMana> MANA_CAPABILITY = null;

    private final Mana mana = new Mana();
    private final LazyOptional<IMana> optional = LazyOptional.of(() -> mana);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
        if (capability == MANA_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    public void invalidate() {
        optional.invalidate();
    }
}
