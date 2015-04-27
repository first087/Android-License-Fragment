package com.ethanf.licensefragment;

import android.app.Activity;
import android.os.Bundle;

import com.ethanf.licensefragment.controller.LicenseManager;
import com.ethanf.licensefragment.model.License;
import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.StatedFragment;

import java.util.ArrayList;

/**
 * Activities that contain this fragment must implement the
 * {@link OnAttachedListener} interface
 * to handle interaction events.
 */
public abstract class LicenseFragmentBase extends StatedFragment {

    private static final String ARG_LICENSE_IDS = "license_ids";

    public interface OnAttachedListener {
        void onAttached();
    }

    private OnAttachedListener mOnAttachedListener;

    protected boolean mLicenseChain;

    private ArrayList<License> mLicenses;

    protected LicenseFragmentBase() {
        super();
        mLicenseChain = true;
    }

    protected static void onNewInstance(LicenseFragmentBase fragment, ArrayList<Integer> licenseIDs) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(ARG_LICENSE_IDS, licenseIDs);
        fragment.setArguments(bundle);
    }

    protected static void onNewInstance(LicenseFragmentBase fragment, int[] licenseIDs) {
        ArrayList<Integer> licenseList = new ArrayList<>();
        for (int licenseID : licenseIDs) {
            licenseList.add(licenseID);
        }

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(ARG_LICENSE_IDS, licenseList);
        fragment.setArguments(bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnAttachedListener = (OnAttachedListener) activity;
            mOnAttachedListener.onAttached();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();

        ArrayList<Integer> licenseIDs = getArguments().getIntegerArrayList(ARG_LICENSE_IDS);

        LicenseManager licenseManager = new LicenseManager(getActivity().getApplicationContext());
        ArrayList<License> licenses = licenseManager.withLicenseChain(mLicenseChain).getLicenses(licenseIDs);
        if (mLicenses != null) licenses.addAll(mLicenses);
        onFirstTimeLaunched(licenses);
    }

    protected abstract void onFirstTimeLaunched(ArrayList<License> licenses);

    @Override
    public void onDetach() {
        super.onDetach();
        mOnAttachedListener = null;
    }

    public LicenseFragmentBase withLicenseChain(boolean enableLicenseChain) {
        mLicenseChain = enableLicenseChain;
        return this;
    }

    public LicenseFragmentBase addCustomLicense(ArrayList<License> licenses) {
        mLicenses = licenses;

        return this;
    }

}
