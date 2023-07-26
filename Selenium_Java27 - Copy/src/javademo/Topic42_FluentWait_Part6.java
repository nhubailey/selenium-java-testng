package javademo;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic42_FluentWait_Part6 {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;
JavascriptExecutor jsExecutor;
FluentWait<WebDriver> fluentDriver;


@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	jsExecutor = (JavascriptExecutor) driver;
}
 
@Test
public void TC_01_Fluent(){
	
	driver.get("https://automationfc.github.io/dynamic-loading/");
	fluentDriver = new FluentWait<WebDriver>(driver);
	
	//set tổng thời gian và tần số
	fluentDriver.withTimeout(Duration.ofSeconds(15))
		//1/3 giây check 1 lần
		.pollingEvery(Duration.ofMillis(333))
		.ignoring(NoSuchElementException.class);
	
	//apply điều kiện
	WebElement startbutton = fluentDriver.until(new Function<WebDriver, WebElement>(){
		@Override
		public WebElement apply(WebDriver driver) {
			return driver.findElement(By.cssSelector("div#start>button"));
		}
	});
	startbutton.click();
}



@AfterClass
public void afterClass() {
	//driver.quit();
	}


}
