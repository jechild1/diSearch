package testCases;

import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.MembersPageFactory;
import pageFactories.SearchPageFactory;
import pageFactories.SettingsPageFactory;
import testCases.ModularTests.LoginMod;

/**
 * Test to add a new member to the system
 * 
 * @author Jesse Childress
 *
 */
public class AddNewMember extends SearchBaseTestScriptConfig {
	
	
	@Test
	public void addNewMember() {
		Reporter.log("Beginning test for adding a new member", true);
		
		//Log into the system
		LoginMod login = new LoginMod();
		
		login.login(USER_NAME, PASSWORD);
		
		//Lands on main search page
		SearchPageFactory searchPF = new SearchPageFactory();
		
		//Clicks the Profile menu
		searchPF.clickProfileMenu();
		
		//Settings page in Admin menus
		SettingsPageFactory settingsPF = new SettingsPageFactory();
		settingsPF.clickMembersLeftLink();
		
		//Members Page
		MembersPageFactory membersPF = new MembersPageFactory();
		
		membersPF.clickInviteMembers();
		
		
		

	}

}
