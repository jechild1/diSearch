package pageFactories;

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


	/**
	 * Returns a reference to the Documents Table.
	 * 
	 * @return WebElement documentsTable
	 */
	public DiTables getDocumentsTable() {

		//Xpath String of the table
		String xpath = "//table";

		return new DiTables(xpath);
	}

}
