package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class GoogleMapsPage extends BasePage {
	
	@FindBy (id = "searchboxinput")
	private WebElement searchBoxInput;


	public GoogleMapsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}

	public GoogleMapsSearchResultsPage search(String keys) {
		fluentSendKeys(searchBoxInput, keys, "searchBoxInput");
		searchBoxInput.sendKeys(Keys.ENTER);
		return new GoogleMapsSearchResultsPage(driver);
	}


	public boolean isUrlCorrect() {
		return driver.getCurrentUrl().contains("https://www.google.com/maps");
	}
	

}
