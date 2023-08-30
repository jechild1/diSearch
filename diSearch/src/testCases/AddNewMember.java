package testCases;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import pageFactories.MembersPageFactory;
import pageFactories.SearchPageFactory;
import pageFactories.SettingsPageFactory;
import testCases.ModularTests.LoginMod;

/**
 * Test to add a new member to the system. This test does not actually invite a
 * member at this time, but rather ensures that the form is present and that the
 * fields can be populated and read.
 * 
 * @author Jesse Childress
 *
 */
public class AddNewMember extends SearchBaseTestScriptConfig {

	@Test
	public void addNewMember() {
		Reporter.log("Beginning test for adding a new member", true);

		// Log into the system
		LoginMod login = new LoginMod();

		login.login(USER_NAME, PASSWORD);

		// Lands on main search page
		SearchPageFactory searchPF = new SearchPageFactory();

		// Clicks the Profile menu
		searchPF.clickProfileMenu();

		// Settings page in Admin menus
		SettingsPageFactory settingsPF = new SettingsPageFactory();
		settingsPF.clickMembersLeftLink();

		// Members Page
		MembersPageFactory membersPF = new MembersPageFactory();

		membersPF.clickInviteMembers();

		// Populate fields
		// Field Strings
		String name = "Test User";
		String email = "Test_User@gmail.com";
		String role = "Data Viewer";

		// Populate Names
		membersPF.setName(name);
		// Populate Email
		membersPF.setEmail(email);
		// Populate Role
		membersPF.selectRole(role);

		// Perform Asserts on fields being populated.
		Assert.assertEquals(membersPF.readName(), name, "New Member - Name field");
		Assert.assertEquals(membersPF.readEmail(), email, "New Member - Email Field");
		Assert.assertEquals(membersPF.readRole(), role, "New Member - Role");

		// Click cancel
		membersPF.clickCancel();

		// Log out.
		membersPF.clickLogout();

	}

}
