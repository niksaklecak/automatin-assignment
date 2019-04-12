package lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import config.BasePage;
		
public class HumanityStaffPage extends BasePage {
	
	@FindBy (id = "act_primary") 
	private WebElement addEmployeeButton;
	@FindBy (id = "_asf1") 
	private WebElement firstNameEmployeeInput1;
	@FindBy (id = "_asl1") 
	private WebElement lastNameEmployeeInput1;
	@FindBy (id = "_asf2") 
	private WebElement firstNameEmployeeInput2;
	@FindBy (id = "_asl2") 
	private WebElement lastNameEmployeeInput2;
	@FindBy (id = "_as_save_multiple") 
	private WebElement saveMultipleEmployeesButton;
	@FindBy (id = "_status") 
	private WebElement additionEmployeeStatusMessage;
	

	public HumanityStaffPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public String getTitle() {
		return null;
	}
	
	public void addEmployee(String firstame, String lastname) {
		
		fluentClick(addEmployeeButton);
		wait(5000);
		fluentSendKeys(firstNameEmployeeInput1, firstame);
		fluentSendKeys(lastNameEmployeeInput1, lastname);
		fluentClick(saveMultipleEmployeesButton);
	}

	public void addMultipleEmployees(String firstame1, String lastname1, String firstame2, String lastname2) {
		fluentClick(addEmployeeButton);
		fluentSendKeys(firstNameEmployeeInput1, firstame1);
		fluentSendKeys(lastNameEmployeeInput1, lastname1);
		fluentSendKeys(firstNameEmployeeInput2, firstame2);
		fluentSendKeys(lastNameEmployeeInput2, lastname2);
		fluentClick(saveMultipleEmployeesButton);
	}
	
	public String getEmployeeAdditionStatusMessage () {
		
		return fluentGetText(additionEmployeeStatusMessage);
	}

	public void cleanUpChanges() {
		// API calls for cleaning up
		
	}

}
