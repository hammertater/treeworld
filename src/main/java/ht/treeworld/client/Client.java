package ht.treeworld.client;

import ht.treeworld.common.init.ModBlockColors;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Client {

    public static void onClientSetup(FMLClientSetupEvent event) {
        ModBlockColors.init();
    }

}
