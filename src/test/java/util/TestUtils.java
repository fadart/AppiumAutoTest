package util;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;

import com.Base.TestBase;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

/**
 * @author fatimah
 *
 */
public class TestUtils extends TestBase {
	/**
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 * @description to take a screenshot
	 */
	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/TestsScreenshots/" + screenshotName + dateName + ".png";

		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	

	private static WebDriver driver() {
		// TODO Auto-generated method stub
		return null;
	}

	

	/**
	 * @return number
	 * @description to generate a 11 digit number.
	 */
	public static String generatePhoneNumber() {

		long y = (long) (Math.random() * 100000 + 0333330000L);
		String Surfix = "080";
		String num = Long.toString(y);
		String number = Surfix + num;
		return number;

	}

	
	public static String addScreenshot() {

		TakesScreenshot ts = (TakesScreenshot) driver();
		File scrFile = ts.getScreenshotAs(OutputType.FILE);

		String encodedBase64 = null;
		FileInputStream fileInputStreamReader = null;
		try {
			fileInputStreamReader = new FileInputStream(scrFile);
			byte[] bytes = new byte[(int) scrFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "data:image/png;base64," + encodedBase64;
	}
	
	

	@Test
	public static void testTitle(String phrase) {
		String word = "<b>" + phrase + "</b>";
		Markup w = MarkupHelper.createLabel(word, ExtentColor.BLACK);
		testInfo.get().info(w);
	}
	@Test
	public static void testTitle2(String phrase) {
		String word = "<b>" + phrase + "</b>";
		Markup w = MarkupHelper.createLabel(word, ExtentColor.BLUE);
		testInfo.get().info(w);
	}
	
	
	

}
