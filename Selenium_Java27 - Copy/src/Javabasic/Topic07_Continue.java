package Javabasic;

public class Topic07_Continue {

	public static void main(String[] args) {
	
			for(int i = 0; i<10; i++) {
				//loại trừ điều kiện i=5 khi có continue
				if(i==5) {
					continue;
				} System.out.println(i);
		}
	}
}
