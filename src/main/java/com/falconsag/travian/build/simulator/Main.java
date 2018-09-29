package com.falconsag.travian.build.simulator;

import com.falconsag.travian.build.simulator.config.Configuration;
import static com.falconsag.travian.build.simulator.config.Configuration.FieldType.*;

public class Main {

	public static void main(String[] args) {
		try {
			Configuration.loadFieldConfigs();

			final Village village = new Village(true, new Hero(14, Hero.HeroProductionState.SHARED));
			village.setFieldLevels(WOOD, 8, 7, 6, 6);
			village.setFieldLevels(CLAY, 7, 7, 7, 7);
			village.setFieldLevels(IRON, 7, 7, 7, 6);
			village.setFieldLevels(CROP, 5, 5, 5, 4, 4, 5);

			System.out.println(village.getProductionOf(WOOD));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	//	one example usage
	//	Configuration.loadFieldConfigs();
	//
	//	final Village village = new Village(true, new Hero(14, Hero.HeroProductionState.SHARED));
	//	village.setFieldLevels(WOOD, 8, 7, 6, 6);
	//	village.setFieldLevels(CLAY, 7, 7, 7, 7);
	//	village.setFieldLevels(IRON, 7, 7, 7, 6);
	//	village.setFieldLevels(CROP, 5, 5, 5, 4, 4, 5);
	//
	//	System.out.println(village.getProductionOf(WOOD));
}
