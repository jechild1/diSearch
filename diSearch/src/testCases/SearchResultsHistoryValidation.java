package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.SearchResultsPageFactory;
import testCases.ModularTests.LoginMod;
import testCases.ModularTests.SearchMod;

/**
 * Test ensure that the Search History on the Search Results page works
 * correctly. This will be accomplished by performing a search and then looking
 * at the History left hand menu to ensure that the search was logged.
 * 
 * @author Jesse Childress
 *
 */
public class SearchResultsHistoryValidation extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void searchResultsHistoryValidation() {

		Reporter.log("Beginning test for a Search Results > History", true);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// This test will be made up of two parts.
		// 1 - we will perform a normal search to get a baseline so that we can search
		// History
		// 2 - we will perform a second search, but then go back and find the original
		// search

		/*
		 * Step 1 Perform original search and store data
		 */
		String originalDomain = "SQA Testing";
		String originalSearchText = "what should I do to implement a software testing process at my company?";
		String originalAnswer;
		String originalChatGPTAnswer;

		// Use Search modular script
		SearchMod search = new SearchMod();
		search.search(originalSearchText, originalDomain);

		// User taken to the Search Results page
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();

		// Store the answer
		originalAnswer = searchResultsPF.readAnswer();

		// Store Chat GPT text
		originalChatGPTAnswer = searchResultsPF.getChatGPT().readAnswer();

		// Navigate back to the main Search Page
		searchResultsPF.clickHomeSlideMenuLink();

		/*
		 * Step 2 Perform a second step that will do a new search, but when we get to
		 * search results page, lets click on History and see if we can see our original
		 * again.
		 */

		String secondDomain = "SQA Testing";
		String secondSearchText = "How do I change transmission fluid in a 1965 shelby cobra?";

		// Get a new reference to the pages
		search = new SearchMod();
		search.search(secondSearchText, secondDomain);

		// Search results page is shown.
		searchResultsPF = new SearchResultsPageFactory();

		Assert.assertTrue(searchResultsPF.getHistory().isHistoryCardPresent(originalSearchText),
				"Search Results > History > Original Search text present.");

		searchResultsPF.getHistory().clickHistoryCard(originalSearchText);

		Reporter.log("Original Answer: " + originalAnswer, true);
		Reporter.log("Current Answer: " + searchResultsPF.readAnswer(), true);

		Assert.assertEquals(searchResultsPF.readAnswer(), originalAnswer,
				"Search Results > History > Original Answer Displayed");

		Reporter.log("Original Chat GPT Answer: " + originalChatGPTAnswer, true);
		Reporter.log("Current Chat GPT Answer: " + searchResultsPF.getChatGPT().readAnswer(), true);

		//TODO - Keeps erroring here and the ACTUAL is truncated
		Assert.assertEquals(searchResultsPF.getChatGPT().readAnswer(), originalChatGPTAnswer,
				"Search Results > History > Original Chat GPT Answer Displayed");

	}

}
