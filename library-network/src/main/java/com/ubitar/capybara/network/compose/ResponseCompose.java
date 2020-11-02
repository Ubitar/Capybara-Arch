package com.ubitar.capybara.network.compose;


import com.ubitar.capybara.network.ApiException;
import com.ubitar.capybara.network.bean.IBaseResponse;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Function;

public abstract class ResponseCompose {

    public  <T> FlowableTransformer<IBaseResponse, T> parseResult() {
        return upstream -> upstream
                .onErrorResumeNext(new ResponseCompose.ErrorResumeFunction())
                .flatMap(new ResponseCompose.ResponseFunction());
    }

    /**
     * 非服务器产生的异常，比如本地无无网络请求，Json数据解析错误等等。
     *
     * @param
     */
    private  class ErrorResumeFunction implements Function<Throwable, Flowable<? extends IBaseResponse>> {

        @Override
        public Flowable<? extends IBaseResponse> apply(Throwable throwable) throws Exception {
            ApiException exception = null;
                exception = onException(throwable);
            if (exception != null) {
                return Flowable.error(exception);
            }
            return Flowable.error(throwable);
        }
    }

    /**
     * 服务其返回的数据解析
     * 正常服务器返回数据和服务器可能返回的exception
     *
     * @param
     */
    private  class ResponseFunction<T extends IBaseResponse> implements Function<IBaseResponse, Flowable<T>> {

        @Override
        public Flowable<T> apply(IBaseResponse tResponse) throws Exception {
            Flowable flowable = onParse(tResponse);
            if (flowable != null) {
                return flowable;
            } else {
                return (Flowable<T>) Flowable.just(tResponse);
            }
        }
    }

    abstract protected ApiException onException(Throwable e);

    abstract protected Flowable<IBaseResponse> onParse(IBaseResponse response);

}


