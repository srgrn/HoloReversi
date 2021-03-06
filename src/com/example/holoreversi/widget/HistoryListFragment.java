package com.example.holoreversi.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
import com.example.holoreversi.R;
import com.example.holoreversi.ReplayActivity;
import com.example.holoreversi.model.history.HistoryContract;

public class HistoryListFragment extends SherlockListFragment implements LoaderManager.LoaderCallbacks<Cursor> {
	// This is the Adapter being used to display the list's data.
	SimpleCursorAdapter mAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Give some text to display if there is no data. In a real
		// application this would come from a resource.
		setEmptyText(getActivity().getString(R.string.no_games_in_history));

		// We have a menu item to show in action bar.
		setHasOptionsMenu(true);

		// Create an empty adapter we will use to display the loaded data.
		mAdapter = new HistoryCursorAdapter(getActivity());
		setListAdapter(mAdapter);

		// Prepare the loader. Either re-connect with an existing one,
		// or start a new one.
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String selection = HistoryContract.HistoryColumns.NUMBEROFMOVES + ">?";
        String[] selectionArgs = new String[] { "0" };
		return new CursorLoader(getActivity(), 
			HistoryContract.History.CONTENT_URI, null, selection, selectionArgs, null
		);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// Swap the new cursor in.  (The framework will take care of closing the
        // old cursor once we return.)
        mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
        // This is called when the last Cursor provided to onLoadFinished()
        // above is about to be closed.  We need to make sure we are no
        // longer using it.
        mAdapter.swapCursor(null);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent replayIntent = new Intent(getActivity(), ReplayActivity.class);
		replayIntent.putExtra(ReplayActivity.EXTRA_GAME_ID, id);
		startActivity(replayIntent);
	}

}