package config;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	@FindBy (xpath = "//a[@id='sn_schedule']") 
	protected WebElement scheduleNavigationButton;
	@FindBy (xpath = "//a[@id='sn_timeclock']") 
	protected WebElement timeClockNavigationButton;
	@FindBy (xpath = "//a[@id='sn_staff']") 
	protected WebElement staffNavigationButton;

	protected WebDriver driver;
	private static final long WAIT_SECONDS = 5;
	

	public BasePage (WebDriver driver) {	
		
		this.driver = driver;
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);	
		PageFactory.initElements(driver, this);
	}

	public abstract String getTitle();


	public void fluentClick(By identifer) {
	
		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(identifer));
		elem.click();
	}

	public void fluentClick(WebElement elem) {

		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elem));
		element.click();
	}
	
	public void fluentSendKeys(WebElement elem, String keys) {

		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elem));
		element.clear();
		element.sendKeys(keys);
	}
	
	public String fluentGetText(WebElement elem) {

		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(elem));
		return element.getText();
	}
	
	public void openFrame(int intLocator) {

		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(intLocator));
	}
	
	public void openFrame(String frameLocator) {

		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@id,'"+frameLocator+"')]")));
	}
	
	public void closeFrame() {
		
		driver.switchTo().defaultContent();
	}

	public void waitAndClick(WebElement element, String name) {

		for (int i = 0; i < WAIT_SECONDS; i++) {
			wait(1000);
			try {
				if (element.isDisplayed())
					break;
			} catch (StaleElementReferenceException e) {
				System.out.println("Element " + name + " yet not visible " + i + "StaleElementReferenceException ");
			} catch (TimeoutException e) {
				System.out.println("Element " + name + " yet not visible " + i + "TimeoutException ");
			} catch (WebDriverException e) {
				System.out.println("Element " + name + " yet not visible " + i + "WebDriverException ");
			} catch (Exception e) {
				System.out.println("Element " + name + " yet not visible " + i + "Exception ");
			}
		}
		element.click();
		System.out.println("Clicked: " + name);
	}

	public String waitAndGetWebElementText(WebElement element, int seconds) {

		try {
			for (int i = 0; i < seconds; i++) {
				wait(1000);
				if (element.isDisplayed())
					break;
			}
			return element.getText();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String waitAndGetWebElementText(WebElement element) {
		
		return waitAndGetWebElementText(element, 3);
	}

	public void wait(int miliseconds) {

		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			System.out.println("Error in Thread wait method " + e.getMessage());
		}
	}

	public void refresh() {
		
		driver.navigate().refresh();
		wait(5000);
	}

	public void switchTab(int tabNmb) {

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window(tabs.get(tabNmb));
		System.out.println("TAB - " + (tabNmb + 1));
	}

	public void typeKeystrokeOn(WebElement elm, Keys key) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(elm);
		actions.sendKeys(key);
		actions.build().perform();
	}

	public void moveToElement(WebElement elm) {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(elm);
		actions.build().perform();
	}

	public void resizeWindow() {
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("document.body.style.zoom = '80%'");
	}

	public void hoverOverElement(WebElement elm) {

		Actions a = new Actions(driver);
		a.moveToElement(elm).build().perform();
		wait(1000);
	}

	public void moveTo(WebElement elem) {

		Actions actions = new Actions(driver);
		actions.moveToElement(elem);
		actions.perform();
	}

	public void writeAllFramesOnPage() {

		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) {
			System.out.println(iframe.getAttribute("id"));
		}
	}
	
	public void selectListElement(String cssSelector, int iterator) {
		
		WebDriverWait wait = new WebDriverWait(driver, WAIT_SECONDS);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
		List<WebElement> rdBtn = driver.findElements(By.cssSelector(cssSelector));
		int i = 0;
		for (WebElement ch : rdBtn) {

			if (i == iterator) {
				ch.click();
				break;
			}

			i++;
		}
	}
	
	public void selectOptionByIndex(WebElement elem, int index) {
		Select select = new Select(elem);
		select.selectByIndex(index-1);
	}


}
