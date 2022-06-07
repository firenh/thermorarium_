package fireopal.thermorarium.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.densityfunction.DensityFunction;
import net.minecraft.world.gen.densityfunction.DensityFunctions;
import net.minecraft.world.gen.noise.NoiseRouter;

@Mixin(value = DensityFunctions.class, priority = 112)
public class DensityFunctionsMixin {
    // @ModifyArg(
    //     method = "method_41211", index = 9, at = @At(
    //         value = "INVOKE", target = "Lnet/minecraft/world/gen/noise/NoiseRouter;<init>"
    //     )
    // )
    @Inject(method = "method_41211", at = @At("RETURN"), cancellable = true)
    private static void method_41211(Registry<DensityFunction> registry, DensityFunction densityFunction, CallbackInfoReturnable<NoiseRouter> cir) {
        NoiseRouter old = cir.getReturnValue();
        
        cir.setReturnValue(
            new NoiseRouter(
                old.barrierNoise(),
                old.fluidLevelFloodednessNoise(),
                old.fluidLevelSpreadNoise(),
                old.lavaNoise(),
                old.temperature(),
                old.vegetation(),
                old.continents(),
                old.erosion(),
                old.depth(),
                method_41116(registry, DensityFunctions.RIDGES_OVERWORLD),
                old.initialDensityWithoutJaggedness(),
                old.finalDensity(),
                old.veinToggle(),
                old.veinRidged(),
                old.veinGap()
            )
        );
    }

    @Invoker("method_41116")
    private static DensityFunction method_41116(Registry<DensityFunction> registry, RegistryKey<DensityFunction> registryKey) {
        throw new AssertionError();
    }
}
