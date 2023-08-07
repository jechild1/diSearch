package pageFactories;

import java.time.DateTimeException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AutomationHelper;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Q&A Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class QandAPageFactory extends diSearchMenusPageFactory {

	public static String regexURL = BASE_URL + "q-and-a";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public QandAPageFactory() {
		super(regexURL);
		waitForPageToLoad();
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@class = 'ant-input ant-input-lg ant-input-borderless']")
	WebElement searchField;

	/**
	 * Sets the Search text box with the passed in text.
	 * 
	 * @param searchText
	 */
	public void setSearchField(String searchText) {
		AutomationHelper.printMethodName();

		AutomationHelper.setTextField(searchField, searchText);
	}

	/**
	 * Reads the value of the Search field
	 * 
	 * @return String
	 */
	public String readSearchField() {
		AutomationHelper.printMethodName();
		return AutomationHelper.getText(searchField);
	}

	/*
	 * Domains Section
	 */
	/**
	 * Clicks the All Domain(s) button to the left of the Search field.
	 */
	private void clickAllDomainsButton() {
		AutomationHelper.printMethodName();
		WebElement allDomainsButton = driver.findElement(By.xpath("//button/span[text() = 'All Domain(s) ']"));
		allDomainsButton.click();
	}

	/**
	 * Utility method to open all of the Closed Domains. This means clicking the
	 * triangle to expand the list. This needs to happen because until the list is
	 * expanded, the object properties are not predictable.
	 */
	private void openClosedDomains() {

		AutomationHelper.printMethodName();

		// Adjust time outs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		// Grab a list of all of the triangle expanders
		List<WebElement> closedDomainGroups = driver
				.findElements(By.xpath("//span[@class = 'ant-tree-switcher ant-tree-switcher_close']"));

		// Set timeout back to normal
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));

		for (WebElement currentDomainGroup : closedDomainGroups) {

			currentDomainGroup.click();
			// To avoid click interceptions.
			AutomationHelper.waitMillis(300);
		}
	}

	/**
	 * Method to select the domains / sub-domains that are needed in the search.
	 * This method takes a String or String array. Note: Case must be correct
	 * 
	 * @param domains
	 */
	public void selectDomain(String... domains) {

		AutomationHelper.printMethodName();

		clickAllDomainsButton();

		// First, open all of the closed domains
		openClosedDomains();

		// Second, uncheck ALL domains. This is necessary to get a clean slate.
		// Find a list of checked domains

		// Find the Domains Menu - Used to reduce scope.
		WebElement domainMenu = driver.findElement(By.xpath("//div[@class = 'ant-tree-list-holder-inner']"));

		// Find all of the elements that are currently checked. This is surrounded by
		// time managers as to reduce wait time.
		long startTime = System.nanoTime();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

		List<WebElement> checkedDomains = domainMenu
				.findElements(By.xpath("//span[@class = 'ant-tree-checkbox ant-tree-checkbox-checked']"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));

		long endTime = System.nanoTime();
		System.out.println("Select Domain > Finding Checked Domains took: "
				+ TimeUnit.SECONDS.convert(Duration.ofNanos(endTime - startTime)));

		// Loop through all of the checked domains and click them to uncheck them. The
		// wait allows time for object to adjust as to not have misplaced clicks.
		for (WebElement currentCheckedDomain : checkedDomains) {
			currentCheckedDomain.click();
			AutomationHelper.waitMillis(50);
		}

		// Take in the passed domain string and cycle through, selecting each
		// one.
		for (String currentDomain : domains) {

			// Build the xpath of the current checkbox, based on the passed in string
			String domainXpath = "//span[text()= '" + currentDomain
					+ "']/ancestor::div/span[@class = 'ant-tree-checkbox']";

			// Grab a WebElement of the checkbox
			WebElement currentCheckBox = driver.findElement(By.xpath(domainXpath));

			// Check the checkbox
			currentCheckBox.click();
		}

		AutomationHelper.hitEscape();

	}

	/**
	 * Clicks the Date drop down (button).
	 */
	public void clickDate() {
		AutomationHelper.printMethodName();

		// First, see if the date has already been picked. If it has, the date picker
		// modal will be visible.
		if (!isDatePickerObjectVisible()) {
			driver.findElement(By.xpath("//button[text() = 'Date ']")).click();
		}

	}

	/**
	 * Utility Method to check for the presence of the Date Picker modal.
	 * 
	 * @return boolean
	 */
	private boolean isDatePickerObjectVisible() {
		AutomationHelper.printMethodName();
		return AutomationHelper.isWebElementPresent(By.xpath(
				"//div[@class = 'MuiPaper-root MuiPaper-elevation MuiPaper-rounded MuiPaper-elevation8 MuiPopover-paper css-1vlrskr']"),
				1);
	}

	/**
	 * Sets the From date with the passed in string. Date must be in mm/dd/yyyy
	 * format
	 * 
	 * @param date
	 */
	public void setFromDate(String date) {

		AutomationHelper.printMethodName();

		// Ensure date is in correct format
		// Attempt to convert it
		if (!date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")) {
			throw new DateTimeException("You're trying to pass in a date that is not in MM/dd/YYYY format.");
		}

		// Click Date to open the box
		clickDate();

		// Grab a reference to the input
		WebElement dateField = driver.findElement(By.xpath("//p[text() = 'From']//parent::div//input"));

		AutomationHelper.setTextField(dateField, date);
		
		
	}

}
