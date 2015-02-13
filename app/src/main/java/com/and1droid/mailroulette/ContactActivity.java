package com.and1droid.mailroulette;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static android.provider.ContactsContract.CommonDataKinds.Email.ADDRESS;
import static android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE;
import static android.provider.ContactsContract.Contacts.Data.MIMETYPE;
import static android.provider.ContactsContract.Contacts.LOOKUP_KEY;
import static android.provider.ContactsContract.Contacts._ID;


public class ContactActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final String[] PROJECTION = {ContactsContract.Contacts.Entity.RAW_CONTACT_ID,
            ContactsContract.Contacts.Entity.DATA1,
            ContactsContract.Contacts.Entity.MIMETYPE};
    private ContactDetailFragment contactDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        if (savedInstanceState == null) {
            contactDetailFragment = new ContactDetailFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, contactDetailFragment)
                    .commit();
        }
        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        return new CursorLoader(
                this,
                Uri.withAppendedPath(
                        mContactUri,
                        ContactsContract.Contacts.Entity.CONTENT_DIRECTORY,
                        PROJECTION,
                        MIMETYPE + " = ?",
                        new String[]{CONTENT_ITEM_TYPE},
                        null
                );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Set<String> adresses = new HashSet<>();
        while (data.moveToNext()) {
            adresses.add(data.getString(data.getColumnIndex(ADDRESS)));
        }
        Log.i(getClass().getSimpleName(), data.getCount() + " adresses trouvées en bd");
        Log.i(getClass().getSimpleName(), adresses.size() + " adresses uniques");
        int randomValue = new Random().nextInt(adresses.size());
        String randomEmail = adresses.toArray(new String[adresses.size()])[randomValue];
        contactDetailFragment.setRandomEmail(randomEmail);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
