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

//		String searchResults = "Based on the context provided, there is no information suggesting how Aretec can help you save money on taxes. The information provided focuses on the company's expertise in cybersecurity, application development, and data analytics, as well as their experience working with various government agencies. There is no mention of tax-related services or strategies that would directly help you save money on taxes.";

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page
		SearchMod search = new SearchMod();
		search.search("How can Aretec save money on taxes?", "Proposals");

		// Search Results
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();
		Assert.assertTrue(searchResultsPF.readAnswer().length() > 100, "Search Page - Answer - Results returned over 100 characters.");
		
		
		
	
	}

}
