package fireopal.thermorarium.gen;

import fireopal.thermorarium.biome.ThermorariumBiomeKeys;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.NoiseHypercube;

public class ThermorariumBiomeAdditions {
    private static final NoisePointWithoutWeirdness NETHER_WASTES_NP = new NoisePointWithoutWeirdness(0f, 0f, 0f, 0f, 0f, 0f);
    private static final NoisePointWithoutWeirdness SOUL_SAND_VALLEY_NP = new NoisePointWithoutWeirdness(0f, -0.5f, 0f, 0f, 0f, 0f);
    private static final NoisePointWithoutWeirdness CRIMSON_FOREST_NP = new NoisePointWithoutWeirdness(0.4f, 0f, 0f, 0f, 0f, 0f);
    private static final NoisePointWithoutWeirdness WARPED_FOREST_NP = new NoisePointWithoutWeirdness(0f, 0.5f, 0f, 0f, 0f, 0.375f);
    private static final NoisePointWithoutWeirdness BASALT_DELTAS_NP = new NoisePointWithoutWeirdness(-0.5f, 0f, 0f, 0f, 0f, 0f);


    public static final float PLAINS_WEIRDNESS = 0.6f;
    public static final float DEFAULT_WEIRDNESS = 0f;
    public static final float MARSHLAND_WEIRDNESS = -0.7f;
    public static final float FUNGAL_JUNGLE_WEIRDNESS = 0.85f;

    public static void init() {
        NetherBiomes.addNetherBiome(BiomeKeys.NETHER_WASTES, NETHER_WASTES_NP.withPlainsWeirdness());
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_OVERGROWN_VALLEY, SOUL_SAND_VALLEY_NP.withWeirdness(FUNGAL_JUNGLE_WEIRDNESS));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_PLAINS, CRIMSON_FOREST_NP.withPlainsWeirdness());
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_PLAINS, WARPED_FOREST_NP.withPlainsWeirdness());
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.BLACKSTONE_DELTAS, BASALT_DELTAS_NP.withPlainsWeirdness());

        NetherBiomes.addNetherBiome(BiomeKeys.NETHER_WASTES, NETHER_WASTES_NP.withMarshlandWeirdness());
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_OVERGROWN_VALLEY, SOUL_SAND_VALLEY_NP.withMarshlandWeirdness());
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_MARSHLAND, CRIMSON_FOREST_NP.withMarshlandWeirdness());
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_MARSHLAND, WARPED_FOREST_NP.withMarshlandWeirdness());
        NetherBiomes.addNetherBiome(BiomeKeys.BASALT_DELTAS, BASALT_DELTAS_NP.withMarshlandWeirdness());

        // NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.FUNGAL_JUNGLE, CRIMSON_FOREST_NP.combineWithMax(WARPED_FOREST_NP, 0f, 0f).withWeirdness(FUNGAL_JUNGLE_WEIRDNESS));
    }

    private static record NoisePointWithoutWeirdness(float temperatureNoise, float humidityNoise, 
        float continentalnessNoise, float erosionNoise, float depth, float offset
    ) {
        public NoiseHypercube withWeirdness(float weirdnessNoise) {
            return MultiNoiseUtil.createNoiseHypercube(temperatureNoise, humidityNoise, continentalnessNoise, erosionNoise, depth, weirdnessNoise, offset);
        }

        public NoiseHypercube withPlainsWeirdness() {
            return this.withWeirdness(PLAINS_WEIRDNESS);
        }

        // public NoiseHypercube withDefaultWeirdness() {
        //     return this.withWeirdness(DEFAULT_WEIRDNESS);
        // }

        public NoiseHypercube withMarshlandWeirdness() {
            return this.withWeirdness(MARSHLAND_WEIRDNESS);
        }

        // public NoisePointWithoutWeirdness average(NoisePointWithoutWeirdness other, float newDepth, float newOffset) {
        //     return new NoisePointWithoutWeirdness(
        //         (temperatureNoise + other.temperatureNoise() / 2), 
        //         (humidityNoise + other.humidityNoise() / 2), 
        //         (continentalnessNoise + other.continentalnessNoise() / 2), 
        //         (erosionNoise + other.erosionNoise() / 2), 
        //         newDepth, 
        //         newOffset
        //     );
        // }

        // public NoisePointWithoutWeirdness combineWithMax(NoisePointWithoutWeirdness other, float newDepth, float newOffset) {
        //     return new NoisePointWithoutWeirdness(
        //         Math.max(temperatureNoise, other.temperatureNoise()), 
        //         Math.max(humidityNoise, other.humidityNoise()), 
        //         Math.max(continentalnessNoise, other.continentalnessNoise()), 
        //         Math.max(erosionNoise, other.erosionNoise()), 
        //         newDepth, 
        //         newOffset
        //     );
        // }
    }
}
