package com.nyf.uneasyguys.test.Service;

        import com.google.gson.Gson;
        import com.google.gson.JsonObject;
        import com.nyf.uneasyguys.test.Model.ArticleModel;
        import com.nyf.uneasyguys.test.Model.PointModel;

        import java.util.List;
        import java.util.concurrent.TimeUnit;

        import okhttp3.OkHttpClient;
        import okhttp3.logging.HttpLoggingInterceptor;
        import retrofit2.Call;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sesan on 2017-12-14.
 싱글톤 패턴으로 구현
 */

public class ServiceHelper {

    private static final String ENDPOINT = "http://ec2-13-125-11-141.ap-northeast-2.compute.amazonaws.com:8000";
    private Gson gson = new Gson();
    private static OkHttpClient httpClient = new OkHttpClient();
    private static ServiceHelper instance = new ServiceHelper();
    private IPlusService service;
    private ArticlePostService articlePostService;
    private PointPostService pointPostService;
    private ServiceHelper() {

        Retrofit retrofit = createAdapter().build();
        service = retrofit.create(IPlusService.class);
        articlePostService = retrofit.create(ArticlePostService.class);
        pointPostService = retrofit.create(PointPostService.class);
    }

    public static ServiceHelper getInstance() {
        return instance;
    }

    private Retrofit.Builder createAdapter() {
/*
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
*/
        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Call<String> postPoint(int point_x, int point_y) {
        PointModel pointModel = new PointModel(point_x,point_y);
        String object = gson.toJson(pointModel);
        System.out.println(object);
        return pointPostService.postPoint(object);}

    public Call<String> postArticle(long point, String text) {return articlePostService.postArticle(point, text);}
    public Call<List<ArticleModel>> getAllCategory() {
        return service.getAllCategory();
    }
}