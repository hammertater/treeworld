package ht.treeworld.common.world.gen.feature.trees;

import com.google.common.collect.Maps;
import ht.treeworld.common.world.gen.feature.BaseTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

import java.util.Map;
import java.util.Random;
import java.util.function.BiPredicate;

public class YoungYewTreeFeature extends BaseTreeFeature {

    public static class Builder extends BaseBuilder<Builder, YoungYewTreeFeature> {
        @Override
        public YoungYewTreeFeature build() {
            return new YoungYewTreeFeature(this.logBlock, this.leavesBlock, this.placeCondition);
        }
    }

    protected YoungYewTreeFeature(Block log, Block leaves, BiPredicate<World, BlockPos> canPlaceOn) {
        super(log, leaves, canPlaceOn);
    }

    @Override
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, BaseTreeFeatureConfig config) {

        UniformBlockTrace trace = new UniformBlockTrace(pos, log.getDefaultState());
        trace.move(Direction.UP, 3);
        trace.draw(world);

        return true;
    }

    private static class BlockTrace {
        private final BlockPos.Mutable currentPos;
        private final Map<BlockPos, BlockState> trace;
        private final MutableBoundingBox boundingBox;

        public BlockTrace(Vector3i pos, BlockState firstBlockState) {
            trace = Maps.newHashMap();
            boundingBox = new MutableBoundingBox(pos, pos);
            currentPos = new BlockPos.Mutable();

            jump(pos, firstBlockState);
        }

        public void jump(Vector3i pos, BlockState state) {
            trace.put(this.currentPos.toImmutable(), state);
            currentPos.setPos(pos);
            boundingBox.expandTo(new MutableBoundingBox(pos, pos));
        }

        public void move(Direction facing, int n, BlockState state) {
            for (int i = 0; i < n; ++i) {
                trace.put(currentPos.toImmutable(), state);
                currentPos.move(facing);
            }
        }

        public BlockPos getPos() {
            return currentPos;
        }

        public Map<BlockPos, BlockState> getTrace() {
            return trace;
        }

        public void draw(ISeedReader world) {
            trace.forEach(
                    (pos, state) -> world.setBlockState(pos, state, 19)
            );
        }
    }

    private static class UniformBlockTrace extends BlockTrace {
        private final BlockState defaultBlockState;

        public UniformBlockTrace(Vector3i pos, BlockState defaultBlockState) {
            super(pos, defaultBlockState);
            this.defaultBlockState = defaultBlockState;
        }

        public void jump(Vector3i pos) {
            jump(pos, defaultBlockState);
        }

        public void move(Direction facing, int n) {
            move(facing, n, defaultBlockState);
        }

    }
}
