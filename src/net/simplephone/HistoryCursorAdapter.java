package net.simplephone;

import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter.ViewBinder;

public class HistoryCursorAdapter implements ViewBinder {

	public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
        Log.i(MainTab.TAG, "View: " + view.toString() + " Cursor: " + cursor.toString() + " Column Index: " + new Integer(columnIndex).toString());
        return true;
    }

}
