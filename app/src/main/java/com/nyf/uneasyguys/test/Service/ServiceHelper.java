package com.nyf.uneasyguys.test.Service;

        import com.nyf.uneasyguys.test.Model.ArticleModel;

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

    private static final String ENDPOINT = "http://test.com";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static ServiceHelper instance = new ServiceHelper();
    private IPlusService service;


    private ServiceHelper() {

        Retrofit retrofit = createAdapter().build();
        service = retrofit.create(IPlusService.class);
    }

    public static ServiceHelper getInstance() {
        return instance;
    }

    private Retrofit.Builder createAdapter() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);

        return new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public Call<List<ArticleModel>> getAllCategory() {
        return service.getAllCategory();
    }
}