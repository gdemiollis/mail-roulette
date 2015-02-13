package com.and1droid.mailroulette.data;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import static android.provider.ContactsContract.CommonDataKinds.Email;
import static android.provider.ContactsContract.Contacts.Entity.DATA1;
import static android.provider.ContactsContract.Contacts.Entity.DATA2;
import static android.provider.ContactsContract.Contacts.Entity.DATA3;
import static android.provider.ContactsContract.Contacts.Entity.DATA4;
import static android.provider.ContactsContract.Contacts.Entity.MIMETYPE;
import static android.provider.ContactsContract.Contacts.Entity.RAW_CONTACT_ID;
import static android.provider.ContactsContract.Data.CONTENT_URI;

public class ContactLoaderFactory {

    public static Loader<Cursor> getEntityLoader(Context context) {
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
        return new String[]{RAW_CONTACT_ID, DATA1,DATA2,DATA3,DATA4, MIMETYPE};
    }

    public static String getSelection() {
        return MIMETYPE + " = ? or "+MIMETYPE + " = ?";
    }

    public static String[] getSelectionArgs() {
        return new String[]{Email.CONTENT_ITEM_TYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE};
    }

    public static String getSortOrder() {
        return null;
    }
}
