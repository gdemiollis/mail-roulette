package com.and1droid.mailroulette.data;

import android.database.Cursor;

public class CursorReader {

    static String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    static Long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndex(column));
    }
}
