package ht.treeworld.common.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.function.BiPredicate;

public abstract class BaseTreeFeature extends Feature<BaseTreeFeatureConfig> {

    protected final Block log;
    protected final Block leaves;
    protected final BiPredicate<World, BlockPos> placeCondition;

    protected BaseTreeFeature(
            Block log,
            Block leaves,
            BiPredicate<World, BlockPos> placeCondition
    ) {
        super(BaseTreeFeatureConfig.CODEC.stable());

        this.log = log;
        this.leaves = leaves;
        this.placeCondition = placeCondition;
    }

    protected static abstract class BaseBuilder<T extends BaseBuilder, F extends BaseTreeFeature> {
        protected BiPredicate<World, BlockPos> placeCondition = (world, pos) -> true;
        protected Block logBlock = null;
        protected Block leavesBlock = null;

        public T withLog(Block log) {
            this.logBlock = log; return (T)this;
        }

        public T withLeaves(Block leaves) {
            this.leavesBlock = leaves; return (T)this;
        }

        public abstract F build();
    }
}
