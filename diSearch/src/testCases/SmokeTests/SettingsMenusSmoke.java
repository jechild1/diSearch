package testCases.SmokeTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageFactories.BillingPageFactory;
import pageFactories.DataContractsPageFactory;
import pageFactories.DataDomainsPageFactory;
import pageFactories.MembersPageFactory;
import pageFactories.SearchPageFactory;
import pageFactories.SettingsPageFactory;
import testCases.SearchBaseTestScriptConfig;
import testCases.ModularTests.LoginMod;

/**
 * Test to login to the system and to ensure that the left menus exist and are
 * clickable.
 * 
 * @author Jesse Childress
 *
 */
public class SettingsMenusSmoke extends SearchBaseTestScriptConfig {

	@Test(invocationCount = 1)
	public void SettingsMenuSmokeTest() {

		// Login to the system
		LoginMod loginMod = new LoginMod();
		loginMod.login(USER_NAME, PASSWORD);

		//Main Search Page displayed - Click Settings
		SearchPageFactory spPF = new SearchPageFactory();
		spPF.clickProfileMenu();
		
		//Settings Page
		SettingsPageFactory settingsPF = new SettingsPageFactory();
		
		//Check for the presence of left links on the Settings Page
		Assert.assertEquals(settingsPF.isSettingsLeftLinkPresent(), true, "Settings Page - Settings Left Link");
		Assert.assertEquals(settingsPF.isDataDomainsLeftLinkPresent(), true, "Settings Page - Data Domains Left Link");
		Assert.assertEquals(settingsPF.isMembersLeftLinkPresent(), true, "Settings Page - Members Left Link");
		Assert.assertEquals(settingsPF.isDataContractsLeftLinkPresent(), true, "Settings Page - Data Contracts Left Link");
		Assert.assertEquals(settingsPF.isBillingLeftLinkPresent(), true, "Settings Page - Billing Left Link");
		
		//Go to the Data Domains page
		settingsPF.clickDataDomainsLeftLink();
		DataDomainsPageFactory dataDomainsPF = new DataDomainsPageFactory();
		Assert.assertEquals(dataDomainsPF.readPageHeader(), "Data Domains", "Data Domains Page - Header Text");
		
		//Go back to the Settings Page
		dataDomainsPF.clickSettingsLeftLink();
		settingsPF = new SettingsPageFactory();
		Assert.assertEquals(settingsPF.readPageHeader(), "Settings", "Settings Page - Header Text");

		
		//Go to the Members Page
		settingsPF.clickMembersLeftLink();
		MembersPageFactory membersPF = new MembersPageFactory();
		Assert.assertEquals(membersPF.readPageHeader(), "Members", "Members Page - Header Text");
		
		//Go to the Data Contracts page
		membersPF.clickDataContractsLeftLink();
		DataContractsPageFactory dataContractsPF = new DataContractsPageFactory();
		Assert.assertEquals(dataContractsPF.readPageHeader(), "Data Contracts", "Data Contracts Page - Header Text");
		
				
		//Go to the Billing page
		dataContractsPF.clickBillingLeftLink();
		BillingPageFactory billingPF = new BillingPageFactory();
		Assert.assertEquals(billingPF.readPageHeader(), "Billing Under Development", "Billing Page - Header Text");
		
		//Logout
		billingPF.clickLogout();

	}

}
