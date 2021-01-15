package com.ubitar.capybara.network;

import com.ubitar.capybara.network.bean.IBaseResponse;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    public static int READ_TIME_OUT = 15;
    public static int CONNECT_TIME_OUT = 15;

    private final Retrofit retrofit;

    public static Server create(String host, OnCreateOkHttp onCreateOkHttp, OnCreateRetrofit onCreateRetrofit) {
        return new Server(host, onCreateOkHttp,onCreateRetrofit);
    }

    private Server(String host, OnCreateOkHttp onCreateOkHttp, OnCreateRetrofit onCreateRetrofit) {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        onCreateOkHttp.onCreate(okhttpBuilder);
        OkHttpClient client = okhttpBuilder.build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(client)
                .baseUrl(host);
        onCreateRetrofit.onCreate(retrofitBuilder);
        retrofit = retrofitBuilder.build();

    }

    public <T> T createService(Class<T> c) {
        synchronized (c) {
            return retrofit.create(c);
        }
    }

    public interface OnCreateOkHttp {
        void onCreate(OkHttpClient.Builder builder);
    }

    public interface OnCreateRetrofit {
        void onCreate(Retrofit.Builder builder);
    }

}
