package javademo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic31_Exercise_interaction {
WebDriver driver;
Actions action;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	action = new Actions(driver);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();	
}


public void TC_01_HovertoviewToolip(){
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	//holder chuột vào textbox
	action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
	sleepInSecond(3);
	//Verify toolip
	Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
	}

public void TC_02_Hovertoelement(){
	driver.get("http://www.myntra.com/");
	//holder chuột vào KIDs
	action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Kids']"))).perform();
	sleepInSecond(3);
	//click chọn homebath hoặc bất kì page nào
	driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")).click();
	
	//verify đã chuyển page sau khi click thành công
	Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
	}

@Test
public void TC_03_Hovertoelement(){
	driver.get("https://www.fahasa.com/");
	//chưa học bài handle pop-up nên manual click để tắt pop-up đi
	sleepInSecond(10);
	//holder chuột vào icon 4 ô
	action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu]"))).perform();
	sleepInSecond(3);
	//hover chuyển sang tab Sách trong nước
	action.moveToElement(driver.findElement(By.xpath("//a[@title='Sách Trong Nước']"))).perform();
	sleepInSecond(3);
	//click vào quản trị lãnh đạo
	driver.findElement(By.xpath("//div[@class='fhs_menu_content fhs_column_left']//a[text()='Quản Trị - Lãnh Đạo']")).click();
	//verify chuyển trang thành công
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mb-breadcrumbs']//strong[text()='Quản Trị - Lãnh Đạo']")).isDisplayed());
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