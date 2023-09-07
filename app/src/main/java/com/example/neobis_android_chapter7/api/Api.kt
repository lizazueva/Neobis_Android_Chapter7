package com.example.neobis_android_chapter7.api

import com.example.neobis_android_chapter7.model.LoginRequest
import com.example.neobis_android_chapter7.model.LoginResponse
import com.example.neobis_android_chapter7.model.UserRequest
import com.example.neobis_android_chapter7.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Content-type: application/json")
    @POST("/login/")
    suspend fun login (@Header("Authorization") token: String, @Body request: LoginRequest): LoginResponse

    @POST("/registration/")
    suspend fun registration (@Body requestRegistration: UserRequest): Call<Unit>
}