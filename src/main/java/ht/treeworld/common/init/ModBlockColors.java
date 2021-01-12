package ht.treeworld.common.init;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GrassColors;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.biome.BiomeColors;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class ModBlockColors implements IBlockColor {

    public static final IBlockColor INSTANCE = new ModBlockColors();
    private static final int WHITE = ColorHelper.PackedColor.packColor(255, 255, 255, 255);

    @Override
    public int getColor(BlockState state, @Nullable IBlockDisplayReader reader, @Nullable BlockPos pos, int tintIndex) {
        return reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos) : GrassColors.get(0.5D, 1.0D);
    }

    public static void init() {
        Minecraft.getInstance().getBlockColors().register(
                INSTANCE,
                ModBlocks.MOSSY_LOAM.get()
        );

        Stream.of(
                ModBlocks.MOSSY_LOAM,
                ModBlocks.YEW_SAPLING
        ).forEach(
                blockRegistryObject -> RenderTypeLookup.setRenderLayer(
                        blockRegistryObject.get(),
                        RenderType.getCutoutMipped()
                )
        );
    }
}
