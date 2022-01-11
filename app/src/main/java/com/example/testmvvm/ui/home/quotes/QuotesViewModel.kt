package com.example.testmvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.testmvvm.data.network.responses.QuotesResponeses
import com.example.testmvvm.data.respositoris.QuotesRespositoris
import com.example.testmvvm.data.respositoris.UserReponsitory
import com.example.testmvvm.ulti.lazyDeferred

class QuotesViewModel(
    repository: QuotesRespositoris
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}