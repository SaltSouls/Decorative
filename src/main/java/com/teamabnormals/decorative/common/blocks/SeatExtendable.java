package com.teamabnormals.decorative.common.blocks;

import com.teamabnormals.decorative.common.property.DBlockStateProperties;
import com.teamabnormals.decorative.common.property.DSeatShapes.SeatShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class SeatExtendable extends Block {
    // Block Properties
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<SeatShape> SHAPE = DBlockStateProperties.SEAT_SHAPE;

    public SeatExtendable(Properties properties) { super(properties); }

    // Seat Connection
    public BlockState getSeatState(BlockState state, LevelAccessor world, BlockPos pos) {
        Direction direction = state.getValue(FACING);
        SeatShape type = state.getValue(SHAPE);
        boolean isLeft = this.isSeatLeft(world, pos, direction);
        boolean isRight = this.isSeatRight(world, pos, direction);
        boolean isCornerLeft = this.isSeatCornerLeft(world, pos, direction);
        boolean isCornerRight = this.isSeatCornerRight(world, pos, direction);
        if (isCornerLeft) { return state.setValue(SHAPE, SeatShape.CORNER_RIGHT); }
        else if (isCornerRight) { return state.setValue(SHAPE, SeatShape.CORNER_LEFT); }
        else if (isLeft && isRight) { return state.setValue(SHAPE, SeatShape.MIDDLE); }
        else if (isLeft) { return state.setValue(SHAPE, SeatShape.RIGHT); }
        else if (isRight) { return state.setValue(SHAPE, SeatShape.LEFT); }
        else { return state.setValue(SHAPE, SeatShape.SINGLE); }
    }

    // Seat Connection Tests
    public boolean isSeat(BlockState state) {
        return state.getBlock() == this;
    }
    private boolean isSeatLeft(LevelAccessor world, BlockPos pos, Direction direction) {
        switch(direction) {
            case NORTH -> {
                BlockState isLeftN = world.getBlockState(pos.relative(Direction.Axis.X, 1));
                if (isSeat(isLeftN)) {
                    Direction joint = isLeftN.getValue(FACING);
                    if (joint == Direction.NORTH || joint == Direction.WEST) { return true; }
                }
            }
            case EAST -> {
                BlockState isLeftE = world.getBlockState(pos.relative(Direction.Axis.Z, 1));
                if (isSeat(isLeftE)) {
                    Direction joint = isLeftE.getValue(FACING);
                    if (joint == Direction.EAST || joint == Direction.NORTH) { return true; }
                }
            }
            case SOUTH -> {
                BlockState isLeftS = world.getBlockState(pos.relative(Direction.Axis.X, -1));
                if (isSeat(isLeftS)) {
                    Direction joint = isLeftS.getValue(FACING);
                    if (joint == Direction.SOUTH || joint == Direction.EAST) { return true; }
                }
            }
            case WEST -> {
                BlockState isLeftW = world.getBlockState(pos.relative(Direction.Axis.Z, -1));
                if (isSeat(isLeftW)) {
                    Direction joint = isLeftW.getValue(FACING);
                    if (joint == Direction.WEST || joint == Direction.SOUTH) { return true; }
                }
            }
        }
        return false;
    }
    private boolean isSeatCornerLeft(LevelAccessor world, BlockPos pos, Direction direction) {
        switch(direction) {
            case NORTH -> {
                BlockState isFrontN = world.getBlockState(pos.relative(Direction.Axis.Z, -1));
                if (isSeat(isFrontN)) {
                    Direction joint = isFrontN.getValue(FACING);
                    if(joint == Direction.EAST) { return true; }
                }
            }
            case EAST -> {
                BlockState isFrontE = world.getBlockState(pos.relative(Direction.Axis.X, 1));
                if (isSeat(isFrontE)) {
                    Direction joint = isFrontE.getValue(FACING);
                    if(joint == Direction.SOUTH) { return true; }
                }
            }
            case SOUTH -> {
                BlockState isFrontS = world.getBlockState(pos.relative(Direction.Axis.Z, 1));
                if (isSeat(isFrontS)) {
                    Direction joint = isFrontS.getValue(FACING);
                    if(joint == Direction.WEST) { return true; }
                }
            }
            case WEST -> {
                BlockState isFrontW = world.getBlockState(pos.relative(Direction.Axis.X, -1));
                if (isSeat(isFrontW)) {
                    Direction joint = isFrontW.getValue(FACING);
                    if(joint == Direction.NORTH) { return true; }
                }
            }
        }
        return false;
    }
    private boolean isSeatRight(LevelAccessor world, BlockPos pos, Direction direction) {
        switch(direction) {
            case NORTH -> {
                BlockState isRightN = world.getBlockState(pos.relative(Direction.Axis.X, -1));
                if (isSeat(isRightN)) {
                    Direction joint = isRightN.getValue(FACING);
                    if (joint == Direction.NORTH || joint == Direction.EAST) { return true; }
                }
            }
            case EAST -> {
                BlockState isRightE = world.getBlockState(pos.relative(Direction.Axis.Z, -1));
                if (isSeat(isRightE)) {
                    Direction joint = isRightE.getValue(FACING);
                    if (joint == Direction.EAST || joint == Direction.SOUTH) { return true; }
                }
            }
            case SOUTH -> {
                BlockState isRightS = world.getBlockState(pos.relative(Direction.Axis.X, 1));
                if (isSeat(isRightS)) {
                    Direction joint = isRightS.getValue(FACING);
                    if (joint == Direction.SOUTH || joint == Direction.WEST) { return true; }
                }
            }
            case WEST -> {
                BlockState isRightW = world.getBlockState(pos.relative(Direction.Axis.Z, 1));
                if (isSeat(isRightW)) {
                    Direction joint = isRightW.getValue(FACING);
                    if (joint == Direction.WEST || joint == Direction.NORTH) { return true; }
                }
            }
        }
        return false;
    }
    private boolean isSeatCornerRight(LevelAccessor world, BlockPos pos, Direction direction) {
        switch(direction) {
            case NORTH -> {
                BlockState isFrontN = world.getBlockState(pos.relative(Direction.Axis.Z, -1));
                if (isSeat(isFrontN)) {
                    Direction joint = isFrontN.getValue(FACING);
                    if(joint == Direction.WEST) { return true; }
                }
            }
            case EAST -> {
                BlockState isFrontE = world.getBlockState(pos.relative(Direction.Axis.X, 1));
                if (isSeat(isFrontE)) {
                    Direction joint = isFrontE.getValue(FACING);
                    if(joint == Direction.NORTH) { return true; }
                }
            }
            case SOUTH -> {
                BlockState isFrontS = world.getBlockState(pos.relative(Direction.Axis.Z, 1));
                if (isSeat(isFrontS)) {
                    Direction joint = isFrontS.getValue(FACING);
                    if(joint == Direction.EAST) { return true; }
                }
            }
            case WEST -> {
                BlockState isFrontW = world.getBlockState(pos.relative(Direction.Axis.X, -1));
                if (isSeat(isFrontW)) {
                    Direction joint = isFrontW.getValue(FACING);
                    if(joint == Direction.SOUTH) { return true; }
                }
            }
        }
        return false;
    }
}
