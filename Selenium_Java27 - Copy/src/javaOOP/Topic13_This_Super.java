package javaOOP;

public class Topic13_This_Super {
	private int firstnumber;
	private int secondnumber;
	
	public Topic13_This_Super(int fnumber, int snumber) {
	firstnumber = fnumber;
	secondnumber = snumber;
	}
	
	public void sumNumber() {
		System.out.println(firstnumber + secondnumber);
	}

	public static void main(String[] args) {
		 Topic13_This_Super topic = new Topic13_This_Super( 15,7);
		 topic.sumNumber();

	}

}
