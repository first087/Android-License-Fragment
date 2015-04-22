package com.ethanf.licensefragment;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ethanf.licensefragment.model.License;

import java.util.Collection;

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

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        //recyclerView.addItemDecoration();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    protected void onFirstTimeLaunched(Collection<License> licenses) {
        // TODO : Set data to view

        recyclerView.setAdapter(new RecyclerViewAdapter(new String[] { "ทดสอบ 1", "ทดสอบ 2", "ทดสอบ 3", "ทดสอบ 4", "ทดสอบ 5", "ทดสอบ 6", "ทดสอบ 7", "ทดสอบ 8", "ทดสอบ 9", "ทดสอบ 10" }));



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

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private String[] data;

        public RecyclerViewAdapter(String[] data) {
            this.data = data;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_license, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvItemTitle.setText("pos = " + position);
            holder.tvItemLicense.setText("data = " + data[position]);
        }

        @Override
        public int getItemCount() {
            return data.length;
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
