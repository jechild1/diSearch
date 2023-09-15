package testCases;

import java.io.File;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utilities.AutomationHelper;

public abstract class SearchBaseTestScriptConfig extends diConfiguration.SearchConfig {

	// TestNG documentation
	/**
	 * After class method that will run the clean up finish test methods at the end
	 * of all tests in a given class
	 */
	@AfterClass
	public void afterClass() {
		AutomationHelper.finishTest();
	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

		if (ITestResult.FAILURE == result.getStatus()) {
			ScreenshotOnFailure screenshot = new ScreenshotOnFailure();
			screenshot.captureScreenshot(driver,
					result.getName() + " - " + formatter.format(java.time.LocalDateTime.now()));
		}

		// Note: In some instances, we want to override this after method as to not quit
		// after each method, when doing multiple @Tests.
		driver.quit();

	}
	
	//Never could get this to work.
//	@BeforeMethod
//	public void showTestName(ITestResult result) {
//		Object driver = result.getTestContext().getAttribute("WebDriver");
////		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		
//		js.executeScript("""
//				var testWindow = window.open('','','width=100, height=100')
//				testWindow.document.write('<u>Test Name is:<u> ' + arguments[0])
//				testWindow.focus() setTimeout(() => testWindow.close(), arguments[1]*1000)""",
//				result.getMethod().getMethodName(), 5);
//	}

	/**
	 * Class to capture screenshots when an error occurs.
	 */
	public class ScreenshotOnFailure {
		public void captureScreenshot(WebDriver driver, String screenshotName) {

			try {
				TakesScreenshot ts = (TakesScreenshot) driver;

				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File(DEFAULT_FILE_PATH_FOR_SCREENSHOTS + screenshotName + ".png"));

				System.out.println("Screenshot taken");
			} catch (Exception e) {
				System.out.println("Exception while taking screenshot " + e.getMessage());
			}

		}
	}

}
