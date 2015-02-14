package com.and1droid.mailroulette;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.and1droid.mailroulette.data.Address;
import com.and1droid.mailroulette.data.EmailLoaderFactory;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


public class ContactActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private static final int EMAIL_REQUEST_ID = 123;
    private static final String EXTRA_LOOKUP_KEY = "EXTRA_LOOKUP_KEY";
    private static final String EXTRA_ADDRESS = "EXTRA_ADDRESS";
    private ContactDetailFragment contactDetailFragment;
    private Set<Address> adresses = new TreeSet<>();

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
        getLoaderManager().initLoader(EMAIL_REQUEST_ID, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
        return EmailLoaderFactory.getEmailListLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            Log.i(getClass().getSimpleName(), data.getCount() + " adresses trouvées en bd");
                adresses = EmailLoaderFactory.getAdresses(data);
                Log.i(getClass().getSimpleName(), adresses.size() + " adresses uniques");
        Address randomAddress = getRandomAddress();
        contactDetailFragment.updateFragment(randomAddress);

    }

    private Address getRandomAddress() {
        int randomValue = new Random().nextInt(adresses.size());
        return adresses.toArray(new Address[adresses.size()])[randomValue];
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.i(getClass().getSimpleName(), "Qui suis-je?");
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
