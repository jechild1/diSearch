package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.ConversationPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;

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

		// QA Page is displayed.
		ConversationPageFactory conversationPF = new ConversationPageFactory();

		// Now on the Conversation page.

		// Ask a simple question.

		/*
		 * Perform some interactions with this chat bot and assert that we get some
		 * responses.
		 */

		/*
		 * First search
		 */
		String searchText = "Write me a three page document on why changing oil in my car is important.";

		// Set search text
		conversationPF.setSearchText(searchText);

		Reporter.log(" ", true);
		Reporter.log("Search 1 Text: " + conversationPF.readSearchText(), true);
		Reporter.log(" ", true);

		Assert.assertEquals(conversationPF.readSearchText(), searchText,
				"Conversations Page - First Question - Text entered in Search box");

		// Click the Send arrow to send the text for conversation. This method contains
		// the waits necessary to let the ChatGPT complete.
		conversationPF.clickSend();

		// Read the question that was asked from the Chat dialog
		Assert.assertEquals(conversationPF.readLastQuestionAsked(), searchText,
				"Conversations Page - First Question - Reading last question asked");

		Reporter.log(" ", true);
		Reporter.log("Last Question: " + conversationPF.readLastQuestionAsked(), true);
		Reporter.log(" ", true);

		// Read the response. We do not know what it will say, but we can ensure that it
		// contains text.
		Assert.assertTrue(conversationPF.readLastResponse().length() > 100,
				"Conversations Page - First Question - Response - Response > 100 characters");
		Reporter.log(" ", true);
		Reporter.log("Response: " + conversationPF.readLastResponse(), true);
		Reporter.log(" ", true);

		/*
		 * Second Search
		 */
		searchText = "What other maintenance should I do to my automobile?";

		// Set search text
		conversationPF.setSearchText(searchText);

		Reporter.log(" ", true);
		Reporter.log("Search Text: " + conversationPF.readSearchText(), true);
		Reporter.log(" ", true);

		Assert.assertEquals(conversationPF.readSearchText(), searchText,
				"Conversations Page - Second Question - Text entered in Search box");

		// Click the Send arrow to send the text for conversation. This method contains
		// the waits necessary to let the ChatGPT complete.
		conversationPF.clickSend();

		// Read the question that was asked from the Chat dialog
		Assert.assertEquals(conversationPF.readLastQuestionAsked(), searchText,
				"Conversations Page - Second Question - Reading last question asked");

		Reporter.log(" ", true);
		Reporter.log("Last Question: " + conversationPF.readLastQuestionAsked(), true);
		Reporter.log(" ", true);

		// Read the response. We do not know what it will say, but we can ensure that it
		// contains text.
		Assert.assertTrue(conversationPF.readLastResponse().length() > 100,
				"Conversations Page - Second Question - Response - Response > 100 characters");

		Reporter.log(" ", true);
		Reporter.log("Response: " + conversationPF.readLastResponse(), true);
		Reporter.log(" ", true);

		/*
		 * Third search
		 */
		searchText = "what can happen if I fail to do maintenance?";

		// Set search text
		conversationPF.setSearchText(searchText);

		Reporter.log(" ", true);
		Reporter.log("Search Text: " + conversationPF.readSearchText(), true);
		Reporter.log(" ", true);

		Assert.assertEquals(conversationPF.readSearchText(), searchText,
				"Conversations Page - Third Question - Text entered in Search box");

		// Click the Send arrow to send the text for conversation. This method contains
		// the waits necessary to let the ChatGPT complete.
		conversationPF.clickSend();

		// Read the question that was asked from the Chat dialog
		Assert.assertEquals(conversationPF.readLastQuestionAsked(), searchText,
				"Conversations Page - Third Question - Reading last question asked");

		Reporter.log(" ", true);
		Reporter.log("Last Question: " + conversationPF.readLastQuestionAsked(), true);
		Reporter.log(" ", true);

		// Read the response. We do not know what it will say, but we can ensure that it
		// contains text.
		Assert.assertTrue(conversationPF.readLastResponse().length() > 100,
				"Conversations Page - Third Question - Response - Response > 100 characters");

		Reporter.log(" ", true);
		Reporter.log("Response: " + conversationPF.readLastResponse(), true);
		Reporter.log(" ", true);

	}

}
