package pageFactories;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Billing Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class BillingPageFactory extends diSettingsMenusPageFactory {
	
	//TODO - Still is settings for the moment. Page doesn't change
	public static String regexURL = BASE_URL + "settings";
	
	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public BillingPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}
	
	
	

}
