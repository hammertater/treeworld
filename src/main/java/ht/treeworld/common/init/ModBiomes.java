package ht.treeworld.common.init;

import ht.treeworld.TreeWorld;
import ht.treeworld.common.biome.MossyGladeBiome;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, TreeWorld.MOD_ID);

    // TODO
    public static final RegistryKey<Biome> MOSSY_GLADE = register("mossy_glade", MossyGladeBiome.build());

    public static RegistryKey<Biome> register(String name, Biome biome) {
        BIOMES.register(name, () -> biome);
        return RegistryKey.getOrCreateKey(Registry.BIOME_KEY, new ResourceLocation(TreeWorld.MOD_ID, name));
    }

}
