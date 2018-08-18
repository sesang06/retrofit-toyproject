package com.sesang06.retrofit.test;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.sesang06.retrofit.test.Fragment.MapFragment;
import com.sesang06.retrofit.test.Fragment.StatisticsFragment;

public class MainActivity extends AppCompatActivity {
    BaseFragmentStatePagerAdapter mBaseFragmentStatePagerAdapter;
    ViewPager mViewPager;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        System.out.println("requestCode"+ requestCode);
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                /*if (mBaseFragmentStatePagerAdapter != null){
                    if ( mBaseFragmentStatePagerAdapter.getItem(1)!= null){
                        if (mBaseFragmentStatePagerAdapter.getItem(1) instanceof MapFragment){
                            ((MapFragment)mBaseFragmentStatePagerAdapter.getItem(1) ).drawMarkers();
                        }
                    }

                }*/
                //mViewPager.setCurrentItem(1);
               // ((MapFragment)mBaseFragmentStatePagerAdapter.getCurrentFragment() ).drawMarkers();
                mViewPager.setCurrentItem(2);

                ((StatisticsFragment)mBaseFragmentStatePagerAdapter.getCurrentFragment()).refresh();

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final ActionBar actionBar = getActionBar();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

        TabLayout tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("카메라"));
        tabLayout.addTab(tabLayout.newTab().setText("지도"));
        tabLayout.addTab(tabLayout.newTab().setText("요약"));

        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mBaseFragmentStatePagerAdapter =
                new BaseFragmentStatePagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.main_pager);
        mViewPager.setAdapter(mBaseFragmentStatePagerAdapter);

        // Specify that tabs should be displayed in the action bar.
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        mViewPager.addOnPageChangeListener(new
                TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new
                                                   TabLayout.OnTabSelectedListener() {
                                                       @Override
                                                       public void onTabSelected(TabLayout.Tab tab) {
                                                           mViewPager.setCurrentItem(tab.getPosition());

                                                       }

                                                       @Override
                                                       public void onTabUnselected(TabLayout.Tab tab) {

                                                       }

                                                       @Override
                                                       public void onTabReselected(TabLayout.Tab tab) {

                                                       }

                                                   });


        // Add 3 tabs, specifying the tab's text and TabListener


    }
}
