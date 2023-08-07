package pageFactories;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.AutomationHelper;

/**
 * All pages with menus should extend this page. This allows access to methods
 * that allow the user to interact with menus.
 * 
 * @author jesse.childress
 *
 */
public abstract class diSearchMenusPageFactory extends diSearchBase {

	/**
	 * Constructor
	 * <p>
	 * Initializes elements on the page and then waits for the page to load. Asserts
	 * that it has loaded prior to proceeding.
	 * 
	 * @param regexURL
	 */
	public diSearchMenusPageFactory(String regexURL) {

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
	 * Checks to see if the slide menu is enabled (visible and clickable).
	 * 
	 * @return boolean
	 */
	private boolean isSlideMenuOpen() {

		boolean found = false;

		AutomationHelper.adjustWaitTimeToShort();
		List<WebElement> slideMenu = driver.findElements(By.xpath("//div[@class = 'drawer_cont']"));

		if (slideMenu.size() > 0) {
			found = true;
		}

		AutomationHelper.adjustWaitTimeToNormal();

		return found;
	}

	/**
	 * Clicks the button to open up the slide menu. This method check to see if it
	 * is open already. If not, it will open it.
	 */
	public void clickSlideMenu() {

		WebElement slideMenuButton = driver
				.findElement(By.xpath("//span[@class = 'material-symbols-outlined menu_bar_icon']"));

		slideMenuButton.click();

	}

	/**
	 * Returns a boolean to if the <b>Home Link</b> is present.
	 * 
	 * @return boolean
	 */
	public boolean isHomeLinkPresent() {
		AutomationHelper.printMethodName();
		if (!isSlideMenuOpen()) {
			clickSlideMenu();
		}
		return isWebElementPresent("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Home')]");
	}

	/**
	 * Clicks the <i>Home</i> slide menu link.
	 */
	public void clickHomeSlideMenuLink() {
		AutomationHelper.printMethodName();
		clickSlideMenu();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Home')]")));

		WebElement homeLinkInSlide = driver.findElement(
				By.xpath("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Home')]"));

		homeLinkInSlide.click();

	}

	/**
	 * Returns a boolean to if the <b>Document(s)</b> slide link is present.
	 * 
	 * @return boolean
	 */
	public boolean isDocumentsSlideLinkPresent() {
		AutomationHelper.printMethodName();
		if (!isSlideMenuOpen()) {
			clickSlideMenu();
		}
		return isWebElementPresent(
				"//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Documents')]");
	}

	/**
	 * Clicks the <i>Documents</i> link in the slide menu.
	 */
	public void clickDocumentsSlideMenuLink() {
		AutomationHelper.printMethodName();
		if (!isSlideMenuOpen()) {
			clickSlideMenu();
		}
		WebElement documentsSlideMenu = driver.findElement(
				By.xpath("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Documents')]"));
		documentsSlideMenu.click();
	}

	/**
	 * Returns a boolean to if the <b>Q&A</b> slide link is present.
	 * 
	 * @return boolean
	 */
	public boolean isQandASlideLinkPresent() {
		AutomationHelper.printMethodName();
		if (!isSlideMenuOpen()) {
			clickSlideMenu();
		}
		return isWebElementPresent("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Q&A')]");
	}

	/**
	 * Clicks the <i>Q&A</i> link in the slide menu.
	 */
	public void clickQandASlideLink() {
		AutomationHelper.printMethodName();
		clickSlideMenu();
		WebElement aboutSlideMenu = driver.findElement(
				By.xpath("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'Q&A')]"));
		aboutSlideMenu.click();
	}

	/**
	 * Returns a boolean to if the <b>History</b> slide link is present.
	 * 
	 * @return boolean
	 */
	public boolean isHistorySlideLinkPresent() {
		AutomationHelper.printMethodName();
		if (!isSlideMenuOpen()) {
			clickSlideMenu();
		}
		return isWebElementPresent(
				"//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'History')]");
	}

	/**
	 * Clicks the <i>History</i> link in the slide menu.
	 */
	public void clickHistorySlideLink() {
		AutomationHelper.printMethodName();
		clickSlideMenu();
		WebElement aboutSlideMenu = driver.findElement(
				By.xpath("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'History')]"));
		aboutSlideMenu.click();
	}

	/**
	 * Returns a boolean to if the <b>About</b> slide link is present.
	 * 
	 * @return boolean
	 */
	public boolean isAboutSlideLinkPresent() {
		AutomationHelper.printMethodName();
		if (!isSlideMenuOpen()) {
			clickSlideMenu();
		}
		return isWebElementPresent("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'About')]");
	}

	/**
	 * Clicks the <i>About</i> link in the slide menu.
	 */
	public void clickAboutSlideLink() {
		AutomationHelper.printMethodName();
		clickSlideMenu();
		WebElement aboutSlideMenu = driver.findElement(
				By.xpath("//div[contains(@class, 'MuiListItemText-root')]/span [contains(text(), 'About')]"));
		aboutSlideMenu.click();
	}

	/**
	 * Clicks the Profile Menu. Clicking this button lands you on the Settings page.
	 */
	public void clickProfileMenu() {

		WebElement profileMenuButton = driver.findElement(By.xpath("//span[text()='manage_accounts']"));
		profileMenuButton.click();

	}

	/**
	 * Clicks the Logout button in the top right.
	 */
	public void clickLogout() {

		WebElement logoutButton = driver.findElement(By.xpath("//span[text()='logout']"));
		logoutButton.click();

	}

	/*
	 * The following methods check to see if the objects are present on a page for
	 * the left menu
	 * 
	 */
	public String xpathDateLink = "//span[text()='Date']";
	public String xpathDocumentsLink = "//span[text()='Document(s)']";
	public String xpathCategoryLink = "//span[text()='Category']";
	public String xpathDomainsLink = "//span[text()='Domain(s)']";
	public String xpathHistoryLink = "//span[text()='History']";

	/**
	 * Checks for the presence of the <b>Date</b> DIV.
	 * 
	 * @return boolean
	 */
	public boolean isDatePresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent(xpathDateLink);
	}

	/**
	 * Clicks the <b>Date</b> left hand link to display the sub-objects
	 */
	public void clickDate() {
		AutomationHelper.printMethodName();

		// The following xpath contains an expanded property (the two .. go up two
		// levels). We must pull attribute property
		String xpanderDiv = xpathDateLink + "/../../div[@class='ant-collapse-header']";
		WebElement evaluationYear = driver.findElement(By.xpath(xpanderDiv));

		boolean expandProperty = Boolean.valueOf(evaluationYear.getAttribute("aria-expanded"));

		if (!expandProperty) {
			// Click the button
			WebElement evaluationYearLink = driver.findElement(By.xpath(xpathDateLink));

			// Because elements consistently move as they're being expanded, we must move to
			// it as to not have click interceptions.
			moveToElement(evaluationYear);

			evaluationYearLink.click();

			// Wait a brief moment to allow the menu to expand. This is more for visual than
			// function.
			AutomationHelper.waitSeconds(1);

		}
	}

	/**
	 * Checks for the presence of the <b>Category</b> DIV.
	 * 
	 * @return boolean
	 */
	public boolean isCategoryPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent(xpathCategoryLink);
	}

	/**
	 * Clicks the <b>Category</b> left hand link to display the sub-objects
	 */
	public void clickCategory() {
		AutomationHelper.printMethodName();

		// The following xpath contains an expanded property (the two .. go up two
		// levels). We must pull attribute property
		String xpanderDiv = xpathCategoryLink + "/../../div[@class='ant-collapse-header']";
		WebElement topics = driver.findElement(By.xpath(xpanderDiv));

		boolean expandProperty = Boolean.valueOf(topics.getAttribute("aria-expanded"));

		if (!expandProperty) {
			// Click the button
			WebElement categoryLink = driver.findElement(By.xpath(xpathCategoryLink));

			// Because elements consistently move as they're being expanded, we must move to
			// it as to not have click interceptions.
			moveToElement(categoryLink);

			categoryLink.click();

			// Wait a brief moment to allow the menu to expand. This is more for visual than
			// function.
			AutomationHelper.waitSeconds(1);
		}
	}

	/**
	 * Checks for the presence of the <b>DOCUMENTS(s)</b> DIV.
	 * 
	 * @return boolean
	 */
	public boolean isDocumentsPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent(xpathDocumentsLink);
	}

	/**
	 * Clicks the <b>DOCUMENTS</b> left hand link to display the sub-objects
	 */
	public void clickDocuments() {
		AutomationHelper.printMethodName();

		// The following xpath contains an expanded property (the two .. go up two
		// levels). We must pull attribute property
		String xpanderDiv = xpathDocumentsLink + "/../../div[@class='ant-collapse-header']";
		WebElement documents = driver.findElement(By.xpath(xpanderDiv));

		boolean expandProperty = Boolean.valueOf(documents.getAttribute("aria-expanded"));

		if (!expandProperty) {
			// Click the button
			WebElement documentsLink = driver.findElement(By.xpath(xpathDocumentsLink));

			// Because elements consistently move as they're being expanded, we must move to
			// it as to not have click interceptions.
			moveToElement(documentsLink);

			documentsLink.click();

			// Wait a brief moment to allow the menu to expand. This is more for visual than
			// function.
			AutomationHelper.waitSeconds(1);
		}
	}

	/**
	 * Checks for the presence of the <b>DOMAINS(s)</b> DIV.
	 * 
	 * @return boolean
	 */
	public boolean isDomainsPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent(xpathDomainsLink);
	}

	/**
	 * Clicks the <b>DOMAIN(S)</b> left hand link to display the sub-objects
	 */
	public void clickDomains() {
		AutomationHelper.printMethodName();

		// The following xpath contains an expanded property (the two .. go up two
		// levels). We must pull attribute property
		String xpanderDiv = xpathDomainsLink + "/../../div[@class='ant-collapse-header']";
		WebElement domains = driver.findElement(By.xpath(xpanderDiv));

		boolean expandProperty = Boolean.valueOf(domains.getAttribute("aria-expanded"));

		if (!expandProperty) {
			// Click the button
			WebElement domainsLink = driver.findElement(By.xpath(xpanderDiv));

			// Because elements consistently move as they're being expanded, we must move to
			// it as to not have click interceptions.
			moveToElement(domainsLink);

			domainsLink.click();

			// Wait a brief moment to allow the menu to expand. This is more for visual than
			// function.
			AutomationHelper.waitSeconds(1);
		}
	}

	/**
	 * Checks for the presence of the <b>HISTORY</b> DIV.
	 * 
	 * @return boolean
	 */
	public boolean isHistoryPresent() {
		AutomationHelper.printMethodName();
		return isWebElementPresent(xpathHistoryLink);
	}

	/**
	 * Clicks the <b>HISTORY</b> left hand link to display the sub-objects
	 */
	public void clickHistory() {
		AutomationHelper.printMethodName();

		// The following xpath contains an expanded property (the two .. go up two
		// levels). We must pull attribute property
		String xpanderDiv = xpathHistoryLink + "/../../div[@class='ant-collapse-header']";
		WebElement domains = driver.findElement(By.xpath(xpanderDiv));

		boolean expandProperty = Boolean.valueOf(domains.getAttribute("aria-expanded"));

		if (!expandProperty) {
			// Click the button
			WebElement historyLink = driver.findElement(By.xpath(xpathHistoryLink));

			// Because elements consistently move as they're being expanded, we must move to
			// it as to not have click interceptions.
			moveToElement(historyLink);

			historyLink.click();

			// Wait a brief moment to allow the menu to expand. This is more for visual than
			// function.
			AutomationHelper.waitSeconds(1);
		}
	}

	/**
	 * Performs an action to Move to an element. Will move to the passed in element.
	 * 
	 * @param elementToMoveTo
	 */
	private void moveToElement(WebElement elementToMoveTo) {
		Actions actions = new Actions(driver);

		// This following line is inserted into this method because the Firefox Driver
		// has a MoveTargetOutOfBounds error that happens here.
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elementToMoveTo);

		actions.moveToElement(elementToMoveTo).build().perform();
	}

	/**
	 * Getter reference for Search Results objects
	 * 
	 * @return SearchResults
	 */
	public SearchResults getSearchResults() {
		return new SearchResults();
	}

	/**
	 * Class to handle the creation of Search Result objects. Note this has
	 * temporary been done away with.
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class SearchResults {

		// Grabs the parent div for the search results to reduce scope
		WebElement parentSearchResultsDiv = driver
				.findElement(By.xpath("//div[@class = 'search_result_top bdr_btm searchResult']"));

		/**
		 * Reads the <b>Results</b> count from the Search Results section.
		 * 
		 * @return String
		 */
		public String readResults() {
			AutomationHelper.printMethodName();
			WebElement resultsText = parentSearchResultsDiv.findElement(By.xpath("//p[@class = 'search_res_p'][1]"));
			return AutomationHelper.getText(resultsText);
		}

		/**
		 * Reads the <b>Findings</b> count from the Search Results section.
		 * 
		 * @return String
		 */
		public String readFindings() {
			AutomationHelper.printMethodName();
			WebElement resultsText = parentSearchResultsDiv.findElement(By.xpath("//p[@class = 'search_res_p'][2]"));
			return AutomationHelper.getText(resultsText);
		}

		/**
		 * Reads the <b>Topics</b> count from the Search Results section.
		 * 
		 * @return String
		 */
		public String readTopics() {
			AutomationHelper.printMethodName();
			WebElement resultsText = parentSearchResultsDiv.findElement(By.xpath("//p[@class = 'search_res_p'][3]"));
			return AutomationHelper.getText(resultsText);
		}

		// Answer section
		/**
		 * Reads the <b>Answer</b> section of the search results.
		 * 
		 * @return String
		 */
		public String readAnswer() {
			AutomationHelper.printMethodName();

			WebElement answer = driver.findElement(By.xpath("//div[@class = 'content mgBt_10 bullet']"));

			return AutomationHelper.getText(answer);
		}

		String xpath = "//span[text()='Reference(s)']";

		/**
		 * Clicks the <b>Open References</b> button on the Search Results page. Note: If
		 * the References window is already open, nothing happens.
		 */
		public void clickOpenReferences() {
			AutomationHelper.printMethodName();

			// If open references is NOT open, then click it
			// This is an WebElement to the grandparent, which is a div that has properties
			// to show if it is expanded.

			WebElement expanderDiv = driver.findElement(By.xpath("//span[text()='Reference(s)']/../div/../div"));

			boolean expandProperty = Boolean.valueOf(expanderDiv.getAttribute("aria-expanded"));

			if (!expandProperty) {
				// Click the button
				WebElement references = driver.findElement(By.xpath(xpath));
				references.click();
			}

		}

		public String readReferences() {
			AutomationHelper.printMethodName();
			clickOpenReferences();
			return "";
		}

	}

	/**
	 * Getter reference for Domain objects in the left hand menu.
	 * 
	 * @return SearchResults
	 */
	public Domains getDomains() {
		return new Domains();
	}

	/**
	 * Class to handle objects in the Domain(s) left hand menu of a given page.
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class Domains {

		/**
		 * Returns a list of Domains from the Domain list. Only prints to the console.
		 * Note: Only returns top level domains and not sub-domains.
		 * 
		 */
		public void readDomainList() {

			// First, click the Domain(s) button
			clickDomains();

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

			clickDomains();

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

		}

		/**
		 * Method to unselect the passed in Domain values. The passed in text is case
		 * sensitive. This method will accept a string or a string array or items to
		 * unselect. Note: Case must be correct
		 * 
		 * @param domains
		 */
		public void unselectDomain(String... domains) {

			clickDomains();

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

			clickDomains();

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
				AutomationHelper.waitMillis(100);
			}

		}

		@FindBy(xpath = "//button[@class='ant-btn ant-btn-default ant-btn-lg upl_btn_icon']")
		WebElement uploadDocumentsButton;

		/**
		 * Clicks the <b>Upload Documents</b> button.
		 */
		public void clickUploadDocuments() {
			AutomationHelper.printMethodName();
			uploadDocumentsButton.click();
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

				// TODO - Click interception can happen here.
				currentDomainGroup.click();

				AutomationHelper.waitMillis(300);

			}

		}

		/**
		 * Clicks the Search Domains button in the left hand menu. Note: Will wait for
		 * the page to load.
		 */
		public void clickSearchDomains() {
			AutomationHelper.printMethodName();

			WebElement searchButton = driver
					.findElement(By.xpath("//button/span[@class = 'anticon anticon-double-right']"));

			searchButton.click();

			waitForPageToLoad();
		}

	}

	/**
	 * Returns a reference to the History sub-class and associated methods.
	 * 
	 * @return History
	 */
	public History getHistory() {
		return new History();
	}

	/**
	 * Sub-Class that contains methods for interacting with the History menu
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class History {

		/**
		 * Reads through a list of History "cards" or lines in the History section and
		 * looks to see if any of them match the passed in string.
		 * 
		 * @param historyText
		 * @return boolean
		 */
		public boolean isHistoryCardPresent(String historyText) {
			AutomationHelper.printMethodName();

			clickHistory();

			// Create a WebElement list of all of the histories in the menu
			List<WebElement> historyRecordWebElements = driver
					.findElements(By.xpath("//div[@class = 'hist_container']//div[@class = 'hist_card']"));

			boolean found = false;

			if (historyRecordWebElements.size() > 0) {

				for (WebElement currentHistoryCard : historyRecordWebElements) {
					if (historyText.toLowerCase().equals(currentHistoryCard.getText().toLowerCase())) {
						found = true;
						break;
					}
				}

			} else {
				throw new NoSuchElementException("There are no records present for History");
			}

			return found;
		}

		/**
		 * Clicks the History card that matches the passed in History text.
		 * 
		 * @param historyText
		 */
		public void clickHistoryCard(String historyText) {
			AutomationHelper.printMethodName();

			clickHistory();

			// Create a WebElement list of all of the histories in the menu
			List<WebElement> historyRecordWebElements = driver
					.findElements(By.xpath("//div[@class = 'hist_container']//div[@class = 'hist_card']"));

			if (historyRecordWebElements.size() > 0) {

				for (WebElement currentHistoryCard : historyRecordWebElements) {
					if (historyText.toLowerCase().equals(currentHistoryCard.getText().toLowerCase())) {
						currentHistoryCard.click();
						break;
					}
				}

			} else {
				throw new NoSuchElementException("There are no records present for History");
			}
			AutomationHelper.waitSeconds(1);
			waitForPageToLoad();
		}

	}
}
