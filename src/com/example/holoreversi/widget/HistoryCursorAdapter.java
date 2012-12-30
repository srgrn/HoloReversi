package com.example.holoreversi.widget;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.example.holoreversi.R;
import com.example.holoreversi.model.history.DataStore;

public class HistoryCursorAdapter extends SimpleCursorAdapter {

	public static final int IDX_TIME = 1;
	public static final int IDX_NUMBEROFMOVES = 2;
	public static final int IDX_SCOREBLACK = 3;
	public static final int IDX_SCOREWHITE = 4;


	public HistoryCursorAdapter(Context context) {
		super(context,
			R.layout.history_list_item, null,
			new String[] { 
				DataStore.COLUMN_NAME_TIME,
				DataStore.COLUMN_NAME_NUMBEROFMOVES,
				DataStore.COLUMN_NAME_SCORE1,
				DataStore.COLUMN_NAME_SCORE2
			},
			new int[] { 
				android.R.id.text1, 
				android.R.id.text2,
				R.id.text3,
				R.id.text4
			}, 0
		);
		setViewBinder(new RowViewBinder(context));
		
	}

	
	static class RowViewBinder implements SimpleCursorAdapter.ViewBinder {

		private Context mContext;

		public RowViewBinder(Context context) {
			mContext = context;
		}
		
		@Override
		public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
			
			if (columnIndex == IDX_TIME) {
				String dateTime = DateUtils.formatDateTime(mContext, cursor.getLong(columnIndex),
					DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE
				);
				((TextView)view).setText(dateTime);
			} else if (columnIndex == IDX_NUMBEROFMOVES) {
				((TextView)view).setText(mContext.getString(R.string.moves_num, cursor.getInt(columnIndex)));
			} else if (columnIndex == IDX_SCOREBLACK) {
					((TextView)view).setText(mContext.getString(R.string.scoreBlack, cursor.getInt(columnIndex)));
			} else if (columnIndex == IDX_SCOREWHITE) {
					((TextView)view).setText(mContext.getString(R.string.scoreWhite, cursor.getInt(columnIndex)));
			}
			return true;
		}
		
	}

}