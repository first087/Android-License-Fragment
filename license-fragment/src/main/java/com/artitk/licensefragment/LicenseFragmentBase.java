package com.artitk.licensefragment;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;

import com.artitk.licensefragment.model.CustomUI;
import com.artitk.licensefragment.model.LicenseID;
import com.artitk.licensefragment.model.LicenseManager;
import com.artitk.licensefragment.model.License;
import com.artitk.licensefragment.utils.ArrayManager;
import com.artitk.licensefragment.utils.BitwiseManager;

import java.util.ArrayList;

/**
 * Activities that contain this fragment can implement the
 * {@link OnAttachedListener} interface
 * to handle interaction events.
 */
public abstract class LicenseFragmentBase extends Fragment {

    private static final String TAG = "LicenseFragment";

    private static final String ARG_LICENSE_IDS = "license_ids";

    protected boolean useFromFragmentTag;
    protected CustomUI customUI, mCustomUI;

    public interface OnAttachedListener {
        void onAttached();
    }

    private OnAttachedListener mOnAttachedListener;

    protected final boolean DEBUG = false;

    protected boolean isLog;

    protected boolean mLicenseChain;

    private ArrayList<Integer> mLicenses;
    private ArrayList<License> mCustomLicenses;

    private int mLicenseIDFlags;

    protected LicenseFragmentBase() {
        super();
        mLicenseChain = true;
        mLicenses = new ArrayList<>();
        customUI = new CustomUI();
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

        useFromFragmentTag = true;

        if (DEBUG) {
            Log.d(TAG, "onInflate(Activity, AttributeSet, Bundle)");
            Log.d(TAG, ">>>> Activity        = " + activity.getClass().getSimpleName());
            Log.d(TAG, ">>>> AttributeSet    = " + attrs);
            Log.d(TAG, ">>>> Bundle not null = " + (savedInstanceState != null));
        }

        TypedArray typedArray = activity.obtainStyledAttributes(attrs, R.styleable.LicenseFragment);

        mLicenseIDFlags = typedArray.getInt(R.styleable.LicenseFragment_lfLicenseID, 0);
        mLicenseChain   = typedArray.getBoolean(R.styleable.LicenseFragment_lfLicenseChain, true);

        Resources resources = activity.getResources();

        customUI.setTitleBackgroundColor(typedArray.getColor(R.styleable.LicenseFragment_lfTitleBackgroundColor,
                resources.getColor(R.color.license_fragment_background)));
        customUI.setTitleTextColor(typedArray.getColor(R.styleable.LicenseFragment_lfTitleTextColor,
                resources.getColor(R.color.license_fragment_text_color)));
        customUI.setLicenseBackgroundColor(typedArray.getColor(R.styleable.LicenseFragment_lfLicenseBackgroundColor,
                resources.getColor(R.color.license_fragment_background_item)));
        customUI.setLicenseTextColor(typedArray.getColor(R.styleable.LicenseFragment_lfLicenseTextColor,
                resources.getColor(R.color.license_fragment_text_color_item)));

        typedArray.recycle();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (DEBUG) {
            Log.d(TAG, "onAttach(Activity)");
            Log.d(TAG, ">>>> Activity = " + activity.getClass().getSimpleName());
            Log.d(TAG, "useFromFragmentTag = " + useFromFragmentTag);
        }

        if (!useFromFragmentTag) {
            Resources resources = activity.getResources();

            customUI.setTitleBackgroundColor(resources.getColor(R.color.license_fragment_background));
            customUI.setTitleTextColor(resources.getColor(R.color.license_fragment_text_color));
            customUI.setLicenseBackgroundColor(resources.getColor(R.color.license_fragment_background_item));
            customUI.setLicenseTextColor(resources.getColor(R.color.license_fragment_text_color_item));
        }

        try {
            mOnAttachedListener = (OnAttachedListener) activity;
            mOnAttachedListener.onAttached();
        } catch (ClassCastException e) {
            if (isLog) e.printStackTrace();
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
            if (mCustomUI != null) {
                if (mCustomUI.getTitleBackgroundColor() != 0)   customUI.setTitleBackgroundColor(mCustomUI.getTitleBackgroundColor());
                if (mCustomUI.getTitleTextColor() != 0)         customUI.setTitleTextColor(mCustomUI.getTitleTextColor());
                if (mCustomUI.getLicenseBackgroundColor() != 0) customUI.setLicenseBackgroundColor(mCustomUI.getLicenseBackgroundColor());
                if (mCustomUI.getLicenseTextColor() != 0)       customUI.setLicenseTextColor(mCustomUI.getLicenseTextColor());
            }

            onFirstTimeLaunched();
        } else {
            isLog = savedInstanceState.getBoolean("log_enable", false);

            int[] intsCustomUI = savedInstanceState.getIntArray("custom_ui");

            customUI = new CustomUI();
            customUI.setTitleBackgroundColor(intsCustomUI[0]);
            customUI.setTitleTextColor(intsCustomUI[1]);
            customUI.setLicenseBackgroundColor(intsCustomUI[2]);
            customUI.setLicenseTextColor(intsCustomUI[3]);

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

        outState.putIntArray("custom_ui", new int[] {
                customUI.getTitleBackgroundColor(),
                customUI.getTitleTextColor(),
                customUI.getLicenseBackgroundColor(),
                customUI.getLicenseTextColor()
        });

        if (isLog) Log.i(TAG, "Call -> onSaveState(Bundle)");
        onSaveState(outState);
    }

    protected void onFirstTimeLaunched() {
        boolean haveArgument = (getArguments() != null && getArguments().getIntegerArrayList(ARG_LICENSE_IDS) != null);

        mLicenses.addAll(haveArgument ? getArguments().getIntegerArrayList(ARG_LICENSE_IDS) : new ArrayList<Integer>());

        addLicensesFromFlag();

        LicenseManager licenseManager = new LicenseManager(getActivity().getApplicationContext());
        ArrayList<License> licenses = licenseManager.withLicenseChain(mLicenseChain).getLicenses(mLicenses);
        if (mCustomLicenses != null) licenses.addAll(mCustomLicenses);

        if (isLog) Log.i(TAG, "Call -> onFirstTimeLaunched(ArrayList<License>)");
        onFirstTimeLaunched(licenses);
    }

    private void addLicensesFromFlag() {
        if (mLicenseIDFlags == 0) return;;

        int[] licenseIDs = {
                /* ----- This Library ----- */
//                LicenseID.LICENSE_FRAGMENT,   // Add by default
                /* ----- Google Library ----- */
                LicenseID.GSON,
                /* ----- Square Library ----- */
                LicenseID.OTTO,
                LicenseID.OKHTTP,
                LicenseID.RETROFIT,
                LicenseID.PICASSO,
                /* ----- Other Library ----- */
                LicenseID.STATED_FRAGMENT
                // TODO : Add new license constant here
        };

        BitwiseManager bitwiseManager = new BitwiseManager(mLicenseIDFlags);

        for (int licenseID : licenseIDs) {
            if (bitwiseManager.isThisFlag(licenseID)) mLicenses.add(licenseID);
        }
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

    /**
     * @param enableLog On/Off print log.
     * @return This instance.
     */
    public LicenseFragmentBase setLog(boolean enableLog) {
        Log.i(TAG, "Log - Turn " + (enableLog ? "on" : "off") + "!");

        isLog = enableLog;

        return this;
    }

    /**
     * @param enableLicenseChain On/Off License chain. Default is true.
     * @return This instance.
     */
    public LicenseFragmentBase withLicenseChain(boolean enableLicenseChain) {
        if (isLog) Log.i(TAG, "License Chain - " + (enableLicenseChain ? "enable" : "disable"));

        mLicenseChain = enableLicenseChain;
        return this;
    }

    /**
     * @param licenseIDs ArrayList<Integer> for License ID. Use constant from {@link LicenseID} class.
     * @return This instance.
     */
    public LicenseFragmentBase addLicense(ArrayList<Integer> licenseIDs) {
        if (isLog) Log.i(TAG, "Add License - count = " + licenseIDs.size());

        mLicenses.addAll(licenseIDs);
        return this;
    }

    /**
     * @param licenseIDs Array for License ID. Use constant from {@link LicenseID} class.
     * @return This instance.
     */
    public LicenseFragmentBase addLicense(int[] licenseIDs) {
        if (isLog) Log.i(TAG, "Add License - count = " + licenseIDs.length);

        mLicenses.addAll(ArrayManager.asIntegerArrayList(licenseIDs));
        return this;
    }

    /**
     * @param customLicenses ArrayList<License> for {@link License} class.
     * @return This instance.
     */
    public LicenseFragmentBase addCustomLicense(ArrayList<License> customLicenses) {
        if (isLog) Log.i(TAG, "Add Custom License - count = " + customLicenses.size());

        mCustomLicenses = customLicenses;
        return this;
    }

    /**
     * @param customUI {@link CustomUI} class.
     * @return This instance.
     */
    public LicenseFragmentBase setCustomUI(CustomUI customUI) {
        if (isLog) Log.i(TAG, "Set Custom UI");

        mCustomUI = customUI;
        return this;
    }

}
