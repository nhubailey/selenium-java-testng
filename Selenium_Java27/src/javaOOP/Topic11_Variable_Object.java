package javaOOP;

public class Topic11_Variable_Object {
	//Tạo biến gồm:
	//Access Modifier
	//Data type
	//Variable name
	//Variable value
	private String studentname ="Auto"; //Biến toàn cục (global variable): được khai báo trong phạm vi class 
	
	
	//Static variable: dùng và gán lại được ngoài class
	public static String studentAddress = "HCM";
	//dùng trong phạm vi class này thôi (cho tất cả instance /object dùng)
	private static String studentphone = "0905111111";
	
	//Final variable:  k cho phép gán lại/ k override lại
	//Cố định dữ liệu k được phép thay đổi trong quá trình chạy
	final String country = "VN";
	
	//Static final variable: hằng số (constant)
	//nó k được ghi đè
	//có thể truy cập rộng rãi cho tất cả các instance/ thread
	static final float PI_NUMBER = 3.1444555f;
	
	
	//Access modifier: default
	int studentID = 100;
	
	//hàm (method) - static
	public static void main(String[] args) {
		//Local variable: biến cục bộ : dùng trong hàm
		String studentname = "Auto";
		if (studentname.startsWith("Auto")) {
			//Local variable: biến cục bộ : dùng trong blockcode
			int number = 100;
		}
		
		//biến public static String ... có thể được dùng trực tiếp vào hàm public static mà không cần thông qua đối tượng
		studentAddress = "HCM"; 
		studentphone = "0905121212111";
	}
	
	//Constructor
	public Topic11_Variable_Object() {
		//Local variable: biến cục bộ : dùng trong constructor
		String studentname = "Auto";
	}
	
	//Hàm ( method) - non static
	public void display() {
		//Local variable: biến cục bộ : dùng trong hàm
		String studentname = "Auto";
	}
}
