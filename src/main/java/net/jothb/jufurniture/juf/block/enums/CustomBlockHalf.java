package net.jothb.jufurniture.juf.block.enums;
import net.minecraft.util.StringIdentifiable;

public enum CustomBlockHalf implements StringIdentifiable {
    UPPER("upper"),
    MIDDLE("middle"),
    LOWER("lower");

    private final String name;

    CustomBlockHalf(String name) {
        this.name=name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}
