package fireopal.thermorarium.biome;

import fireopal.thermorarium.biome.creators.BlackstoneDeltasCreator;
import fireopal.thermorarium.biome.creators.FungalJungleBiomeCreator;
import fireopal.thermorarium.biome.creators.FungalMarshlandBiomeCreator;
import fireopal.thermorarium.biome.creators.FungalPlainsBiomeCreators;
import fireopal.thermorarium.biome.creators.OvergrownValleyBiomeCreator;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;

public class ThermorariumBiomes {
    public static final Biome CRIMSON_PLAINS = FungalPlainsBiomeCreators.createFungalPlainsBiome(FungalTypes.CRIMSON);
    public static final Biome WARPED_PLAINS = FungalPlainsBiomeCreators.createFungalPlainsBiome(FungalTypes.WARPED);
    public static final Biome CRIMSON_OVERGROWN_VALLEY = OvergrownValleyBiomeCreator.createOvergrownValleyBiome(FungalTypes.CRIMSON);
    public static final Biome WARPED_OVERGROWN_VALLEY = OvergrownValleyBiomeCreator.createOvergrownValleyBiome(FungalTypes.WARPED);
    public static final Biome BLACKSTONE_DELTAS = BlackstoneDeltasCreator.createBlackstoneDeltasBiome();
    public static final Biome FUNGAL_JUNGLE = FungalJungleBiomeCreator.createFungalJungleBiome();
    public static final Biome CRIMSON_MARSHLAND = FungalMarshlandBiomeCreator.createFungalMarshlandBiome(FungalTypes.CRIMSON);
    public static final Biome WARPED_MARSHLAND = FungalMarshlandBiomeCreator.createFungalMarshlandBiome(FungalTypes.WARPED);

    private static void register(RegistryKey<Biome> key, Biome biome) {
        Registry.register(BuiltinRegistries.BIOME, key.getValue(), biome);
    }

    public static void init() {
        register(ThermorariumBiomeKeys.CRIMSON_PLAINS, CRIMSON_PLAINS);
        register(ThermorariumBiomeKeys.WARPED_PLAINS, WARPED_PLAINS);
        register(ThermorariumBiomeKeys.CRIMSON_OVERGROWN_VALLEY, CRIMSON_OVERGROWN_VALLEY);
        register(ThermorariumBiomeKeys.WARPED_OVERGROWN_VALLEY, WARPED_OVERGROWN_VALLEY);
        register(ThermorariumBiomeKeys.BLACKSTONE_DELTAS, BLACKSTONE_DELTAS);
        // register(ThermorariumBiomeKeys.FUNGAL_JUNGLE, FUNGAL_JUNGLE);
        register(ThermorariumBiomeKeys.CRIMSON_MARSHLAND, CRIMSON_MARSHLAND);
        register(ThermorariumBiomeKeys.WARPED_MARSHLAND, WARPED_MARSHLAND);
    }
}