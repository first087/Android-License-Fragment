package com.ethanf.licensefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ethanf.licensefragment.model.License;
import com.ethanf.licensefragment.model.LicenseID;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScrollViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScrollViewLicenseFragment extends LicenseFragmentBase {

    private static final String TAG = "LicenseFragment (SV)";

    private ScrollView scrollView;
    private TextView tvLicense;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs ArrayList<Integer> for License ID. Use constant from {@link LicenseID} class.
     * @return A new instance of fragment ScrollViewLicenseFragment.
     */
    public static ScrollViewLicenseFragment newInstance(ArrayList<Integer> licenseIDs) {
         return (ScrollViewLicenseFragment) onNewInstance(new ScrollViewLicenseFragment(), licenseIDs);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array for License ID. Use constant from {@link LicenseID} class.
     * @return A new instance of fragment ScrollViewLicenseFragment.
     */
    public static ScrollViewLicenseFragment newInstance(int[] licenseIDs) {
        return (ScrollViewLicenseFragment) onNewInstance(new ScrollViewLicenseFragment(), licenseIDs);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using without parameter.
     *
     * @return A new instance of fragment ScrollViewLicenseFragment.
     */
    public static ScrollViewLicenseFragment newInstance() {
        return (ScrollViewLicenseFragment) onNewInstance(new ScrollViewLicenseFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (DEBUG) {
            Log.d(TAG, "onCreateView(LayoutInflater, ViewGroup, Bundle)");
            Log.d(TAG, ">>>> ViewGroup = " + ((container != null) ? container.getClass().getSimpleName() : "null"));
            Log.d(TAG, ">>>> Bundle not null = " + (savedInstanceState != null));
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scroll_view_license, container, false);

        scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        tvLicense = (TextView) rootView.findViewById(R.id.tvLicense);

        return rootView;
    }

    @Override
    protected void onFirstTimeLaunched(ArrayList<License> licenses) {
        scrollView.setBackgroundColor(customUI.getTitleBackgroundColor());
        tvLicense.setTextColor(customUI.getTitleTextColor());

        tvLicense.setText("");
        for (License license : licenses) {
            tvLicense.append("-------------------------\n");
            tvLicense.append(license.getTitle() + "\n");
            tvLicense.append("-------------------------\n");
            tvLicense.append(license.getLicense() + "\n\n");
        }
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        scrollView.setBackgroundColor(customUI.getTitleBackgroundColor());
        tvLicense.setTextColor(customUI.getTitleTextColor());
    }

    @Override
    protected void onSaveState(Bundle outState) {

    }

}
