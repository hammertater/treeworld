package ht.treeworld;

import ht.treeworld.client.Client;
import ht.treeworld.common.Common;
import ht.treeworld.common.config.ConfigHandler;
import ht.treeworld.common.init.ModBiomes;
import ht.treeworld.common.init.ModBlockColors;
import ht.treeworld.common.init.ModBlocks;
import ht.treeworld.common.init.ModFeatures;
import ht.treeworld.common.init.ModItems;
import ht.treeworld.common.init.ModWorlds;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("treeworld")
public class TreeWorld {
    public static final String MOD_ID = "treeworld";
    public static final String MOD_NAME = "HT's TreeWorld";

    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public TreeWorld() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener((ModConfig.Loading e) -> ConfigHandler.onReload());
        modBus.addListener((ModConfig.Reloading e) -> ConfigHandler.onReload());

        ModBlocks.BLOCKS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModBiomes.BIOMES.register(modBus);
        ModFeatures.FEATURES.register(modBus);

        modBus.addListener(Client::onClientSetup);
        modBus.addListener(Common::onCommonSetup);
//        modBus.addListener(Server::onServerSetup);

        ModWorlds.setup();
    }

}
