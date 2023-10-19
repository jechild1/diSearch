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
public class SearchResultsAnswer extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void searchAnswerValidation() {

		Reporter.log("Beginning test for a Search Results > Answer...", true);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page
		SearchMod search = new SearchMod();
		search.search("What can I do to set up a new SQA process for my company?", "SQA Testing");

		// Search Results
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();
		Assert.assertTrue(searchResultsPF.readAnswer().length() > 100, "Search Page - Answer - Results returned over 100 characters.");
		
		
		
	
	}

}
