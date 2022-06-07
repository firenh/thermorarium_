package fireopal.thermorarium.features.features.config;

import java.util.List;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;

public record FeatureCollectionFeatureConfig(List<RegistryEntry<PlacedFeature>> features) implements FeatureConfig {
    public static Codec<FeatureCollectionFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        PlacedFeature.REGISTRY_CODEC.listOf().fieldOf("default").forGetter(config -> config.features)
    ).apply(instance, FeatureCollectionFeatureConfig::new));
}
