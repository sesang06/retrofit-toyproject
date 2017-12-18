package com.nyf.uneasyguys.test.Fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyf.uneasyguys.test.Fragment.BaseFragment;
import com.nyf.uneasyguys.test.R;

/**
 * Created by sesan on 2017-12-09.
 */

public class StatisticsFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_statistics, container, false);
        Bundle args = getArguments();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.fragment_statistics_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        return rootView;
    }
}


