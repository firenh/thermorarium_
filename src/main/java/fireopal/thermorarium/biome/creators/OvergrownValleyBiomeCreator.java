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

public class OvergrownValleyBiomeCreator {

    public static Biome createOvergrownValleyBiome(FungalTypes type) {
        final boolean isCrimson = type.isCrimson();

        SpawnSettings spawnSettings = new SpawnSettings.Builder()
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 20, 5, 5))
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.GHAST, 50, 4, 4))
            .spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 4, 4))
            .spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.STRIDER, 60, 1, 2))
            .spawnCost(EntityType.SKELETON, 0.7, 0.15).spawnCost(EntityType.GHAST, 0.7, 0.15)
            .spawnCost(EntityType.ENDERMAN, 0.7, 0.15).spawnCost(EntityType.STRIDER, 0.7, 0.15).build();
        GenerationSettings.Builder builder = new GenerationSettings.Builder()
            .carver(GenerationStep.Carver.AIR, ConfiguredCarvers.NETHER_CAVE)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, MiscPlacedFeatures.SPRING_LAVA)
            .feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, NetherPlacedFeatures.BASALT_PILLAR)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_OPEN)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.PATCH_FIRE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, ThermorariumPlacedFeatures.PATCH_SOUL_FIRE_OVERGROWN_VALLEY)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE_EXTRA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.GLOWSTONE)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, isCrimson ? ThermorariumPlacedFeatures.PATCH_CRIMSON_ROOTS_OVERGROWN_VALLEY : ThermorariumPlacedFeatures.PATCH_WARPED_ROOTS_OVERGROWN_VALLEY)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_MAGMA)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, NetherPlacedFeatures.SPRING_CLOSED)
            .feature(GenerationStep.Feature.UNDERGROUND_DECORATION, OrePlacedFeatures.ORE_SOUL_SAND)
            .feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, isCrimson ? ThermorariumPlacedFeatures.LARGE_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY : ThermorariumPlacedFeatures.LARGE_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? TreePlacedFeatures.CRIMSON_FUNGI : TreePlacedFeatures.WARPED_FUNGI)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? NetherPlacedFeatures.CRIMSON_FOREST_VEGETATION : NetherPlacedFeatures.WARPED_FOREST_VEGETATION)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? ThermorariumPlacedFeatures.WEEPING_VINES_OVERGROWN_VALLEY : NetherPlacedFeatures.TWISTING_VINES)
            .feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, isCrimson ? ThermorariumPlacedFeatures.SMALL_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY : ThermorariumPlacedFeatures.SMALL_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY)
            .feature(GenerationStep.Feature.VEGETAL_DECORATION, isCrimson ? ThermorariumPlacedFeatures.PATCH_CRIMSON_FUNGUS : ThermorariumPlacedFeatures.PATCH_WARPED_FUNGUS);
        DefaultBiomeFeatures.addNetherMineables(builder);
        return new Biome.Builder().precipitation(Biome.Precipitation.NONE)
            .temperature(2.0f).downfall(0.0f)
            .effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(1787717)
                .skyColor(OverworldBiomeCreatorInvoker.getSkyColor(2.0f))
                .particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.00625f))
                .loopSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP)
                .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0))
                .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111))
                .music(MusicType.createIngameMusic(SoundEvents.MUSIC_NETHER_SOUL_SAND_VALLEY)).build())
            .spawnSettings(spawnSettings).generationSettings(builder.build()).build();
    }
}
