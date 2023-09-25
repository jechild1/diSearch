package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.SearchResultsPageFactory;
import testCases.ModularTests.LoginMod;
import testCases.ModularTests.SearchMod;

/**
 * Test to perform a simple search.
 * 
 * @author Jesse Childress
 *
 */
public class SearchResultsChatGPT extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void searchResultsChatGPTValidation() {

		Reporter.log("Beginning test for a Search Results > Chat GPT Answer", true);


		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page
		SearchMod search = new SearchMod();
		search.search("Give me a bulleted list of how Aretec can help my company solve my IT needs", "SQA Testing");

		// Search Results
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();
		Assert.assertTrue(searchResultsPF.getChatGPT().readAnswer().length() > 100, "Search Page - Chat GPT Answer - Results returned over 100 characters.");
		Reporter.log("Chat GPT Response: " + searchResultsPF.getChatGPT().readAnswer(), true);

	
	}

}
