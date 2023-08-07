package pageFactories;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.AutomationHelper;

public abstract class diSearchDocDetailsMenusPageFactory extends diSearchBase {

	/**
	 * Constructor
	 * <p>
	 * Initializes elements on the page and then waits for the page to load. Asserts
	 * that it has loaded prior to proceeding.
	 * 
	 * @param regexURL
	 */
	public diSearchDocDetailsMenusPageFactory(String regexURL) {

		waitForPageToLoad();

		// Because each page factory extends this, we can initialize the elements here.
		PageFactory.initElements(driver, this);

		// Waits for a specified amount of time until the URL matches. After timeout,
		// assertion happens.
		new WebDriverWait(driver, Duration.ofSeconds(NORMAL_TIMEOUT)).until(ExpectedConditions.urlMatches(regexURL));

		Reporter.log("Expected URL: " + regexURL, true);
		Reporter.log("Current URL : " + this.getCurrentUrl(), true);

	}

	/**
	 * Clicks the Back arrow
	 */
	public void clickBackArrow() {
		AutomationHelper.printMethodName();

		WebElement backArrow = driver.findElement(By.xpath("//span[@class = 'search-back-icon']"));
		backArrow.click();
	}

	/**
	 * Utility method to click a menu link based on the passed in text.
	 * 
	 * @param menuLink
	 */
	private void clickMenuLink(String menuLink) {
		String xpath = "//div//span[text() = '" + menuLink + "'] | //div//div[text() = '" + menuLink + "']";
		WebElement menuLinkObject = driver.findElement(By.xpath(xpath));
		menuLinkObject.click();
	}

	/**
	 * Clicks the <b>Overview</b> link.
	 */
	public void clickOverview() {
		AutomationHelper.printMethodName();
		clickMenuLink("Overview");
	}

	/**
	 * Clicks the <b>Policies</b> link.
	 */
	public void clickPolicies() {
		AutomationHelper.printMethodName();
		clickMenuLink("Policies");
	}

	/**
	 * Clicks the <b>People</b> link.
	 */
	public void clickPeople() {
		AutomationHelper.printMethodName();
		clickMenuLink("People");
	}

	/**
	 * Clicks the <b>Technology</b> link.
	 */
	public void clickTechnology() {
		AutomationHelper.printMethodName();
		clickMenuLink("Technology");
	}

	/**
	 * Clicks the <b>Processes</b> link.
	 */
	public void clickProcesses() {
		AutomationHelper.printMethodName();
		clickMenuLink("Processes");
	}

	/**
	 * Clicks the <b>Other</b> link.
	 */
	public void clickOther() {
		AutomationHelper.printMethodName();
		clickMenuLink("Other");
	}

	/**
	 * Clicks the <b>Images</b> link.
	 */
	public void clickImages() {
		AutomationHelper.printMethodName();
		clickMenuLink("Images");
	}

	/**
	 * Clicks the <b>Tables</b> link.
	 */
	public void clickTables() {
		AutomationHelper.printMethodName();
		clickMenuLink("Tables");
	}

	/**
	 * Utility method that accepts a Menu Link text title and checks for the
	 * presence of that link on the page.
	 * 
	 * @param menuLink
	 * @return boolean
	 */
	private boolean isMenuLinkPresent(String menuLink) {
		String xpath = "//div//span[text() = '" + menuLink + "'] | //div//div[text() = '" + menuLink + "']";
		return AutomationHelper.isWebElementPresent(By.xpath(xpath));
	}

	/**
	 * Method to check for the presence of the <b>Overview</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isOverviewLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Overview");
	}
	
	/**
	 * Method to check for the presence of the <b>Policies</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isPoliciesLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Policies");
	}
	
	/**
	 * Method to check for the presence of the <b>People</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isPeopleLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("People");
	}
	
	/**
	 * Method to check for the presence of the <b>Technology</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isTechnologyLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Technology");
	}
	
	/**
	 * Method to check for the presence of the <b>Processes</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isProcessesLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Processes");
	}
	
	/**
	 * Method to check for the presence of the <b>Other</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isOtherLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Other");
	}
	
	/**
	 * Method to check for the presence of the <b>Images</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isImagesLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Images");
	}
	
	/**
	 * Method to check for the presence of the <b>Tables</b> link on the page.
	 * 
	 * @return boolean
	 */
	public boolean isTablesLinkPresent() {
		AutomationHelper.printMethodName();
		return isMenuLinkPresent("Tables");
	}

}
