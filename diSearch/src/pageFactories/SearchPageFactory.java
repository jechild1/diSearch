package pageFactories;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.AutomationHelper;

public class SearchPageFactory extends diSearchMenusPageFactory {

	public static String regexURL = BASE_URL;

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public SearchPageFactory() {

		super(regexURL);
		PageFactory.initElements(driver, this);

		// TODO: Remove this code when defect AS-182 is corrected, to where things load
		// twice
		AutomationHelper.waitSeconds(1);

	}

	@FindBy(xpath = "//input[@placeholder='What would you like to search?']")
	WebElement searchField;

	/**
	 * Sets the search text field with the passed in text.
	 * 
	 * @param searchText
	 */
	public void setSearchField(String searchText) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(searchField, searchText);

		searchField.sendKeys(Keys.TAB);
//		
//		int counter = 1;
//		do {
//			if(!searchField.getText().equals(searchText)) {
//				AutomationHelper.waitSeconds(1);
//				System.out.println("Waiting on Serach Field " + counter);
//				counter ++;
//			}
//		}while (counter < 20);
//		

	}

	/**
	 * Reads the text that is currently in the search field.
	 * 
	 * @return String
	 */
	public String readSearchField() {
		AutomationHelper.printMethodName();
		return AutomationHelper.getText(searchField);
	}

	@FindBy(xpath = "//*[name()='svg' and contains (@data-testid,'SearchIcon') ]")
	WebElement searchIcon;

	/**
	 * Clicks the search magnifying glass icon.
	 */
	public void clickSearchMagnifyingGlass() {
		searchIcon.click();
	}

	/**
	 * Hits the Enter key on the Search object
	 */
	public void hitEnter() {
		AutomationHelper.hitEnter(searchField);
	}

//	/**
//	 * Sets the the Default API as search criteria. If
//	 */
//	public void setAPIDefault(boolean desiredStatus) {
//		AutomationHelper.printMethodName();
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//		boolean objectSelected = driver.findElements(By.xpath("//button//span[text()='Default']")).size() > 0;
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));
//
//		if ((desiredStatus == false) && (objectSelected == true)) {
//			// Click the Default button to change it to LFQA
//			WebElement defaultAPIButton = driver.findElement(By.xpath("//button//span[text()='Default']"));
//			defaultAPIButton.click();
//		} else if ((desiredStatus == true) && (objectSelected == false)) {
//			// Click on the LFQA button to change it to Default
//			WebElement lfqaAPIButton = driver.findElement(By.xpath("//button//span[text()='LFQA']"));
//			lfqaAPIButton.click();
//		}
//	}

//	/**
//	 * Sets the LFQA API as search criteria. If
//	 */
//	public void setLFQADefault(boolean desiredStatus) {
//		AutomationHelper.printMethodName();
//
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
//		boolean objectSelected = driver.findElements(By.xpath("//button//span[text()='LFQA']")).size() > 0;
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));
//
//		if ((desiredStatus == false) && (objectSelected == true)) {
//			WebElement lfqaAPIButton = driver.findElement(By.xpath("//button//span[text()='LFQA']"));
//			// Click the Default button to change it to LFQA
//			lfqaAPIButton.click();
//		} else if ((desiredStatus == true) && (objectSelected == false)) {
//			WebElement defaultAPIButton = driver.findElement(By.xpath("//button//span[text()='Default']"));
//			// Click on the LFQA button to change it to Default
//			defaultAPIButton.click();
//		}
//	}

	@FindBy(xpath = "//button[contains(text(), 'All Domain')]")
	WebElement allDomainsButton;

	/**
	 * Clicks the <b>All Domain(s)</b> button on the main search page.
	 */
	public void clickAllDomainsButton() {
		AutomationHelper.printMethodName();

		// FIrst, lets see if the button has already been clicked and the Domains menu
		// is visible
		if (isDomainMenuVisible()) {
			Reporter.log("All Domains Button already clicked. Menus already present on page.", true);
		} else {

			allDomainsButton.click();

			// Wait on the sub-menu to be visible.
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			// This is the popout. Wait a max of two seconds.
			wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = 'ant-tree-list-holder-inner']")));
		}
	}

	/**
	 * Method to check for the visible presence of the Domain(s) pop out. If the
	 * "Add Domain(s)" button was clicked, this menu should appear.
	 * 
	 * @return boolean
	 */
	private boolean isDomainMenuVisible() {

		// Adjust Timeout Temporarily
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));

		List<WebElement> domainMenu = driver.findElements(By.xpath("//div[@class = 'ant-tree-list-holder-inner']"));

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));

		if (domainMenu.size() > 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns a list of Domains from the Domain list. Only prints to the console.
	 * Note: Only returns top level domains and not sub-domains.
	 * 
	 */
	public void readDomainList() {

		// First, click the All Domain(s) button
		clickAllDomainsButton();

		String xpath = "//div[@class='ant-popover ant-popover-placement-bottomLeft ']//span[@class = 'ant-tree-title']";

		List<WebElement> domainList = driver.findElements(By.xpath(xpath));

		for (WebElement currentListItem : domainList) {
			System.out.println(currentListItem.getText());
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
		
		//We must draw attention away from the domain check box.
		AutomationHelper.hitEscape();
	}

	/**
	 * Method to unselect the passed in Domain values. The passed in text is case
	 * sensitive. This method will accept a string or a string array or items to
	 * unselect. Note: Case must be correct
	 * 
	 * @param domains
	 */
	public void unselectDomain(String... domains) {

		clickAllDomainsButton();

		// First, open all of the closed domains
		openClosedDomains();

		// Second, uncheck ALL domains. This is necessary to get a clean slate.
		// Find a list of checked domains
		List<WebElement> checkedDomains = driver.findElements(
				By.xpath("//span[@class = 'ant-tree-checkbox ant-tree-checkbox-checked']/following-sibling::span"));

		// Cycle through the list of check domains. If the domain text matches what is
		// in the domains String[], uncheck it
		for (WebElement currentCheckedDomain : checkedDomains) {

			String domainText = currentCheckedDomain.getText().trim();

			for (String currentDomainText : domains) {

				if (currentDomainText.equals(domainText)) {
					currentCheckedDomain.click();
				}
			}

		}

	}

	/**
	 * Method to unselect all currently selected domains
	 */
	public void unselectAllDomains() {
		
		//This clicks the modal to open up the domains pop up scrolling list
		clickAllDomainsButton();

		// First, open all of the closed domains
		openClosedDomains();

		// Second, uncheck ALL domains. This is necessary to get a clean slate.
		// Find a list of checked domains
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		List<WebElement> checkedDomains = driver.findElements(
				By.xpath("//span[@class = 'ant-tree-checkbox ant-tree-checkbox-checked']/following-sibling::span"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));

		for (WebElement currentCheckedDomain : checkedDomains) {
			currentCheckedDomain.click();
			AutomationHelper.waitMillis(350);
		}

	}

	@FindBy(xpath = "//button[text() = 'Upload Files']")
	WebElement uploadFilesButton;

	/**
	 * Clicks the <b>Upload Files</b> button.
	 */
	public void clickUploadFiles() {
		AutomationHelper.printMethodName();
		uploadFilesButton.click();
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

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));

		for (WebElement currentDomainGroup : closedDomainGroups) {

			currentDomainGroup.click();

			AutomationHelper.waitMillis(350);

		}

	}

	/**
	 * Method that checks for the presence of recent search text to be in the
	 * Recent Searches section.
	 * <p>
	 * Note: this method requires that the text be exact, and that the recently
	 * uploaded files be within the last 5 files uploaded. That is all this object
	 * holds.
	 * 
	 * @param searchTextForSearch
	 * @return boolean
	 */
	public boolean isSearchTextInRecentSearches(String searchTextForSearch) {
		AutomationHelper.printMethodName();
		boolean found = false;

		List<WebElement> recentlySearchedTextList = driver
				.findElements(By.xpath("//section[@class = 'grid-right-home']//h6"));

		if (recentlySearchedTextList.size() > 0) {

			// Loop through each of the recently searched text strings that were found. If a match
			// is found, break out of the loop and return true.

			for (WebElement currentSearchText : recentlySearchedTextList) {

				// Get the text of the WebElement
				String currentFileText = currentSearchText.getText();
				

				if (currentFileText.equalsIgnoreCase(searchTextForSearch)) {
					
					found = true;
					break;
				}
				
				

			}

		} else {
			throw new ElementNotVisibleException("There are no search texts matching '" + searchTextForSearch + "' in the recent searches list");
		}

		return found;

	}

}
