package com.bekir.mvpbaseproject.network;

import com.bekir.mvpbaseproject.BuildConfig;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseServiceFactory {

    public static BaseService makeBaseService(String baseUrl) {
        OkHttpClient okHttpClient = makeOkHttpClient(makeLoggingInterceptor());
        return makeBaseService(baseUrl, okHttpClient);
    }

    public static BaseService makeBaseService(String baseUrl, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(BaseService.class);
    }

    public static OkHttpClient makeOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder;
                requestBuilder = original.newBuilder()
                        .addHeader("accept-encoding", "identity");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        return httpClient.addInterceptor(httpLoggingInterceptor).build();
    }

    public static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

}
