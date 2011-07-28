package net.simplephone;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTab extends TabActivity {

	public static final String TAG = "SimplePhone";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab);
		
		TabHost tabHost = getTabHost();
		TabHost.TabSpec tabSpec;
		Intent intent;
		
		intent = new Intent().setClass(this, ContactList.class);
		tabSpec = tabHost.newTabSpec("Contacts").setIndicator("Contacts").setContent(intent);
		tabHost.addTab(tabSpec);
		
		intent = new Intent().setClass(this, HistoryList.class);
		tabSpec = tabHost.newTabSpec("History").setIndicator("History").setContent(intent);
		tabHost.addTab(tabSpec);
		
		tabHost.setCurrentTab(1);
	}
}
