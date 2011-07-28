package net.simplephone;

import net.simplephone.contact.ContactController;
import net.simplephone.phonenumber.PhoneNumber;
import android.app.ListActivity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.CallLog;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class HistoryList extends ListActivity{

	ContactController mContactController;
    boolean mBound = false;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		fillList();
	}

	private void fillList() {
		Intent intent = new Intent(this, ContactController.class);
        bindService(intent, mContactConnection, Context.BIND_AUTO_CREATE);
        
		ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(CallLog.Calls.CONTENT_URI, null, null, null, null);
        
        String[] from = new String[] {mContactController.getContact(new PhoneNumber(CallLog.Calls.NUMBER).getPhoneNumber()).getName()};
        int[] to = new int[] {android.R.id.text1};
        
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cursor, from, to);
        cursorAdapter.setViewBinder(new HistoryCursorAdapter());
        ListView myList = (ListView) findViewById(R.id.historyList);
        myList.setAdapter(cursorAdapter);
	}
	
	private ServiceConnection mContactConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className,
                IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ContactController.controllerBinder binder = (ContactController.controllerBinder) service;
            mContactController = binder.getService();
            mBound = true;
        }

        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
