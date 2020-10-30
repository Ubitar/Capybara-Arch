package com.ubitar.capybara.network;

import com.ubitar.capybara.network.bean.BaseResponse;
import com.ubitar.capybara.network.compose.ResponseCompose;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    public static int READ_TIME_OUT = 15;
    public static int CONNECT_TIME_OUT = 15;

    private static Retrofit retrofit;

    public static Server create(String host, OnCreateOkHttp onCreateOkHttp, OnGlobalException onException, OnGlobalParser onParser) {
        return new Server(host,onCreateOkHttp,onException,onParser);
    }

    private Server(String host, OnCreateOkHttp onCreateOkHttp, OnGlobalException onException, OnGlobalParser onParser) {
        NetExceptionParser.onException = onException;
        ResponseCompose.onParser = onParser;
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

    public interface OnGlobalException {
        ApiException onException(Throwable e);
    }

    public interface OnGlobalParser<T> {
        Flowable<BaseResponse<T>> onParse(BaseResponse<T> tResponse);
    }
}
