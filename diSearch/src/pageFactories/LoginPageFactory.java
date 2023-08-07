package pageFactories;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.AutomationHelper;

/**
 * Page Factory Class to contain methods that interact with objects on the
 * <b>Login Page</b>
 * 
 * @author jesse.childress
 *
 */
public class LoginPageFactory extends diSearchBase {

	public static String regexURL = BASE_URL + "login/";

	/**
	 * Page Constructor: Accepts the WebDriver from the calling page and
	 * instantiates the elements on the page.
	 */
	public LoginPageFactory() {
		// Elements must be initiated here.
		// This constructor and extension is a bit different than normal ones in that it
		// is initiated here and gets the base URL.

		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "Register_email")
	WebElement email;

	/**
	 * Set the Email field with the passed in text
	 */
	public void setEmail(String emailText) {
		AutomationHelper.printMethodName();

		AutomationHelper.setTextField(email, emailText);
	}

	/**
	 * Reads the text inside the email field
	 */
	public String readEmail() {
		AutomationHelper.printMethodName();

		return AutomationHelper.getText(email);
	}

	@FindBy(id = "Register_password")
	WebElement password;

	/**
	 * Set the Password field with the passed in text
	 */
	public void setPassword(String password) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(this.password, password);
	}

	@FindBy(xpath = "//button/span[text()='Login']")
	WebElement loginButton;

	/**
	 * Clicks the login button
	 */
	public void clickLogin() {
		AutomationHelper.printMethodName();
		loginButton.click();
	}

	@FindBy(xpath = "//div[text()= 'Continue with Google']")
	WebElement googleLoginButton;

	/**
	 * Clicks the Google login button
	 */
	public void clickGoogleLogin() {
		AutomationHelper.printMethodName();
		googleLoginButton.click();
	}

	@FindBy(id = "identifierId")
	WebElement emailOrPhoneField;

	/**
	 * Sets the Email or Phone field on the Google Sign On prompt.
	 * 
	 * @param emailOrPhone
	 */
	public void setGoogleIdentifierID(String emailOrPhone) {
		AutomationHelper.printMethodName();
		AutomationHelper.setTextField(emailOrPhoneField, emailOrPhone);
	}

	@FindBy(xpath = "//span[text() = 'Next']")
	WebElement nextButton;

	/**
	 * Clicks the Next button on the Google Signon.
	 */
	public void clickNextButton() {
		AutomationHelper.printMethodName();
		nextButton.click();
	}
	
	
}
