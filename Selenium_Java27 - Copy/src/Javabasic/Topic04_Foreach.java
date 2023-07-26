package Javabasic;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class Topic04_Foreach {
	WebDriver driver;
	

	public void TC_01_For()	{
		for(int i = 0; i<5; i++) {
			System.out.println(i);
		}
	}
	
	public void TC_02_For()	{
		//List<WebElement> links = driver.findElement(By.id(""));
		//links.size();
		
		//Aray
		String[] cityName = {"Ha Noi", "HCM", "Da Nang", "Can Tho"};
		
		//Aray / List / Set / Quêu (index)
		//For kết hợp if: thỏa mãn điều kiện mới action
		//Biến đếm - dùng để điều kiện để filter
		
	
		for(int i = 0; i < cityName.length; i++) {
			System.out.println(cityName[i]);
		}
		
		for(int i = 0; i < cityName.length; i++) {
			if(cityName[i].equals("Da Nang")) {
				//Action
				System.out.println("Do action");
				break; 
				//dùng break: chạy tới đà nẵng sẽ dừng
				//k dùng break: quét hết tất cả cityname . Tất cả giá trị element đều được chạy qua dù thỏa mãn điều kiện r
			}
		}
		
		
		for(int i = 1; i <= cityName.length; i++) {
			System.out.println(cityName[i]);
		}
	
	}
	

	public void TC_02a_For()	{
		String[] cityName = {"Ha Noi", "HCM", "Da Nang", "Can Tho"};
		//dùng để chạy qua hết tất cả các giá trị
				for (String city : cityName) {
					System.out.println(city);
				}
	}
	
	@Test
	public void TC_02b_Foreach()	{
		//Array
		String[] cityName = {"Ha Noi", "HCM", "Da Nang", "Can Tho"};
		
		//java collection
		//Class: ArrayList/ LinkedList/ Vector...
		//Interface: List/ Set / Queue
		List<String> cityAddress = new ArrayList<String>();
		System.out.println(cityAddress.size());
		
		//Compile (coding)
		cityAddress.add("Quang Nam");
		cityAddress.add("Bac Giang");
		
		System.out.println(cityAddress.size());
		
		//Runtime (running)
			for (String city : cityName) {
				cityAddress.add(city);
			}
			
			System.out.println(cityAddress.size());
			for (String cityAdd : cityName) {
				System.out.println(cityAddress);
			
			}
			
			
	}
}

	