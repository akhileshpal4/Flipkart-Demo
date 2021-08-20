package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class WriteToJSON {
	
	
	public void writeToJSON(List list) throws IOException {
		
		//Get Property value
		Properties prop=new Properties();
		//FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		FileInputStream fis=new FileInputStream("config.properties");
		prop.load(fis);

		// Instantiating JSONObject and JSONArray class
		JSONObject jobj = new JSONObject();
		JSONObject jobj1 = new JSONObject();
		JSONArray jarr = new JSONArray();

		// Creating partial json file and putting it into json array.
		for (int i = 1; i < list.size(); i++) {
			// jobj.put(Array.get(list.get(i), 0), Array.get(list.get(i), 1));
			jobj1.put(Array.get(list.get(0), 0), Array.get(list.get(i), 0));
			jobj1.put(Array.get(list.get(0), 1), Array.get(list.get(i), 1));
			jarr.add(jobj1);

		}

		// Creating fianl json object
		jobj.put("Mobiles", jarr);
		
		

		// Writing to json value to output file
		//FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\Output\\Result.json");
		FileWriter file = new FileWriter(System.getProperty("user.dir") + prop.getProperty("JSONOutputPath"));
		file.write(jobj.toJSONString());
		file.close();

		 
		 
	}
	

}
