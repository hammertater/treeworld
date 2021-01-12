package ht.treeworld.common.init;

import ht.treeworld.TreeWorld;
import ht.treeworld.common.world.gen.feature.trees.YoungYewTreeFeature;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, TreeWorld.MOD_ID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> YOUNG_YEW_TREE = FEATURES.register(
            "young_yew_tree",
            () -> new YoungYewTreeFeature.Builder()
                    .withLog(ModBlocks.YEW_LOG.get())
                    .withLeaves(ModBlocks.YEW_LEAVES.get())
                    .build()
    );

}
