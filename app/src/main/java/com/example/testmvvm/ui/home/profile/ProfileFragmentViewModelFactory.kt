package com.example.testmvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvm.data.respositoris.UserReponsitory


@Suppress("UNCHECKED_CAST")
class ProfileFragmentViewModelFactory(
    private val responses: UserReponsitory) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileViewModel(responses) as T
    }
}