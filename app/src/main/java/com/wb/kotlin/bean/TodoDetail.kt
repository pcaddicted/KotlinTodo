package com.wb.kotlin.bean

import java.io.Serializable

/**
 * Created by 文博 on 2018/9/21
 */
data class TodoDetail(
        val completeDate: Long?,
        val completeDateStr: String,
        val content: String,
        val date: Long,
        val dateStr: String,
        val id: Int,
        val status: Int,
        val title: String,
        val type: Int,
        val userId: Int
) : Serializable