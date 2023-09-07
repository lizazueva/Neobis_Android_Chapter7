package com.example.neobis_android_chapter7.api


import androidx.lifecycle.ViewModel
import com.example.neobis_android_chapter7.model.LoginRequest
import com.example.neobis_android_chapter7.model.UserRequest
import com.example.neobis_android_chapter7.utils.Token

class Repository: ViewModel() {

    suspend fun login (token: String, request: LoginRequest) = RetrofitInstance.api.login(token, request)
    suspend fun registration (requestRegistration: UserRequest) = RetrofitInstance.api.registration(requestRegistration)



}