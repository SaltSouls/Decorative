package com.teamabnormals.decorative.common.property;

import net.minecraft.util.StringRepresentable;

public class DSeatShapes {
    // Seat Shapes
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
