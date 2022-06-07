package fireopal.thermorarium.features.features;

import java.util.List;

import com.mojang.serialization.Codec;

import fireopal.thermorarium.features.features.config.FeatureCollectionFeatureConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FeatureCollectionFeature extends Feature<FeatureCollectionFeatureConfig> {
    public FeatureCollectionFeature(Codec<FeatureCollectionFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<FeatureCollectionFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        ChunkGenerator generator = context.getGenerator();
        List<RegistryEntry<PlacedFeature>> features = context.getConfig().features(); 

        boolean hasPlaced = false;

        for (RegistryEntry<PlacedFeature> r : features) {
            boolean temp = r.value().generate(world, generator, random, origin);
            hasPlaced = hasPlaced ? true : temp;
        }

        return hasPlaced;
    }
}
