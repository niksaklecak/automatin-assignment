package config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public enum Browser {

	CHROME {
		@Override
		public RemoteWebDriver getDriver() {
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--lang=en-GB");
			RemoteWebDriver driver = new ChromeDriver(options);
			return driver;
		}
	},
	FIREFOX {
		@Override
		public RemoteWebDriver getDriver() {
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("intl.accept_languages", "en-GB");
			FirefoxOptions options = new FirefoxOptions().setProfile(profile);
			RemoteWebDriver driver = new FirefoxDriver(options);
			return driver;
		}
	},
	IE {
		@Override
		public RemoteWebDriver getDriver() {
			// TODO Auto-generated method stub
			return null;
		}
	};

	public abstract RemoteWebDriver getDriver();

}