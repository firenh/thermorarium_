package fireopal.thermorarium.gen;

import java.util.ArrayList;
import java.util.List;

import com.mojang.datafixers.util.Pair;

import fireopal.thermorarium.biome.ThermorariumBiomeKeys;
import net.fabricmc.fabric.api.biome.v1.NetherBiomes;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
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

    public static final List<Pair<RegistryKey<Biome>, NoiseHypercube>> NETHER_BIOMES;

    static {
        List<Pair<RegistryKey<Biome>, NoiseHypercube>> netherBiomesTemp = new ArrayList<>();

        netherBiomesTemp.add(new Pair<>(BiomeKeys.NETHER_WASTES, NETHER_WASTES_NP.withPlainsWeirdness()));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.WARPED_OVERGROWN_VALLEY, SOUL_SAND_VALLEY_NP.withWeirdness(FUNGAL_JUNGLE_WEIRDNESS)));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.CRIMSON_PLAINS, CRIMSON_FOREST_NP.withPlainsWeirdness()));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.WARPED_PLAINS, WARPED_FOREST_NP.withPlainsWeirdness()));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.BLACKSTONE_DELTAS, BASALT_DELTAS_NP.withPlainsWeirdness()));

        netherBiomesTemp.add(new Pair<>(BiomeKeys.NETHER_WASTES, NETHER_WASTES_NP.withMarshlandWeirdness()));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.CRIMSON_OVERGROWN_VALLEY, SOUL_SAND_VALLEY_NP.withMarshlandWeirdness()));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.CRIMSON_MARSHLAND, CRIMSON_FOREST_NP.withMarshlandWeirdness()));
        netherBiomesTemp.add(new Pair<>(ThermorariumBiomeKeys.WARPED_MARSHLAND, WARPED_FOREST_NP.withMarshlandWeirdness()));
        netherBiomesTemp.add(new Pair<>(BiomeKeys.BASALT_DELTAS, BASALT_DELTAS_NP.withMarshlandWeirdness()));

        // NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.FUNGAL_JUNGLE, CRIMSON_FOREST_NP.combineWithMax(WARPED_FOREST_NP, 0f, 0f).withWeirdness(FUNGAL_JUNGLE_WEIRDNESS));
    
        NETHER_BIOMES = netherBiomesTemp;
    }

    public static void init() {
        // for (Pair<RegistryKey<Biome>, NoiseHypercube> b : NETHER_BIOMES) {
        //     Thermorarium.LOGGER.info("biome: " + b.getFirst() + "; noise point: " + b.getSecond());
        //     NetherBiomes.addNetherBiome(b.getFirst(), b.getSecond());
        // }
        
        //  Nether Wastes
        NetherBiomes.addNetherBiome(BiomeKeys.NETHER_WASTES, MultiNoiseUtil.createNoiseHypercube(0f, 0f, 0f, 0f, 0f, MARSHLAND_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(BiomeKeys.NETHER_WASTES, MultiNoiseUtil.createNoiseHypercube(0f, 0f, 0f, 0f, 0f, PLAINS_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(BiomeKeys.NETHER_WASTES, MultiNoiseUtil.createNoiseHypercube(0f, 0f, 0f, 0f, 0f, FUNGAL_JUNGLE_WEIRDNESS, 0.1f));

        //  Soul Sand Valley
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_OVERGROWN_VALLEY, MultiNoiseUtil.createNoiseHypercube(0f, -0.5f, 0f, 0f, 0f, MARSHLAND_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(BiomeKeys.SOUL_SAND_VALLEY, MultiNoiseUtil.createNoiseHypercube(0f, -0.5f, 0f, 0f, 0f, PLAINS_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_OVERGROWN_VALLEY, MultiNoiseUtil.createNoiseHypercube(0f, -0.5f, 0f, 0f, 0f, FUNGAL_JUNGLE_WEIRDNESS, 0.1f));

        //  Crimson Forest
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_MARSHLAND, MultiNoiseUtil.createNoiseHypercube(0.4f, 0f, 0f, 0f, 0f, MARSHLAND_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_PLAINS, MultiNoiseUtil.createNoiseHypercube(0.4f, 0f, 0f, 0f, 0f, PLAINS_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.CRIMSON_PLAINS, MultiNoiseUtil.createNoiseHypercube(0.4f, 0f, 0f, 0f, 0f, FUNGAL_JUNGLE_WEIRDNESS, 0.1f));
    
        //  Warped Forest 
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_MARSHLAND, MultiNoiseUtil.createNoiseHypercube(0f, 0.5f, 0f, 0f, 0f, MARSHLAND_WEIRDNESS, 0.475f));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_PLAINS, MultiNoiseUtil.createNoiseHypercube(0f, 0.5f, 0f, 0f, 0f, PLAINS_WEIRDNESS, 0.475f));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.WARPED_PLAINS, MultiNoiseUtil.createNoiseHypercube(0f, 0.5f, 0f, 0f, 0f, FUNGAL_JUNGLE_WEIRDNESS, 0.475f));
    
        // Basalt Deltas
        NetherBiomes.addNetherBiome(BiomeKeys.BASALT_DELTAS, MultiNoiseUtil.createNoiseHypercube(-0.5f, 0f, 0f, 0f, 0f, MARSHLAND_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(ThermorariumBiomeKeys.BLACKSTONE_DELTAS, MultiNoiseUtil.createNoiseHypercube(-0.5f, 0f, 0f, 0f, 0f, PLAINS_WEIRDNESS, 0.1f));
        NetherBiomes.addNetherBiome(BiomeKeys.BASALT_DELTAS, MultiNoiseUtil.createNoiseHypercube(-0.5f, 0f, 0f, 0f, 0f, FUNGAL_JUNGLE_WEIRDNESS, 0.1f));

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

        public NoiseHypercube withMarshlandWeirdness() {
            return this.withWeirdness(MARSHLAND_WEIRDNESS);
        }
    }
}
