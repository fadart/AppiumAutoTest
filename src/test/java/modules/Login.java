package modules;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Base.TestBase;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import util.TestUtils;

@SuppressWarnings("unused")
public class Login extends TestBase{
	TestUtils util = new TestUtils();


	@Test (priority = 1 ,description = "Valid Login Scenario with exisiting phoneNo and passcode.")
	public void ValidloginTest() throws  IOException, InterruptedException{
		// TODO Auto-generated method stub

		AndroidDriver<MobileElement> driver= Capabilities();

		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);


		TestUtils.testTitle("To verify that a user can login with a valid phoneNo and passcode.");
		TestUtils.testTitle2("User cliks on GO button");
		driver.findElement(By.id(OR.getProperty("GoButton_ID"))).click();
		System.out.println("go button clicked");

//		TestUtils.testTitle2("User clicks on phoneno field");
//		driver.findElement(By.id("android:id/text1")).click();
//		driver.findElement(By.id(OR.getProperty("phoneNoField_ID"))).clear();
//		System.out.println("text clicked");

		TestUtils.testTitle2("User enters phone no");
		driver.findElement(By.id(OR.getProperty("phoneNoField_ID"))).sendKeys(OR.getProperty("PhoneNo_Text"));
		System.out.println("Phone no passed into text field");

		TestUtils.testTitle2("continue button clicked");
		driver.findElement(By.id(OR.getProperty("ContinueBtn_ID"))).click();
		System.out.println("continue button clicked");

		TestUtils.testTitle2("Passcode entered into text field");
		driver.findElement(By.xpath(OR.getProperty("PasscodeField_XPATH"))).sendKeys(OR.getProperty("Passcode_Text"));
		System.out.println("Passcode entered into text field");

		TestUtils.testTitle2("OTP entered into text field");
		driver.findElement(By.xpath(OR.getProperty("OTPField_XPATH"))).sendKeys(OR.getProperty("OTP_TEXT"));
		System.out.println("OTP entered into text field");

		TestUtils.testTitle2("Enabled button clicked");
		driver.findElement(By.id(OR.getProperty("Enable_ID"))).click();
		System.out.println("Enabled button clicked");

		TestUtils.testTitle2("Allow button clicked");
		driver.findElement(By.id(OR.getProperty("allowBtn_ID"))).click();
		System.out.println("Allow button clicked");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		TestUtils.testTitle2("User is waiting for Trails");
		driver.findElement(By.id("com.eyowo.android.debug:id/go_fund_button")).click();
		System.out.println("User logged in successfully");

	}
	
	

	@Test (priority = 2, description = "Invalid Login Scenario with exisiting phoneNo and incorrect passcode.")
	public void InvalidloginTest() throws IOException, InterruptedException{
		// TODO Auto-generated method stub

		AndroidDriver<MobileElement> driver= Capabilities();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

		TestUtils.testTitle("To verify that a user cannot login when incorrect passcode is supplied.");
		TestUtils.testTitle2("User cliks on GO button");
		driver.findElement(By.id(OR.getProperty("GoButton_ID"))).click();
		System.out.println("go button clicked");

//		TestUtils.testTitle2("User clicks on phoneno field");
//		driver.findElement(By.id("android:id/text1")).click();
//		driver.findElement(By.id(OR.getProperty("phoneNoField_ID"))).clear();
//		System.out.println("text clicked");

		TestUtils.testTitle2("User enters phone no");
		driver.findElement(By.id(OR.getProperty("phoneNoField_ID"))).sendKeys(OR.getProperty("PhoneNo_Text"));
		System.out.println("Phone no passed into text field");

		TestUtils.testTitle2("continue button clicked");
		driver.findElement(By.id(OR.getProperty("ContinueBtn_ID"))).click();
		System.out.println("continue button clicked");

		TestUtils.testTitle2("Passcode entered into text field");
		driver.findElement(By.xpath(OR.getProperty("PasscodeField_XPATH"))).sendKeys(OR.getProperty("Passcode1_Text"));
		System.out.println("Passcode entered into text field");
		
		TestUtils.testTitle2("Error message is displayed");
		//This will capture error message
		String actual_msg= driver.findElement(By.id("com.eyowo.android.debug:id/snackbar_text")).getText();
		String expect="Password Provided is incorrect";
		AssertJUnit.assertEquals(actual_msg, expect);
		System.out.println("Error message validated");

		Thread.sleep(3000);


	}
	

}
