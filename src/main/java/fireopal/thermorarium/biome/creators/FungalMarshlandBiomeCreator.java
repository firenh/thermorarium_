package fireopal.thermorarium.biome.creators;

import fireopal.thermorarium.biome.FungalTypes;
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
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.NetherPlacedFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.TreePlacedFeatures;


public class FungalMarshlandBiomeCreator {
    /**
     * @see net.minecraft.world.biome.TheNetherBiomeCreator
     */

    public static Biome createFungalMarshlandBiome(FungalTypes type) {
        final boolean isCrimson = type.isCrimson();

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
                
        if (type == FungalTypes.CRIMSON) {
            spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 1, 2, 4))
                .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 9, 3, 4))
                .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 3, 4))
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2));
        } else {
            spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4))
                .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
                .spawnCost(EntityType.ENDERMAN, 1.0, 0.12);
        }

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder()
            .carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);

        DefaultBiomeFeatures.addDefaultMushrooms(generationSettings);

        generationSettings
            .feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ThermorariumPlacedFeatures.MARSHLAND_DELTA)
            .feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ThermorariumPlacedFeatures.BASALT_FEATURE_MARSHLAND)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? NetherPlacedFeatures.WEEPING_VINES : TreePlacedFeatures.WARPED_FUNGI)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? TreePlacedFeatures.CRIMSON_FUNGI : NetherPlacedFeatures .WARPED_FOREST_VEGETATION)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? NetherPlacedFeatures.CRIMSON_FOREST_VEGETATION : NetherPlacedFeatures.TWISTING_VINES)
            
        ;   
        DefaultBiomeFeatures.addNetherMineables(generationSettings);

        BiomeEffects.Builder biomeEffects = new BiomeEffects.Builder();
                      
        if (type == FungalTypes.CRIMSON) {
            biomeEffects.waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(0x330303)
                .skyColor(OverworldBiomeCreatorInvoker.getSkyColor(2.0f))
                .particleConfig(new BiomeParticleConfig(ParticleTypes.CRIMSON_SPORE, 0.025f))
                .loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0))
                .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111))
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST));
        } else {
            biomeEffects.waterColor(4159204)
                .waterFogColor(329011)
                .fogColor(1705242)
                .skyColor(OverworldBiomeCreatorInvoker.getSkyColor(2.0f))
                .particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.01428f))
                .loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0))
                .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111))
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_WARPED_FOREST));
        }
        return new Biome.Builder().precipitation(Biome.Precipitation.NONE)
            .temperature(2.0f).downfall(0.0f)
            .effects(biomeEffects.build())
            .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
}
