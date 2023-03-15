package com.koinpro.testCases;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.jcraft.jsch.Logger;
import com.koinpro.utilities.ExtentManager;
import com.koinpro.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	ReadConfig readconfig = new ReadConfig();
	
	public String baseURL=readconfig.getapplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getpassword();
	public String signUpURL= readconfig.getsignUpURL();
	
	public static WebDriver driver;
	public static ChromeOptions options = new ChromeOptions();

	
	
	public boolean isAlertPresent() {  //user defined method created to check alert present or not
		try {
			driver.switchTo().alert();
			return true;	
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void Max() {
		driver.manage().window().maximize();
		
	}
	
	
	public void UploadImage(String name) throws InterruptedException , FindFailed{
		String ImageFilepath="C:\\Test_Data\\";
		String InputFilepath="C:\\Test_Data\\";
		
		Screen s = new Screen();
		
		Pattern FileInputTextBox= new Pattern(ImageFilepath+"FileTestBox.PNG");
		Pattern OpenButton= new Pattern(ImageFilepath+"OpenButton.PNG");
		Thread.sleep(3000);
		
		s.wait(FileInputTextBox,10);
		s.type(FileInputTextBox,InputFilepath+name);
		s.click(OpenButton);
		
	}
	
	public void dropdown(WebElement dropDown,String str) {
		Select drop = new Select(dropDown);
		drop.selectByVisibleText(str);
		}
	
	public void checkImageStatus(String str) {

		driver.get(str);
		List<WebElement> links=driver.findElements(By.tagName("img"));
		System.out.println(links.size());

		
		try {
			for(int i=0;i<links.size();i++) {
				WebElement elements = links.get(i);
	    		String image=elements.getAttribute("src");
	    		URL url = new URL(image);
	    		HttpURLConnection httpURLConnect= (HttpURLConnection)url.openConnection();
	    		httpURLConnect.setConnectTimeout(5000);
	    		httpURLConnect.connect();
	    		
	    		int ResposeCode= httpURLConnect.getResponseCode();
	    		if(ResposeCode>=400) {
	    			System.out.println(image+" - "+ResposeCode+" - "+httpURLConnect.getResponseMessage()+" - is Broken image");
	    		}
	    		else {
	    			System.out.println(image+" - "+ResposeCode+" - "+httpURLConnect.getResponseMessage()+" - is active image");
	    		}
			}
		}
		catch(Exception e) {
			
		}
	}
	
	
	 @BeforeSuite
	public void BeforeSuite() throws IOException {
    	ExtentManager.setExtent();
    }
	
    @AfterSuite
	public void AfterSuite() {
    	
		ExtentManager.endreport();
	}
	

    @Parameters("browser")
	@BeforeMethod
	public void setUp(String br) {
		if(br.toLowerCase().equals("chrome")) {
//			System.setProperty("webdriver.chrome.driver", readconfig.getChromePath());
			WebDriverManager.chromedriver().setup();
			options.addArguments("--remote-allow-origins=*");
	        driver=new ChromeDriver(options);
	        System.out.println("Chrome driver lounch successfully");
	     
	       
	     }
		else if(br.toLowerCase().equals("firefox")) {
//			System.setProperty("webdriver.geco.driver",readconfig.)
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println("firefox driver lounch successfully");
			 
		}
		else if(br.toLowerCase().equals("edge")) {
//			System.setProperty("webdriver.ie.driver",readconfig.)
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			System.out.println("edge driver lounch successfully");
			 
       }
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		
	driver.quit();
	}
	
public static String Screenshot(WebDriver driver,String login) {
		
		Date currentdate = new Date();
		String exactdate = currentdate.toString().replace(" ","-").replace(":","-");
		
		File screenshotFile= ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//		String destination = ".//Screenshots/"+ login +exactdate +".png";
		
		String destination = System.getProperty("user.dir")+"\\Screenshot\\"+login+exactdate+".png";
		
		File finalDestination = new File(destination);
		
		try {
			FileUtils.copyFile(screenshotFile,finalDestination);
		} catch(Exception e) {
			e.getMessage();
		}
		
		return destination;
		
	}

public static String exactdate() {
	Date currentdate = new Date();
	String exactdateDate = currentdate.toString().replace(" ","-").replace(":","-");
	return exactdateDate;
}
	

}
