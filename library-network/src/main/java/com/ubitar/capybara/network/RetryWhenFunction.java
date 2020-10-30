package com.ubitar.capybara.network;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

public class RetryWhenFunction implements Function<Flowable<? extends Throwable>, Flowable<?>> {

    //重试延迟时间
    private int delayTime = 3000;
    //重试次数
    private int maxRetryCount = 3;
    private int currentTime = 0;

    public RetryWhenFunction() {

    }

    public RetryWhenFunction(int delayTime, int maxRetryCount) {
        this.delayTime = delayTime;
        this.maxRetryCount = maxRetryCount;
    }

    @Override
    public Flowable<?> apply(Flowable<? extends Throwable> observable) throws Exception {
        return observable.flatMap(new Function<Throwable, Flowable<?>>() {
            @Override
            public Flowable<?> apply(Throwable throwable) throws Exception {
                if (throwable instanceof ApiException) {
                    ApiException exception = (ApiException) throwable;
                    if (exception.getCode() == HttpError.NETWORK_ERROR) {
                        if (currentTime++ < maxRetryCount) {
                            return Flowable.just(currentTime).delay(delayTime, TimeUnit.MILLISECONDS);
                        }
                    }
                }
                return Flowable.error(throwable);
            }
        });
    }

}