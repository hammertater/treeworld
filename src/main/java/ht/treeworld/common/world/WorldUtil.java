package ht.treeworld.common.world;

import net.minecraft.world.gen.LazyAreaLayerContext;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.area.LazyArea;
import net.minecraft.world.gen.layer.IslandLayer;
import net.minecraft.world.gen.layer.Layer;

import java.util.function.LongFunction;

public class WorldUtil {

    public static Layer getLayer(long seed) {
        return new Layer(getLayerBuilder(
                seed,
                seedModifier -> new LazyAreaLayerContext(25, seed, seedModifier)
        ));
    }

    private static IAreaFactory<LazyArea> getLayerBuilder(long seed, LongFunction<LazyAreaLayerContext> noise) {
        IAreaFactory<LazyArea> factory = IslandLayer.INSTANCE.apply(noise.apply(1L));
        return factory;
    }

}
