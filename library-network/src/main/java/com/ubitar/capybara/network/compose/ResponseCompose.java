package com.ubitar.capybara.network.compose;


import com.ubitar.capybara.network.ApiException;
import com.ubitar.capybara.network.NetExceptionParser;
import com.ubitar.capybara.network.NetworkError;
import com.ubitar.capybara.network.NetworkManager;
import com.ubitar.capybara.network.bean.BaseResponse;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

public class ResponseCompose {

    public static NetworkManager.OnGlobalParser onParser;

    public static <T> FlowableTransformer<BaseResponse<T>, BaseResponse<T>> parseResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction<>())
                .flatMap(new ResponseFunction<>());
    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param <T>
     */
    private static class ErrorResumeFunction<T> implements Function<Throwable, Flowable<? extends BaseResponse<T>>> {

        @Override
        public Flowable<? extends BaseResponse<T>> apply(Throwable throwable) throws Exception {
            return Flowable.error(NetExceptionParser.parse(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param <T>
     */
    private static class ResponseFunction<T> implements Function<BaseResponse<T>, Flowable<BaseResponse<T>>> {

        @Override
        public Flowable<BaseResponse<T>> apply(BaseResponse<T> tResponse) throws Exception {
            int code = tResponse.getCode();
            String message = tResponse.getMsg();
            Flowable flowable = null;
            if (onParser != null) {
                flowable = onParser.onParse(tResponse);
            }
            if (flowable != null) {
                return flowable;
            } else if (code == NetworkError.SUCCESS) {
                return Flowable.just(tResponse);
            } else {
                return Flowable.error(new ApiException(code, message));
            }
        }
    }

}


