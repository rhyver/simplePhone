package net.simplephone.contact;

import java.util.List;

import net.simplephone.MainTab;
import net.simplephone.phonenumber.PhoneNumber;
import net.simplephone.phonenumber.PhoneNumberController;
import android.app.Service;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Binder;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

public class ContactController extends Service{
	private final IBinder mBinder = new controllerBinder();
	PhoneNumberController mPhoneNumberController;
    boolean mBound = false;
    
	public class controllerBinder extends Binder {
        public ContactController getService() {
            // Return this instance of LocalService so clients can call public methods
            return ContactController.this;
        }
    }
	
	public Contact getContact(PhoneNumber phonenumber) {
		Log.i(MainTab.TAG, "Get Contact by Phonenumber: " + phonenumber.getPhoneNumber());
		
		String selection = ContactsContract.CommonDataKinds.Phone.NUMBER + " = '" + phonenumber.getPhoneNumber() + "'";
		
		return getContact(selection);
	}
	
	public Contact getContact(String selection) {
		Contact contact = null;
		ContentResolver contentResolver = getContentResolver();
		Cursor contactCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, selection, null, null);
		
		if (contactCursor.moveToFirst()) {
			Intent intent = new Intent(this, PhoneNumberController.class);
	        bindService(intent, mPhoneNumberConnection, Context.BIND_AUTO_CREATE);
	        
			Integer contactId = contactCursor.getInt(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
			String contactName = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			List<PhoneNumber> phonenumbers = mPhoneNumberController.getPhoneNumbers(contactId);
			
			contact = new Contact(contactId, contactName, phonenumbers);
		} else {
			Toast.makeText(getApplicationContext(), "Can't find a Contact for this Phonenumber", Toast.LENGTH_LONG).show();
		}
		
		return contact;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i(MainTab.TAG, "Bind ContactController");
		return mBinder;
	}

	private ServiceConnection mPhoneNumberConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            PhoneNumberController.controllerBinder binder = (PhoneNumberController.controllerBinder) service;
            mPhoneNumberController = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
