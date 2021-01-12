package ht.treeworld.common.biome.tree;

import ht.treeworld.common.init.ModFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class YoungYewTree extends Tree {

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return null;
    }

    @Override
    public boolean attemptGrowTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        Feature<BaseTreeFeatureConfig> feature = ModFeatures.YOUNG_YEW_TREE.get();
        if (feature == null) {
            return false;
        }
        else {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
            if (feature.generate(world, chunkGenerator, rand, pos, Features.OAK.config)) {
                return true;
            }
            else {
                world.setBlockState(pos, state, 4);
                return false;
            }
        }
    }

}
