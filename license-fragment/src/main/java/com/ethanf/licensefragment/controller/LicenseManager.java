package com.ethanf.licensefragment.controller;

import android.content.Context;

import com.ethanf.licensefragment.model.License;
import com.ethanf.licensefragment.model.LicenseID;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Artit on 20/4/2558.
 */
public class LicenseManager {

    private Context context;
    private boolean mLicenseChain;

    public LicenseManager(Context context) {
        this.context = context;
        mLicenseChain = true;
    }

    public LicenseManager withLicenseChain(boolean enableLicenseChain) {
        mLicenseChain = enableLicenseChain;
        return this;
    }

    public ArrayList<License> getLicenses(ArrayList<Integer> licenseIDs) {
        LicenseHashMap licenses = new LicenseHashMap();

        licenses.add(LicenseID.LICENSE_FRAGMENT);
        if (mLicenseChain) {
            licenses.add(LicenseID.STATED_FRAGMENT);
            licenses.add(LicenseID.OTTO);
        }

        if (licenseIDs != null) {
            for (int licenseID : licenseIDs) {
                licenses.add(licenseID);
                if (mLicenseChain) {
                    for (int licenseChainID : getLicenseChains(licenseID)) {
                        licenses.add(licenseChainID);
                    }
                }
            }
        }

        return new ArrayList<>(licenses.values());
    }

    private License getLicenseById(int licenseID) {
        return new License(context, licenseID);
    }

    private int[] getLicenseChains(int licenseID) {
        switch (licenseID) {
            case LicenseID.LICENSE_FRAGMENT:    return new int[] { LicenseID.STATED_FRAGMENT };
            case LicenseID.STATED_FRAGMENT:     return new int[] { LicenseID.OTTO };
            case LicenseID.RETROFIT:            return new int[] { LicenseID.OKHTTP };
            // TODO : Add reference license here
            default:                            return new int[] { };
        }
    }

    private class LicenseHashMap extends LinkedHashMap<Integer, License> {

        public LicenseHashMap add(int licenseID) {
            put(licenseID, getLicenseById(licenseID));
            return this;
        }

    }
}
