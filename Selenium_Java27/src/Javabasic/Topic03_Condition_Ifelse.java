package Javabasic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic03_Condition_Ifelse {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	public void TC_01_If()
	{
		//Hàm if sẽ nhận vào 1 điều kiện đúng
	}
	
	@Test
	public void TC_02_Ifelse()
	{
		//Hàm if sẽ nhận vào 2 điều kiện 
		//đúng => vào phần thân if
		//sai=> vào phần else
		int age =20;
		String access = (age < 18)? "can not access" : "welcome system" ;
		if (age<18) {
			access = "can not access";
		} else {
			access = "welcome system";
		}
		System.out.println(access);
	}
	
	
	@Parameters("browser")
	
	public void TC_03_Ifelse_Ifelse(String browserName){
		//có nhìu điều kiện
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {//safari/edge...
			throw new RuntimeException("please input the new correct browser");
		}
		System.out.println(browserName);
		System.out.println(driver.toString());
		driver.quit();
	}
	
	
	public static void main(String[] args) {
		boolean status = 5 > 3;
		System.out.println(status);
		
		//Hàm if sẽ nhận vào 1 điều kiện đúng
		if (status) {
			//đúng thì trả về phần thân if, sai thì bỏ qua
			System.out.println("Go to if");
		}
		
		WebDriver driver = new FirefoxDriver();
		//Uncheck to check box
		WebElement languagecheckbox = driver.findElement(By.id(""));
		if(languagecheckbox.isSelected()) {
			languagecheckbox.click();
		}
		//Check to checkbox
		
		if(!languagecheckbox.isSelected()) {
			languagecheckbox.click();
		}
	}
}