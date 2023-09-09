package com.example.neobis_android_chapter7.api


import com.example.neobis_android_chapter7.model.LoginRequest
import com.example.neobis_android_chapter7.model.UserRequest
import com.example.neobis_android_chapter7.utils.Constants.Companion.BASE_URL

class Repository {

    suspend fun login (request: LoginRequest) = RetrofitInstance.api.login(request)
    suspend fun registration (requestRegistration: UserRequest) = RetrofitInstance.api.registration(requestRegistration)

}