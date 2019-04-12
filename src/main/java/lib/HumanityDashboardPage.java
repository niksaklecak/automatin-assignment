package lib;

import org.openqa.selenium.WebDriver;

import config.BasePage;

public class HumanityDashboardPage extends BasePage {

	public HumanityDashboardPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}
	
	public HumanitySchedulePage openSchedulePage() {
		fluentClick(scheduleNavigationButton);
		return new HumanitySchedulePage(driver);
	}
	
	public HumanityStaffPage openStaffPage() {
		fluentClick(staffNavigationButton);
		return new HumanityStaffPage(driver);
	}
	
	public HumanityTimeClockPage openTimeClockPage() {
		fluentClick(timeClockNavigationButton);
		return new HumanityTimeClockPage(driver);
	}

}
