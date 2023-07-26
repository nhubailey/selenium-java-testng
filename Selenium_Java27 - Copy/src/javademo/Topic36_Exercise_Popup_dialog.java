package javademo;

import java.util.Random;
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
public class Topic36_Exercise_Popup_dialog {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
String emailAddress = "automationfc" + getRandomnumber()+"@gmail.com";


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


public void TC_05_Randompopup_InDom(){
	driver.get("https://www.javacodegeeks.com/");
	sleepInSecond(25);
	//verify hiển thị popup (div not là phủ định nhánh)
	By lepopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
	//vì popup nó luôn có trong dom nên có thể dùng is display để kiểm tra
	if(driver.findElement(lepopup).isDisplayed()) {
	//nhập email và click go
		driver.findElement(By.xpath("//div[@class='lepopup-input']/input")).sendKeys(emailAddress);
		sleepInSecond(3);
		driver.findElement(By.cssSelector("a[data-label='Get the Books'],[data-label='OK']>span")).click();
		sleepInSecond(5);
		//verify popup hiển thị
		Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),"Thank you!");
		Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful."));
		//sau 5s tự đóng popup
		sleepInSecond(5);
	} 
		String search = "Agile Testing Explained";
		driver.findElement(By.cssSelector("input#search-input")).sendKeys(search);
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button#search-submit")).click();
		sleepInSecond(3);
		//Verify article đầu tiên xuất hiện từ khóa
		Assert.assertTrue(driver.findElement(By.cssSelector("ul#posts-container>li:first-child")).isDisplayed());
		}
	

public void TC_06_Randompopup_InDom(){
	driver.get("https://vnk.edu.vn/");
	sleepInSecond(30);
	By popup = By.cssSelector("div#tve_editor");
	//vì popup nó luôn có trong dom nên có thể dùng is display để kiểm tra
	if(driver.findElement(popup).isDisplayed()) {
	//có hiển thị đóng popup chuyển qua step 3
		driver.findElement(By.cssSelector("div#tve_editor div.thrv_icon")).click();
		sleepInSecond(3);
	}
		//click vào ds khóa học
		driver.findElement(By.cssSelector("button.btn-danger")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Lịch khai giảng các khóa học tại VNK EDU | VNK EDU");
	
}

@Test
public void TC_07_Randompopup_notInDom(){
	driver.get("https://dehieu.vn/");
	sleepInSecond(10);
	By popup = By.cssSelector("div.popup-content");
	//vì popup nó k có trong dom nên k thể dùng is display để kiểm tra
	if(driver.findElements(popup).size()>0 && driver.findElements(popup).get(0).isDisplayed()) {
	//có hiển thị đóng popup nhập name,mail, close popup
		driver.findElement(By.cssSelector("input#popup-name")).sendKeys("automation");
		driver.findElement(By.cssSelector("input#popup-email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("button#close-popup")).click();
		sleepInSecond(3);
	}
		//click vào tab tất cả khóa học
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(3);
		String coursename = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.cssSelector("input#search-courses")).sendKeys(coursename);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		sleepInSecond(3);
		
		//verify duy nhất 1 course hiển thị
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course-content>h4")).size(), 1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content>h4")).getText(),coursename);
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
	
public int getRandomnumber() {
	return new Random().nextInt(9999);
}
}
