package Javabasic;

public class Topic07_DoWhile {

	public static void main(String[] args) {
		int i = 0;
		// Block code
		for (i=0;i<5;i++) {
			System.out.println("For: " + i);
		}
		
		//biến i sau khi done vòng for
		System.out.println("biến i sau khi done vòng for " + i);
		
		//i=5 k t/m dieu kien cua while
		while (i<5) {
			System.out.println("While: " + i);
			i++;
		}
		
		//chạy ít nhất 1 lần rồi mới kiểm tra điều kiện
		do {
			System.out.println("Do-While: " + i);
			i++;
		} while (i<5);
	}

}
