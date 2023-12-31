package com.automation.selenium_docker.basepackege;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.automation.selenium_docker.utilities.Config;
import com.automation.selenium_docker.utilities.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({TestListener.class})
public abstract class AbstractTest {
	protected WebDriver driver;

	private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);
	/*----------alternate approach--------------
	@BeforeTest
	public void setDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	--------------------------------------------*/

	/*Here we Initialize and read all the default properties from default.properties.*/	
	@BeforeSuite
	public void setupConfig() {
		Config.initialize();
	}

	@BeforeTest
	public void setDriver(ITestContext ctx) throws MalformedURLException {
		if (Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))) {
			this.driver = getRemoteDriver();
		} else {
			this.driver = getLocalDriver();
		}
		driver.manage().window().maximize();
		
		//we are storing here the driver so that we can pass it to TestListener class
		ctx.setAttribute(Constants.DRIVER, this.driver);
		
		/*-----Alternate code----------------------------
		Here Boolean.getBoolean also gets the  property from pom.xml file but in boolean format.	
		if(Boolean.getBoolean("selenium.grid.enabled")) {
		this.driver = getRemoteDriver();
		}else {
		this.driver = getLocalDriver();
		}
		--------------------------------------------------*/
	}

	private WebDriver getRemoteDriver() throws MalformedURLException {
		Capabilities capabilities = new ChromeOptions();

		if (Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			capabilities = new FirefoxOptions();
		}
		/*------------------------------------------------------------------------------------		
				Here System.get property will read the property from pom.xml
				if(System.getProperty("browser").equalsIgnoreCase("chrome")) {
					capabilities = new ChromeOptions();
				}else {
					capabilities = new FirefoxOptions();
				}
		------------------------------------------------------------------------------------*/
		
		String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
		String hubHost = Config.get(Constants.GRID_HUB_HOST);
		/*
		 * Uri of selenium hub:- http://%s:4444/wd/hub here %s will be replaced with hub
		 * host.
		 */
		String url = String.format(urlFormat, hubHost);
		log.info("Grid url: {}", url);
		return new RemoteWebDriver(new URL(url), capabilities);
	}

	// To use below method when selenium grid is not enabled in pom.xml
	private WebDriver getLocalDriver() {
		WebDriverManager.chromedriver().setup();

		return new ChromeDriver();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
