package net.simplephone;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ContactService extends Service {
    private final IBinder mBinder = new ContactBinder();

    public class ContactBinder extends Binder {
        ContactService getService() {
            // Return this instance of LocalService so clients can call public methods
            return ContactService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
    	
    	Log.i(ContactList.TAG, "Bind the service");
    	
        return mBinder;
    }

    public String getContact(Integer id) {
    	return id.toString();
    }
//    public Contact getContact(Integer id) {
//    	String name = "";
//    	PhoneNumber phoneNumber = null;
//    	List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();
//    	
//    	ContentResolver contentResolver = getContentResolver();
//        String selection = ContactsContract.Contacts._ID + " = "+ id.toString();
//        Cursor contactCursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, selection, null, null);
//        
//        if (contactCursor.moveToFirst()) {
//	      	name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
//	      	
//	      	selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id.toString();
//	      	Cursor phoneNumberCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, selection, null, null);
//	      	
//	      	if (phoneNumberCursor.moveToFirst()) {
//	      		phoneNumber = new PhoneNumber(phoneNumberCursor.getInt(phoneNumberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)), phoneNumberCursor.getString(phoneNumberCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
//	      		
//	      		phoneNumbers.add(phoneNumber);
//	      	}
//	      	
//	      	phoneNumberCursor.close();
//        }
//        
//        
//        contactCursor.close();
//        
//        return new Contact(id, name, phoneNumbers);
//    }
}