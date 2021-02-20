package com.pratian.SauceDemo.Automation.Tests;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pratian.SauceDemo.Automation.FileHandling.PropertyManager;


public class BaseTest {

	protected WebDriver driver;


	public static ExtentReports extent;

	@BeforeSuite
	public void Setup() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("./Reports/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
			//create firefox instance


			System.setProperty("webdriver.gecko.driver", PropertyManager.getProperty("path.driver.gecko"));

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe

			System.setProperty("webdriver.chrome.driver", PropertyManager.getProperty("path.driver.chrome"));	
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		//Check if parameter passed as 'Edge'
		else if(browser.equalsIgnoreCase("Edge")){
			//set path to Edge.exe
			System.setProperty("webdriver.gecko.driver", PropertyManager.getProperty("path.driver.Ie"));

			//create Edge instance
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}



	@AfterMethod
	public void tearDown()
	{
		driver.close();

	}

	@AfterSuite
	public void extentFlush() {
		extent.flush();

	}
}