package com.wb.kotlin.bean

/**
 * Created by 文博 on 2018/9/21
 */
data class HttpResult<T>(
        val errorMsg: String,
        val errorCode: Int,
        val data: T?
)