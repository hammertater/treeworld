package ht.treeworld.common.init;

import ht.treeworld.TreeWorld;
import ht.treeworld.common.biome.tree.YoungYewTree;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
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
}
