package com.example.testmvvm.data.network.responses

import com.example.testmvvm.database.entities.User


data class AuthResponses (
    val token:String?,
    val user :User?
        )