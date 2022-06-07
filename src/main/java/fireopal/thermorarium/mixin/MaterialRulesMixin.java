package fireopal.thermorarium.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import fireopal.thermorarium.biome.ThermorariumBiomeKeys;
import fireopal.thermorarium.util.ThermorariumGeneralUtil;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.BiomeMaterialCondition;

@Mixin(MaterialRules.class)
public class MaterialRulesMixin {
    @Inject(method = "biome(Ljava/util/List;)Lnet/minecraft/world/gen/surfacebuilder/MaterialRules$BiomeMaterialCondition;",
        at = @At("RETURN"), cancellable = true
    )
    private static void biome(List<RegistryKey<Biome>> biomes, CallbackInfoReturnable<BiomeMaterialCondition> cir) {
        BiomeMaterialCondition returnValue = cir.getReturnValue();
        List<RegistryKey<Biome>> returnedBiomes = ((BiomeMaterialConditionInvoker) (Object) returnValue).getField_36414();

        for (RegistryKey<Biome> k : returnedBiomes) {
            // Add crimson biomes to the same surface rules
            if (k.getValue().equals(BiomeKeys.CRIMSON_FOREST.getValue())) {
                cir.setReturnValue(BiomeMaterialConditionInvoker._init_(
                    ThermorariumGeneralUtil.combine(returnedBiomes, ThermorariumBiomeKeys.THERMORARIUM_CRIMSON_BIOMES))
                );

            // Add warped biomes to the same surface rules
            } else if (k.getValue().equals(BiomeKeys.WARPED_FOREST.getValue())) {
                cir.setReturnValue(BiomeMaterialConditionInvoker._init_(
                    ThermorariumGeneralUtil.combine(returnedBiomes, ThermorariumBiomeKeys.THERMORARIUM_WARPED_BIOMES))
                );
              
            // Add soul sand biomes to the same surface rules
            } else if (k.getValue().equals(BiomeKeys.SOUL_SAND_VALLEY.getValue())) {
                cir.setReturnValue(BiomeMaterialConditionInvoker._init_(
                    ThermorariumGeneralUtil.combine(returnedBiomes, ThermorariumBiomeKeys.THERMORARIUM_SOUL_BIOMES))
                );

            // Add basalt deltas biomes to the same surface rules
            } else if (k.getValue().equals(BiomeKeys.BASALT_DELTAS.getValue())) {
                cir.setReturnValue(BiomeMaterialConditionInvoker._init_(
                    ThermorariumGeneralUtil.combine(returnedBiomes, ThermorariumBiomeKeys.THERMORARIUM_BASALT_DELTAS_BIOMES))
                );
            }
        }
    } 
}