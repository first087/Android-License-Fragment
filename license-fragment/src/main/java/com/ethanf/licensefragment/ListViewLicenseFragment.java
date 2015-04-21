package com.ethanf.licensefragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ethanf.licensefragment.model.License;

import java.util.LinkedHashSet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewLicenseFragment extends LicenseFragmentBase {



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array of License ID
     * @return A new instance of fragment ListViewLicenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListViewLicenseFragment newInstance(int[] licenseIDs) {
        ListViewLicenseFragment fragment = new ListViewLicenseFragment();

        onNewInstance(fragment, licenseIDs);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_view_license, container, false);

        // TODO : Matching view

        return rootView;
    }

    @Override
    protected void onFirstTimeLaunched(LinkedHashSet<License> licenses) {
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
