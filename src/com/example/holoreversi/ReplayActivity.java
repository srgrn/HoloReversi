package com.example.holoreversi;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.holoreversi.widget.HistoryListFragment;

public class ReplayActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Show the Up button in the action bar.
		if (getSupportActionBar()!=null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
        FragmentManager fm = getSupportFragmentManager();

        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
        	HistoryListFragment list = new HistoryListFragment();
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }
	}
}