package com.falconsag.travian.build.simulator.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	public enum FieldType{
		WOOD,CLAY,IRON,CROP
	}
	
	private static final String FIELD_CONFIG_BASE_DIRECTORY = "/fieldConfigs";
	
	public static Map<FieldType, FieldConfig> FIELDS = new HashMap<>();

	private static LevelDetail getLevelDetalFromElements(int lvl, String[] elements) throws ParseException {
		LevelDetail lvlDetail = new LevelDetail(lvl);
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date reference = dateFormat.parse("00:00:00");
		Date date = dateFormat.parse(elements[10]);
		long seconds = (date.getTime() - reference.getTime()) / 1000L;

		lvlDetail.setWood(Integer.parseInt(elements[1]))
				.setClay(Integer.parseInt(elements[2]))
				.setIron(Integer.parseInt(elements[3]))
				.setCrop(Integer.parseInt(elements[4]))
				.setCp(Integer.parseInt(elements[9]))
				.setProduction(Integer.parseInt(elements[11]))
				.setBuildTime(seconds);
		return lvlDetail;
	}

	public static void loadFieldConfigs() throws URISyntaxException, FileNotFoundException, IOException, ParseException {
		final URL configFolderURL = Configuration.class.getResource(FIELD_CONFIG_BASE_DIRECTORY);
		final File fieldConfigFolder = new File(configFolderURL.toURI());

		File[] listOfFiles = fieldConfigFolder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile()) {
				final String fieldName = file.getName().split("\\.")[0];
				final FieldConfig fieldConfig = loadFieldFromSingleFile(file, fieldName);
				FIELDS.put(FieldType.valueOf(fieldName.toUpperCase()), fieldConfig);
			}
		}
	}

	private static FieldConfig loadFieldFromSingleFile(File file, String fieldName) throws NumberFormatException, IOException, ParseException {
		FieldConfig field = new FieldConfig(fieldName);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				final String[] elements = line.split(";");
				int lvl = Integer.parseInt(elements[0]);
				final LevelDetail levelDetalFromElements = getLevelDetalFromElements(lvl, elements);
				field.addLevelDetail(lvl, levelDetalFromElements);
			}
		}
		return field;
	}
}
