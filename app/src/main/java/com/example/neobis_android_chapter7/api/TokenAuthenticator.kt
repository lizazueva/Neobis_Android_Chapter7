package com.example.neobis_android_chapter7.api

import com.example.neobis_android_chapter7.utils.Token
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val token = Token.token
        if (token != null) {
            return response.request.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        }
        return null
    }
}