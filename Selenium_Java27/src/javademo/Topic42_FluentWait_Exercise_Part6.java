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
public class Topic42_FluentWait_Exercise_Part6 {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;
FluentWait<WebDriver> fluentDriver;
FluentWait<WebElement> fluentElement;

long alltime =15; //second
long pollingtime = 100; //milisecond

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
}
 
@Test
public void TC_02_Fluent(){
	driver.get("https://automationfc.github.io/fluent-wait/");
	WebElement countdowntime = driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
	fluentElement = new FluentWait<WebElement>(countdowntime);
	
	fluentElement.withTimeout(Duration.ofSeconds(alltime))
	.pollingEvery(Duration.ofMillis(pollingtime))
	.ignoring(NoSuchElementException.class);
	
	fluentElement.until(new Function<WebElement, Boolean>(){
		@Override
		public Boolean apply(WebElement element) {
			String text = element.getText();
			System.out.println(text);
			return element.getText().endsWith("00");
		}
	});
}

public WebElement findElement(String xpathlocator) {
	
	fluentDriver = new FluentWait<WebDriver>(driver);
	fluentDriver.withTimeout(Duration.ofSeconds(alltime)).pollingEvery(Duration.ofMillis(pollingtime)).ignoring(NoSuchElementException.class);
	return fluentDriver.until(new Function<WebDriver, WebElement>(){
		@Override
		public WebElement apply(WebDriver driver) {
			return driver.findElement(By.xpath(xpathlocator));
		}
	});
}



@AfterClass
public void afterClass() {
	//driver.quit();
	}


}
