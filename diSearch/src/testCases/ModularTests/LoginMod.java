package testCases.ModularTests;

import org.testng.Assert;
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
public class LoginMod extends SearchBaseTestScriptConfig {

	/**
	 * Performs the function of logging into the system.
	 * 
	 * @param userName
	 * @param password
	 */
	public void login(String userName, String password) {

		Reporter.log("Beginning Login Modular Test...", true);
		
		LoginPageFactory loginPageFactory = new LoginPageFactory();

				
		loginPageFactory.loadPage();
		


		// set fields
		loginPageFactory.setEmail(userName);
		loginPageFactory.setPassword(password);

		Reporter.log("Email Address: " + loginPageFactory.readEmail(), true);
		
		Assert.assertEquals(loginPageFactory.readEmail(), userName, "Login Email");

		loginPageFactory.clickLogin();
		
		//After logging in, ensure that the Search page is loaded and ready to go
		SearchPageFactory searchPF = new SearchPageFactory();


	}
}
