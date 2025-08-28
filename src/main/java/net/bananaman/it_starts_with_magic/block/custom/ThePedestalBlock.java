package net.bananaman.it_starts_with_magic.block.custom;

import net.bananaman.it_starts_with_magic.block.entity.custom.PedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class ThePedestalBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = ThePedestalBlock.box(0,0,0,16,16,16);


    public ThePedestalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PedestalBlockEntity(pPos, pState);
    }

    @Override
    public void onRemove(BlockState pState, Level pLevel,
                         BlockPos pPos, BlockState pNewState, boolean pMovedByPiston) {
        if (pState.getBlock() != pNewState.getBlock()) {
            if (pLevel.getBlockEntity(pPos) instanceof PedestalBlockEntity pedestalBlockEntity) {
                pedestalBlockEntity.drops();
                pLevel.updateNeighborsAt(pPos,this);
            }
        }
        super.onRemove(pState, pLevel, pPos, pNewState, pMovedByPiston);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel,
                                 BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        ItemStack pStack = pPlayer.getItemInHand(pHand);

        if (pLevel.getBlockEntity(pPos) instanceof PedestalBlockEntity pedestalBlockEntity){
            if (pedestalBlockEntity.invetory.getStackInSlot(0).isEmpty() && !pStack.isEmpty()){
                pedestalBlockEntity.invetory.insertItem(0, pStack.copy(), false);
                pStack.shrink(1);
                pLevel.playSound(pPlayer,pPos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS,1f,2f);
            } else if (pStack.isEmpty()){
                ItemStack stackOnPedestal  = pedestalBlockEntity.invetory.extractItem(0,1,false);
                pPlayer.setItemInHand(InteractionHand.MAIN_HAND,stackOnPedestal);
                pedestalBlockEntity.clearContents();
                pLevel.playSound(pPlayer,pPos,SoundEvents.ITEM_PICKUP,SoundSource.BLOCKS,1f,1f);

            }

        }
        return InteractionResult.SUCCESS;
    }
}
