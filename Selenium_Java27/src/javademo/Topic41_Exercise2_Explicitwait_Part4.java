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
public class Topic41_Exercise2_Explicitwait_Part4 {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;

String dulichfilename = "dulich.jpg";
String Computerfilename = "computer.jpg";
String HaLongfilename = "HaLong.jpg";

String dulichfilepath = projectPath + "\\UploadFile\\" + dulichfilename;
String Computerfilepath = projectPath + "\\UploadFile\\" + Computerfilename;
String HaLongfilepath = projectPath + "\\UploadFile\\" + HaLongfilename;

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

@Test
public void TC_07_Uploadfile(){
	driver.get("https://gofile.io/uploadFiles");
	explicitWait = new WebDriverWait(driver,45);
	
	//wait cho add file button visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#filesUpload button.filesUploadButton")));
	
	driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dulichfilepath + "\n" + Computerfilepath + "\n" + HaLongfilepath);
	
	//tc2: upload các file và verify file được up lên thành công
	//wait cho các loading icon của từng file biến mất
	explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.mainUploadFilesListDetails div.progress"))));
	
	//wait cho upload message thành công được visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row justify-content-center mainUploadSuccess']//div[contains(@class,'text-white')]")));
	//verify message hiển thị
	Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row justify-content-center mainUploadSuccess']//div[contains(@class,'text-white')]")).isDisplayed());
	
	//tc3: click dowload link
	//wait cho dowload link được click + click luôn
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();
	
	//tc6: chuyển qua tab windown mới=> verify có hiển thị icon dowload and play ở từng file
	//wait + verify  file name và button dowload  hiển thị
	
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + Computerfilename + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + dulichfilename + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + HaLongfilename + "']/parent::a/parent::div/following-sibling::div//span[text()='Download']"))).isDisplayed());

	//wait + verify  file name và button play  hiển thị

	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + Computerfilename + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + dulichfilename + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']"))).isDisplayed());
	Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + HaLongfilename + "']/parent::a/parent::div/following-sibling::div//span[text()='Play']"))).isDisplayed());
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
