package com.and1droid.mailroulette.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.Set;
import java.util.TreeSet;

import static android.provider.BaseColumns._ID;
import static android.provider.ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
import static android.provider.ContactsContract.Contacts.LOOKUP_KEY;
import static android.provider.ContactsContract.Contacts.PHOTO_THUMBNAIL_URI;
import static android.provider.ContactsContract.Contacts.PHOTO_URI;

public class ContactCursorReader {

    public static Set<Contact> getContact(Cursor cursor) {
        Set<Contact> contacts = new TreeSet<>();
        while (cursor.moveToNext()) {
            String name = getName(cursor);
            String photo = getPhoto(cursor);
            String thumbnail = getThumbnail(cursor);
            if (!TextUtils.isEmpty(name)) {
                Contact contact = new Contact(getLookupKey(cursor), name, photo, thumbnail, getContactUri(cursor));
                contacts.add(contact);
            }
        }
        return contacts;
    }

    public static String getLookupKey(Cursor cursor) {
        return CursorReader.getString(cursor, LOOKUP_KEY);
    }

    public static Uri getContactUri(Cursor cursor) {
        return ContactsContract.Contacts.getLookupUri(CursorReader.getLong(cursor, _ID), CursorReader.getString(cursor, LOOKUP_KEY));
    }

    public static String getThumbnail(Cursor cursor) {
        return CursorReader.getString(cursor, PHOTO_THUMBNAIL_URI);
    }

    public static String getPhoto(Cursor cursor) {
        return CursorReader.getString(cursor, PHOTO_URI);
    }

    public static String getName(Cursor cursor) {
        return CursorReader.getString(cursor, DISPLAY_NAME_PRIMARY);
    }
}
