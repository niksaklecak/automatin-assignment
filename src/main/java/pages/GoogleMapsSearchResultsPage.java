package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class GoogleMapsSearchResultsPage extends BasePage {

	@FindBy (xpath = "//button[contains(@jsaction,'pane.placeActions.directions')]")
	private WebElement directionsButton;


	public GoogleMapsSearchResultsPage (WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}

	public GoogleMapsDirectionsPage clickOnDirections() {

		waitAndClick(directionsButton, "directionsButton");
		return new GoogleMapsDirectionsPage(driver);
	}

	

}
