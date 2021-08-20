package akhilesh;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.HomePage;
import resources.Base;
import resources.ReadFromExcel;
import resources.ScreenshotCapture;
import resources.WriteToCSV;
import resources.WriteToJSON;

public class flipkart extends Base{
	
	public WebDriver driver;
	List list =new ArrayList();
	HomePage hp;
	
	
	@BeforeTest
	public void initialize() throws IOException {
		driver=initializeDriver();
		
		//Launch Flipkar.com
		driver.get(prop.getProperty("url"));
		
		//Close login page
		 hp=new HomePage(driver);
		 hp.getCloseLoginPage().click();
	}
	
	@Test(dataProvider = "searchData")
	public void searchProduct(String partialSearchTxt,String completeSearchTxt) throws InterruptedException {

		hp.getSearchBox().sendKeys(partialSearchTxt);
	
		Thread.sleep(2000L);
		List<WebElement> Holder=hp.getAutoSuggest();
		for(WebElement e:Holder) {
			if(e.getAttribute("data-tkid").contains(completeSearchTxt.toLowerCase())) {
				e.click();
				break;
			}
		}
		
	}
	
	@DataProvider
	public Object[][] searchData() throws IOException {
		Object[][] data=new Object[1][2];
		
		ReadFromExcel rfe=new ReadFromExcel();
		
		data[0][0]=rfe.getData("search.xlsx", "Search Data", "Partial Search Value").get(0);
		data[0][1]=rfe.getData("search.xlsx", "Search Data", "Actual Search Value").get(0);
		return data;
	}
	
	@Test(dependsOnMethods ="applyFilter",dataProvider = "getPriceRange" )
	public void acceptPriceRange(String min, String max) throws InterruptedException {
		
		Thread.sleep(2000L);
	
		//Select Min value
		Select s1=new Select (hp.getMinPrice());
		s1.selectByValue(min);
		
		Thread.sleep(2000L);
		//Select max value
		Select s2=new Select (hp.getMaxPrice());
		s2.selectByValue(max);	
	}
	
	@DataProvider
	public Object[][] getPriceRange() throws IOException {
		Object[][] data=new Object[1][2];
		
		ReadFromExcel rfe=new ReadFromExcel();
		
		data[0][0]=rfe.getData("search.xlsx", "Price Range", "Min Range").get(0);
		data[0][1]=rfe.getData("search.xlsx", "Price Range", "Max Range").get(0);
		
		return data;
	}
	
	@Test(dependsOnMethods ="searchProduct",dataProvider = "getFilterData" )
	public void applyFilter(String dropdown,String checkbox) throws InterruptedException {
		
		Thread.sleep(2000L);
		
		List<WebElement> checkHolder=hp.getCheckBoxList();
		boolean flag=true;
		for(int i=0;i<checkHolder.size();i++) {
			WebElement e=checkHolder.get(i);
			if(e.getText().equalsIgnoreCase(checkbox)) {
				e.click();
				flag=false;
				break;
			}
		}
		
		if(flag) {
			
			List<WebElement> drop=hp.getFilterDropdown();
			for(WebElement e:drop) {
				if(e.getText().equalsIgnoreCase(dropdown)) {
					e.click();
					break;
				}
			}
			
			Thread.sleep(2000L);
			checkHolder=hp.getCheckBoxList();
			for(WebElement e:checkHolder) {
				if(e.getText().equalsIgnoreCase(checkbox)) {
					e.click();
					break;
				}
			}	
		}	
	}
	
	@DataProvider
	public Object[][] getFilterData() throws IOException {
		Object[][] data = new Object[2][2];
		
		ReadFromExcel rfe=new ReadFromExcel();
		ArrayList a1=rfe.getData("search.xlsx", "Filter Data", "Filter Category");
		ArrayList a2=rfe.getData("search.xlsx", "Filter Data", "Filter Value");
				
		data[0][0]=a1.get(0);
		data[0][1]=a2.get(0);
	
		data[1][0]=a1.get(1);
		data[1][1]=a2.get(1);
	
		return data;
	}
	
	
	@Test(dependsOnMethods = "applyFilter")
	public void processSearchResult() throws IOException, InterruptedException {
		
		Thread.sleep(3000L);
		
		//GetScreenshot and store result:
		List<WebElement> searchResults=hp.getSearchResult();
		List<WebElement> searchMobile=hp.getMobileNameSearch();
		List<WebElement> searchPrice=hp.getMobilePriceSearch();
		ScreenshotCapture sc=new ScreenshotCapture();

		String [] data= {"Phone Name","Current Price"};
		list.add(data);
		
		for(int i=0;i<searchResults.size();i++) {
		
			String mNaame=searchMobile.get(i).getText();
			String mPrice=searchPrice.get(i).getText();

			System.out.println(mNaame+"  "+mPrice);
			sc.captureScreenOfElement(mNaame, searchResults.get(i));
		
			String [] data1= {mNaame,mPrice};
			list.add(data1);

		}
		
			
	}
	
	
	@AfterTest
	public void storeResut() throws IOException {
		WriteToCSV wtc=new WriteToCSV();
		wtc.writeToCSV(list);
		
		WriteToJSON wtj=new WriteToJSON();
		wtj.writeToJSON(list);	
		
		driver.quit();
	}

}

