package pageFactories;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Document Details page</b>
 * 
 * @author Jesse Childress
 *
 */
public class DocumentDetailsPageFactory extends diSearchDocDetailsMenusPageFactory {
	
	public static String regexURL = BASE_URL + "doc-details";
	
	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public DocumentDetailsPageFactory() {
		super(regexURL);
		waitForPageToLoad();
	}
	
	
	
	
	
	
	

}
