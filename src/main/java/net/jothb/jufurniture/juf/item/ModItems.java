package net.jothb.jufurniture.juf.item;

import net.jothb.jufurniture.juf.Juf;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(Juf.MOD_ID, name), item);
    }

    public static void registerModItems(){
        Juf.LOGGER.info("Registering Mod Items for " + Juf.MOD_ID);
    }
}
