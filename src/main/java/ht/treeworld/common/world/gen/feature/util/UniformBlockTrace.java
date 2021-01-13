package ht.treeworld.common.world.gen.feature.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3i;

public class UniformBlockTrace extends BlockTrace {
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
