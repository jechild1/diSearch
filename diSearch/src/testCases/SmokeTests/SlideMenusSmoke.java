package testCases.SmokeTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageFactories.DocumentsPageFactory;
import pageFactories.HistoryPageFactory;
import pageFactories.ConversationPageFactory;
import pageFactories.SearchPageFactory;
import testCases.SearchBaseTestScriptConfig;
import testCases.ModularTests.LoginMod;

/**
 * Test to login to the system and to ensure that the slide menus exist and are
 * clickable.
 * 
 * @author Jesse Childress
 *
 */
public class SlideMenusSmoke extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void TopMenusSmokeTest() {

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		SearchPageFactory spPF = new SearchPageFactory();

		// The Top menus are accessible without doing a search
		// Check for presence of links

		Assert.assertEquals(spPF.isHomeLinkPresent(), true, "Side Menus - Home Link");
		Assert.assertEquals(spPF.isDocumentsSlideLinkPresent(), true, "Side Menus - Documents Link");
		Assert.assertEquals(spPF.isConversationLinkPresent(), true, "Side Menus - Conversation Link");
		Assert.assertEquals(spPF.isHistoryPresent(), true, "Side Menus - History Link");
		Assert.assertEquals(spPF.isHelpSlideLinkPresent(), true, "Side Menus - Help Link");

		// Click through links

		// Click Documents
		spPF.clickDocumentsSlideMenuLink();
		DocumentsPageFactory documentsPF = new DocumentsPageFactory();

		// Click Home (Which is search page)
		documentsPF.clickHomeSlideMenuLink();
		spPF = new SearchPageFactory();

		// Click Conversations
		spPF.clickConversationSlideLink();
		ConversationPageFactory qaPF = new ConversationPageFactory();

		// Click History
		qaPF.clickHistorySlideLink();

		// Go back home
		HistoryPageFactory historyPF = new HistoryPageFactory();

		historyPF.clickHomeSlideMenuLink();

		// Logout
		spPF = new SearchPageFactory();

		spPF.clickLogout();
	}

}
