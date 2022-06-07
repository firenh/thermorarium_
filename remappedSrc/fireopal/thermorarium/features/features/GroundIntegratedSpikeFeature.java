package fireopal.thermorarium.features.features;

import com.mojang.serialization.Codec;

import fireopal.thermorarium.features.features.config.SpikeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class GroundIntegratedSpikeFeature extends SpikeFeature {
    public GroundIntegratedSpikeFeature(Codec<SpikeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean canGenerate(FeatureContext<SpikeFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        BlockState setState = context.getConfig().setState();
        
        return context.getWorld().getBlockState(origin.down()).isOf(setState.getBlock());
    }
    
}
