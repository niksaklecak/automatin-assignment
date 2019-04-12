package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;

public class HumanitySchedulePage extends BasePage {
	
	@FindBy(xpath = "//td[@id='e_empty']/button")
	private WebElement addEmployeesButton;
	@FindBy(id="fname_2")
	private WebElement firstNameInput2;
	@FindBy(id="lname_2")
	private WebElement lastNameInput2;
	@FindBy(id="add_employee_btn")
	private WebElement addButton;
	

	public HumanitySchedulePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}

	public void addEmployee(String firstName, String lastName) {
		
		writeAllFramesOnPage();
		openFrame(1);
		fluentClick(addEmployeesButton);
//		openFrame(1);
		fluentSendKeys(firstNameInput2, firstName);
		fluentSendKeys(lastNameInput2, lastName);
		fluentClick(addButton);
	}

	public void cleanUpChanges() {
		
	}

}
