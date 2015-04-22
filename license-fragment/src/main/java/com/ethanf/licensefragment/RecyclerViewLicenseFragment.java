package com.ethanf.licensefragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanf.licensefragment.model.License;

import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewLicenseFragment extends LicenseFragmentBase {



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array of License ID
     * @return A new instance of fragment RecyclerViewLicenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerViewLicenseFragment newInstance(int[] licenseIDs) {
        RecyclerViewLicenseFragment fragment = new RecyclerViewLicenseFragment();

        onNewInstance(fragment, licenseIDs);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view_license, container, false);

        // TODO : Matching view

        return rootView;
    }

    @Override
    protected void onFirstTimeLaunched(Collection<License> licenses) {
        // TODO : Set data to view





    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        // TODO : Restore data
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        // TODO : Save data
    }

}
