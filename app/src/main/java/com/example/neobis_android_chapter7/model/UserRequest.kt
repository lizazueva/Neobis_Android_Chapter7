package com.example.neobis_android_chapter7.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRequest(
    val email: String,
    val username: String,
    val password1: String,
    val password2: String
): Parcelable