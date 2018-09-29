package com.falconsag.travian.build.simulator.config;

import java.util.HashMap;
import java.util.Map;

public class FieldConfig {

	private String name;
	private Map<Integer, LevelDetail> levels = new HashMap<>();

	public FieldConfig(String name) {
		this.name = name;
	}

	public void addLevelDetail(int level, LevelDetail levelDetail) {
		levels.put(level, levelDetail);
	}
	
	public LevelDetail atLevel(int lvl){
		return levels.get(lvl);
	}

}
