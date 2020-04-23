package sampleDemo;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * Hello world!
 *
 */
public class GridDemo 
{

	ExtentReports report;
	ExtentTest logger;
		
			// TODO Auto-generated method stub

			private RemoteWebDriver driver;
			DesiredCapabilities caps= new DesiredCapabilities();
			
			@BeforeMethod
			@Parameters("browser")
			public  void intialization(String browser) throws Exception {
				report=new ExtentReports("D:\\AppTestifyDemo\\src\\main\\java\\Reports\\VideoReport.html");
				
				// Example test environment. NOTE: Gridlastic auto scaling requires all
				// these 3 environment variables in each request.
				// see test environments for capabilities to use https://www.gridlastic.com/test-environments.html
				
			
					 //required from selenium version 3.9.1 when testing with firefox or IE. Required when testing with Chrome 77+.
									
				
				if (browser.equalsIgnoreCase("chrome")){
					caps.setPlatform(Platform.WIN10);
					caps.setCapability("platformName", "windows");
					caps.setBrowserName("Chrome");
					caps.setVersion("latest");
					ChromeOptions options = new ChromeOptions();
					options.addArguments("disable-infobars"); // starting from Chrome 57 the info bar displays with "Chrome is being controlled by automated test software."
					options.addArguments(Arrays.asList("--start-maximized"));
					caps.setCapability(ChromeOptions.CAPABILITY, options);
					caps.setCapability("resolution", "1024x768");
					
					} 
				else if (browser.equalsIgnoreCase("FireFox")){
					caps.setPlatform(Platform.WIN10);
					caps.setCapability("platformName", "windows");
					DesiredCapabilities.firefox();
			        caps.setBrowserName("firefox");
					caps.setCapability("browser_version", "72.0");
                    
					} 
				
				else if (browser.equalsIgnoreCase("IE")){
					caps.setPlatform(Platform.WIN10);
					caps.setCapability("platformName", "windows");
					DesiredCapabilities.internetExplorer();
					caps.setBrowserName("internet explore");
					caps.setCapability("browser_version", "11");
					
				
					
					} 
				
				
				 else
				    {
					//If no browser passed throw exception
					throw new Exception("Browser is not correct");
				   }
			
					String record_video = "True";
					if (record_video.equalsIgnoreCase("True")) {
						caps.setCapability("video", "True"); // NOTE: "True" is a case sensitive string, not boolean.
					} else {
						caps.setCapability("video", "False"); // NOTE: "False" is a case sensitive string, not boolean.
					}
					
					driver = new RemoteWebDriver(new URL("http://Gsb18VMfZfjIYYwBkLx6jXdYQ71ux5i4:okVm5ysVDBtspQTArgwB4AqKxh5VdePr@AO2RQ52L.gridlastic.com:80/wd/hub"),caps);
					driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					
					if (record_video.equalsIgnoreCase("True")) {
						 
						System.out.println("Test Video: " + "http://s3-us-west-1.amazonaws.com/027a15f2-530d-31e5-f8cc-7ceaf6355377/fa0d1331-1dc7-8ff1-a72e-0e4ac2c91bb2/play.html?" + ((RemoteWebDriver) driver).getSessionId());
						
						String  Url="http://s3-us-west-1.amazonaws.com/027a15f2-530d-31e5-f8cc-7ceaf6355377/fa0d1331-1dc7-8ff1-a72e-0e4ac2c91bb2/play.html?" + ((RemoteWebDriver) driver).getSessionId();
						logger=report.startTest("Test Started");
								 
						  logger.log(LogStatus.INFO,Url);		
						 
					}
				
				
				
				
				
			}
			
							
	

			@Test(enabled = true)
			
			 public void test_site() throws Exception  { 
	
				
				  logger.log(LogStatus.INFO,"Launch The Url");
		          driver.get("https://www.google.com/ncr");
		        
		        if(driver.getTitle().equals("Google"))
		        {
		        	logger.log(LogStatus.PASS, "Navigated to the specified URL");
		        	Thread.sleep(10000); //slow down for demo purposes
			        WebElement element = driver.findElement(By.name("q"));
			        element.sendKeys("webdriver");
			        element.submit();
			        Thread.sleep(5000);
			        logger.log(LogStatus.INFO,"Test Complted");
			   	     
		        }
		        else
		        {
		        	
		        	
		        	logger.log(LogStatus.FAIL, "Title is not Maching with Url");
		        }
		        
		        		       
		     
			}

			@AfterMethod(alwaysRun = true)
			public void tearDown() throws Exception {
				
				   report.endTest(logger);
			        report.flush();
			        report.close();
				driver.quit();
			}
}
