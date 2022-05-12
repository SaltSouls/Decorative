package com.teamabnormals.decorative.common.property;

import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import com.teamabnormals.decorative.common.property.DSeatShapes.SeatShape;

public class DBlockStateProperties {

    // Block Properties
    public static final IntegerProperty ROTATION_0_7 = IntegerProperty.create("rotation", 0, 7);
    public static final EnumProperty<SeatShape> SEAT_SHAPE = EnumProperty.create("type", SeatShape.class);
}
