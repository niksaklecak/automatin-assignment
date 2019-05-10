package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class GoogleMapsDirectionsPage extends BasePage {

	@FindBy(xpath = "//div[@id='sb_ifc51']/input")
	private WebElement tactileSearchboxInput;
	@FindBy(xpath = "//div[@id='omnibox-directions']/div/div[3]/div[2]/button/div")
	private WebElement widgetDirectionsIcon;
	@FindBy(xpath = "//button[2]/span")
	private WebElement optionButton;
	@FindBy(xpath = "//button[2]/span[2]")
	private WebElement closeOptionButton;
	@FindBy(css = "label.kd-checkbox-label")
	private WebElement skipHighwaysCheckbox;
	@FindBy(xpath = "//a[contains(@aria-labelledby,'section-directions-trip-0')]")
	private WebElement detailsButton0;
	@FindBy(xpath = "//a[contains(@aria-labelledby,'section-directions-trip-1')]")
	private WebElement detailsButton1;
	@FindBy(xpath = "//a[contains(@aria-labelledby,'section-directions-trip-2')]")
	private WebElement detailsButton2;
	@FindBy(css = "div.directions-travel-mode-icon.directions-drive-icon")
	private WebElement driveIconButton;
	@FindBy(css = "#sb_ifc51 > input.tactile-searchbox-input")
	private WebElement startPointInput;
	@FindBy(css = "#sb_ifc52 > input.tactile-searchbox-input")
	private WebElement endPointInput;

	public GoogleMapsDirectionsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}

	public void setStartingPoint(String keys) {
		fluentSendKeys(tactileSearchboxInput, keys, "tactileSearchboxInput");
	}

	public void reverseDirection() {
		fluentClick(widgetDirectionsIcon, "widgetDirectionsIcon");
	}

	public void chooseTravelModeCar() {
		fluentClick(driveIconButton, "driveIconButton");
	}

	public void skipTheHighways() {

		waitAndClick(optionButton, "optionButton");
		fluentClick(skipHighwaysCheckbox, "skipHighwaysCheckbox");
		fluentClick(closeOptionButton, "closeOptionButton");
	}

	public GoogleMapsDetailsPage chooseTheLongestRouteAndOpenDetails() {
		wait(3000);
		List<WebElement> elements = driver.findElements(
				By.cssSelector("div.section-directions-trip-distance.section-directions-trip-secondary-text > div"));
		WebElement longestRoute = elements.get(0);
		int maxSoFar = 0;
		int iterator = -1;
		for (WebElement element : elements) {
			int integer = Integer.parseInt(element.getText().split(" km")[0]);

			if (integer > maxSoFar) {
				maxSoFar = integer;
				longestRoute = element;
				iterator++;
			}
		}
		WebElement detailsButton = driver.findElement(
				By.xpath("//button[contains(@aria-labelledby,'section-directions-trip-" + iterator + "')]"));
		if (iterator == 0 && detailsButton.isDisplayed()) {
			fluentClick(longestRoute, "longestRoute");
		} else {
			fluentClick(longestRoute, "longestRoute");
			fluentClick(detailsButton, "detailsButton");
		}
		return new GoogleMapsDetailsPage(driver);

	}

	public String startPoint() {
		return startPointInput.getAttribute("value");
	}

	public String endPoint() {
		return endPointInput.getAttribute("value");
	}
}
