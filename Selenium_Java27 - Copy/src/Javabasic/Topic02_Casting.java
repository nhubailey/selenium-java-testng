package Javabasic;

public class Topic02_Casting {
	public String studentName;
	
	public String getstudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
		
	}
	public void showStudentName() {
		System.out.println("Student name = " + studentName);
	}
	public static void main(String[] args) {
		Topic02_Casting firstStudent = new Topic02_Casting();
		Topic02_Casting secondStudent = new Topic02_Casting();
		
		firstStudent.setStudentName("Le Hung");
		secondStudent.setStudentName("Vo Ngan");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		//Ép kiểu
		firstStudent = secondStudent;
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
		secondStudent.setStudentName("Quynh Nhu");
		
		firstStudent.showStudentName();
		secondStudent.showStudentName();
		
	}
}
