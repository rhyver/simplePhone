package net.simplephone;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ContactList extends ListActivity {

	private static final int CONTACTVIEW = 0;
	public static final String TAG = "simplePhone";

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ContentResolver contentResolver = getContentResolver();
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, sortOrder);
        
        String[] from = new String[] {ContactsContract.Contacts.DISPLAY_NAME};
        int[] to = new int[] {android.R.id.text1};
        
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, cursor, from, to);
        setListAdapter(cursorAdapter);
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, ContactTab.class);
        intent.putExtra(ContactsContract.Contacts._ID, id);
        startActivityForResult(intent, CONTACTVIEW);
    }
}
