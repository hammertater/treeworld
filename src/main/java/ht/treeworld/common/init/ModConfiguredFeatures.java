package ht.treeworld.common.init;

import ht.treeworld.TreeWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;

public class ModConfiguredFeatures {

    // Trees
    public static final ConfiguredFeature<?, ?> YOUNG_YEW_TREE = register("young_yew_tree", ModFeatures.YOUNG_YEW_TREE.get().withConfiguration(Features.OAK.config));

    // Biome Tree Listings
    public static final ConfiguredFeature<?, ?> MOSSY_GLADE_TREES = register(
            "mossy_glade_trees",
            YOUNG_YEW_TREE
                    .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                    .withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(2, 0.1F, 1)))
    );

    private static <T extends IFeatureConfig> ConfiguredFeature<T, ?> register(String key, ConfiguredFeature<T, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(TreeWorld.MOD_ID, key), feature);
    }
}
