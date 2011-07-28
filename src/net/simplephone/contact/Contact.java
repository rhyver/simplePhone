package net.simplephone.contact;

import java.util.List;

import net.simplephone.phonenumber.PhoneNumber;

import android.util.Log;

public class Contact {
	private Integer mId;
	private String mName;
	private List<PhoneNumber> mPhoneNumber;

	public Contact(Integer id, String name, List<PhoneNumber> phoneNumber) {
		setId(id);
		setName(name);
		setPhoneNumber(phoneNumber);
	}
	
	public void setName(String name) {
		this.mName = name;
	}
	public String getName() {
		return mName;
	}

	public void setPhoneNumber(List<PhoneNumber> phoneNumber) {
		this.mPhoneNumber = phoneNumber;
	}

	public PhoneNumber getPhoneNumber(Integer type) {
		for (int i = 0; i < mPhoneNumber.size(); i++) {
			PhoneNumber phonenumber = mPhoneNumber.get(i);
			
			Log.i("simplePhone", "Get the phone number: " + phonenumber.toString());
			
			if (type.equals(phonenumber.getType())) {
				return phonenumber;
			}
		}
		
		Log.e("simplePhone", "Can'f find the phone number for the type: " + type.toString());
		return null;
	}
	
	public List<PhoneNumber> getPhoneNumber() {
		return mPhoneNumber;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public void setId(Integer mId) {
		this.mId = mId;
	}

	public Integer getId() {
		return mId;
	}
}
