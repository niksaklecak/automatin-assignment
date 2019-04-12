package timeClockFeature;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
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

public class TestInputNotePositionAndBreak extends BaseTest {
	
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
		humanityTimeClockPage.clockIn();
	}
	
	@Test(priority = 4)
	public void testAddNote() {
		
		SoftAssert softAssert = new SoftAssert();
		humanityTimeClockPage.addNote1("testing note addition.123456789!@#$%^&*()_=-");
		humanityTimeClockPage.addNote2("                                                                          1");
		softAssert.assertEquals(humanityTimeClockPage.note1Is(), "testing note addition.123456789!@#$%^&*()_=-");
		softAssert.assertEquals(humanityTimeClockPage.note2Is(), "1");
		softAssert.assertAll();
	}
	
	@Test(priority = 5)
	public void testSetPosition() {
		
		humanityTimeClockPage.setPositionTrainee();
		humanityTimeClockPage.updatePosition();
		assertEquals(humanityTimeClockPage.getPositionLabel(), "Trainee");
	}
	
	@Test(priority = 6)
	public void testCancelSettingPosition() {
		
		humanityTimeClockPage.setPositionTrainee();
		humanityTimeClockPage.cancelSettingPosition();
		assertTrue(humanityTimeClockPage.isPositionPresent());
	}
	
	@Test(priority = 7)
	public void testAddBrake() {
		
		SoftAssert softAssert = new SoftAssert();
		humanityTimeClockPage.addBreak();
		softAssert.assertTrue(humanityTimeClockPage.getBreakStartedLabel().contains("Break started"));
		humanityTimeClockPage.continueShiht();
		softAssert.assertTrue(humanityTimeClockPage.getBreakFinishedLabel().contains("Break"));
	}
	
	@AfterMethod
	public void closeClockInDialog() {
		humanityTimeClockPage.clickOnRemoveThisClockInTime();
		humanityTimeClockPage.closeAlertAndGetItsText();
	}
	
	@AfterClass
	public void cleanUp() {
		
		humanityTimeClockPage.cleanUpChanges();
	}

}
