package Javabasic;

public class Topic01_Datatype {
	static int studentNumber;
	static boolean statusValue;
	static final String browserName = "chrome";
	
	String studentName ="Auto FC";
	public static void main(String[]args) {
		System.out.println(studentNumber);
		System.out.println(statusValue);
	}
	
	//Getter: getcurrenturl/getTitle/getText/getAttribute/getCSSvalue/getSize...
	public String getStudentName() {
		return this.studentName;
	}
	//Setter: click/senkey/clear/select/back/forward/get....
	public void setStudentName(String stdName) {
		this.studentName = stdName;
	}
}
