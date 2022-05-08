package com.teamabnormals.decorative.common.blocks;

import com.teamabnormals.decorative.util.Triple;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
    public static final EnumProperty<CouchShape> SHAPE = EnumProperty.create("type", CouchShape.class);
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
        CouchShape type = state.getValue(SHAPE);
        switch (facing) {
            case NORTH:
            case SOUTH:
                Triple<Boolean, CouchShape, Direction> isLeftX = this.isCouchSide(world, pos, Direction.Axis.X, 1);
                Triple<Boolean, CouchShape, Direction> isRightX = this.isCouchSide(world, pos, Direction.Axis.X, -1);
                if (isLeftX.getA() && isRightX.getA()) {
                    return state.setValue(SHAPE, CouchShape.MIDDLE);
                } else if (dir == Direction.NORTH) {
                     Triple<Boolean, CouchShape, Direction> shouldCurve = this.isCouchSide(world, pos, Direction.Axis.Z, -1);
                     if (shouldCurve.getA()) {
                        if (shouldCurve.getC() == Direction.EAST) {
                            return state.setValue(SHAPE, CouchShape.CORNER_RIGHT);
                        } else {
                            return state.setValue(SHAPE, CouchShape.CORNER_LEFT);
                        }
                     } else if (isLeftX.getA() && !isRightX.getA()) {
                        return state.setValue(SHAPE, CouchShape.RIGHT);
                    } else if (!isLeftX.getA() && isRightX.getA()) {
                        return state.setValue(SHAPE, CouchShape.LEFT);
                    }
                } else if (dir == Direction.SOUTH) {
                    Triple<Boolean, CouchShape, Direction> shouldCurve = this.isCouchSide(world, pos, Direction.Axis.Z, 1);
                    if (shouldCurve.getA()) {
                        if (shouldCurve.getC() == Direction.WEST) {
                            return state.setValue(SHAPE, CouchShape.CORNER_RIGHT);
                        } else {
                            return state.setValue(SHAPE, CouchShape.CORNER_LEFT);
                        }
                    } else if (isLeftX.getA() && !isRightX.getA()) {
                        return state.setValue(SHAPE, CouchShape.LEFT);
                    } else if (!isLeftX.getA() && isRightX.getA()) {
                        return state.setValue(SHAPE, CouchShape.RIGHT);
                    }
                } else {
                return state.setValue(SHAPE, CouchShape.SINGLE);
            }
            case EAST:
            case WEST:
                Triple<Boolean, CouchShape, Direction> isLeftY = this.isCouchSide(world, pos, Direction.Axis.Z, 1);
                Triple<Boolean, CouchShape, Direction> isRightY = this.isCouchSide(world, pos, Direction.Axis.Z, -1);
                if (isLeftY.getA() && isRightY.getA()) {
                    return state.setValue(SHAPE, CouchShape.MIDDLE);
                } else if (dir == Direction.EAST) {
                    Triple<Boolean, CouchShape, Direction> shouldCurve = this.isCouchSide(world, pos, Direction.Axis.X, 1);
                    if (shouldCurve.getA()) {
                        if (shouldCurve.getC() == Direction.SOUTH) {
                            return state.setValue(SHAPE, CouchShape.CORNER_RIGHT);
                        } else {
                            return state.setValue(SHAPE, CouchShape.CORNER_LEFT);
                        }
                    } else if (isLeftY.getA() && !isRightY.getA()) {
                        return state.setValue(SHAPE, CouchShape.RIGHT);
                    } else if (!isLeftY.getA() && isRightY.getA()) {
                        return state.setValue(SHAPE, CouchShape.LEFT);
                    }
                } else if (dir == Direction.WEST) {
                    Triple<Boolean, CouchShape, Direction> shouldCurve = this.isCouchSide(world, pos, Direction.Axis.X, -1);
                    if (shouldCurve.getA()) {
                        if (shouldCurve.getC() == Direction.NORTH) {
                            return state.setValue(SHAPE, CouchShape.CORNER_RIGHT);
                        } else {
                            return state.setValue(SHAPE, CouchShape.CORNER_LEFT);
                        }
                    } else if (isLeftY.getA() && !isRightY.getA()) {
                        return state.setValue(SHAPE, CouchShape.LEFT);
                    } else if (!isLeftY.getA() && isRightY.getA()) {
                        return state.setValue(SHAPE, CouchShape.RIGHT);
                    }
                } else {
                    return state.setValue(SHAPE, CouchShape.SINGLE);
                }
        }
        return state.setValue(SHAPE, CouchShape.SINGLE);
    }

    private Triple<Boolean, CouchShape, Direction> isCouchSide(LevelAccessor world, BlockPos pos, Direction.Axis relativeAxis, int step) {
        BlockState state = world.getBlockState(pos.relative(relativeAxis, step));
        if(state.getBlock() == this) {
            return new Triple(true, state.getValue(SHAPE), state.getValue(FACING));
        }
        return new Triple(false, CouchShape.SINGLE, Direction.NORTH);
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

    // Couch Shapes
    public enum CouchShape implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right"),
        MIDDLE("middle"),
        CORNER_LEFT("corner_left"),
        CORNER_RIGHT("corner_right");

        private final String id;
        CouchShape(String id) { this.id = id; }
        @Override
        public String toString() { return id; }
        @Override
        public String getSerializedName() { return id; }
    }
}
