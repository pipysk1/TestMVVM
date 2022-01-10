package com.example.testmvvm.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvm.data.respositoris.UserReponsitory


@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val responses: UserReponsitory

) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(responses) as T
    }
}