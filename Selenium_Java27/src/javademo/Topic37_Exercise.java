package javademo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic37_Exercise {
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


public void TC_08_Iframe(){
	driver.get("https://skills.kynaenglish.vn/");
	//verify FB iframe hiển thị (FB vẫn là 1 element của trang Kina nên k cần lệnh switch to
	Assert.assertTrue(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")).isDisplayed());
	
	//verify trang FB 167 like (vì 167 like nằm trong html khác nên cần switch to
	//cần phải switch vào đúng cái ther iframe chứa các element đó
	//driver.switchTo().frame(0);
	driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'kyna.vn')]")));
	
	String Facebooklike = driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText();
	System.out.println(Facebooklike);
	Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "164K likes");
	
	//click vào chat iframe (vì chat thuộc iframe khác, k thuộc iframe 167k like
	//=> cần switch về mainpage
	driver.switchTo().defaultContent();
	
	//->từ mainpage switch về iframe chat
	driver.switchTo().frame("cs_chat_iframe");
	
	driver.findElement(By.cssSelector("div.button_bar")).click();

	
	//nhập dữ liệu vào các field chat
	driver.findElement(By.cssSelector("input.input_name")).sendKeys("automation");
	driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456789");
	new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
	driver.findElement(By.name("message")).sendKeys("tư vấn đi mà");
	sleepInSecond(3);
	
	//search vs tu "excel" //vì đang ở hộp chat về lại search nằm ở mainpage nên phải switch to 
	driver.switchTo().defaultContent();
	driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
	//click search icon
	driver.findElement(By.cssSelector("button.search-button")).click();
	//verify chuyển qua page danh sách khóa học chứa từ excel
	List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
	for(WebElement course: courseName) {
		Assert.assertTrue(course.getText().contains("Excel"));
	}
	
}

@Test
public void TC_09_frame(){
	driver.get("https://netbanking.hdfcbank.com/netbanking/");
	//vì nó nằm trong frame nên cần switch qua frame chứa login textbox
	driver.switchTo().frame("login_page");
	driver.findElement(By.name("fldLoginUserId")).sendKeys("johnathan");
	//click continue
	driver.findElement(By.xpath("//a[text()='CONTINUE']")).click();
	
	//verify password textbox
	Assert.assertTrue(driver.findElement(By.xpath("//input[@type='password']")).isDisplayed());
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
