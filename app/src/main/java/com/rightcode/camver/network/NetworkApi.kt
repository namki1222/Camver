package com.rightcode.camver.network

import com.rightcode.ai_workplace_accident.network.model.request.result.join
import com.rightcode.ai_workplace_accident.network.model.request.result.login
import com.rightcode.camver.network.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface NetworkApi {
    //=========================================
    // auth
    // ========================================

    @GET("/v1/auth/existLoginId")
    fun Idcheck(
        @Query("loginId") loginId: String
    ): Call<CommonResponse>

    @GET("/v1/auth/certificationNumberSMS")
    fun Sendsms(
        @Query("tel") tel: String,
        @Query("diff") diff: String
    ): Call<CommonResponse>

    @GET("/v1/auth/confirm")
    fun checksms(
        @Query("tel") tel: String,
        @Query("confirm") confirm: String
    ): Call<CommonResponse>

    @POST("/v1/auth/join")
    fun join(
        @Body body: join,
    ): Call<CommonResponse>

    @POST("/v1/auth/login")
    fun login(
        @Body body: login,
    ): Call<LoginResponse>

    @GET("/v1/auth/findLoginId")
    fun find_id(
        @Query("tel") tel: String
    ): Call<FindIdResponse>

    @POST("/v1/auth/passwordChange")
    fun pwd_change(
        @Body body: PasswordChange,
    ): Call<CommonResponse>

    //=========================================
    // product
    // ========================================
    @GET("/v1/product/list")
    fun product_list(
        @Query("category") category: String,
        @Query("diff") diff: String
    ): Call<ProductListResponse>
    @GET("/v1/product/detail")
    fun product_detail(
        @Query("id") id: Int
    ): Call<ProductdetailResponse>
}