package ht.treeworld.common.biome;

import net.minecraft.util.ColorHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;

public class MossyGladeBiome extends TreeWorldBiome {

    public static Biome build() {
        return new Biome.Builder()
                .precipitation(Biome.RainType.NONE)
                .downfall(0.0F)
                .category(Biome.Category.NONE)
                .temperature(0.66F)
                .withTemperatureModifier(Biome.TemperatureModifier.NONE)
                .scale(0.1F)
                .depth(0.1F)
                .withGenerationSettings(BiomeGenerationSettings.DEFAULT_SETTINGS)
                .withMobSpawnSettings(MobSpawnInfo.EMPTY)
                .setEffects(
                        new BiomeAmbience.Builder()
                                .withGrassColor(ColorHelper.PackedColor.packColor(255, 182, 198, 95))
                                .withFoliageColor(ColorHelper.PackedColor.packColor(255, 87, 229, 119))
                                .withSkyColor(ColorHelper.PackedColor.packColor(255, 140, 229, 255))
                                .setFogColor(ColorHelper.PackedColor.packColor(255, 140, 229, 255))
                                .setWaterColor(ColorHelper.PackedColor.packColor(255, 95, 215, 219))
                                .setWaterFogColor(ColorHelper.PackedColor.packColor(255, 0, 79, 38))
                                .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                                .build()
                )
                .build();
    }

}
