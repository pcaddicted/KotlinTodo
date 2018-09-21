package com.wb.kotlin.network

import com.wb.kotlin.bean.*
import io.reactivex.Observable
import retrofit2.http.*

/**
 * Created by 文博 on 2018/9/21
 */
interface API {
    /**
     * 注册
     * @param username       用户名
     * @param password       密码
     * @param verifyPassword 验证密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username") username: String, @Field("password") password: String, @Field("repassword") verifyPassword: String): Observable<HttpResult<LoginResponse>>

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username") username: String, @Field("password") password: String): Observable<HttpResult<LoginResponse>>

    /**
     * 返回列表数据
     * @param type 类型
     * @return
     */
    @GET("lg/todo/list/{type}/json")
    fun list(@Path("type") type: Int): Observable<HttpResult<AllListResponse>>

    /**
     * 已完成Todo列表
     * @param type 类型
     * @param page 页码
     * @return
     */
    @GET("lg/todo/listdone/{type}/json/{page}")
    fun listDone(@Path("type") type: Int, @Path("page") page: Int): Observable<HttpResult<ListResponse>>

    /**
     * 未完成Todo列表
     * @param type 类型
     * @param page 页码
     * @return
     */
    @GET("lg/todo/listnotdo/{type}/json/{page}")
    fun listNotDo(@Path("type") type: Int, @Path("page") page: Int): Observable<HttpResult<ListResponse>>

    /**
     * 新增一条Todo
     * @param title
     * @param content
     * @param date
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("lg/todo/add/json")
    fun add(@Field("title") title: String, @Field("content") content: String, @Field("date") date: String, @Field("type") type: Int): Observable<HttpResult<TodoDetail>>

    /**
     * 删除
     * @param id
     * @return
     */
    @POST("lg/todo/delete/{id}/json")
    fun delete(@Path("id") id: Int): Observable<HttpResult<Unit>>

    /**
     * 仅更新完成状态Todo
     * @param id
     * @param status
     * @return
     */
    @FormUrlEncoded
    @POST("lg/todo/done/{id}/json")
    fun done(@Path("id") id: Int, @Field("status") status: Int): Observable<HttpResult<TodoDetail>>

    /**
     * 更新一条Todo内容
     * @param id
     * @param title
     * @param content
     * @param date
     * @param status
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("lg/todo/update/{id}/json")
    fun update(@Path("id") id: Int, @Field("title") title: String, @Field("content") content: String, @Field("date") date: String, @Field("status") status: Int, @Field("type") type: Int): Observable<HttpResult<TodoDetail>>

}