package pageFactories;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import utilities.AutomationHelper;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Search Results Page</b>
 * 
 * @author jesse.childress
 *
 */
public class SearchResultsPageFactory extends diSearchMenusPageFactory {

	public static String regexURL = BASE_URL + "search-result" + ".*";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public SearchResultsPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}

	/**
	 * This method will wait for the Answer text to finished and completed before it
	 * allows the script to continue.
	 */
	private void waitForAnswerGeneration() {
		AutomationHelper.printMethodName();

		// Grab the xpath of the Answer paragraph:
		String xpathOfAnswer = "//div[@class = 'content mgBt_10 bullet']";

		// Variables to be used in the do/while loop
		int initialCharacterCount = 0;
		int waitCharacterCount = 0;
		int loopCounter = 0;

		do {

			// Grab a reference to the WebElement
			WebElement answerParagraph = driver.findElement(By.xpath(xpathOfAnswer));

			// Grab the current text of the answer paragraph
			String initialParagraphText = AutomationHelper.getText(answerParagraph);

			// Count the initial characters in the paragraph
			initialCharacterCount = initialParagraphText.length();

			// Force the script to wait one second before grabbing a new reference.
			AutomationHelper.waitSeconds(1);

			// Get new references to objects after a wait so we can compare in the WHILE
			// below
			answerParagraph = driver.findElement(By.xpath(xpathOfAnswer));
			String waitedParagraphText = AutomationHelper.getText(answerParagraph);
			waitCharacterCount = waitedParagraphText.length();

			loopCounter++;

		} while (initialCharacterCount != waitCharacterCount);

		Reporter.log("Waited on Answer text for: " + loopCounter + " seconds.", true);

	}

	/**
	 * Method to read the Answer text from the Search Results page.
	 * <p>
	 * Because the Answer text is automatically generated, it will require that we
	 * wait until the text is completed. This method calls waitForAnswerGeneration()
	 * to ensure the answer is finished before it proceeds to read it.
	 * 
	 * @return String
	 */
	public String readAnswer() {
		AutomationHelper.printMethodName();

		// We must wait for the answer to be dynamically generated.
		waitForAnswerGeneration();

		String xpathOfAnswer = "//div[@class = 'content mgBt_10 bullet']";

		WebElement answerWebElement = driver.findElement(By.xpath(xpathOfAnswer));

		return AutomationHelper.getText(answerWebElement);

	}

	/**
	 * Getter method for References objects
	 * 
	 * @return References
	 */
	public References getReferences() {
		return new References();
	}

	/**
	 * Sub-class that contains methods for interacting with objects in the
	 * References section
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class References {

		/**
		 * Utility method to expand the references section, if not already expanded.
		 */
		private void expandReferencesSection() {
			// Ensure that it is not already open
			WebElement referencesSection = driver.findElement(
					By.xpath("//span[text() = 'Reference(s)']//ancestor::div[@class='ant-collapse-header']"));

			boolean expanded = Boolean.valueOf(referencesSection.getAttribute("aria-expanded"));

			// If it is NOT already open, open it
			if (!expanded) {
				WebElement arrowIcon = driver.findElement(
						By.xpath("//div[@class='ant-collapse-header']/span[text() = 'Reference(s)']//parent::span"));
				arrowIcon.click();
			}
		}

		/**
		 * Reads the <b>File Name</b> in the References section
		 * 
		 * @return String
		 */
		public String readFileName() {
			AutomationHelper.printMethodName();

			expandReferencesSection();
			String xpath = "//div[@class='ant-collapse-header'][1]/span[text() = 'Reference(s)']//ancestor::div[@class = 'ant-collapse-item ant-collapse-item-active ref']//p[@class='name space_0']/parent::span";
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(xpath), ".pdf"));

			String fileName = AutomationHelper.getText(driver.findElement(By.xpath(xpath)));

			return fileName;
		}

		/**
		 * Returns the <b>Upload Date</b> in the References section. Note: this only
		 * returns the date.
		 * 
		 * @return String
		 */
		public String readUploadDate() {
			AutomationHelper.printMethodName();

			expandReferencesSection();

			String completeText = AutomationHelper.getText(driver.findElement(By.xpath(
					"//div[@class='ant-collapse-header'][1]/span[text() = 'Reference(s)']//ancestor::div[@class = 'ant-collapse-item ant-collapse-item-active ref']//span[contains(text(), 'Upload Date')]")));

			// String looks like "Upload Text: 2023-07-04"
			completeText.trim(); // Gets rid of a space at the end
			completeText = completeText.substring(completeText.indexOf(":") + 2, completeText.length()).trim();

			return completeText;

		}

		/**
		 * Reads all of the Domains in the References section, with a space between each
		 * entry.
		 * 
		 * @return String
		 */
		public String readReferenceDomains() {
			AutomationHelper.printMethodName();

			expandReferencesSection();

			String domainXpath = "//div[contains(text(), 'Domains:')]/span";

			List<WebElement> domains = driver.findElements(By.xpath(domainXpath));

			String domainsString = "";

			for (WebElement currentDomain : domains) {
				domainsString += currentDomain.getText() + " ";
			}

			domainsString.trim();

			return domainsString;
		}

		/**
		 * Looks for the passed in domain to be present in the References > Domains
		 * section.
		 * 
		 * @param domain
		 * @return boolean
		 */
		public boolean isReferenceDomainPresent(String domain) {
			AutomationHelper.printMethodName();

			expandReferencesSection();

			String domainsXpath = "//div[contains(text(), 'Domains:')]/span";

			List<WebElement> domainsList = driver.findElements(By.xpath(domainsXpath));

			// Loop through the list and see if the domains exist in the section
			boolean found = false;

			for (WebElement currentDomain : domainsList) {
				if (currentDomain.getText().trim().toLowerCase().equals(domain.trim().toLowerCase())) {
					found = true;
					break;
				}

			}
			return found;

		}

		/**
		 * Reads the text in the References sheet
		 * 
		 * @return text
		 */
		public String readReferencesText() {
			AutomationHelper.printMethodName();

			String xpathForText = "//div[@class='ant-collapse-header'][1]/span[text() = 'Reference(s)']//ancestor::div[@class = 'ant-collapse-item ant-collapse-item-active ref']//p[text() = 'Text']//following-sibling::p";

			WebElement textField = driver.findElement(By.xpath(xpathForText));

			return textField.getText().trim();
		}

	}

	/**
	 * Getter method to return a reference to Chat GPT and their sub-objects
	 * 
	 * @return ChatGPT reference
	 */
	public ChatGPT getChatGPT() {
		return new ChatGPT();
	}

	/**
	 * Sub-class that contains objects to interact with items in the Chat GPT
	 * section of the search results page
	 * 
	 * @author Jesse Childress
	 *
	 */
	public class ChatGPT {

		/**
		 * Utility method to expand the ChatGPT Answer section, if not already expanded.
		 */
		private void expandChatGPTAnswerSection() {
			// Ensure that it is not already open
			WebElement chatGPTSection = driver.findElement(
					By.xpath("//span[text() = 'ChatGPT Answer']//ancestor::div[@class='ant-collapse-header']"));

			boolean expanded = Boolean.valueOf(chatGPTSection.getAttribute("aria-expanded"));

			// If it is NOT already open, open it
			if (!expanded) {
				WebElement arrowIcon = driver.findElement(
						By.xpath("//div[@class='ant-collapse-header']/span[text() = 'ChatGPT Answer']//parent::span"));
				arrowIcon.click();
			}

		}

		/**
		 * This method will wait for the Answer text to finished and completed before it
		 * allows the script to continue.
		 */
		private void waitForChatBPTAnswerGeneration() {
			AutomationHelper.printMethodName();

			// Grab the xpath of the Answer paragraph:
			String xpathOfAnswer = "//div[@class = 'bullet']";

			// Variables to be used in the do/while loop
			int initialCharacterCount = 0;
			int waitCharacterCount = 0;
			int loopCounter = 0;

			do {

				// Grab a reference to the WebElement
				WebElement answerParagraph = driver.findElement(By.xpath(xpathOfAnswer));

				// Grab the current text of the answer paragraph
				String initialParagraphText = AutomationHelper.getText(answerParagraph);
				
				//Sometimes text does not appear immediately, so we shoud wait
				int timeCount = 0;
				while(initialParagraphText == null && timeCount < 30) {
					AutomationHelper.waitSeconds(1);
					timeCount ++;
					initialParagraphText = AutomationHelper.getText(answerParagraph);
					System.out.println("Waiting on Chat GBP answer for " + timeCount + " seconds.");
				}

				// Count the initial characters in the paragraph
				initialCharacterCount = initialParagraphText.length();

				// Force the script to wait one second before grabbing a new reference.
				AutomationHelper.waitSeconds(1);

				// Get new references to objects after a wait so we can compare in the WHILE
				// below
				answerParagraph = driver.findElement(By.xpath(xpathOfAnswer));
				String waitedParagraphText = AutomationHelper.getText(answerParagraph);
				waitCharacterCount = waitedParagraphText.length();

				loopCounter++;

			} while (initialCharacterCount != waitCharacterCount);

			Reporter.log("Waited on Chat GPT Answer text for: " + loopCounter + " seconds.", true);

		}

		/**
		 * Method to read the Answer text from the Search Results page > Chat GPT
		 * section.
		 * <p>
		 * Because the Answer text is automatically generated, it will require that we
		 * wait until the text is completed. This method calls
		 * waitForChatGPTAnswerGeneration() to ensure the answer is finished before it
		 * proceeds to read it.
		 * 
		 * @return String
		 */
		public String readAnswer() {
			AutomationHelper.printMethodName();

			expandChatGPTAnswerSection();

			// We must wait for the answer to be dynamically generated.
			waitForChatBPTAnswerGeneration();

			String xpathOfAnswer = "//div[@class = 'bullet']";

			WebElement answerWebElement = driver.findElement(By.xpath(xpathOfAnswer));

			return AutomationHelper.getText(answerWebElement);

		}
	}

}
