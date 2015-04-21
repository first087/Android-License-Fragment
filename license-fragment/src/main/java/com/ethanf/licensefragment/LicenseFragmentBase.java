package com.ethanf.licensefragment;

import android.app.Activity;
import android.os.Bundle;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.StatedFragment;

public abstract class LicenseFragmentBase extends StatedFragment {

    public interface OnAttachedListener {
        void onAttached();
    }

    private OnAttachedListener mOnAttachedListener;

    protected boolean mLicenseChain;

    protected LicenseFragmentBase() {
        super();
        mLicenseChain = true;
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

        // TODO : Initial one time on create fragment
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        // TODO : Restore State
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        // TODO : Save State
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnAttachedListener = null;
    }

    public LicenseFragmentBase withLicenseChain(boolean enableLicenseChain) {
        mLicenseChain = enableLicenseChain;
        return this;
    }

}
