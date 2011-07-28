package net.simplephone.phonenumber;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.provider.ContactsContract;

public class PhoneNumberController extends Service{

	private final IBinder mBinder = new controllerBinder();
	
	public class controllerBinder extends Binder {
        public PhoneNumberController getService() {
            // Return this instance of LocalService so clients can call public methods
            return PhoneNumberController.this;
        }
    }
	
	public List<PhoneNumber> getPhoneNumbers(Integer id) {
		PhoneNumber phoneNumber;
		List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
		
		ContentResolver contentResolver = getContentResolver();
		String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id.toString();
      	Cursor phoneNumberCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, selection, null, null);
      	
      	if (phoneNumberCursor.moveToFirst()) {
      		phoneNumber = new PhoneNumber(phoneNumberCursor.getInt(phoneNumberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)), phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
      		
      		phoneNumbers.add(phoneNumber);
      	}
      	
      	return phoneNumbers;
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
}
