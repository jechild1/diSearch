package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.SearchResultsPageFactory;
import testCases.ModularTests.LoginMod;
import testCases.ModularTests.SearchMod;

/**
 * Test to ensure that the search results > References section is populated with data.
 * 
 * @author Jesse Childress
 *
 */
public class SearchResultsReferencesValidation extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 5)
	public void searchReferencesValidation() {

		Reporter.log("Beginning test for Search Results > References...", true);


		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page
		SearchMod search = new SearchMod();
		search.search("How can Aretec benefit from Software Quality Assurance Testing?", "SQA Testing");

		// Search Results
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();
		Assert.assertTrue(searchResultsPF.readAnswer().length() > 100, "Search Page - Results returned over 100 characters.");
		
		//The above readAnswer method forces the time for a wait for a bit.
		
		Assert.assertTrue(searchResultsPF.getReferences().readFileName().length() > 5, "Search Results Page > References > File Name Present");
//		Assert.assertTrue(searchResultsPF.getReferences().readUploadDate().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"), "Search Results Page > References > Date format xxxx-xx-xx");
		Assert.assertTrue(searchResultsPF.getReferences().readUploadDate().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9]{2}):([0-9]{2}):([0-9]{2}).([0-9]{6})"), "Search Results Page > References > Date format xxxx-xx-xx");

		Assert.assertTrue(searchResultsPF.getReferences().readReferenceDomains().length() > 5, "Search Results Page > References > Domain Text Present");
		Assert.assertTrue(searchResultsPF.getReferences().isReferenceDomainPresent("UI Interfaces"), "Search Results Page > References > Domain Present");
		
		Assert.assertTrue(searchResultsPF.getReferences().readReferencesText().length() > 50, "Search Results Page > References > Text");


		
	
	}

}
