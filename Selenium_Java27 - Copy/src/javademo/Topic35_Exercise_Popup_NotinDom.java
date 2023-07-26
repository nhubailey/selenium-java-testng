package javademo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic35_Exercise_Popup_NotinDom {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	//cách tắt notification trên firefox
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();	
	}


public void TC_03_Fixedpopup_NotinDOm(){
	driver.get("https://tiki.vn/");
	
	By loginpopup = By.cssSelector("div.ReactModal__Content");
	
	//verify popup chưa hiển thị khi chưa click vào button login
	//nếu verify dùng element số ít thì sẽ bị fail ngay vì cơ chế tìm k ra nó sẽ đánh fail
	Assert.assertEquals(driver.findElements(loginpopup).size(), 0);
	
	//click đăng nhập đăng ký
	//cách khác: driver.findElement(By.cssSelector("div[data-view-id*="header_account"]")).click();
	driver.findElement(By.xpath("//span[text()='Tài khoản']")).click();
	sleepInSecond(3);
	
	//verify popup hiển thị
	//Cách verify hiển thị khác
	//C2: Assert.assertEquals(driver.findElements(loginpopup).size(), 1);
	//c1
	Assert.assertTrue(driver.findElement(loginpopup).isDisplayed());
	
	//click đăng nhập bằng email link
	driver.findElement(By.cssSelector("p.login-with-email")).click();
	sleepInSecond(3);
	
	//k nhập dữ liệu và click đăng nhập
	driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
	sleepInSecond(3);
	
	//verify mess hiển thị
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Email không được để trống']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text()='Mật khẩu không được để trống']")).isDisplayed());
	
	//click để close popup 
	driver.findElement(By.cssSelector("img.close-img")).click();
	//verify popup k còn hiển thị
	Assert.assertEquals(driver.findElements(loginpopup).size(), 0);
}

@Test
public void TC_04_Fixedpopup_NotinDOm(){
	driver.get("https://www.facebook.com/");
	By Createaccountpopup = By.xpath("//div[text()='Sign Up']/parent::div/parent::div");
	
	//click create new account button
	driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
	sleepInSecond(2);
	//verify register popup hiển thị
	Assert.assertEquals(driver.findElements(Createaccountpopup).size(), 1);
	//close pop up đi
	driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
	sleepInSecond(2);
	//verify pop up k hiển thị
	Assert.assertEquals(driver.findElements(Createaccountpopup).size(), 0);
	
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
