package com.example.testmvvm.ui.auth

import com.example.testmvvm.database.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user:User)
    fun onFailure(message: String)

}