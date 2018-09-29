package com.falconsag.travian.build.simulator;

import com.falconsag.travian.build.simulator.config.Configuration;
import com.falconsag.travian.build.simulator.config.Configuration.FieldType;

public class Field {

	private FieldType fieldType;
	int level;

	public Field(FieldType fieldType, int level) {
		this.fieldType = fieldType;
		this.level = level;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public int getProduction() {
		if (level == 0) {
			//this is how it is
			return 8;
		}
		return Configuration.FIELDS.get(fieldType).atLevel(level).getBaseProduction();
	}

}
