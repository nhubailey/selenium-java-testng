package javademo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic41_Seleniumwait_Explicitwait_Part4 {
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
	driver.manage().window().maximize();
	}

public void TC_01_Visible(){
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	explicitWait = new WebDriverWait(driver,3);
	// click vào start btn
	driver.findElement(By.cssSelector("div#start >button")).click();
	//thiếu thời gian để cho 1 element tiếp theo hoạt động được
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
	//get text và verify
	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText() ,"Hello World!");
	
	
	
}


public void TC_02_Invisible(){
	driver.get("https://automationfc.github.io/dynamic-loading/");
	
	explicitWait = new WebDriverWait(driver,5);
	// click vào start btn
	driver.findElement(By.cssSelector("div#start >button")).click();
	//wait cho loading icon biến mất
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
	//get text và verify
	Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText() ,"Hello World!");
	
}

@Test
public void TC_03__btap06Ajax_Loading(){
	driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
	
	explicitWait = new WebDriverWait(driver,15);
	// wait cho date time được hiển thị (trong 15s)
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));
	//verify message không có ngày nào được chọn
	Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText() ,"No Selected Dates to display.");
	//in ra message ngày chưa chọn
	System.out.println("No Selected Dates to display.");
	//chọn 1 ngày bất kì
	//wait cho ngày bất kì (vd:ngày 21) được phép click chọn
	//=> vì k phải element nào load ra cũng click được liền ngay nên k thể thể dùng visiblle=> nên wait cho element được phép click (tự page load xong dc phép click)
	explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='21']")));
	//click vào ngày 21
	driver.findElement(By.xpath("//a[text()='21']")).click();
	//wait cho đến khi ajax loading icon không còn visible (sd invisible)
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1' ]>div.raDiv")));
	//wait cho ngày vừa được click là ngày 21
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='21']")));
	//verify text ngày 21 được chọn
	Assert.assertEquals(driver.findElement(By.cssSelector("//span[@class='label']")).getText() ,"Sunday, May 21, 2023");
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
