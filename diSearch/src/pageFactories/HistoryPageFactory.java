package pageFactories;

import org.openqa.selenium.support.PageFactory;

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



}
