package com.wb.kotlin.bean

/**
 * Created by 文博 on 2018/9/21
 */
data class ListResponse(
        val curPage: Int,
        val datas: List<TodoDetail>,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val size: Int,
        val total: Int
)