package pageFactories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
