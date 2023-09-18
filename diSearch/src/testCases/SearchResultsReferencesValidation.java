package testCases;

import java.util.regex.Pattern;

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

	@Test(invocationCount = 1)
	public void searchReferencesValidation() {

		Reporter.log("Beginning test for Search Results > References...", true);


		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page
		SearchMod search = new SearchMod();
		search.search("How can Aretec benefit from Software Quality Assurance Testing?");

		// Search Results
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();
		Assert.assertTrue(searchResultsPF.readAnswer().length() > 100, "Search Page - Results returned over 100 characters.");
		
		//The above readAnswer method forces the time for a wait for a bit.
		
		Assert.assertTrue(searchResultsPF.getReferences().readFileName().length() > 5, "Search Results Page > References > File Name Present");
//		Assert.assertTrue(searchResultsPF.getReferences().readUploadDate().matches("([0-9]{4})-([0-9]{2})-([0-9]{2})"), "Search Results Page > References > Date format xxxx-xx-xx");
		Pattern p = Pattern.compile(".*([0-9]{4})-([0-9]{2})-([0-9]{2}) ([0-9]{2}):([0-9]{2}) GMT.*");

		Assert.assertTrue(p.matcher(searchResultsPF.getReferences().readUploadDate()).matches(), "Search Results Page > References > Date format xxxx-xx-xx tt:tt GMT");

		Assert.assertTrue(searchResultsPF.getReferences().readReferenceDomains().length() > 5, "Search Results Page > References > Domain Text Present");
//		Assert.assertTrue(searchResultsPF.getReferences().isReferenceDomainPresent("Automation Testing"), "Search Results Page > References > Domain Present");
		
		Assert.assertTrue(searchResultsPF.getReferences().readReferencesText().length() > 50, "Search Results Page > References > Text");


		
	
	}

}
