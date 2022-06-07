package fireopal.thermorarium.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.MaterialRules.BiomeMaterialCondition;

@Mixin(BiomeMaterialCondition.class)
public interface BiomeMaterialConditionInvoker {
    @Accessor
    public List<RegistryKey<Biome>> getBiomes();

    @Invoker("<init>")
    public static BiomeMaterialCondition _init_(List<RegistryKey<Biome>> biomes) {
        throw new AssertionError();
    }
}