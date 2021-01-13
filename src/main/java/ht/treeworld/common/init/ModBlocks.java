package ht.treeworld.common.init;

import ht.treeworld.TreeWorld;
import ht.treeworld.common.biome.tree.YoungYewTree;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TreeWorld.MOD_ID);

    public static final RegistryObject<Block> LOAM = BLOCKS.register("loam",
            () -> new Block(
                    AbstractBlock.Properties
                            .create(Material.EARTH, MaterialColor.BROWN)
                            .hardnessAndResistance(0.5F)
                            .sound(SoundType.FUNGUS)
            )
    );

    public static final RegistryObject<Block> MOSSY_LOAM = BLOCKS.register("mossy_loam",
            () -> new Block(
                    AbstractBlock.Properties
                            .create(Material.EARTH, MaterialColor.BROWN)
                            .hardnessAndResistance(0.5F)
                            .sound(SoundType.FUNGUS)
            )
    );

    public static final RegistryObject<Block> STRIPPED_YEW_LOG = BLOCKS.register("stripped_yew_log",
            () -> createLogBlock(MaterialColor.WOOD, MaterialColor.WOOD) // TODO: use yew material colors
    );

    public static final RegistryObject<Block> STRIPPED_YEW_WOOD = BLOCKS.register("stripped_yew_wood",
            () -> new RotatedPillarBlock(
                    AbstractBlock.Properties
                            .create(Material.WOOD, MaterialColor.WOOD)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> YEW_LEAVES = BLOCKS.register("yew_leaves",
            () -> createLeavesBlock()
    );

    public static final RegistryObject<Block> YEW_LOG = BLOCKS.register("yew_log",
            () -> createLogBlock(MaterialColor.WOOD, MaterialColor.OBSIDIAN)
    );

    public static final RegistryObject<Block> YEW_PLANKS = BLOCKS.register("yew_planks",
            () -> new Block(
                    AbstractBlock.Properties
                            .create(Material.WOOD, MaterialColor.WOOD)
                            .hardnessAndResistance(2.0F, 3.0F)
                            .sound(SoundType.WOOD)
            )
    );

    public static final RegistryObject<Block> YEW_SAPLING = BLOCKS.register("yew_sapling",
            () -> new SaplingBlock(
                    new YoungYewTree(),
                    AbstractBlock.Properties
                            .create(Material.PLANTS)
                            .doesNotBlockMovement()
                            .tickRandomly()
                            .zeroHardnessAndResistance()
                            .sound(SoundType.PLANT)
            )
    );

    public static final RegistryObject<Block> YEW_WOOD = BLOCKS.register("yew_wood",
            () -> new RotatedPillarBlock(
                    AbstractBlock.Properties
                            .create(Material.WOOD, MaterialColor.WOOD)
                            .hardnessAndResistance(2.0F)
                            .sound(SoundType.WOOD)
            )
    );

    private static RotatedPillarBlock createLogBlock(MaterialColor topColor, MaterialColor barkColor) {
        return new RotatedPillarBlock(
                AbstractBlock.Properties.create(
                        Material.WOOD,
                        (state) -> state.get(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topColor : barkColor
                )
                        .hardnessAndResistance(2.0F)
                        .sound(SoundType.WOOD)
        );
    }

    private static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(
                AbstractBlock.Properties.create(Material.LEAVES)
                        .hardnessAndResistance(0.2F)
                        .tickRandomly()
                        .sound(SoundType.PLANT)
                        .notSolid()
                        .setAllowsSpawn(ModBlocks::alwaysFalse)
                        .setSuffocates(ModBlocks::alwaysFalse)
                        .setBlocksVision(ModBlocks::alwaysFalse)
        );
    }

    private static boolean alwaysFalse(BlockState blockState, IBlockReader iBlockReader, BlockPos blockPos) {
        return false;
    }

    private static Boolean alwaysFalse(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
        return false;
    }
}
