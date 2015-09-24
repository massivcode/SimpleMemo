
package com.prchoe.simplememo.database.facade;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.prchoe.simplememo.database.contract.MemoContract;
import com.prchoe.simplememo.database.helper.MemoDBHelper;
import com.prchoe.simplememo.model.MemoData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by massivCode on 2015-09-22.
 */
public class MemoFacade {
    private Context mContext;
    private MemoDBHelper memoDBHelper;

    public MemoFacade(Context context) {
        mContext = context;
        memoDBHelper = MemoDBHelper.getInstance(context);
    }

    public boolean addMemo(ContentValues values) {

        return memoDBHelper.getWritableDatabase().insert(MemoContract.MemoEntry.TABLE_NAME, null,
                values) != -1;

    }

    public List<MemoData> showMemo() {
        // query(String table,
        // String[] columns,
        // String selection,
        // String[] selectionArgs,
        // String groupBy,
        // String having,
        // String orderBy,
        // String limit)
        Cursor cursor = memoDBHelper.getReadableDatabase().query(
                MemoContract.MemoEntry.TABLE_NAME,
                MemoContract.MemoEntry.PROJECTION_ALL
                , null, null, null, null, null);

        return cursorToList(cursor);
    }

    private List<MemoData> cursorToList(Cursor cursor) {

        List<MemoData> list = new ArrayList<>();


        while(cursor.moveToNext()) {
            MemoData memoData = new MemoData(
                    cursor.getInt(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MemoContract.MemoEntry.COLUMN_NAME_DATE))
            );
            list.add(memoData);
        }

        cursor.close();
        return list;
    }

    public boolean updateMemo(MemoData memoData) {
        // table, values, where, whereArgs

        ContentValues values = new ContentValues();
        values.put(MemoContract.MemoEntry.COLUMN_NAME_TITLE, memoData.getTitle());
        values.put(MemoContract.MemoEntry.COLUMN_NAME_CONTENTS, memoData.getContents());

        String where = MemoContract.MemoEntry._ID + " = ?";
        String[] whereArgs = new String[]{""+memoData.get_id()};
        return memoDBHelper.getWritableDatabase().update(MemoContract.MemoEntry.TABLE_NAME, values, where, whereArgs) != 0;
    }

    public boolean deleteMemo(MemoData memoData) {

        String where = MemoContract.MemoEntry._ID + " = ?";
        String[] whereArgs = new String[]{""+memoData.get_id()};
        return memoDBHelper.getWritableDatabase().delete(MemoContract.MemoEntry.TABLE_NAME, where, whereArgs) != 0;
    }



}
