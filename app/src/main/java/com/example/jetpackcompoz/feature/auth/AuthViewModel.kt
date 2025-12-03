package com.example.jetpackcompoz.feature.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var isLoggedIn by mutableStateOf(false)
        private set

    fun updateLoginStatus(value: Boolean) {
        isLoggedIn = value
    }
}