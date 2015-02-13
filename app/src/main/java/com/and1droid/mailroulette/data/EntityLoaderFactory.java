package com.and1droid.mailroulette.data;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import static android.provider.BaseColumns._ID;
import static android.provider.ContactsContract.CommonDataKinds.Email.ADDRESS;
import static android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
import static android.provider.ContactsContract.Contacts.LOOKUP_KEY;
import static android.provider.ContactsContract.Data.CONTENT_URI;
import static android.provider.ContactsContract.Data.MIMETYPE;

public class EntityLoaderFactory {

    public static Loader<Cursor> getEmailListLoader(Context context) {
        return new CursorLoader(
                context,
                getUri(),
                getProjection(),
                getSelection(),
                getSelectionArgs(),
                getSortOrder()
        );
    }

    protected static Uri getUri() {
        return CONTENT_URI;
    }

    public static String[] getProjection() {
        return new String[] {_ID, LOOKUP_KEY, ADDRESS};
    }

    public static String getSelection() {
        return MIMETYPE + " = ?";
    }

    public static String[] getSelectionArgs() {
        return new String[]{CONTENT_ITEM_TYPE};
    }

    public static String getSortOrder() {
        return null;
    }
}
