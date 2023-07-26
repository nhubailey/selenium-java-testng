package javademo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic33_Exercise_interaction3 {
WebDriver driver;
Actions action;
JavascriptExecutor jsExecutor;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
String DragandDropHelperPath = projectPath + "\\DragAndDrop\\drag_and_drop_helper.js";

@BeforeClass
public void beforeClass() {
	if (osName.contains("Mac OS")) {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
	} else {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	}
	
	driver = new FirefoxDriver();
	action = new Actions(driver);
	jsExecutor = (JavascriptExecutor)driver;
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();	
}

public void TC_06_Doubleclick(){
	driver.get("https://automationfc.github.io/basic-form/index.html");
	//scroll đến element đó
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
	sleepInSecond(2);
	//double click vào button
	action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
	sleepInSecond(2);
	//verify message
	Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}

public void TC_07_Rightclicktoelement(){
	driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	//rightclick vào element click right me
	action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
	sleepInSecond(2);
	//kiểm tra Quit menu hiển thị
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	//holder chuột vào element Quit
	action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	//verify giá trị visble + hover khi hover vào
	Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
	//click vào Quit => dùng action click là vì di chuột đến element rồi click 
	action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
	sleepInSecond(3);
	//click OK trong pop-up
	driver.switchTo().alert().accept();
	//kiểm tra quit menu không còn hiển thị
	Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
}

public void TC_08_DragandDrop(){
	driver.get("https://automationfc.github.io/kendo-drag-drop/");
	WebElement smallcircle = driver.findElement(By.cssSelector("div#draggable"));
	WebElement bigcircle = driver.findElement(By.cssSelector("div#droptarget"));
	//kéo hình tròn nhỏ vào hình tròn lớn
	action.dragAndDrop(smallcircle, bigcircle).perform();
	sleepInSecond(3);
	//Verify message you did great
	Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
	//verify hình tròn lớn chuyển màu
	String bigcirclebackground = bigcircle.getCssValue("background-color");
	System.out.println(bigcirclebackground);
	//convert mau sang hexa
	Assert.assertEquals(Color.fromString(bigcirclebackground).asHex().toUpperCase(), "#03A9F4");
}

@Test
public void TC_09_DragandDrop_css() throws IOException{
	String jsHelper = getContentFile(DragandDropHelperPath); //lưu hết toàn bộ nội dung vào trong biết jsHelper
	
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	
	String sourceCss = "div#column-a";
	String targetCss = "div#column-b";
	//vì site này đã có thư viện jquery rồi nên k cần inject jquery vào site, k  cần tạo ra file jqueryhelper
	jsHelper = jsHelper + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});"; // giả lập hành động lấy ra toàn bộ nội dung jshelper 97 và lấy ra cái $ là cái locator ra r gọi hàm stimulate để đến cái dích target
	
	//Drag A to B
	jsExecutor.executeScript(jsHelper);
	sleepInSecond(3);
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
	
	
	//Drag B to A
	jsExecutor.executeScript(jsHelper);
	sleepInSecond(3);
	
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

	
}

@Test
public void TC_09_DragandDrop_xpath() throws IOException, AWTException{
	
	
	driver.get("https://automationfc.github.io/drag-drop-html5/");
	
	//Drag A to b
	dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
	
	
	//Drag B to A
	dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
	sleepInSecond(3);
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());



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

public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

	WebElement source = driver.findElement(By.xpath(sourceLocator));
	WebElement target = driver.findElement(By.xpath(targetLocator));

	// Setup robot
	Robot robot = new Robot();
	robot.setAutoDelay(500);

	// Get size of elements
	Dimension sourceSize = source.getSize();
	Dimension targetSize = target.getSize();

	// Get center distance
	int xCentreSource = sourceSize.width / 2;
	int yCentreSource = sourceSize.height / 2;
	int xCentreTarget = targetSize.width / 2;
	int yCentreTarget = targetSize.height / 2;

	Point sourceLocation = source.getLocation();
	Point targetLocation = target.getLocation();

	// Make Mouse coordinate center of element
	sourceLocation.x += 20 + xCentreSource;
	sourceLocation.y += 110 + yCentreSource;
	targetLocation.x += 20 + xCentreTarget;
	targetLocation.y += 110 + yCentreTarget;

	// Move mouse to drag from location
	robot.mouseMove(sourceLocation.x, sourceLocation.y);

	// Click and drag
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mousePress(InputEvent.BUTTON1_MASK);
	robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

	// Move to final position
	robot.mouseMove(targetLocation.x, targetLocation.y);

	// Drop
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
}

public String getContentFile(String filePath) throws IOException {
	Charset cs = Charset.forName("UTF-8");
	FileInputStream stream = new FileInputStream(filePath);
	try {
		Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[8192];
		int read;
		while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
			builder.append(buffer, 0, read);
		}
		return builder.toString();
	} finally {
		stream.close();
	}
}
}