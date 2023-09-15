package pageFactories;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Data Contracts Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class DataContractsPageFactory extends diSettingsMenusPageFactory {

	// TODO - Still is settings for the moment. Page doesn't change
	public static String regexURL = BASE_URL + "settings";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public DataContractsPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}

	
	
	

}
