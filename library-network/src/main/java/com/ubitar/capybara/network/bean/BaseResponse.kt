package com.ubitar.capybara.network.bean

import java.util.Date

class BaseResponse<T> {

    var code = 200
    var msg = "请求成功"
    var time = Date().time
    var data: T? = null

    constructor() {}

    @JvmOverloads
    constructor(msg: String, code: Int = 200) {
        this.msg = msg
        this.code = code
    }
}
