package com.sesang06.retrofit.test;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.sesang06.retrofit.test.Fragment.BaseFragment;
import com.sesang06.retrofit.test.Fragment.MapFragment;
import com.sesang06.retrofit.test.Fragment.StatisticsFragment;

/**
 * Created by sesan on 2017-12-09.
 */

public class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
public BaseFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        }
private Fragment mCurrentFragment;
public Fragment getCurrentFragment(){
        return mCurrentFragment;
}
        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
                if (getCurrentFragment() != object) {
                        mCurrentFragment = ((Fragment) object);
                }
                super.setPrimaryItem(container, position, object);
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


