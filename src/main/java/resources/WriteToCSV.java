package resources;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVWriter;

public class WriteToCSV {

	public void writeToCSV(List list) throws IOException {
		
		//Get Property value
		Properties prop=new Properties();
		//FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		FileInputStream fis=new FileInputStream("config.properties");
		prop.load(fis);
		
		
		//Instantiating CSVWriter Class
		//String fileLoc=System.getProperty("user.dir")+"\\Output\\Result.csv";
		CSVWriter writer=new CSVWriter(new FileWriter(System.getProperty("user.dir")+prop.getProperty("CSVOutputPath")));
		
		//Data to be written: list
		
		//putting data to writer object
		writer.writeAll(list);
		
		//flushing data from writer to file
		writer.flush();
	}
	
	
}
