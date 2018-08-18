package com.sesang06.retrofit.test.Service;

import com.sesang06.retrofit.test.Model.ArticleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sesan on 2018-01-14.
 */

public interface ArticleGetService {
    //@Headers( "Content-Type: application/json" ) in Post method may use this
    @GET("/article/{id}/")
    Call<ArticleModel> getArticle(@Path("id") int id);
}
