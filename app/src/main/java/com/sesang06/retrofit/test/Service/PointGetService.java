package com.sesang06.retrofit.test.Service;


import com.sesang06.retrofit.test.Model.ArticleModel;
import com.sesang06.retrofit.test.Model.PointModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sesan on 2017-12-30.
 { "point_x" : 1, "point_y" : 4 }
 */

public interface PointGetService {
    //@Headers( "Content-Type: application/json" ) in Post method may use this
    @GET("/point/{id}/")
    Call<PointModel> getPoint(@Path("id") long id);
}
