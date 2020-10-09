package com.ubitar.capybara.network

/**
 * 网络请求返回的状态码
 */
object NetworkError {
    const val SUCCESS = 200
    const val UNKNOWN = 1000
    const val PARSE_ERROR = 1001
    const val NETWORK_ERROR = 1002
}
