package com.ethanf.licensefragment.controller;

import android.content.Context;

import com.ethanf.licensefragment.model.License;
import com.ethanf.licensefragment.model.LicenseID;

import java.util.LinkedHashSet;

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

    public LinkedHashSet<License> getLicenses(int[] licenseIDs) {
        LinkedHashSet<License> licenses = new LinkedHashSet<>();

        licenses.add(getLicenseById(LicenseID.LICENSE_FRAGMENT));
        if (mLicenseChain) {
            licenses.add(getLicenseById(LicenseID.STATED_FRAGMENT));
            licenses.add(getLicenseById(LicenseID.OTTO));
        }

        for (int licenseID : licenseIDs) {
            licenses.add(getLicenseById(licenseID));
            if (mLicenseChain) {
                for (int licenseChainID : getLicenseChains(licenseID)) {
                    licenses.add(getLicenseById(licenseChainID));
                }
            }
        }

        return licenses;
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
}
