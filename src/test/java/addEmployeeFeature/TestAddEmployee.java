package addEmployeeFeature;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import config.BaseTest;
import config.User;
import lib.HumanityDashboardPage;
import lib.HumanityLoginPage;
import lib.HumanityStaffPage;

public class TestAddEmployee extends BaseTest{
	HumanityLoginPage humanityLoginPage;
	HumanityStaffPage humanityStaffPage;
	HumanityDashboardPage humanityDashboardPage;
	User loginUser;
	
	@Parameters(value = {"username", "password"} )
	@BeforeClass 
	public void setUpClass(@Optional("testniksa3@gmail.com") String username, @Optional("sifra123") String password) {
		
		loginUser = new User();
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		humanityLoginPage = new HumanityLoginPage(driver);
		humanityDashboardPage = humanityLoginPage.loginWithCredentilas(loginUser.getUsername(), loginUser.getPassword());
		
	}
	
	@BeforeMethod
	public void setUpTests() {
		humanityStaffPage = humanityDashboardPage.openStaffPage();
	}
	 
	@Parameters(value = {"firstame", "lastname"})
	@Test(priority = 1)
	public void testAddEmployee(@Optional("EmployeeName") String firstame, @Optional("EmployeeLastname") String lastname) {
		
		humanityStaffPage.addEmployee(firstame, lastname);
		assertEquals(humanityStaffPage.getEmployeeAdditionStatusMessage(), "1 employee saved!");
	}
	
	@Parameters(value = {"firstame1", "lastname1", "firstame2", "lastname2"})
	@Test(priority = 2)
	public void testAddMultipleEmployee(@Optional("EmployeeName1") String firstame1, @Optional("EmployeeLastname1") String lastname1, @Optional("EmployeeName2") String firstame2, @Optional("EmployeeLastname2") String lastname2) {
		
		humanityStaffPage.addMultipleEmployees(firstame1, lastname1, firstame2, lastname2);
		assertEquals(humanityStaffPage.getEmployeeAdditionStatusMessage(), "2 employees saved!");
	} 
	
	@AfterClass
	public void cleanUp() {
		
		humanityStaffPage.cleanUpChanges();
	}

}
