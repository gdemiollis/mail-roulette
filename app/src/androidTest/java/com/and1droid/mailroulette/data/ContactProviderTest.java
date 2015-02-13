package com.and1droid.mailroulette.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.test.InstrumentationTestCase;

import java.util.Iterator;
import java.util.Set;

public class ContactProviderTest extends InstrumentationTestCase {

    private ContentResolver contentResolver;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        contentResolver = getInstrumentation().getTargetContext().getContentResolver();
    }

    public void testListEmailAdresses() throws Exception {
        Cursor cursor = contentResolver.query(
                EmailLoaderFactory.getUri(),
                EmailLoaderFactory.getProjection(),
                EmailLoaderFactory.getSelection(),
                EmailLoaderFactory.getSelectionArgs(),
                EmailLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/data", EmailLoaderFactory.getUri().toString());
        assertEquals(484, cursor.getCount());
        assertEquals(3, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("_id", columnNames[0]);
        assertEquals("lookup", columnNames[1]);
        assertEquals("data1", columnNames[2]);
        Set<String> adresses = CursorReader.getAdresses(cursor);
        assertEquals(422, adresses.size());
        assertEquals("thierrysaudreau@gmail.com", adresses.iterator().next());
    }

    public void testContactEntity() throws Exception {
        ContentResolver contentResolver = getInstrumentation().getTargetContext().getContentResolver();
        Cursor cursor = contentResolver.query(
                EntityLoaderFactory.getUri(),
                EntityLoaderFactory.getProjection(),
                EntityLoaderFactory.getSelection(),
                EntityLoaderFactory.getSelectionArgs(),
                EntityLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/data", EntityLoaderFactory.getUri().toString());
        assertEquals(1044, cursor.getCount());
        assertEquals(6, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("raw_contact_id", columnNames[0]);
        assertEquals("data1", columnNames[1]);
        assertEquals("data2", columnNames[2]);
        assertEquals("data3", columnNames[3]);
        assertEquals("data4", columnNames[4]);
        assertEquals("mimetype", columnNames[5]);
        Set<MyEntity> entities = CursorReader.getEntity(cursor);
        assertEquals(1044, entities.size());
        Iterator<MyEntity> iterator = entities.iterator();
        MyEntity entity = iterator.next();
        assertEquals("philippe.gratton@gmail.com", entity.getData1());
        assertEquals("3", entity.getData2());
        assertNull(entity.getData3());
        assertNull(entity.getData4());
        assertEquals("vnd.android.cursor.item/email_v2", entity.getMimeType());
        entity = iterator.next();
        assertNull(entity.getData1());
        assertEquals("vnd.android.cursor.item/photo", entity.getMimeType());
    }

    public void testListContacts() throws Exception {
        Cursor cursor = contentResolver.query(
                ContactLoaderFactory.getUri(),
                ContactLoaderFactory.getProjection(),
                ContactLoaderFactory.getSelection(),
                ContactLoaderFactory.getSelectionArgs(),
                ContactLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/contacts", ContactLoaderFactory.getUri().toString());
        assertEquals(507, cursor.getCount());
        assertEquals(5, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("_id", columnNames[0]);
        assertEquals("lookup", columnNames[1]);
        assertEquals("display_name", columnNames[2]);
        assertEquals("photo_uri", columnNames[3]);
        assertEquals("photo_thumb_uri", columnNames[4]);
        Set<Contact> contacts = CursorReader.getContact(cursor);
        assertEquals(507, contacts.size());
        Iterator<Contact> iterator = contacts.iterator();
        Contact contact = iterator.next();
        assertEquals("Olivier Poissant", contact.getName());
        assertEquals("content://com.android.contacts/contacts/lookup/1088i4cdecdfd8cf311b2/3137", contact.getContactUri().toString());
        assertNull(contact.getPhoto());
        assertNull(contact.getThumbnail());
    }

    public void testListContactsWithEmail() throws Exception {
        Contact contact = new Contact("Olivier Poissant", null, null, Uri.parse("content://com.android.contacts/contacts/lookup/1088i4cdecdfd8cf311b2/3137"));
        Cursor cursor = contentResolver.query(
                ContactDetailLoaderFactory.getUri(contact),
                ContactDetailLoaderFactory.getProjection(),
                ContactDetailLoaderFactory.getSelection(),
                ContactDetailLoaderFactory.getSelectionArgs(),
                ContactDetailLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/contacts/lookup/1088i4cdecdfd8cf311b2/3137/entities", ContactDetailLoaderFactory.getUri(contact).toString());
        assertEquals(1, cursor.getCount());
        assertEquals(3, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("raw_contact_id", columnNames[0]);
        assertEquals("data1", columnNames[1]);
        assertEquals("mimetype", columnNames[2]);
        Set<String> adresses = CursorReader.getAdresses(cursor);
        assertEquals(1, adresses.size());
        assertEquals("olivier.poissant@mediacom.com", adresses.iterator().next());
    }

}
