package testCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.ConversationPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;
import utilities.AutomationHelper;

/**
 * Test to perform a validation of features on the Conversation Page.
 * 
 * @author Jesse Childress
 *
 */
public class ConversationValidation extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void conversationValidation() {

		Reporter.log("Beginning test for Conversation Validation", true);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page

		SearchPageFactory searchPF = new SearchPageFactory();
		searchPF.clickConversationSlideLink();
		
		//QA Page is displayed.
		ConversationPageFactory qaPF = new ConversationPageFactory();
		
		qaPF.setSearchField("This is my search text");
		System.out.println("Search Text: " + qaPF.readSearchField());
		
		qaPF.selectDomain("SQA Testing", "LMN");
		
		AutomationHelper.waitSeconds(5);

	}

}
