package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadFromExcel {
	
	public ArrayList<String> getData(String fileName,String sheetName,String columnName) throws IOException {
		
		//Array List to store result
				ArrayList<String> al=new ArrayList<String>();
				
				//Creating object of XSSFWorkbook class
				FileInputStream fis=new FileInputStream(fileName);
				XSSFWorkbook wb=new XSSFWorkbook(fis);
				
				//To get the required sheet
				int sheetcount=wb.getNumberOfSheets();
				for(int i=0;i<sheetcount;i++) {
					
					if(wb.getSheetName(i).equalsIgnoreCase(sheetName)) {
						
						XSSFSheet sheet=wb.getSheetAt(i);
						
						//To go to required column header
						Iterator<Row> row=sheet.iterator();
						Row firstRow=row.next();
						
						Iterator<Cell> cell=firstRow.cellIterator();
						int column=0,k=0;
						while(cell.hasNext()) {
							Cell ce=cell.next();
							if(ce.getStringCellValue().equalsIgnoreCase(columnName)) {
								column=k;
							}
							k++;
						}
						
						//To get required data from selected column
						
						while(row.hasNext()) {
							Row r=row.next();
							Cell c=r.getCell(column);
							if(c.getCellType()==CellType.STRING) {
								al.add(c.getStringCellValue());
							}else if(c.getCellType()==CellType.NUMERIC) {
							al.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								
							}		
						}
					}	
				}
			return al;	
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//String fileName=System.getProperty("user.dir")+"\\search.xlsx";
		String fileName="search.xlsx";
		String sheetName="Price Range";
		String columnName="Max Range";
		
		ReadFromExcel rfe=new ReadFromExcel();
		ArrayList<String> a=rfe.getData(fileName, sheetName, columnName);
		System.out.println(a);
		
		

	}

}
