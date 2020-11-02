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

    private Retrofit retrofit;

    public static Server create(String host, OnCreateOkHttp onCreateOkHttp) {
        return new Server(host,onCreateOkHttp);
    }

    private Server(String host, OnCreateOkHttp onCreateOkHttp) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        onCreateOkHttp.onCreate(builder);
        OkHttpClient client = builder.build();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(host)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public  <T> T createService(Class<T> c) {
        synchronized (c) {
            return retrofit.create(c);
        }
    }

    public interface OnCreateOkHttp {
        void onCreate(OkHttpClient.Builder builder);
    }

}
