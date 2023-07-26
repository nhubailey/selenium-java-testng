package Javabasic;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic03_Excercise_Condition_Ifelse {
	WebDriver driver;
	Scanner scanner= new Scanner(System.in);
	
	
	public void TC_01_sochanle()	{
		int number = scanner.nextInt();
		if (number %2 ==0) {
			System.out.println("So: " + number + " la so chan");
		} else {
			System.out.println("So: " + number + " la so le");
		}
	}
	
	
	public void TC_02_solonbe()	{
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		if (a>=b) {
			System.out.println(a + " lon hon hoac bang " + b);
		} else {
			System.out.println(a + " be hon " + b);
		}
	}
	
	
	public void TC_03_trungten()	{
		String firstname = scanner.nextLine();
		String secondname = scanner.nextLine();
		if (firstname.equals(secondname)) {
			System.out.println("2 nguoi la cung ten");
		} else {
			System.out.println("2 nguoi la khac ten");
		}
	}
	
	
	public void TC_04_solonnhat()	{
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int c = scanner.nextInt();
		if (a>b && a>c) {
			System.out.println(a + " la so lon nhat");
		} else if(b>c) {
			System.out.println(b + " la so lon nhat");
		} else {
			System.out.println(c + " la so lon nhat");
		}
	}
	
	public void TC_05_phamvi()	{
		int a = scanner.nextInt();
		if (10<a && a<100) {
			System.out.println(a + " nam trong 10 - 100");
		} else {
			System.out.println(a + " nam ngoai 10 - 100");
		}
	}
	
	
	public void TC_06_diemso()	{
		//0<5:D
		//5-7.5:C
		//7.5-8.5:B
		//8.5-10:A
		float a = scanner.nextFloat();
		if (8.5<=a && a <=10) {
			System.out.println("xep loai A");
		} else if(7.5<=a && a <8.5) {
			System.out.println("xep loai B");
		} else if(5<=a && a <7.5) {
			System.out.println("xep loai C");
		}else {
			System.out.println("xep loai D");
		}
	}
	
	@Test
	public void TC_07_verifythang()	{
		int month = scanner.nextInt();
		//tháng 1,3,5,7,8,10,12: 31 ngày
		//tháng 2: 28 ngay
		//tháng 4,6,9,11: 30 ngay
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			System.out.println("thang " + month + " co 31 ngay");
		}  else if (month == 4 || month == 6 || month == 9 || month == 11) {
			System.out.println("thang " + month + " co 30 ngay");
		} else if(month == 2){
			System.out.println("thang " + month + " co 28 ngay");
		}
	}
}
	