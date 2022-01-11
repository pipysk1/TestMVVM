package com.example.testmvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.testmvvm.R
import com.example.testmvvm.database.entities.User
import com.example.testmvvm.databinding.ActivityLoginBinding
import com.example.testmvvm.ui.home.HomeActivity
import com.example.testmvvm.ulti.hide
import com.example.testmvvm.ulti.show
import com.example.testmvvm.ulti.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {
    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val bindingUtil: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
//        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        bindingUtil.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)

                }

            }
        })
    }

    override fun onStarted() {
        progress_bar.show()
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)

    }
}