package com.and1droid.mailroulette.data;

import android.net.Uri;

public class Contact implements Comparable<Contact>{
    private final String name;
    private final String photo;
    private final String thumbnail;
    private Uri contactUri;
    private String lookupKey;
    private int address;

    public Contact(String lookupKey, String name, String photo, String thumbnail, Uri contactUri) {
        this.lookupKey = lookupKey;
        this.name = name;
        this.photo = photo;
        this.thumbnail = thumbnail;
        this.contactUri = contactUri;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Uri getContactUri() {
        return contactUri;
    }

    @Override
    public int compareTo(Contact another) {
        if (name == null) {
            return -1;
        }
        if (another == null || another.name == null) {
            return 1;
        }
        return name.compareTo(another.name);
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public int getAddress() {
        return address;
    }
}
