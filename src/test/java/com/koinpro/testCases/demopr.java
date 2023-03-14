package com.koinpro.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demopr {
	
	public static void main (String[]args) {
//		WebDriverManager.firefoxdriver().setup();
		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new FirefoxDriver();
		
		ChromeOptions options = new ChromeOptions();
//		System.setProperty("webdriver.chrome.driver","C:\\Users\\Mayank Morya\\eclipse-workspace\\koinpro\\Drivers\\chromedriver.exe");
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://koinpro.io/portal/website/web");
		
		
	}

}
