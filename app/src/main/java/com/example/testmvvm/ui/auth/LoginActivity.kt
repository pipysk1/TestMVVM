package com.example.testmvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.testmvvm.R
import com.example.testmvvm.data.network.CallAPI
import com.example.testmvvm.data.network.responses.AuthResponses
import com.example.testmvvm.data.network.responses.NetworkConnectionInterceptor
import com.example.testmvvm.data.respositoris.UserReponsitory
import com.example.testmvvm.database.AppDatabase
import com.example.testmvvm.database.entities.User
import com.example.testmvvm.databinding.ActivityLoginBinding
import com.example.testmvvm.ui.home.HomeActivity
import com.example.testmvvm.ulti.hide
import com.example.testmvvm.ulti.show
import com.example.testmvvm.ulti.snackbar
import com.example.testmvvm.ulti.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val api = CallAPI(networkConnectionInterceptor)
        val db = AppDatabase(this)
        val responses = UserReponsitory(api, db)
        val factory = AuthViewModelFactory(responses)
        val bindingUtil: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
//        val viewModel = ViewModelProvider(this, factory)[AuthViewModel::class.java]
        bindingUtil.viewmodel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
//               Intent(this,HomeActivity::class.java).also {
//                   it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                   startActivity(it)
                var intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
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