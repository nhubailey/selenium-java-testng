package javademo;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic38_Exercise_Javaexecuter {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
JavascriptExecutor jsExecutor;

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
	jsExecutor = (JavascriptExecutor) driver;
	}


public void TC_01_Techpanda(){
	//step1
	navigateToUrlByJS("http://live.techpanda.org/");
	sleepInSecond(5);
	//step2: verify domain
	Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
	//step3: verify url
	Assert.assertEquals(executeForBrowser("return document.URL;"),"http://live.techpanda.org/");
	//step4: open mobile page
	hightlightElement("//a[text()='Mobile']");
	clickToElementByJS("//a[text()='Mobile']");
	sleepInSecond(3);
	//step5: add samsung vào card
	hightlightElement("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
	clickToElementByJS("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button");
	sleepInSecond(3);
	//step6: verify message hiển thị
	Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
	//step7: open customer service page+ verify title
	hightlightElement("//a[text()='Customer Service']");
	clickToElementByJS("//a[text()='Customer Service']");
	sleepInSecond(3);
	//step8: scroll tới Newletter ở cuối trang
	hightlightElement("//input[@id='newsletter']");
	scrollToElementOnDown("//input[@id='newsletter']");
	sleepInSecond(3);
	//step9: input email
	sendkeyToElementByJS("//input[@id='newsletter']", "afc" + getRandomnumber()+ "@gmail.net");
	//step10: click subscribe button
	hightlightElement("//button[@title='Subscribe']");
	clickToElementByJS("//button[@title='Subscribe']");
	sleepInSecond(3);
	//step 11: verify text
	Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
	//step12: navigate tới domain guru + verify domain
	navigateToUrlByJS("https://demo.guru99.com/v4/");
	Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
	sleepInSecond(5);
}

@Test
public void TC_02_validation(){
	driver.get("https://warranty.rode.com/login");
	String createbutton = "//a[text()=' Create an Account ']";
	String registerbutton = "//button[text()=' Register ']";
	String Name = "//input[@id='name']";
	String Email = "//input[@id='email']";
	String Password = "//input[@id='password']";
	String ConfirmPassword = "//input[@id='password_confirmation']";
	
	clickToElementByJS(createbutton);
	sleepInSecond(3);
	
	clickToElementByJS(registerbutton);
	sleepInSecond(3);
	Assert.assertEquals(getElementValidationMessage(Name), "Please fill out this field.");
	
	sendkeyToElementByJS(Name,"john");
	clickToElementByJS(registerbutton);
	sleepInSecond(3);
	
	Assert.assertEquals(getElementValidationMessage(Email), "Please fill out this field.");
	
	sendkeyToElementByJS(Email,"john@gmail.com");
	clickToElementByJS(registerbutton);
	sleepInSecond(3);
	
	Assert.assertEquals(getElementValidationMessage(Password), "Please fill out this field.");
	
	sendkeyToElementByJS(Password ,"123456");
	clickToElementByJS(registerbutton);
	sleepInSecond(3);
	
	Assert.assertEquals(getElementValidationMessage(ConfirmPassword), "Please fill out this field.");
	
	sendkeyToElementByJS(ConfirmPassword ,"123456");
	clickToElementByJS(registerbutton);
	sleepInSecond(3);
}


public Object executeForBrowser(String javaScript) {
	return jsExecutor.executeScript(javaScript);
}
public String getDomainName() {
	return (String) jsExecutor.executeScript("return document.domain;");
}
public String getInnerText() {
	return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
}

public boolean areExpectedTextInInnerText(String textExpected) {
	String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
	return textActual.equals(textExpected);
}

public void scrollToBottomPage() {
	jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
}

public void navigateToUrlByJS(String url) {
	jsExecutor.executeScript("window.location = '" + url + "'");
	sleepInSecond(3);
}

public void hightlightElement(String locator) {
	WebElement element = getElement(locator);
	String originalStyle = element.getAttribute("style");
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
	sleepInSecond(2);
	jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
}

public void clickToElementByJS(String locator) {
	jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	sleepInSecond(3);
}

public void scrollToElementOnTop(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
}

public void scrollToElementOnDown(String locator) {
	jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
}

public void setAttributeInDOM(String locator, String attributeName, String attributeValue) {
	jsExecutor.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(locator));
}

public void removeAttributeInDOM(String locator, String attributeRemove) {
	jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
}

public void sendkeyToElementByJS(String locator, String value) {
	jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
}

public String getAttributeInDOM(String locator, String attributeName) {
	return (String) jsExecutor.executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(locator));
}

public String getElementValidationMessage(String locator) {
	return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
}

public boolean isImageLoaded(String locator) {
	boolean status = (boolean) jsExecutor.executeScript(
			"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
	return status;
}

public WebElement getElement(String locator) {
	return driver.findElement(By.xpath(locator));
}
public int getRandomnumber() {
	return new Random().nextInt(9999);
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
