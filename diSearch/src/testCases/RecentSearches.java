package testCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.SearchPageFactory;
import pageFactories.SearchResultsPageFactory;
import testCases.ModularTests.LoginMod;
import testCases.ModularTests.SearchMod;

/**
 * Test to ensure that the recently searched text appears in the Recent Searches
 * section on the main page.
 * 
 * @author Jesse Childress
 *
 */
public class RecentSearches extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void validateRecentSearches() {

		Reporter.log("Beginning test for Recent search text...", true);

		// Create a string list of random searches
		List<String> searchTextList = new ArrayList<String>();
		searchTextList.add("How can I use software to help me do QA my application?");
		searchTextList.add("What are good skillsets to have if you want to be a QA tester?");
		searchTextList.add("What are the benefits of doing QA on a new application?");

		Random rand = new Random();
		int low = 1;
		int high = searchTextList.size();
		int indexToSearch = rand.nextInt(high - low) + low;
		String searchText = searchTextList.get(indexToSearch);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// Search Page
		SearchMod search = new SearchMod();
		search.search(searchText, "SQA Testing");

		// Search Results
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();
		
		Reporter.log("Search Text: " + searchText);
		Reporter.log("Search Results Answer: " + searchResultsPF.readAnswer());
		
		Assert.assertTrue(searchResultsPF.readAnswer().length() > 50,
				"Search Page - Results returned over 100 characters.");

		// Return back to home to ensure that the text is there
		searchResultsPF.clickHomeSlideMenuLink();

		SearchPageFactory searchPF = new SearchPageFactory();

		searchPF.isSearchTextInRecentSearches(searchText);
		
		Reporter.log("Current Search String: " + searchText, true);

		/*
		 * Check the login page to see if the file exists there
		 */
		Assert.assertEquals(searchPF.isSearchTextInRecentSearches(searchText.toLowerCase()), true, "Recently Searched text");
	}

}
