package com.teamabnormals.decorative.common.blocks;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.teamabnormals.decorative.common.handlers.ShapeHandler;
import com.teamabnormals.decorative.common.property.DBlockStateProperties.SeatShape;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.ArrayList;
import java.util.List;

public class CouchBlock extends AbstractCouchBlock {
    // Block Collision/Outline
    public final ImmutableMap<BlockState, VoxelShape> SHAPES;

    public CouchBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false).setValue(SHAPE, SeatShape.SINGLE));
        SHAPES = this.shapeConstructor(this.getStateDefinition().getPossibleStates());
    }

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
            SeatShape type = (SeatShape) state.getValue(SHAPE);
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
}
