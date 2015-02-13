package com.and1droid.mailroulette.data;

public class MyEntity {
    private String data1;
    private String data2;
    private String data3;
    private String data4;
    private String mimeType;

    public MyEntity(String data1, String data2, String data3, String data4, String mimeType) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.mimeType = mimeType;
    }

    public String getData1() {
        return data1;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getData2() {
        return data2;
    }

    public String getData3() {
        return data3;
    }

    public String getData4() {
        return data4;
    }

}
