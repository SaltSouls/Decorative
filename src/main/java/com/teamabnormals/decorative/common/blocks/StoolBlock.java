package com.teamabnormals.decorative.common.blocks;

import com.teamabnormals.decorative.core.registry.DBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StoolBlock extends AbstractStoolBlock {
    // Block Collision/Outline
    protected static final VoxelShape SHAPE = Block.box(3.0D, 0.0D, 3.0D, 13.0D, 12.0D, 13.0D);
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    // Default State
    public StoolBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(ROTATION, 0).setValue(WATERLOGGED, false));
    }

    /*
        Result Types:
        SUCCESS[Client]: I did a thing, show the animation saying I did the thing.
        CONSUME[Server]: I did a thing, but don't show an animation.
        PASS: I don't have anything to do.
        FAIL: I had something to do, but I couldn't do it.
    */

    // Stool Interaction/Replacement
    private InteractionResult updateStool(BlockState state, Level world, BlockPos pos, Player player) {
        if(!world.isClientSide) {
            if (!player.isCreative()) { player.getMainHandItem().shrink(1); }
            world.setBlockAndUpdate(pos, state);
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!player.isCrouching()) {
            if(!player.getMainHandItem().isEmpty()) {
                Item item = player.getMainHandItem().getItem();
                if (item == DBlocks.WHITE_CUSHION.get().asItem()) { return updateStool(DBlocks.WHITE_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.ORANGE_CUSHION.get().asItem()) { return updateStool(DBlocks.ORANGE_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.MAGENTA_CUSHION.get().asItem()) { return updateStool(DBlocks.MAGENTA_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.LIGHT_BLUE_CUSHION.get().asItem()) { return updateStool(DBlocks.LIGHT_BLUE_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.YELLOW_CUSHION.get().asItem()) { return updateStool(DBlocks.YELLOW_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.LIME_CUSHION.get().asItem()) { return updateStool(DBlocks.LIME_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.PINK_CUSHION.get().asItem()) { return updateStool(DBlocks.PINK_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.GRAY_CUSHION.get().asItem()) { return updateStool(DBlocks.GRAY_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.LIGHT_GRAY_CUSHION.get().asItem()) { return updateStool(DBlocks.LIGHT_GRAY_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.CYAN_CUSHION.get().asItem()) { return updateStool(DBlocks.CYAN_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.PURPLE_CUSHION.get().asItem()) { return updateStool(DBlocks.PURPLE_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.BLUE_CUSHION.get().asItem()) { return updateStool(DBlocks.BLUE_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.BROWN_CUSHION.get().asItem()) { return updateStool(DBlocks.BROWN_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.GREEN_CUSHION.get().asItem()) { return updateStool(DBlocks.GREEN_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.RED_CUSHION.get().asItem()) { return updateStool(DBlocks.RED_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else if (item == DBlocks.BLACK_CUSHION.get().asItem()) { return updateStool(DBlocks.BLACK_CUSHIONED_STOOL.get().withPropertiesOf(state), world, pos, player); }
                else { return InteractionResult.PASS; }
            }
        }
        return InteractionResult.PASS;
    }
}
