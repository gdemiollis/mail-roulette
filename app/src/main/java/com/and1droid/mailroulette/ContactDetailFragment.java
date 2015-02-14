package com.and1droid.mailroulette;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.and1droid.mailroulette.data.Address;

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

    public void updateFragment(Address address) {
        TextView nameView = (TextView) getView().findViewById(R.id.contact_name);
        nameView.setText(address.getName());
        TextView emailView = (TextView) getView().findViewById(R.id.contact_email);
        emailView.setText(address.getAddress());
    }
}