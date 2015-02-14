package com.and1droid.mailroulette.data;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import static android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
import static android.provider.ContactsContract.Contacts.Entity.CONTENT_DIRECTORY;
import static android.provider.ContactsContract.Contacts.Entity.RAW_CONTACT_ID;
import static android.provider.ContactsContract.Data._ID;
import static android.provider.ContactsContract.Data.DATA1;
import static android.provider.ContactsContract.Data.MIMETYPE;

public class ContactDetailLoaderFactory {

    public static Loader<Cursor> getContactDetailLoader(Context context, Contact contact) {
        return new CursorLoader(
                context,
                getUri(contact),
                getProjection(),
                getSelection(),
                getSelectionArgs(),
                getSortOrder()
        );
    }

    protected static Uri getUri(Contact contact) {
        return Uri.withAppendedPath(contact.getContactUri(), CONTENT_DIRECTORY);
    }

    public static String[] getProjection() {
        return new String[]{_ID, RAW_CONTACT_ID, DATA1, MIMETYPE};
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
