package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class GoogleMapsDetailsPage extends BasePage {
	
	@FindBy (css = "span.delay-light")
	private WebElement tripTimeDetailsLabel;
	@FindBy (css = "span.section-trip-summary-subtitle > span")
	private WebElement tripDistanceDetailsLabel;


	public GoogleMapsDetailsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}

	public boolean checksIfDistancePresent() {
		return waitAndCheckElementPresent (tripDistanceDetailsLabel, "tripDistanceDetailsLabel");
	}

	public boolean checkIfTimeDurationPresent() {
		return waitAndCheckElementPresent (tripTimeDetailsLabel, "tripTimeDetailsLabel");
	}

}
