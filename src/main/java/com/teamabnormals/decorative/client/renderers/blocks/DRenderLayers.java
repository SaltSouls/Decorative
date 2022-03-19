package com.teamabnormals.decorative.client.renderers.blocks;

import com.teamabnormals.decorative.core.registry.DBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.level.block.Block;

public class DRenderLayers {

    public static void setupRenderLayers() {
        setRenderLayer(DBlocks.CHAIR.get(), RenderType.cutout());
    }

    private static void setRenderLayer(Block block, RenderType type) {
        ItemBlockRenderTypes.setRenderLayer(block, type::equals);
    }
}
