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

    public static final Block ACACIA_DOOR = registerBlock("acacia_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block BIRCH_DOOR = registerBlock("birch_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block CRIMSON_DOOR = registerBlock("crimson_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block DARK_OAK_DOOR = registerBlock("dark_oak_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block IRON_DOOR = registerBlock("iron_door",
            new TripleDoor(FabricBlockSettings.of(Material.METAL).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block JUNGLE_DOOR = registerBlock("jungle_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block SPRUCE_DOOR = registerBlock("spruce_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block WARPED_DOOR = registerBlock("warped_door",
            new TripleDoor(FabricBlockSettings.of(Material.WOOD).strength(6f).requiresTool().nonOpaque()), JufGroup.JUF);

    public static final Block WOODEN_DOOR = registerBlock("wooden_door",
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
