package testCases.ModularTests;

import org.testng.Assert;
import org.testng.Reporter;

import pageFactories.SearchPageFactory;
import testCases.SearchBaseTestScriptConfig;

/**
 * Modular script to perform the search function on the Main search page.
 * 
 * @author Jesse Childress
 *
 */
public class SearchMod extends SearchBaseTestScriptConfig {

	/**
	 * This search method simply enters the passed in search terms and performs a
	 * search. It does NOT handle domains nor does it select the API to use. By default, it unselects all domains, which searches the entire app.
	 * 
	 * @param searchText
	 */
	public void search(String searchText) {

		Reporter.log("Beginning Search Modular Test..." + System.lineSeparator()
				+ "This is for the main search page from login...", true);

		SearchPageFactory searchPF = new SearchPageFactory();

		searchPF.unselectAllDomains();
		
		searchPF.setSearchField(searchText);

		// Ensure that the text is in the field
		Assert.assertEquals(searchPF.readSearchField(), searchText);

		searchPF.clickSearchMagnifyingGlass();
	}

	/**
	 * This search method performs a search with the passed in domain. It will populate the domains as passed in by the String array.
	 * 
	 * @param searchText the text that you'd like to search on
	 * @param Domain(s)    String array that contains domains. Case has to be correct
	 */
	public void search(String searchText, String... domains) {

		Reporter.log("Beginning Search Modular Test..." + System.lineSeparator()
				+ "This is for the main search page from login...", true);

		SearchPageFactory searchPF = new SearchPageFactory();
		
		//Unselect domains
		searchPF.unselectAllDomains();

		// Select the domains that should be used in the search
		searchPF.selectDomain(domains);

		// Set the search field.
		searchPF.setSearchField(searchText);

		// Ensure that the text is in the field
		Assert.assertEquals(searchPF.readSearchField(), searchText);

		searchPF.clickSearchMagnifyingGlass();
	}
}
