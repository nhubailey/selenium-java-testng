package javademo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic39_Exercise_Uploadfile {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
JavascriptExecutor jsExecutor;

String dulichfilename = "dulich.jpg";
String Computerfilename = "computer.jpg";
String HaLongfilename = "HaLong.jpg";

String dulichfilepath = projectPath + "\\UploadFile\\" + dulichfilename;
String Computerfilepath = projectPath + "\\UploadFile\\" + Computerfilename;
//D:\Selenium Webdriver\Selenium_Java27\UploadFile\computer.jpg
String HaLongfilepath = projectPath + "\\UploadFile\\" + HaLongfilename;

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	}
	
	driver = new ChromeDriver();

	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	jsExecutor = (JavascriptExecutor) driver;
	}


public void TC_01_Single_Uploadfile(){
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//Load file lên
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dulichfilepath);
	sleepInSecond(1);
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(Computerfilepath);
	sleepInSecond(1);
	//C2:driver.findElement(By.xpath("//input[@type='file']")).sendKeys("C:\\Users\\Nhu Ta\\Pictures\\UpLoadFile\\HaLong.jpg");
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(HaLongfilepath);
	sleepInSecond(1);
	
	//verify file được load lên thành công
	//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='dulich.jpg']")).isDisplayed()); verify cách này khi tên file thay đổi dễ bị lỗi
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dulichfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + Computerfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + HaLongfilename + "']")).isDisplayed());
	
	//click để upload => vì start up 3 file nên sẽ khai báo vào 1 list
	List<WebElement> ButtonUploadFile = driver.findElements(By.cssSelector("table button.start"));
	for(WebElement button : ButtonUploadFile) {
		button.click();
		sleepInSecond(3);
	}
	
	//verify file được upload thành công (link)
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dulichfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + Computerfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + HaLongfilename + "']")).isDisplayed());
	
	//verify file được upload thành công (hình)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + dulichfilename + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + Computerfilename + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + HaLongfilename + "')]"));
	
}

@Test
public void TC_01_Multiple_Uploadfile(){
	driver.get("https://blueimp.github.io/jQuery-File-Upload/");
	//Load file lên
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dulichfilepath + "\n" + Computerfilepath + "\n" + HaLongfilepath);
	sleepInSecond(1);

	
	//verify file được load lên thành công
	//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='dulich.jpg']")).isDisplayed()); verify cách này khi tên file thay đổi dễ bị lỗi
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dulichfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + Computerfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + HaLongfilename + "']")).isDisplayed());
	
	//click để upload => vì start up 3 file nên sẽ khai báo vào 1 list
	List<WebElement> ButtonUploadFile = driver.findElements(By.cssSelector("table button.start"));
	for(WebElement button : ButtonUploadFile) {
		button.click();
		sleepInSecond(3);
	}
	
	//verify file được upload thành công (link)
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + dulichfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + Computerfilename + "']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + HaLongfilename + "']")).isDisplayed());
	
	//verify file được upload thành công (hình)
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + dulichfilename + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + Computerfilename + "')]"));
		Assert.assertTrue(isImageLoaded("//img[contains(@src,'" + HaLongfilename + "')]"));
	
}




public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
	return status;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
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
