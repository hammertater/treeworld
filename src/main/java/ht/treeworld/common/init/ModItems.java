package ht.treeworld.common.init;

import ht.treeworld.TreeWorld;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TreeWorld.MOD_ID);

    public static final RegistryObject<Item> LOAM = ITEMS.register("loam",
            () -> new BlockItem(
                    ModBlocks.LOAM.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> MOSSY_LOAM = ITEMS.register("mossy_loam",
            () -> new BlockItem(
                    ModBlocks.MOSSY_LOAM.get(),
                    new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)
            )
    );

    public static final RegistryObject<Item> YEW_SAPLING = ITEMS.register("yew_sapling",
            () -> new BlockItem(
                    ModBlocks.YEW_SAPLING.get(),
                    new Item.Properties().group(ItemGroup.MISC)
            )
    );

}
