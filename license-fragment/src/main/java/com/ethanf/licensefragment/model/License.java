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

    private Context context;
    private String title;
    private int rawId;

    public License(Context context, int licenseId) {
        this.context = context;

        switch (licenseId) {
            /* ----- This Library ----- */
            case LicenseID.LICENSE_FRAGMENT:    title = "License Fragment"; rawId = R.raw.licensefragment;  break;
            /* ----- For This Library ----- */
            case LicenseID.STATED_FRAGMENT:     title = "StatedFragment";   rawId = R.raw.statedfragment;   break;
            /* ----- Google Library ----- */
            case LicenseID.GSON:                title = "Gson";             rawId = R.raw.gson;             break;
            /* ----- Square Library ----- */
            case LicenseID.OTTO:                title = "Otto";             rawId = R.raw.otto;             break;
            case LicenseID.OKHTTP:              title = "OkHttp";           rawId = R.raw.okhttp;           break;
            case LicenseID.RETROFIT:            title = "Retrofit";         rawId = R.raw.retrofit;         break;
            case LicenseID.PICASSO:             title = "Picasso";          rawId = R.raw.picasso;          break;
            // TODO : Add new license case here
            default:                            throw new IllegalArgumentException();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getLicense() {
        return readRawFile(rawId);
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
