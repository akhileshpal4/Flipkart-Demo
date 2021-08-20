package PageObject;

import java.sql.Driver;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}
	
	@FindBy(xpath = "//button[@class='_2KpZ6l _2doB4z']" )
	WebElement closeLoginPage;
	
	@FindBy(xpath = "//input[@title='Search for products, brands and more']")
	WebElement searchBox;
	
	@FindBy(xpath = "//*[@class='Y5N33s']/div")
	List<WebElement> autoSuggest;
	
	@FindBy(xpath = "(//select[@class='_2YxCDZ'])[1]")
	WebElement minPrice;
	
	@FindBy(xpath = "(//select[@class='_2YxCDZ'])[2]")
	WebElement maxPrice;
	

	@FindBy(xpath = "//*[@class='_3879cV']")
	List<WebElement> checkBoxList;
	

	@FindBy(xpath = "//*[@class='_2gmUFU _3V8rao']")
	List<WebElement> filterDropdown;
	
	
	@FindBy(xpath = "//*[@class='_13oc-S']")
	List<WebElement> searchResult;
	
	
	@FindBy(xpath = "//*[@class='_4rR01T']")
	List<WebElement> mobileNameSearch;
	
	
	@FindBy(xpath = "//*[@class='_30jeq3 _1_WHN1']")
	List<WebElement> mobilePriceSearch;

	
	
	
	
	public List<WebElement> getMobileNameSearch() {
		return (mobileNameSearch);
		
		
	}

	public List<WebElement> getMobilePriceSearch() {
		return mobilePriceSearch;
	}


	public List<WebElement> getSearchResult() {
		return searchResult;
	}

	public List<WebElement> getFilterDropdown() {
		return filterDropdown;
	}

	public List<WebElement> getCheckBoxList() {
		return checkBoxList;
	}

	public WebElement getCloseLoginPage() {
		return closeLoginPage;
	}
	
	public WebElement getSearchBox() {
		return searchBox;
	}
	
	public List<WebElement> getAutoSuggest() {
		return autoSuggest;
	}
	
	public WebElement getMinPrice() {
		return minPrice;
	}

	public WebElement getMaxPrice() {
		return maxPrice;
	}
}
