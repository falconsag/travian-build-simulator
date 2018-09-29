package com.falconsag.travian.build.simulator;

import com.falconsag.travian.build.simulator.config.Configuration.FieldType;
import static com.falconsag.travian.build.simulator.config.Configuration.FieldType.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Village {

	private Map<Integer, Field> fields = new HashMap<>();
	private Layout layout = Layout._4446;
	private boolean bonusEnabled = false;
	private Hero hero = null;

	public Village(boolean bonusEnabled, Hero hero) {
		this(Layout._4446, bonusEnabled, hero);
	}

	public Village(Layout layout, boolean bonusEnabled, Hero hero) {
		this.bonusEnabled = bonusEnabled;
		this.layout = layout;
		this.hero = hero;
		reset();
	}

	public int getProductionOf(FieldType fieldType) {
		int production = getBaseProductionOfFieldType(fieldType);
		//TODO apply bonus buildings here

		//apply hero production
		if (hero != null) {
			production += hero.getProductionIncreaseForFieldType(fieldType);
		}
		if (bonusEnabled) {
			production = (int) Math.round(production * 1.25);
		}
		return production;
	}

	private int getBaseProductionOfFieldType(FieldType fieldType) {
		int sum = 0;
		for (Field value : fields.values()) {
			if (value.getFieldType().equals(fieldType)) {
				sum += value.getProduction();
			}
		}
		return sum;
	}

	void reset() {
		//initialize all fields to level 0, according to the layout that's set
		IntStream.range(1, 19).boxed().forEach(position -> {
			fields.put(position, new Field(layout.getFieldTypeAt(position), 0));
		});
	}

	//add levels of positions in order as it's on the field map in travian top-left to bottom right
	//in case of a 4446 and WOOD it's positions: 1,2,14,17
	public void setFieldLevels(FieldType fieldType, Integer... levels) {
		final List<Integer> positionsOfFieldType = layout.getPositionsOf(fieldType);
		if (positionsOfFieldType.size() != levels.length) {
			throw new RuntimeException("Unexpected numbers of levels to set for fieldtype");
		}
		for (int i = 0; i < levels.length; i++) {
			final Integer position = positionsOfFieldType.get(i);
			fields.put(position, new Field(layout.getFieldTypeAt(position), levels[i]));
		}
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public enum Layout {
		_4446(WOOD, CROP, WOOD, IRON, CLAY, CLAY, IRON, CROP, CROP, IRON, IRON, CROP, CROP, WOOD, CROP, CLAY, WOOD, CLAY);

		private FieldType[] fieldTypes;

		private Layout(FieldType... fieldTypesInOrder) {
			this.fieldTypes = fieldTypesInOrder;
		}

		public FieldType getFieldTypeAt(int pos) {
			return fieldTypes[pos - 1];
		}

		public List<Integer> getPositionsOf(FieldType specifiedFieldType) {
			return IntStream.range(0, fieldTypes.length)
					.filter(i -> fieldTypes[i].equals(specifiedFieldType))
					.boxed()
					//because as it's stored it starts from 0, and the way we need is 
					//starting from 1
					.map(index -> index + 1)
					.collect(Collectors.toList());
		}

	}

}
