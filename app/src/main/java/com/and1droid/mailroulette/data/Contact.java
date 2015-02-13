package com.and1droid.mailroulette.data;

import android.net.Uri;

public class Contact {
    private final String name;
    private final String photo;
    private final String thumbnail;
    private Uri contactUri;

    public Contact(String name, String photo, String thumbnail, Uri contactUri) {
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
}
