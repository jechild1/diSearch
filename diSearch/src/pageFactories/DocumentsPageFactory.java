package pageFactories;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import utilities.DiTables;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Documents Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class DocumentsPageFactory extends diSearchMenusPageFactory {

	public static String regexURL = BASE_URL + "documents";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public DocumentsPageFactory() {

		super(regexURL);
		PageFactory.initElements(driver, this);

	}

//	/**
//	 * Returns a reference to the Documents table.
//	 */
//	@FindBy(xpath = "//table")
//	WebElement documentsTable;

	/**
	 * Returns a reference to the Documents Table.
	 * 
	 * @return WebElement documentsTable
	 */
	public DiTables getDocumentsTable() {

//		List<WebElement> documentsTable = driver.findElements(By.xpath("//table"));
//
//		if (documentsTable.size() == 0) {
//			throw new NoSuchElementException(
//					"The table does not exist on the documents page. Ensure that there is a table for the selected domains.");
//		}
		
		//Xpath String of the table
		String xpath = "//table";

		return new DiTables(xpath);
	}

}
