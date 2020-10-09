package com.ubitar.app.demo4.network

import com.ubitar.app.demo4.network.bean.UserBean
import com.ubitar.capybara.network.bean.BaseResponse
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Demo4Api {

    @FormUrlEncoded
    @POST("student/login")
    fun login(
        @Field("account") account: String,
        @Field("password") password: String
    ): Flowable<BaseResponse<UserBean>>

    @FormUrlEncoded
    @POST("student/logout")
    fun logout(@Field("account") account: String): Flowable<BaseResponse<Any>>
}