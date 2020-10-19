package com.ubitar.capybara.network.interceptor

import com.socks.library.KLog
import okhttp3.*
import okio.Buffer
import java.net.URLDecoder

/** json转换成字符串并格式化输出 */
class LoggerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestStr = getRequestLogString(request)
        val startTime = System.nanoTime()
        val response = chain.proceed(request)
        val endTime = System.nanoTime()
        KLog.d(TAG, String.format("Request: %s \r\n @%s %s \r\n response in %.1fms", request.url(), request.method(), requestStr, (endTime - startTime) / 1e6))
        return getResponseLogString(response)
    }

    /** 打印请求参数 */
    private fun getRequestLogString(request: Request): String {
        val method = request.method()
        val sb = StringBuilder()
        if ("POST" == method) {
            sb.append("{\n")
            val body = request.body()
            if (body == null) {
                sb.append("body is empty!!!")
            } else {
                val buffer = Buffer()
                if (body is MultipartBody) {
                    body.parts().forEachIndexed { index, it ->
                        val partBody = it.body()
                        if (partBody.contentType() != null && isText(partBody.contentType()!!)) {
                            if (index > 0) sb.append("\n")
                            val headers = it.headers()
                            for (i in 0 until headers?.size()!!) {
                                val line = headers.value(i)
                                val nameIndex = line.indexOf("name=")
                                if (nameIndex >= 0) {
                                    sb.append(line.subSequence(nameIndex + 6, line.length - 1))
                                    break
                                }
                            }
                            buffer.clear()
                            partBody.writeTo(buffer)
                            val params = URLDecoder.decode(buffer.readUtf8(), "UTF-8")
                            sb.append(":${params}")
                        }
                    }
                } else if (body is FormBody) {
                    for (i in 0 until body.size()) {
                        if (i > 0) sb.append("\n")
                        sb.append(body.encodedName(i))
                        val value = body.encodedValue(i)
                        if (value.length > 10000) sb.append(":The Value Is Too Long,ignored!!")
                        else sb.append(":${value}")
                    }
                } else {
                    body.writeTo(buffer)
                    var params = URLDecoder.decode(buffer.readUtf8(), "UTF-8")
                    params = params.replace("&", "\n")
                    sb.append(params.replace("=", ":"))
                }
            }
            sb.append("\n}")
        }
        return sb.toString()
    }

    /** 打印返回参数  */
    private fun getResponseLogString(response: Response): Response {
        try {
            val builder = response.newBuilder()
            val clone = builder.build()
            var body = clone.body()
            if (body != null) {
                val mediaType = body.contentType()
                if (mediaType != null) {
                    KLog.d(TAG, mediaType.toString())
                    if (isText(mediaType)) {
                        val resp = body.string()
                        KLog.json(TAG, resp)
                        body = ResponseBody.create(mediaType, resp)
                        return response.newBuilder().body(body).build()
                    } else {
                        KLog.d(TAG, "responseBody's content : " + " maybe [file part] , too large too print , ignored!")
                    }
                }
            }
        } catch (e: Exception) {
            //            e.printStackTrace()
        }
        return response
    }

    private fun isText(mediaType: MediaType): Boolean {
        if (mediaType.type() == "text") {
            return true
        }
        if (mediaType.subtype() == "json" ||
            mediaType.subtype() == "xml" ||
            mediaType.subtype() == "html" ||
            mediaType.subtype() == "webviewhtml")
            return true
        return false
    }

    companion object {
        const val TAG = "----NETWORK----"
    }
}