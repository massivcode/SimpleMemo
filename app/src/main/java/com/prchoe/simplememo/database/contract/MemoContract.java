package com.prchoe.simplememo.database.contract;

import android.provider.BaseColumns;

/**
 * Created by massivCode on 2015-09-22.
 */
public class MemoContract {

    public static class MemoEntry  implements BaseColumns{
        public static final String TABLE_NAME = "Memo";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTENTS = "contents";
        public static final String COLUMN_NAME_DATE = "date";

        public static final String[] PROJECTION_ALL = new String[]{
                MemoEntry._ID,
                MemoEntry.COLUMN_NAME_DATE,
                MemoEntry.COLUMN_NAME_TITLE,
                MemoEntry.COLUMN_NAME_CONTENTS};
    }
}
