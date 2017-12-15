package com.nyf.uneasyguys.test.Service;

import com.nyf.uneasyguys.test.Model.ArticleModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sesan on 2017-12-14.
 */

public interface IPlusService {
    //@Headers( "Content-Type: application/json" ) in Post method may use this
    @GET("/api/category")
    Call<List<ArticleModel>> getAllCategory();
}
