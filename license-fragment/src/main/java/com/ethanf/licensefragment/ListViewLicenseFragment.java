package com.ethanf.licensefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ethanf.licensefragment.model.License;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewLicenseFragment extends LicenseFragmentBase {

    private ListView listView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array of License ID
     * @return A new instance of fragment ListViewLicenseFragment.
     */
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

        listView = (ListView) rootView.findViewById(R.id.listView);

        return rootView;
    }

    @Override
    protected void onFirstTimeLaunched(ArrayList<License> licenses) {
        ArrayList<String> titleList = new ArrayList<>();
        ArrayList<String> licenseList = new ArrayList<>();

        for (License license : licenses) {
            titleList.add(license.getTitle());
            licenseList.add(license.getLicense());
        }

        listView.setAdapter(new ListViewAdapter(titleList, licenseList));
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        ArrayList<String> titleList   = savedInstanceState.getStringArrayList("license_title");
        ArrayList<String> licenseList = savedInstanceState.getStringArrayList("license_text");
        listView.setAdapter(new ListViewAdapter(titleList, licenseList));
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        outState.putStringArrayList("license_title", ((ListViewAdapter) listView.getAdapter()).getTitleList());
        outState.putStringArrayList("license_text", ((ListViewAdapter) listView.getAdapter()).getLicenseList());
    }

    private class ListViewAdapter extends BaseAdapter {

        private ArrayList<String> titleList, licenseList;

        public ListViewAdapter(ArrayList<String> titleList, ArrayList<String> licenseList) {
            this.titleList = titleList;
            this.licenseList = licenseList;
        }

        public ArrayList<String> getTitleList() {
            return titleList;
        }

        public ArrayList<String> getLicenseList() {
            return licenseList;
        }

        @Override
        public int getCount() {
            return titleList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;

            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_license, null);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.tvItemTitle.setText(titleList.get(i));
            viewHolder.tvItemLicense.setText(licenseList.get(i));

            return view;
        }

        private class ViewHolder {

            public TextView tvItemTitle, tvItemLicense;

            public ViewHolder(View view) {
                tvItemTitle   = (TextView) view.findViewById(R.id.tvItemTitle);
                tvItemLicense = (TextView) view.findViewById(R.id.tvItemLicense);
            }

        }
    }

}
