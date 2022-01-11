package com.example.testmvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvm.data.respositoris.QuotesRespositoris
import com.example.testmvvm.data.respositoris.UserReponsitory


@Suppress("UNCHECKED_CAST")
class QuotesViewModelFactory(
    private val responses: QuotesRespositoris)
    : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return QuotesViewModel(responses) as T
    }
}