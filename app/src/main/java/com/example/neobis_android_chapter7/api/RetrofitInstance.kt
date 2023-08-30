package com.example.neobis_android_chapter7.api

import com.example.neobis_android_chapter7.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val api: Api by lazy {
            retrofit.create(Api::class.java)
        }
    }
}