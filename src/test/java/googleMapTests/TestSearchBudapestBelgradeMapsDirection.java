package googleMapTests;

import config.BaseTest;
import pages.GoogleMapsDetailsPage;
import pages.GoogleMapsDirectionsPage;
import pages.GoogleMapsPage;
import pages.GoogleMapsSearchResultsPage;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestSearchBudapestBelgradeMapsDirection extends BaseTest {
	
	GoogleMapsPage googleMapsPage;
	GoogleMapsDirectionsPage googleMapsDirectionsPage;
	GoogleMapsDetailsPage googleMapsDetailsPage;
	GoogleMapsSearchResultsPage googleMapsSearchResultsPage;
	
	@BeforeClass
	public void setUpClassTestSearchBudapestBelgradeMapsDirection() {

		googleMapsPage	= new GoogleMapsPage(driver);
	}

	
	@Test(description = "check that google maps URL is correct ", priority = 1)
	public void testURL() {
		try {
			
			assertTrue(googleMapsPage.isUrlCorrect());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Should not have thrown any exception");
		}
	}
	
	@Test(description = "check reverse direction feature ", priority = 2)
	public void test2() {
		try {
			SoftAssert softAssert = new SoftAssert();
			googleMapsSearchResultsPage = googleMapsPage.search("Budapest");
			googleMapsDirectionsPage = googleMapsSearchResultsPage.clickOnDirections();
			googleMapsDirectionsPage.setStartingPoint("Belgrade");
			googleMapsDirectionsPage.reverseDirection();
			googleMapsDirectionsPage.chooseTravelModeCar();
			googleMapsDirectionsPage.skipTheHighways();
			
			softAssert.assertEquals(googleMapsDirectionsPage.startPoint(), "Budapest, Hungary");
			softAssert.assertEquals(googleMapsDirectionsPage.endPoint(), "Belgrade");
			softAssert.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Should not have thrown any exception");
		}
	}
	
	@Test(description = "check that distance and time are present on trip details page ", priority = 3)
	public void test3() {
		try {
			SoftAssert softAssert = new SoftAssert();
			googleMapsDetailsPage = googleMapsDirectionsPage.chooseTheLongestRouteAndOpenDetails(); 
			softAssert.assertTrue(googleMapsDetailsPage.checksIfDistancePresent());
			softAssert.assertTrue(googleMapsDetailsPage.checkIfTimeDurationPresent());
			softAssert.assertAll();
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Should not have thrown any exception");
		}
	}
}




