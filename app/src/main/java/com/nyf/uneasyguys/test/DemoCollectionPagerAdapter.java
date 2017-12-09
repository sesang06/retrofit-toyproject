package com.nyf.uneasyguys.test;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * Created by sesan on 2017-12-09.
 */

public class  DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
public DemoCollectionPagerAdapter(FragmentManager fm) {
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
                        fragment = new DemoObjectFragment();
                        return fragment;
        }

        /*Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);

        fragment.setArguments(args);
        */

        }

@Override
public int getCount() {
        return 3;
        }


        }


