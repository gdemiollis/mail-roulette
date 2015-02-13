package com.and1droid.mailroulette.data;

import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.HashSet;
import java.util.Set;

import static android.provider.ContactsContract.CommonDataKinds.Email.ADDRESS;
import static android.provider.ContactsContract.Contacts.DISPLAY_NAME_PRIMARY;
import static android.provider.ContactsContract.Contacts.PHOTO_URI;
import static android.provider.ContactsContract.Contacts.PHOTO_THUMBNAIL_URI;
import static android.provider.ContactsContract.Contacts.LOOKUP_KEY;
import static android.provider.ContactsContract.Contacts._ID;
import static android.provider.ContactsContract.Data.DATA1;
import static android.provider.ContactsContract.Data.DATA2;
import static android.provider.ContactsContract.Data.DATA3;
import static android.provider.ContactsContract.Data.DATA4;
import static android.provider.ContactsContract.Data.MIMETYPE;

public class CursorReader {
    public static Set<String> getAdresses(Cursor cursor) {
        Set<String> adresses = new HashSet<>();
        while (cursor.moveToNext()) {
            adresses.add(getAddress(cursor));
        }
        return adresses;
    }

    public static Set<MyEntity> getEntity(Cursor cursor) {
        Set<MyEntity> entities = new HashSet<>();
        while (cursor.moveToNext()) {
            MyEntity myEntity = new MyEntity(getData1(cursor), getData2(cursor), getData3(cursor), getData4(cursor), getMimeType(cursor));
            entities.add(myEntity);
        }
        return entities;
    }

    public static Set<Contact> getContact(Cursor cursor) {
        Set<Contact> contacts = new HashSet<>();
        while (cursor.moveToNext()) {
            Contact contact = new Contact(getName(cursor), getPhoto(cursor), getThumbnail(cursor), getContactUri(cursor));
            contacts.add(contact);
        }
        return contacts;
    }

    private static Uri getContactUri(Cursor cursor) {
        return ContactsContract.Contacts.getLookupUri(getLong(cursor, _ID), getString(cursor, LOOKUP_KEY));
    }

    private static String getThumbnail(Cursor cursor) {
        return getString(cursor, PHOTO_THUMBNAIL_URI);
    }

    private static String getPhoto(Cursor cursor) {
        return getString(cursor, PHOTO_URI);
    }

    private static String getName(Cursor cursor) {
        return getString(cursor, DISPLAY_NAME_PRIMARY);
    }

    private static String getMimeType(Cursor cursor) {
        return getString(cursor, MIMETYPE);
    }

    private static String getData1(Cursor cursor) {
        return getString(cursor, DATA1);
    }

    private static String getData2(Cursor cursor) {
        return getString(cursor, DATA2);
    }

    private static String getData3(Cursor cursor) {
        return getString(cursor, DATA3);
    }

    private static String getData4(Cursor cursor) {
        return getString(cursor, DATA4);
    }

    private static String getAddress(Cursor cursor) {
        return getString(cursor, ADDRESS);
    }

    private static String getString(Cursor cursor, String column) {
        return cursor.getString(cursor.getColumnIndex(column));
    }

    private static Long getLong(Cursor cursor, String column) {
        return cursor.getLong(cursor.getColumnIndex(column));
    }
}
