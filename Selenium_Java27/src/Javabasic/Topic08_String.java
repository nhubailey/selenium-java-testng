package Javabasic;

public class Topic08_String {

	public static void main(String[] args) {
		String schoolname = "Automation testing";
		String coursename = "AUTOMATION TESTING";
		String schooladdress = "Ho Chi Minh city"; 
	
		System.out.println("Độ dài = " + schoolname.length());
		System.out.println("Lấy ra 1 ký tự = " + schoolname.charAt(0));
		System.out.println("Nối 2 chuỗi = " + schoolname + " " + schooladdress);
		System.out.println("Nối 2 chuỗi = " + schoolname.concat(schooladdress));
		
		//Tuyệt đối
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tuyệt đối = " + schoolname.equals(schooladdress));
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tuyệt đối = " + schoolname.equals("Automation testing"));
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tuyệt đối = " + schoolname.equals(coursename));
		//Tương đối
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tượng đối = " + schoolname.equalsIgnoreCase(coursename));
		//Start with/end with/ contain
		System.out.println("Có bắt đầu bằng 1 ký tự/chuỗi ký tự = " + schoolname.startsWith("A"));
		System.out.println("Có bắt đầu bằng 1 ký tự/chuỗi ký tự = " + schoolname.startsWith("Automation"));
		System.out.println("Có bắt đầu bằng 1 ký tự/chuỗi ký tự = " + schoolname.startsWith("T"));
	
		System.out.println("Có chứa bằng 1 ký tự/chuỗi ký tự = " + schoolname.contains("A"));
		System.out.println("Có chứa bằng 1 ký tự/chuỗi ký tự = " + schoolname.contains("testing"));
	
		System.out.println("Có kết thúc bằng 1 ký tự/chuỗi ký tự = " + schoolname.endsWith("g"));
		System.out.println("Có kết thúc bằng 1 ký tự/chuỗi ký tự = " + schoolname.endsWith("testing"));
		System.out.println("Có kết thúc bằng 1 ký tự/chuỗi ký tự = " + schoolname.endsWith("Automation"));
		//index
		System.out.println("Vị trí của 1 ký tự/chuỗi ký tự trong chuỗi = " + schoolname.indexOf("A"));
		System.out.println("Vị trí của 1 ký tự/chuỗi ký tự trong chuỗi = " + schoolname.indexOf("utomation"));
		System.out.println("Vị trí của 1 ký tự/chuỗi ký tự trong chuỗi = " + schoolname.indexOf("testing"));
		System.out.println("Vị trí của 1 ký tự/chuỗi ký tự trong chuỗi = " + schoolname.indexOf("g"));
		//tách chuỗi ký tự
		System.out.println("Tách 1 ký tự/chuỗi ký tự trong chuỗi = " + schoolname.substring(11));
		System.out.println("Tách 1 ký tự/chuỗi ký tự trong chuỗi = " + schoolname.substring(11,15));
		//split: tách chuỗi thành mảng dựa vào ký tư/ chuỗi ký tự
		String result = "View 48 of 132 results";
		String results[]= result.split(" "); //tách khoảng trắng
		System.out.println(results[1]);
		//Replace
		String productprice = "$100.00";
		productprice = productprice.replace("$", "");
		System.out.println(productprice); //đây là kiểu chuỗi nên k tính toán được
		
		//Sắp xếp nó: sort data (Asc/dsec)
		float productpriceF = Float.parseFloat(productprice);
		System.out.println(productpriceF); //=>dùng float mới tính toán được vì là kiểu số thực
		//từ số convert qua chuỗi
		productprice =  String.valueOf(productpriceF);
		System.out.println(productprice); 
		
		//Loại trừ khoảng trắng/ xuống dòng/tab
		String helloworld = "   \n   \t  Hello World!        ";
		System.out.println(helloworld.trim());
		
		//blank/empty
		helloworld = "  ";
		System.out.println("Empty =  " + helloworld.isEmpty());
		System.out.println("Blank =  " + helloworld.isBlank()); //blank là vẫn chứa ký tự khoảng trắng
		
		//Dynamic locator
		//Đại diện cho 1 chuỗi: %s
		//%b %t %d
		String dynamicButtonXpath = "//button[@id='%s']";
		System.out.println("Click to Login btn = " + dynamicButtonXpath.format(dynamicButtonXpath, "login"));
		System.out.println("Click to Search btn = " + dynamicButtonXpath.format(dynamicButtonXpath, "search"));
	}

}
