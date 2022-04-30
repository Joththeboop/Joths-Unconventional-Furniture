package net.jothb.jufurniture.juf.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.jothb.jufurniture.juf.Juf;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class JufGroup {
    public static final ItemGroup JUF = FabricItemGroupBuilder.build(new Identifier(Juf.MOD_ID,"juf"),
            () -> new ItemStack(Items.ACACIA_LOG));
}
