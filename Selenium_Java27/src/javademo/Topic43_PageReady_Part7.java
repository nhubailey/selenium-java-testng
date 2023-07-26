package javademo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic43_PageReady_Part7 {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;
Actions action;


@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	explicitWait = new WebDriverWait(driver,30);
	action = new Actions(driver);
}
 

public void TC_01_Orangehrm(){
	driver.get("https://api.orangehrm.com/");
	//wait cho icon loading biến mất
	//vì khi nó biến mất thì trang load hết dữ liệu và hiển thị thành công
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader>div")));
	//verify title trang để bik hiển thị trang thành công 
	Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(),"OrangeHRM REST API Documentation");
}


public void TC_02_C1_Ex10(){
	driver.get("https://admin-demo.nopcommerce.com");
	driver.findElement(By.cssSelector("input#Email")).clear();
	driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
	driver.findElement(By.cssSelector("input#Password")).clear();
	driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
	driver.findElement(By.xpath("//button[text()='Log in']")).click();
	
	//wait cho loading icon biến mất khi click log out (mặc dù vẫn còn trong html
	explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	driver.findElement(By.xpath("//a[text()='Logout']")).click();
	//verify chuyển về trag login thành công
	Assert.assertEquals(driver.getTitle(), "Your store. Login");
}



public void TC_02_C2_Ex10(){
	driver.get("https://admin-demo.nopcommerce.com");
	driver.findElement(By.cssSelector("input#Email")).clear();
	driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
	driver.findElement(By.cssSelector("input#Password")).clear();
	driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
	
	//click chuyển trang từ login vào dashboard
	driver.findElement(By.xpath("//button[text()='Log in']")).click();
	Assert.assertTrue(waitforiconloadinginvisible());
	//vì mỗi khi chuyển sang trang khác cái icon loading luôn xuất hiện nên sẽ viết thành 1 hàm
	//wait cho icon loading biến mất
	
	//click chuyển trang từ dashboard về login
	driver.findElement(By.xpath("//a[text()='Logout']")).click();
	Assert.assertTrue(waitforiconloadinginvisible());
	//verify chuyển về trag login thành công
	Assert.assertEquals(driver.getTitle(), "Your store. Login");
}



public void TC_02_C3_Ex10(){
	driver.get("https://admin-demo.nopcommerce.com");
	driver.findElement(By.cssSelector("input#Email")).clear();
	driver.findElement(By.cssSelector("input#Email")).sendKeys("admin@yourstore.com");
	driver.findElement(By.cssSelector("input#Password")).clear();
	driver.findElement(By.cssSelector("input#Password")).sendKeys("admin");
	
	//click chuyển trang từ login vào dashboard
	driver.findElement(By.xpath("//button[text()='Log in']")).click();
	Assert.assertTrue(isPageLoadedSuccess());
	//vì mỗi khi chuyển sang trang khác cái icon loading luôn xuất hiện nên sẽ viết thành 1 hàm
	//wait cho page load complete/loading
	
	//click chuyển trang từ dashboard về login
	driver.findElement(By.xpath("//a[text()='Logout']")).click();
	Assert.assertTrue(isPageLoadedSuccess());
	//verify chuyển về trag login thành công
	Assert.assertEquals(driver.getTitle(), "Your store. Login");
}

@Test
public void TC_03_Ex11(){
	driver.get("https://blog.testproject.io/");
	//step3: hover chuột vào search textbox và wait cho page được ready
	//phải di chuột lên màn hình thì page mới ready nên phải viết action
	action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
	Assert.assertTrue(isPageLoadedSuccess());
	
	driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("selenium");
	driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
	//cần phải viết thêm 1 lệnh wait vì click xong nó đang quay cho cái page home search mất lun thì cái element đang lấy của trang home chứ k phải trang search
	//wait cho page heading visible
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='main-heading' and text() = 'Search Results']")));
	Assert.assertTrue(isPageLoadedSuccess());
	
	//verify title xuất hiện đều chứa từ khóa search
	List<WebElement> searchtitle = driver.findElements(By.cssSelector("h3.post-title >a"));
	for (WebElement article : searchtitle) {
		Assert.assertTrue(article.getText().contains("Selenium"));
	}
}

@AfterClass
public void afterClass() {
	//driver.quit();
	}

public boolean waitforiconloadinginvisible() {
	return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
	}

public boolean isPageLoadedSuccess() {
	WebDriverWait explicitWait = new WebDriverWait(driver, 30);
	JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
		}
	};

	ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		}
	};
	return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
}

}
