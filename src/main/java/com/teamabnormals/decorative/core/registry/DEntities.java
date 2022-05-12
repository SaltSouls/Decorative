package com.teamabnormals.decorative.core.registry;

import com.teamabnormals.blueprint.core.util.registry.EntitySubRegistryHelper;
import com.teamabnormals.decorative.core.Decorative;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = Decorative.MOD_ID, bus = Bus.MOD)
public class DEntities {
    private static final EntitySubRegistryHelper HELPER = Decorative.REGISTRY_HELPER.getEntitySubHelper();

    //public static final RegistryObject<EntityType<SeatEntity>> SEAT_ENTITY = HELPER.createEntitynew, EntityClassification.MISC, 0.0001F, 0.0001F);

    public static void registerRendering() {

    }
}