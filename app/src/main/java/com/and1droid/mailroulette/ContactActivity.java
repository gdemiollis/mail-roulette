package com.and1droid.mailroulette;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.and1droid.mailroulette.data.CursorReader;
import com.and1droid.mailroulette.data.EmailLoaderFactory;

import java.util.Random;
import java.util.Set;


public class ContactActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
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
        return EmailLoaderFactory.getEmailListLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Set<String> adresses = CursorReader.getAdresses(data);
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
