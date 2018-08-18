package com.sesang06.retrofit.test.Service;

import com.google.gson.JsonObject;
import com.sesang06.retrofit.test.Model.PointModel;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Created by sesan on 2017-12-30.
 { "point_x" : 1, "point_y" : 4 }
 */

public interface PointPostService {
    @FormUrlEncoded
    @POST("/point/")
    Call<String> postPoint(@Field("point_x") long point_x, @Field("point_y") long point_y);
}
