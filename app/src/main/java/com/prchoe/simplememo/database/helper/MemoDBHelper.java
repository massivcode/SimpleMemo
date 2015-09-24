
package com.prchoe.simplememo.database.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prchoe.simplememo.database.contract.MemoContract;

/**
 * Created by massivCode on 2015-09-22.
 */
public class MemoDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Memo.db";
    public static final int DATABASE_VERSION = 1;

    private static MemoDBHelper memoDBHelper = null;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            MemoContract.MemoEntry.TABLE_NAME + " (" +
            MemoContract.MemoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MemoContract.MemoEntry.COLUMN_NAME_DATE + " TEXT NOT NULL, " +
            MemoContract.MemoEntry.COLUMN_NAME_TITLE + " TEXT, " +
            MemoContract.MemoEntry.COLUMN_NAME_CONTENTS + " TEXT " +
            ");";

    private MemoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static MemoDBHelper getInstance(Context context) {

        if (memoDBHelper == null) {
            memoDBHelper = new MemoDBHelper(context);
        }

        return memoDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
