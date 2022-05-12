package com.teamabnormals.decorative.common.blocks;

import com.teamabnormals.decorative.common.property.DBlockStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;

import javax.annotation.Nullable;

public class AbstractCushionBlock extends Block implements SimpleWaterloggedBlock {
    // Block Properties
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final IntegerProperty ROTATION = DBlockStateProperties.ROTATION_0_7;
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ROTATION, WATERLOGGED);
    }

    public AbstractCushionBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    // Placement State of Block
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidstate = ctx.getLevel().getFluidState(ctx.getClickedPos());
        boolean flag = fluidstate.getType() == Fluids.WATER;
        int flag1 = Mth.floor((double) (180.0F + (ctx.getRotation()) * 8.0F / 360.0F) + 0.5D) & 7;
        return this.defaultBlockState().setValue(ROTATION, flag1).setValue(WATERLOGGED, flag);
    }
    // Rotation Properties
    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        int value = rotation.rotate(state.getValue(ROTATION), 8);
        return state.setValue(ROTATION, value);
    }
    @Override
    public BlockState mirror(BlockState state, Mirror rotation) {
        int value = rotation.mirror(state.getValue(ROTATION), 8);
        return state.setValue(ROTATION, value);
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