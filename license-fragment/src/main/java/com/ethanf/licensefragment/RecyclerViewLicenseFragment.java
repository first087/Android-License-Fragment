package com.ethanf.licensefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanf.licensefragment.model.License;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerViewLicenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerViewLicenseFragment extends LicenseFragmentBase {

    private RecyclerView recyclerView;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs ArrayList<Integer> for License ID
     * @return A new instance of fragment RecyclerViewLicenseFragment.
     */
    public static RecyclerViewLicenseFragment newInstance(ArrayList<Integer> licenseIDs) {
        RecyclerViewLicenseFragment fragment = new RecyclerViewLicenseFragment();

        onNewInstance(fragment, licenseIDs);

        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param licenseIDs Array for License ID
     * @return A new instance of fragment RecyclerViewLicenseFragment.
     */
    public static RecyclerViewLicenseFragment newInstance(int[] licenseIDs) {
        RecyclerViewLicenseFragment fragment = new RecyclerViewLicenseFragment();

        onNewInstance(fragment, licenseIDs);

        return fragment;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment RecyclerViewLicenseFragment.
     */
    public static RecyclerViewLicenseFragment newInstance() {
        return new RecyclerViewLicenseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recycler_view_license, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
//        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration(){ });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

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

        recyclerView.setAdapter(new RecyclerViewAdapter(titleList, licenseList));
    }

    @Override
    protected void onRestoreState(Bundle savedInstanceState) {
        super.onRestoreState(savedInstanceState);

        ArrayList<String> titleList   = savedInstanceState.getStringArrayList("license_title");
        ArrayList<String> licenseList = savedInstanceState.getStringArrayList("license_text");
        recyclerView.setAdapter(new RecyclerViewAdapter(titleList, licenseList));
    }

    @Override
    protected void onSaveState(Bundle outState) {
        super.onSaveState(outState);

        outState.putStringArrayList("license_title", ((RecyclerViewAdapter) recyclerView.getAdapter()).getTitleList());
        outState.putStringArrayList("license_text", ((RecyclerViewAdapter) recyclerView.getAdapter()).getLicenseList());
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private ArrayList<String> titleList, licenseList;

        public RecyclerViewAdapter(ArrayList<String> titleList, ArrayList<String> licenseList) {
            this.titleList   = titleList;
            this.licenseList = licenseList;
        }

        public ArrayList<String> getTitleList() {
            return titleList;
        }

        public ArrayList<String> getLicenseList() {
            return licenseList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_license, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvItemTitle.setText(titleList.get(position));
            holder.tvItemLicense.setText(licenseList.get(position));
        }

        @Override
        public int getItemCount() {
            return titleList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView tvItemTitle, tvItemLicense;

            public ViewHolder(View itemView) {
                super(itemView);

                tvItemTitle   = (TextView) itemView.findViewById(R.id.tvItemTitle);
                tvItemLicense = (TextView) itemView.findViewById(R.id.tvItemLicense);
            }
        }
    }

}
