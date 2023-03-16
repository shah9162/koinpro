package com.koinpro.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.koinpro.pageObjects.ForgetPasswordLocators;

public class ForgetPasswordTest extends BaseClass{
	
	@Test
	public void ForgetPassword_Test001() throws InterruptedException, IOException {
		ForgetPasswordLocators fp= new ForgetPasswordLocators(driver);
		driver.get(baseURL);
//		logger.info("ulr is opened");
		fp.clickForgetLink();
//		logger.info("Forget password link clicked");
		fp.enterforgetemail("asdf23@yopmail.com");
//		logger.info("foget password email is provided");
		fp.clickGetLink();
//		logger.info("Get link is clecked");
		Thread.sleep(5000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("forgetPasswordlink")));

		if(driver.getPageSource().contains("Reset Link send to your mail registered mail id.")) {
		Assert.assertTrue(true);
//		logger.info("Forget password passed");
	}
	else {
//	    logger.info("Forget password failed");
		Assert.assertTrue(false);
	   }
		
	}
	
	
	@Test
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
	
	
	@Test
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
	
	
	@Test
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
	
	
	@Test
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
