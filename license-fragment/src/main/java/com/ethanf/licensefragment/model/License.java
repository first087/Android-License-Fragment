package com.ethanf.licensefragment.model;

import android.content.Context;

import com.ethanf.licensefragment.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Artit on 20/4/2558.
 */
public class License {

    // Ref. http://opensource.org/licenses
    public enum LicenseType {
        APACHE_LICENSE_20,
        BSD_3_CLAUSE,
        BSD_2_CLAUSE,
        GPL_30,
        MIT_LICENSE
        // TODO : Add new license type here
    }

    private Context context;
    private String title;
    private LicenseType licenseType;
    private String year;
    private String owner;

    public License(Context context, int licenseId) {
        this.context = context;

        switch (licenseId) {
            /* ----- This Library ----- */
            case LicenseID.LICENSE_FRAGMENT:    title = "License Fragment"; licenseType = LicenseType.APACHE_LICENSE_20;    year = "2015";  owner = "Artit Kiuwilai";       break;
            /* ----- For This Library ----- */
            case LicenseID.STATED_FRAGMENT:     title = "StatedFragment";   licenseType = LicenseType.APACHE_LICENSE_20;    year = "2015";  owner = "Sittiphol Phanvilai";  break;
            /* ----- Google Library ----- */
            case LicenseID.GSON:                title = "Gson";             licenseType = LicenseType.APACHE_LICENSE_20;    year = "2008";  owner = "Google Inc.";          break;
            /* ----- Square Library ----- */
            case LicenseID.OTTO:                title = "Otto";             licenseType = LicenseType.APACHE_LICENSE_20;    year = "2013";  owner = "Square, Inc.";         break;
            case LicenseID.OKHTTP:              title = "OkHttp";           licenseType = LicenseType.APACHE_LICENSE_20;    year = "2014";  owner = "Square, Inc.";         break;
            case LicenseID.RETROFIT:            title = "Retrofit";         licenseType = LicenseType.APACHE_LICENSE_20;    year = "2013";  owner = "Square, Inc.";         break;
            case LicenseID.PICASSO:             title = "Picasso";          licenseType = LicenseType.APACHE_LICENSE_20;    year = "2013";  owner = "Square, Inc.";         break;
            // TODO : Add new license case here
            default:                            throw new IllegalArgumentException();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getLicense() {
        return String.format(readRawFile(getRawId()).trim(), year, owner);
    }

    private int getRawId() {
        switch (licenseType) {
            case APACHE_LICENSE_20:     return R.raw.apache_license_v20;
            case BSD_3_CLAUSE:          return R.raw.bsd_3_clause;
            case BSD_2_CLAUSE:          return R.raw.bsd_2_clause;
            case GPL_30:                return R.raw.gpl_30;
            case MIT_LICENSE:           return R.raw.mit_license;
            // TODO : Add new license type here
            default:            throw new IllegalArgumentException();
        }
    }

    private String readRawFile(int fileId) {
        InputStream inputStream = context.getResources().openRawResource(fileId);
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

        return byteArrayOutputStream.toString();
    }
}
