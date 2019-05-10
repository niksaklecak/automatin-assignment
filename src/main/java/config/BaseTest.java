package config;

import org.apache.commons.lang3.EnumUtils;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	protected RemoteWebDriver driver;

	@BeforeSuite
	@Parameters(value = { "env", "platform", "browser" })
	public void setUp(@Optional("https://www.google.com/maps") String env, @Optional("127.0.0.1") String platform,
			@Optional("Firefox") String browserName) {

		Browser browser = EnumUtils.getEnum(Browser.class, browserName.toUpperCase());
		driver = browser.getDriver();
		driver.get(env);
		driver.manage().window().maximize();

	}

	@AfterSuite
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
