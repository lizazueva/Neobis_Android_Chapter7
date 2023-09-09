package com.example.neobis_android_chapter7.api

import com.example.neobis_android_chapter7.model.LoginRequest
import com.example.neobis_android_chapter7.model.LoginResponse
import com.example.neobis_android_chapter7.model.UserRequest
import com.example.neobis_android_chapter7.model.UserResponse
import com.example.neobis_android_chapter7.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @POST("api/token/")
    suspend fun login (@Body request: LoginRequest): Response<LoginResponse>

    @POST("/registration/")
    suspend fun registration (@Body requestRegistration: UserRequest): Response<UserResponse>
}