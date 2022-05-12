package com.teamabnormals.decorative.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.Nullable;

public class AbstractCouchBlock extends SeatExtendable implements SimpleWaterloggedBlock {
    // Block Properties
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SHAPE);
    }

    public AbstractCouchBlock(BlockBehaviour.Properties properties) { super(properties); }

    // Placement State of Block
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        Direction direction = ctx.getHorizontalDirection();
        BlockPos pos = ctx.getClickedPos();
        LevelAccessor world = ctx.getLevel();
        FluidState fluidstate = ctx.getLevel().getFluidState(ctx.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        BlockState state = this.defaultBlockState().setValue(FACING, direction.getOpposite()).setValue(WATERLOGGED, flag);
        return this.getSeatState(state, world, pos);
    }

    // Seat Connection
    @Override
    public boolean isSeat(BlockState state) {
        return state.getBlock() instanceof AbstractCouchBlock;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return this.getSeatState(state, world, currentPos);
    }
    // Rotation Properties
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        Direction direction = rotation.rotate(state.getValue(FACING));
        return state.setValue(FACING, direction);
    }
    @Override
    public BlockState mirror(BlockState state, Mirror rotation) {
        Direction direction = rotation.mirror(state.getValue(FACING));
        return state.setValue(FACING, direction);
    }

    // Waterlogged State
    @Override
    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false): super.getFluidState(state);
    }

    // Mob Pathfinding Conditions
    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType path) {
        return false;
    }
}