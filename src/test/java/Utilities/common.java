package Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class common {
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
				
	public void takescreenshot(WebDriver driver, String filename) throws IOException {
		String target = "C:\\sridaran\\Selenium-java\\eclipse-workspace\\Maven_TestNG_POM_Hybrid\\Screenshot\\";
		File dir = new File(target);
		File destination = new File(dir, filename);
		System.out.println(destination);
		//File destination = new File(target);
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// now copy the screenshot to desired location using copyFile //method
		Files.copy(src,destination);
	}

}
