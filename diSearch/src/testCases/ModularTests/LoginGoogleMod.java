package testCases.ModularTests;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import pageFactories.LoginPageFactory;
import pageFactories.SearchPageFactory;
import testCases.SearchBaseTestScriptConfig;

/**
 * This is a modular test script that performs the login function
 * 
 * @author Jesse Childress
 *
 */

/*
 * 
 * 
 * NOTE: There is an error that happens when automated drivers attempt to
 * authenticate. After much research, i paused development of this until other
 * things could be completed. 05/23/2023
 * 
 * 
 * 
 * 
 */

public class LoginGoogleMod extends SearchBaseTestScriptConfig {

	/**
	 * Performs the function of logging into the system.
	 * 
	 * @param userName
	 * @param password
	 */
	public void login() {

		Reporter.log("Beginning Login Modular Test for Google...", true);

		LoginPageFactory loginPageFactory = new LoginPageFactory();

		loginPageFactory.loadPage();

		// set fields
//		loginPageFactory.setEmail(userName);
//		loginPageFactory.setPassword(password);
//
//		Reporter.log("Email Address: " + loginPageFactory.readEmail(), true);
//
//		loginPageFactory.clickLogin();

		// Capture original Window
		String originalWindow = driver.getWindowHandle();

		// Switch to old window to close
//		driver.switchTo().window(originalWindowHandle);
//		driver.close();

		// Close the other window.

		loginPageFactory.clickGoogleLogin();

		// After logging in, ensure that the Search page is loaded and ready to go

		// Switch to new window
//		String pageTitle = "Sign in - Google Accounts";
//		driver.switchTo().window(pageTitle);

		// Wait for the new window or tab to open
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		// Change to the new window or tab
		for (String windowHandle : driver.getWindowHandles()) {
			System.out.println("Window Name: " + windowHandle);
			System.out.println("Window Title: " + driver.getTitle());
			System.out.println("Current URL Contains: " + driver.getCurrentUrl().contains("accounts.google"));
			driver.switchTo().window(windowHandle);
//	        if (driver.getTitle().equals("New Window Title")) {
			if (driver.getCurrentUrl().contains("accounts.google")) {

				break;

			}
		}

		loginPageFactory.setGoogleIdentifierID("jesse.childress@gmail.com");
		loginPageFactory.clickNextButton();

		SearchPageFactory searchPF = new SearchPageFactory();

	}
}
