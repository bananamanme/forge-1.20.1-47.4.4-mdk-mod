package net.bananaman.it_starts_with_magic.block.entity.custom;

import net.bananaman.it_starts_with_magic.block.entity.ModBlocksEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PedestalBlockEntity extends BlockEntity {


    public final ItemStackHandler invetory = new ItemStackHandler(1){
        @Override
        protected int getStackLimit(int slot, @NotNull ItemStack stack) {
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(),getBlockState(),getBlockState(),3);
            }
        }
    };

    public PedestalBlockEntity( BlockPos pPos, BlockState pBlockState) {
        super(ModBlocksEntities.PEDESTAL_BE.get(), pPos, pBlockState);
    }

    private float rotation;
    private float position;
    private float the_x =1;
    public float getRenderRotation(){
        rotation +=0.5;
        if (rotation>=360) {
            rotation =0;
        }
        return rotation;
    }
    public float getRenderPosition(){
        position+=0.0004f * the_x;
        if (position >= 0.05f || position <= -0.02f) {
            the_x*=-1;
        }
        return position;
    }



    public void clearContents() {
        invetory.setStackInSlot(0,ItemStack.EMPTY);
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(invetory.getSlots());
        for (int i =0;i<invetory.getSlots(); i++) {
            inv.setItem(i, invetory.getStackInSlot(i));
        }

        Containers.dropContents(this.level,this.worldPosition,inv);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {

        pTag.put("inventory", invetory.serializeNBT());

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        invetory.deserializeNBT(pTag.getCompound("inventory"));
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }
}
