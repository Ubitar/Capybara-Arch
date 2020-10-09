package com.ubitar.app.demo4.network.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserBean(
    val id: Int,
    var account: String,
    var password: String,
    var token: String,
    var headview: String,
    var name: String,
    var mobile: String,
    var idCard: String,
    var gender: Int,
    var birthday: Long,
    var province: String,
    var city: String,
    var buildTime: Long
) : Parcelable
