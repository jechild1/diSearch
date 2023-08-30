package pageFactories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import utilities.AutomationHelper;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Members Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class MembersPageFactory extends diSettingsMenusPageFactory {

	// TODO - Still is settings for the moment. Page doesn't change
	public static String regexURL = BASE_URL + "settings";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public MembersPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}

	/**
	 * Clicks the Invite Members button
	 */
	public void clickInviteMembers() {
		AutomationHelper.printMethodName();
		WebElement inviteMembersButton = driver.findElement(By.xpath("//button/span[text() = 'Invite Members']"));
		inviteMembersButton.click();
	}

	/**
	 * Clicks the Invite button
	 */
	public void clickInvite() {
		AutomationHelper.printMethodName();
		WebElement inviteButton = driver.findElement(By.xpath("//button/span[text() = 'Invite']"));
		inviteButton.click();
	}

	/**
	 * Clicks the Cancel button
	 */
	public void clickCancel() {
		AutomationHelper.printMethodName();
		WebElement cancelButton = driver.findElement(By.xpath("//button/span[text() = 'Cancel']"));
		cancelButton.click();
	}

	/**
	 * Clicks the Members link
	 */
	public void clickMembers() {
		AutomationHelper.printMethodName();
		WebElement membersLink = driver
				.findElement(By.xpath("//div[@class = 'ant-tabs ant-tabs-top']//div[text() = 'Members']"));
		membersLink.click();
	}

	/**
	 * Clicks the New Members link. Note: This is only present when members are
	 * awaiting confirmation. Else, it disappears.
	 */
	public void clickNewMembers() {
		AutomationHelper.printMethodName();
		WebElement membersLink = driver
				.findElement(By.xpath("//div[@class = 'ant-tabs ant-tabs-top']//div[text() = 'New Members']"));
		membersLink.click();
	}

	/**
	 * Clicks the Remove button in a given member row.
	 * 
	 * @param emailToRemove
	 */
	public void clickRemove(String emailToRemove) {
		AutomationHelper.printMethodName();
		Reporter.log("Removing email: " + emailToRemove, true);

		String xpath = "//p[text() = '" + emailToRemove
				+ "']//ancestor::div[@class = 'name-detail']//span[text()='Remove']";

		WebElement removeButton = driver.findElement(By.xpath(xpath));

		removeButton.click();

	}

	@FindBy(id = "Invite_name")
	WebElement nameField;

	/**
	 * Sets the Name field with the passed in information
	 * 
	 * @param fullName
	 */
	public void setName(String fullName) {
		AutomationHelper.printMethodName();
//		WebElement nameField = driver.findElement(By.id("Invite_name"));
		AutomationHelper.setTextField(nameField, fullName);
	}

	/**
	 * Returns the text inside the Name field.
	 * 
	 * @return name
	 */
	public String readName() {
		AutomationHelper.printMethodName();
		return AutomationHelper.getText(nameField);
	}

	@FindBy(id = "Invite_email")
	WebElement emailField;

	/**
	 * Sets the Email field with the passed in information
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		AutomationHelper.printMethodName();
//		WebElement emailField = driver.findElement(By.id("Invite_email"));
		AutomationHelper.setTextField(emailField, email);
	}

	/**
	 * Reads the Email text inside the field.
	 * 
	 * @return email
	 */
	public String readEmail() {
		AutomationHelper.printMethodName();
		return AutomationHelper.getText(emailField);
	}

	/**
	 * Selects a value from the Role drop down.
	 * 
	 * @param role
	 */
	public void selectRole(String role) {

		AutomationHelper.printMethodName();

		WebElement inputField = driver.findElement(By.xpath("//div[@class='ant-select-selector']"));

		inputField.click();

		// There is a hidden drop down list that doesn't display until the arrow is
		// clicked
		// Wait on it to be present
		String xpathForDropDown = "//div[@class = 'rc-virtual-list-holder-inner']";
		AutomationHelper.waitForElementToBePresent(By.xpath(xpathForDropDown), 10);

		// Find a list of DIV's that live in the drop down
		List<WebElement> listOptions = driver.findElements(
				By.xpath(xpathForDropDown + "/div[contains(@class, 'ant-select-item ant-select-item-option')]"));

		// If we don't find a match, we need to let user know
		boolean matchFound = false;

		// Loop through the options in the drop down and check them for a match. Click
		// on the match.
		for (WebElement currentOption : listOptions) {
			String text = currentOption.getText();
			if (text.equalsIgnoreCase(role)) {
				matchFound = true;
				currentOption.click();
				break;
			}
		}

		if (!matchFound) {
			throw new ElementNotInteractableException("There is no such role of " + role + ".");
		}
	}

	/**
	 * Reads the currently selected option in the Role field.
	 * 
	 * @return String
	 */
	public String readRole() {
		AutomationHelper.printMethodName();

		WebElement roleField = driver
				.findElement(By.xpath("//input[@id = 'Invite_role']/parent::span/following-sibling::span"));
		String roleFieldText = AutomationHelper.getText(roleField);
		return roleFieldText;

	}

	/**
	 * Gets a reference to the Confirmation Modal class.
	 * 
	 * @return ConfirmationModal
	 */
	public ConfirmationModal getConfirmationModal() {
		return new ConfirmationModal();
	}

	/**
	 * Contains methods to interaction with objects in a confirmation modal.
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class ConfirmationModal {

		/**
		 * Clicks Yes on the Delete This User popup modal
		 */
		public void clickYesOnModal() {
			AutomationHelper.printMethodName();

			String modalXpath = "//div[@class = 'ant-popover-content']";
			// Ensure that the modal is open. Lets wait on it.
			AutomationHelper.waitForElementToBePresent(By.xpath(modalXpath), 5);

			// Perform the Click
			WebElement yesButton = driver
					.findElement(By.xpath("//div[@class = 'ant-popover-content']//button/span[text() = 'Yes']"));
			yesButton.click();

			// Wait on the modal to disappear
			AutomationHelper.waitForElementToNotBePresent(By.xpath(modalXpath), 5);

		}

		/**
		 * Clicks No on the Delete This User popup modal
		 */
		public void clickNoOnModal() {
			AutomationHelper.printMethodName();

			String modalXpath = "//div[@class = 'ant-popover-content']";
			// Ensure that the modal is open. Lets wait on it.
			AutomationHelper.waitForElementToBePresent(By.xpath(modalXpath), 5);

			// Perform the Click
			WebElement yesButton = driver
					.findElement(By.xpath("//div[@class = 'ant-popover-content']//button/span[text() = 'No']"));
			yesButton.click();

			// Wait on the modal to disappear
			AutomationHelper.waitForElementToNotBePresent(By.xpath(modalXpath), 5);

		}

	}

}
