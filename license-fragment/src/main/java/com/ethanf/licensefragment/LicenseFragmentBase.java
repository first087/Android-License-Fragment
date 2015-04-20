package com.ethanf.licensefragment;

import android.os.Bundle;

import com.inthecheesefactory.thecheeselibrary.fragment.support.v4.app.StatedFragment;

public abstract class LicenseFragmentBase extends StatedFragment {

    protected OnAttachedListener mOnAttachedListener;

    @Override
    protected void onFirstTimeLaunched() {
        super.onFirstTimeLaunched();

        // TODO : Initial one time on create fragment
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        // TODO : Save State
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        // TODO : Restore State
    }

    public interface OnAttachedListener {
        void onAttached();
    }

}
