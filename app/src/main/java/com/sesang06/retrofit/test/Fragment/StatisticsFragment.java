package com.sesang06.retrofit.test.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sesang06.retrofit.test.BaseAdapter;
import com.sesang06.retrofit.test.Model.ArticleModel;
import com.sesang06.retrofit.test.R;
import com.sesang06.retrofit.test.Service.ServiceHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sesan on 2017-12-09.
 */

public class StatisticsFragment extends BaseFragment  implements OnMapReadyCallback{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ArticleModel> articleModels;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    public void refresh(){
        ServiceHelper.getInstance().getArticles().enqueue(new Callback<List<ArticleModel>>() {
            @Override
            public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                System.out.println(response.body().size());
                List<ArticleModel> articleModels = response.body();
                for (ArticleModel articleModel: articleModels){
                    System.out.println(articleModel.getText());
                }
                StatisticsFragment.this.articleModels.clear();
                StatisticsFragment.this.articleModels.addAll(articleModels);
                mAdapter.notifyDataSetChanged();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<ArticleModel>> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
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
        articleModels = new ArrayList<ArticleModel>();
        mAdapter = new BaseAdapter(articleModels, getContext());

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mapView = (MapView)rootView.findViewById(R.id.map);
        mapView.getMapAsync(this);

        mSwipeRefreshLayout= (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_layout);






        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ServiceHelper.getInstance().getArticles().enqueue(new Callback<List<ArticleModel>>() {
                    @Override
                    public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                        System.out.println(response.body().size());
                        List<ArticleModel> articleModels = response.body();
                        for (ArticleModel articleModel: articleModels){
                            System.out.println(articleModel.getText());
                        }
                        StatisticsFragment.this.articleModels.clear();
                        StatisticsFragment.this.articleModels.addAll(articleModels);
                        mAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<ArticleModel>> call, Throwable t) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });
        ServiceHelper.getInstance().getArticles().enqueue(new Callback<List<ArticleModel>>() {
            @Override
            public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                System.out.println(response.body().size());
                List<ArticleModel> articleModels = response.body();
                StatisticsFragment.this.articleModels.addAll(articleModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ArticleModel>> call, Throwable t) {

            }
        });
        return rootView;
    }

    private MapView mapView =null;

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onLowMemory();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //액티비티가 처음 생성될 때 실행되는 함수

        if(mapView != null)
        {
            mapView.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng SEOUL = new LatLng(37.56, 126.97);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("수도");
        googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }

}


