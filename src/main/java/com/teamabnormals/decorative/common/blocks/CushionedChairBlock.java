package com.teamabnormals.decorative.common.blocks;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamabnormals.decorative.common.handlers.ShapeHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CushionedChairBlock extends AbstractChairBlock {
    // Block Collision/Outline
    final ImmutableMap<BlockState, VoxelShape> COLLISION_SHAPES;
    final ImmutableMap<BlockState, VoxelShape> SHAPES;
    Direction defaultFacing = Direction.NORTH.getOpposite();
    protected final VoxelShape SHAPE_BASE = Block.box(3.0D, 6.0D, 3.0D, 13.0D, 8.0D, 13.0D);
    protected final VoxelShape SHAPE_LEG_IXXX = Block.box(3.0D, 0.0D, 3.0D, 5.0D, 6.0D, 5.0D);
    protected final VoxelShape SHAPE_LEG_XIXX = Block.box(3.0D, 0.0D, 11.0D, 5.0D, 6.0D, 13.0D);
    protected final VoxelShape SHAPE_LEG_XXIX = Block.box(11.0D, 0.0D, 3.0D, 13.0D, 6.0D, 5.0D);
    protected final VoxelShape SHAPE_LEG_XXXI = Block.box(11.0D, 0.0D, 11.0D, 13.0D, 6.0D, 13.0D);
    protected final VoxelShape[] SHAPE_CUSHION = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(4.0D, 8.0D, 3.0D, 12.0D, 10.0D, 10.0D), defaultFacing));
    protected final VoxelShape[] SHAPE_BASE_BACK = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(3.0D, 8.0D, 11.0D, 13.0D, 16.0D, 13.0D), defaultFacing));
    protected final VoxelShape[] SHAPE_CUSHION_BACK = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(4.0D, 8.0D, 10.0D, 12.0D, 16.0D, 13.0D), defaultFacing));
    protected final VoxelShape[] SHAPE_TOP = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(3.0D, 0.0D, 11.0D, 13.0D, 4.0D, 13.0D), defaultFacing));
    protected final VoxelShape[] SHAPE_CUSHION_TOP = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(4.0D, 0.0D, 10.0D, 12.0D, 5.0D, 13.0D), defaultFacing));

    protected ImmutableBiMap<BlockState, VoxelShape> shapeConstructor(ImmutableList<BlockState> states) {
        ImmutableBiMap.Builder<BlockState, VoxelShape> outline = new ImmutableBiMap.Builder<>();
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
                shapes.add(SHAPE_CUSHION[facing.get2DDataValue()]);
                shapes.add(SHAPE_BASE_BACK[facing.get2DDataValue()]);
                shapes.add(SHAPE_CUSHION_BACK[facing.get2DDataValue()]);
            } else {
                shapes.add(SHAPE_TOP[facing.get2DDataValue()]);
                shapes.add(SHAPE_CUSHION_TOP[facing.get2DDataValue()]);
            }
            outline.put(state, ShapeHandler.combineAll(shapes));
        }
        return outline.build();
    }
    protected  ImmutableBiMap<BlockState, VoxelShape> collisionConstructor(ImmutableList<BlockState> states) {
        ImmutableBiMap.Builder<BlockState, VoxelShape> collision = new ImmutableBiMap.Builder<>();
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
            collision.put(state, ShapeHandler.combineAll(shapes));
        }
        return collision.build();
    }
    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPES.get(state); }
    @SuppressWarnings({"NullableProblems", "deprecation"})
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return COLLISION_SHAPES.get(state); }

    // Default State
    public CushionedChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(HALF, DoubleBlockHalf.LOWER));
        SHAPES = this.shapeConstructor(this.getStateDefinition().getPossibleStates());
        COLLISION_SHAPES = this.collisionConstructor(this.getStateDefinition().getPossibleStates());
    }
}
