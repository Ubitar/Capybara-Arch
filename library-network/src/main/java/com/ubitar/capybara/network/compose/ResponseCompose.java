package com.ubitar.capybara.network.compose;


import com.ubitar.capybara.network.ApiException;
import com.ubitar.capybara.network.NetExceptionParser;
import com.ubitar.capybara.network.HttpError;
import com.ubitar.capybara.network.Server;
import com.ubitar.capybara.network.bean.IBaseResponse;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

public class ResponseCompose {

    public static Server.OnGlobalParser onParser;

    public static FlowableTransformer<IBaseResponse, IBaseResponse> parseResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ErrorResumeFunction())
                .flatMap(new ResponseFunction());
    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param
     */
    private static class ErrorResumeFunction implements Function<Throwable, Flowable<? extends IBaseResponse>> {

        @Override
        public Flowable<? extends IBaseResponse> apply(Throwable throwable) throws Exception {
            return Flowable.error(NetExceptionParser.parse(throwable));
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param
     */
    private static class ResponseFunction implements Function<IBaseResponse, Flowable<IBaseResponse>> {

        @Override
        public Flowable<IBaseResponse> apply(IBaseResponse tResponse) throws Exception {
            Flowable flowable = null;
            if (onParser != null) {
                flowable = onParser.onParse(tResponse);
            }
            if (flowable != null) {
                return flowable;
            } else {
                return Flowable.just(tResponse);
            }
        }
    }

}


