package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.DocumentDetailsPageFactory;
import pageFactories.DocumentsPageFactory;
import pageFactories.SearchPageFactory;
import testCases.ModularTests.LoginMod;

/**
 * Test to perform a filter by selecting files that have been uploaded on a
 * specific date.
 * 
 * @author Jesse Childress
 *
 */
public class ChatWithDiSearchBot extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void chatWithDiSearchDocument() {

		// First, login
		// Modular Script for Login
		LoginMod login = new LoginMod();
		login.login(USER_NAME, PASSWORD);

		// Navigate straight to documents
		SearchPageFactory searchPF = new SearchPageFactory();
		searchPF.clickDocumentsSlideMenuLink();

		// Documents page
		DocumentsPageFactory documentsPF = new DocumentsPageFactory();

		// Reduce scope of searches by selecting SQA Testing domain
		documentsPF.getDomains().selectDomain("SQA Testing");
		documentsPF.getDomains().clickSearchDomains();

		// Select a document
		documentsPF.getDocumentsTable().clickLinkInRow("File Name(s)", "Aretec - Test A.pdf");

		// Ensure that the Overview page is present
		DocumentDetailsPageFactory docDetailsPF = new DocumentDetailsPageFactory();
		docDetailsPF.clickOverview();

		/*
		 * Start interacting with the chatbot
		 */

		// Set a message to ask the chatbot
		String searchMessage1 = "What is this document about?";
		docDetailsPF.getDiSearchBot().setMessage(searchMessage1);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readMessage(), searchMessage1,
				"Document Details - diSearch Chat Bot - Search Text Present");

		docDetailsPF.getDiSearchBot().clickSearchArrow();

		// Ensure question is in chat dialogue
		Reporter.log("ChatBot Question: " + docDetailsPF.getDiSearchBot().readLastQuestion(), true);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readLastQuestion(), searchMessage1,
				"Document Details - diSearch Chat Bot - Question Present");
		;

		// Ensure answer is in chat dialogue
		Reporter.log("ChatBot Answer: " + docDetailsPF.getDiSearchBot().readLastChatBotResponse(), true);
		Assert.assertTrue(docDetailsPF.getDiSearchBot().readLastChatBotResponse().length() > 10,
				"Document Details - diSearch Chat Bot - Chat Answer > 10 charaters");

		// Perform another search
		String searchMessage2 = "Can you explain it differently?";
		docDetailsPF.getDiSearchBot().setMessage(searchMessage2);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readMessage(), searchMessage2,
				"Document Details - diSearch Chat Bot - Search Text Present 2");

		docDetailsPF.getDiSearchBot().clickSearchArrow();

		// Ensure question is in chat dialogue
		Reporter.log("ChatBot Question: " + docDetailsPF.getDiSearchBot().readLastQuestion(), true);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readLastQuestion(), searchMessage2,
				"Document Details - diSearch Chat Bot - Question Present 2");
		;

		// Ensure answer is in chat dialogue
		Reporter.log("ChatBot Answer: " + docDetailsPF.getDiSearchBot().readLastChatBotResponse(), true);
		Assert.assertTrue(docDetailsPF.getDiSearchBot().readLastChatBotResponse().length() > 10,
				"Document Details - diSearch Chat Bot - Chat Answer > 10 charaters 2");

		
		
		// Lets have fun now
		String searchMessage3 = "Can you predict my future";
		String searchAnswer3 = "No, I cannot predict your future.";
		docDetailsPF.getDiSearchBot().setMessage(searchMessage3);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readMessage(), searchMessage3,
				"Document Details - diSearch Chat Bot - Search Text Present 3");

		docDetailsPF.getDiSearchBot().clickSearchArrow();

		// Ensure question is in chat dialogue

		Reporter.log("ChatBot Question: " + docDetailsPF.getDiSearchBot().readLastQuestion(), true);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readLastQuestion(), searchMessage3,
				"Document Details - diSearch Chat Bot - Question Present 3");
		;

		// Ensure answer is in chat dialogue
		searchAnswer3 = docDetailsPF.getDiSearchBot().readLastChatBotResponse();
		Reporter.log("ChatBot Answer: " + searchAnswer3, true);
		Assert.assertTrue(searchAnswer3.length() > 10,
				"Document Details - diSearch Chat Bot - Chat Answer > 10 charaters 3");

		
		
		
		
		
		// Lets have fun now - again
		String searchMessage4 = "Why not?";
		String searchAnswer4 = "I don't know.";
		docDetailsPF.getDiSearchBot().setMessage(searchMessage4);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readMessage(), searchMessage4,
				"Document Details - diSearch Chat Bot - Search Text Present 4");

		docDetailsPF.getDiSearchBot().clickSearchArrow();

		// Ensure question is in chat dialogue

		Reporter.log("ChatBot Question: " + docDetailsPF.getDiSearchBot().readLastQuestion(), true);
		Assert.assertEquals(docDetailsPF.getDiSearchBot().readLastQuestion(), searchMessage4,
				"Document Details - diSearch Chat Bot - Question Present 4");
		;

		// Ensure answer is in chat dialogue
		searchAnswer4 = docDetailsPF.getDiSearchBot().readLastChatBotResponse();
		Reporter.log("ChatBot Answer: " + searchAnswer4, true);
		Assert.assertTrue(searchAnswer4.length() > 10,
				"Document Details - diSearch Chat Bot - Chat Answer > 10 charaters 4");

		documentsPF.clickLogout();

	}

}