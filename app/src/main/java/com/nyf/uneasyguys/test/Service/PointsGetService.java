package com.nyf.uneasyguys.test.Service;

import com.nyf.uneasyguys.test.Model.ArticleModel;
import com.nyf.uneasyguys.test.Model.PointModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sesan on 2017-12-14.
 */

public interface PointsGetService {
    //@Headers( "Content-Type: application/json" ) in Post method may use this
    @GET("/point/")
    Call<List<PointModel>> getPoints();
}
