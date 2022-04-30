package net.jothb.jufurniture.juf.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.jothb.jufurniture.juf.Juf;
import net.jothb.jufurniture.juf.block.blocks.TripleDoor;
import net.jothb.jufurniture.juf.item.JufGroup;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModBlocks {

    public static final Block TEST_DOOR = registerBlock("test_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);


    private static Block registerBlock(String name, Block block, ItemGroup group){
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(Juf.MOD_ID, name), block);
    }
    private static Block registerBlock(String name, Block block, ItemGroup group, String tooltipKey){
        registerBlockItem(name, block, group, tooltipKey);
        return Registry.register(Registry.BLOCK, new Identifier(Juf.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup group, String tooltipKey){
        return Registry.register(Registry.ITEM, new Identifier(Juf.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)){
            @Override
            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                super.appendTooltip(stack, world, tooltip, context);
            }
        });
    }
    private static Item registerBlockItem(String name, Block block, ItemGroup group){
        return Registry.register(Registry.ITEM, new Identifier(Juf.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }

    public static void registerModBlocks(){
        Juf.LOGGER.info("ModBlocks from "+ Juf.MOD_ID);
    }
}
