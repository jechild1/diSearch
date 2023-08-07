package pageFactories;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Data Domains Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class DataDomainsPageFactory extends diSettingsMenusPageFactory {
	
	//TODO - Still is settings for the moment. Page doesn't change
	public static String regexURL = BASE_URL + "settings";
	
	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public DataDomainsPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}
	
	
	

}
