package com.ethanf.licensefragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ethanf.licensefragment.controller.LicenseManager;
import com.ethanf.licensefragment.model.License;
import com.ethanf.licensefragment.utils.ArrayManager;

import java.util.ArrayList;

/**
 * Activities that contain this fragment must implement the
 * {@link OnAttachedListener} interface
 * to handle interaction events.
 */
public abstract class LicenseFragmentBase extends Fragment {

    private static final String ARG_LICENSE_IDS = "license_ids";

    public interface OnAttachedListener {
        void onAttached();
    }

    private OnAttachedListener mOnAttachedListener;

    protected boolean mLicenseChain;

    private ArrayList<Integer> mLicenses;
    private ArrayList<License> mCustomLicenses;

    protected LicenseFragmentBase() {
        super();
        mLicenseChain = true;
        mLicenses = new ArrayList<>();
    }

    protected static LicenseFragmentBase onNewInstance(LicenseFragmentBase fragment, ArrayList<Integer> licenseIDs) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(ARG_LICENSE_IDS, licenseIDs);
        fragment.setArguments(bundle);
        return fragment;
    }

    protected static LicenseFragmentBase onNewInstance(LicenseFragmentBase fragment, int[] licenseIDs) {
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(ARG_LICENSE_IDS, ArrayManager.asIntegerArrayList(licenseIDs));
        fragment.setArguments(bundle);
        return fragment;
    }

    protected static LicenseFragmentBase onNewInstance(LicenseFragmentBase fragment) {
        fragment.setArguments(new Bundle());
        return fragment;
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
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState == null) {
            onFirstTimeLaunched();
        } else {
            onRestoreState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSaveState(outState);
    }

    protected void onFirstTimeLaunched() {
        boolean haveArgument = (getArguments() != null && getArguments().getIntegerArrayList(ARG_LICENSE_IDS) != null);

        mLicenses.addAll(haveArgument ? getArguments().getIntegerArrayList(ARG_LICENSE_IDS) : new ArrayList<Integer>());

        LicenseManager licenseManager = new LicenseManager(getActivity().getApplicationContext());
        ArrayList<License> licenses = licenseManager.withLicenseChain(mLicenseChain).getLicenses(mLicenses);
        if (mCustomLicenses != null) licenses.addAll(mCustomLicenses);
        onFirstTimeLaunched(licenses);
    }

    protected abstract void onFirstTimeLaunched(ArrayList<License> licenses);
    protected abstract void onRestoreState(Bundle savedInstanceState);
    protected abstract void onSaveState(Bundle outState);

    @Override
    public void onDetach() {
        super.onDetach();
        mOnAttachedListener = null;
    }

    public LicenseFragmentBase withLicenseChain(boolean enableLicenseChain) {
        mLicenseChain = enableLicenseChain;
        return this;
    }

    public LicenseFragmentBase addLicense(ArrayList<Integer> licenseIDs) {
        mLicenses.addAll(licenseIDs);
        return this;
    }

    public LicenseFragmentBase addLicense(int[] licenseIDs) {
        mLicenses.addAll(ArrayManager.asIntegerArrayList(licenseIDs));
        return this;
    }

    public LicenseFragmentBase addCustomLicense(ArrayList<License> customLicenses) {
        mCustomLicenses = customLicenses;
        return this;
    }

}
