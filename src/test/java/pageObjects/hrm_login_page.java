package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class hrm_login_page {
	
	public WebDriver driver;
	
	//constructor to locate all the webelements and associate the
	//driver with the page by useing pagefactory class
	public hrm_login_page(WebDriver dr){
		this.driver = dr;
		PageFactory.initElements(dr,this);
	}
	
	/* method 1 to find the webelement
	By uname=By.id("txtUsername");
	By pwd=By.name("txtPassword");
	By btnLogin = By.cssSelector("#btnLogin");
	
	// method1 to enter username in the txt box
	 * public void setUsername(String un){
	 * driver.findElement(uname).sendkeys(un);
	*/
	
	/*// method 2
	@FindBy(how=How.ID, using="txtUsername")
	WebElement uname;
	*/
	
	// method 3
	@FindBy(id="txtUsername")
	WebElement uname;
	
	@FindBy(name="txtPassword")
	WebElement pwd;
	

	@FindBy(css="#btnLogin")
	WebElement btnLogin;
	
	public void setUsername(String un) {
		uname.sendKeys(un);
	}
	
	public void setPassword(String pass) {
		pwd.sendKeys(pass);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public void hrm_login(String un, String pwd) {
		setUsername(un);
		setPassword(pwd);
		clickLogin();
	}

}
