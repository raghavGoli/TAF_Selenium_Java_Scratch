package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.constants.Env;

public class PropertiesUtil {

	//read properties File
	
	public static String readPropertyFile(Env env,String propertyName) {
		
		File file =new File(System.getProperty("user.dir") +"/config/"+ env+ ".properties");
		FileReader fileReader = null;
		Properties properties =new Properties();
		try {
			fileReader = new FileReader(file);
			properties.load(fileReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 catch (IOException e) {
			e.printStackTrace();
		}
		String value=properties.getProperty(propertyName.toUpperCase());
		return value;
	}
}
