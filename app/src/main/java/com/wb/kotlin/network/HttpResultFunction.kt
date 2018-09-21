package com.wb.kotlin.network

import com.wb.kotlin.bean.HttpResult
import io.reactivex.functions.Function

/**
 * Created by 文博 on 2018/9/21
 */
class HttpResultFunction<T> : Function<HttpResult<T>, T> {

    override fun apply(t: HttpResult<T>): T? {
        if (t.errorCode < 0) {
            throw ApiException(t.errorMsg)
        }
        return t.data
    }

    class ApiException constructor(message: String) : Exception(message)
}