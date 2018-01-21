package com.nyf.uneasyguys.test.Service;

import android.support.annotation.FractionRes;

import com.nyf.uneasyguys.test.Model.ArticleModel;
import com.nyf.uneasyguys.test.Model.UserModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by sesan on 2017-12-30.
 */

public interface ArticlePostService {
    @Multipart
    @POST("/article/")
    Call<ArticleModel> postArticle(@Part("point_x") long point_x, @Part("point_y") long point_y, @Part("text") RequestBody text, @Part("point_n") long point_n, @Part("point_e") long point_e,
                                   @Part("point_w") long point_w, @Part("point_s") long point_s, @Part MultipartBody.Part image);
}
