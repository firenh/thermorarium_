package fireopal.thermorarium.biome.creators;

import fireopal.thermorarium.features.ThermorariumPlacedFeatures;
import fireopal.thermorarium.mixin.OverworldBiomeCreatorInvoker;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.NetherPlacedFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class BlackstoneDeltasCreator {

    public static Biome createBlackstoneDeltasBiome() {
        SpawnSettings spawnSettings = new SpawnSettings.Builder()
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 40, 1, 1))
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.MAGMA_CUBE, 100, 2, 5))
            .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2)).build();
        GenerationSettings.Builder builder = new GenerationSettings.Builder()
            .carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
            .feature(GenerationStep.Feature.SURFACE_STRUCTURES, NetherPlacedFeatures.DELTA)
            .feature(GenerationStep.Feature.SURFACE_STRUCTURES, ThermorariumPlacedFeatures.RARER_SMALL_BASALT_COLUMNS)
            .feature(GenerationStep.Feature.SURFACE_STRUCTURES, ThermorariumPlacedFeatures.BLACKSTONE_SPIKES)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BASALT_BLOBS)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.BLACKSTONE_BLOBS)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_DELTA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_NETHER)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_NETHER)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED_DOUBLE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_GOLD_DELTAS)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_QUARTZ_DELTAS)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ThermorariumPlacedFeatures.ORE_BLACKSTONE_OVER_BASALT)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, ThermorariumPlacedFeatures.UPSIDE_DOWN_BLACKSTONE_SPIKES);
        DefaultBiomeFeatures.addAncientDebris(builder);
        return new Biome.Builder().precipitation(Biome.Precipitation.NONE)
            .temperature(2.0f).downfall(0.0f)
            .effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(6840176)
                .skyColor(OverworldBiomeCreatorInvoker.getSkyColor(2.0f))
                .particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.118093334f))
                .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0))
                .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111))
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_BASALT_DELTAS)).build())
            .spawnSettings(spawnSettings).generationSettings(builder.build()).build();

    }
}
