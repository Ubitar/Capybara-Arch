package com.ubitar.app.common

import android.net.ParseException
import com.ubitar.capybara.network.ApiException
import com.ubitar.capybara.network.bean.IBaseResponse
import com.ubitar.capybara.network.compose.AResponseCompose
import io.reactivex.Flowable
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


class AppResponseCompose : AResponseCompose() {
    override fun onException(e: Throwable?): ApiException {
        //自定义统一处理网络请求码错误
        //返回null表示不添加自定义处理规则
        if (e is JSONException || e is ParseException) {
            return ApiException(HttpError.PARSE_ERROR, "数据解析错误")
        } else if (e is ConnectException) {
            return ApiException(HttpError.NETWORK_ERROR, "无法连接至服务器")
        } else if (e is UnknownHostException || e is SocketTimeoutException) {
            return ApiException(HttpError.NETWORK_ERROR, "无法连接至服务器")
        } else if (e is ApiException) {
            return e
        } else {
            return ApiException(HttpError.UNKNOWN, "未知错误")
        }
    }

    override fun onParse(response: IBaseResponse?): Flowable<IBaseResponse>? {
        //自定义处理网络接口返回值
        //返回null表示不添加自定义处理规则
        //  it.setValue(xx)
        //return Flowable.just(it)
        return null
    }
}