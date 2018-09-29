package com.falconsag.travian.build.simulator;

import static com.falconsag.travian.build.simulator.Hero.HeroProductionState.*;
import com.falconsag.travian.build.simulator.config.Configuration.FieldType;

public class Hero {

	public enum HeroProductionState {
		SHARED, WOOD, CLAY, IRON, CROP
	}

	private int pointsOnProduction;
	private HeroProductionState productionState;

	public Hero(int pointsOnProduction, HeroProductionState heroProductionState) {
		this.pointsOnProduction = pointsOnProduction;
		this.productionState = heroProductionState;
	}

	public int getPointsOnProduction() {
		return pointsOnProduction;
	}

	public HeroProductionState getProductionState() {
		return productionState;
	}

	/**
	 * Returns how much the hero increase the production of the given resource type
	 * considering hero's production state
	 * @param fieldType
	 * @return 
	 */
	public int getProductionIncreaseForFieldType(FieldType fieldType) {
		//if set to shared, it increases any type by shared multiplier
		if (productionState.equals(SHARED)) {
			return SHARED_PRODUCTION_MULTIPLIER * pointsOnProduction;
		} else if (productionState.toString().equals(fieldType.toString())) {
			return SINGLE_PRODUCTION_MULTIPLIER * pointsOnProduction;
		}
		return 0;
	}

	private static final int SHARED_PRODUCTION_MULTIPLIER = 18;
	private static final int SINGLE_PRODUCTION_MULTIPLIER = 60;

}
