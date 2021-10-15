package testCases;

import org.testng.annotations.Test;

import Utilities.XLUtils;
import Utilities.common;
import pageObjects.hrm_landingPage;
import pageObjects.hrm_login_page;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Test_loginPage extends hrm_init{
	
	public hrm_login_page lp ;
	public hrm_landingPage landP;
	public common comfunc = new common();
	
	@BeforeClass
	  public void InitEnviron() throws IOException {
		init();
		openBrowser();
		openApplication();
		lp = new hrm_login_page(driver);
	  }

	  @AfterClass
	  public void teardownEnviron() {
		  driver.quit();
	  }

	  @Test(enabled=true)
		 public void TC001_login() throws IOException {
		  long id = Thread.currentThread().getId();
		  System.out.println("TC001_login: " + id);
		  lp.hrm_login("admin","admin123");
		  if (validate_Login()) {
				landP.logout();
			}
	  }
	
	
	 public boolean validate_Login() throws IOException {
		String msg;
		boolean sucess=true;
		
		//System.out.println("after login, verify msg method is called");
		landP = new hrm_landingPage(driver);
		msg = landP.getWelcomeMsg();
		if ( msg != null) {
			//System.out.println("the message from webelement is: "+msg);
			log.info("Login is successful!");
			Assert.assertTrue(msg.equalsIgnoreCase("welcome"));
		}else {
			log.info("Login has failed! Closing the browser now..");
			comfunc.takescreenshot(driver,"hrm_login.png");
			sucess = false;
			//driver.quit();
			Assert.assertTrue(false);
		}		
		return sucess; 
	  }
	  
	  
	// another method to get data from excel and test login in the loop
		@Test(dataProvider = "hrmLoginData", enabled=true)
		public void TC003_DDTlogin(String un, String pwd) throws InterruptedException, IOException {
			long id = Thread.currentThread().getId();
			System.out.println("TC003_DDTlogin: " + id);
			lp.hrm_login(un,pwd);
			if (validate_Login()) {
				landP.logout();
			}
		}// exit condition

	  @DataProvider
	  public Object[][] hrmLoginData() throws IOException {
	  	String path="C:\\sridaran\\Selenium-java\\eclipse-workspace\\Maven_TestNG_POM_Hybrid\\src\\test\\resources\\hrm.xlsx";
	    int rownum=XLUtils.getRowCount(path, "hrm_login");
		int colcount=XLUtils.getCellCount(path,"hrm_login",1);
		
		Object[][] logdata =new Object[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)// 1,
		{
			for(int j=0;j<colcount;j++)
			{
				logdata[i-1][j]=XLUtils.getCellData(path,"hrm_login", i,j);//1 0
			}
				
		}
		return logdata;
	  }

	 
		 
	/*	 
  @Test(dataProvider = "dp")
  public void f(Integer n, String s) {
  }

  @DataProvider
  public Object[][] dp() {
    return new Object[][] {
      new Object[] { 1, "a" },
      new Object[] { 2, "b" },
    };
  }
  @BeforeMethod
  public void beforeMethod() {
  }

  @AfterMethod
  public void afterMethod() {
  }
  
  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

  @BeforeSuite
  public void beforeSuite() {
  }

  @AfterSuite
  public void afterSuite() {
  }
	*/
	  
}
