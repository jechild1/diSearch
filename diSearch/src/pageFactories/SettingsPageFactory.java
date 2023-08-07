package pageFactories;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Settings Page</b>
 * 
 * @author Jesse Childress
 *
 */
public class SettingsPageFactory extends diSettingsMenusPageFactory {
	
	public static String regexURL = BASE_URL + "settings";
	
	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public SettingsPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}

}
