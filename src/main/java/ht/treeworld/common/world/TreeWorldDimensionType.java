package ht.treeworld.common.world;

import com.mojang.serialization.Lifecycle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Dimension;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.DimensionSettings;

import java.util.OptionalLong;

public class TreeWorldDimensionType extends DimensionType {

    protected TreeWorldDimensionType(OptionalLong fixedTime, boolean hasSkyLight, boolean hasCeiling, boolean ultrawarm, boolean natural, double coordinateScale, boolean piglinSafe, boolean bedWorks, boolean respawnAnchorWorks, boolean hasRaids, int logicalHeight, ResourceLocation infiniburn, ResourceLocation effects, float ambientLight) {
        super(fixedTime, hasSkyLight, hasCeiling, ultrawarm, natural, coordinateScale, piglinSafe, bedWorks, respawnAnchorWorks, hasRaids, logicalHeight, infiniburn, effects, ambientLight);
    }

    public static SimpleRegistry<Dimension> getDimensions(Registry<Biome> biomeRegistry, Registry<DimensionSettings> dimensionSettingsRegistry, long seed) {
        SimpleRegistry<Dimension> registry = new SimpleRegistry<>(Registry.DIMENSION_KEY, Lifecycle.experimental());
//        registry.register(Dimension.NETHER, new Dimension(() -> DEFAULT_NETHER, netherGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
//        registry.register(Dimension.END, new Dimension(() -> DEFAULT_END, endGenerator(biomeRegistry, dimensionSettingsRegistry, seed)), Lifecycle.stable());
        return registry;
    }
}
