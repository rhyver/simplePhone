package net.simplephone.phonenumber;


public class PhoneNumber {
	private Integer mType;
	private String mPhoneNumber;
	
	public PhoneNumber(Integer type, String phoneNumber) {
		setType(type);
		setPhoneNumber(phoneNumber);
	}
	
	public PhoneNumber(String phoneNumber) {
		setPhoneNumber(phoneNumber);
	}
	
	public void setType(Integer type) {
		this.mType = type;
	}
	public Integer getType() {
		return mType;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.mPhoneNumber = phoneNumber;
	}
	
	public String getPhoneNumber() {
		return mPhoneNumber;
	}
	
	@Override
	public String toString() {
		return getPhoneNumber();
	}
}
