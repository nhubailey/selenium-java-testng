package javademo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic32_Exercise_interaction2 {
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


public void TC_04_ClickandHole(){
	driver.get("https://automationfc.github.io/jquery-selectable/");
	List<WebElement> listnumber = driver.findElements(By.cssSelector("ol.ui-selectable>li"));
	//click và hold từ 1->4
	//B1: click vào số 1 -> vẫn giữ chuột (chưa nhả ra) 
	action.clickAndHold(listnumber.get(0)); //vì số 1 được tính là 0 lần chọn đầu tiên
	//B2: hold chuột đến số mình cần (số 4)
	action.moveToElement(listnumber.get(3));
	//B3: nhả chuột trái ra
	action.release();
	//Chạy 
	action.perform();
	sleepInSecond(3);
	//verify có 4 phần tử được chọn thành công sau khi chọn
	List<WebElement> listselected = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
	Assert.assertEquals(listselected.size(),4);
	}

@Test
public void TC_05_ClickandHole_random(){
	driver.get("https://automationfc.github.io/jquery-selectable/");
	Keys key = null;
	if(osName.contains("Windowns")) {
		key = Keys.CONTROL;
	} else {
		key = Keys.COMMAND;
	}
	
	List<WebElement> listnumber = driver.findElements(By.cssSelector("ol.ui-selectable>li"));
	//nhấn ctrl xuống
	action.keyDown(Keys.CONTROL).perform();
	//click chọn số random 1,4,7,11
	action.click(listnumber.get(0))
	.click(listnumber.get(3))
	.click(listnumber.get(6))
	.click(listnumber.get(10))
	.perform();
	//nhả phím ctrl ra
	action.keyUp(Keys.CONTROL).perform();
	sleepInSecond(3);
	//verify chọn 4 số
	List<WebElement> listselected = driver.findElements(By.cssSelector("ol.ui-selectable>li.ui-selected"));
	Assert.assertEquals(listselected.size(), 4);
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