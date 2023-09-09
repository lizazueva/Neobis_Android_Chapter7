package com.example.neobis_android_chapter7.utils

import android.content.Context
import android.content.SharedPreferences

class SharePref(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun isFirstTimeUser(): Boolean {
        return sharedPreferences.getBoolean("isFirstTimeUser", true)
    }

    fun setFirstTimeUser(isFirstTime: Boolean) {
        sharedPreferences.edit().putBoolean("isFirstTimeUser", isFirstTime).apply()
    }
}