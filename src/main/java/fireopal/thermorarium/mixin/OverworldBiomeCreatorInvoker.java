package fireopal.thermorarium.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.biome.OverworldBiomeCreator;

@Mixin(OverworldBiomeCreator.class)
public interface OverworldBiomeCreatorInvoker {
    @Invoker("getSkyColor")
    public static int getSkyColor(float temperature) {
        throw new AssertionError();
    }
}