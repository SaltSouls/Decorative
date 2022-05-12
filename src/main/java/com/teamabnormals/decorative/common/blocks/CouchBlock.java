package com.teamabnormals.decorative.common.blocks;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamabnormals.decorative.common.handlers.ShapeHandler;
import com.teamabnormals.decorative.common.property.DSeatShapes.SeatShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CouchBlock extends AbstractCouchBlock {
    // Block Collision/Outline
    final ImmutableMap<BlockState, VoxelShape> COLLISION_SHAPES;
    final ImmutableMap<BlockState, VoxelShape> SHAPES;
    Direction defaultFacing = Direction.NORTH.getOpposite();

    final VoxelShape COLLISION_BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);
    final VoxelShape[] COLLISION_BACK_SINGLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 5.0D, 11.0D, 15.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_BACK_MIDDLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 5.0D, 11.0D, 16.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_BACK_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 5.0D, 11.0D, 16.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_BACK_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 5.0D, 11.0D, 15.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_ARM_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 5.0D, 0.0D, 3.0D, 10.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_ARM_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(13.0D, 5.0D, 0.0D, 16.0D, 10.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_BACK_CORNER = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 5.0D, 11.0D, 16.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_SIDE_CORNER_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(11.0D, 5.0D, 0.0D, 16.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape[] COLLISION_SIDE_CORNER_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 5.0D, 0.0D, 5.0D, 14.0D, 16.0D), defaultFacing));
    final VoxelShape SHAPE_BASE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D);
    final VoxelShape[] SHAPE_BACK_SINGLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 7.0D, 11.0D, 15.0D, 16.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_BACK_MIDDLE = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 7.0D, 11.0D, 16.0D, 16.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_BACK_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(1.0D, 7.0D, 11.0D, 16.0D, 16.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_BACK_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 7.0D, 11.0D, 15.0D, 16.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_ARM_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 7.0D, 0.0D, 3.0D, 12.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_ARM_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(13.0D, 7.0D, 0.0D, 16.0D, 12.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_BACK_CORNER = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 7.0D, 11.0D, 16.0D, 16.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_SIDE_CORNER_RIGHT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(11.0D, 7.0D, 0.0D, 16.0D, 16.0D, 16.0D), defaultFacing));
    final VoxelShape[] SHAPE_SIDE_CORNER_LEFT = ShapeHandler.getRotated(ShapeHandler.rotate(Block.box(0.0D, 7.0D, 0.0D, 5.0D, 16.0D, 16.0D), defaultFacing));

    protected ImmutableBiMap<BlockState, VoxelShape> shapeConstructor(ImmutableList<BlockState> states) {
        ImmutableBiMap.Builder<BlockState, VoxelShape> outline = new ImmutableBiMap.Builder<>();
        for(BlockState state : states) {
            Direction facing = state.getValue(FACING);
            SeatShape type = state.getValue(SHAPE);
            List<VoxelShape> shapes = new ArrayList<>();
            switch (type) {
                case SINGLE -> {
                    shapes.add(SHAPE_BASE);
                    shapes.add(SHAPE_BACK_SINGLE[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_RIGHT[facing.get2DDataValue()]);
                }
                case MIDDLE -> {
                    shapes.add(SHAPE_BASE);
                    shapes.add(SHAPE_BACK_MIDDLE[facing.get2DDataValue()]);
                }
                case LEFT -> {
                    shapes.add(SHAPE_BASE);
                    shapes.add(SHAPE_BACK_LEFT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_LEFT[facing.get2DDataValue()]);
                }
                case RIGHT -> {
                    shapes.add(SHAPE_BASE);
                    shapes.add(SHAPE_BACK_RIGHT[facing.get2DDataValue()]);
                    shapes.add(SHAPE_ARM_RIGHT[facing.get2DDataValue()]);
                }
                case CORNER_LEFT -> {
                    shapes.add(SHAPE_BASE);
                    shapes.add(SHAPE_BACK_CORNER[facing.get2DDataValue()]);
                    shapes.add(SHAPE_SIDE_CORNER_RIGHT[facing.get2DDataValue()]);
                }
                case CORNER_RIGHT -> {
                    shapes.add(SHAPE_BASE);
                    shapes.add(SHAPE_BACK_CORNER[facing.get2DDataValue()]);
                    shapes.add(SHAPE_SIDE_CORNER_LEFT[facing.get2DDataValue()]);
                }
            }
            outline.put(state, ShapeHandler.combineAll(shapes));
        }
        return outline.build();
    }
    protected ImmutableBiMap<BlockState, VoxelShape> collisionConstructor(ImmutableList<BlockState> states) {
        ImmutableBiMap.Builder<BlockState, VoxelShape> collision = new ImmutableBiMap.Builder<>();
        for(BlockState state : states) {
            Direction facing = state.getValue(FACING);
            SeatShape type = state.getValue(SHAPE);
            List<VoxelShape> shapes = new ArrayList<>();
            switch (type) {
                case SINGLE -> {
                    shapes.add(COLLISION_BASE);
                    shapes.add(COLLISION_BACK_SINGLE[facing.get2DDataValue()]);
                    shapes.add(COLLISION_ARM_LEFT[facing.get2DDataValue()]);
                    shapes.add(COLLISION_ARM_RIGHT[facing.get2DDataValue()]);
                }
                case MIDDLE -> {
                    shapes.add(COLLISION_BASE);
                    shapes.add(COLLISION_BACK_MIDDLE[facing.get2DDataValue()]);
                }
                case LEFT -> {
                    shapes.add(COLLISION_BASE);
                    shapes.add(COLLISION_BACK_LEFT[facing.get2DDataValue()]);
                    shapes.add(COLLISION_ARM_LEFT[facing.get2DDataValue()]);
                }
                case RIGHT -> {
                    shapes.add(COLLISION_BASE);
                    shapes.add(COLLISION_BACK_RIGHT[facing.get2DDataValue()]);
                    shapes.add(COLLISION_ARM_RIGHT[facing.get2DDataValue()]);
                }
                case CORNER_LEFT -> {
                    shapes.add(COLLISION_BASE);
                    shapes.add(COLLISION_BACK_CORNER[facing.get2DDataValue()]);
                    shapes.add(COLLISION_SIDE_CORNER_RIGHT[facing.get2DDataValue()]);
                }
                case CORNER_RIGHT -> {
                    shapes.add(COLLISION_BASE);
                    shapes.add(COLLISION_BACK_CORNER[facing.get2DDataValue()]);
                    shapes.add(COLLISION_SIDE_CORNER_LEFT[facing.get2DDataValue()]);
                }
            }
            collision.put(state, ShapeHandler.combineAll(shapes));
        }
        return collision.build();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return SHAPES.get(state); }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) { return COLLISION_SHAPES.get(state); }

    // Default State
    public CouchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(SHAPE, SeatShape.SINGLE));
        SHAPES = this.shapeConstructor(this.getStateDefinition().getPossibleStates());
        COLLISION_SHAPES = this.collisionConstructor(this.getStateDefinition().getPossibleStates());
    }
}