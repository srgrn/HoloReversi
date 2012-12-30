package com.example.holoreversi;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.example.holoreversi.widget.HistoryListFragment;

public class HistoryActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();

        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
        	HistoryListFragment list = new HistoryListFragment();
            fm.beginTransaction().add(android.R.id.content, list).commit();
        }
	}

}