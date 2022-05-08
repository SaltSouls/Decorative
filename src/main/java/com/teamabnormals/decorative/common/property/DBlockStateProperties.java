package com.teamabnormals.decorative.common.property;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class DBlockStateProperties {

    // Block Properties
    public static final IntegerProperty ROTATION_0_7 = IntegerProperty.create("rotation", 0, 7);
    public static final EnumProperty<SeatShape> SEAT_SHAPE = EnumProperty.create("type", SeatShape.class);
    // Couch Shapes
    public static enum SeatShape implements StringRepresentable {
        SINGLE("single"),
        LEFT("left"),
        RIGHT("right"),
        MIDDLE("middle"),
        CORNER_LEFT("corner_left"),
        CORNER_RIGHT("corner_right");
        private final String id;
        SeatShape(String id) { this.id = id; }
        @Override
        public String toString() { return id; }
        @Override
        public String getSerializedName() { return id; }
    }
}
