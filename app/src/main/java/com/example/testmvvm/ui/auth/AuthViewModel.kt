package com.example.testmvvm.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.testmvvm.data.respositoris.UserReponsitory
import com.example.testmvvm.ui.home.HomeActivity
import com.example.testmvvm.ulti.ApiException
import com.example.testmvvm.ulti.Coroutines
import com.example.testmvvm.ulti.NoInternetException

class AuthViewModel
    (
    private val reponsitory: UserReponsitory
) : ViewModel() {
    var email: String? = null
    var password: String? = null
    var passwordconfig: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = reponsitory.getUser()

    fun onSignup(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun onLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

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
               Intent(view.context,HomeActivity::class.java).also {
                    view.context.startActivity(it)
                }
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }


        }


    }

    fun onClickSignup(view: View) {
        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.onFailure("Invaled Email or Password")
            return
        }
        if (password != passwordconfig) {
            authListener?.onFailure("Password dit not match")
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