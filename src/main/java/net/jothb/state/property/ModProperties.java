package net.jothb.state.property;

import net.jothb.jufurniture.juf.block.enums.CustomBlockHalf;
import net.minecraft.state.property.EnumProperty;

public class ModProperties {
    public static final EnumProperty<CustomBlockHalf> CUSTOM_BLOCK_HALF = EnumProperty.of("half", CustomBlockHalf.class);
}
