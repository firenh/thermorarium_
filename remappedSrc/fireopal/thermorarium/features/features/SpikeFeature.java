package fireopal.thermorarium.features.features;

import com.mojang.serialization.Codec;

// import fireopal.thermorarium.Thermorarium;
import fireopal.thermorarium.features.features.config.SpikeFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SpikeFeature extends Feature<SpikeFeatureConfig> {
    public SpikeFeature(Codec<SpikeFeatureConfig> configCodec) {
        super(configCodec);
    }

    private static final Direction[] DIRECTIONS = {Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST};

    public boolean canGenerate(FeatureContext<SpikeFeatureConfig> context) {
        return true;
    }

    @Override
    public boolean generate(FeatureContext<SpikeFeatureConfig> context) {
        if (!this.canGenerate(context)) return false;

        final SpikeFeatureConfig config = context.getConfig();
        final boolean upsideDown = config.upsideDown();
        final Direction downDir = upsideDown ? Direction.UP : Direction.DOWN;
        final Direction upDir = upsideDown ? Direction.DOWN : Direction.UP;
        final BlockPos origin = context.getOrigin().offset(downDir);
        final int height = config.height().get(context.getRandom()) + 1;
        final StructureWorldAccess world = context.getWorld();
        final BlockState state = config.setState();
        

        this.setBlockState(world, origin, state);

        for (int i = 1; i < height; i += 1) {
            BlockPos newBlockPos = origin.offset(upDir, i);
            if (!world.getBlockState(newBlockPos).isAir()) break;
            this.setBlockState(world, newBlockPos, state);
        }

        for (Direction d : DIRECTIONS) {
            int newHeight = context.getRandom().nextInt(height > 1 ? height - 1 : height);
            // Thermorarium.LOGGER.info("newHeight:" + newHeight);
            int startOffset = 0;
            boolean bl = false;

            for (int i = 0; i < height; i += 1) {
                if (context.getWorld().getBlockState(origin.offset(d).offset(downDir, i)).isOpaque()) {
                    startOffset -= 1;
                    bl = true;
                    break;
                }

                startOffset += 1;
            }

            // Thermorarium.LOGGER.info("startOffset:" + startOffset);
            if (!bl) continue;

            if (newHeight > 2 || context.getRandom().nextBoolean()) {
                this.setBlockState(world, origin.offset(d).offset(downDir, startOffset + 1), state);
                this.setBlockState(world, origin.offset(d).offset(downDir, startOffset), state);
            }

            // Thermorarium.LOGGER.info("newHeight: " + newHeight);

            for (int i = 0; i < newHeight; i += 1) {
                BlockPos newBlockPos = origin.offset(upDir, i).offset(downDir, startOffset).offset(d);
                BlockState setOverState = world.getBlockState(newBlockPos);

                if (setOverState.isOpaque() && !setOverState.isOf(state.getBlock())) break;

                this.setBlockState(world, newBlockPos, state);
                // Thermorarium.LOGGER.info(newBlockPos);
            }
        }

        return true;
    }
}
