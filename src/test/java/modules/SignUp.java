package modules;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static util.TestUtils.generatePhoneNumber;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.Base.TestBase;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import util.TestUtils;

public class SignUp extends TestBase{
	TestUtils util = new TestUtils();


	@Test (priority = 1 ,description = "New user can register")
	public void SignUpTest() throws  IOException, InterruptedException{
		// TODO Auto-generated method stub

		AndroidDriver<MobileElement> driver= Capabilities();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);


		TestUtils.testTitle("To verify that a new user can register");
		TestUtils.testTitle2("User cliks on GO button");
		driver.findElement(By.id(OR.getProperty("GoButton_ID"))).click();
		System.out.println("go button clicked");

//		TestUtils.testTitle2("User clicks on phoneno field");
//		driver.findElement(By.id("android:id/text1")).click();
//		driver.findElement(By.id(OR.getProperty("phoneNoField_ID"))).clear();
//		System.out.println("text clicked");

		TestUtils.testTitle2("User enters phone no");
		driver.findElement(By.id(OR.getProperty("phoneNoField_ID"))).sendKeys(generatePhoneNumber());
		System.out.println("Phone no passed into text field");

		TestUtils.testTitle2("continue button clicked");
		driver.findElement(By.id(OR.getProperty("ContinueBtn_ID"))).click();
		System.out.println("continue button clicked");
		
		
		
	}
	
	
}