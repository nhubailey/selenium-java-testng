package Javabasic;

import org.testng.annotations.Test;

public class Topic02_Operator_Exercise {

	@Test
	public void TC02() {
		int a = 5;
		int b = 6;
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println(a);
		System.out.println(b);
	}
}
