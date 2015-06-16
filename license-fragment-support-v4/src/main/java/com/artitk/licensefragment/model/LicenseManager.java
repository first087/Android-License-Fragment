package com.artitk.licensefragment.model;

import android.content.Context;

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
        LicenseHashMap licenses = new LicenseHashMap()
                .add(LicenseID.LICENSE_FRAGMENT)
                .add(licenseIDs);

        return new ArrayList<>(licenses.values());
    }

    private License getLicenseById(int licenseID) {
        return new License(context, licenseID);
    }

    private int[] getLicenseChains(int licenseID) {
        switch (licenseID) {
            case LicenseID.RETROFIT:            return new int[] { LicenseID.OKHTTP };
            case LicenseID.STATED_FRAGMENT:     return new int[] { LicenseID.OTTO };
            // TODO : Add reference license here
            default:                            return new int[] { };
        }
    }

    private class LicenseHashMap extends LinkedHashMap<Integer, License> {

        public LicenseHashMap add(int licenseID) {
            put(licenseID, getLicenseById(licenseID));
            addChain(getLicenseChains(licenseID));
            return this;
        }

        public LicenseHashMap add(ArrayList<Integer> licenseIDs) {
            if (licenseIDs != null) {
                for (int licenseID : licenseIDs) {
                    add(licenseID);
                }
            }

            return this;
        }

        private void addChain(int[] licenseIDs) {
            if (!mLicenseChain) return;
            if (licenseIDs == null) return;

            for (int licenseID : licenseIDs) {
                put(licenseID, getLicenseById(licenseID));
            }
        }

    }
}
