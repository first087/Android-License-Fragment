package com.artitk.licensefragment.utils;

/**
 * Created by Artit on 7/5/2558.
 */
public class BitwiseManager {

    private int value;

    public BitwiseManager(int value) {
        this.value = value;
    }

    public boolean isThisFlag(int flag) {
        return  (value & flag) == flag;
    }

}
