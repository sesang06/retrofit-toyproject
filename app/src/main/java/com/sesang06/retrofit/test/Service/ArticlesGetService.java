package com.sesang06.retrofit.test.Service;

import com.sesang06.retrofit.test.Model.ArticleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sesan on 2017-12-14.
 */

public interface ArticlesGetService {
    //@Headers( "Content-Type: application/json" ) in Post method may use this
    @GET("/article/")
    Call<List<ArticleModel>> getArticles();
}
