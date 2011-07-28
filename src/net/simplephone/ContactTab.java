package net.simplephone;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TabHost;

public class ContactTab extends TabActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_tab);
		
		Bundle extras = getIntent().getExtras(); 
		Integer id = (int) extras.getLong(ContactsContract.Contacts._ID);
		
		TabHost tabHost = getTabHost();
		TabHost.TabSpec tabSpec;
		Intent intent;
		
		intent = new Intent().setClass(this, ContactInfo.class);
		intent.putExtra(ContactsContract.Contacts._ID, id);
		tabSpec = tabHost.newTabSpec("Info").setIndicator("Info").setContent(intent);
		tabHost.addTab(tabSpec);
		
		intent = new Intent().setClass(this, ContactHistory.class);
		intent.putExtra(ContactsContract.Contacts._ID, id);
		tabSpec = tabHost.newTabSpec("History").setIndicator("History").setContent(intent);
		tabHost.addTab(tabSpec);
		
		tabHost.setCurrentTab(0);
	}
}
