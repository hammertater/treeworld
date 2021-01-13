package ht.treeworld.common.world.gen.feature.util;

import com.google.common.collect.Maps;
import javafx.geometry.BoundingBox;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;

import java.util.Map;
import java.util.stream.IntStream;

public class BlockTrace {
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
        currentPos.setPos(pos);
        trace.put(this.currentPos.toImmutable(), state);
        boundingBox.expandTo(new MutableBoundingBox(pos, pos));
    }

    public void  move(Direction facing, int n, BlockState state) {
        for (int i = 0; i < n; ++i) {
            currentPos.move(facing);
            trace.put(currentPos.toImmutable(), state);
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
