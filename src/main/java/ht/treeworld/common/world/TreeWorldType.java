package ht.treeworld.common.world;

import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraftforge.common.world.ForgeWorldType;

public class TreeWorldType extends ForgeWorldType {

    public TreeWorldType() {
        super(new ChunkFactory());
    }

    private static class ChunkFactory implements IChunkGeneratorFactory {

        @Override
        public ChunkGenerator createChunkGenerator(
                Registry<Biome> biomeRegistry,
                Registry<DimensionSettings> dimensionSettingsRegistry,
                long seed,
                String generatorSettings) {
            return new TreeWorldChunkGenerator(
                    new TreeWorldGenSettings(
                            dimensionSettingsRegistry.getOrThrow(DimensionSettings.field_242734_c).getStructures(),
                            biomeRegistry
                    )
            );
        }

        @Override
        public DimensionGeneratorSettings createSettings(DynamicRegistries dynamicRegistries, long seed, boolean generateStructures, boolean bonusChest, String generatorSettings) {
            Registry<Biome> biomeRegistry = dynamicRegistries.getRegistry(Registry.BIOME_KEY);
            Registry<DimensionSettings> dimensionSettingsRegistry = dynamicRegistries.getRegistry(Registry.NOISE_SETTINGS_KEY);
            Registry<DimensionType> dimensionTypeRegistry = dynamicRegistries.getRegistry(Registry.DIMENSION_TYPE_KEY);

            return new DimensionGeneratorSettings(
                    seed,
                    generateStructures,
                    bonusChest,
                    DimensionGeneratorSettings.func_242749_a(
                            dimensionTypeRegistry,
                            TreeWorldDimensionType.getDimensions(biomeRegistry, dimensionSettingsRegistry, seed),
                            createChunkGenerator(biomeRegistry, dimensionSettingsRegistry, seed, null)
                    )
            );
        }
    }
}
