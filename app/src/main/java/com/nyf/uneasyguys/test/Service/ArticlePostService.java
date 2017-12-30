package com.nyf.uneasyguys.test.Service;

import com.nyf.uneasyguys.test.Model.ArticleModel;
import com.nyf.uneasyguys.test.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sesan on 2017-12-30.
 */

public interface ArticlePostService {
    @FormUrlEncoded
    @POST("/article")
    Call<String> postArticle(@Field("point") long point, @Field("text") String text);
}
