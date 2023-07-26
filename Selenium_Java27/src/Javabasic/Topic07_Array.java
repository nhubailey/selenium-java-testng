package Javabasic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic07_Array {

	public static void main(String[] args) {
		//int student[]= {12, 7, 9, 5, 8};
		//lấy ra phần tử đầu tiên
		//System.out.println(student[0]);
		//lấy ra phần tử thứ 6
		//System.out.println(student[6]);
		
		
		//cố định
		//được define cố định bao nhiu phần tử khi mình viết code
		String studentname[]= {"Hoa","Le","Lan"};
		//studentname[0]="My";
		
		for(int i=0; i<studentname.length;i++) {
			if(studentname[i].equals("Lan")) {
				System.out.println("click vao");
			}	
		}
		
		for(String std : studentname) {
			if(std.equals("Lan")) {
				System.out.println("click vao Lan");
			}	
		}
		
		
		//Tĩnh
		ArrayList<String> stdName = new ArrayList<String>();
		//khi chạy code mới add
		for(String std : studentname) {
		stdName.add(std);
		} 
		
		//Chuyển từ array qua list
		List<String> names = Arrays.asList("A","B","C");
		for(String name : names) {
			System.out.println("Name: " + name);
		}		
	}
}	

