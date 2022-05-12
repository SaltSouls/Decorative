package com.teamabnormals.decorative.client.renderers.entities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Bus.MOD, value = Dist.CLIENT)
public class SeatEntityRenderer {

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
    }


}