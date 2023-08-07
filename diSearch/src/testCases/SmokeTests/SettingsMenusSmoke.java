package testCases.SmokeTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageFactories.BillingPageFactory;
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
		MembersPageFactory membersPageFactory = new MembersPageFactory();
		Assert.assertEquals(membersPageFactory.readPageHeader(), "Members", "Members Page - Header Text");
		
		
		
		//Go to the Billing page
		membersPageFactory.clickBillingLeftLink();
		BillingPageFactory billingPageFactory = new BillingPageFactory();
		Assert.assertEquals(billingPageFactory.readPageHeader(), "Billing Under Development", "Billing Page - Header Text");
		
		//Logout
		billingPageFactory.clickLogout();

		



	}

}
