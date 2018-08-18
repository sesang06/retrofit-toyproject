package com.sesang06.retrofit.test;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sesang06.retrofit.test.Model.ArticleModel;
import com.sesang06.retrofit.test.Service.ServiceHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sesan on 2018-01-14.
 */

public class ArticleListActivity  extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ArticleModel> articleModels;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public void refresh(){
        ServiceHelper.getInstance().getArticles().enqueue(new Callback<List<ArticleModel>>() {
            @Override
            public void onResponse(Call<List<ArticleModel>> call, Response<List<ArticleModel>> response) {
                System.out.println(response.body().size());
                List<ArticleModel> articleModels = response.body();
                for (ArticleModel articleModel: articleModels){
                    System.out.println(articleModel.getText());
                }
                ArticleListActivity.this.articleModels.clear();
                ArticleListActivity.this.articleModels.addAll(articleModels);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.fragment_statistics_recycler_view);

        mRecyclerView.setHasFixedSize(true);
        articleModels = new ArrayList<ArticleModel>();

        mAdapter = new DetailArticleAdapter(articleModels, this);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout= (SwipeRefreshLayout)  findViewById(R.id.swipe_layout);
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
                        ArticleListActivity.this.articleModels.clear();
                        ArticleListActivity.this.articleModels.addAll(articleModels);
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
                ArticleListActivity.this.articleModels.addAll(articleModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ArticleModel>> call, Throwable t) {

            }
        });

    }

}
