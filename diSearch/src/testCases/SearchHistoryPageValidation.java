package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.HistoryPageFactory;
import pageFactories.SearchResultsPageFactory;
import testCases.ModularTests.LoginMod;
import testCases.ModularTests.SearchMod;
import utilities.AutomationHelper;

/**
 * Test ensure that the Search History on the Search Results page works
 * correctly. This will be accomplished by performing a search and then looking
 * at the History left hand menu to ensure that the search was logged.
 * 
 * @author Jesse Childress
 *
 */
public class SearchHistoryPageValidation extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void searchHistoryPageValidation() {

		Reporter.log("Beginning test for History Page -> Previous Searches", true);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		// This test will be made up of two parts.
		
		// 1 - we will perform a normal search to get a baseline so that we can search
		// History


		/*
		 * Step 1 Perform original search and store data
		 */
//		String originalDomain = "SQA Testing";
		String originalSearchText = "How can I begin the implemtation of a new Software QA process?";
		String originalAnswer;
		String originalReferences;
		String originalChatGPTAnswer;

		// Use Search modular script
		SearchMod search = new SearchMod();
		
		//Searching all of the domains
		search.search(originalSearchText);

		// User taken to the Search Results page
		SearchResultsPageFactory searchResultsPF = new SearchResultsPageFactory();

		// Store the answer
		originalAnswer = searchResultsPF.readAnswer();
		
		//Store the References text
		originalReferences = searchResultsPF.getReferences().readReferencesText();

		// Store Chat GPT text
		originalChatGPTAnswer = searchResultsPF.getChatGPT().readAnswer();


		/*
		 * Step 2 Navigate to the Search History. Ensure that the top row is the row that we just searched
		 */
		// Navigate back to the main Search Page
		searchResultsPF.clickHistorySlideLink();
		
		
		HistoryPageFactory historyPF = new HistoryPageFactory();
		
		//Look at the top row. This should be the item were concerned about since we just did a search.
		AutomationHelper.waitSeconds(2);//TEMP - This causes the table to be populated
		Assert.assertEquals(historyPF.getSearchHistoryTable().readLastSearchText(), historyPF.getSearchHistoryTable().stringTruncator(originalSearchText),"Search History > Search Table > Search Text");
		
		Assert.assertEquals(historyPF.getSearchHistoryTable().readLastResponseText(), historyPF.getSearchHistoryTable().stringTruncator(originalAnswer),"Search History > Search Table > Search Response");

		Assert.assertEquals(historyPF.getSearchHistoryTable().readLastChatGPTAnswerText(), historyPF.getSearchHistoryTable().stringTruncator(originalChatGPTAnswer),"Search History > Search Table > ChatGPT Answer Text");

		//Click the top row in the history table
		historyPF.getSearchHistoryTable().clickLinkInRow("Search", originalSearchText);
		
		//The modal is opened
		//Assert that the text is in the modal for
		//Question, Answer, References, and ChatGPT Answer
		Assert.assertEquals(historyPF.getSearchDetailsModal().readQuestion(), originalSearchText, "Search History > Search Details > Search Question");
		
		Assert.assertEquals(historyPF.getSearchDetailsModal().readAnswer(),  originalAnswer, "Search History > Search Details > Search Answer");
		
		Assert.assertEquals(historyPF.getSearchDetailsModal().readReferences(),  originalReferences, "Search History > Search Details > Reference(s)");

		Assert.assertEquals(historyPF.getSearchDetailsModal().readChatGPTAnswer(),  originalChatGPTAnswer, "Search History > Search Details > ChatGPT Answer");

		
		
		
		System.out.println("Search Text: " + historyPF.getSearchHistoryTable().readLastSearchText());
		System.out.println("Response Text: " + historyPF.getSearchHistoryTable().readLastResponseText());
		System.out.println("ChatGPT Answer Text: " + historyPF.getSearchHistoryTable().readLastChatGPTAnswerText());
		System.out.println("Created On Text: " + historyPF.getSearchHistoryTable().readLastCreatedOnText());
		
		String searchText = "Write me a three paragraph document on why Software testing is beneficial for my software applications.";
		historyPF.getSearchHistoryTable().clickLinkInRow("Search", searchText);
		
		System.out.println(historyPF.getSearchDetailsModal().readAnswer());
		System.out.println(historyPF.getSearchDetailsModal().readReferences());
		System.out.println(historyPF.getSearchDetailsModal().readChatGPTAnswer());
		
		AutomationHelper.waitSeconds(10);

	}

}
