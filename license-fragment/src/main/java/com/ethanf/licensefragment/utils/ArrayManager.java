package com.ethanf.licensefragment.utils;

import java.util.ArrayList;

/**
 * Created by Artit on 27/4/2558.
 */
public class ArrayManager {

    public static ArrayList<Integer> asIntegerArrayList(int[] array) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int item : array) {
            arrayList.add(item);
        }

        return arrayList;
    }

}
