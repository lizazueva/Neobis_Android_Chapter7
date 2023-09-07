package com.example.neobis_android_chapter7.api

import com.example.neobis_android_chapter7.utils.Constants.Companion.BASE_URL
import com.example.neobis_android_chapter7.utils.Token
import com.example.neobis_android_chapter7.viewModel.MyViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val tokenInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", Token.token ?: "Bearer ")
                .build()
            chain.proceed(request)
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(tokenInterceptor)
            .build()

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: Api by lazy {
            retrofit.create(Api::class.java)
        }
    }
}