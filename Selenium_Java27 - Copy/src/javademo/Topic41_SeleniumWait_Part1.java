package javademo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic41_SeleniumWait_Part1 {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	explicitWait = new WebDriverWait(driver,10);
	}

public void TC_01_Visible_Displayed_Visibility(){
	driver.get("https://www.facebook.com/");
	
	//1.có trên UI (bắt buộc)
	//1.có trên HTML (bắt buộc)
	//chờ cho email textbox hiển thị trong vòng 10s
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	driver.findElement(By.id("email")).sendKeys("johnathan@gmail.com");
	
}


public void TC_02_Invisible_Undisplayed_Invisibility_I(){
	//2. k có trên UI (bắt buộc)
	//1. có trong HTML 
	driver.get("https://www.facebook.com/");
	driver.findElement(By.xpath("//a[@data-testid=\"open-registration-form-button\"]")).click();
	//chờ cho Re-enter email textbox không hiển thị trong vòng 10s
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	
}

@Test
public void TC_02_Invisible_Undisplayed_Invisibility_II(){
	//2. k có trên UI (bắt buộc)
	//1. k có trong HTML 
	driver.get("https://www.facebook.com/");
	//đóng popup lại nên k hiển thị html vs ui
	//chờ cho Re-enter email textbox không hiển thị trong vòng 10s
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	//vì nếu apply implicit wait nó k có trong html nó sẽ tìm đi tìm lại sẽ mất 10s
}

public void Presence_I(){
	//1. có trên UI 
	//1. có trong HTML (bắt buộc)
	//vd dropdown 1-12 dều có trong html , chỉ disable khi scroll từ 9-12, 1-8 bị disable 
	driver.get("https://www.facebook.com/");

	//chờ cho email address presence trong html trong vòng 10s
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

}
public void TC_04_Presence_II(){
	//2. k có trên UI 
	//1. có trong HTML (bắt buộc)
	driver.get("https://www.facebook.com/");
	driver.findElement(By.xpath("//a[@data-testid=\"open-registration-form-button\"]")).click();
	//chờ cho Re-enter email textbox không hiển thị trong vòng 10s
	explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	
}

public void TC_05_Staleness(){
	//2. k có trên UI (bắt buộc)
	//2. k có trong HTML 
	driver.get("https://www.facebook.com/");
	driver.findElement(By.xpath("//a[@data-testid=\"open-registration-form-button\"]")).click();
	//phase 1: element có trong html
	WebElement Reenteremailtextbox = driver.findElement(By.name("reg_email_confirmation__"));
	//thao tác vs element khác làm cho element Re-enter email textbox không còn tronh DOM
	//close popup
	driver.findElement(By.cssSelector("img._8idr")).click();
	//chờ cho Re-enter email textbox không còn trong DOM trong vòng 10s
	explicitWait.until(ExpectedConditions.stalenessOf(Reenteremailtextbox));
	
}

@AfterClass
public void afterClass() {
	//driver.quit();
	}

public void sleepInSecond(long timeInSecond) {
	try {
		Thread.sleep(timeInSecond * 1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
		// 1000ms = 1s
		}
	}
	
}
