package com.example.testmvvm

import android.app.Application
import com.example.testmvvm.data.network.CallAPI
import com.example.testmvvm.data.network.responses.NetworkConnectionInterceptor
import okhttp3.internal.Internal.instance
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

open class MVVMApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))

    bind() from singleton{ NetworkConnectionInterceptor(instance()) }
        bind() from singleton { CallAPI(instance()) }
        

    }


}

