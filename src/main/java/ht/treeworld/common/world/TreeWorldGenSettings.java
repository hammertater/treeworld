package ht.treeworld.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import ht.treeworld.common.init.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;

import java.util.function.Supplier;

public class TreeWorldGenSettings {

    private final DimensionStructuresSettings structuresSettings;
    private final Registry<Biome> biomeRegistry;
    private Supplier<Biome> biomeToUse;

    public static final Codec<TreeWorldGenSettings> CODEC = RecordCodecBuilder.<TreeWorldGenSettings>create(
            codecBuilder -> codecBuilder.group(
                RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(settings -> settings.biomeRegistry),
                DimensionStructuresSettings.field_236190_a_.fieldOf("structures").forGetter(settings -> settings.structuresSettings),
                Codec.BOOL.fieldOf("lakes").orElse(false).forGetter(settings -> false),
                Codec.BOOL.fieldOf("features").orElse(false).forGetter(settings -> false),
                Biome.BIOME_CODEC.fieldOf("biome").orElse(null)
                        .forGetter(settings -> settings.biomeToUse)
            ).apply(codecBuilder, TreeWorldGenSettings::new)
    ).stable();

    public TreeWorldGenSettings(Registry<Biome> biomeRegistry, DimensionStructuresSettings structuresSettings, boolean genLakes, boolean genFeatures, Supplier<Biome> biomeSupplier) {
        this(structuresSettings, biomeRegistry);
        this.biomeToUse = biomeSupplier;
    }

//    public TreeWorldGenSettings(BiomeProvider p_i241975_1_, long p_i241975_2_, Supplier<DimensionSettings> p_i241975_4_) {
//    }

    public TreeWorldGenSettings(DimensionStructuresSettings structuresSettings, Registry<Biome> biomeRegistry) {
        this.biomeRegistry = biomeRegistry;
        this.structuresSettings = structuresSettings;
        this.biomeToUse = () -> biomeRegistry.getOrThrow(ModBiomes.MOSSY_GLADE);
    }

    public Registry<Biome> getBiomeRegistry() {
        return biomeRegistry;
    }

    public Biome getBiome() {
        return biomeToUse.get();
    }

    public DimensionStructuresSettings getStructuresSettings() {
        return structuresSettings;
    }

}
