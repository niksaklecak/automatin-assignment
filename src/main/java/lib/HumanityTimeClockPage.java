package lib;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class HumanityTimeClockPage extends BasePage {
	
	@FindBy (id = "tc_tl_ci")
	private WebElement clockInButton;
	@FindBy (id = "_status")
	private WebElement statusLabel;
	@FindBy (xpath = "//a[@id='tc_tl_co']/span")
	private WebElement clockOutButton;
	@FindBy (xpath = "//table[@id='tc_tl_overview']/tbody/tr/td[2]/div/ul/li[2]/span")
	private WebElement timesheetsLengthLabel;
	@FindBy (xpath = "//a[contains(text(),'Remove this Clock In Time')]")
	private WebElement removeThisClockInTimeLink;
	@FindBy (id = "tc_tl_no")
	private WebElement addNoteInput;
	@FindBy (id = "tc_tl_no_a")
	private WebElement addNoteButton;
	@FindBy (xpath = "//ul[@id='tc_timeline']/li[2]/ul/li[3]/div/span[2]/span")
	private WebElement note1Label;
	@FindBy (xpath = "//ul[@id='tc_timeline']/li[2]/ul/li[4]/div/span[2]/span")
	private WebElement note2Label;
	@FindBy (xpath = "//a[@id='tc_tl_po_b']/span")
	private WebElement setPositionButton;
	@FindBy (xpath = "//*[@id=\"tc_timeline\"]/li[2]/ul/li[3]")
	private WebElement positionIcon;
	@FindBy (xpath = "//*[@id='tc_timeline']/li[2]/ul/li[3]/div/span[3]/span")
	private WebElement positionNameLabel;
	@FindBy (id = "tc_tl_po_s")
	private WebElement positionSelect;
	@FindBy (xpath = "//li[@id='tc_tl_po']/div/div/span[2]/a[1]")
	private WebElement positionUpdateButon;
	@FindBy (xpath = "//li[@id='tc_tl_po']/div/div/span[2]/a[2]")
	private WebElement positionCancelButon;
	@FindBy (xpath = "//a[@id='tc_tl_br_s']/span")
	private WebElement breakButton;
	@FindBy (xpath = "//ul[@id='tc_timeline']/li[2]/ul/li[8]/div")
	private WebElement breakStartedLabel;
	@FindBy (xpath = "//*[@id='tc_timeline']/li[2]/ul/li[3]/div")
	private WebElement breakFinishedLabel;
	@FindBy (xpath = "//a[@id='tc_tl_br_e']/span")
	private WebElement continueShiftButton;

	public HumanityTimeClockPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}
	
	public HumanityTimeClockPage openTimeClockPage() {
		fluentClick(timeClockNavigationButton);
		return new HumanityTimeClockPage(driver);
	}
	
	public void clockIn() {
		fluentClick(clockInButton);
	}

	public String getStatus() {
		wait(500);
		return fluentGetText(statusLabel);
	}

	public void clockOut() {
		fluentClick(clockOutButton);
	}

	public void cleanUpChanges() {
		// API calls 
		wait(2000);
	}

	public void waitForOneMin() {
		wait(60000);
	}

	public String timeLengthIsOneMin() {
		wait(1000);
		return fluentGetText(timesheetsLengthLabel);
	}

	public void clickOnRemoveThisClockInTime() {
		wait(1000);
		fluentClick(removeThisClockInTimeLink);
	}

	public String closeAlertAndGetItsText() {
		wait(500);
		Alert alert = driver.switchTo().alert();
		String text = alert.getText();
		alert.accept();
		return text;
	}

	public void addNote1(String string) {
		fluentSendKeys(addNoteInput, string);
		fluentClick(addNoteButton);
	}
	public void addNote2(String string) {
		wait(1000);
		fluentSendKeys(addNoteInput, string);
		fluentClick(addNoteButton);
	}

	public String note1Is() {
		return fluentGetText(note1Label);
	}

	public String note2Is() {
		return fluentGetText(note2Label);
	}

	public void setPositionTrainee() {
		fluentClick(setPositionButton);
		selectOptionByIndex(positionSelect, 2);
	}

	public String getPositionLabel() {
		return fluentGetText(positionNameLabel);
	}

	public void updatePosition() {
		fluentClick(positionUpdateButon);
	}

	public void cancelSettingPosition() {
		fluentClick(positionCancelButon);
	}

	public boolean isPositionPresent() {
		return positionIcon.isDisplayed();
	}

	public void addBreak() {
		fluentClick(breakButton);
	}

	public String getBreakStartedLabel() {
		return fluentGetText(breakStartedLabel);
	}
	
	
	public String getBreakFinishedLabel() {
		return fluentGetText(breakFinishedLabel);
	}

	public void continueShiht() {
		fluentClick(continueShiftButton);
	}





}
