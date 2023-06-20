package com.Base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import util.TestUtils;

public class TestBase {

	public static WebDriverWait wait;
//	private static AndroidDriver<MobileElement> driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static BufferedReader contents;
	public static InputStreamReader BufferedReader;
	public static InputStreamReader reader;
	public static ExtentReports reports;
	public static ExtentSparkReporter htmlReporter;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> testInfo = new ThreadLocal<ExtentTest>();
	private Object driver;


	@BeforeSuite
	public void setUp() throws Exception {

		htmlReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/htmlreport.html"));
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
	}
	

	@BeforeClass
	public AndroidDriver<MobileElement> Capabilities() throws IOException{

		File appDir = new File("src");
		File app = new File(appDir, "test/resources/Apps/app-debug.apk");

		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Emulator");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());//You'll give the path of your apk file
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.0");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2913);
		//cap.setCapability("isHeadless", true);

//		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.eyowo.android.debug");
//		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.eyowo.android.ui.splash.SplashActivity");

//		URL url = new URL("http://127.0.0.1:4723/wd/hub");

		AndroidDriver<MobileElement> driver = new AndroidDriver<MobileElement>(new URL ("http://127.0.0.1:4723/wd/hub"), cap);
//		driver = new AndroidDriver(url, cap);
		return driver;

		

	}
	@BeforeClass
	public void setUp2() {


	
		if (driver == null) {


			try {
				fis = new FileInputStream (System.getProperty("user.dir") + "/src/main/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//load properties file
		try {
			OR.load(fis);
			System.out.println("Config file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	



	@BeforeMethod(description = "fetch test cases name")
	public void register(Method method) {

		ExtentTest parent = reports.createTest(getClass().getName().replace(getClass().getPackage().getName()+".", ""));
		parentTest.set(parent);
		ExtentTest child = parentTest.get().createNode(method.getName());
		testInfo.set(child);
	}

	@AfterMethod(description = "to display the result after each test method")
	public void captureStatus(ITestResult result) throws IOException {
		
		if (result.getStatus() == ITestResult.FAILURE) {
//			String screenshotPath = TestUtils.addScreenshot();
//			testInfo.get().addScreenCaptureFromBase64String(screenshotPath);
			testInfo.get().fail(result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP)
			testInfo.get().skip(result.getThrowable());
		else
			testInfo.get().pass(result.getName() + " Test passed");
		reports.flush();
	}

}

