package com.wb.kotlin.bean

/**
 * Created by 文博 on 2018/9/21
 */
data class LoginResponse(
        val collectIds: List<Int>,
        val email: String,
        val icon: String,
        val id: Int,
        val password: String,
        val type: Int,
        val username: String
)