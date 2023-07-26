package javademo;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//demo
public class Topic41_ImplicitExplicit_Part5 {
WebDriver driver;
String osName = System.getProperty("os.name");
String projectPath = System.getProperty("user.dir");
WebDriverWait explicitWait;
JavascriptExecutor jsExecutor;


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
 

public void TC_01_Element_Found(){
	//Case 1: tìm thấy element và k cần chờ hết timeout của cả 2 loại
	//element có xuất hiện và k cần chờ hết timeout
	//dù có set cả 2 loại wait thì không ảnh hưởng
	explicitWait = new WebDriverWait(driver,10);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	//Implicit wait: chỉ apply cho việc find element/ findelements
	//Explicit wait: cho các điều kiện của element
	driver.get("https://vi-vn.facebook.com/");
	//explicit
	System.out.println("Thời gian bắt đầu của explicit : " + getTimeStamp());
	explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
	System.out.println("Thời gian kết thúc của explicit : " + getTimeStamp());

	//implicitwait
	System.out.println("Thời gian bắt đầu của implicit : " + getTimeStamp());
	driver.findElement(By.cssSelector("input#email"));
	System.out.println("Thời gian kết thúc của implicit : " + getTimeStamp());
}	


public void TC_02_Element_NotFound_Implicit(){
	//Case 2: k tìm thấy element 

	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	driver.get("https://vi-vn.facebook.com/");
	
	//implicitwait
	System.out.println("Thời gian bắt đầu của implicit : " + getTimeStamp());
	try {
		driver.findElement(By.cssSelector("input#quynhnhu"));
	} catch (Exception e) {
		System.out.println("Thời gian kết thúc của implicit : " + getTimeStamp());
	}

	//output nếu tìm k ra element (trường hợp k dùng try catch vì try catch là trả kết quả time chứ k đánh fail)
	//cơ chế tìm lại sau mỗi 0.5s
	//khi hết timeout sẽ đánh fail case này
	//throw ra 1 expection: no such element

}	


public void TC_03_Element_NotFound_Implicit_Explicit(){
	//Case 3: k tìm thấy element 
	//3.1 Implicit = Explicit
	//3.2 Implicit < Explicit  (sau khi implicit chạy xong r explicit mới chạy ở giây thứ 1)
	//3.3 Implicit > Explicit
	//imlicit luôn chạy trước vì nó ưu tiên tìm element trước

	explicitWait = new WebDriverWait(driver,5);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
	driver.get("https://vi-vn.facebook.com/");
	
	//explicit
	System.out.println("Thời gian bắt đầu của explicit : " + getTimeStamp());
	try {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#quynhnhu")));
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Thời gian kết thúc của explicit : " + getTimeStamp());
	}
	

}	


public void TC_04_Element_NotFound_Explicit_By(){
	//Case 4: k tìm thấy element 

	explicitWait = new WebDriverWait(driver,5);

	driver.get("https://vi-vn.facebook.com/");
	
	//explicit
	//By là tham số nhận vào của hàm explicit
	//implicit k set tức = 0
	//total time lấy của explicit
	System.out.println("Thời gian bắt đầu của explicit : " + getTimeStamp());
	try {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#quynhnhu")));
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Thời gian kết thúc của explicit : " + getTimeStamp());
	}

}	

@Test
public void TC_05_Element_NotFound_Explicit_Element(){
	//Case 5: k tìm thấy element 

	explicitWait = new WebDriverWait(driver,5);

	driver.get("https://vi-vn.facebook.com/");
	
	//explicit
	//Element là tham số nhận vào của hàm explicit
	//implicit k set tức = 0
	//total time lấy của explicit
	System.out.println("Thời gian bắt đầu của explicit : " + getTimeStamp());
	try {
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#quynhnhu"))));
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Thời gian kết thúc của explicit : " + getTimeStamp());
	}
	
}	//case này chạy hết 0s và dùng timeout của implicit

@AfterClass
public void afterClass() {
	//driver.quit();
	}

//show ra time-stamp tại thời điểm gọi hàm này
//time-stamp = ngày giờ phút giây
public String getTimeStamp() {
	Date date = new Date(0);
	return date.toGMTString();
}
}
