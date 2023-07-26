package Javabasic;

import org.testng.annotations.Test;

public class Topic02_Datatype_Exercise {
	
	private static final boolean False = false;
	private static final boolean True = false;
	public void TC01() {
		int a = 6;
		int b = 2;
		System.out.println("TongP1 = " + (a+b));
		System.out.println("HieuP2 = "+ (a-b));
		System.out.println("TichP3 = "+ (a*b));
		System.out.println("ThuongP4 = "+ (a/b));
	}
	
	
	public void TC02() {
	float a  =7.5f;
	float b  =3.8f;
	System.out.println("Dien tich hinh chu nhat P = " + (a*b));
	}
	
@Test
	public void TC03() {
	String name = "Automation Testing" ;
	System.out.println("Hello " + name);
	}
	
	@Test
	public void TC04() {

	//tam nguyên = ? :
	String address = "HCM";
	boolean status = (address == "Ha Noi") ? True : False;
	System.out.println(status);
	}
}
