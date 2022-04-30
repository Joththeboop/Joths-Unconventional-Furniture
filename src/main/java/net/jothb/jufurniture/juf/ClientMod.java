package net.jothb.jufurniture.juf;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.jothb.jufurniture.juf.block.ModBlocks;
import net.minecraft.client.render.RenderLayer;

public class ClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TEST_DOOR, RenderLayer.getCutout());
    }
}