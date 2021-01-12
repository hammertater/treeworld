package ht.treeworld.common.init;

import ht.treeworld.common.world.TreeWorldBiomeProvider;
import ht.treeworld.common.world.TreeWorldType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModWorlds {

    public static TreeWorldType treeWorldType = new TreeWorldType();

    public static void setup() {
        // Biomes O Plenty uses the minecraft namespace, so let's do that too
        Logger gameDataLogger = (Logger) LogManager.getLogger(GameData.class);
        Level oldLevel = gameDataLogger.getLevel();
        gameDataLogger.setLevel(Level.OFF);
        treeWorldType.setRegistryName(new ResourceLocation("treeworld"));
        ForgeRegistries.WORLD_TYPES.register(treeWorldType);
        gameDataLogger.setLevel(oldLevel);

        Registry.register(Registry.BIOME_PROVIDER_CODEC, "treeworld_overworld", TreeWorldBiomeProvider.CODEC);
    }

}
