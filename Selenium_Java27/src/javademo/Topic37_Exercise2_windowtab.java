package javademo;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic37_Exercise2_windowtab {
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
	
	driver = new FirefoxDriver();

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();	
	}


public void TC_10_Windowtab(){
	//parent page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//window/ tab có 2 hàm để lấy ra ID của window/ tab đó
	//1- Nó lấy ra cái ID của window/tab mà nó đang đứng (active, tab hiện tại) =>vì hiện tại chỉ chuyển qua 1 page google nên sẽ áp dụng c1
	String parentWindowID = driver.getWindowHandle();
	System.out.println("Parent page= " + parentWindowID);
	//click vào Google link để bật ra 1 tab mới
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(5);
	
	SwitchtoWindowByID(parentWindowID);
	//kiểm tra title của windown mới = google
	Assert.assertEquals(driver.getTitle(), "Google");
	driver.findElement(By.xpath("//textarea[@type='search']")).sendKeys("how to learn java");
	sleepInSecond(2);
	
	//switch về parent windown
	String GoogleWindowID = driver.getWindowHandle();
	SwitchtoWindowByID(GoogleWindowID);
	//kiểm tra đã về page parent window
	Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
}


public void TC_10_WindownTab_Title(){
	//parent page
	driver.get("https://automationfc.github.io/basic-form/index.html");
	String parentID = driver.getWindowHandle();
	//click vào Google link để bật ra 1 tab mới
	driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
	sleepInSecond(2);
	
	SwitchtoWindowByTitle("Google");
	Assert.assertEquals(driver.getCurrentUrl(), "https://www.google.com.vn/");
	
	
	//switch về parent windown
	SwitchtoWindowByTitle("Selenium WebDriver");
	Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");

	//click vào Faceboollink để bật ra 1 tab mới
	driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
	sleepInSecond(2);
	//kiểm tra title FB 
	SwitchtoWindowByTitle("Facebook – log in or sign up");
	driver.findElement(By.cssSelector("input#email")).sendKeys("abc@gmail.com");
	
	//switch về parent windown
	SwitchtoWindowByTitle("Selenium WebDriver");
	Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");

	//click vào Faceboollink để bật ra 1 tab mới
	driver.findElement(By.xpath("//a[text()='TIKI']")).click();
	sleepInSecond(2);
	//kiểm tra title Tiki
	SwitchtoWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
	driver.findElement(By.cssSelector("input[class^=FormSearchStyle]")).sendKeys("books");
	//close tất cả windown ngoại trừ parent
	CloseAllWindowexceptParent(parentID);
	sleepInSecond(3);
	
	//kiểm tra đã quay về parent (title/url ) 
	SwitchtoWindowByTitle("Selenium WebDriver");
	Assert.assertEquals(driver.getCurrentUrl(),"https://automationfc.github.io/basic-form/index.html");
	
	

}

@Test
public void TC_12_WindownTab(){
	//parent page
	driver.get("http://live.techpanda.org/index.php/mobile.html");
	
	//Add Sony xeperia + verify text
	driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Sony Xperia has been added to comparison list.");
	
	//Add Samsung + verify text
	driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
	Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),"The product Samsung Galaxy has been added to comparison list.");
	
	//Click to compare
	driver.findElement(By.xpath("//button[@title='Compare']")).click();
	sleepInSecond(2);
	
	//switch qua cửa sổ mới chứa 2 sp đã add
	SwitchtoWindowByTitle("Products Comparison List - Magento Commerce");
	
	//verify title cửa sổ
	Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
	Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());
	
	//close tab và chuyển về parent window
	driver.findElement(By.xpath("//button[@title='Close Window']")).click();
	SwitchtoWindowByTitle("Mobile");//mặc dù close page compare nhưng nó vẫn ở trang compare nên cần switch về
	
	//click clear all link và accept alert
	//verify message hiển thị
	
}

//cần viết hàm để mỗi lần switch đỡ tốn time viết nhiều code
//case này dùng được cho duy nhất 2 ID (window/tab)
public void SwitchtoWindowByID(String otherID) {
	//lấy hết tất cả ID ra
	Set<String> allWindowIDs = driver.getWindowHandles();
	//sau đó dùng vòng lặp duyệt qua r kiểm tra
	for (String id: allWindowIDs ) {
		if(!id.equals(otherID)) {
			driver.switchTo().window(id);
			sleepInSecond(2);
			
			}
		}
}

//case này dùng được cho từ 2 ID trở lên (window/tab)
public void SwitchtoWindowByTitle(String expectedpageTitle) {
	
	Set<String> allWindowIDs = driver.getWindowHandles();
	for (String id: allWindowIDs ) {
		//switch từng id trước
		driver.switchTo().window(id);
		//lấy ra title của page này
		String actualPageTitle = driver.getTitle();
			
		if(actualPageTitle.equals(expectedpageTitle)) {
			sleepInSecond(2);
			break;
		}
	}
}

//hàm close
public void CloseAllWindowexceptParent(String parentID) {
	
	Set<String> allWindowIDs = driver.getWindowHandles();
	for (String id: allWindowIDs ) {
		if(!id.equals(parentID)) {
			driver.switchTo().window(id);
			driver.close();
			sleepInSecond(2);
			
		}
	}
	driver.switchTo().window(parentID);
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
