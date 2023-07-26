package Javabasic;

import org.testng.annotations.Test;

public class Topic07_Exercise_Array {
	int number[] = {3,-7,2,5,9,-6,10,60,80,33,44};
	
	
	public void TC1_maxnumber() {
		int x = 0;
		for(int i = 0; i < number.length; i++) {
			if (x < number[i]) {
				x = number[i];
			}
		}
		System.out.println("Max number = " + x);
	}
	
	
	public void TC2_Sum_FirstandLastnumner() {
		System.out.println(number[0]); //bắt đầu của số 0 (first number) là 3
		System.out.println(number[number.length-1]); //kết thúc của dãy là 44
		System.out.println(number[0] + number[number.length-1]);

	}
	
	
	public void TC3_sochan() {
		for(int i = 0; i < number.length; i++) {
			if (number[i]%2==0) {
				System.out.println("So chan = " + number[i]);
			}
		}
	}
	
	@Test
	public void TC4_tongsolelonhon0() {
		int x = 0;
		int sum = 0;
		for(int i = 0; i < number.length; i++) {
			if (number[i]>=0 && number[i]%2!=0) {
				x = number[i];	
				System.out.println("Các số lẻ = " + x);	
				sum +=x;
				//1: sum = 0+3 =3;
				//2: sum = 3 + 5 =8;
				//3: sum = 8+9 = 17;
				//4: sum =17+33 = 50;
			}	
		}System.out.println("tổng Các số lẻ = " + sum);	
	}
	
	
	public void TC5_solonhon0vabehon10() {
		for(int i = 0; i < number.length; i++) {
			if (number[i]>=0 && number[i]<10) {
				System.out.println("So trong khoang (0-10) = " + number[i]);
			}
		}
	}
	
	
	
}
	