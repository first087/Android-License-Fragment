package com.ethanf.licensefragment.utils;

import android.content.Context;

import com.ethanf.licensefragment.R;
import com.ethanf.licensefragment.model.LicenseType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Artit on 23/4/2558.
 */
public class ResourceManager {

    private Context context;

    public ResourceManager(Context context) {
        this.context = context;
    }

    public String readRawFile(LicenseType licenseType) {
        return readRawFile(getRawId(licenseType));
    }

    private String readRawFile(int rawId) {
        InputStream inputStream = context.getResources().openRawResource(rawId);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

        return byteArrayOutputStream.toString().trim();
    }

    private int getRawId(LicenseType licenseType) {
        switch (licenseType) {
            case APACHE_LICENSE_20:     return R.raw.apache_license_v20;
            case BSD_3_CLAUSE:          return R.raw.bsd_3_clause;
            case BSD_2_CLAUSE:          return R.raw.bsd_2_clause;
            case GPL_30:                return R.raw.gpl_30;
            case MIT_LICENSE:           return R.raw.mit_license;
            // TODO : Add new license type & return raw file id here
            default:            throw new IllegalArgumentException();
        }
    }

}
