package com.nyf.uneasyguys.test.Service;

        import com.google.gson.Gson;
        import com.nyf.uneasyguys.test.Model.ArticleModel;
        import com.nyf.uneasyguys.test.Model.PointModel;

        import java.util.List;

        import okhttp3.Credentials;
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
    private ArticlesGetService articlesGetservice;
    private ArticlePostService articlePostService;
    private PointPostService pointPostService;
    private ArticleGetService articleGetService;
    private ServiceHelper() {

        Retrofit retrofit = createAdapter().build();
        articlesGetservice = retrofit.create(ArticlesGetService.class);
        articlePostService = retrofit.create(ArticlePostService.class);
        pointPostService = retrofit.create(PointPostService.class);
        articleGetService = retrofit.create(ArticleGetService.class);

    }

    public static ServiceHelper getInstance() {
        return instance;
    }

    private Retrofit.Builder createAdapter() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        String username = "admin";
        String password = "1";
        String authToken = Credentials.basic(username, password);
        AuthenticationInterceptor authinterceptor = new AuthenticationInterceptor(authToken);
        httpClient = new OkHttpClient.Builder().addInterceptor(authinterceptor).addInterceptor(interceptor).build();
        /*
        if (!httpClient.interceptors().contains(interceptor)){
            httpClient.interceptors().add(interceptor);
        }*/

        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Call<String> postPoint(int point_x, int point_y) {
        PointModel pointModel = new PointModel(point_x,point_y);
        String object = gson.toJson(pointModel);
        return pointPostService.postPoint(point_x,point_y);}
    public Call<ArticleModel> getArticle(int id){return  articleGetService.getArticle(id); }
    public Call<ArticleModel> postArticle(long point, String text) {return articlePostService.postArticle(point, text);}
    public Call<List<ArticleModel>> getArticles() {
        return articlesGetservice.getArticles();
    }
}