package com.ethanf.licensefragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;

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

    private static final String TAG = "LicenseFragment";

    private static final String ARG_LICENSE_IDS = "license_ids";

    public interface OnAttachedListener {
        void onAttached();
    }

    private OnAttachedListener mOnAttachedListener;

    protected final boolean DEBUG = false;

    protected boolean isLog;

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
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);

        if (DEBUG) {
            Log.d(TAG, "onInflate(Activity, AttributeSet, Bundle)");
            Log.d(TAG, ">>>> Activity        = " + activity.getClass().getSimpleName());
            Log.d(TAG, ">>>> AttributeSet    = " + attrs);
            Log.d(TAG, ">>>> Bundle not null = " + (savedInstanceState != null));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (DEBUG) {
            Log.d(TAG, "onAttach(Activity)");
            Log.d(TAG, ">>>> Activity = " + activity.getClass().getSimpleName());
        }

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

        if (DEBUG) {
            Log.d(TAG, "onActivityCreated(Bundle)");
            Log.d(TAG, ">>>> Bundle not null = " + (savedInstanceState != null));
        }

        if (savedInstanceState == null) {
            onFirstTimeLaunched();
        } else {
            isLog = savedInstanceState.getBoolean("log_enable", false);

            if (isLog) Log.i(TAG, "Call -> onRestoreState(Bundle)");
            onRestoreState(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (DEBUG) {
            Log.d(TAG, "onSaveInstanceState(Bundle)");
            Log.d(TAG, ">>>> Bundle not null only");
        }

        outState.putBoolean("log_enable", isLog);

        if (isLog) Log.i(TAG, "Call -> onSaveState(Bundle)");
        onSaveState(outState);
    }

    protected void onFirstTimeLaunched() {
        boolean haveArgument = (getArguments() != null && getArguments().getIntegerArrayList(ARG_LICENSE_IDS) != null);

        mLicenses.addAll(haveArgument ? getArguments().getIntegerArrayList(ARG_LICENSE_IDS) : new ArrayList<Integer>());

        LicenseManager licenseManager = new LicenseManager(getActivity().getApplicationContext());
        ArrayList<License> licenses = licenseManager.withLicenseChain(mLicenseChain).getLicenses(mLicenses);
        if (mCustomLicenses != null) licenses.addAll(mCustomLicenses);

        if (isLog) Log.i(TAG, "Call -> onFirstTimeLaunched(ArrayList<License>)");
        onFirstTimeLaunched(licenses);
    }

    protected abstract void onFirstTimeLaunched(ArrayList<License> licenses);
    protected abstract void onRestoreState(Bundle savedInstanceState);
    protected abstract void onSaveState(Bundle outState);

    @Override
    public void onDetach() {
        super.onDetach();

        if (DEBUG) Log.d(TAG, "onDetach()");

        mOnAttachedListener = null;
    }

    public LicenseFragmentBase setLog(boolean enableLog) {
        Log.i(TAG, "Log - Turn " + (enableLog ? "on" : "off") + "!");

        isLog = enableLog;

        return this;
    }

    public LicenseFragmentBase withLicenseChain(boolean enableLicenseChain) {
        if (isLog) Log.i(TAG, "License Chain - " + (enableLicenseChain ? "enable" : "disable"));

        mLicenseChain = enableLicenseChain;
        return this;
    }

    public LicenseFragmentBase addLicense(ArrayList<Integer> licenseIDs) {
        if (isLog) Log.i(TAG, "Add License - count = " + licenseIDs.size());

        mLicenses.addAll(licenseIDs);
        return this;
    }

    public LicenseFragmentBase addLicense(int[] licenseIDs) {
        if (isLog) Log.i(TAG, "Add License - count = " + licenseIDs.length);

        mLicenses.addAll(ArrayManager.asIntegerArrayList(licenseIDs));
        return this;
    }

    public LicenseFragmentBase addCustomLicense(ArrayList<License> customLicenses) {
        if (isLog) Log.i(TAG, "Add Custom License - count = " + customLicenses.size());

        mCustomLicenses = customLicenses;
        return this;
    }

}
