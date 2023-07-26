package javaOOP;

public class Topic14_Getter_Setter {
	//Kiểm tra / validate dữ liệu được
	public String personName;
	public int personAge;
	public int personPhone;
	private float personBankAccountAmount;
	
	
	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		if(personName == null|| personName.isEmpty()) {
			throw new IllegalArgumentException ("tên nhập vào không hợp lệ");
		} else {
			this.personName = personName;
		}
	}

	public int getPersonAge() {
		return personAge;
	}

	public void setPersonAge(int personAge) {
		if (personAge > 15 && personAge <60) {
			throw new IllegalArgumentException ("tuổi nhập vào không hợp lệ");
		} else {
			this.personAge = personAge;
		}
	}

	public int getPersonPhone() {
		return personPhone;
	}

	public void setPersonPhone(int personPhone) {
		if(String.valueOf(personPhone).startsWith("0")) {  //vì personphone đang là kiểu int nên phải convert qua string
			throw new IllegalArgumentException ("SỐ điện thoại bắt đầu bằng: 09 - 03 - 027");
		} else if (personPhone <10 || personPhone >11) {
			throw new IllegalArgumentException ("SỐ điện thoại phải từ 10 - 11 số");
		} else {
			this.personPhone = personPhone;
		}
	}

	public float getPersonBankAccountAmount() {
		return personBankAccountAmount;
	}

	public void setPersonBankAccountAmount(float personBankAccountAmount) {
		this.personBankAccountAmount = personBankAccountAmount;
	}


	
	public void showPersonInfo() {
		System.out.println(this.personName);
		System.out.println(this.personAge);
		System.out.println(this.personPhone);
	}
}
