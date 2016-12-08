package com.example.derekvaldez.encodechatsms;


public class EncodeKey {
    private static EncodeKey instance = null;
    public static String key;

    public static void setKey(String s){
        key = s;
    }

    public static String getKey(){
        return key;
    }

    protected EncodeKey() {
        key = null;
    }
    public static EncodeKey getInstance() {
        if(instance == null) {
            instance = new EncodeKey();
        }
        return instance;
    }
}