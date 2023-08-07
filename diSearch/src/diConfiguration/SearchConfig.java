
package diConfiguration;

import java.io.File;

import diCoreConfig.CoreConfig;


public abstract class SearchConfig extends CoreConfig {

	// Environment URL
//	protected static final String BASE_URL = "https://test-search-2my7afm7yq-ue.a.run.app/";
	protected static final String BASE_URL = "https://test.dataimagineers.ai/";
	

	//Production URL
//	protected static final String BASE_URL = "https://aretec-search-2my7afm7yq-ue.a.run.app/";
	
	//Default Locations
	protected static String DEFAULT_FILE_PATH_FOR_SCREENSHOTS = System.getenv("Eclipse-ScreenshotsLocation");
	
	//Default API for search
	protected static boolean DEFAULT_API = true;
	protected static boolean LFQA_API = false;
	
	//Login Credentials
	protected static String USER_NAME = "jesse.childress@aretecinc.com";
	protected static String PASSWORD = "football3";
	
	private static String LOCAL_DATA_SETS = "dataSets\\localDataSets\\";

	
	

	// Constructor
	public SearchConfig() {
		super(BASE_URL);
	}

	/**
	 * Returns the Current URL of the page.
	 * 
	 * @return String
	 */
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	/**
	 * Generates a full file name path to be used to find a data set. This uses data
	 * in the dataSets folder. We can specify here in one place if we want to use
	 * local data or master.
	 * 
	 * @param fileNameWithExtension
	 * @return String
	 */
	protected String generateFullFileNameAndPath(String fileNameWithExtension) {
		return new File(LOCAL_DATA_SETS + fileNameWithExtension).getAbsolutePath();

	}

}
