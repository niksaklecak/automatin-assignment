package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class HumanityLoginPage extends BasePage {

	@FindBy(id = "email")
	private WebElement emailInput;
	@FindBy(id = "password")
	private WebElement passwordInput;
	@FindBy(xpath = "//button[@name='login']")
	private WebElement loginButton;
	@FindBy(id = "response-message")
	private WebElement responseMessageLabel;
	
	public HumanityLoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}

	public HumanityDashboardPage loginWithCredentilas(String username, String password) {
		
		fluentSendKeys(emailInput, username);
		fluentSendKeys(passwordInput, password);
		fluentClick(loginButton);
		return new HumanityDashboardPage(driver);
	}

}
