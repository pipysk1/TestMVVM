package com.example.testmvvm.ui.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testmvvm.R
import com.example.testmvvm.databinding.ProfileFragmentBinding
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class ProfileFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    val factory: ProfileFragmentViewModelFactory by instance()


    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: ProfileFragmentBinding = DataBindingUtil
            .inflate(inflater, R.layout.profile_fragment, container, false)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }


}