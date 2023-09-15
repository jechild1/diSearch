package testCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.DataDomainsPageFactory;
import pageFactories.SearchPageFactory;
import pageFactories.SettingsPageFactory;
import testCases.ModularTests.LoginMod;
import utilities.AutomationHelper;

/**
 * Test to assert the ability to both add and remove a domain in diSearch
 * 
 * @author Jesse Childress
 *
 */
public class AddAndRemoveDataDomain extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void dataDomainsAddAndRemove() {

		Reporter.log("Beginning test for a Data Domains | Add and Remove a domain", true);

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);
		
		//User lands on the main search page.
		SearchPageFactory searchPF = new SearchPageFactory();
		//Click on the Settings menu to get to Data Domains
		searchPF.clickProfileMenu();
		
		//Settings page is displayed
		SettingsPageFactory settingsPF = new SettingsPageFactory();
		
		settingsPF.clickDataDomainsLeftLink();
		
		//Data Domains page is displayed
		DataDomainsPageFactory dataDomainsPF = new DataDomainsPageFactory();
		
		//Set the fields
		dataDomainsPF.setDomain("Delete me");
		dataDomainsPF.setDescription("Delete me description");
		dataDomainsPF.selectParentDomain("SQA Testing");
		
		dataDomainsPF.clickAddDomain();
		
		AutomationHelper.waitForNotificationToDisappear("Domain Added!", 5, true);
		
		dataDomainsPF.getDomainsTable().clickDeleteInRow("Delete me");
		
		AutomationHelper.waitForNotificationToDisappear("Domain Deleted!", 10, true);
		
		dataDomainsPF.clickLogout();
		

	}

}
