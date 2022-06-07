package fireopal.thermorarium.features;

import java.util.List;

import fireopal.thermorarium.Thermorarium;
import fireopal.thermorarium.features.features.config.FeatureCollectionFeatureConfig;
import fireopal.thermorarium.features.features.config.NetherrackBaseFeatureConfig;
import fireopal.thermorarium.features.features.config.SpikeFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DeltaFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ThermorariumConfiguredFeatures {
    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<?, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_FEATURE, Thermorarium.id(id), new ConfiguredFeature<FC, F>(feature, config));
    }

    public static void init() {
        new ThermorariumConfiguredFeatures();
    }

    /*
     *  Overgrown Valley
     */

    @SuppressWarnings("deprecation")
    private static NetherrackBaseFeatureConfig overgrownValleyNBFConfig(BlockState state, IntProvider radius, IntProvider height, IntProvider count) {
        return new NetherrackBaseFeatureConfig(
            radius,
            height,
            state,
            Blocks.NETHERRACK.getDefaultState(),
            RegistryEntryList.of(Block::getRegistryEntry, 
                Blocks.SOUL_SAND,
                Blocks.SOUL_SOIL,
                Blocks.NETHERRACK
            ),
            count,
            true,
            false
        );
    }

    private static NetherrackBaseFeatureConfig overgrownValleyNBFConfigLarge(BlockState state) {
        return overgrownValleyNBFConfig(
            state, 
            UniformIntProvider.create(6, 12),
            UniformIntProvider.create(4, 9),
            UniformIntProvider.create(30, 50)
        );
    }

    private static NetherrackBaseFeatureConfig overgrownValleyNBFConfigSmall(BlockState state) {
        return overgrownValleyNBFConfig(
            state, 
            UniformIntProvider.create(2, 4),
            UniformIntProvider.create(3, 5),
            UniformIntProvider.create(10, 20)
        );
    }

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("large_crimson_nylium_patches_overgrown_valley", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        overgrownValleyNBFConfigLarge(Blocks.CRIMSON_NYLIUM.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("small_crimson_nylium_patches_overgrown_valley", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        overgrownValleyNBFConfigSmall(Blocks.CRIMSON_NYLIUM.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("large_warped_nylium_patches_overgrown_valley", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        overgrownValleyNBFConfigLarge(Blocks.WARPED_NYLIUM.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("small_warped_nylium_patches_overgrown_valley", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        overgrownValleyNBFConfigSmall(Blocks.WARPED_NYLIUM.getDefaultState())
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> PATCH_CRIMSON_FUNGI = register("patch_crimson_fungi",
        Feature.RANDOM_PATCH,
        new RandomPatchFeatureConfig(
            64, 7, 3, 
            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, 
                new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.CRIMSON_FUNGUS))
            )
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> PATCH_WARPED_FUNGI = register("patch_warped_fungi",
        Feature.RANDOM_PATCH,
        new RandomPatchFeatureConfig(
            64, 7, 3, 
            PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, 
                new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.WARPED_FUNGUS))
            )
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> PATCH_WARPED_ROOTS = register("patch_warped_roots",
        Feature.RANDOM_PATCH, 
        ConfiguredFeatures.createRandomPatchFeatureConfig(
            Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(
                BlockStateProvider.of(Blocks.CRIMSON_ROOTS)
            )
        )
    );

    /*
     *  Blackstone Deltas
     */

    public static final RegistryEntry<ConfiguredFeature<?, ?>> SMALL_BLACKSTONE_SPIKE = register("small_blackstone_spike",
        ThermorariumFeatures.SPIKE_FEATURE,
        new SpikeFeatureConfig(
            UniformIntProvider.create(2, 4),
            Blocks.BLACKSTONE.getDefaultState(),
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> LARGE_BLACKSTONE_SPIKE = register("large_blackstone_spike",
        ThermorariumFeatures.SPIKE_FEATURE,
        new SpikeFeatureConfig(
            UniformIntProvider.create(4, 8),
            Blocks.BLACKSTONE.getDefaultState(),
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> BLACKSTONE_SPIKES = register("blackstone_spikes",
        Feature.RANDOM_PATCH, 
        new RandomPatchFeatureConfig(
            96, 12, 3, 
            PlacedFeatures.createEntry(Feature.RANDOM_SELECTOR, 
                new RandomFeatureConfig(
                    List.of(
                        new RandomFeatureEntry(
                            PlacedFeatures.createEntry(LARGE_BLACKSTONE_SPIKE,
                                BlockFilterPlacementModifier.of(
                                    BlockPredicate.matchingBlock(Blocks.BLACKSTONE, new BlockPos(0, -1, 0))
                                )
                            ),
                            0.1f
                        )
                    ),
                    PlacedFeatures.createEntry(SMALL_BLACKSTONE_SPIKE,
                        BlockFilterPlacementModifier.of(
                            BlockPredicate.matchingBlock(Blocks.BLACKSTONE, new BlockPos(0, -1, 0))
                        )
                    )
                )
            )
        )  
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> UPSIDE_DOWN_BLACKSTONE_SPIKE = register("upside_down_blackstone_spike", 
        ThermorariumFeatures.SPIKE_FEATURE,
        new SpikeFeatureConfig(
            UniformIntProvider.create(2, 5),
            Blocks.BLACKSTONE.getDefaultState(),
            true
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> UPSIDE_DOWN_BLACKSTONE_SPIKES = register("upside_down_blackstone_spikes",
        Feature.RANDOM_PATCH, 
        new RandomPatchFeatureConfig(
            64, 12, 3, 
            PlacedFeatures.createEntry(UPSIDE_DOWN_BLACKSTONE_SPIKE
                // BlockFilterPlacementModifier.of(
                //     BlockPredicate.matchingBlock(Blocks.BLACKSTONE, new BlockPos(0, 1, 0))
                // )
            )
        )  
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> ORE_BLACKSTONE_OVER_BASALT = register("ore_blackstone_over_basalt",
        Feature.ORE,
        new OreFeatureConfig(
            new BlockMatchRuleTest(Blocks.BASALT),
            Blocks.BLACKSTONE.getDefaultState(), 
            64
        )
    );
    
    /*
     *  Fungal Jungle
     */ 

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<ConfiguredFeature<?, ?>> CRIMSON_NYLIUM_PATCHES_FUNGAL_JUNGLE = register("crimson_nylium_patches_fungal_jungle", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(5, 9),
            UniformIntProvider.create(4, 8),
            Blocks.CRIMSON_NYLIUM.getDefaultState(),
            Blocks.NETHERRACK.getDefaultState(),
            RegistryEntryList.of(Block::getRegistryEntry, 
                Blocks.NETHERRACK
            ),
            UniformIntProvider.create(15, 40),
            true,
            false
        )
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<ConfiguredFeature<?, ?>> WARPED_NYLIUM_PATCHES_FUNGAL_JUNGLE = register("warped_nylium_patches_fungal_jungle", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(5, 9),
            UniformIntProvider.create(4, 8),
            Blocks.WARPED_NYLIUM.getDefaultState(),
            Blocks.NETHERRACK.getDefaultState(),
            RegistryEntryList.of(Block::getRegistryEntry, 
                Blocks.NETHERRACK
            ),
            UniformIntProvider.create(15, 40),
            true,
            false
        )
    );

    /*
     *  Marshland 
     */

    public static final RegistryEntry<ConfiguredFeature<?, ?>> MARSHLAND_DELTA_BASE = register("marshland_delta_base", 
        Feature.DELTA_FEATURE, 
        new DeltaFeatureConfig(
            Blocks.MAGMA_BLOCK.getDefaultState(), 
            Blocks.MAGMA_BLOCK.getDefaultState(), 
            UniformIntProvider.create(3, 6), 
            UniformIntProvider.create(2, 2)
        )     
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<ConfiguredFeature<?, ?>> LAVA_OVER_MAGMA = register("lava_over_magma", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(5, 9),
            ConstantIntProvider.create(1),
            Blocks.LAVA.getDefaultState(),
            Blocks.MAGMA_BLOCK.getDefaultState(),
            RegistryEntryList.of(Block::getRegistryEntry, 
                Blocks.MAGMA_BLOCK
            ),
            UniformIntProvider.create(5, 15),
            false,
            true
        )
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<ConfiguredFeature<?, ?>> NETHERRACK_PATCHES_MARSHLAND = register("netherrack_patches_marshland", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(6, 9),
            UniformIntProvider.create(4, 9),
            Blocks.NETHERRACK.getDefaultState(),
            Blocks.NETHERRACK.getDefaultState(),
            RegistryEntryList.of(Block::getRegistryEntry, 
                Blocks.CRIMSON_NYLIUM,
                Blocks.WARPED_NYLIUM,
                Blocks.MAGMA_BLOCK
            ),
            UniformIntProvider.create(1, 3),
            true,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> MARSHLAND_DELTA = register("marshland_delta", 
        ThermorariumFeatures.FEATURE_COLLECTION_FEATURE,
        new FeatureCollectionFeatureConfig(
            List.of(
                PlacedFeatures.createEntry(MARSHLAND_DELTA_BASE),
                PlacedFeatures.createEntry(LAVA_OVER_MAGMA),
                PlacedFeatures.createEntry(NETHERRACK_PATCHES_MARSHLAND)
            )
        )
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<ConfiguredFeature<?, ?>> BASALT_PATCHES_MARSHLAND = register("basalt_patches_marshland", 
        ThermorariumFeatures.NETHERRACK_BASE_FEATURE,
        new NetherrackBaseFeatureConfig(
            UniformIntProvider.create(6, 12),
            UniformIntProvider.create(4, 9),
            Blocks.BASALT.getDefaultState(),
            Blocks.BASALT.getDefaultState(),
            RegistryEntryList.of(Block::getRegistryEntry, 
                Blocks.NETHERRACK,
                Blocks.CRIMSON_NYLIUM,
                Blocks.WARPED_NYLIUM,
                Blocks.MAGMA_BLOCK
            ),
            UniformIntProvider.create(16, 32),
            true,
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> BASALT_SPIKE = register("basalt_spike",
        ThermorariumFeatures.SPIKE_FEATURE,
        new SpikeFeatureConfig(
            UniformIntProvider.create(3, 6),
            Blocks.BASALT.getDefaultState(),
            false
        )
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> BASALT_SPIKES = register("basalt_spikes",
        Feature.RANDOM_PATCH, 
        new RandomPatchFeatureConfig(
            96, 12, 3, 
            PlacedFeatures.createEntry(Feature.RANDOM_SELECTOR, 
                new RandomFeatureConfig(
                    List.of(),
                    PlacedFeatures.createEntry(BASALT_SPIKE,
                        BlockFilterPlacementModifier.of(
                            BlockPredicate.matchingBlock(Blocks.BASALT, new BlockPos(0, -1, 0))
                        )
                    )
                )
            )
        )  
    );

    public static final RegistryEntry<ConfiguredFeature<?, ?>> BASALT_FEATURE_MARSHLAND = register("basalt_feature_marshland", 
        ThermorariumFeatures.FEATURE_COLLECTION_FEATURE,
        new FeatureCollectionFeatureConfig(
            List.of(
                PlacedFeatures.createEntry(BASALT_PATCHES_MARSHLAND),
                PlacedFeatures.createEntry(BASALT_SPIKES),
                PlacedFeatures.createEntry(NETHERRACK_PATCHES_MARSHLAND)
            )
        )
    );
}
