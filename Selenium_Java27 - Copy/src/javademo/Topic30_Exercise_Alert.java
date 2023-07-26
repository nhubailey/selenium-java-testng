package javademo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic30_Exercise_Alert {
WebDriver driver;
WebDriverWait explicitWait;
Alert alert;
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
	explicitWait = new WebDriverWait(driver,10);
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();	
}


public void TC_06_AcceptAlert(){
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	//1: có thể switch qua và tương tác luôn
	//alert = driver.switchTo().alert();
	
	//2 cần wait trước khi nào nó xuât hiện thì mới switch qua và tương tác=> best way
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	//3 Verify alert title đúng như mong đợi => vì k inspect được nên sẽ lấy alert.gettext
	Assert.assertEquals(alert.getText(), "I am a JS Alert");
	//accept alert
	alert.accept();
	//verify lại message
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}


public void TC_07_ConfirmAlert(){
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
	
	//2 cần wait trước khi nào nó xuât hiện thì mới switch qua và tương tác=> best way
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	//3 Verify alert title đúng như mong đợi => vì k inspect được nên sẽ lấy alert.gettext
	Assert.assertEquals(alert.getText(), "I am a JS Confirm");
	//cancel alert
	alert.dismiss();
	//verify lại message
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
	}


public void TC_08_PromptAlert(){
	driver.get("https://automationfc.github.io/basic-form/index.html");
	driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
	
	//2 cần wait trước khi nào nó xuât hiện thì mới switch qua và tương tác=> best way
	alert = explicitWait.until(ExpectedConditions.alertIsPresent());
	//3 Verify alert title đúng như mong đợi => vì k inspect được nên sẽ lấy alert.gettext
	Assert.assertEquals(alert.getText(), "I am a JS prompt");
	String name = "talequynhnhu";
	//Nhập vào text bất kì
	alert.sendKeys(name);
	alert.accept();
	//verify lại message hiển thị tại kết quả
	Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + name);
	}

public void TC_10_AuthenticationAlert(){
	//truyền trực tiếp username/password vào trong chính url này -> tự động sign in luôn
	//http:// + username : password @ the-internet.herokuapp.com/basic_auth
	driver.get(passUserAndPasstoUrl("http://the-internet.herokuapp.com/basic_auth", "admin", "admin"));
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
}

@Test
public void TC_10_AuthenticationAlert_clickpageAtoB(){

	driver.get("http://the-internet.herokuapp.com");
	//mình sẽ get url href ra để pass username pass mà k cần click vì click là sẽ bật alert lên k xử lý được
	String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
	driver.get(passUserAndPasstoUrl(authenUrl, "admin", "admin"));
	Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
}

public String passUserAndPasstoUrl(String url, String username, String password) {
// url: http://the-internet.herokuapp.com/basic_auth
//username: admin
//Password: admin
String[] arrayUrl = url.split("//");
return arrayUrl[0] + "//" + username + ":" + password + "@" + arrayUrl[1];
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