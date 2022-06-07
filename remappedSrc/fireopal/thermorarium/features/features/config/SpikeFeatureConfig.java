package fireopal.thermorarium.features.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record SpikeFeatureConfig(IntProvider height, BlockState setState, boolean upsideDown) implements FeatureConfig {

    public static final Codec<SpikeFeatureConfig> CODEC = RecordCodecBuilder
        .create(instance -> instance.group(
            IntProvider.VALUE_CODEC.fieldOf("height").orElse(UniformIntProvider.create(10, 20))
                .forGetter(config -> config.height),
            BlockState.CODEC.fieldOf("set_state").orElse(Blocks.BLACKSTONE.getDefaultState())
                .forGetter(config -> config.setState),
            Codec.BOOL.fieldOf("upside_down").orElse(false)
                .forGetter(config -> config.upsideDown)
        ).apply(instance, SpikeFeatureConfig::new));
}
