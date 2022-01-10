package com.example.testmvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.testmvvm.data.respositoris.UserReponsitory
import com.example.testmvvm.ulti.ApiException
import com.example.testmvvm.ulti.Coroutines
import com.example.testmvvm.ulti.NoInternetException

class AuthViewModel
    (
    private val reponsitory: UserReponsitory
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = reponsitory.getUser()
    fun onClickListener(view: View) {
        authListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invaled Email or Password")
            return
        }

        Coroutines.main {

            try {
                val authresponse = reponsitory.userLogin(email!!, password!!)
                authresponse.user?.let {
                    authListener?.onSuccess(it)
                    reponsitory.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authresponse.token!!)
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }


        }


    }
}