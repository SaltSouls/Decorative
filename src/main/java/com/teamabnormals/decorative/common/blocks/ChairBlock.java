package com.teamabnormals.decorative.common.blocks;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamabnormals.decorative.common.handlers.ShapeHandler;
import com.teamabnormals.decorative.core.registry.DBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class ChairBlock extends AbstractChairBlock {
    // Block Collision/Outline
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;
    protected ImmutableBiMap<BlockState, VoxelShape> shapeConstructor(ImmutableList<BlockState> states) {
        Direction defaultFacing = Direction.NORTH.getOpposite();
        final VoxelShape SHAPE_BASE = Block.box(3.0D, 6.0D, 3.0D, 13.0D, 8.0D, 13.0D);
        final VoxelShape SHAPE_LEG_IXXX = Block.box(3.0D, 0.0D, 3.0D, 5.0D, 6.0D, 5.0D);
        final VoxelShape SHAPE_LEG_XIXX = Block.box(3.0D, 0.0D, 11.0D, 5.0D, 6.0D, 13.0D);
        final VoxelShape SHAPE_LEG_XXIX = Block.box(11.0D, 0.0D, 3.0D, 13.0D, 6.0D, 5.0D);
        final VoxelShape SHAPE_LEG_XXXI = Block.box(11.0D, 0.0D, 11.0D, 13.0D, 6.0D, 13.0D);
        final VoxelShape[] SHAPE_BASE_BACK = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(3.0D, 8.0D, 11.0D, 13.0D, 16.0D, 13.0D), defaultFacing));
        final VoxelShape[] SHAPE_TOP = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(3.0D, 0.0D, 11.0D, 13.0D, 4.0D, 13.0D), defaultFacing));

        ImmutableBiMap.Builder<BlockState, VoxelShape> shape = new ImmutableBiMap.Builder<>();
        for(BlockState state : states) {
            Direction facing = state.getValue(FACING);
            boolean isLower = state.getValue(HALF) == DoubleBlockHalf.LOWER;
            List<VoxelShape> shapes = new ArrayList<>();
            if(isLower) {
                shapes.add(SHAPE_BASE);
                shapes.add(SHAPE_LEG_IXXX);
                shapes.add(SHAPE_LEG_XIXX);
                shapes.add(SHAPE_LEG_XXIX);
                shapes.add(SHAPE_LEG_XXXI);
                shapes.add(SHAPE_BASE_BACK[facing.get2DDataValue()]);
            } else { shapes.add(SHAPE_TOP[facing.get2DDataValue()]); }
            shape.put(state, ShapeHandler.combineAll(shapes));
        }
        return shape.build();
    }
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPES.get(state); }

    // Default State
    public ChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.LOWER));
        SHAPES = this.shapeConstructor(this.getStateDefinition().getPossibleStates());
    }

    /*
        Result Types:
        SUCCESS[Client]: I did a thing, show the animation saying I did the thing.
        CONSUME[Server]: I did a thing, but don't show an animation.
        PASS: I don't have anything to do.
        FAIL: I had something to do, but I couldn't do it.
    */

    // Chair Interaction/Replacement
    private InteractionResult updateChair(BlockState state, Level world, BlockPos pos, Player player) {
        if(!world.isClientSide) {
            boolean isLower = state.getValue(HALF) == DoubleBlockHalf.LOWER;
            boolean flag = world.getBlockState(pos.above()).getFluidState().getType() == Fluids.WATER;
            boolean flag1 = world.getBlockState(pos.below()).getFluidState().getType() == Fluids.WATER;
            if (!player.isCreative()) { player.getMainHandItem().shrink(2); }
            if(isLower) {
                world.removeBlock(pos, false);
                world.setBlockAndUpdate(pos, state.setValue(HALF, DoubleBlockHalf.LOWER));
                world.setBlockAndUpdate(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER).setValue(WATERLOGGED, flag));
                return InteractionResult.CONSUME;
            } else {
                world.removeBlock(pos.below(), false);
                world.setBlockAndUpdate(pos.below(), state.setValue(HALF, DoubleBlockHalf.LOWER).setValue(WATERLOGGED, flag1));
                world.setBlockAndUpdate(pos, state.setValue(HALF, DoubleBlockHalf.UPPER));
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.SUCCESS;
    }
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!player.isCrouching()) {
            if(!player.getMainHandItem().isEmpty()) {
                Item item = player.getMainHandItem().getItem();
                int count = player.getMainHandItem().getCount();
                boolean isCreative = player.isCreative();
                if (item == DBlocks.WHITE_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.WHITE_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.ORANGE_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.ORANGE_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.MAGENTA_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.MAGENTA_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.LIGHT_BLUE_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.LIGHT_BLUE_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.YELLOW_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.YELLOW_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.LIME_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.LIME_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.PINK_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.PINK_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.GRAY_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.GRAY_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.LIGHT_GRAY_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.LIGHT_GRAY_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.CYAN_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.CYAN_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.PURPLE_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.PURPLE_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.BLUE_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.BLUE_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.BROWN_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.BROWN_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.GREEN_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.GREEN_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.RED_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.RED_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else if (item == DBlocks.BLACK_CUSHION.get().asItem()) {
                    if(!isCreative && count < 2) { return InteractionResult.PASS; }
                    return updateChair(DBlocks.BLACK_CUSHIONED_CHAIR.get().withPropertiesOf(state), world, pos, player);
                }
                else { return InteractionResult.PASS; }
            }
        }
        return InteractionResult.PASS;
    }
}