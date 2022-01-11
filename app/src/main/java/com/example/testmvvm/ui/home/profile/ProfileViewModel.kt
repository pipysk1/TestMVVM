package com.example.testmvvm.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.testmvvm.data.respositoris.UserReponsitory

class ProfileViewModel(
    reponsitory: UserReponsitory

) : ViewModel() {


    val user =reponsitory.getUser()


}