package com.falconsag.travian.build.simulator.config;

public class LevelDetail {

	private int level;
	private int wood;
	private int clay;
	private int iron;
	private int crop;
	private int cp;
	private long baseBuildTime;
	private int baseProduction;

	public LevelDetail(int level) {
		this.level = level;
	}

	public LevelDetail setLevel(int level) {
		this.level = level;
		return this;
	}

	public LevelDetail setWood(int wood) {
		this.wood = wood;
		return this;
	}

	public LevelDetail setClay(int clay) {
		this.clay = clay;
		return this;
	}

	public LevelDetail setIron(int iron) {
		this.iron = iron;
		return this;
	}

	public LevelDetail setCrop(int crop) {
		this.crop = crop;
		return this;
	}

	public LevelDetail setCp(int cp) {
		this.cp = cp;
		return this;
	}

	public LevelDetail setBuildTime(long buildTime) {
		this.baseBuildTime = buildTime;
		return this;
	}

	public LevelDetail setProduction(int production) {
		this.baseProduction = production;
		return this;
	}

	public int getLevel() {
		return level;
	}

	public int getWood() {
		return wood;
	}

	public int getClay() {
		return clay;
	}

	public int getIron() {
		return iron;
	}

	public int getCrop() {
		return crop;
	}

	public int getCp() {
		return cp;
	}

	public long getBaseBuildTime() {
		return baseBuildTime;
	}

	public long getBuildTime(int mainBuildingLvl) {
		double notRounded = baseBuildTime * Math.pow(0.964, mainBuildingLvl - 1);
		return ((int) ((notRounded + 5) / 10)) * 10;
	}

	public int getBaseProduction() {
		return baseProduction;
	}

}
