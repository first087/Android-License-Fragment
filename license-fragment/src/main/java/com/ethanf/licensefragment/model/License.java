package com.ethanf.licensefragment.model;

import android.content.Context;

import com.ethanf.licensefragment.utils.ResourceManager;

/**
 * License data class.
 */
public class License {

    private Context context;
    private String title;
    private LicenseType licenseType;
    private String year;
    private String owner;

    protected License(Context context, int licenseId) {
        this.context = context;

        switch (licenseId) {
            /* ----- This Library ----- */
            case LicenseID.LICENSE_FRAGMENT:    title = "License Fragment"; licenseType = LicenseType.APACHE_LICENSE_20;    year = "2015";  owner = "Artit Kiuwilai";       break;
            /* ----- Google Library ----- */
            case LicenseID.GSON:                title = "Gson";             licenseType = LicenseType.APACHE_LICENSE_20;    year = "2008";  owner = "Google Inc.";          break;
            /* ----- Square Library ----- */
            case LicenseID.OTTO:                title = "Otto";             licenseType = LicenseType.APACHE_LICENSE_20;    year = "2013";  owner = "Square, Inc.";         break;
            case LicenseID.OKHTTP:              title = "OkHttp";           licenseType = LicenseType.APACHE_LICENSE_20;    year = "2014";  owner = "Square, Inc.";         break;
            case LicenseID.RETROFIT:            title = "Retrofit";         licenseType = LicenseType.APACHE_LICENSE_20;    year = "2013";  owner = "Square, Inc.";         break;
            case LicenseID.PICASSO:             title = "Picasso";          licenseType = LicenseType.APACHE_LICENSE_20;    year = "2013";  owner = "Square, Inc.";         break;
            /* ----- Nuuneoi Library ----- */
            case LicenseID.STATED_FRAGMENT:     title = "StatedFragment";   licenseType = LicenseType.APACHE_LICENSE_20;    year = "2015";  owner = "Sittiphol Phanvilai";  break;
            // TODO : Add new license case here
            default:                            throw new IllegalArgumentException();
        }
    }

    /**
     * Use this constructor for create instance.
     *
     * @param context {@link Context} class.
     * @param title Open-source library name.
     * @param licenseType Type of License. Use constant from {@link LicenseType} enum.
     * @param year Year.
     * @param owner Owner name.
     */
    public License(Context context, String title, LicenseType licenseType, String year, String owner) {
        this.context     = context;
        this.title       = title;
        this.licenseType = licenseType;
        this.year        = year;
        this.owner       = owner;
    }

    /**
     * @return Open-source library name.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return Wording for display Open-source license.
     */
    public String getLicense() {
        return String.format(new ResourceManager(context).readRawFile(licenseType), year, owner);
    }

}
