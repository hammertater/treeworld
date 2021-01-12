package ht.treeworld.common.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import ht.treeworld.common.init.ModBiomes;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.layer.Layer;
import net.minecraftforge.fml.RegistryObject;

import java.util.stream.Collectors;

public class TreeWorldBiomeProvider extends BiomeProvider {

    private final Layer genBiomes;
    private final Registry<Biome> lookupRegistry;
    private final long seed;

    public static final Codec<TreeWorldBiomeProvider> CODEC = RecordCodecBuilder.create(
            builder -> builder.group(
                    Codec.LONG.fieldOf("seed").stable().forGetter(biomeProvider -> biomeProvider.seed),
                    RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(biomeProvider -> biomeProvider.lookupRegistry)
            ).apply(builder, builder.stable(TreeWorldBiomeProvider::new))
    );

    protected TreeWorldBiomeProvider(Long seed, Registry<Biome> lookupRegistry) {
        super(ModBiomes.BIOMES.getEntries().stream().map(RegistryObject::get).collect(Collectors.toList()));
        this.lookupRegistry = lookupRegistry;
        this.seed = seed;
        this.genBiomes = WorldUtil.getLayer(seed);
    }

    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return CODEC;
    }

    @Override
    public BiomeProvider getBiomeProvider(long seed) {
        return this; // TODO
    }

    @Override
    public Biome getNoiseBiome(int x, int y, int z) {
        return getBiomes().get(0); // TODO
    }

}
