package com.and1droid.mailroulette.data;

public class Address implements Comparable<Address> {
    private String lookupKey;
    private String address;
    private String name;
    private String photoUri;

    public Address(String lookupKey, String address, String name, String photoUri) {
        this.lookupKey = lookupKey;
        this.address = address;
        this.name = name;
        this.photoUri = photoUri;
    }

    public String getLookupKey() {
        return lookupKey;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    @Override
    public int compareTo(Address another) {
        if (address == null) {
            return -1;
        }
        if (another == null || another.address == null) {
            return 1;
        }
        return address.compareTo(another.address);
    }
}
