package javaOOP;

public class Topic11_Class_Object {
	public int studentID;
	private String studentName;
	private float diemlythuyet;
	private float diemthuchanh;
	
	private int getStudentID() {
		return studentID;
	}

	private void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	private String getStudentName() {
		return studentName;
	}

	private void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	private float getDiemlythuyet() {
		return diemlythuyet;
	}

	private void setDiemlythuyet(float diemlythuyet) {
		this.diemlythuyet = diemlythuyet;
	}

	private float getDiemthuchanh() {
		return diemthuchanh;
	}

	private void setDiemthuchanh(float diemthuchanh) {
		this.diemthuchanh = diemthuchanh;
	}
	
	private float getTrungbinh() {
		return (this.diemlythuyet + this.diemthuchanh*2) / 3;
	}
	
	private void studentinfo () {
		System.out.println("**************************");
		System.out.println("Student ID = " + getStudentID());
		System.out.println("Student Name = " + getStudentName());
		System.out.println("Diem Ly Thuyet = " + getDiemlythuyet());
		System.out.println("Diem Thuc Hanh = " + getDiemthuchanh());
		System.out.println("Diem Trung Binh = " + getTrungbinh());
		System.out.println("**************************");
	}
	
	public static void main(String[] args) {
		Topic11_Class_Object firststudent = new Topic11_Class_Object();
		firststudent.setStudentID(205206);
		firststudent.setStudentName("Hung");
		firststudent.setDiemlythuyet(4.5f);
		firststudent.setDiemthuchanh(8.0f);
		firststudent.studentinfo();

		Topic11_Class_Object secondstudent = new Topic11_Class_Object();
		secondstudent.setStudentID(205207);
		secondstudent.setStudentName("Lan");
		secondstudent.setDiemlythuyet(5.2f);
		secondstudent.setDiemthuchanh(8.4f);
		secondstudent.studentinfo();
	}
}
