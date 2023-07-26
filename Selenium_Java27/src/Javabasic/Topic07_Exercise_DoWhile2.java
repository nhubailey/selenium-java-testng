package Javabasic;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic07_Exercise_DoWhile2 {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
	}
	
	
	public static void TC_2_while_chiahet35() {
		int A = scanner.nextInt();

		while (A<100) {
			//chia hết cho 3&5
			if(A %3 == 0 && A %5 == 0) {
			System.out.println(A);
			} A++; //để A++ bên trong k đúng vì nếu A 15 và tăng A lên 16 thì nó kiểm tra 16<100 nhưng k vào thân if được nên nó k thoát được vòng while
		}		
	}
	
	
	public static void TC_3_while_tongsole() {
		int A = scanner.nextInt();
		int i = 0;
		
		while (A>0) {
			if(A %2 != 0 ) {
			System.out.println(A);
			i = i + A; //i+=A
			}A-- ;
		} System.out.println(i);;
	}
	
	@Test
	public static void TC_5_while_giaithua() {
		int A = scanner.nextInt();
		int i = 1;
		
		while (A>0) {
			System.out.println(A);
			i = i * A; 
			A-- ;
			}  System.out.println(i);;
	}
}
