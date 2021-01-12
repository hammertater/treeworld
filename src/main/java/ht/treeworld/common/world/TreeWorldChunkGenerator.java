package ht.treeworld.common.world;

import com.mojang.serialization.Codec;
import ht.treeworld.common.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Blockreader;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.biome.provider.SingleBiomeProvider;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.WorldGenRegion;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Arrays;

public class TreeWorldChunkGenerator extends ChunkGenerator {

    public static final Codec<TreeWorldChunkGenerator> CODEC = TreeWorldGenSettings.CODEC.fieldOf("settings")
            .xmap(TreeWorldChunkGenerator::new, TreeWorldChunkGenerator::getSettings).codec();
    private final TreeWorldGenSettings settings;

    private static final BlockState[] layerBlockStates = {
            Blocks.BEDROCK.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            Blocks.STONE.getDefaultState(),
            ModBlocks.LOAM.get().getDefaultState(),
            ModBlocks.LOAM.get().getDefaultState(),
            ModBlocks.LOAM.get().getDefaultState(),
            ModBlocks.MOSSY_LOAM.get().getDefaultState()
    };

    public TreeWorldChunkGenerator(TreeWorldGenSettings settings) {
        super(new SingleBiomeProvider(settings.getBiome()), settings.getStructuresSettings());
        this.settings = settings;
    }

    @Override
    protected Codec<? extends ChunkGenerator> func_230347_a_() {
        return CODEC;
    }

    /**
     * Not sure what this does
     */
    @Override
    public ChunkGenerator func_230349_a_(long p_230349_1_) {
        return this;
    }

    /**
     * Generate the surface of the chunk
     */
    @Override
    public void generateSurface(WorldGenRegion worldGenRegion, IChunk chunk) {

    }

    /**
     * Generate the chunk shape and structures
     */
    @Override
    public void func_230352_b_(IWorld world, StructureManager structureManager, IChunk chunk) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        Heightmap heightmap = chunk.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
        Heightmap heightmap1 = chunk.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);

        for(int i = 0; i < layerBlockStates.length; ++i) {
            BlockState blockstate = layerBlockStates[i];
            if (blockstate != null) {
                for(int j = 0; j < 16; ++j) {
                    for(int k = 0; k < 16; ++k) {
                        chunk.setBlockState(blockpos$mutable.setPos(j, i, k), blockstate, false);
                        heightmap.update(j, i, k, blockstate);
                        heightmap1.update(j, i, k, blockstate);
                    }
                }
            }
        }
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmapType) {
        return 3;
    }

    /**
     * Not sure what this does
     */
    @Override
    public IBlockReader func_230348_a_(int p_230348_1_, int p_230348_2_) {
        return new Blockreader(
                Arrays.stream(layerBlockStates)
                        .map(blockState -> blockState == null ? Blocks.AIR.getDefaultState() : blockState)
                        .toArray(BlockState[]::new)
        );
    }

    public TreeWorldGenSettings getSettings() {
        return settings;
    }
}
