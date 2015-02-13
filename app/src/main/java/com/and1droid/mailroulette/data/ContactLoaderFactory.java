package com.and1droid.mailroulette.data;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import static android.provider.ContactsContract.Contacts.CONTENT_URI;
import static android.provider.ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
import static android.provider.ContactsContract.Contacts.LOOKUP_KEY;
import static android.provider.ContactsContract.Contacts._ID;
import static android.provider.ContactsContract.Contacts.PHOTO_THUMBNAIL_URI;
import static android.provider.ContactsContract.Contacts.PHOTO_URI;

public class ContactLoaderFactory {

    public static Loader<Cursor> getContactLoader(Context context) {
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
        return new String[]{_ID, LOOKUP_KEY, DISPLAY_NAME_PRIMARY, PHOTO_URI, PHOTO_THUMBNAIL_URI, };
    }

    public static String getSelection() {
        return null;
    }

    public static String[] getSelectionArgs() {
        return null;
    }

    public static String getSortOrder() {
        return null;
    }
}
