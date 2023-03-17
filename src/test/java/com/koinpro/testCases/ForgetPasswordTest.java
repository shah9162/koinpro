package com.koinpro.testCases;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.koinpro.pageObjects.ForgetPasswordLocators;
import com.koinpro.pageObjects.LoginPage;

public class ForgetPasswordTest extends BaseClass{
	
	String email = randomEmail();
	
	@Test
	public void ForgetPassword_Test001() throws InterruptedException, IOException {
		ForgetPasswordLocators fp= new ForgetPasswordLocators(driver);
		
		//signUp a new user
		RegisterUser(email);
		
		//email Verification 
		verify_Newly_AddedCustomer(email);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		driver.navigate().to(baseURL);
		Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
//		logger.info("ulr is opened");
		fp.clickForgetLink();
//		logger.info("Forget password link clicked");
		fp.enterforgetemail(email);
//		logger.info("foget password email is provided");
		fp.clickGetLink();
//		logger.info("Get link is clecked");
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='cursorPointer']")));

		if(driver.getPageSource().contains("Reset Link send to your mail registered mail id.")) {
			System.out.println("link sent on registered mail id");
			}
		else {
			System.out.println("link sent on registered mail id");
		}
		
		driver.navigate().to("https://yopmail.com/");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		 WebElement emailbox= driver.findElement(By.id("login"));
	        emailbox.sendKeys(email);
	        emailbox.submit();
	        Thread.sleep(3000);
	        int size = driver.findElements(By.tagName("iframe")).size();
	        System.out.println("total frame : "+size);
	      
	       driver.switchTo().frame(2);
	       
	   List<WebElement> links=    driver.findElements(By.tagName("a"));
	   System.out.println(links.size());
	       
	       WebElement link= driver.findElement(By.xpath("//a[normalize-space()='Reset My Password']"));
	       if(link.isEnabled()) {
	     	  System.out.println("forget password link is opening");
	       }
	       else {
	     	  System.out.println("there is problem with forget password link");
	       }

	         // wait until the title of the page contains the specified text
	         WebDriverWait Exwait = new WebDriverWait(driver, Duration.ofSeconds(10));
	         Exwait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reset My Password")));
	         
	         link.click();
	         Thread.sleep(2000);
	         
	         //Enter new password 
	         driver.findElement(By.id("password")).sendKeys("Abc@12345");
	         driver.findElement(By.id("confirm_password")).sendKeys("Abc@12345");
	         driver.findElement(By.xpath("//button[@class='btn btn-block btn-primary btn-lg']")).click();
	         Thread.sleep(2000);
	         if(driver.getPageSource().contains("Password Reset")) {
	        	 System.out.println("password reset success Now login with current password");
	         }
	         else {
	        	 System.out.println("password reset failed");
	         }
	         
	         //Again login to the Account with new password
	         driver.navigate().to(baseURL);
	         driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	         LoginPage lp = new LoginPage(driver);
	 		driver.get(baseURL);
	 		lp.setUserName(email);
	 		lp.setPassword("Abc@12345");
	 		//		lp.eyeButton();
	 		lp.clicksignUp();

	 		Thread.sleep(2000);
	 	
	 		if (driver.getPageSource().contains("Logged In Successfully.")) {
	 			Assert.assertTrue(true);
	 			
	 		} else {
	 			Assert.assertTrue(false);
	 		}
	}
	
	
	@Test(enabled=false)
	public void ForgetPassword_Test002_Timecheck() throws InterruptedException, IOException {
		ForgetPasswordLocators fp= new ForgetPasswordLocators(driver);
		driver.get(baseURL);
//		logger.info("ulr is opened");
		fp.clickForgetLink();
//		logger.info("Forget password link clicked");
		fp.enterforgetemail("asdf23@yopmail.com");
//		logger.info("foget password email is provided");
		fp.clickGetLink();
//		logger.info("Get link is clecked");
		Thread.sleep(2000);
//		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
	if(driver.getPageSource().contains("Link recently sent, try after sometime.")) {
		Assert.assertTrue(true);
//		logger.info("Forget password passed");
	}
	else {
//	    logger.info("Forget password failed");
	    Assert.assertTrue(false);
	    }
		
	}
	
	
	@Test(enabled=false)
	public void ForgetPassword_Test003_NoUser() throws InterruptedException, IOException {
		ForgetPasswordLocators fp= new ForgetPasswordLocators(driver);
		driver.get(baseURL);
//		logger.info("ulr is opened");
		fp.clickForgetLink();
//		logger.info("Forget password link clicked");
		fp.enterforgetemail("mgdfddsd916288@gmail.com");
//		logger.info("foget password email is provided");
		fp.clickGetLink();
//		logger.info("Get link is clecked");
		Thread.sleep(2000);
//		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
	if(driver.getPageSource().contains("No user found with this email.")) {
		Assert.assertTrue(true);
//		logger.info("Forget password passed");
	}
	else {
//	    logger.info("Forget password failed");
	
	    Assert.assertTrue(false);
	    }
		
	}
	
	
	@Test(enabled=false)
	public void ForgetPassword_Test004_invalid_Email_Format() throws InterruptedException, IOException {
		ForgetPasswordLocators fp= new ForgetPasswordLocators(driver);
		driver.get(baseURL);
//		logger.info("ulr is opened");
		fp.clickForgetLink();
//		logger.info("Forget password link clicked");
		fp.enterforgetemail("mgdfddsd916288@gmail.123");
//		logger.info("foget password email is provided");
		fp.clickGetLink();
//		logger.info("Get link is clecked");
		Thread.sleep(2000);
//		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
	if(driver.getPageSource().contains("Email is Invalid.")) {
		Assert.assertTrue(true);
//		logger.info("Forget password passed");
	}
	else {
//	    logger.info("Forget password failed");
		
	    Assert.assertTrue(false);
	    }
		
	}
	
	
	@Test(enabled=false)
	public void ForgetPassword_Test005_ResendLink() throws InterruptedException, IOException {
		ForgetPasswordLocators fp= new ForgetPasswordLocators(driver);
		driver.get(baseURL);
//		logger.info("ulr is opened");
		fp.clickForgetLink();
//		logger.info("Forget password link clicked");
		fp.enterforgetemail("asdf23@yopmail.com");
//		logger.info("foget password email is provided");
		fp.clickResendLink();
//		logger.info("Get link is clecked");
		Thread.sleep(3000);
//		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
	if(driver.getPageSource().contains("Link recently sent, try after sometime.")) {
		Assert.assertTrue(true);
//		logger.info("Forget password passed");
	}
	else {
//	    logger.info("Forget password failed");
	
	    Assert.assertTrue(false);
	    }
		
	}

}
