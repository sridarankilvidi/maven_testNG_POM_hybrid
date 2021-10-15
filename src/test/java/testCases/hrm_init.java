package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

//import pageObjects.hrm_login_page;

public class hrm_init {
	String appConfigPath = "C:\\sridaran\\Selenium-java\\eclipse-workspace\\Maven_TestNG_POM_Hybrid\\Properties\\config.properties";
	public static WebDriver driver;
	
	//public hrm_login_page lp;
		
	public String chromepath;
	public String edgepath;
	public String log4jpath;
	public String appurl;
	public String br;
	
	public static Logger log;
	
	public void init() throws IOException{
		// call the method to get the property object
		Properties prop =  readPropertiesFile(appConfigPath);
		chromepath = prop.getProperty("chromepath");
		edgepath = prop.getProperty("edgepath");
		log4jpath = prop.getProperty("log4jpath");
		appurl = prop.getProperty("appURL");
		br=prop.getProperty("browser");
		
		log= Logger.getLogger("HRM_Maven_testNG_Hybrid");
		PropertyConfigurator.configure(log4jpath); 
	}
	
	public void openBrowser() {
		if (br.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",chromepath );  
			driver = new ChromeDriver();
			log.info("Chrome Browser is used....");
		}else if(br.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", edgepath);  
	    	driver = new EdgeDriver();
	    	log.info("Edge Browser is used....");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	public void openApplication() {
		driver.get(appurl);
		log.info("HRM Applicaiton "+appurl+ " is Open now....");
	}
	 public Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }
	
}
