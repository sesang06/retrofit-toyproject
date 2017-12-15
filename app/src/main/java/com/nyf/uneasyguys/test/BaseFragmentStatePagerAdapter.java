package com.nyf.uneasyguys.test;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.nyf.uneasyguys.test.Fragment.BaseFragment;
import com.nyf.uneasyguys.test.Fragment.MapFragment;
import com.nyf.uneasyguys.test.Fragment.StatisticsFragment;

/**
 * Created by sesan on 2017-12-09.
 */

public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
public BaseFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        }

@Override
public Fragment getItem(int i) {
        Fragment fragment;
        switch (i){
                case 0:
                        fragment = new CameraFragment();
                        return fragment;
                case 1:
                        fragment = new MapFragment();
                        return fragment;

                case 2:
                        fragment = new StatisticsFragment();
                        return fragment;
                default:
                        fragment = new BaseFragment();
                        return fragment;
        }

        /*Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(BaseFragment.ARG_OBJECT, i + 1);

        fragment.setArguments(args);
        */

        }

@Override
public int getCount() {
        return 3;
        }


        }


