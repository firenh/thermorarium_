package fireopal.thermorarium.biome;

import java.util.List;

import com.google.common.collect.ImmutableList;

import fireopal.thermorarium.Thermorarium;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ThermorariumBiomeKeys {
    private static RegistryKey<Biome> register(String id) {
        return RegistryKey.of(Registry.BIOME_KEY, Thermorarium.id(id));
    }

    public static final RegistryKey<Biome> CRIMSON_PLAINS = register("crimson_plains");
    public static final RegistryKey<Biome> WARPED_PLAINS = register("warped_plains");
    public static final RegistryKey<Biome> CRIMSON_OVERGROWN_VALLEY = register("weeping_valley");
    public static final RegistryKey<Biome> WARPED_OVERGROWN_VALLEY = register("twisted_valley");
    public static final RegistryKey<Biome> BLACKSTONE_DELTAS = register("blackstone_deltas");
    public static final RegistryKey<Biome> CRIMSON_MARSHLAND = register("crimson_marshland");
    public static final RegistryKey<Biome> WARPED_MARSHLAND = register("warped_marshland");
    // public static final RegistryKey<Biome> FUNGAL_JUNGLE = register("fungal_jungle");

    public static final List<RegistryKey<Biome>> THERMORARIUM_CRIMSON_BIOMES = ImmutableList.of(CRIMSON_PLAINS, CRIMSON_MARSHLAND);
    public static final List<RegistryKey<Biome>> THERMORARIUM_WARPED_BIOMES = ImmutableList.of(WARPED_PLAINS, WARPED_MARSHLAND);
    public static final List<RegistryKey<Biome>> THERMORARIUM_SOUL_BIOMES = ImmutableList.of(CRIMSON_OVERGROWN_VALLEY, WARPED_OVERGROWN_VALLEY);
    public static final List<RegistryKey<Biome>> THERMORARIUM_BASALT_DELTAS_BIOMES = ImmutableList.of(BLACKSTONE_DELTAS);
}