package com.and1droid.mailroulette.data;

public class Address implements Comparable<Address> {
    private String lookupKey;
    private String address;
    private String name;

    public Address(String lookupKey, String address, String name) {
        this.lookupKey = lookupKey;
        this.address = address;
        this.name = name;
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
