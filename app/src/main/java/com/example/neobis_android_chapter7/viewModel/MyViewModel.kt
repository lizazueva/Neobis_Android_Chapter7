package com.example.neobis_android_chapter7.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neobis_android_chapter7.api.Repository
import com.example.neobis_android_chapter7.model.LoginRequest
import com.example.neobis_android_chapter7.model.LoginResponse
import com.example.neobis_android_chapter7.model.UserRequest
import com.example.neobis_android_chapter7.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class MyViewModel(private var repository: Repository): ViewModel() {


    private val _token: MutableLiveData<Resource<String>> = MutableLiveData()
    val token: LiveData<Resource<String>>
        get() = _token

    private fun saveToken(token: String) {
        _token.postValue(Resource.Success(token))
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(username, password)
                val response = repository.login("Bearer $token", loginRequest)
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    loginResponse?.access?.let { saveToken(it) }
                }
            } catch (e: Exception) {
                _token.postValue(Resource.Error(e.message ?: "Ошибка авторизации"))
            }
        }
    }

    fun newUser(email: String, username: String, password1: String, password2: String) {
        viewModelScope.launch {
            val userRequest = UserRequest(email, username, password1, password2)
            val response = repository.registration(userRequest)
            if (response.isSuccessful) {

            } else {

            }
        }
    }
}
