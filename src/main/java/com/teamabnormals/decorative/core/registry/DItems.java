package com.teamabnormals.decorative.core.registry;

import com.teamabnormals.blueprint.core.util.registry.ItemSubRegistryHelper;
import com.teamabnormals.decorative.core.Decorative;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Decorative.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DItems {
	public static final ItemSubRegistryHelper HELPER = Decorative.REGISTRY_HELPER.getItemSubHelper();

}