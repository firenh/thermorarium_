package fireopal.thermorarium;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fireopal.thermorarium.biome.ThermorariumBiomes;
import fireopal.thermorarium.features.ThermorariumConfiguredFeatures;
import fireopal.thermorarium.features.ThermorariumPlacedFeatures;
import fireopal.thermorarium.gen.ThermorariumBiomeAdditions;
import fireopal.thermorarium.util.MultinoiseCommand;

public class Thermorarium implements ModInitializer {
	private static final String MODID = "thermorarium";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Identifier id(String id) {
		return new Identifier(MODID, id);
	}

	@Override
	public void onInitialize() {
		ThermorariumConfiguredFeatures.init();
		ThermorariumPlacedFeatures.init();
		ThermorariumBiomes.init();
		ThermorariumBiomeAdditions.init();
		MultinoiseCommand.register();

		// LOGGER.info("Things are working: " + NetherBiomes.canGenerateInNether(ThermorariumBiomeKeys.BLACKSTONE_DELTAS) );
	}
}
