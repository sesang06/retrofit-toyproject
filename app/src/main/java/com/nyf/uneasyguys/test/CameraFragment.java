package com.nyf.uneasyguys.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sesan on 2017-12-09.
 */

public class CameraFragment extends DemoObjectFragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_camera, container, false);
        Bundle args = getArguments();
        return rootView;
    }
}

