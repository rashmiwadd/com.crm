package com.crm.generic.fileutility;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	public String getDataFromJson(String key) throws FileNotFoundException, IOException, ParseException {

		// step 1: parse the Json Physical file int Java object using JsonParser class

		JSONParser parser = new JSONParser();
		FileReader FileR = new FileReader("./configAppData/appcommandata.json");
		Object obj = parser.parse(FileR);
		
		// Step :2 Convert java object in to JsonObject using down casting
		JSONObject map = (JSONObject) obj;
		
		// : get the value from json file using key
		String data = (String) map.get(key);
		return data;

	}
}
