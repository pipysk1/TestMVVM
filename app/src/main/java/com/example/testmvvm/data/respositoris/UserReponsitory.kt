package com.example.testmvvm.data.respositoris

import com.example.testmvvm.data.network.CallAPI
import com.example.testmvvm.data.network.responses.AuthResponses
import com.example.testmvvm.data.network.responses.SafeApiRequest
import com.example.testmvvm.database.AppDatabase
import com.example.testmvvm.database.entities.User
import retrofit2.Response

class UserReponsitory
    (
    private val api: CallAPI,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun userLogin(email: String, password: String): AuthResponses {
        return apiRequest { api.userLogin(email, password) }

    }

    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)


    fun getUser() = db.getUserDao().getuser()
}