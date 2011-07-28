package net.simplephone;

import net.simplephone.ContactService.ContactBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

public class ContactInfo extends Activity {

	TextView mName;
	TextView mPhoneNumber;
	TextView mLastCall;
	TextView mLastMessage;
	
	ContactService mService;
    boolean mBound = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_info);
		
		mName = (TextView)findViewById(R.id.name);
		mPhoneNumber = (TextView)findViewById(R.id.phoneNumber);
		mLastCall = (TextView)findViewById(R.id.lastCall);
		mLastMessage = (TextView)findViewById(R.id.lastMessage);
		
		Bundle extras = getIntent().getExtras(); 
		Integer id = (int) extras.getInt(ContactsContract.Contacts._ID);
        
		Log.i(ContactList.TAG, "Start the contact service");
		
		Intent intent = new Intent(this, ContactService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        
        mName.setText(mService.getContact(id));
//        Contact contact = mService.getContact(id);
        
        // mName.setText(contact.getName());
        
//        PhoneNumber contactPhoneNumber = contact.getPhoneNumber(type);
//        
//        if (contactPhoneNumber == null) {
//        	mPhoneNumber.setText("No Phone Number");
//        } else {
//        	mPhoneNumber.setText(contactPhoneNumber.getPhoneNumber());
//            
//            selection = CallLog.Calls.NUMBER + " = '" + contactPhoneNumber.getPhoneNumber() + "'";
//            String sortOrder = CallLog.Calls.DATE + " COLLATE LOCALIZED DESC";
//            Cursor callLogCursor = contentResolver.query(CallLog.Calls.CONTENT_URI, null, selection, null, sortOrder);
//            startManagingCursor(callLogCursor);
//            
//            if (callLogCursor.moveToFirst()) {
//            	Long dateLong = callLogCursor.getLong(callLogCursor.getColumnIndex(CallLog.Calls.DATE));
//            	
//            	if (dateLong == null) {
//            		Log.e("simplePhone", "dateString is null");
//            	}
//            	
//            	Date date = new Date(dateLong);
//    				
//    			mLastCall.setText(date.toGMTString());
//            } else {
//            	mLastCall.setText("No Call Log");
//            }
//            
//            Uri uriSMSURI = Uri.parse("content://sms");
//            selection = "address" + " = '" + contactPhoneNumber.getPhoneNumber() + "'";
//            Cursor messageCursor = contentResolver.query(uriSMSURI, null, selection, null, null);
//            
//            if (messageCursor.moveToFirst()) {
//            	StringBuffer info = new StringBuffer();
//            	for( int i = 0; i < messageCursor.getColumnCount(); i++) {
//            	    info.append("Column: " + messageCursor.getColumnName(i) + " : " + messageCursor.getString(i) + "\n");
//            	}
//
//            	mLastMessage.setText(info);
//            	Log.i(Main.TAG, "Found a message");
//            } else {
//            	Log.e(Main.TAG, "Can't find a message");
//            }
//            
//        }
	}
	
    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            
        	Log.i(ContactList.TAG, "Service Connected");
        	
        	ContactBinder binder = (ContactBinder) service;
            mService = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
