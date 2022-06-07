package fireopal.thermorarium.features;

import java.util.List;

import fireopal.thermorarium.Thermorarium;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.NetherConfiguredFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountMultilayerPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.EnvironmentScanPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class ThermorariumPlacedFeatures {
    public static void init() {
        new ThermorariumPlacedFeatures();
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, Thermorarium.id(id), new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier ... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> SPARSE_CRIMSON_FUNGI = register("sparse_crimson_fungi", 
        TreeConfiguredFeatures.CRIMSON_FUNGUS, 
        CountMultilayerPlacementModifier.of(1),     
        BiomePlacementModifier.of()
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> SPARSE_WARPED_FUNGI = register("sparse_warped_fungi", 
        TreeConfiguredFeatures.WARPED_FUNGUS, 
        CountMultilayerPlacementModifier.of(1),     
        BiomePlacementModifier.of()
    );

    private static List<PlacementModifier> overgrownValleyNBFPlacementModifiers(IntProvider count) {
        return List.of(
            CountPlacementModifier.of(count), 
            SquarePlacementModifier.of(), 
            PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
            EnvironmentScanPlacementModifier.of(
                Direction.DOWN, BlockPredicate.solid(), 
                BlockPredicate.IS_AIR, 12
            ), 
            RandomOffsetPlacementModifier.vertically(
                ConstantIntProvider.create(1)
            ), BiomePlacementModifier.of()
        );
    }

    public static final RegistryEntry<PlacedFeature> LARGE_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("large_crimson_nylium_patches_overgrown_valley",
        ThermorariumConfiguredFeatures.LARGE_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY,
        overgrownValleyNBFPlacementModifiers(UniformIntProvider.create(4, 12))
    );

    public static final RegistryEntry<PlacedFeature> SMALL_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("small_crimson_nylium_patches_overgrown_valley",
        ThermorariumConfiguredFeatures.SMALL_CRIMSON_NYLIUM_PATCHES_OVERGROWN_VALLEY,
        overgrownValleyNBFPlacementModifiers(UniformIntProvider.create(12, 28))
    );

    public static final RegistryEntry<PlacedFeature> LARGE_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("large_warped_nylium_patches_overgrown_valley",
        ThermorariumConfiguredFeatures.LARGE_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY,
        overgrownValleyNBFPlacementModifiers(UniformIntProvider.create(4, 12))
    );

    public static final RegistryEntry<PlacedFeature> SMALL_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY = register("small_warped_nylium_patches_overgrown_valley",
        ThermorariumConfiguredFeatures.SMALL_WARPED_NYLIUM_PATCHES_OVERGROWN_VALLEY,
        overgrownValleyNBFPlacementModifiers(UniformIntProvider.create(12, 28))
    );

    public static final RegistryEntry<PlacedFeature> PATCH_CRIMSON_ROOTS_OVERGROWN_VALLEY = register("patch_crimson_roots_overgrown_valley", 
        NetherConfiguredFeatures.PATCH_CRIMSON_ROOTS, 
        CountPlacementModifier.of(16),
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_WARPED_ROOTS_OVERGROWN_VALLEY = register("patch_warped_roots_overgrown_valley", 
        ThermorariumConfiguredFeatures.PATCH_WARPED_ROOTS, 
        CountPlacementModifier.of(16),
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> WEEPING_VINES_OVERGROWN_VALLEY = register("weeping_vines_overgrown_valley", 
        NetherConfiguredFeatures.WEEPING_VINES, 
        CountPlacementModifier.of(20), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_CRIMSON_FUNGUS = register("patch_crimson_fungus", 
        ThermorariumConfiguredFeatures.PATCH_CRIMSON_FUNGI, 
        CountPlacementModifier.of(8),
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_WARPED_FUNGUS = register("patch_warped_fungus", 
    ThermorariumConfiguredFeatures.PATCH_WARPED_FUNGI, 
        CountPlacementModifier.of(8),
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> PATCH_SOUL_FIRE_OVERGROWN_VALLEY = register("patch_soul_fire_overgrown_valley", 
        NetherConfiguredFeatures.PATCH_SOUL_FIRE, 
        CountPlacementModifier.of(UniformIntProvider.create(4, 8)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.FOUR_ABOVE_AND_BELOW_RANGE, 
        BiomePlacementModifier.of()
    );

    // public static final RegistryEntry<PlacedFeature> SMALL_BLACKSTONE_SPIKE = register("small_blackstone_spike",
    //     ThermorariumConfiguredFeatures.SMALL_BLACKSTONE_SPIKE,
    //     BlockFilterPlacementModifier.of(
    //         BlockPredicate.matchingBlock(Blocks.BLACKSTONE, new BlockPos(0, -1, 0))
    //     )
    // );

    // public static final RegistryEntry<PlacedFeature> LARGE_BLACKSTONE_SPIKE = register("large_blackstone_spike",
    //     ThermorariumConfiguredFeatures.LARGE_BLACKSTONE_SPIKE,
    //     BlockFilterPlacementModifier.of(
    //         BlockPredicate.matchingBlock(Blocks.BLACKSTONE, new BlockPos(0, -1, 0))
    //     )
    // );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> BLACKSTONE_SPIKES = register("blackstone_spikes",
        ThermorariumConfiguredFeatures.BLACKSTONE_SPIKES,
        SquarePlacementModifier.of(),
        CountMultilayerPlacementModifier.of(UniformIntProvider.create(2, 5)), 
        BiomePlacementModifier.of()
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> RARER_SMALL_BASALT_COLUMNS = register("rare_small_basalt_columns",
        NetherConfiguredFeatures.SMALL_BASALT_COLUMNS,
        SquarePlacementModifier.of(),
        CountMultilayerPlacementModifier.of(10), 
        BlockFilterPlacementModifier.of(
            BlockPredicate.matchingBlocks(new BlockPos(0, -1, 0), Blocks.BASALT)
        ),
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> ORE_BLACKSTONE_OVER_BASALT = register("ore_blackstone_over_basalt",
        ThermorariumConfiguredFeatures.ORE_BLACKSTONE_OVER_BASALT,
        SquarePlacementModifier.of(),
        CountPlacementModifier.of(UniformIntProvider.create(5, 15)),
        HeightRangePlacementModifier.uniform(YOffset.aboveBottom(32), YOffset.belowTop(10))
    );

    public static final RegistryEntry<PlacedFeature> UPSIDE_DOWN_BLACKSTONE_SPIKES = register("upside_down_blackstone_spikes",
        ThermorariumConfiguredFeatures.UPSIDE_DOWN_BLACKSTONE_SPIKES,
        SquarePlacementModifier.of(),
        CountPlacementModifier.of(UniformIntProvider.create(40, 60)), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> CRIMSON_NYLIUM_PATCHES_FUNGAL_JUNGLE = register("crimson_nylium_patches_fungal_jungle",
        ThermorariumConfiguredFeatures.CRIMSON_NYLIUM_PATCHES_FUNGAL_JUNGLE,
        CountPlacementModifier.of(UniformIntProvider.create(12, 28)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> WARPED_NYLIUM_PATCHES_FUNGAL_JUNGLE = register("warped_nylium_patches_fungal_jungle",
        ThermorariumConfiguredFeatures.WARPED_NYLIUM_PATCHES_FUNGAL_JUNGLE,
        CountPlacementModifier.of(UniformIntProvider.create(12, 28)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), BiomePlacementModifier.of()
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> CRIMSON_FUNGI_FUNGAL_JUNGLE = register("crimson_fungi_fungal_jungle",
        TreeConfiguredFeatures.CRIMSON_FUNGUS,
        SquarePlacementModifier.of(),
        CountMultilayerPlacementModifier.of(5), 
        BiomePlacementModifier.of()
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> WARPED_FUNGI_FUNGAL_JUNGLE = register("warped_fungi_fungal_jungle",
        TreeConfiguredFeatures.WARPED_FUNGUS,
        SquarePlacementModifier.of(),
        CountMultilayerPlacementModifier.of(5), 
        BiomePlacementModifier.of()
    );
    
    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> CRIMSON_VEGETATION_FUNGAL_JUNGLE = register("crimson_vegetation_fungal_jungle",
        NetherConfiguredFeatures.CRIMSON_FOREST_VEGETATION, 
        SquarePlacementModifier.of(),
        CountMultilayerPlacementModifier.of(6), 
        BiomePlacementModifier.of()
    );

    @SuppressWarnings("deprecation")
    public static final RegistryEntry<PlacedFeature> WARPED_VEGETATION_FUNGAL_JUNGLE = register("warped_vegetation_fungal_jungle",
        NetherConfiguredFeatures.WARPED_FOREST_VEGETATION, 
        SquarePlacementModifier.of(),
        CountMultilayerPlacementModifier.of(6), 
        BiomePlacementModifier.of()
    );
    
    /*
     *  Marshland
     */

    public static final RegistryEntry<PlacedFeature> MARSHLAND_DELTA = register("marshland_delta",
        ThermorariumConfiguredFeatures.MARSHLAND_DELTA,
        CountMultilayerPlacementModifier.of(UniformIntProvider.create(40, 60)), 
        SquarePlacementModifier.of(), 
        PlacedFeatures.BOTTOM_TO_TOP_RANGE, 
        EnvironmentScanPlacementModifier.of(
            Direction.DOWN, BlockPredicate.solid(), 
            BlockPredicate.IS_AIR, 12
        ), 
        RandomOffsetPlacementModifier.vertically(
            ConstantIntProvider.create(1)
        ), 
        BiomePlacementModifier.of()
    );

    public static final RegistryEntry<PlacedFeature> BASALT_FEATURE_MARSHLAND = register("basalt_feature_marshland", 
        ThermorariumConfiguredFeatures.BASALT_FEATURE_MARSHLAND,
        overgrownValleyNBFPlacementModifiers(UniformIntProvider.create(6, 24))
    );

    public static final RegistryEntry<PlacedFeature> BASALT_SPIKES = register("basalt_spikes",
        ThermorariumConfiguredFeatures.BASALT_SPIKES,
        CountPlacementModifier.of(UniformIntProvider.create(5, 20)),
        SquarePlacementModifier.of(),
        BlockFilterPlacementModifier.of(
            BlockPredicate.matchingBlocks(new BlockPos(0, -1, 0), Blocks.BLACKSTONE)
        ),
        BiomePlacementModifier.of()
    );
}
