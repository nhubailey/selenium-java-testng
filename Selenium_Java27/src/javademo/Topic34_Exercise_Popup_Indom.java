package javademo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic34_Exercise_Popup_Indom {
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

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();	
	}

public void TC_01_Fixedpopup_InDom(){
	driver.get("https://ngoaingu24h.vn/");
	By loginbutton = By.cssSelector("div#modal-login-v1 div.modal-content");
	//kiểm tra popup đăng nhập không hiển thị
	Assert.assertFalse(driver.findElement(loginbutton).isDisplayed());
	//click vào button đăng nhập
	driver.findElement(By.xpath("//div[@id='button-login-dialog']//button[text()='Đăng nhập']")).click();
	sleepInSecond(3);
	//kiểm tra pop-up đăng nhập hiển thị
	Assert.assertTrue(driver.findElement(loginbutton).isDisplayed());
	//nhập username/password
	driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("input#password-input")).sendKeys("automationfc");
	driver.findElement(By.cssSelector("button.btn-login-v1 ")).click();
	sleepInSecond(3);
	//verify message hiển thị tài khoản không tồn tại
	Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	//đóng pop-up
	driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
	//kiểm tra pop-up k hiển thị
	Assert.assertFalse(driver.findElement(loginbutton).isDisplayed());
	
}

@Test
public void TC_02_Fixedpopup_InDom(){
	driver.get("https://skills.kynaenglish.vn/");
	By loginpopup = By.cssSelector("div#k-popup-account-login");
	//click đăng nhập button
	driver.findElement(By.cssSelector("a.login-btn")).click();
	sleepInSecond(3);
	//kiểm tra pop-up login hiển thị
	Assert.assertTrue(driver.findElement(loginpopup).isDisplayed());
	//nhập thông tin vào form email, pass
	driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
	driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
	//click đăng nhập button
	driver.findElement(By.cssSelector("button#btn-submit-login")).click();
	sleepInSecond(3);
	//verify message hiển thị
	Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
	//click close button
	driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
	sleepInSecond(3);
	//verify popup k còn hiển thị
	Assert.assertFalse(driver.findElement(loginpopup).isDisplayed());
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
