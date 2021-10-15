package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class hrm_landingPage {

	public WebDriver driver;
	
	By welcome = By.xpath("//a[@id='welcome']");
	By linklogout = By.xpath("//div[@id='welcome-menu']/ul/li[3]/a");

	//constructor to locate all the webelements and associate the
	//driver with the page by useing pagefactory class
	
	public hrm_landingPage(WebDriver dr){
	this.driver = dr;
	PageFactory.initElements(dr,this);
	}
	// validate that the login is successful 
	public String getWelcomeMsg() {
		String msg = null;		
		try {
			msg = driver.findElement(welcome).getAttribute("id");			
		}catch(NoSuchElementException e) {
			e.printStackTrace();			
		}
		return msg;
	}
	
	public void logout() {
		driver.findElement(welcome).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.visibilityOf(driver.findElement(linklogout))); 
	    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(linklogout)));
		driver.findElement(linklogout).click();
	}
		
}
