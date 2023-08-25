package pageFactories;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import diConfiguration.SearchConfig;
import utilities.AutomationHelper;

public abstract class diSearchBase extends SearchConfig  {

	/*
	 * Initialize elements of a given page factory
	 */
	public void initializeElements() {
		PageFactory.initElements(driver, this);
	}

	/**
	 * Private utility method that checks for the presence of a WebElement by a
	 * passed in XPATH.
	 * 
	 * @param xPath
	 * @return
	 */
	protected boolean isWebElementPresent(String xPath) {

		// Long startTime = System.currentTimeMillis();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		boolean isElementPresent = driver.findElements(By.xpath(xPath)).size() > 0;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NORMAL_TIMEOUT));
		// Long endTime = System.currentTimeMillis();

		// System.out.println("Time for check for xpath " + xPath + ": " + (endTime -
		// startTime) / 1000);

		return isElementPresent;
	}

	/**
	 * Method to take in a parameter as string and wait a max of 10 seconds for the
	 * message to be displayed. Please ensure that all is
	 * 
	 * @param message
	 * @return boolean
	 */
	public boolean isAnnouncementMessageDisplayed(String message) {
		AutomationHelper.printMethodName();

		boolean announcementMessagePresent = false;

		List<WebElement> messageList = driver.findElements(By.xpath("//span[text() = '" + message + "']"));

		int timerCount = 0;

		if (messageList.size() == 0 && timerCount < 10) {

			AutomationHelper.waitSeconds(1);
			timerCount++;

			// Attempt a new reference
			messageList = driver.findElements(By.xpath("//span[text() = '" + message + "']"));

		} else if (messageList.size() > 0) {
			// Cycle through the message and see if it matches the passed in string
			for (WebElement currentMessage : messageList) {
				if (AutomationHelper.getText(currentMessage).equals(message)) {
					announcementMessagePresent = true;
					break;
				}
			}
		}
		return announcementMessagePresent;
	}

	/**
	 * Getter method to return a class object for Upload Files
	 * 
	 * @return UploadFiles
	 */
	public UploadFiles getUploadFiles() {
		return new UploadFiles();
	}

	/**
	 * Class that will contain methods that handle the uploading of files
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class UploadFiles {

		/*
		 * Initialize elements of a given page factory
		 */
		public UploadFiles() {
			PageFactory.initElements(driver, this);
		}

		@FindBy(xpath = "//div[@class = 'header_modal']//button[contains(text() , 'Domain')]")
		WebElement selectDomainsButton;

//		/**
//		 * Clicks the Select Domain button on the Upload Documents modal
//		 */
//		public void clickSelectDomain() {
//			AutomationHelper.printMethodName();
//			selectDomainButton.click();
//		}

		/**
		 * Clicks the <b>Select Domain</b> button on the main search page.
		 */
		public void clickSelectDomainButton() {
			AutomationHelper.printMethodName();

			// FIrst, lets see if the button has already been clicked and the Domains menu
			// is visible
			if (isDomainMenuVisible()) {
				Reporter.log("Select Domain Button already clicked. Menus already present on page.", true);
			} else {

				selectDomainsButton.click();

				// Wait on the sub-menu to be visible.
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				// This is the popout. Wait a max of two seconds.
				wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//div[@class = 'header_modal']//button[contains(text() , 'Domain')]")));
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

			List<WebElement> domainMenu = driver.findElements(By.xpath("//div[@class = 'ant-tree-list-holder']"));

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

			// First, click the Select Domain button
			clickSelectDomainButton();

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

			// First, click the Select Domain button
			clickSelectDomainButton();

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
			System.out.println("Select Domain > Finding Checked Domains took "
					+ TimeUnit.SECONDS.convert(Duration.ofNanos(endTime - startTime)) + " seconds.");

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

			// Performs a click to ensure the Domain picker goes away
			Actions builder = new Actions(driver);
			builder.moveByOffset(1, 1).click().build().perform();

		}

		/**
		 * Method to unselect the passed in Domain values. The passed in text is case
		 * sensitive. This method will accept a string or a string array or items to
		 * unselect. Note: Case must be correct
		 * 
		 * @param domains
		 */
		public void unselectDomain(String... domains) {

			// First, click the Select Domain button
			clickSelectDomainButton();

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

			// First, click the Select Domain button
			clickSelectDomainButton();

			// Next, open all of the closed domains
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

		@FindBy(xpath = "//button[text()='Upload']")
		WebElement uploadButton;

		/**
		 * Clicks the <b>Upload</b> button.
		 * <p>
		 * Note: This method will wait until the popup has disappeared to continue
		 * processing (for a max of 5 seconds)
		 */
		public void clickUpload() {
			AutomationHelper.printMethodName();
			uploadButton.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions
					.invisibilityOf(driver.findElement(By.xpath("//div[@class='ant-modal file_upload_modal_cont']"))));

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

			JavascriptExecutor je = (JavascriptExecutor) driver;

			
			for (WebElement currentDomainGroup : closedDomainGroups) {

				je.executeScript("arguments[0].scrollIntoView(true);", currentDomainGroup);

				currentDomainGroup.click();

				AutomationHelper.waitMillis(100);

			}

		}

//		@FindBy(xpath = "//button/span[text() = 'Select Domain']")
//		WebElement selectDomainButton;

		@FindBy(xpath = "//button/span[text() = 'Browse Files']")
		WebElement browseFilesButton;

		/**
		 * Clicks the Browse Files button on the Upload Documents modal
		 */
		public void clickBrowseFiles() {
			AutomationHelper.printMethodName();
			browseFilesButton.click();
		}

		/**
		 * Method to upload a file (or multiple files, as indicated by a comma between
		 * each file name.)
		 * 
		 * Keep in mind that this puts the documents in the list to be uploaded, but
		 * does not actually upload until the user clicks the Upload button
		 * 
		 * @param filesToUpload
		 */
		public void uploadFile(String filesToUpload) {

			// First, see if the data has multiple files, as separated by a comma.
			String[] files = filesToUpload.split(",");
			// Remove the white space from before and after each separate file name
			for (int i = 0; i < files.length; i++) {
				files[i] = files[i].trim();
			}

			// Loop through each item in the array and perform an upload

			for (String currentFile : files) {

				String xpath = "//input[@type='file'][@multiple = '']";
				WebElement fileUploadObject = driver.findElement(By.xpath(xpath));

				String filePath = generateFullFileNameAndPath(currentFile);
				// Good to print in the log
				Reporter.log("File Uploaded: " + filePath, true);

				fileUploadObject.sendKeys(filePath);

			}

		}

		/**
		 * Method to accept a file name and check the file upload list to see if the
		 * file is in the modal and ready for upload. Note that the case must be correct
		 * and the file extension must be added.
		 * 
		 * @param fileName
		 * @return boolean
		 */
		public boolean isFileInUploadList(String fileName) {
			AutomationHelper.printMethodName();

			boolean fileNameFound = false;
			// Find a list of all the objects that contain file names
			List<WebElement> fileNamesList = driver.findElements(By.xpath("//p[@class='file_name']"));

			if (fileNamesList.size() > 0) {

				// Cycle through the list of WebElements and look for the passed in file name.
				for (WebElement currentFileName : fileNamesList) {

					if (fileName.matches(AutomationHelper.getText(currentFileName))) {
						fileNameFound = true;
						break;
					}

				}

			} else {
				throw new ElementNotVisibleException("There are no files prepared for upload.");
			}

			return fileNameFound;
		}
	}
}
