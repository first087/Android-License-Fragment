package com.ethanf.licensefragment.model;

import android.content.Context;

import com.ethanf.licensefragment.utils.ResourceManager;

/**
 * Created by Artit on 20/4/2558.
 */
public class License {

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

    public License(Context context, String title, LicenseType licenseType, String year, String owner) {
        this.context     = context;
        this.title       = title;
        this.licenseType = licenseType;
        this.year        = year;
        this.owner       = owner;
    }

    public String getTitle() {
        return title;
    }

    public String getLicense() {
        return String.format(new ResourceManager(context).readRawFile(licenseType), year, owner);
    }

}
