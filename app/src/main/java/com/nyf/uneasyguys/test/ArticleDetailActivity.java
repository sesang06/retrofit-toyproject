package com.nyf.uneasyguys.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nyf.uneasyguys.test.Model.ArticleModel;
import com.nyf.uneasyguys.test.Service.DateService;
import com.nyf.uneasyguys.test.Service.ServiceHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sesan on 2018-01-14.
 */

public class ArticleDetailActivity extends AppCompatActivity {
    public TextView createdTimeTextView;
    public TextView userTextView;
    public TextView pointTextView;
    public TextView titleTextView;
    public ImageView profileImageView;
    public ArticleModel articleModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("디테일");
        Intent intent = new Intent(this.getIntent());
        int id =  intent.getIntExtra("id", 0);
        createdTimeTextView = (TextView) findViewById(R.id.created_time_text_view);
        userTextView = (TextView) findViewById(R.id.user_text_view);
        pointTextView = (TextView) findViewById(R.id.point_text_view);
        profileImageView = (ImageView) findViewById(R.id.user_image_view);
        titleTextView = (TextView) findViewById(R.id.title_text_view);
        ServiceHelper.getInstance().getArticle(id).enqueue(new Callback<ArticleModel>() {
            @Override
            public void onResponse(Call<ArticleModel> call, Response<ArticleModel> response) {
                articleModel = response.body();
                createdTimeTextView.setText(articleModel.getCreatedTime());
                userTextView.setText(articleModel.getUser());
                pointTextView.setText(articleModel.getPoint()+"");
                titleTextView.setText(articleModel.getText());
            }

            @Override
            public void onFailure(Call<ArticleModel> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case android.R.id.home :
                finish();
                return true;
            case R.id.menu_post:

                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
