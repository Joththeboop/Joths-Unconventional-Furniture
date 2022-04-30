package net.jothb.jufurniture.juf;

import net.fabricmc.api.ModInitializer;
import net.jothb.jufurniture.juf.block.ModBlocks;
import net.jothb.jufurniture.juf.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Juf implements ModInitializer {

    public static final String MOD_ID="juf";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();


    }
}
