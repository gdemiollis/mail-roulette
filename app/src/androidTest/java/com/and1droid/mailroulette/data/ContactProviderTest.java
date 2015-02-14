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
        assertEquals("content://com.android.contacts/data/emails", EmailLoaderFactory.getUri().toString());
        assertEquals(483, cursor.getCount());
        assertEquals(5, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("_id", columnNames[0]);
        assertEquals("lookup", columnNames[1]);
        assertEquals("data1", columnNames[2]);
        assertEquals("photo_uri", columnNames[3]);
        assertEquals("display_name", columnNames[4]);
        Set<Address> adresses = EmailLoaderFactory.getAdresses(cursor);
        assertEquals(420, adresses.size());
        Address address = adresses.iterator().next();
        assertEquals("22804903-809b-4a29-8827-8112134f83a5@reply.linkedin.com", address.getAddress());
        assertEquals("1088i77d83bb38bac7c3b", address.getLookupKey());
        assertEquals("?tienne Savard via LinkedIn", address.getName());
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
        assertEquals(1043, cursor.getCount());
        assertEquals(6, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("raw_contact_id", columnNames[0]);
        assertEquals("data1", columnNames[1]);
        assertEquals("data2", columnNames[2]);
        assertEquals("data3", columnNames[3]);
        assertEquals("data4", columnNames[4]);
        assertEquals("mimetype", columnNames[5]);
        Set<MyEntity> entities = EntityLoaderFactory.getEntity(cursor);
        assertEquals(1043, entities.size());
        Iterator<MyEntity> iterator = entities.iterator();
        MyEntity entity = iterator.next();
        assertEquals("philippe.gratton@gmail.com", entity.getData1());
        assertEquals("0", entity.getData2());
        assertNull(entity.getData3());
        assertNull(entity.getData4());
        assertEquals("vnd.android.cursor.item/email_v2", entity.getMimeType());
        entity = iterator.next();
        assertNull(entity.getData1());
        assertEquals("vnd.android.cursor.item/photo", entity.getMimeType());
    }

    public void testListContacts() throws Exception {
        Cursor cursor = contentResolver.query(
                ContactListLoaderFactory.getUri(),
                ContactListLoaderFactory.getProjection(),
                ContactListLoaderFactory.getSelection(),
                ContactListLoaderFactory.getSelectionArgs(),
                ContactListLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/contacts", ContactListLoaderFactory.getUri().toString());
        assertEquals(507, cursor.getCount());
        assertEquals(5, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("_id", columnNames[0]);
        assertEquals("lookup", columnNames[1]);
        assertEquals("display_name", columnNames[2]);
        assertEquals("photo_uri", columnNames[3]);
        assertEquals("photo_thumb_uri", columnNames[4]);
        Set<Contact> contacts = ContactCursorReader.getContact(cursor);
        assertEquals(449, contacts.size());
        Iterator<Contact> iterator = contacts.iterator();
        Contact contact = iterator.next();
        contact = iterator.next();
        contact = iterator.next();
        contact = iterator.next();
        assertEquals("Adélie Saudreau", contact.getName());
        assertEquals("1088i76a31d50d21cad5.453ee%3Aadeliesaudreau%40gmail..com", contact.getLookupKey());
        assertEquals("content://com.android.contacts/contacts/lookup/1088i76a31d50d21cad5.453ee%3Aadeliesaudreau%40gmail..com/2767", contact.getContactUri().toString());
        assertEquals("content://com.android.contacts/contacts/2767/photo", contact.getPhoto());
        assertEquals("content://com.android.contacts/contacts/2767/photo", contact.getThumbnail());
    }

    public void testListContactsWithEmail() throws Exception {
        Contact contact = new Contact("", "Olivier Poissant", null, null, Uri.parse("content://com.android.contacts/contacts/lookup/1088i4cdecdfd8cf311b2/3137"));
        Cursor cursor = contentResolver.query(
                ContactDetailLoaderFactory.getUri(contact),
                ContactDetailLoaderFactory.getProjection(),
                ContactDetailLoaderFactory.getSelection(),
                ContactDetailLoaderFactory.getSelectionArgs(),
                ContactDetailLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/contacts/lookup/1088i4cdecdfd8cf311b2/3137/entities", ContactDetailLoaderFactory.getUri(contact).toString());
        assertEquals(1, cursor.getCount());
        assertEquals(4, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("_id", columnNames[0]);
        assertEquals("raw_contact_id", columnNames[1]);
        assertEquals("data1", columnNames[2]);
        assertEquals("mimetype", columnNames[3]);
        Set<Address> adresses = EmailLoaderFactory.getAdresses(cursor);
        assertEquals(1, adresses.size());
        Address address = adresses.iterator().next();
        assertEquals("olivier.poissant@mediacom.com", address.getAddress());
        assertEquals(Long.valueOf(3137), address.getLookupKey());
    }

    public void testFindContactsWithId_2767() throws Exception {
        Cursor cursor = contentResolver.query(
                ContactLoaderFactory.getUri(),
                ContactLoaderFactory.getProjection(),
                ContactLoaderFactory.getSelection(),
                ContactLoaderFactory.getSelectionArgs("1088i76a31d50d21cad5.453ee%3Aadeliesaudreau%40gmail..com"),
                ContactLoaderFactory.getSortOrder());
        assertEquals("content://com.android.contacts/contacts", ContactLoaderFactory.getUri().toString());
        assertEquals(1, cursor.getCount());
        assertEquals(5, cursor.getColumnCount());
        String[] columnNames = cursor.getColumnNames();
        assertEquals("_id", columnNames[0]);
        assertEquals("lookup", columnNames[1]);
        assertEquals("display_name", columnNames[2]);
        assertEquals("photo_uri", columnNames[3]);
        assertEquals("photo_thumb_uri", columnNames[4]);
        Set<Contact> contacts = ContactCursorReader.getContact(cursor);
        assertEquals(1, contacts.size());
        Iterator<Contact> iterator = contacts.iterator();
        Contact contact = iterator.next();
        assertEquals("Adélie Saudreau", contact.getName());
        assertEquals("content://com.android.contacts/contacts/lookup/1088i76a31d50d21cad5.453ee%3Aadeliesaudreau%40gmail..com/2767", contact.getContactUri().toString());
        assertEquals("content://com.android.contacts/contacts/2767/photo", contact.getPhoto());
        assertEquals("content://com.android.contacts/contacts/2767/photo", contact.getThumbnail());
    }

}
