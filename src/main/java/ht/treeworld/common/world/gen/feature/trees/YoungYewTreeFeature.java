package ht.treeworld.common.world.gen.feature.trees;

import ht.treeworld.common.world.gen.feature.BaseTreeFeature;
import ht.treeworld.common.world.gen.feature.util.UniformBlockTrace;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;

import java.util.Random;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class YoungYewTreeFeature extends BaseTreeFeature {

    private static final int MIN_HEIGHT = 4;
    private static final int MAX_HEIGHT = 8;

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
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos origin, BaseTreeFeatureConfig config) {
        int height = MIN_HEIGHT + rand.nextInt(MAX_HEIGHT - MIN_HEIGHT);

        // Leaves
        int leavesBeginHeight = 2 + rand.nextInt(1);
        int leavesEndHeight = height + 1 + rand.nextInt(2);
        int numLeavesLayers = leavesEndHeight - leavesBeginHeight;
        double maxRadius = Math.sqrt(numLeavesLayers);
        double maxRadiusWithWiggleRoom = maxRadius + 1;

        worldRectStream(
                origin.add(-maxRadiusWithWiggleRoom, leavesBeginHeight, -maxRadiusWithWiggleRoom),
                origin.add(maxRadiusWithWiggleRoom, leavesEndHeight, maxRadiusWithWiggleRoom)
        ).filter(
                pos -> posIsWithinEgg(
                        makeD3From3I(pos).add(pickRandomVectorInUnitCircle().scale(0.25)),
                        makeD3From3I(origin).add(-maxRadius, leavesBeginHeight, -maxRadius),
                        makeD3From3I(origin).add(maxRadius, leavesEndHeight, maxRadius),
                        0.1
                )
        ).forEach(
                pos2 -> world.setBlockState(pos2, leaves.getDefaultState().with(LeavesBlock.DISTANCE, 1), 19)
        );

        // Logs
        UniformBlockTrace logsTrace = new UniformBlockTrace(origin, log.getDefaultState());
        logsTrace.move(Direction.UP, height);
        logsTrace.draw(world);

        return true;
    }

    /**
     * As described by https://web.archive.org/web/20201202152048/https://karthikkaranth.me/blog/generating-random-points-in-a-sphere/
     */
    private static Vector3d pickRandomVectorInUnitCircle() {
        double theta = Math.random() * 2.0 * Math.PI;
        double phi = Math.acos(2.0 * Math.random() - 1.0);
        double r = Math.cbrt(Math.random());
        double sinPhi = Math.sin(phi);
        return new Vector3d(
                r * sinPhi * Math.cos(theta),
                r * sinPhi * Math.sin(theta),
                r * Math.cos(phi)
        );
    }

    private static Vector3d makeD3From3I(Vector3i in) {
        return new Vector3d(in.getX(), in.getY(), in.getZ());
    }

    /**
     * As described by https://web.archive.org/web/20200708020735/http://www.mathematische-basteleien.de/eggcurves.htm
     */
    private static boolean posIsWithinEgg(
            Vector3d pos,
            Vector3d from,
            Vector3d to,
            double egginess
    ) {
        Vector3d normPos = new Vector3d(
                normalizeInRange(pos.getX() + .5, from.getX(), to.getX() + 1),
                normalizeInRange(pos.getY() + .5, from.getY(), to.getY() + 1),
                normalizeInRange(pos.getZ() + .5, from.getZ(), to.getZ() + 1)
        ).scale(2.0F).subtract(1, 1, 1);

        double u = len2d(normPos.getX(), normPos.getZ());
        double v = normPos.getY();

        return sqr(v) + sqr(u) * (1 + egginess * v) / (1 - egginess * v) <= 1;
    }

    private static double len2d(double x, double y) {
        return Math.sqrt(sqr(x) + sqr(y));
    }
    private static double sqr(double value) {
        return value * value;
    }

    private static double normalizeInRange(double x, double x2, double x3) {
        return (x - x2) / (x3 - x2);
    }

    public Stream<BlockPos> worldRectStream(Vector3i from, Vector3i to) {
        return worldRectStream(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
    }

    public Stream<BlockPos> worldRectStream(int fromX, int fromY, int fromZ, int toX, int toY, int toZ) {
        int xLen = toX - fromX + 2;
        int yLen = toY - fromY + 2;
        int zLen = toZ - fromZ + 2;
        int xyLen = xLen * yLen;
        int total = xyLen * zLen;
        return IntStream.range(0, total).mapToObj(
                i -> new BlockPos(
                        fromX + (i % xLen),
                        fromY + (Math.floorDiv(i, xLen) % yLen),
                        fromZ + (Math.floorDiv(i, xyLen))
                )
        );
    }
}
