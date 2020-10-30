package com.ubitar.capybara.network;

import android.net.ParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class NetExceptionParser {

    public static HostCreator.OnGlobalException onException;

    public static ApiException parse(Throwable e) {
        ApiException exception = null;
        if (onException != null) {
            exception = onException.onException(e);
        }
        if (exception != null) {
            return exception;
        } else if (e instanceof JSONException || e instanceof ParseException) {
            return new ApiException(NetworkError.PARSE_ERROR,  "数据解析错误");
        } else if (e instanceof ConnectException) {
            return new ApiException(NetworkError.NETWORK_ERROR,  "无法连接至服务器");
        } else if (e instanceof UnknownHostException || e instanceof SocketTimeoutException) {
            return new ApiException(NetworkError.NETWORK_ERROR, "无法连接至服务器");
        } else if (e instanceof ApiException) {
            return (ApiException) e;
        } else {
            return new ApiException(NetworkError.UNKNOWN, "未知错误");
        }
    }
}
