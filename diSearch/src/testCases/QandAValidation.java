package testCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.QandAPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;
import utilities.AutomationHelper;

/**
 * Test to perform a validation of features on the Q&A Page.
 * 
 * @author Jesse Childress
 *
 */
public class QandAValidation extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void qAndAValidation() {

		Reporter.log("Beginning test for Q & A Validation", true);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page

		SearchPageFactory searchPF = new SearchPageFactory();
		searchPF.clickQandASlideLink();
		
		//QA Page is displayed.
		QandAPageFactory qaPF = new QandAPageFactory();
		
		qaPF.setSearchField("This is my search text");
		System.out.println("Search Text: " + qaPF.readSearchField());
		
		qaPF.selectDomain("SQA Testing", "Health and Fitness");
		
		AutomationHelper.waitSeconds(5);

	}

}
