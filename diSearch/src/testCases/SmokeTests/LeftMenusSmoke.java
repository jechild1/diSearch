package testCases.SmokeTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageFactories.DocumentsPageFactory;
import pageFactories.SearchPageFactory;
import pageFactories.SearchResultsPageFactory;
import testCases.SearchBaseTestScriptConfig;
import testCases.ModularTests.LoginMod;
import testCases.ModularTests.SearchMod;

/**
 * Test to login to the system and to ensure that the left menus exist and are
 * clickable.
 * 
 * @author Jesse Childress
 *
 */
public class LeftMenusSmoke extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void leftMenusSmoke() {

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		SearchPageFactory spPF = new SearchPageFactory();

		// Perform a simple search
		spPF.unselectAllDomains();

		//Use a modular script to perform a search
		SearchMod search = new SearchMod();
		// A search is necessary to pull back the page.
		search.search("What is software testing?", "SQA Testing");


		SearchResultsPageFactory srPF = new SearchResultsPageFactory();
		srPF.clickDocumentsSlideMenuLink();
		DocumentsPageFactory documentsPF = new DocumentsPageFactory();


		/*
		 * Assert that the left links are present
		 */

		Assert.assertEquals(documentsPF.isDatePresent(), true, "Left Links - Evaluation Time");
		documentsPF.clickDate();

		Assert.assertEquals(documentsPF.isDocumentsPresent(), true, "Left Links - Document(s)");
		documentsPF.clickDocuments();

		Assert.assertEquals(documentsPF.isCategoryPresent(), true, "Left Links - Category");
		documentsPF.clickCategory();

		Assert.assertEquals(documentsPF.isDomainsPresent(), true, "Left Links - Domain(s)");
		documentsPF.clickDomains();

		Assert.assertEquals(documentsPF.isHistoryPresent(), true, "Left Links - History");
		documentsPF.clickHistory();

	}

}
