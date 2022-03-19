package com.teamabnormals.decorative.common.blocks;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamabnormals.decorative.common.handlers.ShapeHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CouchBlock extends Block implements SimpleWaterloggedBlock {
    // Block Collision/Outline
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;
    protected ImmutableBiMap<BlockState, VoxelShape> shapeConstructor(ImmutableList<BlockState> states) {
        Direction defaultFacing = Direction.NORTH.getOpposite();
        final VoxelShape[] SHAPE_BASE_SINGLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 0.0D, 1.0D, 15.0D, 6.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BACK_SINGLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(3.0D, 6.0D, 11.0D, 13.0D, 15.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BASE_MIDDLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 0.0D, 1.0D, 16.0D, 6.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BACK_MIDDLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 6.0D, 11.0D, 16.0D, 15.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BASE_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 0.0D, 1.0D, 16.0D, 6.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BACK_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(3.0D, 6.0D, 11.0D, 16.0D, 15.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BASE_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 0.0D, 1.0D, 15.0D, 6.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_BACK_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 6.0D, 11.0D, 13.0D, 15.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_ARM_JOINT_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 6.0D, 1.0D, 3.0D, 7.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_ARM_CUSHION_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 7.0D, 1.0D, 3.0D, 11.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_ARM_JOINT_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(13.0D, 6.0D, 1.0D, 15.0D, 7.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_ARM_CUSHION_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(13.0D, 7.0D, 1.0D, 16.0D, 11.0D, 16.0D), defaultFacing));
        final VoxelShape SHAPE_BASE_CORNER = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
        final VoxelShape[] SHAPE_BACK_CORNER = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 6.0D, 11.0D, 16.0D, 15.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_SIDE_CORNER_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(11.0D, 6.0D, 0.0D, 16.0D, 15.0D, 16.0D), defaultFacing));
        final VoxelShape[] SHAPE_SIDE_CORNER_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 6.0D, 0.0D, 5.0D, 15.0D, 16.0D), defaultFacing));

        ImmutableBiMap.Builder<BlockState, VoxelShape> shape = new ImmutableBiMap.Builder<>();
        for(BlockState state : states) {
            Direction facing = state.getValue(FACING);
            CouchShape type = state.getValue(SHAPE);
            List<VoxelShape> shapes = new ArrayList<>();
            switch (type) {
                case SINGLE -> {
                    shapes.add(SHAPE_BASE_SINGLE[facing.get2DDataValue()]);
                    shapes.add(SHAPE_BACK_SINGLE[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_JOINT_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_CUSHION_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_JOINT_RIGHT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_CUSHION_RIGHT[facing.get2DDataValue()]);
                }
                case MIDDLE -> {
                    shapes.add(SHAPE_BASE_MIDDLE[facing.get2DDataValue()]);
                    shapes.add(SHAPE_BACK_MIDDLE[facing.get2DDataValue()]);
                }
                case LEFT -> {
                    shapes.add(SHAPE_BASE_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_BACK_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_JOINT_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_CUSHION_LEFT[facing.get2DDataValue()]);
                }
                case RIGHT -> {
                    shapes.add(SHAPE_BASE_RIGHT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_BACK_RIGHT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_JOINT_RIGHT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_CUSHION_RIGHT[facing.get2DDataValue()]);
                }
                case CORNER_LEFT -> {
                    shapes.add(SHAPE_BASE_CORNER);
                    shapes.add(SHAPE_BACK_CORNER[facing.get2DDataValue()]);
                    shapes.add(SHAPE_SIDE_CORNER_LEFT[facing.get2DDataValue()]);
                }
                case CORNER_RIGHT -> {
                    shapes.add(SHAPE_BASE_CORNER);
                    shapes.add(SHAPE_BACK_CORNER[facing.get2DDataValue()]);
                    shapes.add(SHAPE_SIDE_CORNER_RIGHT[facing.get2DDataValue()]);
                }
            }
            shape.put(state, ShapeHandler.combineAll(shapes));
        }
        return shape.build();
    }

    // Block Properties
    public static final EnumProperty<CouchShape> SHAPE = EnumProperty.create("type", CouchShape.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED, SHAPE);
    }

    public CouchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(SHAPE, CouchShape.SINGLE));
        SHAPES = this.shapeConstructor(this.getStateDefinition().getPossibleStates());
    }


    // Placement State of Block
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        BlockState state = super.getStateForPlacement(ctx);
        return this.getCouchState(state, ctx.getLevel(), ctx.getClickedPos(), state.getValue(FACING));
    }
    private BlockState getCouchState(BlockState state, LevelAccessor world, BlockPos pos, Direction dir) {
        boolean left = this.isCouch(world, pos, dir.getCounterClockWise(), dir) || this.isCouch(world, pos, dir.getCounterClockWise(), dir.getCounterClockWise());
        boolean right = this.isCouch(world, pos, dir.getClockWise(), dir) || this.isCouch(world, pos, dir.getClockWise(), dir.getClockWise());
        boolean cornerLeft = this.isCouch(world, pos, dir.getOpposite(), dir.getCounterClockWise());
        boolean cornerRight = this.isCouch(world, pos, dir.getOpposite(), dir.getClockWise());

        if(cornerLeft) { return state.setValue(SHAPE, CouchShape.CORNER_LEFT); }
        else if(cornerRight) { return state.setValue(SHAPE, CouchShape.CORNER_RIGHT); }
        else if(left && right) { return state.setValue(SHAPE, CouchShape.MIDDLE); }
        else if(left) { return state.setValue(SHAPE, CouchShape.RIGHT); }
        else if(right) { return state.setValue(SHAPE, CouchShape.LEFT); }
        return state.setValue(SHAPE, CouchShape.SINGLE);
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

    //Waterlogged State
    @Override
    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false): super.getFluidState(state);
    }

    // Mob Pathfinding Conditions
    @Override
    public boolean isPathfindable(BlockState state, BlockGetter world, BlockPos pos, PathComputationType path) {
        return false;
    }

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
