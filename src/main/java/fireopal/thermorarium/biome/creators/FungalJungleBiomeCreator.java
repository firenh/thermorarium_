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
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.NetherPlacedFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;

public class FungalJungleBiomeCreator {
    /**
     * @see net.minecraft.world.biome.TheNetherBiomeCreator
     */

    public static Biome createFungalJungleBiome() {
        SpawnSettings spawnSettings = new SpawnSettings.Builder()
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4))
            .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
            .spawnCost(EntityType.ENDERMAN, 1.0, 0.12)
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ZOMBIFIED_PIGLIN, 10, 2, 4))
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.HOGLIN, 9, 3, 4))
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.PIGLIN, 5, 3, 4))
            .build();
        GenerationSettings.Builder builder = new GenerationSettings.Builder()
            .carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ThermorariumPlacedFeatures.CRIMSON_NYLIUM_PATCHES_FUNGAL_JUNGLE)
            .feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ThermorariumPlacedFeatures.CRIMSON_NYLIUM_PATCHES_FUNGAL_JUNGLE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_SOUL_FIRE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, ThermorariumPlacedFeatures.CRIMSON_FUNGI_FUNGAL_JUNGLE)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, ThermorariumPlacedFeatures.CRIMSON_VEGETATION_FUNGAL_JUNGLE)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, ThermorariumPlacedFeatures.WARPED_FUNGI_FUNGAL_JUNGLE)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, ThermorariumPlacedFeatures.WARPED_VEGETATION_FUNGAL_JUNGLE)
            ;
        DefaultBiomeFeatures.addNetherMineables(builder);
        return new Biome.Builder().precipitation(Biome.Precipitation.NONE).category(Biome.Category.NETHER)
            .temperature(2.0f).downfall(0.0f)
            .effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(0x440033)
                .skyColor(OverworldBiomeCreatorInvoker.getSkyColor(2.0f))
                .particleConfig(new BiomeParticleConfig(ParticleTypes.WARPED_SPORE, 0.045f))
                .loopSound(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP)
                .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0))
                .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111))
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_CRIMSON_FOREST)).build())
            .spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }
}
