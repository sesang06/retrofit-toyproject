package com.nyf.uneasyguys.test.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyf.uneasyguys.test.Fragment.BaseFragment;
import com.nyf.uneasyguys.test.R;

/**
 * Created by sesan on 2017-12-09.
 */

public class StatisticsFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_statistics, container, false);
        Bundle args = getArguments();
        return rootView;
    }
}


