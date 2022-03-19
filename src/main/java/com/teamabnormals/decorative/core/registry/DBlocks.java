package com.teamabnormals.decorative.core.registry;

import com.teamabnormals.blueprint.core.util.registry.BlockSubRegistryHelper;
import com.teamabnormals.decorative.common.blocks.*;
import com.teamabnormals.decorative.core.Decorative;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Decorative.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DBlocks {

//	private static BedBlock cushion(DyeColor color) {
//		return new BedBlock(color, BlockBehaviour.Properties.of(Material.WOOL, (state) -> {
//			return state.getValue(BedBlock.PART) == BedPart.FOOT ? color.getMaterialColor() : MaterialColor.WOOL;
//		}).sound(SoundType.WOOL).strength(0.2F).noOcclusion());
//	}

	public static final BlockSubRegistryHelper HELPER = Decorative.REGISTRY_HELPER.getBlockSubHelper();

	// Base Blocks
	public static final RegistryObject<CushionBlock> WHITE_CUSHION = HELPER.createBlock("white_cushion", () -> new CushionBlock(Properties.copy(Blocks.WHITE_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> ORANGE_CUSHION = HELPER.createBlock("orange_cushion", () -> new CushionBlock(Properties.copy(Blocks.ORANGE_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> MAGENTA_CUSHION = HELPER.createBlock("magenta_cushion", () -> new CushionBlock(Properties.copy(Blocks.MAGENTA_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> LIGHT_BLUE_CUSHION = HELPER.createBlock("light_blue_cushion", () -> new CushionBlock(Properties.copy(Blocks.LIGHT_BLUE_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> YELLOW_CUSHION = HELPER.createBlock("yellow_cushion", () -> new CushionBlock(Properties.copy(Blocks.YELLOW_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> LIME_CUSHION = HELPER.createBlock("lime_cushion", () -> new CushionBlock(Properties.copy(Blocks.LIME_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> PINK_CUSHION = HELPER.createBlock("pink_cushion", () -> new CushionBlock(Properties.copy(Blocks.PINK_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> GRAY_CUSHION = HELPER.createBlock("gray_cushion", () -> new CushionBlock(Properties.copy(Blocks.GRAY_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> LIGHT_GRAY_CUSHION = HELPER.createBlock("light_gray_cushion", () -> new CushionBlock(Properties.copy(Blocks.LIGHT_GRAY_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> CYAN_CUSHION = HELPER.createBlock("cyan_cushion", () -> new CushionBlock(Properties.copy(Blocks.CYAN_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> PURPLE_CUSHION = HELPER.createBlock("purple_cushion", () -> new CushionBlock(Properties.copy(Blocks.PURPLE_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> BLUE_CUSHION = HELPER.createBlock("blue_cushion", () -> new CushionBlock(Properties.copy(Blocks.BLUE_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> BROWN_CUSHION = HELPER.createBlock("brown_cushion", () -> new CushionBlock(Properties.copy(Blocks.BROWN_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> GREEN_CUSHION = HELPER.createBlock("green_cushion", () -> new CushionBlock(Properties.copy(Blocks.GREEN_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> RED_CUSHION = HELPER.createBlock("red_cushion", () -> new CushionBlock(Properties.copy(Blocks.RED_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<StoolBlock> STOOL = HELPER.createBlock("stool", () -> new StoolBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<ChairBlock> CHAIR = HELPER.createBlock("chair", () -> new ChairBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CushionBlock> BLACK_CUSHION = HELPER.createBlock("black_cushion", () -> new CushionBlock(Properties.copy(Blocks.BLACK_WOOL)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> WHITE_COUCH = HELPER.createBlock("white_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> ORANGE_COUCH = HELPER.createBlock("orange_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> MAGENTA_COUCH = HELPER.createBlock("magenta_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> LIGHT_BLUE_COUCH = HELPER.createBlock("light_blue_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> YELLOW_COUCH = HELPER.createBlock("yellow_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> LIME_COUCH = HELPER.createBlock("lime_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> PINK_COUCH = HELPER.createBlock("pink_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> GRAY_COUCH = HELPER.createBlock("gray_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> LIGHT_GRAY_COUCH = HELPER.createBlock("light_gray_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> CYAN_COUCH = HELPER.createBlock("cyan_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> PURPLE_COUCH = HELPER.createBlock("purple_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> BLUE_COUCH = HELPER.createBlock("blue_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> BROWN_COUCH = HELPER.createBlock("brown_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> GREEN_COUCH = HELPER.createBlock("green_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> RED_COUCH = HELPER.createBlock("red_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);
	public static final RegistryObject<CouchBlock> BLACK_COUCH = HELPER.createBlock("black_couch", () -> new CouchBlock(Properties.copy(Blocks.OAK_PLANKS)), CreativeModeTab.TAB_DECORATIONS);


	// Stool Variants
	public static final RegistryObject<CushionedStoolBlock> WHITE_CUSHIONED_STOOL = HELPER.createBlockNoItem("white_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> ORANGE_CUSHIONED_STOOL = HELPER.createBlockNoItem("orange_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> MAGENTA_CUSHIONED_STOOL = HELPER.createBlockNoItem("magenta_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> LIGHT_BLUE_CUSHIONED_STOOL = HELPER.createBlockNoItem("light_blue_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> YELLOW_CUSHIONED_STOOL = HELPER.createBlockNoItem("yellow_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> LIME_CUSHIONED_STOOL = HELPER.createBlockNoItem("lime_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> PINK_CUSHIONED_STOOL = HELPER.createBlockNoItem("pink_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> GRAY_CUSHIONED_STOOL = HELPER.createBlockNoItem("gray_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> LIGHT_GRAY_CUSHIONED_STOOL = HELPER.createBlockNoItem("light_gray_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> CYAN_CUSHIONED_STOOL = HELPER.createBlockNoItem("cyan_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> PURPLE_CUSHIONED_STOOL = HELPER.createBlockNoItem("purple_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> BLUE_CUSHIONED_STOOL = HELPER.createBlockNoItem("blue_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> BROWN_CUSHIONED_STOOL = HELPER.createBlockNoItem("brown_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> GREEN_CUSHIONED_STOOL = HELPER.createBlockNoItem("green_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> RED_CUSHIONED_STOOL = HELPER.createBlockNoItem("red_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));
	public static final RegistryObject<CushionedStoolBlock> BLACK_CUSHIONED_STOOL = HELPER.createBlockNoItem("black_cushioned_stool", () -> new CushionedStoolBlock(Properties.copy(STOOL.get())));

	// Chair Variants
	public static final RegistryObject<CushionedChairBlock> WHITE_CUSHIONED_CHAIR = HELPER.createBlockNoItem("white_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> ORANGE_CUSHIONED_CHAIR = HELPER.createBlockNoItem("orange_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> MAGENTA_CUSHIONED_CHAIR = HELPER.createBlockNoItem("magenta_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> LIGHT_BLUE_CUSHIONED_CHAIR = HELPER.createBlockNoItem("light_blue_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> YELLOW_CUSHIONED_CHAIR = HELPER.createBlockNoItem("yellow_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> LIME_CUSHIONED_CHAIR = HELPER.createBlockNoItem("lime_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> PINK_CUSHIONED_CHAIR = HELPER.createBlockNoItem("pink_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> GRAY_CUSHIONED_CHAIR = HELPER.createBlockNoItem("gray_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> LIGHT_GRAY_CUSHIONED_CHAIR = HELPER.createBlockNoItem("light_gray_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> CYAN_CUSHIONED_CHAIR = HELPER.createBlockNoItem("cyan_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> PURPLE_CUSHIONED_CHAIR = HELPER.createBlockNoItem("purple_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> BLUE_CUSHIONED_CHAIR = HELPER.createBlockNoItem("blue_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> BROWN_CUSHIONED_CHAIR = HELPER.createBlockNoItem("brown_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> GREEN_CUSHIONED_CHAIR = HELPER.createBlockNoItem("green_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> RED_CUSHIONED_CHAIR = HELPER.createBlockNoItem("red_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
	public static final RegistryObject<CushionedChairBlock> BLACK_CUSHIONED_CHAIR = HELPER.createBlockNoItem("black_cushioned_chair", () -> new CushionedChairBlock(Properties.copy(CHAIR.get())));
}
