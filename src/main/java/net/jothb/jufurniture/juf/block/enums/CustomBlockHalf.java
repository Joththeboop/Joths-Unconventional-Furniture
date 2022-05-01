package net.jothb.jufurniture.juf.block.enums;
import net.minecraft.util.StringIdentifiable;

public enum CustomBlockHalf implements StringIdentifiable {
    UPPER("upper", null),
    MIDDLE("middle", UPPER),
    LOWER("lower", MIDDLE);

    private final String name;
    public final CustomBlockHalf nextUp;

    CustomBlockHalf(String name, CustomBlockHalf nextUp) {
        this.name=name;
        this.nextUp=nextUp;
    }


    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }

}
