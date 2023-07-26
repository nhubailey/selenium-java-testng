package Javabasic;

import org.testng.annotations.Test;

public class Topic07_Exercise2_Array {
	int number[] = {3,-7, 5,7,30,10,5,8,23,0,-5};
	
	@Test
	public void TC6_trungbinhcong() {
		int sum = 0;
		for(int i = 0; i < number.length; i++) {
			sum += number[i]; //sum = sum + number[i] 
			// run lần 1: sum = 0 + 3 = 3
			// run lần 2: sum = 3 + (-7) = -4
			System.out.println("Chi tiet Tong cac so trong mang moi lan sum= " + sum);
		}
				System.out.println("Tong cac so trong mang= " + sum);
				System.out.println("trung binh cong= " + sum/number.length);	
	}
}
	
	
	

	