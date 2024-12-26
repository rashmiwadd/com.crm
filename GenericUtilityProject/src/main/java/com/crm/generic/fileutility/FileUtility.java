package com.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	public String getDataFromProperties(String key) throws IOException {

		FileInputStream fis = new FileInputStream("./configAppData/commondata.properties");

		Properties pobj = new Properties();
		pobj.load(fis);

		String data = pobj.getProperty(key);

		return data;
	}

}
