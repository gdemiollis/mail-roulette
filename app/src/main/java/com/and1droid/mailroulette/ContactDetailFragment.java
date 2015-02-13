package com.and1droid.mailroulette;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ContactDetailFragment extends Fragment {

    public ContactDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    public void setRandomEmail(String randomEmail) {
        TextView emailView = (TextView) getView().findViewById(R.id.contact_email);
        emailView.setText(randomEmail);
    }
}