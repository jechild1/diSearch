package pageFactories;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import utilities.AutomationHelper;
import utilities.DiTables;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>History Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class HistoryPageFactory extends diSearchMenusPageFactory {

	public static String regexURL = BASE_URL + "history";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public HistoryPageFactory() {

		super(regexURL);
		PageFactory.initElements(driver, this);

	}

	/**
	 * Returns a reference to the Search History table.
	 * 
	 * @return DiTables
	 */
	public SearchHistoryTable getSearchHistoryTable() {

		// Xpath String of the table
		String xpath = "//table";
		return new SearchHistoryTable(xpath);

	}

	public class SearchHistoryTable extends DiTables {

		public SearchHistoryTable(String tableXpath) {
			super(tableXpath);
		}

		// The row index for methods using the first row is 1.
		int rowIndex = 1;

		/**
		 * Reads the <b>Search</b> field text for the top row.
		 * 
		 * @return String
		 */
		public String readLastSearchText() {
			AutomationHelper.printMethodName();
			return getSearchHistoryTable().getCellText(rowIndex, "Search");
		}

		public void selectSearchLink(String seachTextStartsWith) {
			AutomationHelper.printMethodName();

			getSearchHistoryTable().clickLinkInRow(seachTextStartsWith, seachTextStartsWith);
		}

		/**
		 * Reads the <b>Response</b> field text for the top row.
		 * 
		 * @return String
		 */
		public String readLastResponseText() {
			AutomationHelper.printMethodName();
			return getSearchHistoryTable().getCellText(rowIndex, "Response");
		}

		/**
		 * Reads the <b>ChatGPT Answer</b> field text for the top row.
		 * 
		 * @return String
		 */
		public String readLastChatGPTAnswerText() {
			AutomationHelper.printMethodName();
			return getSearchHistoryTable().getCellText(rowIndex, "ChatGPT Answer");
		}

		/**
		 * Reads the <b>Created On</b> field text for the top row.
		 * 
		 * @return String
		 */
		public String readLastCreatedOnText() {
			AutomationHelper.printMethodName();
			return getSearchHistoryTable().getCellText(rowIndex, "Created On");
		}

		/**
		 * Clicks a link in table row with that matches value 1 and value 2, and
		 * contains the passed in Link Text.
		 * 
		 * @param String - primaryColumnHeader
		 * @param String - linkText
		 * @override - We do not need to throw an exception if multiple searches have
		 *           the same name. This is why we override. We choose the first link if
		 *           that happens.
		 */
		public void clickLinkInRow(String primaryColumnHeader, String linkText) {

			int rowIndex = -1;
			int primaryColIndex = -1;
			boolean paginationPresent;

			// Pass the text through the truncator
			linkText = stringTruncator(linkText);

			do {

				// Print in logs both the table search criteria and the table page
				Reporter.log(String.format("Beginning click Link In Row search for '%s' in column '%s'.", linkText,
						primaryColumnHeader), true);
				Reporter.log("Currently on table page: " + getPagination().getPaginationPageNumber(), true);

				// Get CellIndex for Primary column Header
				primaryColIndex = getColumnIndex(primaryColumnHeader, false);

				// Get the rowIndex for the row in the primary column.
				// This uses a special getRowIndex that selects the first record.
				rowIndex = getRowIndex(primaryColIndex, linkText, false, true);

				// We need to know if Pagination is present before we go into the loop.
				paginationPresent = getPagination().isPaginationPresent();

				// Click the next arrow and look again, if conditions are met.
				if (paginationPresent && rowIndex == -1) {

					// There is a chance that clicking pagination here puts us to the end of the
					// loop. If so, that's OK because our next reference won't happen until we
					// execute the loop again.
					getPagination().clickNextPagination();

					waitForPageToLoad();
					getNewTableReference();

				}

			} while (rowIndex == -1 && paginationPresent);

			// If the row index is NOT -1, then we found the object and can click it.

			WebElement tableCell = getCell(rowIndex, primaryColIndex, true);

			// This xpath gave me lots of trouble. Used to be just //p but that kept
			// clicking link above. Must have got duplicates?
			WebElement link = tableCell.findElement(By.xpath(".//a[contains(text(),'" + linkText + "')]"));

			link.click();

			Reporter.log("Link " + linkText + " clicked for row " + rowIndex + ".", true);
		}

		/**
		 * On the Search History table, all row text over 50 characters are truncated,
		 * and a three dot ellipsis is added to the end, making the total length of the
		 * string 53 characters. This method takes a string and truncates it after 50
		 * characters and then adds a three dot ellipsis. It is necessary because we
		 * will pass in a full string to methods in this class, but we will need to
		 * modify that string to find records.
		 * 
		 * @param inputString
		 * @return String
		 */
		public String stringTruncator(String inputString) {

			String finalString;

			if (inputString.length() > 50) {
				finalString = inputString.substring(0, 50);
				finalString = finalString + "...";
				Reporter.log("Search history string '" + inputString + "' was truncated to '" + finalString + "'.",
						true);
			} else
				finalString = inputString;
			return finalString;
		}

	}

	public SearchDetailsModal getSearchDetailsModal() {
		return new SearchDetailsModal();
	}

	/**
	 * Sub-class to handle the Search Details modal and objects on that modal.
	 */
	public class SearchDetailsModal {

		/**
		 * Reads the <b>Question<b> of the <b>Search Details Modal</b>. This method is smart enough to remove the "Question: " part from the object.
		 * 
		 * @return String
		 */
		public String readQuestion() {
			AutomationHelper.printMethodName();

			WebElement questionElement= driver.findElement(By.xpath("//div[@class = 'ans_module']//p"));
			
			// The WebElement will be formatted like: "Question: what is your question?". We
			// must not count the Question: part of this so lets strip it off.
			
			String questionString = AutomationHelper.getText(questionElement);
			
			String[] questionParts = questionString.split("Question: ");
			
			return questionParts[1];
		}

		/**
		 * Reads the <b>Answer<b> of the <b>Search Details Modal</b>.
		 * 
		 * @return String
		 */
		public String readAnswer() {
			AutomationHelper.printMethodName();

			expandTree("Answer");
			WebElement answerTextArea = driver.findElement(By.xpath(
					"//span[text() = 'Answer']/ancestor::div[@class = 'ant-collapse-item ant-collapse-item-active ref']//div[@class = 'ant-collapse-content-box']"));
			AutomationHelper.waitSeconds(1);
			return AutomationHelper.getText(answerTextArea);
		}

		/**
		 * Reads the <b>Reference(s)<b> of the <b>Search Details Modal</b>.
		 * 
		 * @return String
		 */
		public String readReferences() {
			AutomationHelper.printMethodName();

			expandTree("Reference(s)");
			WebElement answerTextArea = driver.findElement(By.xpath(
					"//span[text() = 'Reference(s)']/ancestor::div[@class = 'ant-collapse-item ant-collapse-item-active ref']//div[@class = 'ant-collapse-content-box']"));
			AutomationHelper.waitSeconds(1);

			return AutomationHelper.getText(answerTextArea);
		}

		/**
		 * Reads the <b>ChatGPT Answer<b> of the <b>Search Details Modal</b>.
		 * 
		 * @return String
		 */
		public String readChatGPTAnswer() {
			AutomationHelper.printMethodName();

			expandTree("ChatGPT Answer");
			WebElement answerTextArea = driver.findElement(By.xpath(
					"//span[text() = 'ChatGPT Answer']/ancestor::div[@class = 'ant-collapse-item ant-collapse-item-active']//div[@class = 'ant-collapse-content-box']"));
			AutomationHelper.waitSeconds(1);

			return AutomationHelper.getText(answerTextArea);
		}

		public void clickCloseSearchDetailsModal() {
			AutomationHelper.printMethodName();
			WebElement closeButton = driver.findElement(By.xpath(
					"//button[@class = 'MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium css-1yxmbwk']"));
			closeButton.click();
		}

		/**
		 * Utility Method to expand the sections so that we can see text.
		 * 
		 * @param sectionTitle
		 */
		private void expandTree(String sectionTitle) {

			// We must grab the span with the passed in text, and then it's parent div. It's
			// this DIV that contains a property called "aria-expanded". This property tells
			// if the box is open or closed.

			WebElement containerDiv = driver
					.findElement(By.xpath("//span[text() = '" + sectionTitle + "']/parent::div"));

			boolean expandedProperty = Boolean.valueOf(containerDiv.getAttribute("aria-expanded"));

			if (expandedProperty == false) {

				// just click the DIV
				containerDiv.click();

			}

		}

	}

}
