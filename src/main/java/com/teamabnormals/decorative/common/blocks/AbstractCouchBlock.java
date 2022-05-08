package com.teamabnormals.decorative.common.blocks;

import com.teamabnormals.decorative.common.property.DBlockStateProperties;
import com.teamabnormals.decorative.common.property.DBlockStateProperties.SeatShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.Nullable;

public class AbstractCouchBlock extends Block implements SimpleWaterloggedBlock {
    // Block Properties
    public static final EnumProperty<SeatShape> SHAPE = DBlockStateProperties.SEAT_SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SHAPE);
    }

    public AbstractCouchBlock(BlockBehaviour.Properties properties) { super(properties); }

    // Placement State of Block
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidstate = ctx.getLevel().getFluidState(ctx.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        BlockState state = super.getStateForPlacement(ctx);
        return this.getCouchState(state.setValue(WATERLOGGED, flag).setValue(FACING, ctx.getHorizontalDirection().getOpposite()), ctx.getLevel(), ctx.getClickedPos(), state.getValue(FACING));
    }
    private BlockState getCouchState(BlockState state, LevelAccessor world, BlockPos pos, Direction dir) {
        Direction facing = state.getValue(FACING);
        DBlockStateProperties.SeatShape type = state.getValue(SHAPE);
        boolean isLeft = this.isCouch(world, pos, dir.getClockWise(), dir);
        boolean isRight = this.isCouch(world, pos, dir.getCounterClockWise(), dir);
        switch (facing) {
            case NORTH -> {
                if (this.isCouch(world, pos, dir, Direction.WEST)) { return state.setValue(SHAPE, SeatShape.CORNER_LEFT); }
                else if (this.isCouch(world, pos, dir, Direction.EAST)) { return state.setValue(SHAPE, SeatShape.CORNER_RIGHT); }
            }
            case EAST -> {
                if (this.isCouch(world, pos, dir, Direction.NORTH)) { return state.setValue(SHAPE, SeatShape.CORNER_LEFT); }
                else if (this.isCouch(world, pos, dir, Direction.SOUTH)) { return state.setValue(SHAPE, SeatShape.CORNER_RIGHT); }
            }
            case SOUTH -> {
                if (this.isCouch(world, pos, dir, Direction.EAST)) { return state.setValue(SHAPE, SeatShape.CORNER_LEFT); }
                else if (this.isCouch(world, pos, dir, Direction.WEST)) { return state.setValue(SHAPE, SeatShape.CORNER_RIGHT); }
            }
            case WEST -> {
                if (this.isCouch(world, pos, dir, Direction.SOUTH)) { return state.setValue(SHAPE, SeatShape.CORNER_LEFT); }
                else if (this.isCouch(world, pos, dir, Direction.NORTH)) { return state.setValue(SHAPE, SeatShape.CORNER_RIGHT); }
            }
        }
        if (isLeft && isRight) { return state.setValue(SHAPE, SeatShape.MIDDLE); }
        else if (isLeft) { return state.setValue(SHAPE, SeatShape.RIGHT); }
        else if (isRight) { return state.setValue(SHAPE, SeatShape.LEFT); }
        else { return state.setValue(SHAPE, SeatShape.SINGLE); }
    }
    private boolean isCouch(LevelAccessor world, BlockPos pos, Direction direction, Direction targetDirection) {
        BlockState state = world.getBlockState(pos.relative(direction));
        if(state.getBlock() == this) {
            Direction couchFacing = state.getValue(FACING);
            return couchFacing.equals(targetDirection);
        }
        return false;
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return this.getCouchState(state, world, currentPos, state.getValue(FACING));
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
