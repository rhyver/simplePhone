package net.simplephone;

import android.app.ListActivity;
import android.os.Bundle;

public class ContactHistory extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_history);
	}
}
