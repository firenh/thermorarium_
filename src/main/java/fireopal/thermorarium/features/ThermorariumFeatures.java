package fireopal.thermorarium.features;

import fireopal.thermorarium.Thermorarium;
import fireopal.thermorarium.features.features.CaveSurfaceFeature;
import fireopal.thermorarium.features.features.FeatureCollectionFeature;
import fireopal.thermorarium.features.features.GroundIntegratedSpikeFeature;
import fireopal.thermorarium.features.features.NetherrackBaseFeature;
import fireopal.thermorarium.features.features.SpikeFeature;
import fireopal.thermorarium.features.features.config.CaveSurfaceFeatureConfig;
import fireopal.thermorarium.features.features.config.FeatureCollectionFeatureConfig;
import fireopal.thermorarium.features.features.config.NetherrackBaseFeatureConfig;
import fireopal.thermorarium.features.features.config.SpikeFeatureConfig;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ThermorariumFeatures {
    private static <C extends FeatureConfig, F extends Feature<C>> F register(String id, F feature) {
        return (F)Registry.register(Registry.FEATURE, Thermorarium.id(id), feature);
    }

    public static void init() {
        new ThermorariumFeatures();
    }

    public final static Feature<CaveSurfaceFeatureConfig> CAVE_SURFACE_FEATURE = register("cave_surface_feature", new CaveSurfaceFeature(CaveSurfaceFeatureConfig.CODEC));
    public final static Feature<SpikeFeatureConfig> SPIKE_FEATURE = register("spike_feature", new SpikeFeature(SpikeFeatureConfig.CODEC));
    public final static Feature<SpikeFeatureConfig> GROUND_INTEGRATED_SPIKE_FEATURE = register("ground_integrated_spike_feature", new GroundIntegratedSpikeFeature(SpikeFeatureConfig.CODEC));
    public final static Feature<NetherrackBaseFeatureConfig> NETHERRACK_BASE_FEATURE = register("netherrack_base_feature", new NetherrackBaseFeature(NetherrackBaseFeatureConfig.CODEC));
    public final static Feature<FeatureCollectionFeatureConfig> FEATURE_COLLECTION_FEATURE = register("feature_collection_feature", new FeatureCollectionFeature(FeatureCollectionFeatureConfig.CODEC));
}
