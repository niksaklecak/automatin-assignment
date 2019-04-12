package timeClockFeature;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import config.BaseTest;
import config.User;
import lib.HumanityDashboardPage;
import lib.HumanityLoginPage;
import lib.HumanityTimeClockPage;

public class TestClockInClockOut extends BaseTest {
	
	HumanityLoginPage humanityLoginPage;
	HumanityTimeClockPage humanityTimeClockPage;
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
		humanityTimeClockPage = humanityDashboardPage.openTimeClockPage();
	}
	
	@BeforeMethod
	public void setUpMethods() {
		
		humanityTimeClockPage.openTimeClockPage();
	}
	 
	@Test(priority = 1)
	public void testClockInClockOut() {
		
		SoftAssert softAssert = new SoftAssert();
		humanityTimeClockPage.clockIn();
		softAssert.assertEquals(humanityTimeClockPage.getStatus(), "Clocked in.");
		humanityTimeClockPage.clockOut();
		softAssert.assertEquals(humanityTimeClockPage.getStatus(), "Clocked out.");
		softAssert.assertAll();
	}
	
	@Test(priority = 2)
	public void testRemoveThisClockInTime() {
		
		SoftAssert softAssert = new SoftAssert();
		humanityTimeClockPage.clockIn();
		humanityTimeClockPage.clickOnRemoveThisClockInTime();
		softAssert.assertEquals(humanityTimeClockPage.closeAlertAndGetItsText(), "Are you sure you want to remove this Clock In Time?");
		softAssert.assertEquals(humanityTimeClockPage.getStatus(), "Clock time deleted.");
		softAssert.assertAll();
	}
	
	@Test(priority = 3)
	public void testTime() {
		
		humanityTimeClockPage.clockIn();
		humanityTimeClockPage.waitForOneMin();
		humanityTimeClockPage.clockOut();
		assertEquals(humanityTimeClockPage.timeLengthIsOneMin(), " 1m");
	}
	
	@AfterClass
	public void cleanUp() {
		
		humanityTimeClockPage.cleanUpChanges();
	}

}
