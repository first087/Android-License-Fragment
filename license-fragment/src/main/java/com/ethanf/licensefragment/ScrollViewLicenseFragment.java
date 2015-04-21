package com.ethanf.licensefragment;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanf.licensefragment.model.License;

import java.util.LinkedHashSet;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScrollViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScrollViewLicenseFragment extends LicenseFragmentBase {

    private TextView tvLicense;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array of License ID
     * @return A new instance of fragment ScrollViewLicenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScrollViewLicenseFragment newInstance(int[] licenseIDs) {
        ScrollViewLicenseFragment fragment = new ScrollViewLicenseFragment();

        onNewInstance(fragment, licenseIDs);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_scroll_view_license, container, false);

        tvLicense = (TextView) rootView.findViewById(R.id.tvLicense);

        return rootView;
    }

    @Override
    protected void onFirstTimeLaunched(LinkedHashSet<License> licenses) {
        tvLicense.setText("");
        for (License license : licenses) {
            tvLicense.append(license.getTitle() + "\n");
            tvLicense.append("-------------------------\n");
            tvLicense.append(license.getLicense() + "\n\n");
        }
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        tvLicense.setText(savedInstanceState.getCharSequence("license_text"));
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        outState.putCharSequence("license_text", tvLicense.getText());
    }

}
