package Javabasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic07_Exercise_DoWhile {
	static Scanner scanner = new Scanner(System.in);
	

	@Test
	public static void TC_1_for() {
		int number = scanner.nextInt();
		
		for (int i = number; i<100; i++) {
			//chia hết cho 2
			if(i%2==0) {
			System.out.println(i);
			}
		}		
	}
	
	@Test
	public static void TC_1_while() {
		int number = scanner.nextInt();
		
		while (number <100) {
			//chia hết cho 2
			if(number %2 == 0) {
			System.out.println(number);
			number++;
			}
		}		
	}
	
	@Test
	public static void TC_1_dowhile() {
		int number = scanner.nextInt();
		
		do {
			if(number %2 == 0) {
			System.out.println(number);
			number++;
			}
		} while (number <100);		
	}
	
	@Test
	public static void TC_2_while_chiahet35() {
		int A = scanner.nextInt();
		int B = scanner.nextInt();
		
		while (A<B) {
			//chia hết cho 3&5
			if(A %3 == 0 && B %5 == 0) {
			System.out.println(A);
			} A++;
		}		
	}
}
