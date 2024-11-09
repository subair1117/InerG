package utility;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericUtiity {
	protected WebDriver driver;
	WebElement webElement;
	WebElement e;
	public String currentDirectory = System.getProperty("user.dir");
	String data;
	Actions actions;

	public  GenericUtiity(WebDriver driver,String className) {
		this.driver=  driver;
	}

	public GenericUtiity(String browserName) throws Exception {
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",currentDirectory + "\\drivers\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
	} else {
		throw new Exception("Browser is not correct");
	}
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	actions = new Actions(driver);
	
	
	}
	public void launchUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
		waitForLoading();
	}
	public void navigateUrl(String url) {
		driver.navigate().to(url);
		driver.manage().window().maximize();
		waitForLoading();
	
	}
	public void waitTime(int timeInSecs) {
		try {
			Thread.sleep(1000 * timeInSecs);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public WebElement element(By by) {
		e= driver.findElement(by);
		return e;		
	}
	public List<WebElement> elements(By by) {
		return driver.findElements(by);		
	}
	public WebElement click(By by) {
		waitToClick(by);
		e = driver.findElement(by);
		e.click();
		return e;
	}
	public WebElement waitToClick(By by) {
		// waitForLoading();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 12);
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

		} catch (Exception e) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 12);
				wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

			} catch (Exception e1) {
				try {
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

				} catch (Exception e2) {
					waitTime(2);
					WebDriverWait wait = new WebDriverWait(driver, 12);
					wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));

				}
			}
		}
		return driver.findElement(by);

		}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
	    this.driver = driver;
	}
		public WebElement waitToVisible(By by) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 6);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
			} catch (Exception e) {
				waitTime(2);
				WebDriverWait wait = new WebDriverWait(driver, 6);
				wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
			}
			return driver.findElement(by);
		}
		public void waitUntilInvisible(By by) {
			waitUntilInvisible(by,3600);
		}
		public void waitUntilInvisible(By by, int timeOutInSec) {
			waitTime(2);
			for (int i = 0; i <= (timeOutInSec/3); ++i) {
				if (driver.findElements(by).isEmpty()) {
					break;
				} else {
					waitTime(3);
				}
			}
		}
		public void waitForLoading() {
			By byLoading = By.xpath("(//div[@class='ath-loader'])[2]");
			
			try {
				waitUntilInvisible(byLoading);
			} catch (Exception e) {
			}
		
	
	

	}
	public WebElement aClick(By by) {
		// waitForLoading();
		waitToClick(by);
		e = driver.findElement(by);
		actions.moveToElement(e).click(e).build().perform();
		return e;
	}
	public WebElement waitToClick(WebElement e) {
		// waitForLoading();
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(e));

		} catch (Exception ex) {
			waitTime(2);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(e));
		}
		return e;
	}
	public void aClick(WebElement e) {
		waitToClick(e);
		actions.moveToElement(e).click(e).build().perform();

	}
}
