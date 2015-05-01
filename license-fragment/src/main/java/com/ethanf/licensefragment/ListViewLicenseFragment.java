package com.ethanf.licensefragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.ethanf.licensefragment.model.License;
import com.ethanf.licensefragment.model.LicenseID;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListViewLicenseFragment extends LicenseFragmentBase {

    private static final String TAG = "LicenseFragment (LV)";

    private ListView listView;

    private int viewBackgroundItemColor, viewTextItemColor;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs ArrayList<Integer> for License ID. Use constant from {@link LicenseID} class.
     * @return A new instance of fragment ListViewLicenseFragment.
     */
    public static ListViewLicenseFragment newInstance(ArrayList<Integer> licenseIDs) {
        return (ListViewLicenseFragment) onNewInstance(new ListViewLicenseFragment(), licenseIDs);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array for License ID. Use constant from {@link LicenseID} class.
     * @return A new instance of fragment ListViewLicenseFragment.
     */
    public static ListViewLicenseFragment newInstance(int[] licenseIDs) {
        return (ListViewLicenseFragment) onNewInstance(new ListViewLicenseFragment(), licenseIDs);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using without parameter.
     *
     * @return A new instance of fragment ListViewLicenseFragment.
     */
    public static ListViewLicenseFragment newInstance() {
        return (ListViewLicenseFragment) onNewInstance(new ListViewLicenseFragment());
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

        TypedArray typedArray = activity.obtainStyledAttributes(attrs, R.styleable.ListViewLicenseFragment);

        viewBackgroundItemColor = typedArray.getColor(R.styleable.ListViewLicenseFragment_lfBackgroundListViewItem,
                activity.getResources().getColor(R.color.license_fragment_background_item));
        viewTextItemColor = typedArray.getColor(R.styleable.ListViewLicenseFragment_lfTextColorListViewItem,
                activity.getResources().getColor(R.color.license_fragment_text_color_item));

        typedArray.recycle();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!useFromFragmentTag) {
            viewBackgroundItemColor = activity.getResources().getColor(R.color.license_fragment_background_item);
            viewTextItemColor = activity.getResources().getColor(R.color.license_fragment_text_color_item);
        }
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

        listView.setBackgroundColor(viewBackgroundColor);

        listView.setAdapter(new ListViewAdapter(titleList, licenseList));
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        listView.setBackgroundColor(viewBackgroundColor);

        ArrayList<String> titleList   = savedInstanceState.getStringArrayList("license_title");
        ArrayList<String> licenseList = savedInstanceState.getStringArrayList("license_text");
        listView.setAdapter(new ListViewAdapter(titleList, licenseList));
    }

    @Override
    protected void onSaveState(Bundle outState) {
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

                tvItemTitle.setTextColor(viewTextColor);
                tvItemLicense.setBackgroundColor(viewBackgroundItemColor);
                tvItemLicense.setTextColor(viewTextItemColor);
            }

        }
    }

}
