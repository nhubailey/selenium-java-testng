package Javabasic;

import org.testng.annotations.Test;

public class Topic08_Exercise_String {

	
	public void TC01() {
		String coursename = "Automation Testing Advanced 19 20 21";
		//lấy ra số lượng ký tự in hoa trong chuỗi
		char coursenameArr[]= coursename.toCharArray(); //cần convert chuyển sang mảng char để kiểm tra ký tự
		int countUpper = 0;
		int countLower = 0;
		int countNumber = 0;
		for(char character : coursenameArr) {
			//Uppercase
			if (character <= 'Z' && character >= 'A') {
				countUpper++;
			}
			//Lowercase
			if (character <= 'z' && character >= 'a') {
				countLower++;
			}
			//Number
			if (character <= '9' && character >= '0') {
				countNumber++;
			}
		} 
		System.out.println("Sum of Uppercase = " + countUpper);
		System.out.println("Sum of Lowercase = " + countLower);
		System.out.println("Sum of Numbercase = " + countNumber);
	}
	
	
	public void TC02() {
		String coursename = "Automation Testing 345 Tutorial Online 789";
		int count = 0;
		//đếm số kí tự là a có trong chuỗi trên
		char coursenameArr[]= coursename.toCharArray();
		for(char c : coursenameArr) {
			if (c=='a') {
				count++;
			}	
		}
		System.out.println ("Sum ký tự a = " + count);
		System.out.println("Có chứa bằng 1 ký tự/chuỗi ký tự = " + coursename.contains("Testing"));
	}
	
	@Test
	public void TC03() {
		String coursename = "Automation FC";
		char coursenameArr[]= coursename.toCharArray();
		//cách làm: gán i = độ dài mấy, i >=0, i giảm dần
		for(int i  = coursenameArr.length -1 ; i>=0 ; i-- ) {
			System.out.print(coursenameArr[i]); //lấy thằng cúi cùng in ra trước tức i-- 
		}
	}
}
