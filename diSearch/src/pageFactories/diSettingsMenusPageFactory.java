package pageFactories;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AutomationHelper;

/**
 * This class should be inherited to sub-classes that are in the Settings menu.
 * 
 * @author jesse.childress
 *
 */
public abstract class diSettingsMenusPageFactory extends diSearchMenusPageFactory {

	/**
	 * Constructor
	 * <p>
	 * Initializes elements on the page and then waits for the page to load. Asserts
	 * that it has loaded prior to proceeding.
	 * 
	 * @param regexURL
	 */
	public diSettingsMenusPageFactory(String regexURL) {

		super(regexURL);
		PageFactory.initElements(driver, this);

	}
	
	/**
	 * Utility method to read the page header of a given page. This is specific to Headings on Settings pages
	 * @return String
	 */
	public String readPageHeader() {
		AutomationHelper.printMethodName();
		String xpath = "//div[contains(@class , 'ant-tabs-tabpane-active')]//h1";
		WebElement header = driver.findElement(By.xpath(xpath));
		
		return AutomationHelper.getText(header);
	}
	
	
	@FindBy (xpath = "//div[text() = 'Settings']")
	WebElement settingsLeftLink;
	
	/**
	 * Clicks the <b>Settings</b> Left link on the Settings Page. 
	 */
	public void clickSettingsLeftLink() {
		AutomationHelper.printMethodName();
		settingsLeftLink.click();
	}
	
	/**
	 * Returns a boolean if the <b>Settings Left Link</b> is present.
	 * @return boolean
	 */
	public boolean isSettingsLeftLinkPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent("//div[text() = 'Settings']");
	}
	
	@FindBy (xpath = "//div[text() = 'Data Domains']")
	WebElement dataDomainsLeftLink;
	
	/**
	 * Clicks the <b>Data Domains</b> Left link on the Settings Page. 
	 */
	public void clickDataDomainsLeftLink() {
		AutomationHelper.printMethodName();
		dataDomainsLeftLink.click();
	}
	
	/**
	 * Returns a boolean if the <b>Data Domains Left Link</b> is present.
	 * @return boolean
	 */
	public boolean isDataDomainsLeftLinkPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent("//div[text() = 'Data Domains']");
	}
	
	@FindBy (xpath = "//div[text() = 'Members']")
	WebElement membersLeftLink;
	
	/**
	 * Clicks the <b>Members</b> Left link on the Settings Page. 
	 */
	public void clickMembersLeftLink() {
		AutomationHelper.printMethodName();
		membersLeftLink.click();
	}
	
	/**
	 * Returns a boolean if the <b>Members Left Link</b> is present.
	 * @return boolean
	 */
	public boolean isMembersLeftLinkPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent("//div[text() = 'Members']");
	}
	
	@FindBy (xpath = "//div[text() = 'Billing']")
	WebElement billingLeftLink;
	
	/**
	 * Clicks the <b>Billing</b> Left link on the Settings Page. 
	 */
	public void clickBillingLeftLink() {
		AutomationHelper.printMethodName();
		billingLeftLink.click();
	}
	
	/**
	 * Returns a boolean if the <b>Billing Left Link</b> is present.
	 * @return boolean
	 */
	public boolean isBillingLeftLinkPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent("//div[text() = 'Billing']");
	}
	
	
	
	

}
