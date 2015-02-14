package com.and1droid.mailroulette.data;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import java.util.Set;
import java.util.TreeSet;

import static android.provider.ContactsContract.CommonDataKinds.Email.ADDRESS;
import static android.provider.ContactsContract.CommonDataKinds.Email.PHOTO_URI;
import static android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
import static android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_URI;
import static android.provider.ContactsContract.CommonDataKinds.Email.DISPLAY_NAME_PRIMARY;
import static android.provider.ContactsContract.Contacts.LOOKUP_KEY;
import static android.provider.ContactsContract.Contacts._ID;
import static android.provider.ContactsContract.Data.MIMETYPE;

public class EmailLoaderFactory {

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
        return new String[] {_ID, LOOKUP_KEY, ADDRESS, PHOTO_URI, DISPLAY_NAME_PRIMARY};
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

    public static Set<Address> getAdresses(Cursor cursor) {
        Set<Address> adresses = new TreeSet<>();
        while (cursor.moveToNext()) {
            Address address = new Address(getLookupKey(cursor), getAddress(cursor), getName(cursor), getPhotoUri(cursor));
            adresses.add(address);
        }
        return adresses;
    }

    private static String getPhotoUri(Cursor cursor) {
        return CursorReader.getString(cursor, PHOTO_URI);
    }

    private static String getName(Cursor cursor) {
        return CursorReader.getString(cursor, DISPLAY_NAME_PRIMARY);
    }

    private static String getAddress(Cursor cursor) {
        return CursorReader.getString(cursor, ADDRESS);
    }

    public static String getLookupKey(Cursor cursor) {
        return CursorReader.getString(cursor, LOOKUP_KEY);
    }
}
