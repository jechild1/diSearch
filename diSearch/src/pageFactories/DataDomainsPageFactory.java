package pageFactories;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.AutomationHelper;
import utilities.DiTables;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Data Domains Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class DataDomainsPageFactory extends diSettingsMenusPageFactory {

	// TODO - Still is settings for the moment. Page doesn't change
	public static String regexURL = BASE_URL + "settings";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public DataDomainsPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}

	@FindBy(id = "domain-form_domain")
	WebElement domainTextField;

	/**
	 * Sets the <b>Domain</b> text field with the passed in domain.
	 * 
	 * @param domain
	 */
	public void setDomain(String domain) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(domainTextField, domain);
	}

	/**
	 * Reads the value of the <b>Domain</b> text field.
	 * 
	 * @return String
	 */
	public String readDomain() {
		AutomationHelper.printMethodName();
		return AutomationHelper.getText(domainTextField);
	}

	@FindBy(id = "domain-form_description")
	WebElement descriptionTextField;

	/**
	 * Sets the <b>Description</b> text field with the passed in domain.
	 * 
	 * @param domain
	 */
	public void setDescription(String description) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(descriptionTextField, description);
	}

	/**
	 * Reads the value of the <b>Description</b> text field.
	 * 
	 * @return String
	 */
	public String readDescription() {
		AutomationHelper.printMethodName();
		return AutomationHelper.getText(descriptionTextField);
	}

	/**
	 * Selects a value from the Parent Domain drop down.
	 * 
	 * @param parentDomain
	 */
	public void selectParentDomain(String parentDomain) {

		AutomationHelper.printMethodName();

		WebElement inputField = driver.findElement(By.xpath("//input[@id = 'domain-form_parent_domain']"));

		inputField.click();

		// There is a hidden drop down list that doesn't display until the arrow is
		// clicked
		// Wait on it to be present
		String xpathForDropDown = "//div[@class = 'rc-virtual-list-holder-inner']";
		AutomationHelper.waitForElementToBePresent(By.xpath(xpathForDropDown), 10);

		// Finds the object for the ACTIVE item
		WebElement activeListItem = driver.findElement(By.xpath(xpathForDropDown
				+ "/div[@class='ant-select-item ant-select-item-option ant-select-item-option-active']"));

		AutomationHelper.hoverOverElement(activeListItem);

		// First element so we can know we came to the top of the list again.
		String topListItem = activeListItem.getText();
		boolean found = false;

		do {
			if (activeListItem.getText().equalsIgnoreCase(parentDomain)) {
				activeListItem.click();
				AutomationHelper.waitSeconds(1);
				found = true;
				break;

			} else {
				AutomationHelper.hitArrowDown();
				activeListItem = driver.findElement(By.xpath(xpathForDropDown
						+ "/div[@class='ant-select-item ant-select-item-option ant-select-item-option-active']"));
				AutomationHelper.hoverOverElement(activeListItem);
			}

		} while (!topListItem.equalsIgnoreCase(activeListItem.getText()));

		if (!found) {
			throw new ElementNotInteractableException("There is no such Parent Domain of " + parentDomain + ".");
		}

	}

	/**
	 * Reads the currently selected option in the Parent Domain field.
	 * 
	 * @return String
	 */
	public String readParentDomain() {
		AutomationHelper.printMethodName();

		WebElement parentDomainField = driver.findElement(
				By.xpath("//input[@id = 'domain-form_parent_domain']/parent::span/following-sibling::span"));
		String parentDomainFieldText = AutomationHelper.getText(parentDomainField);
		return parentDomainFieldText;
	}

	/**
	 * Clicks the <b>Add Domain</b> button.
	 */
	public void clickAddDomain() {
		AutomationHelper.printMethodName();
		WebElement addDomainButton = driver.findElement(By.xpath("//button/span[text() = 'Add Domain']"));
		addDomainButton.click();
		waitForPageToLoad();
	}
	
	/**
	 * Returns a reference to the Domains Table.
	 * 
	 * @return WebElement documentsTable
	 */
	public DiTables getDomainsTable() {

		//Xpath String of the table
		String xpath = "//table";

		return new DiTables(xpath);
	}
	
	

}
