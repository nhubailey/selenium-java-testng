package Javabasic;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic04_Excercise_Switchcase {
	WebDriver driver;
	Scanner scanner= new Scanner(System.in);
	
	
	public void TC_03_verifythang()	{
		int month = scanner.nextInt();
		//tháng 1,3,5,7,8,10,12: 31 ngày
		//tháng 2: 28 ngay
		//tháng 4,6,9,11: 30 ngay
		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 112:
				System.out.println("thang " + month + " co 31 ngay");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("thang " + month + " co 30 ngay");
				break;
			case 2:
				System.out.println("thang " + month + " co 28 ngay");
				break;
			default:
				System.out.println("vui long nhap thang trong khoang 1 den 12");
				break;
		}
	}
	
	
	public void TC_01_nhapso()	{
		int a = scanner.nextInt();
		switch(a){
			case 1:
				System.out.println("One");
				break;
			case 2:
				System.out.println("Two");
				break;
			case 3:
				System.out.println("Three");
				break;
			case 4:
				System.out.println("Four");
				break;
			case 5:
				System.out.println("Five");
				break;
			case 6:
				System.out.println("Six");
				break;
			case 7:
				System.out.println("Seven");
				break;
			case 8:
				System.out.println("Eight");
				break;
			case 9:
				System.out.println("Nine");
				break;
			case 10:
				System.out.println("Ten");
				break;
			default:
				System.out.println("vui long nhap so trong khoang 1 den 10");
				break;
		}
	}
	
	@Test
	public void TC_02_pheptoan()	{
		int firstnumber = scanner.nextInt();
		int secondnumber = scanner.nextInt();
		String operator = scanner.next();
		switch(operator){
			case "+":
				System.out.println("a + b = " + (firstnumber + secondnumber));
				break;
			case "-":
				System.out.println("a - b = " + (firstnumber - secondnumber));
				break;
			case "*":
				System.out.println("a * b = " + (firstnumber * secondnumber));
				break;
			case "/":
				System.out.println("a / b = " + (firstnumber / secondnumber));
				break;
			default:
				break;
		}
	}
}
	