package javaOOP;

public class Topic12_Non_Access_Modifier {
	//static: variable/ method : biến static có thể dc sử dụng trong hàm static
	//dùng cho tất cả các instance/ object
	//phạm vi cho toàn bộ system sử dụng nó
	//có thể overide được
	static String browsername = "chrome";
	
	//Non static:biến non static thì k thể dc sd trong hàm static
	String serveName = "testing";
	
	//Hằng số
	final String colorCar = "red";
	
	final static String REGISTER_BUTTON = "";
	
	public static void main(String[] args) {
		System.out.println(browsername);
		
		browsername ="firefox";
		System.out.println(browsername);
		
		Topic12_Non_Access_Modifier topic = new Topic12_Non_Access_Modifier();
		System.out.println(topic.serveName);
		
		//k được phép gán lại giá trị
		System.out.println(topic.colorCar);
		
		topic.clickToElement("button");
		sendkeyToElement("link");

	}

	//non static
	public void clickToElement(String elementName) {
		System.out.println(elementName);
	}
	//static
	public static void sendkeyToElement(String elementName) {
		System.out.println(elementName);
	}
}
