package com.example.testmvvm

import android.app.Application
import com.example.testmvvm.data.network.CallAPI
import com.example.testmvvm.data.network.responses.NetworkConnectionInterceptor
import com.example.testmvvm.data.network.responses.QuotesResponeses
import com.example.testmvvm.data.preferences.PreferenceProvider
import com.example.testmvvm.data.respositoris.QuotesRespositoris
import com.example.testmvvm.data.respositoris.UserReponsitory
import com.example.testmvvm.database.AppDatabase
import com.example.testmvvm.ui.auth.AuthViewModelFactory
import com.example.testmvvm.ui.home.profile.ProfileFragmentViewModelFactory
import com.example.testmvvm.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

open class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { CallAPI(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserReponsitory(instance(), instance() )}
        bind() from singleton { PreferenceProvider(instance() )}
//        bind() from singleton { QuotesResponeses(instance()) }
        bind() from singleton { QuotesRespositoris(instance(), instance(), instance()) }
        bind() from provider {AuthViewModelFactory(instance())  }
        bind() from provider {ProfileFragmentViewModelFactory(instance())  }
        bind() from provider {QuotesViewModelFactory(instance())  }



    }


}

